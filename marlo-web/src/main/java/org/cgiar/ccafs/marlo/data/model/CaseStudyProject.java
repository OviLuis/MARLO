package org.cgiar.ccafs.marlo.data.model;
// Generated Dec 12, 2016 3:55:58 PM by Hibernate Tools 4.3.1.Final

import org.cgiar.ccafs.marlo.data.IAuditLog;

import com.google.gson.annotations.Expose;

/**
 * CaseStudyProjects generated by hbm2java
 */
public class CaseStudyProject implements java.io.Serializable, IAuditLog {

  /**
   * 
   */
  private static final long serialVersionUID = -1004953642319195188L;

  @Expose
  private Long id;


  private CaseStudy caseStudy;

  @Expose
  private Project project;
  @Expose
  private boolean created;

  public CaseStudyProject() {
  }

  public CaseStudyProject(CaseStudy caseStudy, Project project) {
    this.caseStudy = caseStudy;
    this.project = project;
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
    CaseStudyProject other = (CaseStudyProject) obj;
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!id.equals(other.id)) {
      return false;
    }
    return true;
  }

  public CaseStudy getCaseStudy() {
    return caseStudy;
  }


  @Override
  public Long getId() {
    return id;
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


  public Project getProject() {
    return project;
  }


  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }


  @Override
  public boolean isActive() {

    return true;
  }


  public boolean isCreated() {
    return created;
  }


  public void setCaseStudy(CaseStudy caseStudy) {
    this.caseStudy = caseStudy;
  }


  public void setCreated(boolean created) {
    this.created = created;
  }


  public void setId(Long id) {
    this.id = id;
  }


  public void setProject(Project project) {
    this.project = project;
  }

  @Override
  public String toString() {
    return "CaseStudyProject [id=" + id + ", caseStudy=" + caseStudy + ", project=" + project + "]";
  }


}

