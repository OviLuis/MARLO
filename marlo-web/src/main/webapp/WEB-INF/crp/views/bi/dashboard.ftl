[#ftl]
[#assign title = "MARLO Business Intelligence" /]
[#assign currentSectionString = "${actionName?replace('/','-')}" /]
[#assign pageLibs = ["select2","font-awesome","jsUri"] /]
[#assign customJS = [] 
/]
[#assign customCSS = ["${baseUrlMedia}/css/summaries/summaries.css"] /]
[#assign currentSection = "moduleBI" /]

[#assign breadCrumb = [
  {"label":"summaries", "nameSpace":"summaries", "action":"summaries"}
]/]

[#include "/WEB-INF/crp/pages/header.ftl" /]
[#include "/WEB-INF/crp/pages/main-menu.ftl" /]
    
<section class="container">
  <article id="" class="col-md-12" > 
    <div class="borderBox">
       
       [#assign biURL = "http://172.22.33.21:8090/pentaho/api/repos/%3Apublic%3AmarloBI%3AbudgetDashboards%3AbudgetDashboard.wcdf/generatedContent?userid=admin&password=password"]
       
       [#-- 
       <iframe id="dashboard" type="text/html" width="100%" height="1000px" src="${biURL}"></iframe>
        --]
       
       <iframe width="1064px" height="800px" src="https://datastudio.google.com/embed/reporting/1EfeOigBJKHQ0LRcaK6mCK3GsYI1DweiW/page/1kVL" frameborder="0" style="border:0"></iframe>
       
    </div> 
  </article>
</section>



[#include "/WEB-INF/crp/pages/footer.ftl"]