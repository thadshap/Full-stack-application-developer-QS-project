package ntnu.karolisw.project_backend.dto.in;

import ntnu.karolisw.project_backend.model.Assignment;

import java.util.List;
import java.util.Set;

public class GroupIn {
    private long courseId;
    private int orderNumber;
    private int numOfPractices;
    private int minimumNumApproved;
    private Set<Assignment> groupOfAssignments;

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

    public int getMinimumNumApproved() {
        return minimumNumApproved;
    }

    public Set<Assignment> getGroupOfAssignments() {
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

    public void setMinimumNumApproved(int minimumNumApproved) {
        this.minimumNumApproved = minimumNumApproved;
    }

    public void setGroupsOfAssignments(Set<Assignment> groupOfAssignments) {
        this.groupOfAssignments = groupOfAssignments;
    }
}
