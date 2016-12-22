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

package org.cgiar.ccafs.marlo.action.json.project;


import org.cgiar.ccafs.marlo.action.BaseAction;
import org.cgiar.ccafs.marlo.config.APConstants;
import org.cgiar.ccafs.marlo.data.manager.CrpManager;
import org.cgiar.ccafs.marlo.data.manager.LocElementTypeManager;
import org.cgiar.ccafs.marlo.data.manager.ProjectLocationElementTypeManager;
import org.cgiar.ccafs.marlo.data.manager.ProjectManager;
import org.cgiar.ccafs.marlo.data.manager.SectionStatusManager;
import org.cgiar.ccafs.marlo.data.model.Activity;
import org.cgiar.ccafs.marlo.data.model.Crp;
import org.cgiar.ccafs.marlo.data.model.CrpProgram;
import org.cgiar.ccafs.marlo.data.model.Deliverable;
import org.cgiar.ccafs.marlo.data.model.DeliverableActivity;
import org.cgiar.ccafs.marlo.data.model.DeliverablePartnership;
import org.cgiar.ccafs.marlo.data.model.DeliverablePartnershipTypeEnum;
import org.cgiar.ccafs.marlo.data.model.LocElement;
import org.cgiar.ccafs.marlo.data.model.LocElementType;
import org.cgiar.ccafs.marlo.data.model.ProgramType;
import org.cgiar.ccafs.marlo.data.model.Project;
import org.cgiar.ccafs.marlo.data.model.ProjectClusterActivity;
import org.cgiar.ccafs.marlo.data.model.ProjectFocus;
import org.cgiar.ccafs.marlo.data.model.ProjectLocation;
import org.cgiar.ccafs.marlo.data.model.ProjectLocationElementType;
import org.cgiar.ccafs.marlo.data.model.ProjectOutcome;
import org.cgiar.ccafs.marlo.data.model.ProjectPartner;
import org.cgiar.ccafs.marlo.data.model.ProjectPartnerContribution;
import org.cgiar.ccafs.marlo.data.model.ProjectScope;
import org.cgiar.ccafs.marlo.data.model.ProjectSectionStatusEnum;
import org.cgiar.ccafs.marlo.data.model.ProjectStatusEnum;
import org.cgiar.ccafs.marlo.data.model.SectionStatus;
import org.cgiar.ccafs.marlo.utils.APConfig;
import org.cgiar.ccafs.marlo.utils.CountryLocationLevel;
import org.cgiar.ccafs.marlo.validation.projects.DeliverableValidator;
import org.cgiar.ccafs.marlo.validation.projects.ProjectActivitiesValidator;
import org.cgiar.ccafs.marlo.validation.projects.ProjectBudgetsCoAValidator;
import org.cgiar.ccafs.marlo.validation.projects.ProjectBudgetsValidator;
import org.cgiar.ccafs.marlo.validation.projects.ProjectDescriptionValidator;
import org.cgiar.ccafs.marlo.validation.projects.ProjectLocationValidator;
import org.cgiar.ccafs.marlo.validation.projects.ProjectOutcomeValidator;
import org.cgiar.ccafs.marlo.validation.projects.ProjectPartnersValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Hermes Jiménez - CIAT/CCAFS
 */
public class ValidateProjectSectionAction extends BaseAction {


  public static final long serialVersionUID = 2334147747892988744L;


  // Logger
  public static final Logger LOG = LoggerFactory.getLogger(ValidateProjectSectionAction.class);

  // Model
  public boolean existProject;
  public Crp loggedCrp;
  public boolean validSection;

  public String sectionName;
  public Long projectID;
  public SectionStatus sectionStatus;
  public Map<String, Object> section;
  // Managers
  @Inject
  public SectionStatusManager sectionStatusManager;
  @Inject
  public ProjectManager projectManager;
  @Inject
  ProjectLocationValidator locationValidator;

  @Inject
  ProjectBudgetsValidator projectBudgetsValidator;

  @Inject
  DeliverableValidator deliverableValidator;
  @Inject
  ProjectOutcomeValidator projectOutcomeValidator;

