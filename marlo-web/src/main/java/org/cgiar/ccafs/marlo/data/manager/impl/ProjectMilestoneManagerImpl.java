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
package org.cgiar.ccafs.marlo.data.manager.impl;


import org.cgiar.ccafs.marlo.data.dao.ProjectMilestoneDAO;
import org.cgiar.ccafs.marlo.data.manager.ProjectMilestoneManager;
import org.cgiar.ccafs.marlo.data.model.ProjectMilestone;

import java.util.List;

import javax.inject.Named;
import javax.inject.Inject;

/**
 * @author Christian Garcia
 */
@Named
public class ProjectMilestoneManagerImpl implements ProjectMilestoneManager {


  private ProjectMilestoneDAO projectMilestoneDAO;
  // Managers


  @Inject
  public ProjectMilestoneManagerImpl(ProjectMilestoneDAO projectMilestoneDAO) {
    this.projectMilestoneDAO = projectMilestoneDAO;


  }

  @Override
  public void deleteProjectMilestone(long projectMilestoneId) {

    projectMilestoneDAO.deleteProjectMilestone(projectMilestoneId);
  }

  @Override
  public boolean existProjectMilestone(long projectMilestoneID) {

    return projectMilestoneDAO.existProjectMilestone(projectMilestoneID);
  }

  @Override
  public List<ProjectMilestone> findAll() {

    return projectMilestoneDAO.findAll();

  }

  @Override
  public ProjectMilestone getProjectMilestoneById(long projectMilestoneID) {

    return projectMilestoneDAO.find(projectMilestoneID);
  }

  @Override
  public ProjectMilestone saveProjectMilestone(ProjectMilestone projectMilestone) {

    return projectMilestoneDAO.save(projectMilestone);
  }


}
