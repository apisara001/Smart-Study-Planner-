# Smart Study Planner

Smart Study Planner is a Java console-based application designed to help students organize academic and personal tasks in one place. The system allows users to add, manage, sort, and track different types of plans such as exams, quizzes, assignments, meetings, projects, presentations, and other tasks.

## Features

- Add and manage multiple task types:
  - Exam
  - Quiz
  - Assignment
  - Meeting
  - Project
  - Presentation
  - Other
- Sort tasks by deadline and priority
- Mark tasks as completed
- Remove tasks that are still in progress
- Clear finished tasks from the planner
- Optional course code support for academic tasks
- Custom reminders, urgency levels, and status messages for each task type

## Object-Oriented Concepts Used

- **Inheritance**: Specialized task classes extend base task classes
- **Polymorphism**: Different task types override methods such as priority, reminder message, and status
- **Encapsulation**: Task-related data is stored within classes and managed through methods
- **Abstraction**: Common task behaviors are defined in general parent classes

## Design Patterns Used

- **Factory Pattern**: `TaskFactory` creates the correct task object based on user input
- **Strategy Pattern**: `SortStrategy` and `SortByDeadline` define flexible sorting behavior
- **Observer Pattern**: `Observer` and `NotificationSystem` represent the notification feature structure

## Project Structure

- `Main.java` — runs the application and handles user interaction
- `Planner.java` — stores tasks and provides task management operations
- `Task.java` — base class for all tasks
- `AcademicTask.java` — parent class for academic-related tasks
- `EventTask.java` — parent class for event-related tasks
- `ExamTask.java`, `QuizTask.java`, `AssignmentTask.java`, `ProjectTask.java` — academic task types
- `MeetingTask.java`, `PresentationTask.java` — event task types
- `TaskFactory.java` — creates task objects
- `SortStrategy.java`, `SortByDeadline.java` — sorting logic
- `Observer.java`, `NotificationSystem.java` — notification-related classes

## How to Run

1. Compile the Java files
2. Run `Main.java`
3. Use the console menu to interact with the planner

## Purpose of the Project

This project was developed to demonstrate the practical application of Java Object-Oriented Programming principles and design patterns in a real-world student productivity tool.
