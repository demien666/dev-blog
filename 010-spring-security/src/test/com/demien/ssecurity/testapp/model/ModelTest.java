package com.demien.ssecurity.testapp.model;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demien.ssecurity.testapp.model.Post;
import com.demien.ssecurity.testapp.model.Topic;
import com.demien.ssecurity.testapp.model.User;

import java.util.List;

/**
 * Created by dmitry on 28.09.14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:ApplicationContext.xml"})
public class ModelTest {

    @Test
    public void UserTest() {
        User user = new User("login", "password");
        user.deleteAll();
        int cnt = user.getAll().size();
        Assert.assertEquals(0, cnt);
        Long id = user.save();
        List<User> users = user.getAll();
        cnt = users.size();
        Assert.assertEquals(1, cnt);
        Assert.assertTrue(users.contains(user));
        User user2 = new User();
        user2 = user2.get(id);
        Assert.assertEquals("login", user2.getLogin());
    }

    @Test
    public void TopicTest() {
        Topic topic = new Topic("test1");
        topic.deleteAll();
        topic.save();
        List<Topic> topics = topic.getAll();
        int cnt = topics.size();
        Assert.assertEquals(1, cnt);
        Assert.assertTrue(topics.contains(topic));
    }
    
    @Test
    public void PostTest() {
    	Topic topic = new Topic("test1");    	
    	topic.deleteAll();
        Long topicId = topic.save();

        User user=new User("Joe", "Black");
    	user.deleteAll();
    	user.save();
    	
    	Post post1=new Post();
        post1.setTopicId(topicId);
        post1.setPostUser(user);
    	post1.setPostMessage("hello world");    	
    	post1.save();
    	
    	Post post2=new Post();
        post2.setTopicId(topicId);
        post2.setPostUser(user);
    	post2.setPostMessage("Putin-Huilo!");    	
    	post2.save();
    	
    	List<Post> posts=post1.getAll();
    	Assert.assertEquals(2, posts.size());
    	Assert.assertTrue(posts.contains(post1));
        Assert.assertTrue(posts.contains(post2));

    }

}
