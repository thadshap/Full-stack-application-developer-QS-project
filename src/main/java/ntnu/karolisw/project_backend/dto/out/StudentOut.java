package ntnu.karolisw.project_backend.dto.out;

public class StudentOut {
    private long studentId;
    private String firstName;
    private String lastName;
    private String email;
    private int approvedAssignmentsInCourse;
    private boolean digital;

    // GETTERS

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public int getApprovedAssignmentsInCourse() {
        return approvedAssignmentsInCourse;
    }


    // SETTERS

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setApprovedAssignmentsInCourse(int approvedAssignmentsInCourse) {
        this.approvedAssignmentsInCourse = approvedAssignmentsInCourse;
    }
}
