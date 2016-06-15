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

import java.util.HashSet;
import java.util.Set;

public class InstitutionType implements java.io.Serializable {


  static final long serialVersionUID = -943657365260109270L;


  private Long id;

  private String name;

  private String acronym;
  private Set<Institution> institutions = new HashSet<Institution>(0);

  public InstitutionType() {
  }

  public InstitutionType(String name) {
    this.name = name;
  }

  public InstitutionType(String name, String acronym, Set<Institution> institutions) {
    this.name = name;
    this.acronym = acronym;
    this.institutions = institutions;
  }

  public String getAcronym() {
    return this.acronym;
  }

  public Long getId() {
    return this.id;
  }

  public Set<Institution> getInstitutions() {
    return institutions;
  }

  public String getName() {
    return this.name;
  }

  public void setAcronym(String acronym) {
    this.acronym = acronym;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setInstitutions(Set<Institution> institutions) {
    this.institutions = institutions;
  }

  public void setName(String name) {
    this.name = name;
  }

}
