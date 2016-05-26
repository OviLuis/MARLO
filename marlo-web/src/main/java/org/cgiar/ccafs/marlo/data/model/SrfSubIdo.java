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
 * SrfSubIdo generated by hbm2java
 */
public class SrfSubIdo implements java.io.Serializable {


  /**
   * 
   */
  private static final long serialVersionUID = -6590673724952913143L;
  private Long id;
  private SrfIdo srfIdos;
  private String description;
  private Set<CrpOutcomeSubIdo> crpOutcomeSubIdos = new HashSet<CrpOutcomeSubIdo>(0);
  private Set<CrpSubIdosContribution> crpSubIdosContributions = new HashSet<CrpSubIdosContribution>(0);

  public SrfSubIdo() {
  }


  public SrfSubIdo(SrfIdo srfIdos, String description) {
    this.srfIdos = srfIdos;
    this.description = description;
  }

  public SrfSubIdo(SrfIdo srfIdos, String description, Set<CrpOutcomeSubIdo> crpOutcomeSubIdos,
    Set<CrpSubIdosContribution> crpSubIdosContributions) {
    this.srfIdos = srfIdos;
    this.description = description;
    this.crpOutcomeSubIdos = crpOutcomeSubIdos;
    this.crpSubIdosContributions = crpSubIdosContributions;
  }

  public Set<CrpOutcomeSubIdo> getCrpOutcomeSubIdos() {
    return this.crpOutcomeSubIdos;
  }

  public Set<CrpSubIdosContribution> getCrpSubIdosContributions() {
    return this.crpSubIdosContributions;
  }

  public String getDescription() {
    return this.description;
  }

  public Long getId() {
    return this.id;
  }

  public SrfIdo getSrfIdos() {
    return this.srfIdos;
  }

  public void setCrpOutcomeSubIdos(Set<CrpOutcomeSubIdo> crpOutcomeSubIdos) {
    this.crpOutcomeSubIdos = crpOutcomeSubIdos;
  }

  public void setCrpSubIdosContributions(Set<CrpSubIdosContribution> crpSubIdosContributions) {
    this.crpSubIdosContributions = crpSubIdosContributions;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setSrfIdos(SrfIdo srfIdos) {
    this.srfIdos = srfIdos;
  }


}

