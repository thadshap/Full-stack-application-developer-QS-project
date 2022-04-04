package ntnu.karolisw.project_backend.dto.out;

public class CourseOut {
    // id, name, code
    private long id;
    private String name;
    private String code;
    private int numberOfAssignments;
    private int minApprovedAssignments;


    public long getId() {
        return id;
    }

    public int getNumberOfAssignments() {
        return numberOfAssignments;
    }

    public void setNumberOfAssignments(int numberOfAssignments) {
        this.numberOfAssignments = numberOfAssignments;
    }

    public int getMinApprovedAssignments() {
        return minApprovedAssignments;
    }

    public void setMinApprovedAssignments(int minApprovedAssignments) {
        this.minApprovedAssignments = minApprovedAssignments;
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
