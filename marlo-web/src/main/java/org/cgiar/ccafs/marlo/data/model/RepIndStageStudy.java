package org.cgiar.ccafs.marlo.data.model;
// Generated Apr 30, 2018 10:29:46 AM by Hibernate Tools 3.4.0.CR1

import com.google.gson.annotations.Expose;

/**
 * RepIndStageStudy generated by hbm2java
 */
public class RepIndStageStudy implements java.io.Serializable {

  private static final long serialVersionUID = -3600242932024372635L;

  @Expose
  private Long id;

  @Expose
  private String name;

  @Expose
  private String description;


  public RepIndStageStudy() {
  }


  public String getDescription() {
    return description;
  }


  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }


}
