Scenario: simple message receiving by one consumer
Given one producer, one consumer, one message
When producer sends message to queue
Then consumer receives message from queue

Scenario: simple message receiving by several consumers:only one of them have to receive message
Given one producer, several consumers, one message
When producer sends message to queue
Then only one consumer have to receive message from queue
