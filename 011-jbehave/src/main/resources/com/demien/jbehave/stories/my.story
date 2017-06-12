
Scenario: A number can be added

Given an empty list
When I add number 123
Then size becomes 1 and the list contains 123

Scenario: A number can be deleted
Given a no-empty list
When I remove number 123
Then size becomes 0

