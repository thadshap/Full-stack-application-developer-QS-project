package ntnu.karolisw.project_backend.dto.out;

import java.util.Date;

public class CourseOut {
    // id, name, code
    private long id;
    private String name;
    private String code;
    private int numberOfAssignments;
    private int minApprovedAssignments;
    private Date startDate;
    private Date expectedEndDate;



    public long getId() {
        return id;
    }

    public int getNumberOfAssignments() {
        return numberOfAssignments;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getExpectedEndDate() {
        return expectedEndDate;
    }

    public void setExpectedEndDate(Date expectedEndDate) {
        this.expectedEndDate = expectedEndDate;
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
