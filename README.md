Application to generate unique passwords.
User as a request body is providing: chars sequences generator will use, quantity passwords to generate and min and max length.
Application is checking if from user input is possible to build requested quantity od passwords and if not it throws an exception.
In default settings passwords are kept in memory but in application.properties there are commented settings to use database.
User can generate simultanously few requests (application is using JobRunr framework).
Application is tested by integration tests and unit tests.
