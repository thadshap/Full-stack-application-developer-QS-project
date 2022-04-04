package ntnu.karolisw.project_backend.dto.out;

import ntnu.karolisw.project_backend.enumFolder.Status;
import ntnu.karolisw.project_backend.model.Queue;
import ntnu.karolisw.project_backend.model.Student;

import javax.persistence.*;

public class StudentInQueueOut {
    private Long studentInQueueId;

    private long studentId;

    private int placementInQueue;

    private boolean digital;

    private String campus;

    private String building;

    private String room;

    private int tableNumber;

    // Help or approved
    private boolean assessmentHelp;

    private String statusInQueue;

    private long queueId;

    // GETTERS && SETTERS
    public Long getStudentInQueueId() {
        return studentInQueueId;
    }

    public void setStudentInQueueId(Long studentInQueueId) {
        this.studentInQueueId = studentInQueueId;
    }

    public int getPlacementInQueue() {
        return placementInQueue;
    }

    public void setPlacementInQueue(int placementInQueue) {
        this.placementInQueue = placementInQueue;
    }

    public boolean isDigital() {
        return digital;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public void setDigital(boolean digital) {
        this.digital = digital;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public boolean isAssessmentHelp() {
        return assessmentHelp;
    }

    public void setAssessmentHelp(boolean assessmentHelp) {
        this.assessmentHelp = assessmentHelp;
    }

    public String getStatusInQueue() {
        return statusInQueue;
    }

    public void setStatusInQueue(String statusInQueue) {
        this.statusInQueue = statusInQueue;
    }

    public long getQueue() {
        return queueId;
    }

    public void setQueue(long queueId) {
        this.queueId = queueId;
    }
}
