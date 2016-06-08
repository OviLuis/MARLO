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


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * SrfCrossCuttingIssue generated by hbm2java
 */
public class SrfCrossCuttingIssue implements java.io.Serializable {


  /**
   * 
   */
  private static final long serialVersionUID = -1176189419675828693L;


  private Long id;


  private String name;


  private Set<SrfIdo> srfIdos = new HashSet<SrfIdo>(0);


  private boolean active;


  private User createdBy;


  private Date activeSince;


  private User modifiedBy;

  private String modificationJustification;

  public SrfCrossCuttingIssue() {
  }

  public SrfCrossCuttingIssue(String name) {
    this.name = name;
  }

  public SrfCrossCuttingIssue(String name, Set<SrfIdo> srfIdos) {
    this.name = name;
    this.srfIdos = srfIdos;
  }

  public Date getActiveSince() {
    return activeSince;
  }

  public User getCreatedBy() {
    return createdBy;
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

  public String getName() {
    return this.name;
  }

  public Set<SrfIdo> getSrfIdos() {
    return this.srfIdos;
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

  public void setId(Long id) {
    this.id = id;
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

  public void setSrfIdos(Set<SrfIdo> srfIdos) {
    this.srfIdos = srfIdos;
  }


}

