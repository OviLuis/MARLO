package org.cgiar.ccafs.marlo.data.model;
// Generated Sep 8, 2017 2:12:52 PM by Hibernate Tools 5.2.5.Final


/**
 * PlasAgreement generated by hbm2java
 */
public class PlaAgreement implements java.io.Serializable {


  private Long id;
  private Agreement agreements;
  private String plaId;
  private String description;
  private Double ammount;

  public PlaAgreement() {
  }


  public PlaAgreement(Agreement agreements, String plaId, String description) {
    this.agreements = agreements;
    this.plaId = plaId;
    this.description = description;
  }

  public PlaAgreement(Agreement agreements, String plaId, String description, Double ammount) {
    this.agreements = agreements;
    this.plaId = plaId;
    this.description = description;
    this.ammount = ammount;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Agreement getAgreements() {
    return this.agreements;
  }

  public void setAgreements(Agreement agreements) {
    this.agreements = agreements;
  }

  public String getPlaId() {
    return this.plaId;
  }

  public void setPlaId(String plaId) {
    this.plaId = plaId;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Double getAmmount() {
    return this.ammount;
  }

  public void setAmmount(Double ammount) {
    this.ammount = ammount;
  }


}

