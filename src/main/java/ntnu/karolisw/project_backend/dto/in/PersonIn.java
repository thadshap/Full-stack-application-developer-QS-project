package ntnu.karolisw.project_backend.dto.in;

public class PersonIn {
    private Long courseId;
    private long personId;
    private String email;

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
}
