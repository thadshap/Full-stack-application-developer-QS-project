package ntnu.karolisw.project_backend.dto.in;

public class StudentInQueueIn {
    private long studentInQueueId; // Only used upon delete (not creation)
    private long studentId;
    private long courseId;
    private int tableNumber;
    private String campus;
    private boolean digital;
    private String building;
    private String room;
    private boolean assessmentHelp;
    private String statusInQueue;

    // for the queue only
    private boolean active;

    // GETTERS

    public long getStudentId() {
        return studentId;
    }

    public long getCourseId() {
        return courseId;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public String getCampus() {
        return campus;
    }

    public boolean isDigital() {
        return digital;
    }

    public String getBuilding() {
        return building;
    }

    public String getRoom() {
        return room;
    }

    public boolean isAssessmentHelp() {
        return assessmentHelp;
    }

    public String getStatusInQueue() {
        return statusInQueue;
    }

    public long getStudentInQueueId() {
        return studentInQueueId;
    }

    public boolean isActive() {
        return active;
    }

    // SETTERS

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public void setDigital(boolean digital) {
        this.digital = digital;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setAssessmentHelp(boolean assessmentHelp) {
        this.assessmentHelp = assessmentHelp;
    }

    public void setStatusInQueue(String statusInQueue) {
        this.statusInQueue = statusInQueue;
    }

    public void setStudentInQueueId(long studentInQueueId) {
        this.studentInQueueId = studentInQueueId;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "StudentInQueueIn{" +
                "studentInQueueId=" + studentInQueueId +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                ", tableNumber=" + tableNumber +
                ", campus='" + campus + '\'' +
                ", digital=" + digital +
                ", building='" + building + '\'' +
                ", room='" + room + '\'' +
                ", assessmentHelp=" + assessmentHelp +
                ", statusInQueue='" + statusInQueue + '\'' +
                ", active=" + active +
                '}';
    }
}
