package ntnu.karolisw.project_backend.service.classes;

import ntnu.karolisw.project_backend.dto.in.CourseIn;
import ntnu.karolisw.project_backend.dto.in.PersonIn;
import ntnu.karolisw.project_backend.model.Course;
import ntnu.karolisw.project_backend.model.Student;
import ntnu.karolisw.project_backend.repository.CourseRepository;
import ntnu.karolisw.project_backend.service.interfaces.CourseServiceI;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseServiceTest {

    @Autowired
    CourseServiceI courseService;

    @Autowired
    CourseRepository courseRepository;

    /**
     * Test fails automatically due to not null arguments of course not being fulfilled
     */
    @Test
    void testThatAutomaticallyFails() {
        // Creating not-complete dto object
        CourseIn dto = new CourseIn();
        courseService.createCourse(dto);
    }

    @Test
    void markAsArchived() {

    }

    @Test
    void archiveCourse() {
    }

    @Test
    void createCourse() {
        Course course = new Course();
        // Adding the attributes
        course.setMinApprovedAssignments(7);
        course.setNumberOfAssignments(9);
        course.setArchived(false);
        course.setCourseCode("IDATT25");
        course.setName("Database for tøffinger");
        course.setStartDate(new Date());
        course.setExpectedEndDate(new Date());

        // Post the course!
        courseRepository.save(course);

        // Assert that a course with this id exists
        assertEquals(courseRepository.findById(60L).get().getCourseCode(),
                course.getCourseCode());
    }

    @Test
    void getAllStudentsInCourse() {
        List<Course> students = courseRepository.findAll();
        assertNotEquals(students.size(),0);
    }

    @Test
    void addTeacher() {
    }

    @Test
    void addStudent() {
        // Create dto as if it had been made from frontend
        PersonIn dto = new PersonIn();
        dto.setFirstName("karoline");
        dto.setCourseId(16L);
        dto.setLastName("wahl");
        dto.setEmail("hello@hadebra.no");

        // Add student to course
        courseService.addStudentToCourse(dto);
    }

    @Test
    void removeStudent() {
    }

    @Test
    void updateStartDate() {
    }

    @Test
    void updateEndDate() {
    }

    @Test
    void updateNumberOfAssignments() {
    }

    @Test
    void updateMinApprovedAssignments() {
    }

    @Test
    void updateNumberPartsAssignments() {
    }

    @Test
    void getNumberOfApprovedAssignmentsByCourse() {
    }

    @Test
    void getAllAssignmentsForStudent() {
    }

    @Test
    void getAllAssignmentsForStudentInCourse() {
    }

    @Test
    void getAllApprovedAssignmentsForStudent() {
    }

    @Test
    void getAllCoursesForStudent() {
    }

    @Test
    void getAllCourses() {
    }

    @Test
    void getAllCoursesByTeacherId() {
    }

    @Test
    void getAllGroupsOfAssignmentByCourseId() {
    }

    @Test
    void addGroupOfAssignmentToCourse() {
    }

    @Test
    void deleteCourse() {
    }
}
