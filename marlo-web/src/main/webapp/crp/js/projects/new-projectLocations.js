$(document).ready(init);
var map;
var style;
var infoWindow = null;
var markers = [];
var countID;
var countries = [];
var layer;
var arSelectedLocations = [];

function init() {
// Init select2
  $('form select').select2({
    width: "100%"
  });

  //Google Maps
  loadScript();

  /* Declaring Events */
  attachEvents();

}

function attachEvents() {

  // REMOVE REGION
  $(".removeRegion").on("click", removeRegion);

  //Remove a location element-Event
  $(".removeLocation").on("click", removeLocationItem);

  // REGIONAL QUESTION
  $(".button-label").on("click", function() {
    var valueSelected = $(this).hasClass('yes-button-label');
    var $input = $(this).parent().find('input');
    $input.val(valueSelected);
    $(this).parent().find("label").removeClass("radio-checked");
    $(this).addClass("radio-checked");
    $(document).trigger('updateComponent');
  });

  $(".isRegional .button-label").on("click", function() {
    var valueSelected = $(this).hasClass('yes-button-label');
    if(!valueSelected) {
      $(".regionsBox").hide("slow");
      $(".regionsContent").hide("slow");
    } else {
      $(".regionsBox").show("slow");
      $(".regionsContent").show("slow");
    }
  });


  /** Remove region option from region select */
  var regionSelect = $("#regionSelect");
  $(".regionsContent").find(".recommended.locElement").each(function(i,e) {
    if($(e).find(".recommendedSelected").val() == "true") {
      var id = $(e).find(".elementID");
      var scope = $(e).find(".locScope");
      var option = regionSelect.find("option[value='" + id.val() + "-" + scope.val() + "']");
      option.prop('disabled', true);
    }
  });

  $("#regionList").find(".region").each(function(i,e) {
    var id = $(e).find("input.rId");
    var scope = $(e).find("input.regionScope");
    var option = regionSelect.find("option[value='" + id.val() + "-" + scope.val() + "']");
    option.prop('disabled', true);
  });

  // Region Select
  $("#regionSelect").on("change", function() {
    var option = $(this).find("option:selected");
    if(option.val() != "-1") {
      addRegion(option);
      // Remove option from select
      // option.remove();
      option.prop('disabled', true);
      $('#regionSelect').select2();
      // $(this).trigger("select2:change");
    }
  });

//Events
  $("#locLevelSelect").on("change",function() {
    var option = $(this).find("option:selected");
    if(option.val() == "-1") {
      $("#addLocationButton").hide("slow");
      resetInfoWindow();
    } else {
      $("#addLocationButton").show("slow");
      if(option.val().split("-")[1] == "true") {
        // LocElements options using ajax
        var select = $("#countriesCmvs");
        var url = baseURL + "/searchCountryListPL.do";
        var data = {
            parentId: option.val().split("-")[0],
            phaseID: phaseID
        };
        $.ajax({
            url: url,
            type: 'GET',
            dataType: "json",
            data: data
        }).done(
            function(m) {
              select.empty();
              for(var i = 0; i < m.locElements.length; i++) {
                select.append("<option class='" + m.locElements[i].isoAlpha2 + "' value='" + m.locElements[i].id
                    + "-" + m.locElements[i].isoAlpha2 + "-" + m.locElements[i].name + "' >"
                    + m.locElements[i].name + "</option>");
              }
            });
        $("#inputFormWrapper").slideUp();
        $(".selectLocations").slideDown();

      } else {
        $(".selectLocations").slideUp();
        $("#inputFormWrapper").slideDown();
      }
    }
    modalButtonsListeners();
  });

  // Clicking recommended location
  $('.recommendedLocName, .iconSelected').on(
      'click',
      function() {
        var parent = $(this).parent();
        var selectedInput = parent.find("input.recommendedSelected");
        var option =
            $("#regionSelect").find(
                "option[value='" + parent.find("input.elementID").val() + "-" + parent.find("input.locScope").val()
                    + "']");
        if(parent.find(".acceptLocation").exists()) {
          parent.find(".iconSelected").removeClass("acceptLocation");
          parent.find(".iconSelected").addClass("notAcceptLocation");
          selectedInput.val("false");
          option.prop('disabled', false);
          $('#regionSelect').select2();
        } else {
          checkRecommendedLocation(parent);
          parent.find(".iconSelected").removeClass("notAcceptLocation");
          parent.find(".iconSelected").addClass("acceptLocation");
          selectedInput.val("true");
          option.prop('disabled', true);
          $('#regionSelect').select2();

        }
        $(document).trigger('updateComponent');
      });

  $('input.recommendedSelected').on('change', function() {
    $(this).next().val($(this).is(":checked"));
  });
}

