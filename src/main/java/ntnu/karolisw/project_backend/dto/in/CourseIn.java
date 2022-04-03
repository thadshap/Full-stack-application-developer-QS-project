package ntnu.karolisw.project_backend.dto.in;

import ntnu.karolisw.project_backend.model.Assignment;
import ntnu.karolisw.project_backend.model.Student;

import java.util.*;

/**
 * DTO for creation of course object in frontEnd in order to insert in DB
 */
public class CourseIn {
    private Long courseId;
    private Long personId; // For when the user wants to add a teacher as well
    private int typeOfUser; // To verify that the personId belongs to a teacher!
    private String courseCode;
    private int assignmentNumber;
    private String name;
    private Date startDate;
    private Date expectedEndDate;
    private int numberOfAssignments = 0;
    private int minApprovedAssignments = 0;
    private boolean archived;

    // not necessary to send this list but needed for setting the assistant at a later stage as well
    private Set<Student> studentAssistants = new HashSet<>();

    // all assignments end up here
    private List<List<Assignment>> groupsOfAssignments;


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

    public boolean isArchived() {
        return archived;
    }

    public Set<Student> getStudentAssistants() {
        return studentAssistants;
    }

    public List<List<Assignment>> getGroupsOfAssignments() {
        return groupsOfAssignments;
    }

    public Long getPersonId() {
        return personId;
    }

    public int getTypeOfUser() {
        return typeOfUser;
    }

    public int getAssignmentNumber() {
        return assignmentNumber;
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

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public void setStudentAssistants(Set<Student> studentAssistants) {
        this.studentAssistants = studentAssistants;
    }

    public void setGroupsOfAssignments(List<List<Assignment>> groupsOfAssignments) {
        this.groupsOfAssignments = groupsOfAssignments;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public void setTypeOfUser(int typeOfUser) {
        this.typeOfUser = typeOfUser;
    }

    public void setAssignmentNumber(int assignmentNumber) {
        this.assignmentNumber = assignmentNumber;
    }
}
