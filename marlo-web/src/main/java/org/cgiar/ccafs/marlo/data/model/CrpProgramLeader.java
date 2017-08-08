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

import com.google.gson.annotations.Expose;

/**
 * CrpProgramLeader generated by hbm2java
 */
public class CrpProgramLeader implements java.io.Serializable, IAuditLog {

  /**
   * 
   */
  private static final long serialVersionUID = 7345327536072331541L;

  @Expose
  private Long id;
  @Expose
  private CrpProgram crpProgram;

  @Expose
  private User user;

  @Expose
  private boolean active;

  @Expose
  private boolean manager;

  @Expose
  private User createdBy;


  @Expose
  private Date activeSince;

  @Expose
  private User modifiedBy;

  @Expose
  private String modificationJustification;

  public CrpProgramLeader() {
  }

  public CrpProgramLeader(CrpProgram crpProgram, User user) {
    this.crpProgram = crpProgram;
    this.user = user;
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
    }
    CrpProgramLeader other = (CrpProgramLeader) obj;
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

  public User getCreatedBy() {
    return createdBy;
  }

  public CrpProgram getCrpProgram() {
    return this.crpProgram;
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

  public User getUser() {
    return this.user;
  }

  @Override
  public boolean isActive() {
    return active;
  }

  public boolean isManager() {
    return manager;
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

  public void setCrpProgram(CrpProgram crpProgram) {
    this.crpProgram = crpProgram;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setManager(boolean manager) {
    this.manager = manager;
  }

  public void setModificationJustification(String modificationJustification) {
    this.modificationJustification = modificationJustification;
  }

  public void setModifiedBy(User modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public String toString() {
    return "CrpProgramLeader [id=" + id + ", crpProgram=" + crpProgram + ", user=" + user + ", manager=" + manager
      + "]";
  }

}

