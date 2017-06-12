function createTestData() {
	var region1=new Object();
	region1.id="1";
	region1.regionName="Africa";

    var region2=new Object();
	region2.id="2";
	region2.regionName="America";
		
	var URL = URL_MAP["region"]+"add";
	var json = JSON.stringify(region1);
	sentPostRequest(URL, json, dummy);
	json = JSON.stringify(region2);
	sentPostRequest(URL, json, dummy);
}

function dummy() {
	
}