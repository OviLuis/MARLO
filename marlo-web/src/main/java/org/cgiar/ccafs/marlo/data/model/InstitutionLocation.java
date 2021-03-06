package org.cgiar.ccafs.marlo.data.model;
// Generated May 5, 2017 11:04:03 AM by Hibernate Tools 4.3.1.Final

import org.cgiar.ccafs.marlo.data.IAuditLog;

import com.google.gson.annotations.Expose;

/**
 * InstitutionsLocations generated by hbm2java
 */
public class InstitutionLocation implements java.io.Serializable, IAuditLog {


  private static final long serialVersionUID = 304237036320773165L;
  @Expose
  private Long id;

  private Institution institution;
  @Expose
  private LocElement locElement;
  @Expose
  private boolean headquater;
  @Expose
  private String city;


  public InstitutionLocation() {
  }


  public InstitutionLocation(Institution institution, LocElement locElement, boolean isHeadquater) {
    this.institution = institution;
    this.locElement = locElement;
    this.headquater = isHeadquater;
  }


  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }

    InstitutionLocation other = (InstitutionLocation) obj;
    if (locElement == null) {
      if (other.locElement != null) {
        return false;
      }
    } else if (!this.getLocElement().getIsoAlpha2().equals(other.getLocElement().getIsoAlpha2())) {
      return false;
    }
    return true;
  }


  public String getCity() {
    return city;
  }

  public String getComposedName() {

    if (this.isHeadquater()) {
      return "HQ: " + this.getLocElement().getName();
    }
    return this.getLocElement().getName();
  }

  @Override
  public Long getId() {
    return id;
  }

  public Institution getInstitution() {
    return institution;
  }

  public LocElement getLocElement() {
    return locElement;
  }

  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();
    sb.append("Id : ").append(this.getId());
    return sb.toString();
  }

  @Override
  public String getModificationJustification() {

    return "";
  }


  @Override
  public User getModifiedBy() {
    User u = new User();
    u.setId(new Long(3));
    return u;
  }


  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((locElement == null) ? 0 : locElement.hashCode());
    return result;
  }

  @Override
  public boolean isActive() {

    return true;
  }

  public boolean isHeadquater() {
    return headquater;
  }


  public void setCity(String city) {
    this.city = city;
  }


  public void setHeadquater(boolean isHeadquater) {
    this.headquater = isHeadquater;
  }


  public void setId(Long id) {
    this.id = id;
  }


  public void setInstitution(Institution institution) {
    this.institution = institution;
  }


  public void setLocElement(LocElement locElement) {
    this.locElement = locElement;
  }


  @Override
  public String toString() {
    return "InstitutionLocation [id=" + id + ", institution=" + institution + ", locElement=" + locElement
      + ", headquater=" + headquater + ", city=" + city + "]";
  }


}

