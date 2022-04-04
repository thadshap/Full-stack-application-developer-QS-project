package ntnu.karolisw.project_backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ntnu.karolisw.project_backend.model.Course;
import ntnu.karolisw.project_backend.repository.CourseRepository;
import ntnu.karolisw.project_backend.service.interfaces.CourseServiceI;
import ntnu.karolisw.project_backend.service.interfaces.QueueServiceI;
import ntnu.karolisw.project_backend.service.interfaces.UserServiceI;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Spring Boot @WebMvcTest annotation provides a way to test Rest Controllers
 * It disables full auto-configuration, meaning @Component, @Service AND @Repository
 * beans will not be scanned. It also applies configuration relevant to the web layer (@controller)
 */
@WebMvcTest(CourseController.class)
public class CourseControllerTest {

    /**
     * The @MockBean annotation creates and inject a mock of the repository
     */
    @MockBean
    CourseRepository courseRepository;

    @MockBean
    CourseServiceI courseService;

    @MockBean
    UserServiceI userService;

    @MockBean
    QueueServiceI queueService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    Date startDate;
    Date endDate;

    @Before
    public void start() {
        startDate = new Date();
        endDate = new Date();
    }


    @Test
    void postNewCourse() throws Exception{

        // Creating the new course
        Course course = new Course();

        // Adding the attributes
        course.setCourseId(1L);
        course.setMinApprovedAssignments(7);
        course.setNumberOfAssignments(9);
        course.setArchived(false);
        course.setCourseCode("IDATT25");
        course.setName("Database for tøffinger");
        course.setStartDate(startDate);
        course.setExpectedEndDate(endDate);

        // Mocking a post command from frontend (format == JSON)
        String courseAsString = objectMapper.writeValueAsString(course);
        System.out.println(courseAsString);

        mockMvc.perform(post("/courses/addNew")
                .contentType(MediaType.APPLICATION_JSON)
                .content(courseAsString))
                .andExpect(status().isOk())
                .andDo(print());
    }

    /**
     * This test uses method for posting new course (from earlier)
     * and then tries to get a course. For this method to work,
     * the Http post entity (request header) must return the object created
     * along with the header!
     *
     * @throws Exception
     */
    @Test
    void getCourseById() throws Exception {
        long courseId = 1L;
        Course course = new Course();

        // Adding the attributes
        course.setCourseId(courseId);
        course.setMinApprovedAssignments(7);
        course.setNumberOfAssignments(9);
        course.setArchived(false);
        course.setCourseCode("IDATT25");
        course.setName("Database for tøffinger");
        course.setStartDate(startDate);
        course.setExpectedEndDate(endDate);


        // Testing that when retrieving the course, we get the desired answer
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));

        mockMvc.perform(get("/courses/{courseId}",courseId)).andExpect(status().isOk())
                .andExpect(jsonPath("$.data.courseId").value(courseId))
                .andExpect(jsonPath("$.minApprovedAssignments").value(course.getMinApprovedAssignments()))
                .andExpect(jsonPath("$.numberOfAssignments").value(course.getNumberOfAssignments()))
                .andExpect(jsonPath("$.archived").value(course.isArchived()))
                .andExpect(jsonPath("$.courseCode").value(course.getCourseCode()))
                .andExpect(jsonPath("$.name").value(course.getName()))
                .andExpect(jsonPath("$.startDate").value(course.getStartDate()))
                .andExpect(jsonPath("$.expectedEndDate").value(course.getExpectedEndDate()))

                .andDo(print());


    }

    @Test
    void deleteCourse() throws Exception {
        long id = 1L;
        doNothing().when(courseRepository).deleteById(id);
        mockMvc.perform(delete("/courses/{id}", id))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void archiveCourse() {

    }

    @Test
    void addStudentToCourse() {
    }
}
