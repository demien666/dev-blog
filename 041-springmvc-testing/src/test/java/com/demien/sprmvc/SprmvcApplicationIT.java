package com.demien.sprmvc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.demien.sprmvc.domain.Greeting;
import com.demien.sprmvc.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SprmvcApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class SprmvcApplicationIT {

	private static final String LOCAL_HOST = "http://localhost:";

	@LocalServerPort
	private int port;
	private TestRestTemplate template = new TestRestTemplate();

	@Test
	public void helloTest() throws Exception {
		ResponseEntity<String> response = template.getForEntity(createURL("/hello"), String.class);
		assertThat(response.getBody(), equalTo("Hello world!"));
	}

	private String createURL(String uri) {
		return LOCAL_HOST + port + uri;
	}

	@Test
	public void helloWithObjectTest() throws Exception {
		ResponseEntity<String> response = template.getForEntity(createURL("/hello-with-object"), String.class);
		assertThat(response.getBody(), containsString("Hello World"));
	}

	@Test
	public void helloWithParameterTest() throws Exception {
		ResponseEntity<String> response = template.getForEntity(createURL("/hello-with-parameter/name/Buddy"),
				String.class);
		assertThat(response.getBody(), containsString("Hello World, Buddy"));
	}

	@Test
	public void postTest() throws Exception {
		User userBean = new User("Joe");
		ResponseEntity<Greeting> response = template.postForEntity(createURL("/hello-post"), userBean, Greeting.class);
		Greeting result = response.getBody();
		assertThat(result.getMessage(), containsString("Hello World, Joe"));
	}

}
