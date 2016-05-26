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


import java.math.BigDecimal;

/**
 * CrpMilestone generated by hbm2java
 */
public class CrpMilestone implements java.io.Serializable {


  /**
   * 
   */
  private static final long serialVersionUID = 4398175741401855358L;
  private Long id;
  private CrpProgramOutcome crpProgramOutcome;
  private SrfTargetUnit srfTargetUnit;
  private String title;
  private int year;
  private BigDecimal value;
  private boolean active;

  public CrpMilestone() {
  }

  public CrpMilestone(CrpProgramOutcome crpProgramOutcome, SrfTargetUnit srfTargetUnits, String title, int year,
    BigDecimal value) {
    this.crpProgramOutcome = crpProgramOutcome;
    this.srfTargetUnit = srfTargetUnit;
    this.title = title;
    this.year = year;
    this.value = value;
  }

  public CrpProgramOutcome getCrpProgramOutcome() {
    return this.crpProgramOutcome;
  }

  public Long getId() {
    return this.id;
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

  public int getYear() {
    return this.year;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public void setCrpProgramOutcome(CrpProgramOutcome crpProgramOutcome) {
    this.crpProgramOutcome = crpProgramOutcome;
  }

  public void setId(Long id) {
    this.id = id;
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

  public void setYear(int year) {
    this.year = year;
  }


}

