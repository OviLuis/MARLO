package org.cgiar.ccafs.marlo.data.model;
// Generated Jan 22, 2018 1:53:05 PM by Hibernate Tools 4.3.1.Final


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.annotations.Expose;

/**
 * ProjectExpectedStudy generated by hbm2java
 */
public class ProjectExpectedStudy extends MarloAuditableEntity implements java.io.Serializable, IAuditLog {


  /**
   * 
   */
  private static final long serialVersionUID = 8398283484560886533L;

  @Expose
  private Phase phase;
  private Project project;
  @Expose
  private SrfSubIdo srfSubIdo;
  @Expose
  private String composedId;
  @Expose
  private String topicStudy;
  @Expose
  private Integer scope;
  @Expose
  private Integer type;
  @Expose
  private String otherType;
  @Expose
  private SrfSloIndicator srfSloIndicator;
  @Expose
  private String comments;

  private Set<ExpectedStudyProject> expectedStudyProjects = new HashSet<ExpectedStudyProject>(0);
  private List<ExpectedStudyProject> projects;


  public ProjectExpectedStudy() {
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
    ProjectExpectedStudy other = (ProjectExpectedStudy) obj;
    if (this.getId() == null) {
      if (other.getId() != null) {
        return false;
      }
    } else if (!this.getId().equals(other.getId())) {
      return false;
    }
    return true;
  }


  public String getComments() {
    return comments;
  }


  public String getComposedId() {
    return composedId;
  }

  public Set<ExpectedStudyProject> getExpectedStudyProjects() {
    return expectedStudyProjects;
  }


  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();
    sb.append("Id : ").append(this.getId());
    return sb.toString();
  }


  public String getOtherType() {
    return otherType;
  }


  public Phase getPhase() {
    return phase;
  }


  public Project getProject() {
    return project;
  }


  public List<ExpectedStudyProject> getProjects() {
    return projects;
  }


  public Integer getScope() {
    return scope;
  }


  public String getScopeName() {
    if (scope == null || scope.intValue() == -1) {
      return "";
    }
    return GlobalScopeEnum.getValue(scope.intValue()).getType();

  }


  public SrfSloIndicator getSrfSloIndicator() {
    return srfSloIndicator;
  }


  public SrfSubIdo getSrfSubIdo() {
    return srfSubIdo;
  }


  public String getTopicStudy() {
    return topicStudy;
  }

  public Integer getType() {
    return type;
  }

  public String getTypeName() {
    if (type == null || type.intValue() == -1) {
      return "";
    }
    return TypeExpectedStudiesEnum.getValue(type.intValue()).getType();

  }


  public void setComments(String comments) {
    this.comments = comments;
  }


  public void setComposedId(String composedId) {
    this.composedId = composedId;
  }

  public void setExpectedStudyProjects(Set<ExpectedStudyProject> expectedStudyProjects) {
    this.expectedStudyProjects = expectedStudyProjects;
  }

  public void setOtherType(String otherType) {
    this.otherType = otherType;
  }


  public void setPhase(Phase phase) {
    this.phase = phase;
  }


  public void setProject(Project project) {
    this.project = project;
  }


  public void setProjects(List<ExpectedStudyProject> projects) {
    this.projects = projects;
  }


  public void setScope(Integer scope) {
    this.scope = scope;
  }


  public void setSrfSloIndicator(SrfSloIndicator srfSloIndicator) {
    this.srfSloIndicator = srfSloIndicator;
  }


  public void setSrfSubIdo(SrfSubIdo srfSubIdo) {
    this.srfSubIdo = srfSubIdo;
  }


  public void setTopicStudy(String topicStudy) {
    this.topicStudy = topicStudy;
  }


  public void setType(Integer type) {
    this.type = type;
  }


}

