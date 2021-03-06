/*****************************************************************
 * This file is part of Managing Agricultural Research for Learning &
 * Outcomes Platform (MARLO).
 * MARLO is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * at your option) any later version.
 * MARLO is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with MARLO. If not, see <http://www.gnu.org/licenses/>.
 *****************************************************************/

package org.cgiar.ccafs.marlo.action.summaries;

import org.cgiar.ccafs.marlo.config.APConstants;
import org.cgiar.ccafs.marlo.data.manager.CaseStudyManager;
import org.cgiar.ccafs.marlo.data.manager.GlobalUnitManager;
import org.cgiar.ccafs.marlo.data.manager.PhaseManager;
import org.cgiar.ccafs.marlo.data.model.CaseStudy;
import org.cgiar.ccafs.marlo.data.model.CaseStudyIndicator;
import org.cgiar.ccafs.marlo.data.model.CaseStudyProject;
import org.cgiar.ccafs.marlo.data.model.GlobalUnitProject;
import org.cgiar.ccafs.marlo.data.model.Project;
import org.cgiar.ccafs.marlo.utils.APConfig;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.dispatcher.Parameter;
import org.pentaho.reporting.engine.classic.core.ClassicEngineBoot;
import org.pentaho.reporting.engine.classic.core.CompoundDataFactory;
import org.pentaho.reporting.engine.classic.core.Element;
import org.pentaho.reporting.engine.classic.core.ItemBand;
import org.pentaho.reporting.engine.classic.core.MasterReport;
import org.pentaho.reporting.engine.classic.core.SubReport;
import org.pentaho.reporting.engine.classic.core.TableDataFactory;
import org.pentaho.reporting.engine.classic.core.modules.output.pageable.pdf.PdfReportUtil;
import org.pentaho.reporting.engine.classic.core.modules.output.table.xls.ExcelReportUtil;
import org.pentaho.reporting.engine.classic.core.util.TypedTableModel;
import org.pentaho.reporting.libraries.resourceloader.Resource;
import org.pentaho.reporting.libraries.resourceloader.ResourceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Hermes Jiménez - CIAT/CCAFS
 */
public class CaseStudySummaryAction extends BaseSummariesAction implements Summary {


  private static final long serialVersionUID = 1L;
  private static Logger LOG = LoggerFactory.getLogger(CaseStudySummaryAction.class);

  // Managers
  private CaseStudyManager caseStudyManager;

  // PDF bytes
  private byte[] bytesPDF;
  // XLSX bytes
  private byte[] bytesXLSX;

  // Streams
  InputStream inputStream;


  // Parameters
  private long startTime;

  private String selectedFormat;

  @Inject
  public CaseStudySummaryAction(APConfig config, CaseStudyManager caseStudyManager, GlobalUnitManager crpManager,
    PhaseManager phaseManager) {
    super(config, crpManager, phaseManager);
    this.caseStudyManager = caseStudyManager;
  }

  /**
   * Method to add i8n parameters to masterReport in Pentaho
   * 
   * @param masterReport
   * @return masterReport with i8n parameters added
   */
  private MasterReport addi8nParameters(MasterReport masterReport) {
    masterReport.getParameterValues().put("i8nCaseStudies", this.getText("breadCrumb.menu.caseStudies"));
    masterReport.getParameterValues().put("i8nNoData", this.getText("caseStudy.noData"));
    masterReport.getParameterValues().put("i8nCaseStudies", this.getText("projects.menu.caseStudies"));
    masterReport.getParameterValues().put("i8nProjects", this.getText("caseStudy.projects"));
    masterReport.getParameterValues().put("i8nOutcomeStatement", this.getText("caseStudy.outcomeStatement.readText"));
    masterReport.getParameterValues().put("i8nResearchOutput", this.getText("caseStudy.researchOutput.readText"));
    masterReport.getParameterValues().put("i8nResearchPartners", this.getText("caseStudy.researchPartners.readText"));
    masterReport.getParameterValues().put("i8nActivitiesContributed",
      this.getText("caseStudy.activitiesContributed.readText"));
    masterReport.getParameterValues().put("i8nNonResearchPartners",
      this.getText("caseStudy.nonResearchPartners.readText"));
    masterReport.getParameterValues().put("i8nOutputUsers", this.getText("caseStudy.outputUsers.readText"));
    masterReport.getParameterValues().put("i8nEvidence", this.getText("caseStudy.evidence.readText"));
    masterReport.getParameterValues().put("i8nOutputUsed", this.getText("caseStudy.outputUsed.readText"));
    masterReport.getParameterValues().put("i8nReferences", this.getText("caseStudy.references.readText"));
    masterReport.getParameterValues().put("i8nCaseStudyIndicators",
      this.getText("caseStudy.caseStudyIndicators.readText"));
    masterReport.getParameterValues().put("i8nExplainIndicatorRelation",
      this.getText("caseStudy.explainIndicatorRelation.readText"));
    masterReport.getParameterValues().put("i8nUploadAnnexes", this.getText("caseStudy.uploadAnnexes.readText"));
    masterReport.getParameterValues().put("i8nCaseStudyId", this.getText("caseStudy.caseStudyId"));
    masterReport.getParameterValues().put("i8nTitle", this.getText("caseStudy.title"));
    return masterReport;
  }


