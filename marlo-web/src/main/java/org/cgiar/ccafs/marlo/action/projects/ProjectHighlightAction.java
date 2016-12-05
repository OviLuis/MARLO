/*****************************************************************
 * This file is part of CCAFS Planning and Reporting Platform.
 * CCAFS P&R is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * at your option) any later version.
 * CCAFS P&R is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with CCAFS P&R. If not, see <http://www.gnu.org/licenses/>.
 *****************************************************************/
package org.cgiar.ccafs.marlo.action.projects;


import org.cgiar.ccafs.marlo.action.BaseAction;
import org.cgiar.ccafs.marlo.config.APConstants;
import org.cgiar.ccafs.marlo.data.manager.AuditLogManager;
import org.cgiar.ccafs.marlo.data.manager.CrpManager;
import org.cgiar.ccafs.marlo.data.manager.FileDBManager;
import org.cgiar.ccafs.marlo.data.manager.LocElementManager;
import org.cgiar.ccafs.marlo.data.manager.ProjectHighligthManager;
import org.cgiar.ccafs.marlo.data.manager.ProjectManager;
import org.cgiar.ccafs.marlo.data.model.Crp;
import org.cgiar.ccafs.marlo.data.model.LocElement;
import org.cgiar.ccafs.marlo.data.model.Project;
import org.cgiar.ccafs.marlo.data.model.ProjectHighlightsTypeEnum;
import org.cgiar.ccafs.marlo.data.model.ProjectHighligth;
import org.cgiar.ccafs.marlo.data.model.ProjectHighligthCountry;
import org.cgiar.ccafs.marlo.data.model.ProjectHighligthType;
import org.cgiar.ccafs.marlo.data.model.ProjectStatusEnum;
import org.cgiar.ccafs.marlo.security.Permission;
import org.cgiar.ccafs.marlo.utils.APConfig;
import org.cgiar.ccafs.marlo.utils.AutoSaveReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Sebastian Amariles G.
 * @author Christian Garcia O.
 */
public class ProjectHighlightAction extends BaseAction {

  private static final long serialVersionUID = 6921586701429004011L;

  // LOG
  private static Logger LOG = LoggerFactory.getLogger(ProjectHighlightAction.class);
  private CrpManager crpManager;


  private Crp loggedCrp;


  private String transaction;


  private AuditLogManager auditLogManager;


  // Manager
  private ProjectManager projectManager;


  private ProjectHighligthManager projectHighLightManager;

  private FileDBManager fileDBManager;
  private LocElementManager locElementManager;
  private String highlightsImagesUrl;
  private File file;
  // private ProjectHighLightValidator validator;
  private String fileFileName;
  private String contentType;
  // Model for the back-end
  private ProjectHighligth highlight;
  private Project project;
  // Model for the front-end
  private long highlightID;
  private long projectID;


  private Map<String, String> highlightsTypes;


  private Map<String, String> statuses;


  private List<Integer> allYears;

  private List<LocElement> countries;
  private List<ProjectHighligthType> previewTypes;


  private List<ProjectHighligthCountry> previewCountries;


  @Inject
  public ProjectHighlightAction(APConfig config, ProjectManager projectManager,
    ProjectHighligthManager highLightManager, LocElementManager locElementManager, CrpManager crpManager,
    AuditLogManager auditLogManager, FileDBManager fileDBManager) {
    super(config);
    this.projectManager = projectManager;
    this.projectHighLightManager = highLightManager;
    this.locElementManager = locElementManager;
    this.auditLogManager = auditLogManager;
    this.crpManager = crpManager;
    this.fileDBManager = fileDBManager;


  }

  @Override
  public String cancel() {

    Path path = this.getAutoSaveFilePath();

    if (path.toFile().exists()) {

      boolean fileDeleted = path.toFile().delete();
    }

    this.setDraft(false);
    Collection<String> messages = this.getActionMessages();
    if (!messages.isEmpty()) {
      String validationMessage = messages.iterator().next();
      this.setActionMessages(null);
      this.addActionMessage("draft:" + this.getText("cancel.autoSave"));
    } else {
      this.addActionMessage("draft:" + this.getText("cancel.autoSave"));
    }
    messages = this.getActionMessages();

    return SUCCESS;
  }

  public List<Integer> getAllYears() {
    return allYears;
  }

  private String getAnualReportRelativePath() {
    return config.getProjectsBaseFolder(this.getCrpSession()) + File.separator + project.getId() + File.separator
      + "hightlihts" + File.separator;
  }


  private Path getAutoSaveFilePath() {
    String composedClassName = highlight.getClass().getSimpleName();
    String actionFile = this.getActionName().replace("/", "_");
    String autoSaveFile = highlight.getId() + "_" + composedClassName + "_" + actionFile + ".json";

    return Paths.get(config.getAutoSaveFolder() + autoSaveFile);
  }


