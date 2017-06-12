package com.demien.services;

import com.demien.cxfspringrestjs.domain.Country;


public class CountryServiceTest extends BaseServiceTest<Country> {
	
	public CountryServiceTest() {
		super("countryService", Country.class);
	}
/*	
	public Country getTestCountry() {
		Country result=new Country();
		result.setCountryId("ZB");
		result.setCountryName("Zimbabve");
		return result;
	}
	
	@Test
	public void sayHelloTest() throws ClientProtocolException, IOException {

		String value=client.sendGetStringResult(COUNTRY_SERVICE_URL+"sayhello");
		System.out.println("value="+value);
		assertEquals(CountryService.HELLO, value );
	}
	
	@Test
	public void addCountryTest() throws Exception {
		List<NameValuePair> postParams=new ArrayList<NameValuePair>();
		Country testCountry=getTestCountry();
		postParams.add(new BasicNameValuePair("id", testCountry.getCountryId()));
		postParams.add(new BasicNameValuePair("name", testCountry.getCountryName()));
		HttpResponse response=client.sendPost(COUNTRY_SERVICE_URL+"add", postParams);
		int responseCode = response.getStatusLine().getStatusCode();
		assertEquals(RestTestClient.RESPONSE_OK, responseCode);
		//System.out.println("Response Code : " + responseCode);
		
		//String s=client.sendGetStringResult();
		//System.out.println(s);
		Country resultCountry=(Country)client.sendGetObjectResult(COUNTRY_SERVICE_URL+"countrybyid?id="+testCountry.getCountryId(), Country.class);
		assertNotNull(resultCountry);
		assertEquals(testCountry.getCountryId(), resultCountry.getCountryId());
		assertEquals(testCountry.getCountryName(), resultCountry.getCountryName());
	}
	
	@Test
	public void updateCountryTest() throws Exception {
		Country testCountry=getTestCountry();
		testCountry.setCountryName("Updated");
		HttpResponse response=client.sendPost(COUNTRY_SERVICE_URL+"update", testCountry);
		int responseCode = response.getStatusLine().getStatusCode();
		assertEquals(RestTestClient.RESPONSE_OK, responseCode);
		
		Country resultCountry=(Country)client.sendGetObjectResult(COUNTRY_SERVICE_URL+"countrybyid?id="+testCountry.getCountryId(), Country.class);
		assertNotNull(resultCountry);
		assertEquals(testCountry.getCountryId(), resultCountry.getCountryId());
		assertEquals(testCountry.getCountryName(), "Updated");
		
	}

	@Override
	public Country getTestEntity() {
		Country result=new Country();
		result.setCountryId("ZB");
		result.setCountryName("Zimbabve");
		return result;
	}
*/
}