function modalButtonsListeners(){

  // Add location button
  $("#addLocationButton").on("click", function(e) {

    //Select input
    var $locationLevelSelect = $("#locLevelSelect");
    var locationId = $locationLevelSelect.val().split("-")[0];
    var locationIsList = $locationLevelSelect.val().split("-")[1];
    var locationName = $locationLevelSelect.val().split("-")[2];

    var $locationSelect = $("#countriesCmvs");
    // checking if is list
    if(locationIsList == "true") {
      // Checking if locations select is empty
      if($locationSelect.val() != null) {

        // Checking if the location level exist in the bottom wrapper
        if($(".locationsDataTable").find("input.locationLevelId[value='" + locationId + "']").exists()) {
          console.log('locationLvlId Exists');
          addCountryIntoLocLevel(locationId, $locationSelect, locationName);
        } else {
          addLocLevel(locationName, locationId, locationIsList, $locationSelect, locationIsList);
        }
      }
    } else {
      if($("#inputFormWrapper").find(".name").val().trim() == "") {
        $("#inputFormWrapper").find(".name").addClass("fieldError");
      } else {
        $("#inputFormWrapper").find(".name").removeClass("fieldError");
        if($("#inputFormWrapper").find(".fieldError").exists()) {

        } else {
          // Checking if the location level exist in the bottom wrapper
          if($(".locationsDataTable").find("input.locationLevelId[value='" + locationId + "']").exists()) {
            addLocByCoordinates(locationId, $locationSelect, locationName)
          } else {
            addLocLevel(locationName, locationId, locationIsList, $locationSelect, locationIsList);
          }
        }
      }
    }
  });

  // Cancel button
  $("#cancelButton-locations").on("click", function(e) {
    e.preventDefault();
    $("#close-modal-button").click();
  });
}

//Add Regions
function addRegion(option) {
  var canAdd = true;
  if(option.val() == "-1") {
    canAdd = false;
  }
  var optionValue = option.val().split("-")[0];
  var optionScope = option.val().split("-")[1];

  var $list = $(option).parents("#regionList").find(".list");
  var $item = $("#regionTemplate").clone(true).removeAttr("id");
  var v = $(option).text().length > 20 ? $(option).text().substr(0, 20) + ' ... ' : $(option).text();

  // Check if is already selected
  $list.find('.region').each(function(i,e) {
    if($(e).find('input.rId').val() == optionValue) {
      canAdd = false;
      return;
    }
  });
  if(!canAdd) {
    return;
  }

  // Set region parameters
  $item.find(".name").attr("title", $(option).text());
  $item.find(".name").html(v);
  $item.find(".rId").val(optionValue);
  $item.find(".regionScope").val(optionScope);
  $item.find(".id").val(-1);
  $list.append($item);
  $item.show('slow');
  updateRegionList($list);
  checkRegionList($list);

}

function removeRegion() {
  var $list = $(this).parents('.list');
  var $item = $(this).parents('.region');
  var value = $item.find(".rId").val();
  var scope = $item.find(".regionScope").val();
  var name = $item.find(".name").attr("title");

  var $select = $(".regionsSelect");
  $item.hide(300, function() {
    $item.remove();
    checkRegionList($list);
    updateRegionList($list);
  });
  var option = $select.find("option[value='" + value + "-" + scope + "']");
  option.prop('disabled', false);
  $('#regionSelect').select2();
}

function updateRegionList($list) {

  $($list).find('.region').each(function(i,e) {
    // Set regions indexes
    $(e).setNameIndexes(1, i);
  });
}