  public String getContentType() {
    return contentType;
  }

  public List<LocElement> getCountries() {
    return countries;
  }

  public int getEndYear() {
    DateFormat dateFormat = new SimpleDateFormat("yyyy");
    return Integer.parseInt(dateFormat.format(project.getEndDate()));
  }


  public File getFile() {
    return file;
  }


  public String getFileFileName() {
    return fileFileName;
  }

  public ProjectHighligth getHighlight() {
    return highlight;
  }

  public long getHighlightID() {
    return highlightID;
  }


  public String getHighlightsImagesUrl() {
    return config.getDownloadURL() + "/" + this.getHighlightsImagesUrlPath().replace('\\', '/');
  }


  public String getHighlightsImagesUrlPath() {
    return config.getProjectsBaseFolder(this.getCrpSession()) + File.separator + project.getId() + File.separator
      + "hightlightsImage" + File.separator;
  }


  public Map<String, String> getHighlightsTypes() {
    return highlightsTypes;
  }


  private String getHightlightImagePath() {
    return config.getUploadsBaseFolder() + File.separator + this.getHighlightsImagesUrlPath() + File.separator;
  }


  public Crp getLoggedCrp() {
    return loggedCrp;
  }


  public List<ProjectHighligthCountry> getPreviewCountries() {
    return previewCountries;
  }


  public List<ProjectHighligthType> getPreviewTypes() {
    return previewTypes;
  }


  public Project getProject() {
    return project;
  }


  public long getProjectID() {
    return projectID;
  }


  public Map<String, String> getStatuses() {
    return statuses;
  }


  public String getTransaction() {
    return transaction;
  }


  @Override
  public String next() {
    return SUCCESS;
  }


  @Override
  public void prepare() throws Exception {

    super.prepare();
    previewTypes = new ArrayList<>();
    loggedCrp = (Crp) this.getSession().get(APConstants.SESSION_CRP);
    loggedCrp = crpManager.getCrpById(loggedCrp.getId());
    highlightID = Integer.parseInt(StringUtils.trim(this.getRequest().getParameter(APConstants.HIGHLIGHT_REQUEST_ID)));
    if (this.getRequest().getParameter(APConstants.TRANSACTION_ID) != null) {


      transaction = StringUtils.trim(this.getRequest().getParameter(APConstants.TRANSACTION_ID));
      ProjectHighligth history = (ProjectHighligth) auditLogManager.getHistory(transaction);

      if (history != null) {
        highlight = history;
      } else {
        this.transaction = null;

        this.setTransaction("-1");
      }

    } else {
      this.highlight = projectHighLightManager.getProjectHighligthById(highlightID);
    }


    if (highlight != null) {

      project = projectManager.getProjectById(highlight.getProject().getId());
      projectID = project.getId();

      Path path = this.getAutoSaveFilePath();

      if (path.toFile().exists() && this.getCurrentUser().isAutoSave()) {

        BufferedReader reader = null;

        reader = new BufferedReader(new FileReader(path.toFile()));

        Gson gson = new GsonBuilder().create();


        JsonObject jReader = gson.fromJson(reader, JsonObject.class);

        AutoSaveReader autoSaveReader = new AutoSaveReader();

        highlight = (ProjectHighligth) autoSaveReader.readFromJson(jReader);
        if (highlight.getCountries() != null) {
          for (ProjectHighligthCountry projectHighligthCountry : highlight.getCountries()) {
            projectHighligthCountry
              .setLocElement(locElementManager.getLocElementById(projectHighligthCountry.getLocElement().getId()));
          }
        }
        if (highlight.getFile() != null) {
          if (highlight.getFile().getId() != null) {
            highlight.setFile(fileDBManager.getFileDBById(highlight.getFile().getId()));
          } else {
            highlight.setFile(null);
          }
        }
        this.setDraft(true);
      } else {

        highlight.setCountries(
          highlight.getProjectHighligthCountries().stream().filter(c -> c.isActive()).collect(Collectors.toList()));
        highlight.setTypes(
          highlight.getProjectHighligthsTypes().stream().filter(c -> c.isActive()).collect(Collectors.toList()));
        this.setDraft(false);
      }

    }


    // Getting highlights Types
    highlightsTypes = new HashMap<>();
    List<ProjectHighlightsTypeEnum> list = Arrays.asList(ProjectHighlightsTypeEnum.values());
    for (ProjectHighlightsTypeEnum ProjectHighlightsType : list) {
      highlightsTypes.put(ProjectHighlightsType.getId(), ProjectHighlightsType.getDescription());
    }

    // Getting statuses
    statuses = new HashMap<>();
    List<ProjectStatusEnum> statusesList = Arrays.asList(ProjectStatusEnum.values());
    for (ProjectStatusEnum projectStatusEnum : statusesList) {
      statuses.put(projectStatusEnum.getStatusId(), projectStatusEnum.getStatus());
    }

    // Getting all years from project
    allYears = project.getAllYears();
    List<Integer> listYears = new ArrayList<Integer>();
    for (int i = 0; i < allYears.size(); i++) {
      if ((allYears.get(i) <= this.getCurrentCycleYear())) {
        listYears.add(allYears.get(i));
      }
    }
    allYears.clear();
    allYears.addAll(listYears);

    // Getting countries list
    countries = locElementManager.findAll().stream().filter(c -> c.getLocElementType().getId().intValue() == 2)
      .collect(Collectors.toList());

    String params[] = {loggedCrp.getAcronym(), project.getId() + ""};
    this.setBasePermission(this.getText(Permission.PROJECT_DELIVERABLE_BASE_PERMISSION, params));


    if (this.isHttpPost()) {
      if (highlight.getTypes() != null) {
        highlight.getTypes().clear();
      }


      if (highlight.getCountries() != null) {
        highlight.getCountries().clear();

      }


    }

  }


