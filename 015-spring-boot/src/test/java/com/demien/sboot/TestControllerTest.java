package com.demien.sboot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class TestControllerTest {

    private MockMvc mockMvc;

    @Autowired
    TestRepository repository;


    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream().filter(
                hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();

        Assert.assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Autowired
    private WebApplicationContext webApplicationContext;

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

    @Before
    public void init()  {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void helloTest() throws Exception {
        mockMvc.perform(get("/entity/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, world!"));
    }

    @Test
    public void addTest() throws Exception {
        TestEntity entity=new TestEntity();
        entity.setEntityName("Test");

        mockMvc.perform(post("/entity/add/")
                .content(this.json(entity))
                .contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.entityId", is(repository.getMaxId())))
                .andExpect(jsonPath("$.entityName", is(entity.getEntityName())))
        ;
    }

    @Test
    public void getTest() throws Exception {
        TestEntity entity=new TestEntity();
        entity.setEntityName("TestGet");
        entity=repository.add(entity);

        mockMvc.perform(get("/entity/get/" + entity.getEntityId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.entityId", is(entity.getEntityId())))
                .andExpect(jsonPath("$.entityName", is(entity.getEntityName())))
        ;

    }

    @Test
    public void getAllTest() throws Exception {
        TestEntity entity=new TestEntity();
        entity.setEntityName("TestGetAll");
        entity=repository.add(entity);

        mockMvc.perform(get("/entity/getall"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(repository.getAll().size())))
        ;

    }
}