  @Override
  public String execute() throws Exception {
    ClassicEngineBoot.getInstance().start();
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    ResourceManager manager = new ResourceManager();
    manager.registerDefaults();
    try {
      Resource reportResource;
      if (this.getSelectedFormat().equals(APConstants.SUMMARY_FORMAT_EXCEL)) {
        reportResource =
          manager.createDirectly(this.getClass().getResource("/pentaho/crp/CaseStudiesExcel.prpt"), MasterReport.class);
      } else {
        reportResource =
          manager.createDirectly(this.getClass().getResource("/pentaho/crp/CaseStudiesPDF.prpt"), MasterReport.class);
      }
      MasterReport masterReport = (MasterReport) reportResource.getResource();
      String center = this.getLoggedCrp().getAcronym();

      // Get datetime
      ZonedDateTime timezone = ZonedDateTime.now();
      DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-d 'at' HH:mm ");
      String zone = timezone.getOffset() + "";
      if (zone.equals("Z")) {
        zone = "+0";
      }
      String date = timezone.format(format) + "(GMT" + zone + ")";

      // Set Main_Query
      CompoundDataFactory cdf = CompoundDataFactory.normalize(masterReport.getDataFactory());
      String masterQueryName = "main";
      TableDataFactory sdf = (TableDataFactory) cdf.getDataFactoryForQuery(masterQueryName);
      TypedTableModel model = this.getMasterTableModel(center, date, String.valueOf(this.getSelectedYear()));
      sdf.addTable(masterQueryName, model);
      masterReport.setDataFactory(cdf);
      // Set i8n for pentaho
      masterReport = this.addi8nParameters(masterReport);
      // Get details band
      ItemBand masteritemBand = masterReport.getItemBand();
      // Create new empty subreport hash map
      HashMap<String, Element> hm = new HashMap<String, Element>();
      // method to get all the subreports in the prpt and store in the HashMap
      this.getAllSubreports(hm, masteritemBand);
      // Uncomment to see which Subreports are detecting the method getAllSubreports
      // System.out.println("Pentaho SubReports: " + hm);

      this.fillSubreport((SubReport) hm.get("case_studies"), "case_studies");

      if (this.getSelectedFormat().equals(APConstants.SUMMARY_FORMAT_EXCEL)) {
        ExcelReportUtil.createXLSX(masterReport, os);
        bytesXLSX = os.toByteArray();
      } else {
        PdfReportUtil.createPDF(masterReport, os);
        bytesPDF = os.toByteArray();
      }
      os.close();
    } catch (Exception e) {
      LOG.error("Error generating CaseStudy" + this.getSelectedFormat() + ": " + e.getMessage());
      throw e;
    }
    // Calculate time of generation
    long stopTime = System.currentTimeMillis();
    stopTime = stopTime - startTime;
    LOG.info(
      "Downloaded successfully: " + this.getFileName() + ". User: " + this.getCurrentUser().getComposedCompleteName()
        + ". CRP: " + this.getLoggedCrp().getAcronym() + ". Time to generate: " + stopTime + "ms.");
    return SUCCESS;
  }


  private void fillSubreport(SubReport subReport, String query) {
    CompoundDataFactory cdf = CompoundDataFactory.normalize(subReport.getDataFactory());
    TableDataFactory sdf = (TableDataFactory) cdf.getDataFactoryForQuery(query);
    TypedTableModel model = null;
    switch (query) {
      case "case_studies":
        model = this.getCaseStudiesTableModel();
        break;
    }
    sdf.addTable(query, model);
    subReport.setDataFactory(cdf);
  }

  public byte[] getBytesPDF() {
    return bytesPDF;
  }

  public byte[] getBytesXLSX() {
    return bytesXLSX;
  }

