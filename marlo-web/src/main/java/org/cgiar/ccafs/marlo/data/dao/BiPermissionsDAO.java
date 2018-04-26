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

import org.cgiar.ccafs.marlo.data.model.BiPermissions;

public interface BiPermissionsDAO {


  /**
   * This method gets a BiPermissions object by a given activity identifier.
   * 
   * @param permissionID is the permission identifier.
   * @return a BiPermissions object.
   */
  public BiPermissions find(long id);


  /**
   * this method gets the BiPermissions by user and type (Dashboard/analytics)
   * 
   * @param idUser
   * @param type
   * @return BiPermissions object
   */
  public BiPermissions findByUserAndType(long idUser, long type);


}
