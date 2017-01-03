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
package org.cgiar.ccafs.marlo.data.manager;

import org.cgiar.ccafs.marlo.data.manager.impl.IpProgramTypeManagerImpl;
import org.cgiar.ccafs.marlo.data.model.IpProgramType;

import java.util.List;

import com.google.inject.ImplementedBy;

/**
 * @author Christian Garcia
 */
@ImplementedBy(IpProgramTypeManagerImpl.class)
public interface IpProgramTypeManager {


  /**
   * This method removes a specific ipProgramType value from the database.
   * 
   * @param ipProgramTypeId is the ipProgramType identifier.
   * @return true if the ipProgramType was successfully deleted, false otherwise.
   */
  public boolean deleteIpProgramType(long ipProgramTypeId);


  /**
   * This method validate if the ipProgramType identify with the given id exists in the system.
   * 
   * @param ipProgramTypeID is a ipProgramType identifier.
   * @return true if the ipProgramType exists, false otherwise.
   */
  public boolean existIpProgramType(long ipProgramTypeID);


  /**
   * This method gets a list of ipProgramType that are active
   * 
   * @return a list from IpProgramType null if no exist records
   */
  public List<IpProgramType> findAll();


  /**
   * This method gets a ipProgramType object by a given ipProgramType identifier.
   * 
   * @param ipProgramTypeID is the ipProgramType identifier.
   * @return a IpProgramType object.
   */
  public IpProgramType getIpProgramTypeById(long ipProgramTypeID);

  /**
   * This method saves the information of the given ipProgramType
   * 
   * @param ipProgramType - is the ipProgramType object with the new information to be added/updated.
   * @return a number greater than 0 representing the new ID assigned by the database, 0 if the ipProgramType was
   *         updated
   *         or -1 is some error occurred.
   */
  public long saveIpProgramType(IpProgramType ipProgramType);


}
