package org.cgiar.ccafs.marlo.data.model;
// Generated Jan 3, 2017 1:26:41 PM by Hibernate Tools 4.3.1.Final


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.Expose;

/**
 * IpPrograms generated by hbm2java
 */
public class IpProgram implements java.io.Serializable, IAuditLog {


  /**
   * 
   */
  private static final long serialVersionUID = -25507491163714552L;


  @Expose
  private Long id;

  @Expose
  private IpProgramType ipProgramType;


  @Expose
  private String name;

  @Expose
  private String acronym;


  @Expose
  private Long regionId;

  @Expose
  private ProjectComponentLesson ProjectComponentLesson;


  private Set<ProjectComponentLesson> projectComponentLessons = new HashSet<ProjectComponentLesson>(0);

  @Expose
  private CrpProgram crpProgram;
  private Set<IpElement> ipElements = new HashSet<IpElement>(0);

  private Set<OutcomeSynthesy> outcomeSynthesis = new HashSet<OutcomeSynthesy>(0);

  private Set<IpProgramElement> ipProgramElements = new HashSet<IpProgramElement>(0);

  private Set<MogSynthesy> mogSynthesis = new HashSet<MogSynthesy>(0);

  private Set<SectionStatus> sectionStatuses = new HashSet<SectionStatus>(0);

  public IpProgram() {
  }

  public IpProgram(IpProgramType ipProgramType) {
    this.ipProgramType = ipProgramType;
  }

  public IpProgram(Long id) {
    super();
    this.id = id;
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
    IpProgram other = (IpProgram) obj;
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!id.equals(other.id)) {
      return false;
    }
    return true;
  }

  public String getAcronym() {
    return acronym;
  }

  public String getComposedName() {
    StringBuilder builder = new StringBuilder();
    if (this.acronym != null) {
      builder.append(acronym);
      builder.append(": ");
    }
    if (this.name != null) {
      builder.append(name);
    }
    return builder.toString();
  }

  public CrpProgram getCrpProgram() {
    return crpProgram;
  }

  @Override
  public Long getId() {
    return id;
  }

  public Set<IpElement> getIpElements() {
    return ipElements;
  }

  public Set<IpProgramElement> getIpProgramElements() {
    return ipProgramElements;
  }

  public IpProgramType getIpProgramType() {
    return ipProgramType;
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

  public Set<MogSynthesy> getMogSynthesis() {
    return mogSynthesis;
  }

  public String getName() {
    return name;
  }

  public Set<OutcomeSynthesy> getOutcomeSynthesis() {
    return outcomeSynthesis;
  }

  public ProjectComponentLesson getProjectComponentLesson() {
    return ProjectComponentLesson;
  }


  public Set<ProjectComponentLesson> getProjectComponentLessons() {
    return projectComponentLessons;
  }


  public Long getRegionId() {
    return regionId;
  }


  public Set<SectionStatus> getSectionStatuses() {
    return sectionStatuses;
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


  public boolean isFlagshipProgram() {
    if (ipProgramType != null) {
      return (this.ipProgramType.getId().intValue() == 4);
    } else {
      return false;
    }
  }


  public boolean isRegionalProgram() {
    if (ipProgramType != null) {
      return (this.ipProgramType.getId().intValue() == 5);
    } else {
      return false;
    }
  }


  public void setAcronym(String acronym) {
    this.acronym = acronym;
  }


  public void setCrpProgram(CrpProgram crpProgram) {
    this.crpProgram = crpProgram;
  }


  public void setId(Long id) {
    this.id = id;
  }


  public void setIpElements(Set<IpElement> ipElements) {
    this.ipElements = ipElements;
  }


  public void setIpProgramElements(Set<IpProgramElement> ipProgramElements) {
    this.ipProgramElements = ipProgramElements;
  }


  public void setIpProgramType(IpProgramType ipProgramType) {
    this.ipProgramType = ipProgramType;
  }


  public void setMogSynthesis(Set<MogSynthesy> mogSynthesises) {
    this.mogSynthesis = mogSynthesises;
  }


  public void setName(String name) {
    this.name = name;
  }


  public void setOutcomeSynthesis(Set<OutcomeSynthesy> outcomeSynthesis) {
    this.outcomeSynthesis = outcomeSynthesis;
  }


  public void setProjectComponentLesson(ProjectComponentLesson projectComponentLesson) {
    ProjectComponentLesson = projectComponentLesson;
  }


  public void setProjectComponentLessons(Set<ProjectComponentLesson> projectComponentLessons) {
    this.projectComponentLessons = projectComponentLessons;
  }


  public void setRegionId(Long regionId) {
    this.regionId = regionId;
  }

  public void setSectionStatuses(Set<SectionStatus> sectionStatuses) {
    this.sectionStatuses = sectionStatuses;
  }


}

