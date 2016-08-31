$(document).ready(init);

function init() {
  /* Init Select2 plugin */
  $('form select').select2({
    width: "100%"
  });

  /* Events select */
  subTypes();
  keyOutputs();

  // validate option type for ajax service
  var optionSelected = $(".typeSelect").find("option:selected");
  if(optionSelected.val() != "-1") {
    var url = baseURL + "/deliverableSubType.do";
    var data = {
      deliverableTypeId: optionSelected.val()
    }
    $.ajax({
        url: url,
        type: 'GET',
        dataType: "json",
        data: data
    }).success(
        function(m) {
          for(var i = 0; i < m.deliverableSubTypes.length; i++) {
            $(".subTypeSelect").append(
                "<option value='" + m.deliverableSubTypes[i].id + "' >" + m.deliverableSubTypes[i].name + "</option>");
          }
        });
  }

  $(".addPartner").on("click", addPartnerEvent);
  $(".removeElement").on("click", removePartnerEvent);
  // Update value of responsible person
  $(".responsible").on(
      "change",
      function() {
        var option = $(this).find("option:selected");

        // validate if exists this person in contact person list
        var validation =
            $(this).parents(".fullBlock").parent().find(".personList").find("input[value=" + option.val() + "]");
        if(validation.exists()) {
          // Remove from contact person list
          validation.parent().hide("slow", function() {
            $(this).remove();
          })
          // Show message
          var text = option.html() + ' was removed from contact persons list';
          notify(text);
        } else {
          $(this).parent().parent().parent().parent().find(".id").val(option.val());
        }

      });
// Update value of partner
  $(".partner").on("change", function() {
    var option = $(this).find("option:selected");
    $(this).parent().parent().parent().parent().find(".id").val(option.val());
  });

}

// Add a new person element
function addPartnerEvent() {
  var $list = $(".personList");
  var $item = $("#deliverablePartner-template").clone(true).removeAttr("id");
  $list.append($item);
  $item.find('select').select2({
    width: "100%"
  });
  $item.show('slow');
  checkItems($list);
  updatePartners();
}

// Remove person element
function removePartnerEvent() {
  var $list = $(this).parents('.personList');
  var $item = $(this).parents('.deliverablePartner');
  $item.hide(1000, function() {
    $item.remove();
    checkItems($list);
    updatePartners();
  });

}

function updatePartners() {
  var name = "deliverable.deliverablePartnerships";
  $(".personList").find('.deliverablePartner').each(function(i,item) {

    var customName = name + '[' + i + ']';
    $(item).find('span.index').html(i + 1);
    $(item).find('.id').attr('name', customName + '.id');
    $(item).find('.type').attr('name', customName + '.type');
  });
}

function subTypes() {
  var url = baseURL + "/deliverableSubType.do";
  var typeSelect = $(".typeSelect");
  var subTypeSelect = $(".subTypeSelect");
  typeSelect.on("change", function() {

    subTypeSelect.empty();
    subTypeSelect.append("<option value='-1' >Select an option... </option>");
    subTypeSelect.trigger("change.select2");
    var option = $(this).find("option:selected");

    if(option.val() != "-1") {
      var data = {
        deliverableTypeId: option.val()
      }
      $.ajax({
          url: url,
          type: 'GET',
          dataType: "json",
          data: data
      }).success(
          function(m) {
            for(var i = 0; i < m.deliverableSubTypes.length; i++) {
              subTypeSelect.append("<option value='" + m.deliverableSubTypes[i].id + "' >"
                  + m.deliverableSubTypes[i].name + "</option>");
            }
          });
    }
  });
}

function keyOutputs() {
  var url = baseURL + "/keyOutputList.do";
  var clusterSelect = $(".cluster");
  var keyOutputSelect = $(".keyOutput");
  clusterSelect.on("change", function() {

    keyOutputSelect.empty();
    keyOutputSelect.append("<option value='-1' >Select an option... </option>");
    keyOutputSelect.trigger("change.select2");
    var option = $(this).find("option:selected");
    var data = {
      clusterActivityID: option.val()
    }
    $.ajax({
        url: url,
        type: 'GET',
        dataType: "json",
        data: data
    }).success(
        function(m) {
          console.log(m);
          for(var i = 0; i < m.keyOutputs.length; i++) {
            keyOutputSelect.append("<option value='" + m.keyOutputs[i].id + "' >" + m.keyOutputs[i].description
                + "</option>");
          }
        });
  });
}

function checkItems(block) {
  console.log(block);
  var items = $(block).find('.deliverablePartner').length;
  if(items == 0) {
    $(block).parent().find('p.emptyText').fadeIn();
  } else {
    $(block).parent().find('p.emptyText').fadeOut();
  }
}

function notify(text) {
  var notyOptions = jQuery.extend({}, notyDefaultOptions);
  notyOptions.text = text;
  notyOptions.type = 'alert';
  noty(notyOptions);
}