  @Inject
  ProjectBudgetsCoAValidator projectBudgetsCoAValidator;

  @Inject
  LocElementTypeManager locElementTypeManager;

  @Inject
  ProjectLocationElementTypeManager projectLocationElementTypeManager;

  @Inject
  ProjectDescriptionValidator descriptionValidator;
  @Inject
  ProjectPartnersValidator projectPartnerValidator;
  @Inject
  ProjectActivitiesValidator projectActivitiesValidator;
  @Inject
  public CrpManager crpManager;

  @Inject
  public ValidateProjectSectionAction(APConfig config) {
    super(config);
  }


  @Override
  public String execute() throws Exception {
    if (existProject && validSection) {
      // getting the current section status.
      switch (ProjectSectionStatusEnum.value(sectionName.toUpperCase())) {
        case LOCATIONS:
          this.validateProjectLocations();
          break;
        case DESCRIPTION:
          this.validateProjectDescription();
          break;
        case ACTIVITIES:
          this.validateProjectActivities();
          break;
        case PARTNERS:
          this.validateProjectParnters();
        case BUDGET:
          this.validateProjectBudgets();
          break;
        case BUDGETBYCOA:
          this.validateProjectBudgetsCoAs();
          break;

        case DELIVERABLES:
          this.validateProjectDeliverables();
          break;


        case OUTCOMES:
          this.validateProjectOutcomes();
          break;

        default:
          break;
      }
    }
    String cycle = "";
    if (this.isPlanningActive()) {
      cycle = APConstants.PLANNING;
    } else {
      cycle = APConstants.REPORTING;
    }
    Project project = projectManager.getProjectById(projectID);
    switch (ProjectSectionStatusEnum.value(sectionName.toUpperCase())) {
      case OUTCOMES:
        section = new HashMap<String, Object>();
        section.put("sectionName", ProjectSectionStatusEnum.OUTCOMES);
        section.put("missingFields", "");

        List<ProjectOutcome> projectOutcomes =
          project.getProjectOutcomes().stream().filter(c -> c.isActive()).collect(Collectors.toList());


        if (!(project.getAdministrative() != null && project.getAdministrative().booleanValue() == true)) {
          if (projectOutcomes.isEmpty()) {
            section.put("missingFields", section.get("missingFields") + "-" + "outcomes");
          }
          project.setOutcomes(projectOutcomes);
          for (ProjectOutcome projectOutcome : project.getOutcomes()) {
            sectionStatus = sectionStatusManager.getSectionStatusByProjectOutcome(projectOutcome.getId(), cycle,
              this.getCurrentCycleYear(), sectionName);
            if (sectionStatus.getMissingFields().length() > 0) {
              section.put("missingFields", section.get("missingFields") + "-" + sectionStatus.getMissingFields());

            }
          }
        } else {
          section.put("missingFields", "");
        }


        break;

      case DELIVERABLES:
        section = new HashMap<String, Object>();
        section.put("sectionName", ProjectSectionStatusEnum.DELIVERABLES);
        section.put("missingFields", "");

        if (project.getDeliverables().stream().filter(d -> d.isActive()).collect(Collectors.toList()).isEmpty()) {
          section.put("missingFields", section.get("missingFields") + "-" + "deliveralbes");
        }

        List<Deliverable> deliverables =
          project.getDeliverables().stream().filter(d -> d.isActive()).collect(Collectors.toList());
        List<Deliverable> openA = deliverables.stream()
          .filter(a -> a.isActive() && a.getYear() <= this.getCurrentCycleYear()
            && ((a.getStatus() == null || a.getStatus() == Integer.parseInt(ProjectStatusEnum.Ongoing.getStatusId())
              || (a.getStatus() == Integer.parseInt(ProjectStatusEnum.Extended.getStatusId())
                || a.getStatus().intValue() == 0))))
          .collect(Collectors.toList());

        for (Deliverable deliverable : openA) {
          sectionStatus = sectionStatusManager.getSectionStatusByDeliverable(deliverable.getId(), cycle,
            this.getCurrentCycleYear(), sectionName);
          if (sectionStatus == null) {

            sectionStatus = new SectionStatus();
            sectionStatus.setMissingFields("No section");
          }
          if (sectionStatus.getMissingFields().length() > 0) {
            section.put("missingFields", section.get("missingFields") + "-" + sectionStatus.getMissingFields());

          }


        }


        break;


      case ACTIVITIES:
        sectionStatus =
          sectionStatusManager.getSectionStatusByProject(projectID, cycle, this.getCurrentCycleYear(), sectionName);
        section = new HashMap<String, Object>();
        section.put("sectionName", sectionStatus.getSectionName());
        section.put("missingFields", sectionStatus.getMissingFields());
        if (project.getActivities().stream().filter(d -> d.isActive()).collect(Collectors.toList()).isEmpty()) {
          section.put("missingFields", section.get("missingFields") + "-" + "activities");
        }

        break;


      default:
        sectionStatus =
          sectionStatusManager.getSectionStatusByProject(projectID, cycle, this.getCurrentCycleYear(), sectionName);
        section = new HashMap<String, Object>();
        section.put("sectionName", sectionStatus.getSectionName());
        section.put("missingFields", sectionStatus.getMissingFields());
        break;
    }


    // Thread.sleep(500);
    return SUCCESS;
  }


