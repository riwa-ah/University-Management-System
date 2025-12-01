# University-Management-System
A desktop-based University Management System built using Java, and OOP. The system provides an easy-to-use graphical interface for managing students, courses, and faculties, with structured data handling and table-based visualization.

Features
Student Management
Add new students through a dialog form
Store full academic details (name, ID, phone, major, etc.)
Display all students in a clean table format
Automatically refresh the student list after adding a student

Course Management
Add new courses
Display course details including:
Course Code
Course Name
Credits
Capacity
Registered Students Count
Assigned Faculty
Search for a course by course code

Faculty Management
Add new faculties
Display faculty information:
Faculty Name
Dean Name
Faculty Location
Modify faculty details
Search for a faculty

GUI
The interface has been upgraded to a clean, modern design with:
Tabbed navigation (Students / Courses / Faculty)
JTable-based layouts for professional data display
Rounded buttons and enhanced spacing
Consistent teal-white theme for a modern experience


Project Structure
├── Driver.java          # Main GUI and program launcher
├── Student.java         # Student model class
├── Course.java          # Course model class
├── Faculty.java         # Faculty model class
└── README.md            # Project documentation

How to Run the Project
Install JDK 17 (or later)
Open project in Visual Studio Code or IntelliJ
Make sure your Java extension pack is installed
Run Driver.java
