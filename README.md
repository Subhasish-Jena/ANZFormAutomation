# ANZFormAutomation

#Project Dependencies

  Java 1.8
  
  Selenium Webdriver
  
#Tools to use 
 
 Preferably use IntelliJ
  
#How to run

  a. Download the maven depencies
  
  b. Navigate to the project directory on Terminal/Command prompt
  
  c. Execute in order
      mvn clean -> mvn compile -> mvn verify
  
  d. Also, one can run the tests directly by navigating to 'src/test/java/RunCucumberTest', right click and select Run option
  
  e. The report of execution can be found in 'target/Reports/cucumber.json'


#Note
If the tests are being run on Mac for the first time below command has to be executed on the Project directory

  xattr -d com.apple.quarantine chromedriver 
