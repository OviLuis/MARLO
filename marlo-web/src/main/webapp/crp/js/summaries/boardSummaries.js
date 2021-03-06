$(document).ready(init);
var genderArray =
    [
        "Gender", "female", "male", "men", "elderly", "caste", "women", "equitable", "inequality", "equity",
        "social differentiation", "social inclusion", "youth", "social class", "children", "child"
    ];
var termsArray = [];
var reportYear = "2017";
function init() {
  // addGenderTerms();
  addSelect2();
  attachEvents();
  $(".reportYear").attr("disabled", "true");
  updateYearSelect();
  ajaxService();
}

function attachEvents() {

  // On change of projects select
  $(".allProjects").on("change", function() {
    var option = $(this).find("option:selected");
    $("#projectID").val(option.val());
    validateAllData();
  });

  $("input[name='cycle']").on(
      "change",
      function() {
        $("select.reportYear").empty();
        if($(this).val() == "Planning") {
          $("select.reportYear").append(
              "<option value='" + $(".planningYear").text() + "' selected>" + $(".planningYear").text() + "</option>");
        } else {
          $("select.reportYear")
              .append(
                  "<option value='" + $(".reportingYear").text() + "' selected>" + $(".reportingYear").text()
                      + "</option>");
// console.log(reportYear);
        }
        $("#projectID").val("-1");
        ajaxService();
        updateUrl($(".summariesOptions").find(".selected"));
        validateAllData();
      });

  $("#gender").on("change", function() {

    if($(this).hasClass("view")) {
      $(this).addClass("notview");
      $(this).removeClass("view");
      $(".gender").hide();
      removeGenderTerms();
    } else {
      $(this).removeClass("notview");
      $(this).addClass("view");
      addGenderTerms();
    }
  });
  // ADD TERM
  $(".inputTerm").on("keypress", function(event) {
    if(event.keyCode === 10 || event.keyCode === 13) {
      addTerm();
    }
  });
  $('.removeTerm').on('click', removeTerm);

  $(".notAvailable").attr("title", "Not available at the moment");
  $('.summariesSection a, .summariesSection span').on('click', selectSummariesSection);
  $('.generateReport').on('click', generateReport);

  // Clicking other report
  $(".title-file , .description").on("click", function() {
    $("#optionsPopUp").find(".projectSelectWrapper").hide("slow");
    $("#projectID").val("-1");
    // fileTypes
    $("#optionsPopUp").find(".pdfIcon").parent().show();
    $("#optionsPopUp").find(".excelIcon").parent().show();
    $("#optionsPopUp").find(".pdfIcon").parent().removeClass("choose");
    $("#optionsPopUp").find(".pdfIcon").parent().addClass("notChoose");
    $("#optionsPopUp").find(".excelIcon").parent().removeClass("choose");
    $("#optionsPopUp").find(".excelIcon").parent().addClass("notChoose");
    // $('.wordContent').empty();
    var $this = $(this).parents(".summariesFiles");
    $(".summariesFiles").removeClass("selected");
    $(".extraOptions").fadeOut();
    $(".generateReport").fadeOut();
    $('.extraOptions').find('select, input').attr('disabled', true);
    $($this).find('.extraOptions').fadeIn();
    $($this).find('.generateReport').fadeIn();
    $($this).find('.extraOptions').find('select, input').attr('disabled', false).trigger("liszt:updated");
    $($this).addClass("selected");
    updateUrl($this);
  });

  $('.close-dialog').on('click', function() {
    $("#optionsPopUp").dialog("close");
    $("#optionsPopUp").find(".okButton").prepend("<span class='blockButton'></span>");
    $(".okButton a").css("opacity", "0.4");
  });

  $(".okButton a").on("click", function() {
    var $selected = $('.selected');
    reportTypes($selected);
    $("#optionsPopUp").dialog("close");
    $("#optionsPopUp").find(".okButton").prepend("<span class='blockButton'></span>");
    $(".okButton a").css("opacity", "0.4");
  });

  $(".file").on("click", function() {
    $(".file").parent().removeClass("choose");
    $(".file").parent().addClass("notChoose");
    $(this).parent().addClass("choose");
    $(this).parent().removeClass("notChoose");
    validateAllData();
  });

}

