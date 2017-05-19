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
import java.util.Set;

import com.google.gson.annotations.Expose;

/**
 * LocElement generated by hbm2java
 */
public class LocElement implements java.io.Serializable, IAuditLog {

  private static final long serialVersionUID = -5589133827714008187L;


  @Expose
  private Long id;

  @Expose
  private Crp crp;


  @Expose
  private String isoAlpha2;

  @Expose
  private Long isoNumeric;


  @Expose
  private LocElementType locElementType;

  @Expose
  private LocGeoposition locGeoposition;

  @Expose
  private String name;

  @Expose
  private LocElement locElement;


  @Expose
  private Boolean isSiteIntegration;

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


  private Set<CrpsSiteIntegration> crpsSitesIntegrations = new HashSet<CrpsSiteIntegration>(0);


  private Set<CrpProgramCountry> crpProgramCountries = new HashSet<CrpProgramCountry>(0);


  private Set<Institution> institutions = new HashSet<Institution>(0);

  private Set<ProjectLocation> projectLocations = new HashSet<ProjectLocation>(0);
  private Set<InstitutionLocation> institutionLocations = new HashSet<InstitutionLocation>(0);


  public LocElement() {
  }


  public LocElement(LocElementType locElementTypes, LocGeoposition locGeopositions, String name, String isoAlpha2,
    LocElement locElement, Boolean isSiteIntegration, Set<CrpsSiteIntegration> crpsSitesIntegrations,
    Set<Institution> institutions, Set<ProjectLocation> projectLocations) {
    this.locElementType = locElementTypes;
    this.locGeoposition = locGeopositions;
    this.name = name;
    this.isoAlpha2 = isoAlpha2;
    this.locElement = locElement;
    this.isSiteIntegration = isSiteIntegration;
    this.crpsSitesIntegrations = crpsSitesIntegrations;
    this.institutions = institutions;
    this.projectLocations = projectLocations;
  }

  public LocElement(LocElementType locElementTypes, String name) {
    this.locElementType = locElementTypes;
    this.name = name;
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
    LocElement other = (LocElement) obj;
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

  public Crp getCrp() {
    return crp;
  }

  public Set<CrpProgramCountry> getCrpProgramCountries() {
    return crpProgramCountries;
  }

  public Set<CrpsSiteIntegration> getCrpsSitesIntegrations() {
    return this.crpsSitesIntegrations;
  }

  @Override
  public Long getId() {
    return this.id;
  }

  public Set<InstitutionLocation> getInstitutionLocations() {
    return institutionLocations;
  }

  public Set<Institution> getInstitutions() {
    return institutions;
  }

  public String getIsoAlpha2() {
    return isoAlpha2;
  }

  public Long getIsoNumeric() {
    return isoNumeric;
  }

  public Boolean getIsSiteIntegration() {
    return this.isSiteIntegration;
  }

  public LocElement getLocElement() {
    return locElement;
  }

  public LocElementType getLocElementType() {
    return this.locElementType;
  }

  public LocGeoposition getLocGeoposition() {
    return this.locGeoposition;
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

  public String getName() {
    return this.name;
  }


  public Set<ProjectLocation> getProjectLocations() {
    return projectLocations;
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

  public void setCreatedBy(User createdBy) {
    this.createdBy = createdBy;
  }

  public void setCrp(Crp crp) {
    this.crp = crp;
  }

  public void setCrpProgramCountries(Set<CrpProgramCountry> crpProgramCountries) {
    this.crpProgramCountries = crpProgramCountries;
  }

  public void setCrpsSitesIntegrations(Set<CrpsSiteIntegration> crpsSitesIntegrations) {
    this.crpsSitesIntegrations = crpsSitesIntegrations;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setInstitutionLocations(Set<InstitutionLocation> institutionLocations) {
    this.institutionLocations = institutionLocations;
  }

  public void setInstitutions(Set<Institution> institutions) {
    this.institutions = institutions;
  }

  public void setIsoAlpha2(String isoAlpha2) {
    this.isoAlpha2 = isoAlpha2;
  }

  public void setIsoNumeric(Long isoNumeric) {
    this.isoNumeric = isoNumeric;
  }

  public void setIsSiteIntegration(Boolean isSiteIntegration) {
    this.isSiteIntegration = isSiteIntegration;
  }

  public void setLocElement(LocElement locElement) {
    this.locElement = locElement;
  }

  public void setLocElementType(LocElementType locElementTypes) {
    this.locElementType = locElementTypes;
  }

  public void setLocGeoposition(LocGeoposition locGeopositions) {
    this.locGeoposition = locGeopositions;
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

  public void setProjectLocations(Set<ProjectLocation> projectLocations) {
    this.projectLocations = projectLocations;
  }

  @Override
  public String toString() {
    return id.toString();
  }
}