  public Long getProjectID() {
    return projectID;
  }


  public List<CountryLocationLevel> getProjectLocationsData(Project project) {

    List<Map<String, Object>> parentLocations = new ArrayList<>();
    List<CountryLocationLevel> locationLevels = new ArrayList<>();
    List<ProjectLocationElementType> locationsElementType = new ArrayList<>(
      project.getProjectLocationElementTypes().stream().filter(pl -> pl.getIsGlobal()).collect(Collectors.toList()));

    project.setLocations(new ArrayList<ProjectLocation>(
      project.getProjectLocations().stream().filter(p -> p.isActive()).collect(Collectors.toList())));
    Map<String, Object> locationParent;
    if (!project.getLocations().isEmpty()) {

      if (locationsElementType != null) {
        for (ProjectLocationElementType projectLocationElementType : locationsElementType) {
          boolean existElementType = false;
          for (ProjectLocation location : project.getLocations()) {
            if (projectLocationElementType.getLocElementType().getId() == location.getLocElement().getLocElementType()
              .getId()) {
              existElementType = true;
            }
          }
          if (!existElementType) {
            locationParent = new HashMap<String, Object>();
            if (!parentLocations.isEmpty()) {
              locationParent.put(projectLocationElementType.getLocElementType().getName(),
                projectLocationElementType.getLocElementType().getId());
              if (!parentLocations.contains(locationParent)) {
                parentLocations.add(locationParent);
              }
            } else {
              locationParent.put(projectLocationElementType.getLocElementType().getName(),
                projectLocationElementType.getLocElementType().getId());
              parentLocations.add(locationParent);
            }
          }
        }
      }

      for (ProjectLocation location : project.getLocations()) {
        locationParent = new HashMap<String, Object>();
        if (!parentLocations.isEmpty()) {
          locationParent.put(location.getLocElement().getLocElementType().getName(),
            location.getLocElement().getLocElementType().getId());
          if (!parentLocations.contains(locationParent)) {
            parentLocations.add(locationParent);
          }
        } else {
          locationParent.put(location.getLocElement().getLocElementType().getName(),
            location.getLocElement().getLocElementType().getId());
          parentLocations.add(locationParent);
        }

      }

    } else {
      if (!locationsElementType.isEmpty()) {
        for (ProjectLocationElementType projectLocationElementType : locationsElementType) {
          locationParent = new HashMap<String, Object>();
          if (!parentLocations.isEmpty()) {
            locationParent.put(projectLocationElementType.getLocElementType().getName(),
              projectLocationElementType.getLocElementType().getId());
            if (!parentLocations.contains(locationParent)) {
              parentLocations.add(locationParent);
            }
          } else {
            locationParent.put(projectLocationElementType.getLocElementType().getName(),
              projectLocationElementType.getLocElementType().getId());
            parentLocations.add(locationParent);
          }
        }
      }
    }

    CountryLocationLevel countryLocationLevel;
    ProjectLocationElementType locationElementType = null;
    for (Map<String, Object> map : parentLocations) {

      for (Map.Entry<String, Object> entry : map.entrySet()) {
        countryLocationLevel = new CountryLocationLevel();
        countryLocationLevel.setId(Long.parseLong(entry.getValue().toString()));
        countryLocationLevel.setName(entry.getKey());
        countryLocationLevel.setLocElements(new ArrayList<LocElement>());

        LocElementType elementType =
          locElementTypeManager.getLocElementTypeById(Long.parseLong(entry.getValue().toString()));

        countryLocationLevel.setAllElements(new ArrayList<LocElement>(elementType.getLocElements()));

        for (ProjectLocation projectLocation : project.getLocations().stream().filter(l -> l.isActive())
          .collect(Collectors.toList())) {
          if (projectLocation.getLocElement().getLocElementType().getId() == Long
            .parseLong(entry.getValue().toString())) {
            countryLocationLevel.getLocElements().add(projectLocation.getLocElement());
          }
        }

        if (elementType.getId() == 2 || elementType.getCrp() != null) {

          locationElementType =
            projectLocationElementTypeManager.getByProjectAndElementType(projectID, elementType.getId());

          countryLocationLevel.setList(true);
          if (locationElementType != null) {
            countryLocationLevel.setAllCountries(locationElementType.getIsGlobal());
          }
        } else {
          countryLocationLevel.setList(false);
          countryLocationLevel.setAllCountries(false);
        }

        locationLevels.add(countryLocationLevel);
      }


    }

    return locationLevels;
  }


