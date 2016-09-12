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


package org.cgiar.ccafs.marlo.data.dao;

import org.cgiar.ccafs.marlo.data.dao.mysql.ProjectBilateralCofinancingMySQLDAO;
import org.cgiar.ccafs.marlo.data.model.ProjectBilateralCofinancing;

import java.util.List;
import java.util.Map;

import com.google.inject.ImplementedBy;

@ImplementedBy(ProjectBilateralCofinancingMySQLDAO.class)
public interface ProjectBilateralCofinancingDAO {

  /**
   * This method removes a specific projectBilateralCofinancing value from the database.
   * 
   * @param projectBilateralCofinancingId is the projectBilateralCofinancing identifier.
   * @return true if the projectBilateralCofinancing was successfully deleted, false otherwise.
   */
  public boolean deleteProjectBilateralCofinancing(long projectBilateralCofinancingId);

  /**
   * This method validate if the projectBilateralCofinancing identify with the given id exists in the system.
   * 
   * @param projectBilateralCofinancingID is a projectBilateralCofinancing identifier.
   * @return true if the projectBilateralCofinancing exists, false otherwise.
   */
  public boolean existProjectBilateralCofinancing(long projectBilateralCofinancingID);

  /**
   * This method gets a projectBilateralCofinancing object by a given projectBilateralCofinancing identifier.
   * 
   * @param projectBilateralCofinancingID is the projectBilateralCofinancing identifier.
   * @return a ProjectBilateralCofinancing object.
   */
  public ProjectBilateralCofinancing find(long id);

  /**
   * This method gets a list of projectBilateralCofinancing that are active
   * 
   * @return a list from ProjectBilateralCofinancing null if no exist records
   */
  public List<ProjectBilateralCofinancing> findAll();


  /**
   * Get the list of permissions that the user can access and edit.
   * 
   * @param userId - The user Id.
   * @param crp - The crp acronym that the user has logged.
   * @return a permissions user list.
   */
  public List<Map<String, Object>> getUserCofundedProjects(long userId, String crp);

  /**
   * This method saves the information of the given projectBilateralCofinancing
   * 
   * @param projectBilateralCofinancing - is the projectBilateralCofinancing object with the new information to be
   *        added/updated.
   * @return a number greater than 0 representing the new ID assigned by the database, 0 if the
   *         projectBilateralCofinancing was
   *         updated
   *         or -1 is some error occurred.
   */
  public long save(ProjectBilateralCofinancing projectBilateralCofinancing);

  public List<ProjectBilateralCofinancing> searchProject(String searchValue, long institutionID, int year);
}
