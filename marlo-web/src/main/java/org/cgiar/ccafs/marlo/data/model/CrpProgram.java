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


  private @Expose Long id;


  private @Expose Crp crp;


  private @Expose String name;


  private @Expose String acronym;


  private @Expose int programType;


  private Set<CrpClusterOfActivity> crpClusterOfActivities = new HashSet<CrpClusterOfActivity>(0);


  private Set<CrpProgramLeader> crpProgramLeaders = new HashSet<CrpProgramLeader>(0);

  private Set<CrpProgramOutcome> crpProgramOutcomes = new HashSet<CrpProgramOutcome>(0);

  private @Expose boolean active;

  private @Expose User createdBy;
  private @Expose Date activeSince;
  private @Expose User modifiedBy;
  private @Expose String modificationJustification;
  private @Expose List<CrpProgramLeader> leaders;

  public CrpProgram() {
  }

  public CrpProgram(Crp crps, String name, String acronym, int programType) {
    this.crp = crps;
    this.name = name;
    this.acronym = acronym;
    this.programType = programType;
  }

  public CrpProgram(Crp crps, String name, String acronym, int programType,
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
    if (this.getClass() != obj.getClass()) {
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

  public Date getActiveSince() {
    return activeSince;
  }

  public User getCreatedBy() {
    return createdBy;
  }

  public Crp getCrp() {
    return this.crp;
  }


  public Set<CrpClusterOfActivity> getCrpClusterOfActivities() {
    return this.crpClusterOfActivities;
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

  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();
    sb.append("Id : ").append(this.getId());


    return sb.toString();
  }

  public String getModificationJustification() {
    return modificationJustification;
  }


  public User getModifiedBy() {
    return modifiedBy;
  }

  public String getName() {
    return this.name;
  }

  public int getProgramType() {
    return this.programType;
  }

  @Override
  public boolean isActive() {
    return active;
  }

  public void setAcronym(String acronym) {
    this.acronym = acronym;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public void setActiveSince(Date activeSince) {
    this.activeSince = activeSince;
  }

  public void setCreatedBy(User createdBy) {
    this.createdBy = createdBy;
  }

  public void setCrp(Crp crp) {
    this.crp = crp;
  }

  public void setCrpClusterOfActivities(Set<CrpClusterOfActivity> crpClusterOfActivities) {
    this.crpClusterOfActivities = crpClusterOfActivities;
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

  public void setModificationJustification(String modificationJustification) {
    this.modificationJustification = modificationJustification;
  }

  public void setModifiedBy(User modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setProgramType(int programType) {
    this.programType = programType;
  }


}