  public Map<String, Object> getSection() {
    return section;
  }


  public String getSectionName() {
    return sectionName;
  }


  public boolean isExistProject() {
    return existProject;
  }


  public boolean isValidSection() {
    return validSection;
  }


  public List<DeliverablePartnership> otherPartners(Deliverable deliverable) {
    try {
      List<DeliverablePartnership> list = deliverable.getDeliverablePartnerships().stream()
        .filter(dp -> dp.isActive() && dp.getPartnerType().equals(DeliverablePartnershipTypeEnum.OTHER.getValue()))
        .collect(Collectors.toList());
      return list;
    } catch (Exception e) {
      return null;
    }
  }

  @Override
  public void prepare() throws Exception {
    Map<String, Object> parameters = this.getParameters();
    loggedCrp = (Crp) this.getSession().get(APConstants.SESSION_CRP);
    loggedCrp = crpManager.getCrpById(loggedCrp.getId());
    sectionName = StringUtils.trim(((String[]) parameters.get(APConstants.SECTION_NAME))[0]);

    projectID = Long.parseLong(StringUtils.trim(((String[]) parameters.get(APConstants.PROJECT_REQUEST_ID))[0]));
    // Validate if project exists.
    existProject = projectManager.existProject(projectID);

    // Validate if the section exists.
    validSection = ProjectSectionStatusEnum.value(sectionName) != null;


  }

  public DeliverablePartnership responsiblePartner(Deliverable deliverable) {
    try {
      DeliverablePartnership partnership = deliverable.getDeliverablePartnerships().stream()
        .filter(
          dp -> dp.isActive() && dp.getPartnerType().equals(DeliverablePartnershipTypeEnum.RESPONSIBLE.getValue()))
        .collect(Collectors.toList()).get(0);
      return partnership;
    } catch (Exception e) {
      return null;
    }
  }

  public void setExistProject(boolean existProject) {
    this.existProject = existProject;
  }

  public void setProjectID(Long projectID) {
    this.projectID = projectID;
  }

  public void setSection(Map<String, Object> section) {
    this.section = section;
  }

  public void setSectionName(String sectionName) {
    this.sectionName = sectionName;
  }

  public void setValidSection(boolean validSection) {
    this.validSection = validSection;
  }