function ajaxService() {
  $(".allProjects").empty();
  $(".allProjects").addOption("-1", "Select an option...");
  $.ajax({
      url: baseURL + "/projectList.do?",
      type: 'GET',
      data: {
          cycle: $("input[name='cycle']:checked").val(),
          year: $("select#reportYears").find("option:selected").val(),
          phaseID: phaseID
      },
      success: function(m) {
        console.log(m);
        $.each(m.projects, function(i,e) {
          $(".allProjects").addOption(e.id, "P" + e.id + "-" + e.description);
        })
      },
      error: function(e) {
        console.log(e);
      }
  });
}

function updateYearSelect() {
  if($("input[name='cycle']:checked").val() == "Planning") {
    $("select.reportYear").append(
        "<option value='" + $(".planningYear").text() + "' selected>" + $(".planningYear").text() + "</option>");
  } else {
    $("select.reportYear").append(
        "<option value='" + $(".planningYear").text() + "' selected>" + $(".planningYear").text() + "</option>");
  }
}

function addGenderTerms() {
  for(var i = 0; i < genderArray.length; i++) {
    var $item = $('#term').clone(true).removeAttr("id");
    $item.addClass("gender");
    $item.css("display", "inline-block");
    $item.find(".text").html(genderArray[i]);
    $(".wordContent").append($item);
    $item.show('slow');
  }
}

function removeGenderTerms() {
  $(".wordContent").find(".gender").each(function(i,e) {
    $(e).remove();
  });
}

function addTerm() {
  console.log("holi test");
  var input = $(".inputTerm");
  var $list = $('.wordContent');
  var $item = $('#term').clone(true).removeAttr("id");
  if(validateInputTerms() == true) {
    $item.css("display", "inline-block");
    $item.find(".text").html(input.val());
    input.removeClass("fieldError");
    $list.prepend($item);
    $item.show('slow');
    input.val("");
  } else {
    input.addClass("fieldError");
  }

}

function removeTerm() {
  var $list = $(this).parents('.wordContent');
  var $item = $(this).parents('.terms');
  $item.hide(1000, function() {
    $item.remove();
  });
}

function validateInputTerms() {
  var input = $(".inputTerm");
  if(input.val().length > 0) {
    if(/^\s+|\s+$/.test(input.val()) || /~#/g.test(input.val())) {
      return false
    } else {
      return true;
    }
  } else {
    return false;
  }
}

function selectSummariesSection(e) {
  e.preventDefault();
  var $section = $(e.target).parents('.summariesSection');

  var $content = $('#' + $section.attr('id') + '-contentOptions');
  $section.siblings().removeClass('current');
  $section.addClass('current');
  $content.siblings().hide();
  $content.fadeIn();

  // Uncheck from formOptions the option selected
  $("input[name='projectID']").val("-1");
  $("#selectProject").html("Click over me");
  $('input[name=formOptions]').attr('checked', false);
  $(".summariesFiles").removeClass("selected");
  $(".extraOptions").fadeOut();
  $('.extraOptions').find('select, input').attr('disabled', true);
  // Clean URL
  setUrl('#');
  $("#optionsPopUp").find(".projectSelectWrapper").hide("slow");
  $("#projectID").val("-1");
}

