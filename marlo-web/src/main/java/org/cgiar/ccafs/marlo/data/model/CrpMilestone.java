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

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.Expose;

/**
 * CrpMilestone generated by hbm2java
 */
public class CrpMilestone implements java.io.Serializable, IAuditLog {

  private static final long serialVersionUID = 4398175741401855358L;

  @Expose
  private Long id;
  @Expose
  private CrpProgramOutcome crpProgramOutcome;

  @Expose
  private SrfTargetUnit srfTargetUnit;

  @Expose
  private String title;
  private Long index;

  @Expose
  private Integer year;


  @Expose
  private BigDecimal value;

  @Expose
  private boolean active;

  @Expose
  private User createdBy;

  @Expose
  private Date activeSince;

  @Expose
  private User modifiedBy;

  @Expose
  private String composeID;


  private String modificationJustification;


  private Set<ProjectMilestone> projectMilestones = new HashSet<ProjectMilestone>(0);

  public CrpMilestone() {
  }

  public CrpMilestone(CrpProgramOutcome crpProgramOutcome, SrfTargetUnit srfTargetUnits, String title, Integer year,
    BigDecimal value) {
    this.crpProgramOutcome = crpProgramOutcome;
    this.srfTargetUnit = srfTargetUnits;
    this.title = title;
    this.year = year;
    this.value = value;
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
    CrpMilestone other = (CrpMilestone) obj;
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!id.equals(other.id)) {
      return false;
    }
    return true;
  }

  public Date getActiveSince() {
    return activeSince;
  }


  public String getComposedName() {
    return this.year + " - " + this.title;
  }


  public String getComposeID() {
    return composeID;
  }

  public User getCreatedBy() {
    return createdBy;
  }


  public CrpProgramOutcome getCrpProgramOutcome() {
    return this.crpProgramOutcome;
  }

  @Override
  public Long getId() {
    return this.id;
  }

  public Long getIndex() {
    return index;
  }

  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();
    sb.append("Id : ").append(this.getId());
    return sb.toString();
  }

  @Override
  public String getModificationJustification() {
    return modificationJustification;
  }

  @Override
  public User getModifiedBy() {
    return modifiedBy;
  }

  public Set<ProjectMilestone> getProjectMilestones() {
    return projectMilestones;
  }

  public SrfTargetUnit getSrfTargetUnit() {

    return this.srfTargetUnit;
  }

  public String getTitle() {
    return this.title;
  }

  public BigDecimal getValue() {
    return this.value;
  }

  public Integer getYear() {
    return this.year;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }


  @Override
  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public void setActiveSince(Date activeSince) {
    this.activeSince = activeSince;
  }

  public void setComposeID(String composeID) {
    this.composeID = composeID;
  }


  public void setCreatedBy(User createdBy) {
    this.createdBy = createdBy;
  }

  public void setCrpProgramOutcome(CrpProgramOutcome crpProgramOutcome) {
    this.crpProgramOutcome = crpProgramOutcome;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setIndex(Long index) {
    this.index = index;
  }

  public void setModificationJustification(String modificationJustification) {
    this.modificationJustification = modificationJustification;
  }

  public void setModifiedBy(User modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  public void setProjectMilestones(Set<ProjectMilestone> projectMilestones) {
    this.projectMilestones = projectMilestones;
  }

  public void setSrfTargetUnit(SrfTargetUnit srfTargetUnit) {
    this.srfTargetUnit = srfTargetUnit;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setValue(BigDecimal value) {
    this.value = value;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  @Override
  public String toString() {
    return id.toString();
  }


}

