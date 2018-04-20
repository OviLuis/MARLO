package org.cgiar.ccafs.marlo.data.model;
// Generated Jun 27, 2017 2:55:00 PM by Hibernate Tools 4.3.1.Final


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.google.gson.annotations.Expose;

/**
 * ProjectInfo generated by hbm2java
 */
public class ProjectInfo extends MarloAuditableEntity implements java.io.Serializable, IAuditLog {

  private static final long serialVersionUID = -3820243690705823369L;
  @Expose
  private LiaisonInstitution liaisonInstitution;
  @Expose
  private LiaisonUser liaisonUser;
  @Expose
  private Phase phase;
  private Project project;
  @Expose
  private String title;
  @Expose
  private String summary;
  @Expose
  private Date startDate;
  @Expose
  private Date endDate;
  @Expose
  private String type;
  @Expose
  private boolean cofinancing;
  @Expose
  private String leaderResponsabilities;
  @Expose
  private Boolean projectEditLeader;
  @Expose
  private int scale;
  @Expose
  private Boolean noRegional;
  @Expose
  private Date presetDate;
  @Expose
  private Boolean locationGlobal;
  @Expose
  private Long status;
  private String statusName;

  @Expose
  private String statusJustification;
  @Expose
  private String genderAnalysis;
  @Expose
  private Boolean crossCuttingGender;
  @Expose
  private Boolean crossCuttingYouth;
  @Expose
  private Boolean crossCuttingCapacity;
  @Expose
  private Boolean crossCuttingNa;
  @Expose
  private String dimension;
  @Expose
  private Boolean administrative;
  @Expose
  private Boolean reporting;
  @Expose
  private Boolean locationRegional;
  @Expose
  private String newPartnershipsPlanned;

  public ProjectInfo() {
  }

  public Boolean getAdministrative() {
    return administrative;
  }

  public List<Integer> getAllYears() {
    List<Integer> allYears = new ArrayList<>();
    if (startDate != null && endDate != null) {
      Calendar calendarStart = Calendar.getInstance();
      calendarStart.setTime(startDate);
      Calendar calendarEnd = Calendar.getInstance();
      calendarEnd.setTime(endDate);

      while (calendarStart.get(Calendar.YEAR) <= calendarEnd.get(Calendar.YEAR)) {
        // Adding the year to the list.

        allYears.add(calendarStart.get(Calendar.YEAR));


        // Adding a year (365 days) to the start date.
        calendarStart.add(Calendar.YEAR, 1);
      }
    }

    return allYears;
  }

  /**
   * This method calculates all the years between the start date and the end date.
   * 
   * @return a List of numbers representing all the years, or an empty list if nothing found.
   */
  public List<Integer> getAllYearsPhase() {
    List<Integer> allYears = new ArrayList<>();
    if (startDate != null && endDate != null) {
      Calendar calendarStart = Calendar.getInstance();
      calendarStart.setTime(startDate);
      Calendar calendarEnd = Calendar.getInstance();
      calendarEnd.setTime(endDate);

      while (calendarStart.get(Calendar.YEAR) <= calendarEnd.get(Calendar.YEAR)) {
        // Adding the year to the list.
        if (calendarStart.get(Calendar.YEAR) >= phase.getYear()) {
          allYears.add(calendarStart.get(Calendar.YEAR));
        }


        // Adding a year (365 days) to the start date.
        calendarStart.add(Calendar.YEAR, 1);
      }
    }

    return allYears;
  }


  public Boolean getCrossCuttingCapacity() {
    return crossCuttingCapacity;
  }

  public Boolean getCrossCuttingGender() {
    return crossCuttingGender;
  }

  public Boolean getCrossCuttingNa() {
    return crossCuttingNa;
  }


  public Boolean getCrossCuttingYouth() {
    return crossCuttingYouth;
  }

  public String getDimension() {
    return dimension;
  }

  public Date getEndDate() {
    return endDate;
  }


  public String getGenderAnalysis() {
    return genderAnalysis;
  }

  public String getLeaderResponsabilities() {
    return leaderResponsabilities;
  }


  public LiaisonInstitution getLiaisonInstitution() {
    return liaisonInstitution;
  }

  public LiaisonUser getLiaisonUser() {
    return liaisonUser;
  }


  public Boolean getLocationGlobal() {
    return locationGlobal;
  }


  public Boolean getLocationRegional() {
    return locationRegional;
  }


  @Override
  public String getLogDeatil() {

    return null;
  }

  public String getNewPartnershipsPlanned() {
    return newPartnershipsPlanned;
  }


  public Boolean getNoRegional() {
    return noRegional;
  }


  public Phase getPhase() {
    return phase;
  }


  public Date getPresetDate() {
    return presetDate;
  }


  public Project getProject() {
    return project;
  }


  public Boolean getProjectEditLeader() {
    return projectEditLeader;
  }


  public Boolean getReporting() {
    return reporting;
  }


  public int getScale() {
    return scale;
  }


  public Date getStartDate() {
    return startDate;
  }


  public Long getStatus() {
    return status;
  }


  public String getStatusJustification() {
    return statusJustification;
  }


  public Boolean getStatusJustificationRequired() {
    if (status != null) {
      // Alow for comments when the project is extended, cancelled or complete;
      if ((this.status == Long.parseLong(ProjectStatusEnum.Extended.getStatusId()))
        || (this.status == Long.parseLong(ProjectStatusEnum.Cancelled.getStatusId()))
        || (this.status == Long.parseLong(ProjectStatusEnum.Complete.getStatusId()))) {
        return true;
      }
    }

    return false;
  }


