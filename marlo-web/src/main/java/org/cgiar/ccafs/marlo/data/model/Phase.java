package org.cgiar.ccafs.marlo.data.model;
// Generated Apr 11, 2017 9:08:14 AM by Hibernate Tools 4.3.1.Final


import org.cgiar.ccafs.marlo.config.APConstants;
import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.Expose;

/**
 * Phases generated by hbm2java
 */
public class Phase extends MarloBaseEntity implements java.io.Serializable, IAuditLog {


  /**
   * 
   */
  private static final long serialVersionUID = -1603080182029677862L;

  private GlobalUnit crp;
  @Expose
  private String description;

  @Expose
  private int year;
  @Expose
  private Boolean editable;
  @Expose
  private Boolean visible;
  @Expose
  private Phase next;
  @Expose
  private Date startDate;
  @Expose
  private Date endDate;

  private Set<ProjectPhase> projectPhases = new HashSet<ProjectPhase>(0);
  private Set<ProjectInfo> projectInfos = new HashSet<ProjectInfo>(0);


  private Set<ProjectFocus> projectFocuses = new HashSet<ProjectFocus>(0);


  private Set<ProjectClusterActivity> projectClusters = new HashSet<ProjectClusterActivity>(0);


  private Set<CrpPpaPartner> crpPpaPartner = new HashSet<CrpPpaPartner>(0);


  private Set<ProjectPartner> partners = new HashSet<ProjectPartner>(0);
  private Set<CrpProgramOutcome> outcomes = new HashSet<CrpProgramOutcome>(0);

  private Set<CrpClusterOfActivity> clusters = new HashSet<CrpClusterOfActivity>(0);


  private Set<ProjectOutcome> projectOutcomes = new HashSet<ProjectOutcome>(0);

  private Set<Activity> projectActivites = new HashSet<Activity>(0);
  private Set<ProjectLocation> projectLocations = new HashSet<ProjectLocation>(0);
  private Set<DeliverableInfo> deliverableInfos = new HashSet<DeliverableInfo>(0);
  private Set<DeliverableFundingSource> deliverableFundingSources = new HashSet<DeliverableFundingSource>(0);
  private Set<DeliverablePartnership> deliverablePartnerships = new HashSet<DeliverablePartnership>(0);
  private Set<ProjectBudget> projectBudgets = new HashSet<ProjectBudget>(0);
  private Set<FundingSourceInfo> fundingSourceInfo = new HashSet<FundingSourceInfo>(0);
  private Set<FundingSourceInstitution> fundingSourceInstitutions = new HashSet<FundingSourceInstitution>(0);
  private Set<FundingSourceLocation> fundingSourceLocations = new HashSet<FundingSourceLocation>(0);
  private Set<FundingSourceBudget> fundingSourceBudgets = new HashSet<FundingSourceBudget>(0);
  private Set<ProjectBudgetsCluserActvity> projectBudgetsActivities = new HashSet<ProjectBudgetsCluserActvity>(0);
  private Set<ProjectExpectedStudy> projectExpectedStudies = new HashSet<ProjectExpectedStudy>(0);
  private Set<ProjectBudgetsFlagship> projectBudgetsFlagships = new HashSet<ProjectBudgetsFlagship>(0);
  private Set<PowbSynthesis> powbSynthesis = new HashSet<PowbSynthesis>(0);


  public Phase() {
  }


  public Phase(GlobalUnit crp, String description, int year) {
    this.crp = crp;
    this.description = description;
    this.year = year;
  }

  @Override
  public boolean equals(Object obj) {

    if (obj == null) {
      return false;
    }

    Phase other = (Phase) obj;
    if (this.getId() == null) {
      if (other.getId() != null) {
        return false;
      }
    } else if (!this.getId().equals(other.getId())) {
      return false;
    }
    return true;
  }

  public Set<CrpClusterOfActivity> getClusters() {
    return clusters;
  }

  public String getComposedName() {
    return this.description + " - " + year;
  }

  public GlobalUnit getCrp() {
    return crp;
  }

  public Set<CrpPpaPartner> getCrpPpaPartner() {
    return crpPpaPartner;
  }


  public Set<DeliverableFundingSource> getDeliverableFundingSources() {
    return deliverableFundingSources;
  }


  public Set<DeliverableInfo> getDeliverableInfos() {
    return deliverableInfos;
  }

  public Set<DeliverablePartnership> getDeliverablePartnerships() {
    return deliverablePartnerships;
  }


  public String getDescription() {
    return this.description;
  }

  public Boolean getEditable() {
    return editable;
  }

  public Date getEndDate() {
    return endDate;
  }

  public Set<FundingSourceBudget> getFundingSourceBudgets() {
    return fundingSourceBudgets;
  }


  public Set<FundingSourceInfo> getFundingSourceInfo() {
    return fundingSourceInfo;
  }


  public Set<FundingSourceInstitution> getFundingSourceInstitutions() {
    return fundingSourceInstitutions;
  }


