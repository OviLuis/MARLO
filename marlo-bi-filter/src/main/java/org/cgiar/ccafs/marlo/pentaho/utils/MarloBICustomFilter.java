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

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


public class MarloBICustomFilter implements Filter {

  public void destroy() {
    // TODO Auto-generated method stub

  }

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
    throws IOException, ServletException {

    // creation of the token
    String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    String dst = request.getParameter("dst");
    String myToken = request.getParameter("token");
    String usr = request.getParameter("userid");
    String usrPass = request.getParameter("userPass");
    String password = date + "SomeExtraText" + dst;

    MessageDigest md;
    StringBuffer sb = new StringBuffer();

    try {
      md = MessageDigest.getInstance("MD5");
      md.update(password.getBytes());
      byte byteData[] = md.digest();
      // convert the byte to hex format method 1
      for (int i = 0; i < byteData.length; i++) {
        sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
      }
      System.out.println("Digest(in hex format):: " + sb.toString());
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }

    // If myToken is equal to user's token
    if (myToken.equals(sb.toString())) {
      chain.doFilter(new InjectUser((HttpServletRequest) request, usr, usrPass), response);
    } else {
      chain.doFilter(request, response);
    }


  }

  public void init(FilterConfig arg0) throws ServletException {
    // TODO Auto-generated method stub

  }

}