  public void validateProjectActivities() {
    // Getting the project information.
    Project project = projectManager.getProjectById(projectID);

    project.setProjectActivities(new ArrayList<Activity>(project.getActivities().stream()
      .filter(a -> a.isActive() && a.getActivityStatus() != null
        && ((a.getActivityStatus() == Integer.parseInt(ProjectStatusEnum.Ongoing.getStatusId())
          || (a.getActivityStatus() == Integer.parseInt(ProjectStatusEnum.Extended.getStatusId())))))
      .collect(Collectors.toList())));


    if (project.getProjectActivities() != null) {
      for (Activity openActivity : project.getProjectActivities()) {
        openActivity.setDeliverables(new ArrayList<DeliverableActivity>(
          openActivity.getDeliverableActivities().stream().filter(da -> da.isActive()).collect(Collectors.toList())));
      }
    }

    project.setClosedProjectActivities(new ArrayList<Activity>(project.getActivities().stream()
      .filter(a -> a.isActive() && a.getActivityStatus() != null
        && ((a.getActivityStatus() == Integer.parseInt(ProjectStatusEnum.Complete.getStatusId())
          || (a.getActivityStatus() == Integer.parseInt(ProjectStatusEnum.Cancelled.getStatusId())))))
      .collect(Collectors.toList())));

    if (project.getClosedProjectActivities() != null) {
      for (Activity closedActivity : project.getClosedProjectActivities()) {
        closedActivity.setDeliverables(new ArrayList<DeliverableActivity>(
          closedActivity.getDeliverableActivities().stream().filter(da -> da.isActive()).collect(Collectors.toList())));
      }
    }
    projectActivitiesValidator.validate(this, project, false);
  }

  public void validateProjectBudgets() {
    // Getting the project information.
    Project project = projectManager.getProjectById(projectID);

    project.setBudgets(project.getProjectBudgets().stream().filter(c -> c.isActive()).collect(Collectors.toList()));


    projectBudgetsValidator.validate(this, project, false);

  }


  public void validateProjectBudgetsCoAs() {
    // Getting the project information.
    Project project = projectManager.getProjectById(projectID);
    project.setBudgetsCluserActvities(
      project.getProjectBudgetsCluserActvities().stream().filter(c -> c.isActive()).collect(Collectors.toList()));
    if (!(project.getProjectBudgetsCluserActvities().isEmpty()
      || project.getProjectBudgetsCluserActvities().size() == 1)) {
      projectBudgetsCoAValidator.validate(this, project, false);
    }

  }

  public void validateProjectDeliverables() {
    // Getting the project information.
    Project project = projectManager.getProjectById(projectID);

    List<Deliverable> deliverables =
      project.getDeliverables().stream().filter(d -> d.isActive()).collect(Collectors.toList());
    List<Deliverable> openA = deliverables.stream()
      .filter(a -> a.isActive() && a.getYear() <= this.getCurrentCycleYear()
        && ((a.getStatus() == null || a.getStatus() == Integer.parseInt(ProjectStatusEnum.Ongoing.getStatusId())
          || (a.getStatus() == Integer.parseInt(ProjectStatusEnum.Extended.getStatusId())
            || a.getStatus().intValue() == 0))))
      .collect(Collectors.toList());

    for (Deliverable deliverable : openA) {
      deliverable.setResponsiblePartner(this.responsiblePartner(deliverable));
      deliverable.setOtherPartners(this.otherPartners(deliverable));
      deliverable.setGenderLevels(
        deliverable.getDeliverableGenderLevels().stream().filter(c -> c.isActive()).collect(Collectors.toList()));
      deliverable.setFundingSources(
        deliverable.getDeliverableFundingSources().stream().filter(c -> c.isActive()).collect(Collectors.toList()));
      deliverableValidator.validate(this, deliverable, false);
    }

  }

