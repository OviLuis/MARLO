package org.cgiar.ccafs.marlo.data.model;
// Generated Jan 10, 2017 9:14:52 AM by Hibernate Tools 3.4.0.CR1


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.Date;

import com.google.gson.annotations.Expose;

/**
 * DeliverableQualityCheck generated by hbm2java
 */
public class DeliverableQualityCheck implements java.io.Serializable, IAuditLog {


  private static final long serialVersionUID = 436392743366274799L;

  @Expose
  private Long id;

  @Expose
  private User modifiedBy;

  @Expose
  private User createdBy;
  @Expose
  private Phase phase;
  @Expose
  private DeliverableQualityAnswer dataDictionary;
  @Expose
  private Deliverable deliverable;

  @Expose
  private DeliverableQualityAnswer dataTools;

  @Expose
  private DeliverableQualityAnswer qualityAssurance;

  @Expose
  private FileDB fileTools;

  @Expose
  private FileDB fileAssurance;

  @Expose
  private FileDB fileDictionary;

  @Expose
  private String linkAssurance;

  @Expose
  private String linkDictionary;

  @Expose
  private String linkTools;

  @Expose
  private boolean active;

  @Expose

  private Date activeSince;

  private String modificationJustification;

  public DeliverableQualityCheck() {
  }

  public DeliverableQualityCheck(boolean active) {
    this.active = active;
  }

  public DeliverableQualityCheck(User modifiedBy, User createdBy, DeliverableQualityAnswer dataDictionary,
    Deliverable deliverable, DeliverableQualityAnswer dataTools, DeliverableQualityAnswer qualityAssurance,
    boolean active, Date activeSince, String modificationJustification) {
    this.modifiedBy = modifiedBy;
    this.createdBy = createdBy;
    this.dataDictionary = dataDictionary;
    this.deliverable = deliverable;
    this.dataTools = dataTools;
    this.qualityAssurance = qualityAssurance;
    this.active = active;
    this.activeSince = activeSince;
    this.modificationJustification = modificationJustification;
  }

  public Date getActiveSince() {
    return activeSince;
  }

  public User getCreatedBy() {
    return createdBy;
  }


  public DeliverableQualityAnswer getDataDictionary() {
    return dataDictionary;
  }


  public DeliverableQualityAnswer getDataTools() {
    return dataTools;
  }


  public Deliverable getDeliverable() {
    return deliverable;
  }

  public FileDB getFileAssurance() {
    return fileAssurance;
  }

  public FileDB getFileDictionary() {
    return fileDictionary;
  }

  public FileDB getFileTools() {
    return fileTools;
  }


  @Override
  public Long getId() {
    return id;
  }


  public String getLinkAssurance() {
    return linkAssurance;
  }


  public String getLinkDictionary() {
    return linkDictionary;
  }


  public String getLinkTools() {
    return linkTools;
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


  public Phase getPhase() {
    return phase;
  }


  public DeliverableQualityAnswer getQualityAssurance() {
    return qualityAssurance;
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

  public void setDataDictionary(DeliverableQualityAnswer dataDictionary) {
    this.dataDictionary = dataDictionary;
  }

  public void setDataTools(DeliverableQualityAnswer dataTools) {
    this.dataTools = dataTools;
  }

  public void setDeliverable(Deliverable deliverable) {
    this.deliverable = deliverable;
  }

  public void setFileAssurance(FileDB fileAssurance) {
    this.fileAssurance = fileAssurance;
  }

  public void setFileDictionary(FileDB fileDictionary) {
    this.fileDictionary = fileDictionary;
  }

  public void setFileTools(FileDB fileTools) {
    this.fileTools = fileTools;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setLinkAssurance(String linkAssurance) {
    this.linkAssurance = linkAssurance;
  }

  public void setLinkDictionary(String linkDictionary) {
    this.linkDictionary = linkDictionary;
  }

  public void setLinkTools(String linkTools) {
    this.linkTools = linkTools;
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


  public void setQualityAssurance(DeliverableQualityAnswer qualityAssurance) {
    this.qualityAssurance = qualityAssurance;
  }

  @Override
  public String toString() {
    return "DeliverableQualityCheck [id=" + id + ", dataDictionary=" + dataDictionary + ", deliverable=" + deliverable
      + ", qualityAssurance=" + qualityAssurance + ", fileAssurance=" + fileAssurance + ", fileDictionary="
      + fileDictionary + ", linkAssurance=" + linkAssurance + ", linkDictionary=" + linkDictionary + "]";
  }

}

