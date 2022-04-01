package ntnu.karolisw.project_backend.service;

import ntnu.karolisw.project_backend.dto.CourseDto;
import ntnu.karolisw.project_backend.model.*;
import ntnu.karolisw.project_backend.repository.CourseRepository;
import ntnu.karolisw.project_backend.repository.GroupOfAssignmentRepository;
import ntnu.karolisw.project_backend.repository.QueueRepository;
import ntnu.karolisw.project_backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequestMapping("/calculator") // means all other mappings actually start with this!
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private GroupOfAssignmentRepository groupOfAssignmentRepository;

    @Autowired
    private QueueRepository queueRepository;

    // Mark a specified course as archived
    public void markAsArchived(long courseId) {
        Optional<Course> course = courseRepository.findById(courseId);

        // Archive the course if present
        course.ifPresent(value -> value.setArchived(true));
    }

    // when we create a new course we set archived == false and active == true
            // remember to insert course into courses and group of assignment into course and into group of assignment
            // remember to create the queue as well inside the backend
    public void createCourse(CourseDto newCourse) {
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
    }

    // get all students in a course
    public ResponseEntity<Object> getAllStudentsInCourse(long courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        if(course.isPresent()) {
            return new ResponseEntity<>(course.get().getStudents(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // add a list of student assistant to a course
    public ResponseEntity<Object> addStudentAssistant(long courseId, Student student) {
        Optional<Course> course = courseRepository.findById(courseId);
        if(course.isPresent()) {
            course.get().addStudentAssistant(student);
            courseRepository.save(course.get());
            return new ResponseEntity<>(course.get(), HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // add a teacher to a course
    public ResponseEntity<Object> addTeacher(long courseId, Teacher teacher) {
        Optional<Course> course = courseRepository.findById(courseId);
        if(course.isPresent()) {
            course.get().addTeacher(teacher);
            courseRepository.save(course.get());
            return new ResponseEntity<>(course.get(), HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // add a student to a course
    public ResponseEntity<Object> addStudent(long courseId, Student student) {
        Optional<Course> course = courseRepository.findById(courseId);
        if(course.isPresent()) {
            course.get().addStudent(student);
            courseRepository.save(course.get()); // TODO maybe it updates itself?
            return new ResponseEntity<>(course.get(), HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Deletes a student from a course
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
    public ResponseEntity<Object> updateStartDate(long courseId, Date startDate) {
        // Get the course
        Optional<Course> course = courseRepository.findById(courseId);

        if(course.isPresent()) {
            // Update its start date
            course.get().setStartDate(startDate);
            return new ResponseEntity<>(course.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // update end date
    public ResponseEntity<Object> updateEndDate(long courseId, Date endDate) {
        // Get the course
        Optional<Course> course = courseRepository.findById(courseId);

        if(course.isPresent()) {
            // Update its end date
            course.get().setExpectedEndDate(endDate);
            return new ResponseEntity<>(course.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // update number of assignments
    public ResponseEntity<Object> updateNumberOfAssignments(long courseId, int numberOfAssignments) {
        // Get the course
        Optional<Course> course = courseRepository.findById(courseId);
        if(course.isPresent()) {
            course.get().setNumberOfAssignments(numberOfAssignments);
            return new ResponseEntity<>(course.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // update min_approved_assignments
    public ResponseEntity<Object> updateMinApprovedAssignments(long courseId, int numberOfApprovedAssignments) {
        // Get the course
        Optional<Course> course = courseRepository.findById(courseId);
        if(course.isPresent()) {
            course.get().setMinApprovedAssignments(numberOfApprovedAssignments);
            return new ResponseEntity<>(course.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // update number_parts_assignments
    public ResponseEntity<Object> updateNumberPartsAssignments(long courseId, int numberOfParts) {
        // Get the course
        Optional<Course> course = courseRepository.findById(courseId);
        if(course.isPresent()) {
            course.get().setNumberPartsAssignments(numberOfParts);
            return new ResponseEntity<>(course.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // setArchived --> when course is archived --> the queue must be deleted!
    public ResponseEntity<Object> archiveCourse(long courseId, int numberOfParts) {
        // Get the course
        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isPresent()) {
            // Archive the course
            course.get().setArchived(true);

            // Delete the queue
            long queueId = courseRepository.getQueueByCourseId(courseId).getQueueId();
            queueRepository.delete(queueRepository.getById(queueId)); //todo cascade needed!!
            return new ResponseEntity<>(course.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // get number of approved assignments necessary for a certain course
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
    public ResponseEntity<Object> getAllAssignmentsForStudent(long studentId) {
        // Check that the student exists
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isPresent()) {
            Set<Assignment> allAssignments = studentRepository.getAssignmentsByStudentId(studentId);
            return new ResponseEntity<>(allAssignments, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // get all courses for student with student id (whole object)
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
