SUT:  https://github.com/Brioal/Game
 Instruction to run the tests:
Build the project: If there are issues with the font, please change the encoding to GBK or UTF-8 or Build it twice.
To run the game itself, run com.brioal.test.test
To run all our tests, go to each test and run, comments and fault reports are provided at the beginning of each test class
Reports are generated in build -> jacoco -> html -> index.html, build -> reports -> pittest for mutation testing reports.
Notes:
We performed white box and black box testing (both unit and integration) on com.brioal.utool.*.
To test com.brioal.socket, we setup a new connection and mimic the behavior done in the Client/Server classes.
We performed GUI testing for panel/frame/override_view classes.
We performed mutation testing on CalculatePitTest class but did not achieve high mutation score and reasons are provided at the beginning of test ->tests -> CalculatePitTest class.
We were not able to perform mock testing because of socket connection issues, we werent able to mock the connection and initialize objects to run mock testing
Screenshots of successfully running the tests is under test_screenshots folder
A demo of us running GUI testing is also inside the folder