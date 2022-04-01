package ntnu.karolisw.project_backend.dto.out;

public class AssignmentOut {
    private int assignmentNumber;
    private boolean approved;

    // GETTERS
    public int getAssignmentNumber() {
        return assignmentNumber;
    }

    public boolean isApproved() {
        return approved;
    }


    // SETTERS
    public void setAssignmentNumber(int assignmentNumber) {
        this.assignmentNumber = assignmentNumber;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
