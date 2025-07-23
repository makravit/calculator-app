# Calculator Application


> ⚠️ **DISCLAIMER — PLEASE READ!**
>
> **This entire application was generated using GitHub Copilot in agent mode, without writing a single line of code manually and without any prior experience with Java Swing.**
> 
> The code, UI, logic, and tests were all created through natural language instructions and iterative refinement with Copilot. This project demonstrates the power and capabilities of AI-assisted software development.

This is a simple calculator application built using Java Swing. The application provides a graphical user interface (GUI) for performing basic arithmetic operations such as addition, subtraction, multiplication, and division.

## Project Structure


```
calculator-app
├── src
│   ├── main
│   │   └── java
│   │       └── app
│   │           ├── CalculatorApp.java       # Entry point of the application
│   │           ├── ui
│   │           │   ├── CalculatorUI.java    # User interface components
│   │           │   └── CalculatorPresenter.java # Presenter for UI logic
│   │           └── logic
│   │               └── CalculatorLogic.java # Logic for performing calculations
│   └── test
│       └── java
│           └── app
│               ├── logic
│               │   └── CalculatorLogicTest.java # Unit tests for logic
│               └── ui
│                   ├── CalculatorPresenterTest.java # Unit tests for presenter
│                   └── integration
│                       └── CalculatorUIIntegrationTest.java # Integration/UI tests
├── README.md                     # Project documentation
├── pom.xml                       # Maven configuration file
```

## Requirements

- Java Development Kit (JDK) 8 or higher
- Apache Maven

## Building the Project

To build the project, navigate to the project directory and run the following command:

```
mvn clean install
```

## Running the Application

After building the project, you can run the application using the following command:

```
mvn exec:java -Dexec.mainClass="CalculatorApp"
```

## Features

- Basic arithmetic operations: addition, subtraction, multiplication, and division.
- User-friendly interface with buttons for each operation.
- Display area to show results.

## License

This project is licensed under the MIT License.