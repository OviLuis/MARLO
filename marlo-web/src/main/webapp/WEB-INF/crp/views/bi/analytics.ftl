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
       <iframe width="1064px" height="800px" src="${urlSaiku}" frameborder="0" style="border:0"></iframe>       
    </div> 
  </article>
</section>



[#include "/WEB-INF/crp/pages/footer.ftl"]