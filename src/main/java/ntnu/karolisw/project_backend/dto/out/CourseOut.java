package ntnu.karolisw.project_backend.dto.out;

public class CourseOut {
    // id, name, code
    private long id;
    private String name;
    private String code;

    public long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setId(long id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
