public class Course {
    private String courseCode;
    private String courseName;
    private int numberOfCredits;
    private int courseCapacity;
    private int numberOfStudents;
    private Faculty faculty;


    public Course(String courseCode, String courseName, int numberOfCredits, int courseCapacity, Faculty faculty) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.numberOfCredits = numberOfCredits;
        this.courseCapacity = courseCapacity;
        this.faculty = faculty;
        this.numberOfStudents = 0; // Initialize the number of students to 0
    }

    // Getters and setters for all attributes

    public void incrementNumberOfStudents() {
        numberOfStudents++;
    }

    public void decrementNumberOfStudents() {
        numberOfStudents--;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getNumberOfCredits() {
        return numberOfCredits;
    }

    public void setNumberOfCredits(int numberOfCredits) {
        this.numberOfCredits = numberOfCredits;
    }

    public int getCourseCapacity() {
        return courseCapacity;
    }

    public void setCourseCapacity(int courseCapacity) {
        this.courseCapacity = courseCapacity;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setNumberOfStudents(int numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    @Override
    public String toString() {
        return "Course information:" +
                "\n--------------------------"+
                "\ncourseCode='" + courseCode +
                "\ncourseName='" + courseName +
                "\nnumberOfCredits=" + numberOfCredits +
                "\ncourseCapacity=" + courseCapacity +
                "\nnumberOfStudents=" + numberOfStudents +
                "\n" + faculty;
    }
}