  private TypedTableModel getCaseStudiesTableModel() {

    TypedTableModel model = new TypedTableModel(
      new String[] {"id", "title", "outcomeStatement", "researchOutputs", "researchPartners", "activities",
        "nonResearchPartneres", "outputUsers", "evidenceOutcome", "outputUsed", "referencesCase",
        "explainIndicatorRelation", "anex", "owner", "indicators", "shared", "year", "phaseID"},
      new Class[] {Long.class, String.class, String.class, String.class, String.class, String.class, String.class,
        String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class,
        String.class, String.class, Long.class},
      0);
    Long id = null;
    if (caseStudyManager.findAll() != null) {
      List<CaseStudy> caseStudies = new ArrayList<>(caseStudyManager.findAll().stream()
        .filter(cs -> cs.isActive() && cs.getYear() == this.getSelectedYear()).collect(Collectors.toList()));
      if (!caseStudies.isEmpty()) {
        for (CaseStudy caseStudy : caseStudies) {
          String title = null, outcomeStatement = null, researchOutputs = null, researchPartners = null,
            activities = null, nonResearchPartneres = null, outputUsers = null, evidenceOutcome = null,
            outputUsed = null, referencesCase = null, explainIndicatorRelation = null, anex = null, owner = null,
            shared = null, indicators = null, year = null;
          year = String.valueOf(caseStudy.getYear());

          id = caseStudy.getId();
          title = caseStudy.getTitle().trim().isEmpty() ? null : caseStudy.getTitle();
          outcomeStatement = caseStudy.getOutcomeStatement().trim().isEmpty() ? null : caseStudy.getOutcomeStatement();
          researchOutputs = caseStudy.getResearchOutputs().trim().isEmpty() ? null : caseStudy.getResearchOutputs();
          researchPartners = caseStudy.getResearchPartners().trim().isEmpty() ? null : caseStudy.getResearchPartners();
          activities = caseStudy.getActivities().trim().isEmpty() ? null : caseStudy.getActivities();
          nonResearchPartneres =
            caseStudy.getNonResearchPartneres().trim().isEmpty() ? null : caseStudy.getNonResearchPartneres();
          outputUsers = caseStudy.getOutputUsers().trim().isEmpty() ? null : caseStudy.getOutputUsers();
          outputUsed = caseStudy.getOutputUsed().trim().isEmpty() ? null : caseStudy.getOutputUsed();
          evidenceOutcome = caseStudy.getEvidenceOutcome().trim().isEmpty() ? null : caseStudy.getEvidenceOutcome();
          referencesCase = caseStudy.getReferencesCase().trim().isEmpty() ? null : caseStudy.getReferencesCase();
          explainIndicatorRelation =
            caseStudy.getExplainIndicatorRelation().trim().isEmpty() ? null : caseStudy.getExplainIndicatorRelation();
          List<CaseStudyProject> studyProjects = new ArrayList<>(caseStudy.getCaseStudyProjects().stream()
            .filter(csp -> csp.isActive() && csp.getProject().getProjecInfoPhase(this.getSelectedPhase()) != null
              && csp.getProject().getProjecInfoPhase(this.getSelectedPhase()).getReporting())
            .collect(Collectors.toList()));
          boolean add = false;
          owner = "";
          List<Project> projects = new ArrayList<>();
          for (CaseStudyProject caseStudyProject : studyProjects) {
            if (caseStudyProject.isCreated()) {
              shared = String.valueOf(caseStudyProject.getProject().getId());
              if (owner.length() == 0) {
                owner = "P" + caseStudyProject.getProject().getId();
                projects.add(caseStudyProject.getProject());
              } else {
                if (!projects.contains(caseStudyProject.getProject())) {
                  owner = owner + ", P" + caseStudyProject.getProject().getId();
                  projects.add(caseStudyProject.getProject());
                }
              }
            } else {
              if (owner.length() == 0) {
                owner = "P" + caseStudyProject.getProject().getId();
                projects.add(caseStudyProject.getProject());
              } else {
                if (!projects.contains(caseStudyProject.getProject())) {
                  owner = owner + ", P" + caseStudyProject.getProject().getId();
                  projects.add(caseStudyProject.getProject());
                }
              }
            }

            // Get The Crp/Center/Platform where the project was created
            GlobalUnitProject globalUnitProject = caseStudyProject.getProject().getGlobalUnitProjects().stream()
              .filter(gu -> gu.isActive() && gu.isOrigin()).collect(Collectors.toList()).get(0);

            if (globalUnitProject.getGlobalUnit().getId().longValue() == this.getLoggedCrp().getId().longValue()) {
              add = true;
            }

          }
          List<CaseStudyIndicator> studyIndicators = new ArrayList<>(
            caseStudy.getCaseStudyIndicators().stream().filter(c -> c.isActive()).collect(Collectors.toList()));

          StringBuilder indicatorsS = new StringBuilder();
          for (CaseStudyIndicator caseStudyIndicator : studyIndicators) {
            if (caseStudyIndicator.isActive()) {
              indicatorsS.append(caseStudyIndicator.getIpIndicator().getDescription() + "\n");
            }
          }
          indicators = indicatorsS.toString();

          if (caseStudy.getFile() != null) {
            anex = this.getCaseStudyUrl(shared) + caseStudy.getFile().getFileName();
          }

          Long phaseID = this.getSelectedPhase().getId();
          if (add) {
            model.addRow(new Object[] {id, title, outcomeStatement, researchOutputs, researchPartners, activities,
              nonResearchPartneres, outputUsers, evidenceOutcome, outputUsed, referencesCase, explainIndicatorRelation,
              anex, owner.trim(), indicators.trim(), shared.trim(), year, phaseID});
          }
        }
      }
    }
    return model;
  }

