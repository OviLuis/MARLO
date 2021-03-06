package org.cgiar.ccafs.marlo.data.model;

import org.cgiar.ccafs.marlo.data.IAuditLog;

import com.google.gson.annotations.Expose;

// Generated Jan 6, 2017 8:46:55 AM by Hibernate Tools 4.3.1.Final


/**
 * FundingSourceInstitutions generated by hbm2java
 */
public class FundingSourceInstitution implements java.io.Serializable, IAuditLog {


  /**
   * 
   */
  private static final long serialVersionUID = -3682242088869157772L;

  @Expose
  private Long id;

  private FundingSource fundingSource;

  @Expose
  private Institution institution;

  @Expose
  private Phase phase;


  public FundingSourceInstitution() {
  }


  public FundingSourceInstitution(FundingSource fundingSource, Institution institution) {
    this.fundingSource = fundingSource;
    this.institution = institution;
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
    FundingSourceInstitution other = (FundingSourceInstitution) obj;
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!id.equals(other.id)) {
      return false;
    }
    return true;
  }


  public FundingSource getFundingSource() {
    return this.fundingSource;
  }


  @Override
  public Long getId() {
    return this.id;
  }

  public Institution getInstitution() {
    return this.institution;
  }

  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();
    sb.append("Id : ").append(this.getId());
    return sb.toString();
  }

  @Override
  public String getModificationJustification() {
    // TODO Auto-generated method stub
    return "";
  }

  @Override
  public User getModifiedBy() {
    User u = new User();
    u.setId(new Long(3));
    return u;
  }

  public Phase getPhase() {
    return phase;
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

    return true;
  }

  public void setFundingSource(FundingSource fundingSource) {
    this.fundingSource = fundingSource;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setInstitution(Institution institution) {
    this.institution = institution;
  }

  public void setPhase(Phase phase) {
    this.phase = phase;
  }

  @Override
  public String toString() {
    return "FundingSourceInstitution [id=" + id + ", fundingSource=" + fundingSource + ", institution=" + institution
      + "]";
  }


}

