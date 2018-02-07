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
package org.cgiar.ccafs.marlo.data.model;
// Generated May 26, 2016 9:42:28 AM by Hibernate Tools 4.3.1.Final


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.annotations.Expose;

/**
 * CrpProgram generated by hbm2java
 */
public class CrpProgram implements java.io.Serializable, IAuditLog {


  /**
   * 
   */
  private static final long serialVersionUID = -799819119525448185L;


  @Expose
  private Long id;

  @Expose
  private GlobalUnit crp;


  @Expose
  private String name;


  @Expose
  private String acronym;

  @Expose
  private int programType;

  private String action;

  private Set<CrpClusterOfActivity> crpClusterOfActivities = new HashSet<CrpClusterOfActivity>(0);

  private Set<CrpProgramLeader> crpProgramLeaders = new HashSet<CrpProgramLeader>(0);


  private Set<CrpProgramOutcome> crpProgramOutcomes = new HashSet<CrpProgramOutcome>(0);

  private Set<SectionStatus> sectionStatuses = new HashSet<SectionStatus>(0);

  private Set<CrpProgramCountry> crpProgramCountries = new HashSet<CrpProgramCountry>(0);

  private Set<Submission> submissions = new HashSet<Submission>(0);

  private List<String> selectedCountries;

  private List<CrpProgramOutcome> outcomes;

  private List<CrpClusterOfActivity> clusterofActivities;
  private Set<LiaisonInstitution> liaisonInstitutions = new HashSet<LiaisonInstitution>(0);

  private Set<ProjectFocus> projectFocuses = new HashSet<ProjectFocus>(0);
  @Expose
  private boolean active;


  @Expose
  private User createdBy;
  @Expose
  private Date activeSince;


  @Expose
  private User modifiedBy;


  @Expose
  private String modificationJustification;

  private List<CrpProgramLeader> leaders;

  private List<CrpProgramLeader> managers;

  private List<PowbExpectedCrpProgress> powbs;
  private List<CrpMilestone> milestones;


  @Expose
  private String color;


  @Expose
  private Boolean baseLine;

  private Double w1;

  private Double w3;


  public CrpProgram() {
  }


  public CrpProgram(GlobalUnit crps, String name, String acronym, int programType) {
    this.crp = crps;
    this.name = name;
    this.acronym = acronym;
    this.programType = programType;
  }


