package org.cgiar.ccafs.marlo.data.model;
// Generated Jan 11, 2017 1:46:43 PM by Hibernate Tools 4.3.1.Final


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.Date;

import com.google.gson.annotations.Expose;

/**
 * OtherContributions generated by hbm2java
 */
public class OtherContribution implements java.io.Serializable, IAuditLog {


  /**
   * 
   */
  private static final long serialVersionUID = 4819733480683048733L;


  @Expose
  private Long id;
  @Expose
  private IpIndicator ipIndicator;
  @Expose
  private IpProgram ipProgram;

  private Project project;


  @Expose
  private User modifiedBy;
  @Expose
  private User createdBy;
  @Expose
  private String description;
  @Expose
  private Integer target;
  @Expose
  private boolean active;
  @Expose
  private Date activeSince;
  @Expose
  private String modificationJustification;

  public OtherContribution() {
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
    OtherContribution other = (OtherContribution) obj;
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
    return activeSince;
  }


  public User getCreatedBy() {
    return createdBy;
  }


  public String getDescription() {
    return description;
  }


  @Override
  public Long getId() {
    return id;
  }


  public IpIndicator getIpIndicator() {
    return ipIndicator;
  }


  public IpProgram getIpProgram() {
    return ipProgram;
  }


  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();

    sb.append("Id : ").append(this.getId());


    return sb.toString();
  }


  @Override
  public String getModificationJustification() {
    return modificationJustification;
  }


  @Override
  public User getModifiedBy() {
    return modifiedBy;
  }


  public Project getProject() {
    return project;
  }


  public Integer getTarget() {
    return target;
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


  public void setDescription(String description) {
    this.description = description;
  }


  public void setId(Long id) {
    this.id = id;
  }


  public void setIpIndicator(IpIndicator ipIndicator) {
    this.ipIndicator = ipIndicator;
  }


  public void setIpProgram(IpProgram ipProgram) {
    this.ipProgram = ipProgram;
  }


  public void setModificationJustification(String modificationJustification) {
    this.modificationJustification = modificationJustification;
  }

  public void setModifiedBy(User modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  public void setTarget(Integer target) {
    this.target = target;
  }


  @Override
  public String toString() {
    return "OtherContribution [id=" + id + ", ipIndicator=" + ipIndicator + ", ipProgram=" + ipProgram + ", project="
      + project + ", description=" + description + ", target=" + target + "]";
  }


}