  public String getStatusName() {

    if (statusName != null) {
      return statusName;
    }
    if (status != null) {
      return ProjectStatusEnum.getValue(status.intValue()).getStatus();
    }
    return "";
  }

  public String getSummary() {
    return summary;
  }


  public String getTitle() {
    return title;
  }

  public String getType() {
    return type;
  }


  public List<Integer> getYears(int year) {
    List<Integer> allYears = new ArrayList<>();
    if (startDate != null && endDate != null) {

      Calendar calendarEnd = Calendar.getInstance();
      calendarEnd.setTime(endDate);

      while (year < calendarEnd.get(Calendar.YEAR)) {
        year++;
        // Adding the year to the list.
        allYears.add(year);
        // Adding a year (365 days) to the start date.

      }
    }

    return allYears;
  }


  @Override
  public boolean isActive() {
    return true;
  }


  public boolean isCofinancing() {
    return cofinancing;
  }


  public Boolean isProjectEditLeader() {
    return projectEditLeader;
  }


  public void setAdministrative(Boolean administrative) {
    this.administrative = administrative;
  }


  public void setCofinancing(boolean cofinancing) {
    this.cofinancing = cofinancing;
  }


  public void setCrossCuttingCapacity(Boolean crossCuttingCapacity) {
    this.crossCuttingCapacity = crossCuttingCapacity;
  }


  public void setCrossCuttingGender(Boolean crossCuttingGender) {
    this.crossCuttingGender = crossCuttingGender;
  }


  public void setCrossCuttingNa(Boolean crossCuttingNa) {
    this.crossCuttingNa = crossCuttingNa;
  }


  public void setCrossCuttingYouth(Boolean crossCuttingYouth) {
    this.crossCuttingYouth = crossCuttingYouth;
  }


  public void setDimension(String dimension) {
    this.dimension = dimension;
  }


  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }


  public void setGenderAnalysis(String genderAnalysis) {
    this.genderAnalysis = genderAnalysis;
  }


  public void setLeaderResponsabilities(String leaderResponsabilities) {
    this.leaderResponsabilities = leaderResponsabilities;
  }


  public void setLiaisonInstitution(LiaisonInstitution liaisonInstitution) {
    this.liaisonInstitution = liaisonInstitution;
  }


  public void setLiaisonUser(LiaisonUser liaisonUser) {
    this.liaisonUser = liaisonUser;
  }


  public void setLocationGlobal(Boolean locationGlobal) {
    this.locationGlobal = locationGlobal;
  }


  public void setLocationRegional(Boolean locationRegional) {
    this.locationRegional = locationRegional;
  }

  public void setNewPartnershipsPlanned(String newPartnershipsPlanned) {
    this.newPartnershipsPlanned = newPartnershipsPlanned;
  }


  public void setNoRegional(Boolean noRegional) {
    this.noRegional = noRegional;
  }


  public void setPhase(Phase phase) {
    this.phase = phase;
  }


  public void setPresetDate(Date presetDate) {
    this.presetDate = presetDate;
  }


  public void setProject(Project project) {
    this.project = project;
  }


  public void setProjectEditLeader(Boolean projectEditLeader) {
    this.projectEditLeader = projectEditLeader;
  }


  public void setReporting(Boolean reporting) {
    this.reporting = reporting;
  }


  public void setScale(int scale) {
    this.scale = scale;
  }


  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }


  public void setStatus(Long status) {
    this.status = status;
  }


  public void setStatusJustification(String statusJustification) {
    this.statusJustification = statusJustification;
  }


  public void setStatusName(String statusName) {
    this.statusName = statusName;

  }


  public void setSummary(String summary) {
    this.summary = summary;
  }


  public void setTitle(String title) {
    this.title = title;
  }


  public void setType(String type) {
    this.type = type;
  }


  public void updateProjectInfo(ProjectInfo update) {
    this.setAdministrative(update.getAdministrative());
    this.setCofinancing(update.isCofinancing());
    this.setCrossCuttingCapacity(update.getCrossCuttingCapacity());
    this.setCrossCuttingGender(update.getCrossCuttingGender());
    this.setCrossCuttingNa(update.getCrossCuttingNa());
    this.setCrossCuttingYouth(update.getCrossCuttingYouth());
    this.setDimension(update.getDimension());
    this.setEndDate(update.getEndDate());
    this.setGenderAnalysis(update.getGenderAnalysis());
    this.setLeaderResponsabilities(update.getLeaderResponsabilities());
    this.setLiaisonInstitution(update.getLiaisonInstitution());
    this.setLiaisonUser(update.getLiaisonUser());
    this.setLocationGlobal(update.getLocationGlobal());
    this.setNoRegional(update.getNoRegional());
    this.setPresetDate(update.getPresetDate());
    this.setReporting(update.getReporting());
    this.setScale(update.getScale());
    this.setStartDate(update.getStartDate());
    this.setStatus(update.getStatus());
    this.setStatusJustification(update.getStatusJustification());
    this.setSummary(update.getSummary());
    this.setTitle(update.getTitle());
    this.setType(update.getType());
    this.setNewPartnershipsPlanned(update.getNewPartnershipsPlanned());
  }


}

