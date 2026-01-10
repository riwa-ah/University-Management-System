import java.util.Arrays;
import java.util.Date;
import java.time.LocalDate;

public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String gender;
    private int age;
    private int remainingCredits;
    private int coursesDone;
    private String email;
    private String password;
    private static int count=0;

    // Constructor
    public Student(int id,String firstName, String lastName, String fatherName, String gender, int age, int remainingCredits,
                   int coursesDone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.gender = gender;
        this.age = age;
        this.remainingCredits = remainingCredits;
        this.coursesDone = coursesDone;
        this.id = generateId();
        this.email = generateEmail();
        this.password = generatePassword();
    }

    // Getters and setters for all attributes
    public static int generateId() {
        int start = 20230527 ;
        int id = start+count;
        count++;
        return id;
    }

    private String generateEmail() {
        // Generate the email based on the provided format
        String email = "";
        email += firstName.charAt(0);
        email += fatherName.charAt(0);
        email += lastName.charAt(0);
        int majorNumber = 1; // Assuming a default major number of 1
        int randomDigits = (int) (Math.random() * 900000) + 100000;
        email += majorNumber + String.valueOf(randomDigits)+"@students.aust.edu.lb";
        return email;
    }

    private String generatePassword() {
        // Generate the password by concatenating the name with "123"
        return firstName + "123";
    }
    public int getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getRemainingCredits() {
        return remainingCredits;
    }

    public void setRemainingCredits(int remainingCredits) {
        this.remainingCredits = remainingCredits;
    }

    public int getCoursesDone() {
        return coursesDone;
    }

    public void setCoursesDone(int coursesDone) {
        this.coursesDone = coursesDone;
    }

    @Override
    public String toString() {
        return  "Student information:" +
                "\n---------------------------"+
                "\nid=" + id +
                "\nfirstName='" + firstName + '\'' +
                "\nlastName='" + lastName + '\'' +
                "\nfatherName='" + fatherName + '\'' +
                "\ngender='" + gender + '\'' +
                "\nage=" + age +
                "\ncoursesDone=" + coursesDone +
                "\nremainingCredits=" + remainingCredits +
                "\nemail='" + email + '\'' +
                "\npassword='" + password + '\'';
    }
    public void listStudents(){
        System.out.println("AUST Students"+
                "\n--------------"+
                "First Name\tLast Name\tID");
    }
}
