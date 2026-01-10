public class Faculty {
    private String facultyName;
    private String deanName;
    private String facultyLocation;

    public Faculty(String facultyName, String deanName, String facultyLocation) {
        this.facultyName = facultyName;
        this.deanName = deanName;
        this.facultyLocation = facultyLocation;
    }

    // Getters and setters for all attributes
    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getDeanName() {
        return deanName;
    }

    public void setDeanName(String deanName) {
        this.deanName = deanName;
    }

    public String getFacultyLocation() {
        return facultyLocation;
    }

    public void setFacultyLocation(String facultyLocation){
        this.facultyLocation = facultyLocation;
    }

    @Override
    public String toString() {
        return "Faculty information:" +
                "\n---------------------------"+
                "\nfacultyName='" + facultyName +
                "\ndeanName='" + deanName +
                "\nfacultyLocation='" + facultyLocation ;
    }
}