  public void validateProjectDescription() {
    Project project = projectManager.getProjectById(projectID);
    List<CrpProgram> programs = new ArrayList<>();
    for (ProjectFocus projectFocuses : project.getProjectFocuses().stream()
      .filter(c -> c.isActive() && c.getCrpProgram().getProgramType() == ProgramType.FLAGSHIP_PROGRAM_TYPE.getValue())
      .collect(Collectors.toList())) {
      programs.add(projectFocuses.getCrpProgram());

      if (project.getFlagshipValue() == null) {
        project.setFlagshipValue(projectFocuses.getCrpProgram().getId().toString());

      } else {
        project.setFlagshipValue(project.getFlagshipValue() + "," + projectFocuses.getCrpProgram().getId().toString());
      }
    }

    List<CrpProgram> regions = new ArrayList<>();
    for (ProjectFocus projectFocuses : project.getProjectFocuses().stream()
      .filter(c -> c.isActive() && c.getCrpProgram().getProgramType() == ProgramType.REGIONAL_PROGRAM_TYPE.getValue())
      .collect(Collectors.toList())) {
      regions.add(projectFocuses.getCrpProgram());
      if (project.getRegionsValue() == null) {
        project.setRegionsValue(projectFocuses.getCrpProgram().getId().toString());

      } else {
        project.setRegionsValue(project.getRegionsValue() + "," + projectFocuses.getCrpProgram().getId().toString());
      }
    }

    List<ProjectClusterActivity> projectClusterActivities = new ArrayList<>();
    for (ProjectClusterActivity projectClusterActivity : project.getProjectClusterActivities().stream()
      .filter(c -> c.isActive()).collect(Collectors.toList())) {

      projectClusterActivity.getCrpClusterOfActivity().setLeaders(projectClusterActivity.getCrpClusterOfActivity()
        .getCrpClusterActivityLeaders().stream().filter(c -> c.isActive()).collect(Collectors.toList()));
      projectClusterActivities.add(projectClusterActivity);
    }

    List<ProjectScope> projectLocations = new ArrayList<>();
    for (ProjectScope projectLocation : project.getProjectScopes().stream().filter(c -> c.isActive())
      .collect(Collectors.toList())) {

      projectLocations.add(projectLocation);
    }
    project.setClusterActivities(projectClusterActivities);
    project.setFlagships(programs);
    project.setRegions(regions);
    project.setScopes(projectLocations);

    descriptionValidator.validate(this, project, false);
  }


  public void validateProjectLocations() {
    // Getting the project information.
    Project project = projectManager.getProjectById(projectID);
    project.setLocationsData(new ArrayList<>(this.getProjectLocationsData(project)));

    locationValidator.validate(this, project, false);
  }

  public void validateProjectOutcomes() {
    // Getting the project information.
    Project project = projectManager.getProjectById(projectID);

    List<ProjectOutcome> projectOutcomes =
      project.getProjectOutcomes().stream().filter(c -> c.isActive()).collect(Collectors.toList());


    project.setOutcomes(projectOutcomes);
    for (ProjectOutcome projectOutcome : project.getOutcomes()) {
      projectOutcome.setMilestones(
        projectOutcome.getProjectMilestones().stream().filter(c -> c.isActive()).collect(Collectors.toList()));

      projectOutcome.setCommunications(
        projectOutcome.getProjectCommunications().stream().filter(c -> c.isActive()).collect(Collectors.toList()));
      projectOutcome.setNextUsers(
        projectOutcome.getProjectNextusers().stream().filter(c -> c.isActive()).collect(Collectors.toList()));

      projectOutcomeValidator.validate(this, projectOutcome, false);

    }


  }

  public void validateProjectParnters() {
    Project project = projectManager.getProjectById(projectID);
    project.setPartners(project.getProjectPartners().stream().filter(c -> c.isActive()).collect(Collectors.toList()));
    for (ProjectPartner projectPartner : project.getPartners()) {
      List<ProjectPartnerContribution> contributors = new ArrayList<>();


      List<ProjectPartnerContribution> partnerContributions =
        projectPartner.getProjectPartnerContributions().stream().filter(c -> c.isActive()).collect(Collectors.toList());
      for (ProjectPartnerContribution projectPartnerContribution : partnerContributions) {
        contributors.add(projectPartnerContribution);
      }
      projectPartner.setPartnerContributors(contributors);
      projectPartner.setPartnerPersons(
        projectPartner.getProjectPartnerPersons().stream().filter(c -> c.isActive()).collect(Collectors.toList()));
    }
    if (this.isLessonsActive()) {
      this.loadLessons(loggedCrp, project, ProjectSectionStatusEnum.PARTNERS.getStatus());
    }
    projectPartnerValidator.validate(this, project, false);

  }

}
