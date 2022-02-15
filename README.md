# 🚀 What is Smart E-Health Consulting System?

Smart E-Health Consulting System is a program that aims to provide its users the best possible outcome regarding their
health. People should be able to find the most suitable medical doctor regarding their specific health issues.

# ⚙️ How To Install the Project

## 🛠️ Requirements

### 1. Java JDK

- **Amazon Corretto 11** version
- To check your version type this in the console
    ```programming
    java -version
    ```

### 2. Git Setup

- **Git** or download it [here](https://git-scm.com/downloads). Make sure to set up your <span style="color:green">
  .gitconfig</span> and optionally <span style="color:green">.git-credentials</span>.

- If you are a **Windows** user please make sure **WSL** is enabled and configured. If not
  [here](https://docs.microsoft.com/de-de/windows/wsl/install-win10) is a detailed guide from Microsoft on how to set up
  WSL. Also make sure that **git** is installed and set up (<span style="color:green">.gitconfig and
  .git-credentials</span>) in WSL.

- IntelliJ IDEA or download it [here](https://www.jetbrains.com/de-de/idea/download/#section=windows)

### 2. Docker Setup

- Here you can install [Docker](https://docs.docker.com/get-docker/)
- After executing the program, you have to setup your database
- Check if any other **process** is running on **Port 3306**. You can do this on Linux with:
    ```programming
    sudo lsof -i:3306
    ```
  -If you see there a process running you can kill it with:
  ```programming
   sudo kill PID
    ```

- If everything is fine start docker and enter the following configuration in your terminal:

```
docker run -p 3306:3306 --name ehealth -e MYSQL_ROOT_PASSWORD=ehealth -e MYSQL_DATABASE=ehealth -e MYSQL_USER=ehealth -e MYSQL_PASSWORD=ehealth -d mysql:5.6
```

- Finally, go to the already opened docker programm and hit the start button

## Setup Java Project in Intellij IDEA

### 1. Clone the git repository

To clone the Java application type this in your console. If you are a **Windwos** user use **cmd**

```programming
gh repo clone ayhancolakoglu/ehealth_g23
```

### 2. Run IntelliJ

- Open the project in IntelliJ
- Trust maven project
- Then make sure that the right **Java SDK** is selected. For that go to **File -> Project Structure -> Project**

### 3. Setup Database in IntelliJ

- On the very right, you will find the view of the database inside your IDE
- upon clicking **database**
- the previously configurated localhost presents itself
- right click **localhost** and select **properties**
- Now install all drivers which are proposed by the IDE on order to run the project
- Now a window opens where you set the username and password to: **ehealth**. After "Test Connection" succeeds confirm
  with "Okay".

### 4. Execute doctor.sql

## Run Application

- MydeskApplication
- (start main)

````
@SpringBootApplication
public class MydeskApplication {

    public static void main(String[] args) {
        Application.launch(FxApplication.class, args);
    }

}
````

## Have fun with our application, we hope you enjoy our application 🚀🚀🚀



