package org.cgiar.ccafs.marlo.data.model;
// Generated Feb 15, 2017 8:49:31 AM by Hibernate Tools 3.4.0.CR1


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.Expose;

/**
 * CenterProjectStatus generated by hbm2java
 */
public class CenterProjectStatus implements java.io.Serializable, IAuditLog {


  private static final long serialVersionUID = -6089631421914717278L;


  @Expose
  private Long id;


  @Expose
  private User modifiedBy;


  @Expose
  private User createdBy;


  @Expose
  private String name;


  @Expose
  private boolean active;

  @Expose
  private Date activeSince;

  @Expose
  private String modificationJustification;

  private Set<CenterProject> projects = new HashSet<CenterProject>(0);

  private Set<CenterDeliverable> deliverables = new HashSet<CenterDeliverable>(0);

  public CenterProjectStatus() {
  }

  public CenterProjectStatus(Long id, boolean active) {
    this.id = id;
    this.active = active;
  }

  public CenterProjectStatus(Long id, User modifiedBy, User createdBy, String name, boolean active, Date activeSince,
    String modificationJustification, Set<CenterProject> projects) {
    this.id = id;
    this.modifiedBy = modifiedBy;
    this.createdBy = createdBy;
    this.name = name;
    this.active = active;
    this.activeSince = activeSince;
    this.modificationJustification = modificationJustification;
    this.projects = projects;
  }


  public Date getActiveSince() {
    return activeSince;
  }

  public User getCreatedBy() {
    return createdBy;
  }


  public Set<CenterDeliverable> getDeliverables() {
    return deliverables;
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
    return modificationJustification;
  }


  @Override
  public User getModifiedBy() {
    return modifiedBy;
  }


  public String getName() {
    return name;
  }


  public Set<CenterProject> getProjects() {
    return projects;
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

  public void setDeliverables(Set<CenterDeliverable> deliverables) {
    this.deliverables = deliverables;
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


  public void setName(String name) {
    this.name = name;
  }

  public void setProjects(Set<CenterProject> projects) {
    this.projects = projects;
  }


  @Override
  public String toString() {
    return "CenterProjectStatus [id=" + id + ", name=" + name + "]";
  }

}

