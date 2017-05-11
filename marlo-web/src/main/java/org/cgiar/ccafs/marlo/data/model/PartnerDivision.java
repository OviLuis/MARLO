package org.cgiar.ccafs.marlo.data.model;
// Generated Apr 12, 2017 3:46:19 PM by Hibernate Tools 3.4.0.CR1


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.Expose;

/**
 * PartnerDivision generated by hbm2java
 */
public class PartnerDivision implements java.io.Serializable, IAuditLog {


  private static final long serialVersionUID = -1853814625671155813L;


  @Expose
  private Long id;


  @Expose
  private User modifiedBy;

  @Expose
  private User createdBy;

  @Expose
  private String acronym;

  @Expose
  private String name;

  @Expose
  private boolean active;

  @Expose
  private Date activeSince;

  @Expose
  private String modificationJustification;

  private Set<DeliverablePartnership> deliverablePartnerships = new HashSet<DeliverablePartnership>(0);

  public PartnerDivision() {
  }


  public PartnerDivision(boolean active) {
    this.active = active;
  }


  public PartnerDivision(User modifiedBy, User createdBy, String acronym, String name, boolean active, Date activeSince,
    String modificationJustification, Set<DeliverablePartnership> deliverablePartnerships) {
    this.modifiedBy = modifiedBy;
    this.createdBy = createdBy;
    this.acronym = acronym;
    this.name = name;
    this.active = active;
    this.activeSince = activeSince;
    this.modificationJustification = modificationJustification;
    this.deliverablePartnerships = deliverablePartnerships;
  }


  public String getAcronym() {
    return acronym;
  }


  public Date getActiveSince() {
    return activeSince;
  }


  public String getComposedName() {

    if (this.getAcronym() != null) {
      if (this.getAcronym().length() != 0) {
        try {
          return this.getAcronym() + " - " + this.getName();
        } catch (Exception e) {
          return this.getName();
        }
      }
    }
    return this.getName();
  }


  public User getCreatedBy() {
    return createdBy;
  }

  public Set<DeliverablePartnership> getDeliverablePartnerships() {
    return deliverablePartnerships;
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

  public void setAcronym(String acronym) {
    this.acronym = acronym;
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

  public void setDeliverablePartnerships(Set<DeliverablePartnership> deliverablePartnerships) {
    this.deliverablePartnerships = deliverablePartnerships;
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

}

