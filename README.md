# 🚀 What is Smart E-Health Consulting System?

Smart E-Health Consulting System is a program that aims to provide its users the best possible outcome regarding their
health. People should be able to find the most suitable medical doctor regarding their specific health issues.

# ⚙️ How To Install the Project

## 🛠️ Requirements

- **Amazon Corretto 11** version

- To check your version type this in the console
    ```programming
    java -version
    ```


- **Git** or download it [here](https://git-scm.com/downloads). Make sure to set up your <span style="color:green">
  .gitconfig</span> and optionally <span style="color:green">.git-credentials</span>.

- If you are a **Windows** user please make sure **WSL** is enabled and configured. If not
  [here](https://docs.microsoft.com/de-de/windows/wsl/install-win10) is a detailed guide from Microsoft on how to set up
  WSL. Also make sure that **git** is installed and set up (<span style="color:green">.gitconfig and
  .git-credentials</span>) in WSL.

- IntelliJ IDEA or download it [here](https://www.jetbrains.com/de-de/idea/download/#section=windows)

## Setup Java Project in Intellij IDEA

### 1. Clone the git repository

To clone the Java application type this in your console. If you are a **Windwos** user use **cmd**

```programming
git clone **
```

### 2. Run IntelliJ

- Open the project in IntelliJ
- Then make sure that the right **Java SDK** is selected. For that go to **File -> Project Structure -> Project**
- Then link the **libraries**. For that go to:  **File -> Project Structure -> Libraries** Then click on the **+** icon
  and choose Java.

For JavaFx **/lib/javafx-sdk-16/lib**

For Json-Simple **/lib/json-simple/json-simple-1.1.1.jar**

### 3. Add Build Configuration

Next you have to set up the build configuration. Go to the top right corner and click on **Add Configuration**

Click on the **+** icon in the top left corner and then select **Application**

Click on **Modify options** and enable the VM options

and enter the following configuration in the VM options and apply.

```
--module-path your\path\to\campuas\lib\javafx-sdk-16\lib --add-modules javafx.controls,javafx.fxml
```

Now you are ready to execute the project 😁

## Docker Setup

- Here you can install [Docker](https://docs.docker.com/get-docker/)
- After executing the program, you have to setup your database
- start docker and enter the following configuration in your terminal:

```
docker run -p 3306:3306 --name ehealth -e MYSQL_ROOT_PASSWORD=ehealth -e MYSQL_DATABASE=ehealth -e MYSQL_USER=ehealth -e MYSQL_PASSWORD=ehealth -d mysql:5.6
```

- Finally, go to the already opened docker programm and hit the start button

## Setup Database in IntelliJ

- On the very right, you will find the view of the database inside your IDE
- upon clicking **database**
- the previously configurated localhost presents itself
- right click **localhost** and select **properties**
- Now a window opens in which you set the password to: **eHealth**. After "Test Connection" succeeds confirm with "Okay"
  .

