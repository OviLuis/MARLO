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


import java.util.HashSet;
import java.util.Set;

/**
 * LocElement generated by hbm2java
 */
public class LocElement implements java.io.Serializable {


  /**
   * 
   */
  private static final long serialVersionUID = -5589133827714008187L;
  private Long id;
  private LocElementType locElementTypes;
  private LocGeoposition locGeopositions;
  private String name;
  private String code;
  private Long parentId;
  private Boolean isSiteIntegration;
  private Set<CrpsSiteIntegration> crpsSitesIntegrations = new HashSet<CrpsSiteIntegration>(0);

  public LocElement() {
  }


  public LocElement(LocElementType locElementTypes, LocGeoposition locGeopositions, String name, String code,
    Long parentId, Boolean isSiteIntegration, Set<CrpsSiteIntegration> crpsSitesIntegrations) {
    this.locElementTypes = locElementTypes;
    this.locGeopositions = locGeopositions;
    this.name = name;
    this.code = code;
    this.parentId = parentId;
    this.isSiteIntegration = isSiteIntegration;
    this.crpsSitesIntegrations = crpsSitesIntegrations;
  }

  public LocElement(LocElementType locElementTypes, String name) {
    this.locElementTypes = locElementTypes;
    this.name = name;
  }

  public String getCode() {
    return this.code;
  }

  public Set<CrpsSiteIntegration> getCrpsSitesIntegrations() {
    return this.crpsSitesIntegrations;
  }

  public Long getId() {
    return this.id;
  }

  public Boolean getIsSiteIntegration() {
    return this.isSiteIntegration;
  }

  public LocElementType getLocElementTypes() {
    return this.locElementTypes;
  }

  public LocGeoposition getLocGeopositions() {
    return this.locGeopositions;
  }

  public String getName() {
    return this.name;
  }

  public Long getParentId() {
    return this.parentId;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public void setCrpsSitesIntegrations(Set<CrpsSiteIntegration> crpsSitesIntegrations) {
    this.crpsSitesIntegrations = crpsSitesIntegrations;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setIsSiteIntegration(Boolean isSiteIntegration) {
    this.isSiteIntegration = isSiteIntegration;
  }

  public void setLocElementTypes(LocElementType locElementTypes) {
    this.locElementTypes = locElementTypes;
  }

  public void setLocGeopositions(LocGeoposition locGeopositions) {
    this.locGeopositions = locGeopositions;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }


}

