Feature: Cucumber spark testing

  Scenario: should generate and process data by [InputToOutputMapper]
    Given input data:
      | id | name |
      | 1  | Joe  |
      | 2  | Moe  |
    When date were processed by 'InputToOutputMapper' at '2021-09-06'
    Then output should be:
      | header.processingStatus | header.processingDate | body.sourceDataId | body.sourceDataValue | body.sourceDataCategory |
      | OK                      | 2021-09-06            | 1                 | JOE                  | DEFAULT                 |
      | OK                      | 2021-09-06            | 2                 | MOE                  | DEFAULT                 |

