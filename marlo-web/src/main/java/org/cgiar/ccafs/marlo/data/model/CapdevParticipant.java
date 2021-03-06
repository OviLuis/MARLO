package org.cgiar.ccafs.marlo.data.model;
// default package
// Generated Jun 9, 2017 3:40:30 PM by Hibernate Tools 3.4.0.CR1


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.Date;

import com.google.gson.annotations.Expose;

/**
 * CapdevParticipant generated by hbm2java
 */
public class CapdevParticipant implements java.io.Serializable, IAuditLog {


  /**
   * 
   */

  private static final long serialVersionUID = 1L;

  @Expose
  private Long id;

  @Expose
  private Participant participant;

  @Expose
  private User modifiedBy;

  @Expose
  private User createdBy;

  @Expose
  private CapacityDevelopment capacityDevelopment;

  @Expose
  private boolean active;

  @Expose
  private Date activeSince;

  @Expose
  private String modificationJustification;


  public CapdevParticipant() {
  }


  public CapdevParticipant(Participant participant, User modifiedBy, User createdBy,
    CapacityDevelopment capacityDevelopment, boolean active, Date activeSince, String modificationJustification) {
    this.participant = participant;
    this.modifiedBy = modifiedBy;
    this.createdBy = createdBy;
    this.capacityDevelopment = capacityDevelopment;
    this.active = active;
    this.activeSince = activeSince;
    this.modificationJustification = modificationJustification;
  }

  public CapdevParticipant(User createdBy, boolean active) {
    this.createdBy = createdBy;
    this.active = active;
  }

  public Date getActiveSince() {
    return this.activeSince;
  }

  public CapacityDevelopment getCapacityDevelopment() {
    return this.capacityDevelopment;
  }

  public User getCreatedBy() {
    return createdBy;
  }

  @Override
  public Long getId() {
    return this.id;
  }

  @Override
  public String getLogDeatil() {
    final StringBuilder sb = new StringBuilder();
    sb.append("Id : ").append(this.getId());
    return sb.toString();
  }


  @Override
  public String getModificationJustification() {
    return this.modificationJustification;
  }

  @Override
  public User getModifiedBy() {
    return modifiedBy;
  }

  public Participant getParticipant() {
    return this.participant;
  }

  @Override
  public boolean isActive() {
    return this.active;
  }

  public void setActive(boolean isActive) {
    this.active = isActive;
  }

  public void setActiveSince(Date activeSince) {
    this.activeSince = activeSince;
  }

  public void setCapacityDevelopment(CapacityDevelopment capacityDevelopment) {
    this.capacityDevelopment = capacityDevelopment;
  }


  public void setCreatedBy(User createdBy) {
    this.createdBy = createdBy;
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


  public void setParticipant(Participant participant) {
    this.participant = participant;
  }


}

