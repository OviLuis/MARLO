[#ftl]
[#-- Compliance check (Data products only) --]
<h4 class="headTitle">Compliance check (Data products only)</h4>

<div class="fullBlock" > 
<div class="col-md-12 note">[@s.text name = "Compliance check section guarantees that a data deliverable is 'Gold Data'.  If you select 2 out of the 3 questions with yes and documented, and the ranking is over 3.5, it qualifies to be a Gold Data deliverable." /]</div>
<div class="clearfix"></div>
  [#-- Question1 --]
  <div class="question borderBox">
    <h5>Have you had a process of data quality assurance in place?</h5>
    <hr />
    <br />
    <div class="col-md-4">
    [#list answers as answer]
      <div class="radio">
        <label><input type="radio" name="deliverable.qualityCheck.qualityAssurance.id" value="${(answer.id)!}">${(answer.name)!}</label>
      </div>
    [/#list]
    </div>
    
    [#-- FILE --]
    <div class="col-md-8">
      <div class="col-md-6 form-group fileUploadContainer">
        <label>[@customForm.text name="Proof of submission" readText=!editable /]:</label>
        [#-- Input File --]
        [#if editable]
        <div class="fileUpload" style="display:block"> <input class="upload" type="file" name="file" data-url=""></div>
        [/#if]
      </div>
      <span class="col-md-1"> <br /> or</span>
      <div class="col-md-5">
      <br />
      [@customForm.input name="" i18nkey="" showTitle=false placeholder="Please give us the link" required=true  editable=editable /]
      </div>
    </div>
  </div>
  [#-- Question2 --]
  <div class="question borderBox">
    <h5>Do you have a data dictionary?</h5>
    <hr />
    <br />
    <div class="col-md-4">
    [#list answers as answer]
      <div class="radio">
        <label><input type="radio" name="deliverable.qualityCheck.dataDictionary.id" value="${(answer.id)!}">${(answer.name)!}</label>
      </div>
    [/#list]
    </div>
    
    [#-- FILE --]
    <div class="col-md-8">
      <div class="col-md-6 form-group fileUploadContainer">
        <label>[@customForm.text name="Proof of submission" readText=!editable /]:</label>
        [#-- Input File --]
        [#if editable]
        <div class="fileUpload" style="display:block"> <input class="upload" type="file" name="file" data-url=""></div>
        [/#if]
      </div>
      <span class="col-md-1"> <br /> or</span>
      <div class="col-md-5">
      <br />
      [@customForm.input name="" i18nkey="" showTitle=false placeholder="Please give us the link" required=true  editable=editable /]
      </div>
    </div>
  </div>
  [#-- Question3 --]
  <div class="question borderBox">
    <h5>Are the tools used for data collection available (e.g. surveys, data analysis scripts, training materials, etc.)?</h5>
    <hr />
    <br />
    <div class="col-md-4">
      [#list answers as answer]
      <div class="radio">
        <label><input type="radio" name="deliverable.qualityCheck.dataTools.id" value="${(answer.id)!}">${(answer.name)!}</label>
      </div>
    [/#list]
    </div>
    
    [#-- FILE --]
    <div class="col-md-8">
      <div class="col-md-6 form-group fileUploadContainer">
        <label>[@customForm.text name="Proof of submission" readText=!editable /]:</label>
        [#-- Input File --]
        [#if editable]
        <div class="fileUpload" style="display:block"> <input class="upload" type="file" name="file" data-url=""></div>
        [/#if]
      </div>
      <span class="col-md-1"> <br /> or</span>
      <div class="col-md-5">
      <br />
      [@customForm.input name="" i18nkey="" showTitle=false placeholder="Please give us the link" required=true  editable=editable /]
      </div>
    </div>
  </div>
</div>