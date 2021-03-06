[#ftl]
[#assign title = "POWB Report" /]
[#assign currentSectionString = "powb-${actionName?replace('/','-')}-${liaisonInstitutionID}" /]
[#assign pageLibs = [ ] /]
[#assign customJS = [ ] /]
[#assign customCSS = ["${baseUrlMedia}/css/powb/powbGlobal.css"] /]
[#assign currentSection = "synthesis" /]
[#assign currentStage = "interactions" /]

[#assign breadCrumb = [
  {"label":"${currentSection}", "nameSpace":"", "action":""},
  {"label":"powbReport", "nameSpace":"powb", "action":"${crpSession}/summaryHighlight"},
  {"label":"interactions", "nameSpace":"powb", "action":"${crpSession}/interactions"}
]/]

[#import "/WEB-INF/global/macros/utils.ftl" as utilities /]
[#include "/WEB-INF/global/pages/header.ftl" /]
[#include "/WEB-INF/global/pages/main-menu.ftl" /]

[#-- Helptext --]
[@utilities.helpBox name="crossCrpinteractions.help" /]
    
<section class="container">
  [#-- Program (Flagships and PMU) --]
  [#include "/WEB-INF/crp/views/powb/submenu-powb.ftl" /]
  
  <div class="row">
    [#-- POWB Menu --]
    <div class="col-md-3">
      [#include "/WEB-INF/crp/views/powb/menu-powb.ftl" /]
    </div> 
    <div class="col-md-9">
      [#-- Section Messages --]
      [#include "/WEB-INF/crp/views/powb/messages-powb.ftl" /]
      
      [@s.form action=actionName method="POST" enctype="multipart/form-data" cssClass=""]
         
        [#-- Title --]
        <h3 class="headTitle">[@s.text name="crossCrpinteractions.title" /]</h3>
        <div class="borderBox">
          <h5 class="sectionSubTitle">[@s.text name="crossCrpinteractions.platformsCollaborations" /]</h5>
          <div class="collaborationBlock">
            <div class="items-list">
              [#list 1..2 as element]
                [@crossCrpInteractions element=element name="liaisonInstitution.powb.crossCrpinteractions" index=element_index /]
              [/#list]
            </div>
            [#-- Add Item --]
            [#if editable] 
              <div class="text-center">
                <div class="dddCollaboration bigAddButton"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span> [@s.text name="form.buttons.addCrossCrpInteraction" /]</div>
              </div>
            [/#if]
          </div>
          
          [#-- Other Flagships CRP Interactions--]
          <div class="form-group">
            <h5 class="sectionSubTitle">[@s.text name="crossCrpinteractions.otherCollaborations" /]</h5>
            <table class="">
              <thead>
                <tr>
                  <th>Platform Name</th>
                  <th>Platform Description</th>
                  <th>Flagship</th>
                </tr>
              </thead>
              <tbody>
                [#list 2..3 as crossCrpInteraction]
                <tr>
                  <td>Big Data</td>
                  <td>Lorem ipsum dolor sit amet, consectetur adipisicing elit. A earum delectus placeat reiciendis ducimus aspernatur animi vel corporis incidunt numquam odit esse natus illo ipsum pariatur blanditiis eius totam sed?</td>
                  <td>F${crossCrpInteraction}</td>
                </tr>
                [/#list]
              </tbody>
            </table>
          </div>
          
        </div>
        
        [#-- Section Buttons & hidden inputs--]
        [#include "/WEB-INF/crp/views/powb/buttons-powb.ftl" /]
        
      [/@s.form] 
    </div> 
  </div> 
</section>
[#include "/WEB-INF/global/pages/footer.ftl"]

[#macro crossCrpInteractions element name index ]
  [#local customName = "${name}[${index}]"]
  <div class="simpleBox">
    <div class="leftHead blue sm">
      <span class="index">${index+1}</span>
      <span class="elementId"> Cross-CRP Interaction</span>
    </div>
    <br />
    <div class="form-group row">
      <div class="col-md-8">
        [@customForm.input name="${customName}.name" i18nkey="${name}.name" required=true className="" editable=editable /]
      </div>
      <div class="col-md-4">
        [#if PMU]
          [@customForm.select name="${customName}.crpProgram.id" label="" i18nkey="${name}.flagship" listName="flafshipsList" keyFieldName="id"  displayFieldName="composedName" className="" editable=editable/]
        [#else]
          <label for="">[@s.text name="${name}.flagship" /]</label>     
          <p>${liaisonInstitution.composedName}</p>
          <input type="hidden" name="${customName}.crpProgram.id" value="${liaisonInstitution.crpProgram.id}"/>
        [/#if]
      </div>
    </div>
    <div class="form-group">
      [@customForm.textArea name="${customName}.description" i18nkey="${name}.description" required=true className="limitWords-100" editable=editable /]
    </div>
  </div>
[/#macro]