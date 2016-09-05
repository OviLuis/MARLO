package org.cgiar.ccafs.marlo.data.model;
// Generated Sep 5, 2016 8:28:28 AM by Hibernate Tools 4.3.1.Final


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.Date;

import com.google.gson.annotations.Expose;

/**
 * ProjectBudget generated by hbm2java
 */
public class ProjectBudget implements java.io.Serializable, IAuditLog {


  /**
   * 
   */
  private static final long serialVersionUID = -6117852720583204865L;
  @Expose
  private Long id;
  @Expose
  private Institution institution;
  @Expose
  private Project projectCofinance;
  @Expose
  private Project project;
  @Expose
  private User createdBy;
  @Expose
  private User modifiedBy;
  @Expose
  private Long amount;
  @Expose
  private long budgetType;
  @Expose
  private int year;
  @Expose
  private Double genderPercentage;
  @Expose
  private boolean active;
  @Expose
  private Date activeSince;
  @Expose
  private String modificationJustification;

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
    ProjectBudget other = (ProjectBudget) obj;
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

  public Long getAmount() {
    return amount;
  }

  public long getBudgetType() {
    return budgetType;
  }

  public User getCreatedBy() {
    return createdBy;
  }

  public Double getGenderPercentage() {
    return genderPercentage;
  }

  @Override
  public Long getId() {
    return id;
  }

  public Institution getInstitution() {
    return institution;
  }

  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();
    sb.append("Id : ").append(this.getId());
    return sb.toString();
  }

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

  public Project getProjectCofinance() {
    return projectCofinance;
  }

  public int getYear() {
    return year;
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

  public void setAmount(Long amount) {
    this.amount = amount;
  }

  public void setBudgetType(long budgetType) {
    this.budgetType = budgetType;
  }

  public void setCreatedBy(User createdBy) {
    this.createdBy = createdBy;
  }

  public void setGenderPercentage(Double genderPercentage) {
    this.genderPercentage = genderPercentage;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setInstitution(Institution institution) {
    this.institution = institution;
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

  public void setProjectCofinance(Project projectCofinance) {
    this.projectCofinance = projectCofinance;
  }

  public void setYear(int year) {
    this.year = year;
  }


}

