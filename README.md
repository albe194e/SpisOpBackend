# Spis op backend
This is the backend for the Spis Op project. It is built using Spring Boot and Java.

## Installation
To run the project you need to have Java 22 installed on your machine.

## Running the project
To run the project on http://localhost:8080 run by pressing the play button in your IDE or run the following command:
```mvn spring-boot:run```

## Clean-seeding the local database
Before clean seeding make sure your application is not running.
To clean-seed the local database run the following command:
```mvn spring-boot:run -Dspring-boot.run.arguments=clean-seed```

After running the command, and it says "Database has been reset and seeded" then stop script by pressing `ctrl + c` and run the project again.

## Testing the project
Testing the project is done through Postman. You can find the Postman collection in the `postman` folder.

Testing is also done through Pull Requests. When you create a Pull Request, the GitHub Actions will run the tests and check if the code is formatted correctly.

## Git commands
To creating a new branch run the following command:
```git checkout -b "branch-name"```

To add all files to the staging area run the following command:
```git add .```

To commit the files in the staging area run the following command:
```git commit -m "Your commit message"```

To push the committed files to the remote repository run the following command:
```git push```

To pull the latest changes from the remote repository run the following command:
```git pull```

To switch to a different branch or master branch run the following command:
```git checkout "branch-name"``` or ```git checkout master```
