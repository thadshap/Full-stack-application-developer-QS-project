package ntnu.karolisw.project_backend.service.classes;

import ntnu.karolisw.project_backend.dto.in.CourseIn;
import ntnu.karolisw.project_backend.dto.out.AssignmentOut;
import ntnu.karolisw.project_backend.dto.out.StudentOut;
import ntnu.karolisw.project_backend.model.*;
import ntnu.karolisw.project_backend.repository.*;
import ntnu.karolisw.project_backend.service.interfaces.CourseServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourseService implements CourseServiceI {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private GroupOfAssignmentRepository groupOfAssignmentRepository;

    @Autowired
    private QueueRepository queueRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    // Mark a specified course as archived
    @Override
    public ResponseEntity<Object> markAsArchived(long courseId) {
        Optional<Course> course = courseRepository.findById(courseId);

        // Archive the course if present
        if(course.isPresent()) {
            course.get().setArchived(true);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        // Else, the course was not found
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @Override
    public ResponseEntity<Object> createCourse(CourseIn newCourse) {
        Course course = new Course();

        // Set all the variables needed
        course.setCourseCode(newCourse.getCourseCode());
        course.setArchived(false);
        course.setExpectedEndDate(newCourse.getExpectedEndDate());
        course.setStartDate(newCourse.getStartDate());
        course.setMinApprovedAssignments(newCourse.getMinApprovedAssignments());
        course.setNumberOfAssignments(newCourse.getNumberOfAssignments());
        course.setName(newCourse.getName());

        // Get all the assignments sent from frontend
        List<Set<Assignment>> assignmentList = newCourse.getGroupsOfAssignments();

        // For all groups of assignment, create a group of assignment object
        for(Set<Assignment> group : assignmentList) {
            GroupOfAssignment groupOfAssignment = new GroupOfAssignment();
            groupOfAssignment.setAssignments(group);

            // Add each individual group to the course object
            course.addGroupOfAssignment(groupOfAssignment);

            // Add each group to the GroupOfAssignmentRepository
            groupOfAssignmentRepository.save(groupOfAssignment);
        }

        // When all groups are added, the course object is finished and may be added to the db!
        courseRepository.save(course);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // get all students in a course
    @Override
    public ResponseEntity<Object> getAllStudentsInCourse(long courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        if(course.isPresent()) {

            // Get students
            Set<Student> students = course.get().getStudents();
            ArrayList<StudentOut> students2 = new ArrayList<>(students.size());

            // Shape student entity-objects into correct data-transfer-objects (security)
            for(Student student: students) {
                StudentOut so = new StudentOut();
                so.setFirstName(student.getFirstName());
                so.setLastName(student.getLastName());
                try {
                    so.setApprovedAssignmentsInCourse(getAllApprovedAssignmentsForStudent(student.getId()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                students2.add(new StudentOut());
            }
            // Return the DTO
            return new ResponseEntity<>(students2, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // add a list of student assistant to a course
    @Override
    public ResponseEntity<Object> addStudentAssistant(long courseId, Student student) {
        Optional<Course> course = courseRepository.findById(courseId);
        if(course.isPresent()) {
            course.get().addStudentAssistant(student);
            courseRepository.save(course.get());
            return new ResponseEntity<>( HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // add a teacher to a course
    @Override
    public ResponseEntity<Object> addTeacher(long courseId, Teacher teacher) {
        Optional<Course> course = courseRepository.findById(courseId);
        if(course.isPresent()) {
            course.get().addTeacher(teacher);
            courseRepository.save(course.get());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // add a student to a course
    @Override
    public ResponseEntity<Object> addStudent(long courseId, Student student) {
        Optional<Course> course = courseRepository.findById(courseId);
        if(course.isPresent()) {
            course.get().addStudent(student);
            courseRepository.save(course.get()); // TODO maybe it updates itself?
            return new ResponseEntity<>( HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Deletes a student from a course
    @Override
    public ResponseEntity<Object> removeStudent(long courseId, long studentId) {
        // if the course exists
        if(courseRepository.existsById(courseId)){
            // get all the students taking the course
            Set<Student> students = courseRepository.getStudentsByCourseId(courseId);

            // If there is a student with the specified student id, remove it
            students.removeIf(student -> student.getId() == studentId);

            // todo if this is necessary, remember to fix cascade
            for(Student student : students) {
                if(student.getId() == studentId) {
                    studentRepository.delete(student);
                }
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // update start date
    @Override
    public ResponseEntity<Object> updateStartDate(long courseId, Date startDate) {
        // Get the course
        Optional<Course> course = courseRepository.findById(courseId);

        if(course.isPresent()) {
            // Update its start date
            course.get().setStartDate(startDate);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // update end date
    @Override
    public ResponseEntity<Object> updateEndDate(long courseId, Date endDate) {
        // Get the course
        Optional<Course> course = courseRepository.findById(courseId);

        if(course.isPresent()) {
            // Update its end date
            course.get().setExpectedEndDate(endDate);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // update number of assignments
    @Override
    public ResponseEntity<Object> updateNumberOfAssignments(long courseId, int numberOfAssignments) {
        // Get the course
        Optional<Course> course = courseRepository.findById(courseId);
        if(course.isPresent()) {
            course.get().setNumberOfAssignments(numberOfAssignments);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // update min_approved_assignments
    @Override
    public ResponseEntity<Object> updateMinApprovedAssignments(long courseId, int numberOfApprovedAssignments) {
        // Get the course
        Optional<Course> course = courseRepository.findById(courseId);
        if(course.isPresent()) {
            course.get().setMinApprovedAssignments(numberOfApprovedAssignments);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // update number_parts_assignments
    @Override
    public ResponseEntity<Object> updateNumberPartsAssignments(long courseId, int numberOfParts) {
        // Get the course
        Optional<Course> course = courseRepository.findById(courseId);
        if(course.isPresent()) {
            course.get().setNumberPartsAssignments(numberOfParts);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // setArchived --> when course is archived --> the queue must be deleted!
    @Override
    public ResponseEntity<Object> archiveCourse(long courseId, int numberOfParts) {
        // Get the course
        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isPresent()) {
            // Archive the course
            course.get().setArchived(true);

            // Delete the queue
            long queueId = courseRepository.getQueueByCourseId(courseId).getQueueId();
            queueRepository.delete(queueRepository.getById(queueId)); //todo cascade needed!!
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // get number of approved assignments necessary for a certain course
    @Override
    public ResponseEntity<Object> getNumberOfApprovedAssignmentsByCourse(long courseId) {
        Optional<Course> course = courseRepository.findById(courseId);

        // If the course exists, retrieve how many (minimum) assignments must be approved
        if(course.isPresent()) {
            int minApprovedAssignments = course.get().getMinApprovedAssignments();
            return new ResponseEntity<>(minApprovedAssignments, HttpStatus.OK);
        }
        // If the course was not found
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // get all the assignments for a student with student id
    @Override
    public ResponseEntity<Object> getAllAssignmentsForStudent(long studentId) {
        // Create dto list
        ArrayList<AssignmentOut> assignments = new ArrayList<>();

        // Check that the student exists
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isPresent()) {

            Set<Assignment> allAssignments = studentRepository.getAssignmentsByStudentId(studentId);
            // Get all assignments with the correct course id and add them to assignments list
            for(Assignment assignment: allAssignments) {
                if(assignment.isApproved()) {
                    AssignmentOut ao = new AssignmentOut();
                    ao.setApproved(true);
                    ao.setAssignmentNumber(assignment.getAssignmentNumber());
                    assignments.add(ao);
                }
            }
            // Return the dto
            return new ResponseEntity<>(assignments, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // getAssignmentsByStudentId
    @Override
    public ResponseEntity<Object> getAllAssignmentsForStudentInCourse(long studentId, long courseId) {
        // List of assignments that are from the specified course
        ArrayList<AssignmentOut> assignments = new ArrayList<>();

        // Check that the student exists
        Optional<Student> student = studentRepository.findById(studentId);

        if (student.isPresent()) {

            // Get all assignments the student has
            Set<Assignment> allAssignments = studentRepository.getAssignmentsByStudentId(studentId);

            // Get all assignments with the correct course id and add them to assignments list
            for(Assignment assignment: allAssignments) {
                // Get assignment group id
                long groupId = assignmentRepository.getGroupIdOfAssignment(assignment.getAssignmentId());
                // Get course id
                long courseId2 = groupOfAssignmentRepository.getCourseIdOfGroup(groupId);

                // If the assignment has the correct course id
                if(courseId2 == courseId) {
                    // See if approved
                    if(assignment.isApproved()) {
                        AssignmentOut ao = new AssignmentOut();
                        ao.setApproved(true);
                        ao.setAssignmentNumber(assignment.getAssignmentNumber());
                        assignments.add(ao);
                    }
                }
            }
            // Return the dto
            return new ResponseEntity<>(assignments, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // get number of approved assignments for a student with student id
    @Override
    public int getAllApprovedAssignmentsForStudent(long studentId) throws Exception {
        // Create a counter
        int numberOfApprovedAssignments = 0;

        // Check that the student exists
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isPresent()) {
            Set<Assignment> allAssignments = studentRepository.getAssignmentsByStudentId(studentId);
            for(Assignment assignment : allAssignments) {
                if(assignment.isApproved()) {
                    numberOfApprovedAssignments ++;
                }
            }
            return numberOfApprovedAssignments;
        }
        else {
            throw new Exception("Could not find student with id: " + studentId);
        }
    }

    // get all courses for student with student id (whole object)
    @Override
    public ResponseEntity<Object> getAllCoursesForStudent(long studentId) {
        // Check that the student exists
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isPresent()) {
            Set<Course> allCourses = studentRepository.getCoursesByStudentId(studentId);
            return new ResponseEntity<>(allCourses, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