  @Override
  public String save() {

    if (this.hasPermission("canEdit")) {

      DateFormat dateformatter = new SimpleDateFormat(APConstants.DATE_FORMAT);

      Path path = this.getAutoSaveFilePath();

      if (highlight.getFile().getId() == null) {
        highlight.setFile(null);
      } else {
        highlight.setFile(highlight.getFile());
      }
      highlight.setProject(project);

      List<String> relationsName = new ArrayList<>();
      relationsName.add(APConstants.PROJECT_DELIVERABLE_PARTNERSHIPS_RELATION);
      relationsName.add(APConstants.PROJECT_DELIVERABLE_FUNDING_RELATION);
      ProjectHighligth highlightDB = projectHighLightManager.getProjectHighligthById(highlightID);
      highlight.setActiveSince(new Date());
      highlight.setModifiedBy(this.getCurrentUser());
      highlight.setModificationJustification(this.getJustification());
      highlight.setCreatedBy(highlightDB.getCreatedBy());

      projectHighLightManager.saveProjectHighligth(highlight);

      // Get the validation messages and append them to the save message

      if (path.toFile().exists()) {
        path.toFile().delete();
      }

      if (this.getUrl() == null || this.getUrl().isEmpty()) {
        Collection<String> messages = this.getActionMessages();
        if (!this.getInvalidFields().isEmpty()) {
          this.setActionMessages(null);
          // this.addActionMessage(Map.toString(this.getInvalidFields().toArray()));
          List<String> keys = new ArrayList<String>(this.getInvalidFields().keySet());
          for (String key : keys) {
            this.addActionMessage(key + ": " + this.getInvalidFields().get(key));
          }

        } else {
          this.addActionMessage("message:" + this.getText("saving.saved"));
        }
        return SUCCESS;
      } else {
        this.addActionMessage("");
        this.setActionMessages(null);
        return REDIRECT;
      }
    }
    return NOT_AUTHORIZED;
  }


  public void setAllYears(List<Integer> allYears) {
    this.allYears = allYears;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }


  public void setCountries(List<LocElement> countries) {
    this.countries = countries;
  }

  public void setFile(File file) {
    this.file = file;
  }

  public void setFileFileName(String fileFileName) {
    this.fileFileName = fileFileName;
  }


  public void setHighlight(ProjectHighligth highlight) {
    this.highlight = highlight;
  }

  public void setHighlightID(long highlightID) {
    this.highlightID = highlightID;
  }

  public void setHighlightsImagesUrl(String highlightsImagesUrl) {
    this.highlightsImagesUrl = highlightsImagesUrl;
  }


  public void setHighlightsTypes(Map<String, String> highlightsTypes) {
    this.highlightsTypes = highlightsTypes;
  }

  public void setLoggedCrp(Crp loggedCrp) {
    this.loggedCrp = loggedCrp;
  }

  public void setPreviewCountries(List<ProjectHighligthCountry> previewCountries) {
    this.previewCountries = previewCountries;
  }


  public void setPreviewTypes(List<ProjectHighligthType> previewTypes) {
    this.previewTypes = previewTypes;
  }


  public void setProject(Project project) {
    this.project = project;
  }


  public void setProjectID(long projectID) {
    this.projectID = projectID;
  }

  public void setStatuses(Map<String, String> statuses) {
    this.statuses = statuses;
  }

  public void setTransaction(String transaction) {
    this.transaction = transaction;
  }


  @Override
  public void validate() {

    if (save) {
      // validator.validate(this, project, highlight, this.getCycleName());
    }
  }
}
