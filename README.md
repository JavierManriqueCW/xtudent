<img src="https://github.com/user-attachments/assets/fc6677c2-f211-44a0-ba68-1d76fba80035" alt="xtudent_icon" width="90" height="90"/>

# Xtudent

Native android application that transforms exam `.xlsx` files into user-friendly study interfaces. Built with **Kotlin** and **Jetpack Compose** with a focus in best practices, clean architecture, and robust testing to ensure a stable and scalable product.

## Table of Contents

- [Features](#features)
- [Architecture](#architecture)
- [Project details](#project-details)
- [How does it work?](#how-does-it-work)
- [Look & feel](#look-and-feel)

## Features

- **Multi-Module Project Structure**: Organized codebase for scalability and maintainability.
- **Jetpack Compose UI**: Modern, declarative UI toolkit for responsive and intuitive interfaces.
- **Model-View-Intent (MVI) Pattern**: Predictable state management for consistent user experiences.
- **Clean Architecture**: Separation of concerns for easier testing and feature enhancements.
- **Unit Testing**: Comprehensive testing suites written with the purpose of preserving robust logics over time.
- **UI Testing (screenshot testing)**: User action flows covered with screenshot tests to ensure a stable user experience after continuous development
- **GitHub Actions Integration**: Automated unit and ui testing, code coverage reports using JaCoCo and code sanity checks with Detekt.

## Architecture

The app follows the **Clean Architecture** paradigm, integrating the **MVI** pattern in the presentation layer.

- **Presentation Layer**: Handles UI rendering with Jetpack Compose and state management with a single source of truth, as part of the MVI design pattern integration.
- **Domain Layer**: Contains business logic and use cases.
- **Data Layer**: Manages data sources, including Excel file parsing and data repositories, database and web service communications.

## Project details

- **JDK 17**
- **Min version** 26
- **Target version** 35
- **SDK version** 35
- **Kotlin version** 1.9.25
- **Gradle version** 8.6.1 

## How does it work?

Just install the app, navigate to the exam creation screen and provide an xlsx file [like this one](https://github.com/JavierManriqueCW/examinr/files/13759825/solar-system-exam.right-format.xlsx) following this specific format:

- The first row must be the exam name.
- The second row must be the exam description.
- The third row must be the image described with a string like one of these.
    - NIGHT_SKY
    - SPACE_ROCKET
    - BURGER
    - BABY_BUDGIE
    - FOREST
    - BEACH
    - VIDEO_GAME
    - BOOKS
    - CUPCAKE
    - FIRST_ROBOT
    - SECOND_ROBOT
    - CAR
    - CUPCAKE_BRIGHT
    - MIC
    - CANDY
- All subsequent rows will be questions, where the first row will be the question itself, the second row the right answer, and all the other rows the wrong answers.


## Look & feel

https://github.com/user-attachments/assets/94528d36-b63d-469e-822e-e3051e9eb1c0



### Splash screen

<img src="https://github.com/user-attachments/assets/15c782a8-7d3b-41df-ba69-06c5eb75d176" width="200" height="420" />

### Onboarding steps

<img src="https://github.com/user-attachments/assets/82ef04e5-1ec3-43f1-acc5-732534ab6d6b" width="200" height="420" />

<img src="https://github.com/user-attachments/assets/87a29a34-7cf9-44a9-bdaa-008bc715783c" width="200" height="420" />

<img src="https://github.com/user-attachments/assets/8b36ab22-f733-4f9f-93be-d57500b117b2" width="200" height="420" />

### Empty exam list

<img src="https://github.com/user-attachments/assets/a1246f00-1054-47fd-a009-c1b679d177b9" width="200" height="420" />

### Exam creation (one element)

<img src="https://github.com/user-attachments/assets/250e182b-17fb-4697-b59b-3a73ab252e31" width="200" height="420" />

### Exam list (one element)

<img src="https://github.com/user-attachments/assets/a86afff8-85f2-4a30-a69f-c27ddd99276e" width="200" height="420" />

### Exam detail

<img src="https://github.com/user-attachments/assets/8598bcc9-032b-4198-9351-7411ce47e87f" width="200" height="420" />

