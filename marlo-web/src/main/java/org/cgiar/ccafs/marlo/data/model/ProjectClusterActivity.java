package org.cgiar.ccafs.marlo.data.model;
// Generated Aug 17, 2016 3:47:49 PM by Hibernate Tools 4.3.1.Final


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.Date;

import com.google.gson.annotations.Expose;

/**
 * ProjectClusterActivity generated by hbm2java
 */
public class ProjectClusterActivity implements java.io.Serializable, IAuditLog {


  /**
   * 
   */
  private static final long serialVersionUID = 7447742527635093703L;
  @Expose
  private Long id;

  @Expose
  private CrpClusterOfActivity crpClusterOfActivity;

  @Expose
  private Project project;
  @Expose
  private Phase phase;

  @Expose
  private User createdBy;


  @Expose
  private User modifiedBy;

  @Expose
  private boolean active;
  @Expose
  private Date activeSince;
  @Expose
  private String modificationJustification;

  public ProjectClusterActivity() {
  }

  public ProjectClusterActivity(CrpClusterOfActivity crpClusterOfActivity, Project project, User usersByCreatedBy,
    User usersByModifiedBy, boolean isActive, Date activeSince, String modificationJustification) {
    this.crpClusterOfActivity = crpClusterOfActivity;
    this.project = project;
    this.createdBy = usersByCreatedBy;
    this.modifiedBy = usersByModifiedBy;
    this.active = isActive;
    this.activeSince = activeSince;
    this.modificationJustification = modificationJustification;
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
    ProjectClusterActivity other = (ProjectClusterActivity) obj;
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!id.equals(other.id)) {
      return false;
    }
    return true;
  }

  public Date getActiveSince() {
    return this.activeSince;
  }

  public User getCreatedBy() {
    return createdBy;
  }

  public CrpClusterOfActivity getCrpClusterOfActivity() {
    return crpClusterOfActivity;
  }


  @Override
  public Long getId() {
    return this.id;
  }

  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();

    sb.append("Id : ").append(this.getId());


    return sb.toString();
  }

  @Override
  @Override
  public String getModificationJustification() {
    return this.modificationJustification;
  }


  @Override
  public User getModifiedBy() {
    return modifiedBy;
  }


  public Phase getPhase() {
    return phase;
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
    return active;
  }


  public void setActive(boolean active) {
    this.active = active;
  }


  public void setActiveSince(Date activeSince) {
    this.activeSince = activeSince;
  }


  public void setCreatedBy(User createdBy) {
    this.createdBy = createdBy;
  }


  public void setCrpClusterOfActivity(CrpClusterOfActivity crpClusterOfActivity) {
    this.crpClusterOfActivity = crpClusterOfActivity;
  }


  public void setId(Long id) {
    this.id = id;
  }

  public void setModificationJustification(String modificationJustification) {
    this.modificationJustification = modificationJustification;
  }


  public void setModifiedBy(User modifiedBy) {
    this.modifiedBy = modifiedBy;
  }


  public void setPhase(Phase phase) {
    this.phase = phase;
  }


  public void setProject(Project project) {
    this.project = project;
  }

  @Override
  public String toString() {
    return "ProjectClusterActivity [id=" + id + ", crpClusterOfActivity=" + crpClusterOfActivity + ", project="
      + project + "]";
  }


}

