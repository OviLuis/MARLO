package org.cgiar.ccafs.marlo.data.model;
// Generated Dec 5, 2016 1:23:27 PM by Hibernate Tools 4.3.1.Final

import org.cgiar.ccafs.marlo.data.IAuditLog;

import com.google.gson.annotations.Expose;

/**
 * ProjectHighligthsTypes generated by hbm2java
 */
public class ProjectHighlightType implements java.io.Serializable, IAuditLog {


  /**
   * 
   */
  private static final long serialVersionUID = -4160886976093977707L;

  @Expose
  private Integer id;


  private ProjectHighlight projectHighlight;
  @Expose
  private int idType;


  public ProjectHighlightType() {
  }

  public ProjectHighlightType(ProjectHighlight projectHighlight, int idType) {
    this.projectHighlight = projectHighlight;
    this.idType = idType;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (this.getClass() != obj.getClass()) {
      return false;
    }
    ProjectHighlightType other = (ProjectHighlightType) obj;
    if (idType != other.idType) {
      return false;
    }
    return true;
  }

  @Override
  public Integer getId() {
    return id;
  }

  public int getIdType() {
    return idType;
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


  public ProjectHighlight getProjectHighligth() {
    return projectHighlight;
  }


  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + idType;
    return result;
  }


  @Override
  public boolean isActive() {

    return true;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public void setIdType(int idType) {
    this.idType = idType;
  }


  public void setProjectHighligth(ProjectHighlight projectHighlight) {
    this.projectHighlight = projectHighlight;
  }


  @Override
  public String toString() {
    return "ProjectHighlightType [id=" + id + ", projectHighlight=" + projectHighlight + ", idType=" + idType + "]";
  }

}

