package org.cgiar.ccafs.marlo.data.model;
// Generated Feb 5, 2018 11:32:15 AM by Hibernate Tools 3.4.0.CR1


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.Date;

import com.google.gson.annotations.Expose;

/**
 * PowbEvidence generated by hbm2java
 */
public class PowbFinancialExpenditure implements java.io.Serializable, IAuditLog {


  private static final long serialVersionUID = -8817130891128431256L;


  @Expose
  private Long id;


  @Expose
  private PowbSynthesis powbSynthesis;


  @Expose
  private PowbExpenditureAreas powbExpenditureArea;


  @Expose
  private Double w1w2Percentage;

  @Expose
  private String comments;


  @Expose
  private boolean active;


  @Expose
  private Date activeSince;

  @Expose
  private User createdBy;

  @Expose
  private User modifiedBy;

  @Expose
  private String modificationJustification;

  public PowbFinancialExpenditure() {
  }

  public Date getActiveSince() {
    return activeSince;
  }


  public String getComments() {
    return comments;
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


  public PowbExpenditureAreas getPowbExpenditureArea() {
    return powbExpenditureArea;
  }

  public PowbSynthesis getPowbSynthesis() {
    return powbSynthesis;
  }

  public Double getW1w2Percentage() {
    return w1w2Percentage;
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


  public void setComments(String comments) {
    this.comments = comments;
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

  public void setPowbExpenditureArea(PowbExpenditureAreas powbExpenditureArea) {
    this.powbExpenditureArea = powbExpenditureArea;
  }

  public void setPowbSynthesis(PowbSynthesis powbSynthesis) {
    this.powbSynthesis = powbSynthesis;
  }


  public void setW1w2Percentage(Double w1w2Percentage) {
    this.w1w2Percentage = w1w2Percentage;
  }

  @Override
  public String toString() {
    return "PowbFinancialExpenditure [id=" + id + ", powbSynthesis=" + powbSynthesis + ", powbExpenditureArea="
      + powbExpenditureArea + ", w1w2Percentage=" + w1w2Percentage + ", comments=" + comments + "]";
  }

}

