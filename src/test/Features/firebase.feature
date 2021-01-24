Feature: Test firebase app
  Scenario: Create and Delete To-do List
    Given User launch Chrome browser
    When Go to URL 'https://todo-list-login.firebaseapp.com/'
    And Click 'Sign In with github'
    And Sign in by Github account with Username 'testfirebaseapp123' and Password '321ppaesaberiftset'
    And Create 10 to do list with random strings
    Then Verify total to do list is 10
    And Sign out
    And Click 'Sign In with github'
    And Delete User list from 5-10
    Then Verify total to do list is 4
    And Sign out
    Then Quit browser

