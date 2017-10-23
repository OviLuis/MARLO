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


package org.cgiar.ccafs.marlo.data.dao.mysql;

import org.cgiar.ccafs.marlo.data.dao.CountryAgreementDAO;
import org.cgiar.ccafs.marlo.data.model.CountryAgreement;

import java.util.List;

import com.google.inject.Inject;


public class CountryAgreementMySQLDAO implements CountryAgreementDAO {

  private StandardDAO dao;

  @Inject
  public CountryAgreementMySQLDAO(StandardDAO dao) {
    this.dao = dao;
  }

  /**
   * find a country agreement by its id
   * 
   * @author Julián Rodríguez Calderón
   * @since 20171023
   * @param id - id of the country agreement
   * @return
   */
  @Override
  public CountryAgreement find(String id) {
    return dao.find(CountryAgreement.class, id);
  }

  /**
   * find a country agreement by its code and agreement id
   * 
   * @author Julián Rodríguez Calderón
   * @since 20171023
   * @param code - code of the country
   * @param agreement - id of the agreement
   * @return
   */
  @Override
  public CountryAgreement findByCodeAndAgreement(String code, String agreement) {
    String query = "from " + CountryAgreement.class.getName() + "agreements.id =" + agreement + "and code=" + code;
    List<CountryAgreement> list = dao.findAll(query);
    if (list.size() > 0) {
      return list.iterator().next();
    }
    return null;
  }

  /**
   * find all the countries agreement of an agreement
   * 
   * @author Julián Rodríguez Calderón
   * @since 20171023
   * @param codAgreement - id code of the agreement
   * @return
   */
  @Override
  public List<CountryAgreement> getCountriesByAgreement(String codAgreement) {

    String query = "from " + CountryAgreement.class.getName() + "agreements.id =" + codAgreement;
    List<CountryAgreement> list = dao.findAll(query);
    if (list.size() > 0) {
      return list;
    }
    return null;

  }

  /**
   * save a new country agreement
   * 
   * @author Julián Rodríguez Calderón
   * @since 20171023
   * @param country - the country agreement object
   * @return - the code of the new agreement
   */
  @Override
  public String save(CountryAgreement country) {
    dao.save(country);
    return String.valueOf(country.getId());
  }


  /**
   * update a country agreement
   * 
   * @author Julián Rodríguez Calderón
   * @since 20171023
   * @param country - the country agreement object
   * @return - the code of the updated agreement
   */
  @Override
  public String update(CountryAgreement country) {
    dao.update(country);
    return String.valueOf(country.getId());
  }

}