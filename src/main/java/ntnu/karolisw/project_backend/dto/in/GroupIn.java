package ntnu.karolisw.project_backend.dto.in;

public class GroupIn {
    private long courseId;
    private int orderNumber;
    private int numOfPractices;
    private int minimumNumApproved;

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
}
