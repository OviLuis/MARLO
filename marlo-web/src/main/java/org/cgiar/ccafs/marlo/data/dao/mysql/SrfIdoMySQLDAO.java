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


package org.cgiar.ccafs.marlo.data.dao.mysql;

import org.cgiar.ccafs.marlo.data.dao.SrfIdoDAO;
import org.cgiar.ccafs.marlo.data.model.SrfIdo;

import java.util.List;

import com.google.inject.Inject;

public class SrfIdoMySQLDAO implements SrfIdoDAO {

  private StandardDAO dao;

  @Inject
  public SrfIdoMySQLDAO(StandardDAO dao) {
    this.dao = dao;
  }

  @Override
  public boolean deleteSrfIdo(long srfIdoId) {
    SrfIdo srfIdo = this.find(srfIdoId);
    srfIdo.setActive(false);
    return this.save(srfIdo) > 0;
  }

  @Override
  public boolean existSrfIdo(long srfIdoID) {
    SrfIdo srfIdo = this.find(srfIdoID);
    if (srfIdo == null) {
      return false;
    }
    return true;

  }

  @Override
  public SrfIdo find(long id) {
    return dao.find(SrfIdo.class, id);

  }

  @Override
  public List<SrfIdo> findAll() {
    String query = "from " + SrfIdo.class.getName() + " where is_active=1";
    List<SrfIdo> list = dao.findAll(query);
    if (list.size() > 0) {
      return list;
    }
    return null;

  }

  @Override
  public long save(SrfIdo srfIdo) {
    if (srfIdo.getId() == null) {
      dao.save(srfIdo);
    } else {
      dao.update(srfIdo);
    }
    return srfIdo.getId();
  }


}