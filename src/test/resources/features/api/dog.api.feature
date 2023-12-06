Feature: API testing request to retrieve dog breeds

  Scenario Outline: Perform an api request to produce a list of all dog breeds
    Then verify response code
    And  Verify a list of all dog breeds is displayed "<breed>"
    Examples:
      | breed     |
      | retriever |

  Scenario: Verify a list of retriever sub breeds is returned
    Then verify response code
    And confirm a list of retriever sub breeds

  Scenario: Verify a random image is returned from golden breed
    Then verify response code
    And  verify a random image is returned from golden breed
