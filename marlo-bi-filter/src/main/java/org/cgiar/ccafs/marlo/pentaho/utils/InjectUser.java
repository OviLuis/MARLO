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


package org.cgiar.ccafs.marlo.pentaho.utils;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;


public class InjectUser extends HttpServletRequestWrapper {

  private final Map<String, String[]> modifiableParameters;
  private Map<String, String[]> allParameters = null;
  private Map<String, String[]> additionalParams = new HashMap<String, String[]>();

  public InjectUser(HttpServletRequest request, String usr, String password) {
    super(request);

    modifiableParameters = new TreeMap<String, String[]>();
    additionalParams.put("userid", new String[] {usr});
    additionalParams.put("password", new String[] {password});
    modifiableParameters.putAll(additionalParams);
  }


  @Override
  public String getParameter(final String name) {
    String[] strings = this.getParameterMap().get(name);
    if (strings != null) {
      return strings[0];
    }
    return super.getParameter(name);
  }

  @Override
  public Map<String, String[]> getParameterMap() {
    if (allParameters == null) {
      allParameters = new TreeMap<String, String[]>();
      allParameters.putAll(super.getParameterMap());
      allParameters.putAll(modifiableParameters);
    }
    // Return an unmodifiable collection because we need to uphold the interface contract.
    return Collections.unmodifiableMap(allParameters);
  }

  @Override
  public Enumeration<String> getParameterNames() {
    return Collections.enumeration(this.getParameterMap().keySet());
  }

  @Override
  public String[] getParameterValues(final String name) {
    return this.getParameterMap().get(name);
  }

}
