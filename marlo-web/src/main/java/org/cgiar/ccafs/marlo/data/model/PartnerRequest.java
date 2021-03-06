package org.cgiar.ccafs.marlo.data.model;

import java.util.Date;

import com.google.gson.annotations.Expose;

// Generated Apr 18, 2017 3:48:50 PM by Hibernate Tools 3.4.0.CR1


/**
 * PartnerRequest generated by hbm2java
 */
/**
 * PartnerRequest: Used to store information of institutions and officeLocations requests
 * 
 * @author avalencia - CCAFS
 * @date Oct 30, 2017
 * @time 11:01:07 AM: Added Crp
 * @time 2:44:06 PM: Added Self-Relationship to identify modified data
 * @time 4:24:09 PM: Added modified boolean variable
 * @date Oct 31, 2017
 * @time 9:56:44 AM: Added acepted and rejected date
 */
public class PartnerRequest implements java.io.Serializable {


  private static final long serialVersionUID = 6550441704130583208L;


  @Expose
  private Long id;

  @Expose
  private LocElement locElement;


  @Expose
  private User modifiedBy;


  @Expose
  private User createdBy;


  @Expose
  private User rejectedBy;

  @Expose
  private InstitutionType institutionType;


  @Expose
  private String partnerName;

  @Expose
  private String acronym;


  @Expose
  private String webPage;


  @Expose
  private Boolean acepted;

  @Expose
  private boolean active;

  @Expose
  private String modificationJustification;

  @Expose
  private String rejectJustification;

  @Expose
  private Date activeSince;

  @Expose
  private boolean office;

  @Expose
  private Institution institution;


  @Expose
  private String requestSource;

  @Expose
  private GlobalUnit crp;

  @Expose
  private PartnerRequest partnerRequest;

  @Expose
  private Boolean modified;

  @Expose
  private Date aceptedDate;

  @Expose
  private Date rejectedDate;

  public PartnerRequest() {
  }

  public PartnerRequest(boolean active) {
    this.active = active;
  }

  public PartnerRequest(LocElement locElement, User modifiedBy, User createdBy, InstitutionType institutionType,
    String partnerName, String acronym, String webPage, Boolean acepted, boolean active,
    String modificationJustification) {
    this.locElement = locElement;
    this.modifiedBy = modifiedBy;
    this.createdBy = createdBy;
    this.institutionType = institutionType;
    this.partnerName = partnerName;
    this.acronym = acronym;
    this.webPage = webPage;
    this.acepted = acepted;
    this.active = active;
    this.modificationJustification = modificationJustification;
  }

  public PartnerRequest(PartnerRequest partnerRequest) {
    this.partnerRequest = partnerRequest;
  }

  public Boolean getAcepted() {
    return acepted;
  }

  public Date getAceptedDate() {
    return aceptedDate;
  }

  public String getAcronym() {
    return acronym;
  }

  public Date getActiveSince() {
    return activeSince;
  }

  public String getCountryInfo() {
    return this.locElement.getName();
  }


  public String getCountryISO() {
    return this.locElement.getIsoAlpha2();
  }

  public User getCreatedBy() {
    return createdBy;
  }


  public GlobalUnit getCrp() {
    return crp;
  }


  public Long getId() {
    return id;
  }


  public Institution getInstitution() {
    return institution;
  }


  public InstitutionType getInstitutionType() {
    return institutionType;
  }


  public LocElement getLocElement() {
    return locElement;
  }

  public String getModificationJustification() {
    return modificationJustification;
  }


  public Boolean getModified() {
    return modified;
  }


  public User getModifiedBy() {
    return modifiedBy;
  }


  public String getPartnerInfo() {
    if (this.acronym != null && !this.acronym.isEmpty()) {
      return this.acronym + " - " + this.partnerName;
    }
    return this.partnerName;
  }


  public String getPartnerName() {
    return partnerName;
  }


  public PartnerRequest getPartnerRequest() {
    return partnerRequest;
  }


  public User getRejectedBy() {
    return rejectedBy;
  }

  public Date getRejectedDate() {
    return rejectedDate;
  }

  public String getRejectJustification() {
    return rejectJustification;
  }

  public String getRequestSource() {
    return requestSource;
  }


  public String getWebPage() {
    return webPage;
  }

  public boolean isActive() {
    return active;
  }

  public boolean isOffice() {
    return office;
  }

  public void setAcepted(Boolean acepted) {
    this.acepted = acepted;
  }

  public void setAceptedDate(Date aceptedDate) {
    this.aceptedDate = aceptedDate;
  }

  public void setAcronym(String acronym) {
    this.acronym = acronym;
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


  public void setCrp(GlobalUnit crp) {
    this.crp = crp;
  }


  public void setId(Long id) {
    this.id = id;
  }

  public void setInstitution(Institution institution) {
    this.institution = institution;
  }

  public void setInstitutionType(InstitutionType institutionType) {
    this.institutionType = institutionType;
  }

  public void setLocElement(LocElement locElement) {
    this.locElement = locElement;
  }


  public void setModificationJustification(String modificationJustification) {
    this.modificationJustification = modificationJustification;
  }


  public void setModified(Boolean modified) {
    this.modified = modified;
  }

  public void setModifiedBy(User modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  public void setOffice(boolean office) {
    this.office = office;
  }

  public void setPartnerName(String partnerName) {
    this.partnerName = partnerName;
  }

  public void setPartnerRequest(PartnerRequest partnerRequest) {
    this.partnerRequest = partnerRequest;
  }

  public void setRejectedBy(User rejectedBy) {
    this.rejectedBy = rejectedBy;
  }


  public void setRejectedDate(Date rejectedDate) {
    this.rejectedDate = rejectedDate;
  }

  public void setRejectJustification(String rejectJustification) {
    this.rejectJustification = rejectJustification;
  }

  public void setRequestSource(String requestSource) {
    this.requestSource = requestSource;
  }

  public void setWebPage(String webPage) {
    this.webPage = webPage;
  }


}

