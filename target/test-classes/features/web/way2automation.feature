Feature: Launch webTable application to add users

  Scenario Outline: Add user into webTable application
    Given User launch webtable browser "<browser>"
    Then Validate you are on the user list table
    And Click add user button
    And Add users into the table "<companyName>" company "<role>" role
      | firstName | lastName | userName | password | email         | mobilePhone |
      | Vinay     | Rama     | ewer     | 4321     | test@test.com | 0899999222  |
      | Zara      | Good     | deed     | 4322     | test@dsd.com  | 0899999111  |
    And Confirm a user is added to the list

    Examples:
      | companyName | role       | browser |
      | Company AAA | Sales Team | chrome  |
      | Company BBB | Customer   | chrome  |