function checkRegionList(block) {
  var items = $(block).find('.region').length;
  if(items == 0) {
    $(block).parent().find('p.emptyText').fadeIn();
  } else {
    $(block).parent().find('p.emptyText').fadeOut();
  }
}

//Load script of google services
function loadScript() {
  var script = document.createElement("script");
  script.src = "https://maps.googleapis.com/maps/api/js?key=" + GOOGLE_API_KEY + "&callback=initMap";
  document.body.appendChild(script);
}

//Initialization Google Map API
function initMap() {

  style = [
      {
          "featureType": "water",
          "stylers": [
              {
                "visibility": "on"
              }, {
                "color": "#b5cbe4"
              }
          ]
      }, {
          "featureType": "landscape",
          "stylers": [
            {
              "color": "#efefef"
            }
          ]
      }, {
          "featureType": "road.highway",
          "elementType": "geometry",
          "stylers": [
            {
              "color": "#83a5b0"
            }
          ]
      }, {
          "featureType": "road.arterial",
          "elementType": "geometry",
          "stylers": [
            {
              "color": "#bdcdd3"
            }
          ]
      }, {
          "featureType": "road.local",
          "elementType": "geometry",
          "stylers": [
            {
              "color": "#ffffff"
            }
          ]
      }, {
          "featureType": "poi.park",
          "elementType": "geometry",
          "stylers": [
            {
              "color": "#e3eed3"
            }
          ]
      }, {
          "featureType": "administrative",
          "stylers": [
              {
                "visibility": "on"
              }, {
                "lightness": 33
              }
          ]
      }, {
        "featureType": "road"
      }, {
          "featureType": "poi.park",
          "elementType": "labels",
          "stylers": [
              {
                "visibility": "on"
              }, {
                "lightness": 20
              }
          ]
      }, {}, {
          "featureType": "road",
          "stylers": [
            {
              "lightness": 20
            }
          ]
      }
  ];
  var mapDiv = document.getElementById('map');
  map = new google.maps.Map(mapDiv, {
      center: new google.maps.LatLng(14.41, -12.52),
      zoom: 3,
      mapTypeId: 'roadmap',
      styles: style
  });
  //Center marker into map on click
  $('<div/>').addClass('centerMarker').appendTo(map.getDiv()).click(function(){
    addLocationFromMap();
 });
  var centerControlDiv = document.createElement('div');
  if(editable && $("span.has_otherLoc").text() == "true") {
    var centerControl = new CenterControl(centerControlDiv, map);
    centerControlDiv.index = 1;
    map.controls[google.maps.ControlPosition.TOP_CENTER].push(centerControlDiv);
  }

  infoWindow = new google.maps.InfoWindow();

  google.maps.event.addListener(infoWindow, 'closeclick', function() {
    $(".locations").removeClass("selected");
  });

  google.maps.event.addListener(map, 'click', function(event) {
    // infoWindow.close();
    $(".locations").removeClass("selected");
  });

  map.addListener('center_changed', function() {
    // 3 seconds after the center of the map has changed, pan back to the
    // marker.
    console.log();
    $("#inputFormWrapper").find(".latitude").val(map.getCenter().lat());
    $("#inputFormWrapper").find(".longitude").val(map.getCenter().lng());
  });

// google.maps.event.addListener(map, 'rightclick', function(e) {
// openInfoWindowForm(e);
// });

  if(markers.length > 0) {
    map.setCenter(markers[markers.length - 1].getPosition());
  }

  mappingCountries();

}

function mappingCountries() {
  var query = "";
  layer = new google.maps.FusionTablesLayer();
  if(countries.length > 0) {

    $.each(countries, function(i,c) {
      if(i == countries.length - 1) {
        query = query + "'" + c + "'";
      } else {
        query = query + "'" + c + "',";
      }
    });
    var FT_Options = {
        suppressInfoWindows: true,
        query: {
            select: 'kml_4326',
            from: 2597535,
            where: "'ISO_2DIGIT' IN (" + query + ") "
        },
        styles: [
          {
            polygonOptions: {
                fillColor: "#2E2EFE",
                fillOpacity: 0.15
            }
          }
        ]
    };
    layer = new google.maps.FusionTablesLayer(FT_Options);
    layer.setMap(map);
    google.maps.event.addListener(layer, 'click', function(e) {
      openInfoWindowCountries(e);
    });
  }

}

