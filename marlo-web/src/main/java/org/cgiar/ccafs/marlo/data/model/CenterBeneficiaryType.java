package org.cgiar.ccafs.marlo.data.model;
// Generated Nov 24, 2016 11:37:14 AM by Hibernate Tools 3.4.0.CR1


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.Expose;

/**
 * CenterBeneficiaryType generated by hbm2java
 */
public class CenterBeneficiaryType implements java.io.Serializable, IAuditLog {


  private static final long serialVersionUID = -1358087568675397584L;

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

  private Set<CenterBeneficiary> beneficiaries = new HashSet<CenterBeneficiary>(0);

  public CenterBeneficiaryType() {
  }

  public CenterBeneficiaryType(String name, boolean active) {
    this.name = name;
    this.active = active;
  }


  public CenterBeneficiaryType(User modifiedBy, User createdBy, String name, boolean active, Date activeSince,
    String modificationJustification, Set<CenterBeneficiary> beneficiaries) {
    this.modifiedBy = modifiedBy;
    this.createdBy = createdBy;
    this.name = name;
    this.active = active;
    this.activeSince = activeSince;
    this.modificationJustification = modificationJustification;
    this.beneficiaries = beneficiaries;
  }


  public Date getActiveSince() {
    return activeSince;
  }


  public Set<CenterBeneficiary> getBeneficiaries() {
    return beneficiaries;
  }


  public User getCreatedBy() {
    return createdBy;
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

  public void setBeneficiaries(Set<CenterBeneficiary> beneficiaries) {
    this.beneficiaries = beneficiaries;
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

  public void setName(String name) {
    this.name = name;
  }


  @Override
  public String toString() {
    return "CenterBeneficiaryType [id=" + id + ", name=" + name + "]";
  }

}

