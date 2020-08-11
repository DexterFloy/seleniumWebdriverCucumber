Feature: Log i/out - Smoke Test

Scenario: Startup
	Given I open the browser
	
Scenario: Login
	Given I Navigate To "targetSite" Page
	When I Insert "username" In The "email" Area
	And I Hit The "enter" Keyboard Key On "email" Area
	And I Insert "password" In The "password" Area
	And I Hit The "enter" Keyboard Key On "password" Area
	Then I Verify That "Inbox" - "mailTab" Is Loaded
	
Scenario: Logout
	When I Click The "User" Button
	When I Click The "Sign out" Button
	Then I Verify That "targetSiteEnd" Page Is Loaded

Scenario: Teardown
	Given I close the browser