function generateReport(e) {
  var $select = $(".reportYear ");
  $select.empty();
  $select.attr("disabled", true);
  e.preventDefault();
  $("#optionsPopUp").find("#planning").removeAttr("disabled");
  $("#optionsPopUp").find("#reporting").removeAttr("disabled");
  var $selected = $('.selected');
// Check cycle summary
  if($($selected).find(".forCycle").hasClass("forPlanningCycle")
      && $($selected).find(".forCycle").hasClass("forReportingCycle")) {
    // Planning and Reporing
    $select.append("<option value='" + $(".planningYear").text() + "' selected>" + $(".planningYear").text()
        + "</option>");
    $("#optionsPopUp").find("#planning").attr("checked", true).trigger("click");
    $("#optionsPopUp").find("#reporting").removeAttr("checked");
    $("#optionsPopUp").find("#reporting").removeAttr("disabled");
  } else if($($selected).find(".forCycle").hasClass("forPlanningCycle")) {
    // Planning
    $select.append("<option value='" + $(".planningYear").text() + "' selected>" + $(".planningYear").text()
        + "</option>");
    $("#optionsPopUp").find("#planning").attr("checked", true).trigger("click");
    $("#optionsPopUp").find("#reporting").removeAttr("checked");
    $("#optionsPopUp").find("#reporting").attr("disabled", true);
  } else if($($selected).find(".forCycle").hasClass("forReportingCycle")) {
    // Reporting
    $select.append("<option value='" + $(".reportingYear").text() + "' selected>" + $(".reportingYear").text()
        + "</option>");
    $("#optionsPopUp").find("#reporting").attr("checked", true).trigger("click");
    $("#optionsPopUp").find("#planning").removeAttr("checked");
    $("#optionsPopUp").find("#planning").attr("disabled", true);
  }
  if($selected.find("#generateProject").exists()) {
    $("#optionsPopUp").find(".projectSelectWrapper").show();
    validateFileType($selected);
    openDialog();
  } else {
    validateFileType($selected);
    openDialog();
  }

  if($selected.find(".specificYears").exists()) {
    $select.empty();
    $select.attr("disabled", false);
    $.each($selected.find(".specificYears").text().split("-"), function(i,e) {
      $select.addOption(e, e);
    });
    // $select.val($("span.reportingYear").text()).trigger("change");
  }
  validateAllData();
}

function validateAllData() {
  if($(".selected").find("#projectID").exists()) {
    if($("#projectID").val() == "-1") {
      $("#optionsPopUp").find(".blockButton").remove();
      $(".okButton").prepend('<span class="blockButton"></span>');
      $(".okButton a").css("opacity", "0.4");
      return true;
    }
  }
  console.log("validate");
  var count = 0;
  console.log($("input[name='cycle']:checked").val());
  if($("input[name='cycle']:checked").val() != undefined) {
    count++;
  }
  console.log(count);
  if($("#optionsPopUp").find(".choose").exists()) {
    count++;
  }
  console.log(count);
  if(count >= 2) {
    $("#optionsPopUp").find(".blockButton").remove();
    $(".okButton a").css("opacity", "1");
  }
}

function validateFileType($selected) {
  if($selected.find(".fileTypes").length <= 1) {
    var type = $selected.find(".fileTypes");
    if($(type).hasClass("pdfType")) {
      var file = $("#optionsPopUp").find(".excelIcon").parent();
      $(file).removeClass("choose");
      file.hide();
      $("#optionsPopUp").find(".pdfIcon").parent().removeClass("notChoose");
      $("#optionsPopUp").find(".pdfIcon").parent().addClass("choose");
    } else {
      var file = $("#optionsPopUp").find(".pdfIcon").parent();
      file.hide();
      $(file).removeClass("choose");
      $("#optionsPopUp").find(".excelIcon").parent().removeClass("notChoose");
      $("#optionsPopUp").find(".excelIcon").parent().addClass("choose");
    }
  }
}

function openDialog() {
  $("#optionsPopUp").dialog({
      resizable: false,
      width: 500,
      modal: true,
      dialogClass: 'dialog-searchUsers',
      show: {
          effect: "blind",
          duration: 500
      },
      hide: {
          effect: "fadeOut",
          duration: 500
      },
      open: function(event,ui) {
      }
  });
}