  public String getCaseStudyUrl(String project) {
    return config.getDownloadURL() + "/" + this.getCaseStudyUrlPath(project).replace('\\', '/');
  }

  public String getCaseStudyUrlPath(String project) {
    return config.getProjectsBaseFolder(this.getCrpSession()) + File.separator + project + File.separator + "caseStudy"
      + File.separator;
  }

  @Override
  public int getContentLength() {
    if (this.getSelectedFormat().equals(APConstants.SUMMARY_FORMAT_EXCEL)) {
      return bytesXLSX.length;
    } else {
      return bytesPDF.length;
    }
  }


  @Override
  public String getContentType() {
    if (this.getSelectedFormat().equals(APConstants.SUMMARY_FORMAT_EXCEL)) {
      return "application/xlsx";
    } else {
      return "application/pdf";
    }

  }

  @SuppressWarnings("unused")
  private File getFile(String fileName) {
    // Get file from resources folder
    ClassLoader classLoader = this.getClass().getClassLoader();
    File file = new File(classLoader.getResource(fileName).getFile());
    return file;
  }

  @Override
  public String getFileName() {
    StringBuffer fileName = new StringBuffer();
    fileName.append("OutcomesCaseStudiesSummary-");
    fileName.append(this.getSelectedYear() + "_");
    fileName.append(new SimpleDateFormat("yyyyMMdd-HHmm").format(new Date()));
    if (this.getSelectedFormat().equals(APConstants.SUMMARY_FORMAT_EXCEL)) {
      fileName.append(".xlsx");
    } else {
      fileName.append(".pdf");
    }
    return fileName.toString();
  }

  @Override
  public InputStream getInputStream() {
    if (inputStream == null) {
      if (this.getSelectedFormat().equals(APConstants.SUMMARY_FORMAT_EXCEL)) {
        inputStream = new ByteArrayInputStream(bytesXLSX);
      } else {
        inputStream = new ByteArrayInputStream(bytesPDF);
      }
    }
    return inputStream;
  }

  private TypedTableModel getMasterTableModel(String center, String date, String year) {
    // Initialization of Model
    TypedTableModel model = new TypedTableModel(new String[] {"center", "date", "year"},
      new Class[] {String.class, String.class, String.class});
    model.addRow(new Object[] {center, date, year});
    return model;
  }

  public String getSelectedFormat() {
    return selectedFormat;
  }

  @Override
  public void prepare() throws Exception {
    // Map<String, Object> parameters = this.getParameters();
    Map<String, Parameter> parameters = this.getParameters();
    this.setGeneralParameters();
    this.setSelectedFormat((StringUtils.trim(parameters.get(APConstants.SUMMARY_FORMAT).getMultipleValues()[0])));
    // Calculate time to generate report
    startTime = System.currentTimeMillis();
    LOG.info("Start report download: " + this.getFileName() + ". User: "
      + this.getCurrentUser().getComposedCompleteName() + ". CRP: " + this.getLoggedCrp().getAcronym());
  }

  public void setBytesPDF(byte[] bytesPDF) {
    this.bytesPDF = bytesPDF;
  }


  public void setBytesXLSX(byte[] bytesXLSX) {
    this.bytesXLSX = bytesXLSX;
  }

  public void setInputStream(InputStream inputStream) {
    this.inputStream = inputStream;
  }

  public void setSelectedFormat(String selectedFormat) {
    this.selectedFormat = selectedFormat;
  }

}
