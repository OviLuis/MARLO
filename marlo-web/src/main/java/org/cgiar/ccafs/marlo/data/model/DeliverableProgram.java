package org.cgiar.ccafs.marlo.data.model;
// Generated Feb 9, 2017 11:49:29 AM by Hibernate Tools 4.3.1.Final

import org.cgiar.ccafs.marlo.data.IAuditLog;

import com.google.gson.annotations.Expose;

/**
 * DeliverablePrograms generated by hbm2java
 */
public class DeliverableProgram implements java.io.Serializable, IAuditLog {


  /**
   * 
   */
  private static final long serialVersionUID = 283477401039556496L;
  @Expose
  private Long id;


  private Deliverable deliverable;


  @Expose
  private Phase phase;
  @Expose
  private IpProgram ipProgram;


  public DeliverableProgram() {
  }

  public DeliverableProgram(Deliverable deliverable, IpProgram ipProgram) {
    this.deliverable = deliverable;
    this.ipProgram = ipProgram;
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
    DeliverableProgram other = (DeliverableProgram) obj;
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!id.equals(other.id)) {
      return false;
    }
    return true;
  }

  public Deliverable getDeliverable() {
    return deliverable;
  }


  @Override
  public Long getId() {
    return id;
  }


  public IpProgram getIpProgram() {
    return ipProgram;
  }

  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();
    sb.append("Id : ").append(this.getId());
    return sb.toString();
  }

  @Override
  public String getModificationJustification() {

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


  public void setDeliverable(Deliverable deliverable) {
    this.deliverable = deliverable;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setIpProgram(IpProgram ipProgram) {
    this.ipProgram = ipProgram;
  }

  public void setPhase(Phase phase) {
    this.phase = phase;
  }

  @Override
  public String toString() {
    return "DeliverableProgram [id=" + id + ", deliverable=" + deliverable + ", ipProgram=" + ipProgram + "]";
  }


}

