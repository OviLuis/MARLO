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

import org.cgiar.ccafs.marlo.data.dao.LocElementDAO;
import org.cgiar.ccafs.marlo.data.model.LocElement;

import java.util.List;

import com.google.inject.Inject;

public class LocElementMySQLDAO implements LocElementDAO {

  private StandardDAO dao;

  @Inject
  public LocElementMySQLDAO(StandardDAO dao) {
    this.dao = dao;
  }

  @Override
  public boolean deleteLocElement(long locElementId) {
    LocElement locElement = this.find(locElementId);

    return dao.delete(locElement);
  }

  @Override
  public boolean existLocElement(long locElementID) {
    LocElement locElement = this.find(locElementID);
    if (locElement == null) {
      return false;
    }
    return true;

  }

  @Override
  public LocElement find(long id) {
    return dao.find(LocElement.class, id);

  }

  @Override
  public List<LocElement> findAll() {
    String query = "from " + LocElement.class.getName();
    List<LocElement> list = dao.findAll(query);
    if (list.size() > 0) {
      return list;
    }
    return null;

  }

  @Override
  public long save(LocElement locElement) {
    if (locElement.getId() == null) {
      dao.save(locElement);
    } else {
      dao.update(locElement);
    }
    return locElement.getId();
  }


}