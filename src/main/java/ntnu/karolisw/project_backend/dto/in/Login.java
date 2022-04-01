package ntnu.karolisw.project_backend.dto.in;

public class Login {
    private String email;
    private String password;
    private int typeOfUser;
    private long personId;

    // GETTERS

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getTypeOfUser() {
        return typeOfUser;
    }

    public long getPersonId() {
        return personId;
    }

    // SETTERS

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTypeOfUser(int typeOfUser) {
        this.typeOfUser = typeOfUser;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }
}
