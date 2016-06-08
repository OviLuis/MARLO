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
// Generated May 17, 2016 9:53:46 AM by Hibernate Tools 4.3.1.Final

import java.util.Date;

import com.google.gson.annotations.Expose;

/**
 * CrpUsers generated by hbm2java
 */
public class CrpUser implements java.io.Serializable {


  /**
   * 
   */
  private static final long serialVersionUID = -1140371637324045336L;


  private @Expose Long id;


  private Crp crp;


  private User user;


  private @Expose boolean active;


  private User createdBy;


  private Date activeSince;


  private User modifiedBy;

  private String modificationJustification;

  public CrpUser() {
  }

  public CrpUser(Crp crps, User users) {
    this.crp = crps;
    this.user = users;
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

  public Long getId() {
    return this.id;
  }

  public String getModificationJustification() {
    return modificationJustification;
  }

  public User getModifiedBy() {
    return modifiedBy;
  }

  public User getUser() {
    return this.user;
  }

  public boolean isActive() {
    return active;
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

  public void setCrp(Crp crps) {
    this.crp = crps;
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

  public void setUser(User users) {
    this.user = users;
  }


}

