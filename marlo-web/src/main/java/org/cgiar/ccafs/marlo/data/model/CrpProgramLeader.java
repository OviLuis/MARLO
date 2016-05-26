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


/**
 * CrpProgramLeader generated by hbm2java
 */
public class CrpProgramLeader implements java.io.Serializable {


  /**
   * 
   */
  private static final long serialVersionUID = 7345327536072331541L;
  private Long id;
  private CrpProgram crpPrograms;
  private User users;

  public CrpProgramLeader() {
  }

  public CrpProgramLeader(CrpProgram crpPrograms, User users) {
    this.crpPrograms = crpPrograms;
    this.users = users;
  }

  public CrpProgram getCrpPrograms() {
    return this.crpPrograms;
  }

  public Long getId() {
    return this.id;
  }

  public User getUsers() {
    return this.users;
  }

  public void setCrpPrograms(CrpProgram crpPrograms) {
    this.crpPrograms = crpPrograms;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setUsers(User users) {
    this.users = users;
  }


}

