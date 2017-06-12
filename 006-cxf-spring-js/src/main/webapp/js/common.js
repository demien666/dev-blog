var BASE_URL="http://localhost:8080/spring-cxf-rest/services/rest/";
// entity
var ENTITY_REGION="region";
var ENTITY_COUNTRY="country";
//services 
var SERVICE_ENTITY_LIST="entitylist";
var SERVICE_ADD="add";
var SERVICE_UPDATE="update";
var SERVICE_DELETE="delete";
//view modes (for table view:view|select)
var VIEW_MODE_VIEW="view";
var VIEW_MODE_SELECT="select";
// actions
var ACTION_ADD="ADD";
var ACTION_UPDATE="UPDATE";
var ACTION_DELETE="DELETE";
// field types
var FIELD_TYPE_STRING="STRING";
//----------------------
//global structures
//----------------------
// field list
var REGION_FIELD_LIST=[ {fieldName: "id", fieldType:FIELD_TYPE_STRING}, {fieldName: "regionName", fieldType:FIELD_TYPE_STRING}];
// field map
var FIELD_MAP=new Object();
FIELD_MAP[ENTITY_REGION]=REGION_FIELD_LIST;
// URL map
var URL_MAP=new Object();
URL_MAP[ENTITY_REGION]=BASE_URL+"regionService/";
// ---------------------------
// common functions
//----------------------------
function getServiceUrlByEntityName(entityName) {
	return URL_MAP[entityName.toString()];
}

function getFieldListByEntityName(entityName) {
	return FIELD_MAP[entityName];
}

function processErrorResult(xhr, statusText) {
	alert("Error: " + statusText + " xhr:" + xhr);
}

function loadDataSuccess(data) {
	alert("Operation " + currentAction + " completed successfully.");
	//var div=document.getElementById("content");
	//div.innerHTML=data;   	   
}

function sentPostRequest(URL, json, successFunction) {
	$.ajax({
		type : "POST",
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		data : json,
		url : URL,
		cache : false,
		error : processErrorResult,
		dataType : 'json',
		success : successFunction
	});
}
