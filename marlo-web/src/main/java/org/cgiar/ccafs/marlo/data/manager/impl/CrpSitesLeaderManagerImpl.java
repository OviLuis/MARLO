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
package org.cgiar.ccafs.marlo.data.manager.impl;


import org.cgiar.ccafs.marlo.data.dao.CrpSitesLeaderDAO;
import org.cgiar.ccafs.marlo.data.manager.CrpSitesLeaderManager;
import org.cgiar.ccafs.marlo.data.model.CrpSitesLeader;

import java.util.List;

import com.google.inject.Inject;

/**
 * @author Christian Garcia
 */
public class CrpSitesLeaderManagerImpl implements CrpSitesLeaderManager {


  private CrpSitesLeaderDAO crpSitesLeaderDAO;
  // Managers


  @Inject
  public CrpSitesLeaderManagerImpl(CrpSitesLeaderDAO crpSitesLeaderDAO) {
    this.crpSitesLeaderDAO = crpSitesLeaderDAO;


  }

  @Override
  public boolean deleteCrpSitesLeader(long crpSitesLeaderId) {

    return crpSitesLeaderDAO.deleteCrpSitesLeader(crpSitesLeaderId);
  }

  @Override
  public boolean existCrpSitesLeader(long crpSitesLeaderID) {

    return crpSitesLeaderDAO.existCrpSitesLeader(crpSitesLeaderID);
  }

  @Override
  public List<CrpSitesLeader> findAll() {

    return crpSitesLeaderDAO.findAll();

  }

  @Override
  public CrpSitesLeader getCrpSitesLeaderById(long crpSitesLeaderID) {

    return crpSitesLeaderDAO.find(crpSitesLeaderID);
  }

  @Override
  public long saveCrpSitesLeader(CrpSitesLeader crpSitesLeader) {

    return crpSitesLeaderDAO.save(crpSitesLeader);
  }


}
