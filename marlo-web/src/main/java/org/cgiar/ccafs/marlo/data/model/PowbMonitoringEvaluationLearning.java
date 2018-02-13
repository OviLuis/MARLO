package org.cgiar.ccafs.marlo.data.model;
// Generated Feb 9, 2018 11:37:50 AM by Hibernate Tools 3.4.0.CR1


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.annotations.Expose;

/**
 * PowbMonitoringEvaluationLearning generated by hbm2java
 */
public class PowbMonitoringEvaluationLearning implements java.io.Serializable, IAuditLog {


  private static final long serialVersionUID = 6681061181269663002L;


  @Expose
  private Long id;


  @Expose
  private User modifiedBy;

  private PowbSynthesis powbSynthesis;

  @Expose
  private User createdBy;


  @Expose
  private String highlight;

  @Expose
  private boolean active;

  @Expose
  private Date activeSince;

  @Expose
  private String modificationJustification;

  private Set<PowbMonitoringEvaluationLearningExercise> powbMonitoringEvaluationLearningExercises =
    new HashSet<PowbMonitoringEvaluationLearningExercise>(0);

  private List<PowbMonitoringEvaluationLearningExercise> exercises;


  public PowbMonitoringEvaluationLearning() {
  }


  public Date getActiveSince() {
    return activeSince;
  }


  public User getCreatedBy() {
    return createdBy;
  }


  public List<PowbMonitoringEvaluationLearningExercise> getExercises() {
    return exercises;
  }


  public String getHighlight() {
    return highlight;
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


  public Set<PowbMonitoringEvaluationLearningExercise> getPowbMonitoringEvaluationLearningExercises() {
    return powbMonitoringEvaluationLearningExercises;
  }

  public PowbSynthesis getPowbSynthesis() {
    return powbSynthesis;
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

  public void setExercises(List<PowbMonitoringEvaluationLearningExercise> exercises) {
    this.exercises = exercises;
  }

  public void setHighlight(String highlight) {
    this.highlight = highlight;
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


  public void setPowbMonitoringEvaluationLearningExercises(
    Set<PowbMonitoringEvaluationLearningExercise> powbMonitoringEvaluationLearningExercises) {
    this.powbMonitoringEvaluationLearningExercises = powbMonitoringEvaluationLearningExercises;
  }


  public void setPowbSynthesis(PowbSynthesis powbSynthesis) {
    this.powbSynthesis = powbSynthesis;
  }


}
