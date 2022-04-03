package ntnu.karolisw.project_backend.dto.in;

public class PersonIn {
    private Long courseId;
    private long personId;
    private String firstName;
    private String lastName;
    private String email;
    private int typeOfUser;
    private String pronouns;

    // GETTERS

    public Long getCourseId() {
        return courseId;
    }

    public long getPersonId() {
        return personId;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getTypeOfUser() {
        return typeOfUser;
    }

    public String getPronouns() {
        return pronouns;
    }

    // SETTERS

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setTypeOfUser(int typeOfUser) {
        this.typeOfUser = typeOfUser;
    }

    public void setPronouns(String pronouns) {
        this.pronouns = pronouns;
    }
}