function reportTypes($selected) {
// FULL REPORT
  if($selected.find(".extraOptions").find("#projectID").exists()) {
    updateUrl($selected);
  } else if($selected.find(".extraOptions").find(".wordContent").exists()) {
    // TERMS
    var url = "";
    var $formOptions = $($selected).find('input[name=formOptions]');
    var formOption = $formOptions.val() || 0;
    if($(".wordContent").find(".terms").length > 0) {
      console.log("here");
      $(".wordContent").find(".terms").each(function(i,e) {
        termsArray.push($(e).find(".text").html());
      });
      url =
          baseURL + "/projects/" + currentCrpSession + "/" + formOption + ".do" + "?cycle="
              + $("input[name='cycle']:checked").val() + "&keys=" + termsArray.join("~/");

      console.log(reportYear);

      if($("input[name='cycle']:checked").val() == "Planning") {
        reportYear = $(".planningYear").text();
      } else {
        reportYear = $(".reportingYear").text();
      }

      url += '&year=' + reportYear;
      var replace = url.replace(/ /g, "%20");
      setUrl(replace);
    } else {
      updateUrl($selected);
    }
    termsArray = [];
    $("#gender").prop('checked', true);
    $("#gender").removeClass("view");
    $("#gender").removeClass("notview");
    $("#gender").addClass("view");
  } else {
    updateUrl($selected);
  }
}

function updateUrl(element) {
  var generateUrl = "";
  var formOption = "";
  var $formOptions = $(element).find('input[name=formOptions]');
  var type = $(element).find(".fileTypes").text().split("-")[0];
// Check type of file
  if($("#optionsPopUp").find(".pdfIcon").parent().hasClass("choose")) {
    console.log("here1");
    formOption = $(element).find(".pdfType").text();
  } else if($("#optionsPopUp").find(".excelIcon").parent().hasClass("choose")) {
    if($("#optionsPopUp").find("input[name='cycle']:checked").val() == "Planning") {
      console.log("aaa");
      formOption = $(element).find(".excelType").text().split("-")[0];
    } else {
      console.log("bbb");
      formOption = $(element).find(".excelType").text().split("-")[1];
    }
    console.log("here2");

  } else {
    console.log("here3");
    formOption = $formOptions.val() || 0;
  }
  // Check extra options
  var extraOptions = $('form [name!="formOptions"]').serialize() || 0;
  console.log(formOption);
  if(formOption != 0) {
    console.log("create url yes");
    generateUrl =
        baseURL + "/projects/" + currentCrpSession + "/" + formOption + ".do?" + "cycle="
            + $("input[name='cycle']:checked").val();
    if(extraOptions != 0) {
      generateUrl += '&' + extraOptions;
    }
    console.log(reportYear);
    if($("input[name='cycle']:checked").val() == "Planning") {
      reportYear = $(".planningYear").text();
    } else {
      reportYear = $(".reportingYear").text();
    }

    if($(element).find(".specificYears").exists()) {
      reportYear = $(".reportYear").find("option:selected").val();
    }

// console.log(reportYear);
    generateUrl += '&year=' + reportYear;
    setUrl(generateUrl, element);
  } else {
    setUrl('#');
  }
}

function setUrl(url,$this) {
  console.log(url);
  if(url == '#') {
    $('#generateReport').hide();
  } else {
    $('.okButton a').attr('href', url).fadeIn();
  }
}

// Activate the select plugin.
function addSelect2() {
  $('select').select2({
    width: '100%'
  });
  $("#genderKeywords").select2({
    tags: [
        "red", "green", "blue"
    ]
  });
  $.ui.dialog.prototype._allowInteraction = function(e) {
    return !!$(e.target).closest('.ui-dialog, .ui-datepicker, .select2-dropdown').length;
  };
}
