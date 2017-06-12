Scenario: broadcast message receiving by all subscribers of topic
Given one publisher, several subscribers, one message
When publisher sends message
Then all subscribers have to receive this message


