package com.demien.ssecurity.testapp.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dmitry on 28.09.14.
 */

@Entity(name = "TOPIC")
public class Topic extends AbstractModel<Topic, Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TOPIC_ID")
    private Long topicId;

    @Column(name = "TOPIC_NAME")
    private String topicName;

    public Topic() {
        super(Topic.class);
    }

    public Topic(String topicName) {
        this();
        this.topicName = topicName;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    @Override
    public String toString() {
        return "Topic [topicId=" + topicId + ", topicName=" + topicName + "]";
    }


}
