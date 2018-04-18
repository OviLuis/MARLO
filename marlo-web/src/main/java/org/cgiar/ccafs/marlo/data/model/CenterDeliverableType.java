package org.cgiar.ccafs.marlo.data.model;
// Generated Mar 13, 2017 1:43:12 PM by Hibernate Tools 3.4.0.CR1


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.Expose;

/**
 * CenterDeliverableType generated by hbm2java
 */
public class CenterDeliverableType implements java.io.Serializable, IAuditLog {


  private static final long serialVersionUID = -5820694928440158435L;


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
  private String description;

  @Expose
  private CenterDeliverableType deliverableType;

  @Expose
  private String modificationJustification;

  private Set<CenterDeliverable> deliverables = new HashSet<CenterDeliverable>(0);

  private Set<CenterDeliverableType> centerDeliverableTypes = new HashSet<CenterDeliverableType>(0);

  public CenterDeliverableType() {
  }

  public CenterDeliverableType(String name, boolean active) {
    this.name = name;
    this.active = active;
  }

  public CenterDeliverableType(User modifiedBy, User createdBy, String name, boolean active, Date activeSince,
    String modificationJustification, Set<CenterDeliverable> deliverables) {
    this.modifiedBy = modifiedBy;
    this.createdBy = createdBy;
    this.name = name;
    this.active = active;
    this.activeSince = activeSince;
    this.modificationJustification = modificationJustification;
    this.deliverables = deliverables;
  }

  public Date getActiveSince() {
    return activeSince;
  }

  public Set<CenterDeliverableType> getCenterDeliverableTypes() {
    return centerDeliverableTypes;
  }

  public User getCreatedBy() {
    return createdBy;
  }

  public Set<CenterDeliverable> getDeliverables() {
    return deliverables;
  }

  public CenterDeliverableType getDeliverableType() {
    return deliverableType;
  }


  public String getDescription() {
    return description;
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

  public void setCenterDeliverableTypes(Set<CenterDeliverableType> centerDeliverableTypes) {
    this.centerDeliverableTypes = centerDeliverableTypes;
  }

  public void setCreatedBy(User createdBy) {
    this.createdBy = createdBy;
  }

  public void setDeliverables(Set<CenterDeliverable> deliverables) {
    this.deliverables = deliverables;
  }

  public void setDeliverableType(CenterDeliverableType deliverableType) {
    this.deliverableType = deliverableType;
  }

  public void setDescription(String description) {
    this.description = description;
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


  @Override
  public String toString() {
    return "CenterDeliverableType [id=" + id + ", name=" + name + "]";
  }


}

