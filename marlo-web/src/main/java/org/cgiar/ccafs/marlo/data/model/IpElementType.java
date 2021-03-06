package org.cgiar.ccafs.marlo.data.model;
// Generated Jan 3, 2017 1:26:41 PM by Hibernate Tools 4.3.1.Final


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.Expose;

/**
 * IpElementTypes generated by hbm2java
 */
public class IpElementType implements java.io.Serializable, IAuditLog {


  /**
   * 
   */
  private static final long serialVersionUID = -8195824815754607450L;
  @Expose
  private Long id;
  @Expose
  private String name;

  private Set<IpElement> ipElements = new HashSet<IpElement>(0);

  public IpElementType() {
  }

  public IpElementType(Long id) {
    super();
    this.id = id;
  }


  public IpElementType(String name, Set<IpElement> ipElementses) {
    this.name = name;
    this.ipElements = ipElementses;
  }

  @Override
  public Long getId() {
    return this.id;
  }

  public Set<IpElement> getIpElements() {
    return this.ipElements;
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

  public String getName() {
    return this.name;
  }

  @Override
  public boolean isActive() {

    return true;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setIpElements(Set<IpElement> ipElementses) {
    this.ipElements = ipElementses;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "IpElementType [id=" + id + ", name=" + name + "]";
  }


}

