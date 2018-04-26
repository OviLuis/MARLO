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
import org.cgiar.ccafs.marlo.config.APConstants;
import org.cgiar.ccafs.marlo.data.manager.BiPermissionsManager;
import org.cgiar.ccafs.marlo.data.manager.GlobalUnitManager;
import org.cgiar.ccafs.marlo.data.model.BiPermissions;
import org.cgiar.ccafs.marlo.data.model.GlobalUnit;
import org.cgiar.ccafs.marlo.data.model.User;
import org.cgiar.ccafs.marlo.security.Permission;
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


  private GlobalUnitManager crpManager;
  private BiPermissionsManager biManager;

  private String urlSaiku;
  private GlobalUnit loggedCrp;
  private BiPermissions biPermissions;


  @Inject
  public BiSaikuAnalytics(APConfig config, GlobalUnitManager crpManager, BiPermissionsManager biManager) {
    super(config);
    this.crpManager = crpManager;
    this.biManager = biManager;

  }

  @Override
  public String execute() throws Exception {

    if (this.hasPermission(Permission.BI_ANALYTICS_PERMISSION)) {
      Date today;
      String dateOut;
      SimpleDateFormat dateFormatter;
      dateFormatter = new SimpleDateFormat("dd-MM-yyyy", this.getLocale());

      today = new Date();

      dateOut = dateFormatter.format(today);

      // create a token of the date (dd-MM-yyyy) + SomeExtraText + destination form. which understands the .jar that
      // does
      // the bypass to Pentaho

      String biUrl = null;
      if (this.biPermissions != null) {
        biUrl = this.biPermissions.getUrlbi();

        String token = MD5Convert.stringToMD5(dateOut + "SomeExtraText" + biUrl);

        // create the url with the bypass
        this.urlSaiku = this.getText("bi.serverurl") + token + "&dst=" + biUrl + "&urlUser="
          + this.biPermissions.getUserBi() + "&urlPass=" + this.biPermissions.getUserPass();
      }


    }


    return SUCCESS;

  }

  public String getUrlSaiku() {
    return urlSaiku;
  }


  @Override
  public void prepare() throws Exception {
    // In the future here will validate the roles and the security.
    loggedCrp = (GlobalUnit) this.getSession().get(APConstants.SESSION_CRP);
    loggedCrp = crpManager.getGlobalUnitById(loggedCrp.getId());
    User user = (User) this.getSession().get(APConstants.SESSION_USER);

    this.biPermissions =
      biManager.searchPermissionByUserAndType(user.getId(), Long.parseLong(APConstants.ANALYTICS_TYPE));


  }


  public void setUrlSaiku(String urlSaiku) {
    this.urlSaiku = urlSaiku;
  }


}