//Remove a location element-Function
function removeLocationItem() {
  var globalList = $(this).parents("#selectsContent");
  var list = $(this).parents(".optionSelect-content");
  var $item = $(this).parents('.locElement');
  if($item.find(".geoLatitude").val() != "" && $item.find(".geoLongitude").val() != "") {
    var optionValue = $item.attr("id").split('-');
    var id = optionValue[1];
    if(markers[id] == undefined) {

    } else {
      removeMarker(id);
    }
  }
  $item.hide(function() {
    $item.remove();
    if($(list).find(".locElement").length == 0) {
      $(list).parents(".locationLevel").remove();
    }
    updateIndex();
    checkItems(globalList);
  });
  layer.setMap(null);
  /* Remove of countries array */

  var index = countries.indexOf($item.find(".locElementCountry").val());
  if(index > -1) {
    countries.splice(index, 1);
  }
  mappingCountries();
}

//Set default country to countries select
function setCountryDefault() {

// Ajax for country name
  $.ajax({
      'url': 'https://maps.googleapis.com/maps/api/geocode/json',
      'data': {
          key: GOOGLE_API_KEY,
          latlng: (map.getCenter().lat() + "," + map.getCenter().lng())
      },
      success: function(data) {
        if(data.status == 'OK') {
          var country = getResultByType(data.results[0], 'country').short_name;
          var $countrySelect = $("#countriesCmvs");
          arSelectedLocations.push($countrySelect.find("option." + country).val());
          $countrySelect.val(arSelectedLocations);
          $countrySelect.select2().trigger("change");
        } else {
          console.log(data.status);
        }
      },
  });
}

//Get short and long country name
function getResultByType(results,type) {
  if(results) {
    for(var i = 0; i < results.address_components.length; i++) {
      var types = results.address_components[i].types;
      for(var typeIdx = 0; typeIdx < types.length; typeIdx++) {
        if(types[typeIdx] == type) {
          return {
              short_name: results.address_components[i].short_name,
              long_name: results.address_components[i].long_name
          };
        }
      }
    }
  } else {
    return undefined;
  }
}

function addLocationFromMap(){
  setCountryDefault();
}

function notify(text) {
  var notyOptions = jQuery.extend({}, notyDefaultOptions);
  notyOptions.text = text;
  notyOptions.type = 'alert';
  noty(notyOptions);
}

//Adding location level with locElements
function addLocLevel(locationName,locationId,locationIsList,$locationSelect,locationIsList) {
  console.log('addLocLevel');
  var $locationItem = $("#locationLevel-template").clone(true).removeAttr("id");
  $locationItem.find(".locLevelName").html(locationName);
  $locationItem.find("input.locationLevelId").val(locationId);
  $locationItem.find("input.locationLevelName").val(locationName);
  $locationItem.find("input.isList").val(locationIsList);
  $(".locationsDataTable > tbody:last-child").append($locationItem);
  $locationItem.show("slow");
  updateIndex();
  if(locationIsList == "true") {
    if(locationName == "Country") {
    } else {
// $locationItem.find(".allCountriesQuestion").show();
// $locationItem.find("span.question").html($("span.qCmvSites").text());
    }
    addCountryIntoLocLevel(locationId, $locationSelect, locationName);
  } else {
    addLocByCoordinates(locationId, $locationSelect, locationName);
  }
}

