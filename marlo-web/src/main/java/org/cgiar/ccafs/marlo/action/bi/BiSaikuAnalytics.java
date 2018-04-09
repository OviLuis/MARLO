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


package org.cgiar.ccafs.marlo.action.bi;

import org.cgiar.ccafs.marlo.action.BaseAction;
import org.cgiar.ccafs.marlo.utils.APConfig;
import org.cgiar.ccafs.marlo.utils.MD5Convert;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

/**
 * This action is responsible for generating the URL of BI for SAIKU analysis. The URL is composed of date + security
 * text + destination. The destination will depend on the user and the CRP, which will obtain the appropriate
 * destination from the database.
 * 
 * @author julianrodriguez
 * @update Only generate the URL of BI. Pending assign destination by user and Global Unit
 */
public class BiSaikuAnalytics extends BaseAction {

  private static final long serialVersionUID = 1L;

  private String urlSaiku;


  @Inject
  public BiSaikuAnalytics(APConfig config) {
    super(config);

  }

  @Override
  public String execute() throws Exception {

    Date today;
    String dateOut;
    SimpleDateFormat dateFormatter;
    dateFormatter = new SimpleDateFormat("dd-MM-yyyy", this.getLocale());

    today = new Date();

    dateOut = dateFormatter.format(today);

    // In the future, the destination will depend of the user and crp
    // create a token of the date (dd-MM-yyyy) + SomeExtraText + destination form. which understands the .jar that does
    // the bypass to Pentaho
    String token = MD5Convert.stringToMD5(dateOut + "SomeExtraText" + "destination_1");

    // create the url with the bypass
    this.urlSaiku = this.getText("bi.serverurl") + token + "&dst=destination_1";

    return SUCCESS;

  }

  public String getUrlSaiku() {
    return urlSaiku;
  }


  @Override
  public void prepare() throws Exception {
    // In the future here will validate the roles and the security.
  }


  public void setUrlSaiku(String urlSaiku) {
    this.urlSaiku = urlSaiku;
  }


}
