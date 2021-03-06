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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.annotations.Expose;

/**
 * CrpOutcomeSubIdo generated by hbm2java
 */
public class CrpOutcomeSubIdo implements java.io.Serializable, IAuditLog {

  /**
   * 
   */
  private static final long serialVersionUID = 7556470694083733136L;

  @Expose
  private Long id;

  @Expose
  private CrpProgramOutcome crpProgramOutcome;
  @Expose
  private SrfSubIdo srfSubIdo;

  @Expose
  private BigDecimal contribution;

  private Set<CrpAssumption> crpAssumptions = new HashSet<CrpAssumption>(0);
  private List<CrpAssumption> assumptions;

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

  private HashMap<Long, String> subIdoList;


  public CrpOutcomeSubIdo() {
  }


  public CrpOutcomeSubIdo(CrpProgramOutcome crpProgramOutcome, SrfSubIdo srfSubIdo, BigDecimal contribution) {
    this.crpProgramOutcome = crpProgramOutcome;
    this.srfSubIdo = srfSubIdo;
    this.contribution = contribution;
  }

  public CrpOutcomeSubIdo(CrpProgramOutcome crpProgramOutcomes, SrfSubIdo srfSubIdo, BigDecimal contribution,
    Set<CrpAssumption> crpAssumptions) {
    this.crpProgramOutcome = crpProgramOutcomes;
    this.srfSubIdo = srfSubIdo;
    this.contribution = contribution;
    this.crpAssumptions = crpAssumptions;
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
    CrpOutcomeSubIdo other = (CrpOutcomeSubIdo) obj;
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

  public List<CrpAssumption> getAssumptions() {
    return assumptions;
  }


  public BigDecimal getContribution() {
    return this.contribution;
  }

  public User getCreatedBy() {
    return createdBy;
  }

  public Set<CrpAssumption> getCrpAssumptions() {
    return this.crpAssumptions;
  }

  public CrpProgramOutcome getCrpProgramOutcome() {
    return this.crpProgramOutcome;
  }

  @Override
  public Long getId() {
    return this.id;
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

  public SrfSubIdo getSrfSubIdo() {
    return this.srfSubIdo;
  }

  public HashMap<Long, String> getSubIdoList() {
    return subIdoList;
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

  public void setAssumptions(List<CrpAssumption> assumptions) {
    this.assumptions = assumptions;
  }

  public void setContribution(BigDecimal contribution) {
    this.contribution = contribution;
  }

  public void setCreatedBy(User createdBy) {
    this.createdBy = createdBy;
  }

  public void setCrpAssumptions(Set<CrpAssumption> crpAssumptions) {
    this.crpAssumptions = crpAssumptions;
  }

  public void setCrpProgramOutcome(CrpProgramOutcome crpProgramOutcome) {
    this.crpProgramOutcome = crpProgramOutcome;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setModificationJustification(String modificationJustification) {
    this.modificationJustification = modificationJustification;
  }

  public void setModifiedBy(User modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  public void setSrfSubIdo(SrfSubIdo srfSubIdo) {
    this.srfSubIdo = srfSubIdo;
  }

  public void setSubIdoList(HashMap<Long, String> subIdoList) {
    this.subIdoList = subIdoList;
  }

  @Override
  public String toString() {
    return "CrpOutcomeSubIdo [id=" + id + ", crpProgramOutcome=" + crpProgramOutcome + ", srfSubIdo=" + srfSubIdo
      + ", contribution=" + contribution + "]";
  }


}