  public Set<FundingSourceLocation> getFundingSourceLocations() {
    return fundingSourceLocations;
  }

  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();
    sb.append("Id : ").append(this.getId());
    return sb.toString();
  }


  @Override
  public String getModificationJustification() {

    return "";
  }


  @Override
  public User getModifiedBy() {
    User u = new User();
    u.setId(new Long(3));
    return u;
  }


  public Phase getNext() {
    return next;
  }

  public Set<CrpProgramOutcome> getOutcomes() {
    return outcomes;
  }

  public Set<ProjectPartner> getPartners() {
    return partners;
  }

  public Set<PowbSynthesis> getPowbSynthesis() {
    return powbSynthesis;
  }

  public Set<Activity> getProjectActivites() {
    return projectActivites;
  }


  public Set<ProjectBudget> getProjectBudgets() {
    return projectBudgets;
  }


  public Set<ProjectBudgetsCluserActvity> getProjectBudgetsActivities() {
    return projectBudgetsActivities;
  }


  public Set<ProjectBudgetsFlagship> getProjectBudgetsFlagships() {
    return projectBudgetsFlagships;
  }


  public Set<ProjectClusterActivity> getProjectClusters() {
    return projectClusters;
  }


  public Set<ProjectExpectedStudy> getProjectExpectedStudies() {
    return projectExpectedStudies;
  }


  public Set<ProjectFocus> getProjectFocuses() {
    return projectFocuses;
  }


  public Set<ProjectInfo> getProjectInfos() {
    return projectInfos;
  }


  public Set<ProjectLocation> getProjectLocations() {
    return projectLocations;
  }


  public Set<ProjectOutcome> getProjectOutcomes() {
    return projectOutcomes;
  }


  public Set<ProjectPhase> getProjectPhases() {
    return this.projectPhases;
  }


  public Date getStartDate() {
    return startDate;
  }


  public Boolean getVisible() {
    return visible;
  }


  public int getYear() {
    return this.year;
  }


  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.getId() == null) ? 0 : this.getId().hashCode());
    return result;
  }

  @Override
  public boolean isActive() {

    return true;
  }

  public Boolean isReporting() {
    return description.equals(APConstants.REPORTING);
  }


  public void setClusters(Set<CrpClusterOfActivity> clusters) {
    this.clusters = clusters;
  }


  public void setCrp(GlobalUnit crp) {
    this.crp = crp;
  }

  public void setCrpPpaPartner(Set<CrpPpaPartner> crpPpaPartner) {
    this.crpPpaPartner = crpPpaPartner;
  }


  public void setDeliverableFundingSources(Set<DeliverableFundingSource> deliverableFundingSources) {
    this.deliverableFundingSources = deliverableFundingSources;
  }


  public void setDeliverableInfos(Set<DeliverableInfo> deliverableInfos) {
    this.deliverableInfos = deliverableInfos;
  }

  public void setDeliverablePartnerships(Set<DeliverablePartnership> deliverablePartnerships) {
    this.deliverablePartnerships = deliverablePartnerships;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public void setEditable(Boolean editable) {
    this.editable = editable;
  }


  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }


  public void setFundingSourceBudgets(Set<FundingSourceBudget> fundingSourceBudgets) {
    this.fundingSourceBudgets = fundingSourceBudgets;
  }


  public void setFundingSourceInfo(Set<FundingSourceInfo> fundingSourceInfo) {
    this.fundingSourceInfo = fundingSourceInfo;
  }

  public void setFundingSourceInstitutions(Set<FundingSourceInstitution> fundingSourceInstitutions) {
    this.fundingSourceInstitutions = fundingSourceInstitutions;
  }


  public void setFundingSourceLocations(Set<FundingSourceLocation> fundingSourceLocations) {
    this.fundingSourceLocations = fundingSourceLocations;
  }

  public void setNext(Phase next) {
    this.next = next;
  }


  public void setOutcomes(Set<CrpProgramOutcome> otucomes) {
    this.outcomes = otucomes;
  }

  public void setPartners(Set<ProjectPartner> partners) {
    this.partners = partners;
  }

  public void setPowbSynthesis(Set<PowbSynthesis> powbSynthesis) {
    this.powbSynthesis = powbSynthesis;
  }

  public void setProjectActivites(Set<Activity> projectActivites) {
    this.projectActivites = projectActivites;
  }

  public void setProjectBudgets(Set<ProjectBudget> projectBudgets) {
    this.projectBudgets = projectBudgets;
  }

  public void setProjectBudgetsActivities(Set<ProjectBudgetsCluserActvity> projectBudgetsActivities) {
    this.projectBudgetsActivities = projectBudgetsActivities;
  }

  public void setProjectBudgetsFlagships(Set<ProjectBudgetsFlagship> projectBudgetsFlagships) {
    this.projectBudgetsFlagships = projectBudgetsFlagships;
  }

  public void setProjectClusters(Set<ProjectClusterActivity> projectClusters) {
    this.projectClusters = projectClusters;
  }

  public void setProjectExpectedStudies(Set<ProjectExpectedStudy> projectExpectedStudies) {
    this.projectExpectedStudies = projectExpectedStudies;
  }

  public void setProjectFocuses(Set<ProjectFocus> projectFocuses) {
    this.projectFocuses = projectFocuses;
  }

  public void setProjectInfos(Set<ProjectInfo> projectInfos) {
    this.projectInfos = projectInfos;
  }

  public void setProjectLocations(Set<ProjectLocation> projectLocations) {
    this.projectLocations = projectLocations;
  }

  public void setProjectOutcomes(Set<ProjectOutcome> projectOutcomes) {
    this.projectOutcomes = projectOutcomes;
  }

  public void setProjectPhases(Set<ProjectPhase> projectPhases) {
    this.projectPhases = projectPhases;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public void setVisible(Boolean visible) {
    this.visible = visible;
  }

  public void setYear(int year) {
    this.year = year;
  }

  @Override
  public String toString() {
    return "Phase [id=" + this.getId() + " description=" + description + ", year=" + year + "]";
  }
}

