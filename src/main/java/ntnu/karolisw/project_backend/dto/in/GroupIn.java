package ntnu.karolisw.project_backend.dto.in;

import ntnu.karolisw.project_backend.model.Assignment;

import java.util.List;

public class GroupIn {
    private long courseId;
    private int orderNumber;
    private int numOfPractices;
    private int minNumApproved;
    private List<Assignment> groupOfAssignments;

    // GETTERS

    public long getCourseId() {
        return courseId;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public int getNumOfPractices() {
        return numOfPractices;
    }

    public int getMinNumApproved() {
        return minNumApproved;
    }

    public List<Assignment> getGroupOfAssignments() {
        return groupOfAssignments;
    }
    // SETTERS

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setNumOfPractices(int numOfPractices) {
        this.numOfPractices = numOfPractices;
    }

    public void setMinNumApproved(int minNumApproved) {
        this.minNumApproved = minNumApproved;
    }

    public void setGroupsOfAssignments(List<Assignment> groupOfAssignments) {
        this.groupOfAssignments = groupOfAssignments;
    }
}