function addLocByCoordinates(locationId,$locationSelect,locationName) {
  var countryName = "";
  var $list =
      $(".locationsDataTable").find("input.locationLevelId[value='" + locationId + "']").parent().find(
          ".optionSelect-content");
  var $item = $('#location-template').clone(true).removeAttr("id");
  countID++;
  var latitude = $("#inputFormWrapper").find(".latitude").val();
  var longitude = $("#inputFormWrapper").find(".longitude").val();
  console.log(latitude);
  console.log(longitude);
  var name = $("#inputFormWrapper").find("input.name").val();
  // Ajax for country name
  $.ajax({
      'url': 'https://maps.googleapis.com/maps/api/geocode/json',
      'data': {
          key: GOOGLE_API_KEY,
          latlng: (latitude + "," + longitude)
      },
      success: function(data) {
        if(data.status == 'OK') {
          $item.find('input.locElementCountry').val(getResultByType(data.results[0], 'country').short_name);
        } else {
          console.log(data.status);
        }
      },
      complete: function(data) {
        $item.attr("id", "location-" + (countID));
        $item.find('.locationName').html(
            '<span class="lName">' + name + '</span><span class="lPos"> (' + parseFloat(latitude).toFixed(4) + ', '
                + parseFloat(longitude).toFixed(4) + ' )</span> ');
        $item.find('.geoLatitude').val(latitude);
        $item.find('.geoLongitude').val(longitude);
        $item.find('.locElementName').val(name);
        $item.find('.locElementId').val(-1);
        $list.append($item);
        $item.show('slow');
        // add marker
        //addMarker(map, (countID), parseFloat(latitude), parseFloat(longitude), name, "false", 1);
        // update indexes
        updateIndex();
        checkItems($list.parents("#selectsContent"));
      }
  });
}

function updateIndex() {
  $('.locationsDataTable ').find('.locationLevel').each(function(i,e) {
    $(e).setNameIndexes(1, i);
    $.each($(e).find(".locElement"), function(index,element) {
      $(element).setNameIndexes(2, index);
    });
  });
  // Update component event
  $(document).trigger('updateComponent');
}

//Adding locElement into location level(Country and CSVS)
function addCountryIntoLocLevel(locationId,$locationSelect,locationName) {
  var locationContent =
      $(".locationsDataTable").find("input.locationLevelId[value='" + locationId + "']").parent().find(
          ".optionSelect-content");
  $.each($locationSelect.val(), function(i,e) {
    var $item = $("#location-template").clone(true).removeAttr("id");
    var locId = e.split("-")[0];
    var locIso = e.split("-")[1];
    var locName = e.split("-")[2];
    // Check if the item doesn't exists into the list
    if(locationContent.find("input.locElementId[value='" + locId + "']").exists()) {
      notify(locName + " already exists into the " + locationContent.parent().parent().find(".locationLevelName").val()
          + " list")
    } else {
      console.log('doesnt exist');
      /* GET COORDINATES */
      var url = baseURL + "/geopositionByElement.do";
      var data = {
          "locElementID": locId,
          phaseID: phaseID
      };
      countID++;
      $.ajax({
          url: url,
          type: 'GET',
          dataType: "json",
          data: data
      }).done(function(m) {

        if(m.geopositions.length != 0) {
          latitude = m.geopositions[0].latitude;
          longitude = m.geopositions[0].longitude;
          $item.find('.geoLatitude').val(latitude);
          $item.find('.geoLongitude').val(longitude);
          //addMarker(map, (countID), parseFloat(latitude), parseFloat(longitude), locName, "true", 2);
          var latLng = new google.maps.LatLng(latitude, longitude);
          map.setCenter(latLng);
        }
      });
      $item.attr("id", "location-" + (countID));
      $item.find(".lName").html(locName);
      $item.find(".locElementName").val(locName);
      $item.find(".locElementId").val(locId);

      // If is a country
      if(locationName == "Country") {
        countries.push(locIso);
        $item.find(".locElementCountry").val(locIso);
      }
      locationContent.append($item);
      $item.show("slow");
    }
  });
  updateIndex();
  checkItems(locationContent.parents("#selectsContent"));
  //infoWindow.close();
  if(locationName == "Country") {
    layer.setMap(null);
    mappingCountries();
  }

  $("#close-modal-button").click();
/*  $locationSelect.select2("destroy");
  $locationSelect.html("<option></option>");
  $locationSelect.select2();*/
}

function checkItems(block) {
  var items = $(block).find('.locElement').length;
  if(items == 0) {
    $(block).find('p.inf').fadeIn();
  } else {
    $(block).find('p.inf').fadeOut();
  }
}