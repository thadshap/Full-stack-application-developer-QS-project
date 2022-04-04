package ntnu.karolisw.project_backend.service.classes;

import ntnu.karolisw.project_backend.dto.in.CourseIn;
import ntnu.karolisw.project_backend.dto.in.GroupIn;
import ntnu.karolisw.project_backend.dto.in.PersonIn;
import ntnu.karolisw.project_backend.dto.out.AssignmentOut;
import ntnu.karolisw.project_backend.dto.out.CourseOut;
import ntnu.karolisw.project_backend.dto.out.PersonOut;
import ntnu.karolisw.project_backend.model.*;
import ntnu.karolisw.project_backend.model.Queue;
import ntnu.karolisw.project_backend.model.user.StudentUser;
import ntnu.karolisw.project_backend.repository.*;
import ntnu.karolisw.project_backend.service.interfaces.CourseServiceI;
import ntnu.karolisw.project_backend.service.interfaces.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
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

    @Autowired
    private TeacherRepository teacherRepository;

    /**
     * Archives a course
     * @param courseId the course to archive
     *
     * @return
     */
    @Override
    public ResponseEntity<Object> archiveCourse(long courseId) {
        // Get the course
        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isPresent()) {
            // Archive the course
            course.get().setArchived(true);

            // Get the queue belonging to the course
            Queue queue = courseRepository.getQueueByCourseId(courseId);

            // Set the queues course fk = null in order to delete the queue object
            queue.setCourse(null);

            // Do the same for the course
            course.get().setQueue(null);

            // Update the course
            courseRepository.save(course.get());

            // Delete the queue
            queueRepository.delete(queue);

            // Return HTTP OK
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private boolean validateCourse(CourseIn newCourse) {
        // If new course contains all arguments it should
        return newCourse.getCourseCode() != null && newCourse.getExpectedEndDate() != null &&
                newCourse.getStartDate() != null && newCourse.getMinApprovedAssignments() != 0 &&
                newCourse.getNumberOfAssignments() != 0 && newCourse.getName() != null;
    }


    @Override
    public ResponseEntity<Object> createCourse(CourseIn newCourse) {
        // If the dto contains all arguments it should, we can proceed
        if(validateCourse(newCourse)) {

            // We create a course object
            Course course = new Course();

            // Set all the variables needed
            course.setCourseCode(newCourse.getCourseCode());
            course.setArchived(false);
            course.setExpectedEndDate(newCourse.getExpectedEndDate());
            course.setStartDate(newCourse.getStartDate());
            course.setMinApprovedAssignments(newCourse.getMinApprovedAssignments());
            course.setNumberOfAssignments(newCourse.getNumberOfAssignments());
            course.setName(newCourse.getName());

            // If groups of assignments were issued, they must be added
            if(newCourse.getGroupsOfAssignments() != null) {
                List<List<Assignment>> assignmentList = newCourse.getGroupsOfAssignments();

                // For all groups of assignment, create a group of assignment object
                for(List<Assignment> group : assignmentList) {
                    GroupOfAssignment groupOfAssignment = new GroupOfAssignment();
                    groupOfAssignment.setAssignments(group);

                    // Add each individual group to the course object
                    course.addGroupOfAssignment(groupOfAssignment);
                }
            }

            // Set all assignments for the course
            for (int i = 0; i < newCourse.getNumberOfAssignments(); i++) {

                // Create a new assignment
                Assignment newAssignment = new Assignment();
                newAssignment.setAssignmentNumber(i);
                newAssignment.setApproved(false);
                newAssignment.setCreateDate(new Date());

                // CascadeType == PERSIST!
                assignmentRepository.save(newAssignment);
            }

            // Course and Queue has a one-to-one connection, where their lifespan is the same
            Queue queue = new Queue();
            queue.setNumberOfWaitingStudents(0);
            queue.setActive(false);
            queueRepository.save(queue);

            // Add the queue to the course
            course.setQueue(queue);

            // If there is a teacher present in the dto, add it to the course!
            if(newCourse.getPersonId() != null) {
                Optional<Teacher> teacher = teacherRepository.findById(newCourse.getPersonId());
                if(teacher.isPresent()) {
                    // If the person id belongs to a teacher
                    if(newCourse.getTypeOfUser() == 2) {
                        System.out.println("trying to add teacher");

                        // Add this teacher as a foreign key to the course!
                        course.addTeacher(teacher.get());
                        teacher.get().addCourse(course);
                    }
                    else {
                        // Wrong typeOfUser sent from frontend
                        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                    }
                }
            }

            // When all groups are added, the course object is finished and may be added to the db!
            courseRepository.save(course);

            queue.setCourse(course);
            queueRepository.save(queue);

            return new ResponseEntity<>( HttpStatus.CREATED);
        }
        // If the dto did not contain the proper attributes (parameters)
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Bad method-name, but method retrieves full name and number of approved assignments for
     * each student in the specified course
     *
     * @param courseId is the specified course
     * @return PersonOut (dto) containing:
     *      - FirstName
     *      - LastName
     *      - NumberOfApprovedAssignments (int)
     */
    @Override
    public ResponseEntity<Object> getAllStudentsInCourse(long courseId) {
        Optional<Course> course = courseRepository.findById(courseId);

        if(course.isPresent()) {

            // Get students
            Set<Student> students = course.get().getStudents();
            System.out.println("Students");
            System.out.println(students.toString());
            ArrayList<PersonOut> students2 = new ArrayList<>(students.size());

            // Shape student entity-objects into correct data-transfer-objects (security)
            for(Student student: students) {

                // New dto
                PersonOut so = new PersonOut();
                so.setFirstName(student.getFirstName());
                so.setLastName(student.getLastName());
                so.setPersonId(student.getId());
                so.setEmail(student.getEmail());
                try {
                    so.setApprovedAssignmentsInCourse(getNumberOfApprovedAssignmentsForStudent(student.getId()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                students2.add(so);
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
    public ResponseEntity<Object> addStudentAssistantToCourse(PersonIn dto) {
        Optional<Course> course = courseRepository.findById(dto.getCourseId());
        Optional<Student> student = studentRepository.findByEmail(dto.getEmail());

        if(course.isPresent() && student.isPresent()) {
            course.get().addStudentAssistant(student.get());
            student.get().addTaInCourse(course.get());
            student.get().addCourse(course.get());

            courseRepository.save(course.get());
            studentRepository.save(student.get());
            return new ResponseEntity<>( HttpStatus.OK);
        }
        else if(course.isPresent()) {
            // Create the student
            Student newStudent = new Student();
            newStudent.setEmail(dto.getEmail());
            newStudent.setFirstName(dto.getFirstName());
            newStudent.setLastName(dto.getLastName());
            newStudent.addCourse(course.get());

            // Add the student
            studentRepository.save(newStudent);

            course.get().addStudent(newStudent);
            course.get().addStudentAssistant(newStudent);

            // Update course
            courseRepository.save(course.get());

            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // add a teacher to a course
    // When course is added to teacher, then teacher is also added to course (cascade.persist)
    @Override
    public ResponseEntity<Object> addTeacherToCourse(PersonIn dto) {
        Optional<Course> course = courseRepository.findById(dto.getCourseId());
        Optional<Teacher> teacher = teacherRepository.findByEmail(dto.getEmail());

        if(course.isPresent() && teacher.isPresent()) {

            // Add course to teacher only, as course will be automatically updated
            teacher.get().addCourse(course.get());
            teacherRepository.save(teacher.get());

            return new ResponseEntity<>(HttpStatus.OK);
        }

        else if (course.isPresent()){
            Teacher newTeacher = new Teacher();
            newTeacher.setEmail(dto.getEmail());
            newTeacher.setFirstName(dto.getFirstName());
            newTeacher.setLastName(dto.getLastName());

            // Add the course to the new teacher
            newTeacher.addCourse(course.get());
            teacherRepository.save(newTeacher);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // add a student to a course
    @Override
    public ResponseEntity<Object> addStudentToCourse(PersonIn dto) {

        // Retrieve the students
        Optional<Course> course = courseRepository.findById(dto.getCourseId());
        Optional<Student> student = studentRepository.findByEmail(dto.getEmail());

        // If the id and email were correct
        if(course.isPresent() && student.isPresent()) {

            // Add student to course and update
            course.get().addStudent(student.get());
            courseRepository.save(course.get());

            // Add course to student and update
            student.get().addCourse(course.get());
            studentRepository.save(student.get());

            return new ResponseEntity<>( HttpStatus.OK);
        }
        // If only the course is present, we create the student
        else if(course.isPresent()) {
            Student newStudent = new Student();
            newStudent.setEmail(dto.getEmail());
            newStudent.setFirstName(dto.getFirstName());
            newStudent.setLastName(dto.getLastName());
            course.get().addStudent(newStudent);

            // Add the course to the new teacher
            newStudent.addCourse(course.get());
            studentRepository.save(newStudent);

            // Add the student to the course
            course.get().addStudent(newStudent);
            courseRepository.save(course.get());

            return new ResponseEntity<>( HttpStatus.CREATED);
        }
        // If there is no course with the specified id
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Deletes a student from a course
    @Override
    public ResponseEntity<Object> removeStudentFromCourse(long courseId, String email) {
        // if the course exists
        if(courseRepository.existsById(courseId)){

            // get all the students taking the course
            Set<Student> students = courseRepository.getStudentsByCourseId(courseId);

            // For each student
            for(Student student : students) {

                // If there is a student with the specified email
                if(student.getEmail().equalsIgnoreCase(email)) {

                    // Get all the courses the student is taking
                    Set<Course> courses = student.getCourses();

                    // For all the courses the student is taking
                    for(Course course : courses) {

                        // If correct course
                        if (course.getCourseId() == courseId) {

                            // Remove from list of foreign keys
                            courses.remove(course);

                            // Update the list of courses (now without the course the student is done with)
                            student.setCourses(courses);
                            studentRepository.save(student);
                        }
                    }

                    // We can now delete the student from the course
                    Set<Student> studentsInCourse = courseRepository.getStudentsByCourseId(courseId);

                    // For each student
                    for(Student student1 : studentsInCourse) {

                        // If the student in question
                        if(student1.getEmail().equalsIgnoreCase(email)) {

                            // Remove student1 from the list
                            studentsInCourse.remove(student1);

                            Optional<Course> course = courseRepository.findById(courseId);

                            // If the course is present
                            if(course.isPresent()) {

                                // Set the courses list of students equal to the new list
                                course.get().setStudents(studentsInCourse);

                                // Update the course
                                courseRepository.save(course.get());

                                // Return ok
                                return new ResponseEntity<>(HttpStatus.OK);
                            }
                        }
                    }
                }
            }
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return null;
    }

    // update start date
    @Override
    public ResponseEntity<Object> updateStartDate(long courseId, Date startDate) {
        // Get the course
        Optional<Course> course = courseRepository.findById(courseId);

        if(course.isPresent()) {
            // Update its start date
            course.get().setStartDate(startDate);

            // Update course
            courseRepository.save(course.get());

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

            // Update course
            courseRepository.save(course.get());
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

            // Update course
            courseRepository.save(course.get());
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

            // Update the course
            courseRepository.save(course.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    // get number of approved assignments necessary for a certain course
    @Override
    public ResponseEntity<Object> getMinApprovedAssignmentsByCourse(long courseId) {
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
        // Check that the student exists
        Optional<Student> student = studentRepository.findById(studentId);

        // If the student exists
        if (student.isPresent()) {

            // Get all assignments for the student
            Set<Assignment> allAssignments = studentRepository.getAssignmentsByStudentId(studentId);

            // Return the dto
            return new ResponseEntity<>(allAssignments, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    // get all the assignments a student has that belong to a specific course and are approved
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

                        // Add the new AssignmentOut dto to the list
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
    public int getNumberOfApprovedAssignmentsForStudent(long studentId) throws Exception {
        // Create a counter
        int numberOfApprovedAssignments = 0;

        // Check that the student exists
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isPresent()) {
            Set<Assignment> allAssignments = studentRepository.getAssignmentsByStudentId(studentId);
            for (Assignment assignment : allAssignments) {
                if (assignment.isApproved()) {
                    numberOfApprovedAssignments++;
                }
            }
            return numberOfApprovedAssignments;
        } else {
            throw new Exception("Could not find student with id: " + studentId);
        }
    }

    @Override
    public ArrayList<Assignment> getAllApprovedAssignmentsForStudent(long studentId) throws Exception {
        ArrayList<Assignment> approvedAssignments = new ArrayList<>();

        // Check that the student exists
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isPresent()) {
            Set<Assignment> allAssignments = studentRepository.getAssignmentsByStudentId(studentId);
            for (Assignment assignment : allAssignments) {
                if (assignment.isApproved()) {
                    approvedAssignments.add(assignment);
                }
            }
            return approvedAssignments;
        } else {
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

            List<CourseOut> coursesOut = new ArrayList<>();

            for (Course course : allCourses) {

                // Create dto object
                CourseOut courseOut = new CourseOut();

                // Set necessary attributes
                courseOut.setId(course.getCourseId());
                courseOut.setCode(course.getCourseCode());
                courseOut.setName(course.getName());
                courseOut.setMinApprovedAssignments(course.getMinApprovedAssignments());
                courseOut.setNumberOfAssignments(course.getNumberOfAssignments());

                // Add dto to list
                coursesOut.add(courseOut);
            }

            return new ResponseEntity<>(coursesOut, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Object> getAllCoursesForStudentAssistant(long studentId) {

        // Final list to be returned (TA = teachers assistant --> student assistant)
        List<CourseOut> taCourses = new ArrayList<>();

        // Check that the student exists before going forth
        Optional<Student> student = studentRepository.findById(studentId);

        if(student.isPresent()) {

            // Get all courses
            List<Course> allCourses = courseRepository.findAll();

            // For each course
            for(Course course : allCourses) {

                // For all student assistants in each course
                for(Student s : course.getStudentAssistants()) {

                    // If this student assistant is the one in arg
                    if(s.getId() == studentId) {

                        // Convert the course to a dto in order to get as little information as possible
                        CourseOut courseDto = new CourseOut();
                        courseDto.setId(course.getCourseId());
                        courseDto.setName(course.getName());
                        courseDto.setCode(course.getCourseCode());
                        courseDto.setMinApprovedAssignments(course.getMinApprovedAssignments());
                        courseDto.setNumberOfAssignments(course.getNumberOfAssignments());

                        // Add the course to list of courses that this student is TA in
                        taCourses.add(courseDto);
                    }
                }
            }
            // Return all courses found
            return new ResponseEntity<>(taCourses, HttpStatus.OK);
        }
        // If student did not exist
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @Override
    public ResponseEntity<Object> getAllCourses() {
        // id, name, code
        List<CourseOut> courses = new ArrayList<>();

        List<Course> fullCourses = courseRepository.findAll();

        for (Course course : fullCourses) {

            // Create dto object
            CourseOut courseOut = new CourseOut();

            // Set necessary attributes
            courseOut.setId(course.getCourseId());
            courseOut.setCode(course.getCourseCode());
            courseOut.setName(course.getName());

            // Add dto to list
            courses.add(courseOut);
        }
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }


    /**
     * Method finds all courses that are not archived and the teacher is given (params)
     * @param teacherId the given teacher
     * @return list of courses that fit the criteria (as DTO)
     */
    @Override
    public ResponseEntity<Object> getAllCoursesByTeacherId(long teacherId) {
        Set<Course> courses = teacherRepository.getCoursesByTeacherId(teacherId);

        List<CourseOut> coursesOut = new ArrayList<>();

        for (Course course : courses) {
            if (!course.isArchived()) {
                // Create dto object
                CourseOut courseOut = new CourseOut();

                // Set necessary attributes
                courseOut.setId(course.getCourseId());
                courseOut.setCode(course.getCourseCode());
                courseOut.setName(course.getName());
                courseOut.setMinApprovedAssignments(course.getMinApprovedAssignments());
                courseOut.setNumberOfAssignments(course.getNumberOfAssignments());
                courseOut.setStartDate(course.getStartDate());
                courseOut.setExpectedEndDate(course.getExpectedEndDate());

                // Add dto to list
                coursesOut.add(courseOut);
            }
        }
        return new ResponseEntity<>(coursesOut, HttpStatus.OK);
    }

    @Override
    public List<GroupOfAssignment> getAllGroupsOfAssignmentByCourseId(long courseId) {
        // If course exists...
        Optional<Course> course = courseRepository.findById(courseId);
        if(course.isPresent()) {
            return courseRepository.getAllGroupsOfAssignmentByCourseId
                    (courseId);
        }
        else {
            return null;
        }
    }

    @Override
    public boolean addGroupOfAssignmentToCourse(GroupIn dto) throws Exception {
        // Get all groups of assignments in the course
        List<GroupOfAssignment> groups = getAllGroupsOfAssignmentByCourseId(dto.getCourseId());

        int newAssignments = 0;
        if(groups != null) {

            // Get the groups from dto
            List<Assignment> newGroup = dto.getGroupOfAssignments();
            newAssignments = newGroup.size();

            // For all groups (could be only one group, but works either way)
            for(GroupOfAssignment group : groups) {
                // For all assignments in the group
                for(Assignment assignment : group.getAssignments()) {
                    // For all assignments in the new group
                    for(Assignment a: newGroup) {
                        // If newGroup contains an assignment with assignment id == assignment id of previous assignment
                        if (Objects.equals(assignment.getAssignmentId(), a.getAssignmentId())) {
                            // Remove the assignment that was there from the start in the course
                            group.removeAssignment(assignment);
                            newAssignments --;
                        }
                    }
                }
            }
            // When all assignments that had to be removed are removed, the new group is added to the course
            GroupOfAssignment goa = new GroupOfAssignment();
            goa.setNumberOfAssignment(dto.getNumOfPractices());
            goa.setOrderNr(dto.getOrderNumber());
            goa.setMinApprovedAssignmentsInGroup(dto.getMinNumApproved());
            goa.setAssignments(newGroup);

            // Set group
            Optional<Course> course = courseRepository.findById(dto.getCourseId());

            // If the course id was present in db
            if(course.isPresent()){
                goa.setCourse(course.get());
                groupOfAssignmentRepository.save(goa);

                // Edit the amount of assignments present in course now
                int previousNumAssignments = course.get().getNumberOfAssignments();
                course.get().setNumberOfAssignments(previousNumAssignments + newAssignments);

                // Edit the min amount approved present in course now
                int previousNumApproved = course.get().getMinApprovedAssignments();
                course.get().setMinApprovedAssignments(previousNumApproved + goa.getMinApprovedAssignmentsInGroup());

                // Update the course
                courseRepository.save(course.get());

                /**
                // Add the course as foreign key
                goa.setCourse(course.get());
                // Add the group to the course as foreign key
                course.get().addGroupOfAssignment(goa);
                 */
            }
            else {
                throw new Exception("The course id: " + dto.getCourseId() + " did not exist.");
            }
        }
        else {
            throw new Exception("The group was null");
        }
        return false;
    }

    /**
     * Deletes the course (not archive)
     *
     * @param courseId is the course to delete
     */
    @Override
    public ResponseEntity<Object> deleteCourse(long courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        if(course.isPresent()) {
            // Due to cascade.remove, there should not be any need to set foreign keys == null...
            Set<Teacher> teachers = courseRepository.getTeachersByCourseId(courseId);

            // For all teachers --> remove foreign key!
            for(Teacher teacher : teachers) {
                Set<Course> teachersCourses = teacher.getCourses();

                // for all courses
                for(Course teachersCourse : teachersCourses) {
                    if(teachersCourse.getCourseId() == courseId) {
                        teacher.getCourses().remove(teachersCourse); //todo Set null if this does not work
                    }
                }
                // Update the teacher
                teacherRepository.save(teacher);
            }

            course.get().setTeachers(null);
            courseRepository.delete(course.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Object> getCourse(long courseId) {
        Optional<Course> course = courseRepository.findById(courseId);

        // If present
        if(course.isPresent()) {

            // Create dto object
            CourseOut courseOut = new CourseOut();

            // Set necessary attributes
            courseOut.setId(course.get().getCourseId());
            courseOut.setCode(course.get().getCourseCode());
            courseOut.setName(course.get().getName());

            return new ResponseEntity<>(courseOut, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
