package org.cgiar.ccafs.marlo.data.model;
// Generated Apr 30, 2018 10:29:46 AM by Hibernate Tools 3.4.0.CR1


import com.google.gson.annotations.Expose;

/**
 * StudyType generated by hbm2java
 */
public class StudyType implements java.io.Serializable {

  private static final long serialVersionUID = 5404311195258996896L;


  @Expose
  private Long id;


  @Expose
  private String name;


  public StudyType() {
  }


  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }


}
