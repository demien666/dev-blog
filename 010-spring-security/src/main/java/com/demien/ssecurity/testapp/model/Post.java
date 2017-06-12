package com.demien.ssecurity.testapp.model;

import javax.persistence.*;

/**
 * Created by dmitry on 28.09.14.
 */

@Entity(name = "POST")
public class Post extends AbstractModel<Post, Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "POST_ID")
    private Long postId;

    @Column(name = "POST_NAME")
    private String postMessage;

    @Column(name = "TOPIC_ID")
    private Long topicId;

    @Column(name = "POST_USER")
    private User postUser;

    public Post() {
        super(Post.class);
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getPostMessage() {
        return postMessage;
    }

    public void setPostMessage(String postMessage) {
        this.postMessage = postMessage;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public User getPostUser() {
        return postUser;
    }

    public void setPostUser(User postUser) {
        this.postUser = postUser;
    }

    @Override
    public String toString() {
        return "Post [postId=" + postId + ", postMessage=" + postMessage
                + ", topicId=" + topicId + ", postUser=" + postUser + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Post)) {
            return false;
        }
        Post post = (Post) o;
        if (post.getPostId() != this.getPostId()) {
            return false;
        }
        return true;
    }


}
