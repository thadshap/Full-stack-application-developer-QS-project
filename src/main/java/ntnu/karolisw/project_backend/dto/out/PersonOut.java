package ntnu.karolisw.project_backend.dto.out;

public class PersonOut {
    private long personId;
    private String firstName;
    private String lastName;
    private String email;
    private int approvedAssignmentsInCourse;
    private boolean digital;
    private boolean loggedIn;

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

    public long getPersonId() {
        return personId;
    }

    public boolean isDigital() {
        return digital;
    }

    public boolean isLoggedIn() {
        return loggedIn;
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

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public void setDigital(boolean digital) {
        this.digital = digital;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
