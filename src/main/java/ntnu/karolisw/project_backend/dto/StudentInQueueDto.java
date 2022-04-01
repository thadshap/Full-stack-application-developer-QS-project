package ntnu.karolisw.project_backend.dto;

public class StudentInQueueDto {
    long studentInQueueId; // Only used upon delete (not creation)
    long studentId;
    long courseId;
    int tableNumber;
    String campus;
    boolean digital;
    String building;
    String room;
    boolean assessmentHelp;
    int statusInQueue;

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

    public int getStatusInQueue() {
        return statusInQueue;
    }

    public long getStudentInQueueId() {
        return studentInQueueId;
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

    public void setStatusInQueue(int statusInQueue) {
        this.statusInQueue = statusInQueue;
    }

    public void setStudentInQueueId(long studentInQueueId) {
        this.studentInQueueId = studentInQueueId;
    }
}
