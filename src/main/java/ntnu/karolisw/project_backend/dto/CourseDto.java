package ntnu.karolisw.project_backend.dto;

import ntnu.karolisw.project_backend.model.Assignment;
import ntnu.karolisw.project_backend.model.Student;

import java.util.*;

/**
 * DTO for creation of course object in frontEnd in order to insert in DB
 */
public class CourseDto {
    private Long courseId;
    private String courseCode;
    private String name;
    private Date startDate;
    private Date expectedEndDate;
    private int numberOfAssignments;
    private int minApprovedAssignments;
    private int numberPartsAssignments;
    private boolean archived;

    // not necessary to send this list but needed for setting the assistant at a later stage as well
    private Set<Student> studentAssistants = new HashSet<>();

    // all assignments end up here
    private List<Set<Assignment>> groupsOfAssignments;


    // GETTERS

    public Long getCourseId() {
        return courseId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getName() {
        return name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getExpectedEndDate() {
        return expectedEndDate;
    }

    public int getNumberOfAssignments() {
        return numberOfAssignments;
    }

    public int getMinApprovedAssignments() {
        return minApprovedAssignments;
    }

    public int getNumberPartsAssignments() {
        return numberPartsAssignments;
    }

    public boolean isArchived() {
        return archived;
    }

    public Set<Student> getStudentAssistants() {
        return studentAssistants;
    }

    public List<Set<Assignment>> getGroupsOfAssignments() {
        return groupsOfAssignments;
    }

    // SETTERS

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setExpectedEndDate(Date expectedEndDate) {
        this.expectedEndDate = expectedEndDate;
    }

    public void setNumberOfAssignments(int numberOfAssignments) {
        this.numberOfAssignments = numberOfAssignments;
    }

    public void setMinApprovedAssignments(int minApprovedAssignments) {
        this.minApprovedAssignments = minApprovedAssignments;
    }

    public void setNumberPartsAssignments(int numberPartsAssignments) {
        this.numberPartsAssignments = numberPartsAssignments;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public void setStudentAssistants(Set<Student> studentAssistants) {
        this.studentAssistants = studentAssistants;
    }

    public void setGroupsOfAssignments(List<Set<Assignment>> groupsOfAssignments) {
        this.groupsOfAssignments = groupsOfAssignments;
    }
}
