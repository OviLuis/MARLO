package org.cgiar.ccafs.marlo.data.model;
// Generated Feb 28, 2017 11:23:33 AM by Hibernate Tools 3.4.0.CR1


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.Expose;

/**
 * CenterFundingSourceType generated by hbm2java
 */
public class CenterFundingSourceType implements java.io.Serializable, IAuditLog {


  private static final long serialVersionUID = 3947855061444655147L;


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

  private Set<CenterProjectFundingSource> projectFundingSources = new HashSet<CenterProjectFundingSource>(0);

  public CenterFundingSourceType() {
  }


  public CenterFundingSourceType(boolean active) {
    this.active = active;
  }


  public CenterFundingSourceType(User modifiedBy, User createdBy, String name, boolean active, Date activeSince,
    String modificationJustification, Set<CenterProjectFundingSource> projectFundingSources) {
    this.modifiedBy = modifiedBy;
    this.createdBy = createdBy;
    this.name = name;
    this.active = active;
    this.activeSince = activeSince;
    this.modificationJustification = modificationJustification;
    this.projectFundingSources = projectFundingSources;
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
    CenterFundingSourceType other = (CenterFundingSourceType) obj;
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

  public Set<CenterProjectFundingSource> getProjectFundingSources() {
    return projectFundingSources;
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

  public void setProjectFundingSources(Set<CenterProjectFundingSource> projectFundingSources) {
    this.projectFundingSources = projectFundingSources;
  }


  @Override
  public String toString() {
    return "CenterFundingSourceType [id=" + id + ", name=" + name + "]";
  }

}