  public CrpProgram(GlobalUnit crps, String name, String acronym, int programType,
    Set<CrpClusterOfActivity> crpClusterOfActivities, Set<CrpProgramLeader> crpProgramLeaders,
    Set<CrpProgramOutcome> crpProgramOutcomes) {
    this.crp = crps;
    this.name = name;
    this.acronym = acronym;
    this.programType = programType;
    this.crpClusterOfActivities = crpClusterOfActivities;
    this.crpProgramLeaders = crpProgramLeaders;
    this.crpProgramOutcomes = crpProgramOutcomes;
  }


  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }

    CrpProgram other = (CrpProgram) obj;
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!id.equals(other.id)) {
      return false;
    }
    return true;
  }


  public String getAcronym() {
    return this.acronym;
  }


  public String getAction() {
    return action;
  }

  public Date getActiveSince() {
    return activeSince;
  }

  public Boolean getBaseLine() {
    return baseLine;
  }


  public List<CrpClusterOfActivity> getClusterofActivities() {
    return clusterofActivities;
  }


  public String getColor() {
    return color;
  }

  public String getComposedName() {
    return this.acronym + ": " + this.name;
  }


  public User getCreatedBy() {
    return createdBy;
  }


  public GlobalUnit getCrp() {
    return crp;
  }

  public Set<CrpClusterOfActivity> getCrpClusterOfActivities() {
    return this.crpClusterOfActivities;
  }

  public Set<CrpProgramCountry> getCrpProgramCountries() {
    return crpProgramCountries;
  }

  public Set<CrpProgramLeader> getCrpProgramLeaders() {
    return this.crpProgramLeaders;
  }


  public Set<CrpProgramOutcome> getCrpProgramOutcomes() {
    return this.crpProgramOutcomes;
  }

  @Override
  public Long getId() {
    return this.id;
  }

  public List<CrpProgramLeader> getLeaders() {
    return leaders;
  }


  public Set<LiaisonInstitution> getLiaisonInstitutions() {
    return liaisonInstitutions;
  }


  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();

    sb.append("Id : ").append(this.getId());


    return sb.toString();
  }

  public List<CrpProgramLeader> getManagers() {
    return managers;
  }


  public List<CrpMilestone> getMilestones() {
    return milestones;
  }

  @Override
  public String getModificationJustification() {
    return modificationJustification;
  }

  @Override
  public User getModifiedBy() {
    return modifiedBy;
  }

  public String getName() {
    return this.name;
  }

  public List<CrpProgramOutcome> getOutcomes() {
    return outcomes;
  }


  public List<PowbExpectedCrpProgress> getPowbs() {
    return powbs;
  }

  public int getProgramType() {
    return this.programType;
  }


  public Set<ProjectFocus> getProjectFocuses() {
    return projectFocuses;
  }


  public Set<SectionStatus> getSectionStatuses() {
    return sectionStatuses;
  }

  public List<String> getSelectedCountries() {
    return selectedCountries;
  }

  public Set<Submission> getSubmissions() {
    return submissions;
  }

  public Double getW1() {
    return w1;
  }

  public Double getW3() {
    return w3;
  }

  @Override
  public boolean isActive() {
    return active;
  }

  public void setAcronym(String acronym) {
    this.acronym = acronym;
  }

  public void setAction(String action) {
    this.action = action;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public void setActiveSince(Date activeSince) {
    this.activeSince = activeSince;
  }

  public void setBaseLine(Boolean baseLine) {
    this.baseLine = baseLine;
  }

  public void setClusterofActivities(List<CrpClusterOfActivity> clusterofActivities) {
    this.clusterofActivities = clusterofActivities;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public void setCreatedBy(User createdBy) {
    this.createdBy = createdBy;
  }

  public void setCrp(GlobalUnit crp) {
    this.crp = crp;
  }


  public void setCrpClusterOfActivities(Set<CrpClusterOfActivity> crpClusterOfActivities) {
    this.crpClusterOfActivities = crpClusterOfActivities;
  }


  public void setCrpProgramCountries(Set<CrpProgramCountry> crpProgramCountries) {
    this.crpProgramCountries = crpProgramCountries;
  }


  public void setCrpProgramLeaders(Set<CrpProgramLeader> crpProgramLeaders) {
    this.crpProgramLeaders = crpProgramLeaders;
  }

  public void setCrpProgramOutcomes(Set<CrpProgramOutcome> crpProgramOutcomes) {
    this.crpProgramOutcomes = crpProgramOutcomes;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setLeaders(List<CrpProgramLeader> leaders) {
    this.leaders = leaders;
  }

  public void setLiaisonInstitutions(Set<LiaisonInstitution> liaisonInstitutions) {
    this.liaisonInstitutions = liaisonInstitutions;
  }


  public void setManagers(List<CrpProgramLeader> managers) {
    this.managers = managers;
  }

  public void setMilestones(List<CrpMilestone> milestones) {
    this.milestones = milestones;
  }

  public void setModificationJustification(String modificationJustification) {
    this.modificationJustification = modificationJustification;
  }

  public void setModifiedBy(User modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setOutcomes(List<CrpProgramOutcome> outcomes) {
    this.outcomes = outcomes;
  }

  public void setPowbs(List<PowbExpectedCrpProgress> powbs) {
    this.powbs = powbs;
  }

  public void setProgramType(int programType) {
    this.programType = programType;
  }

  public void setProjectFocuses(Set<ProjectFocus> projectFocuses) {
    this.projectFocuses = projectFocuses;
  }

  public void setSectionStatuses(Set<SectionStatus> sectionStatuses) {
    this.sectionStatuses = sectionStatuses;
  }

  public void setSelectedCountries(List<String> selectedCountries) {
    this.selectedCountries = selectedCountries;
  }

  public void setSubmissions(Set<Submission> submissions) {
    this.submissions = submissions;
  }

  public void setW1(Double w1) {
    this.w1 = w1;
  }

  public void setW3(Double w3) {
    this.w3 = w3;
  }

  @Override
  public String toString() {
    return "CrpProgram [id=" + id + ", crp=" + crp + ", name=" + name + ", acronym=" + acronym + ", programType="
      + programType + "]";
  }


}

