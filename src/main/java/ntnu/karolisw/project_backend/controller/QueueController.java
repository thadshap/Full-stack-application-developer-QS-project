package ntnu.karolisw.project_backend.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import ntnu.karolisw.project_backend.dto.in.CourseIn;
import ntnu.karolisw.project_backend.dto.in.StudentInQueueIn;
import ntnu.karolisw.project_backend.service.interfaces.QueueServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/queues")
@CrossOrigin("http://192.168.1.80:8081/") // Vue address
public class QueueController {

    @Autowired
    QueueServiceI queueService;

    /**
     *
     * @param dto contains courseId, userId, assignmentNumber
     * @return
     */
    @ApiOperation(value = "Approve assignment for student",
            notes = "Sets boolean approved true for a single assignment in one course for one student",
            response = QueueController.class)
    @PostMapping("/students/assignments")
    public ResponseEntity<Object> approveAssignmentForStudent(@ApiParam(value = "CourseIn dto")
                                                                          CourseIn dto) {
        return queueService.approveStudent(dto.getPersonId(),
                dto.getAssignmentNumber(), dto.getCourseId());
    }

    @ApiOperation(value = "Get all students in a queue",
            notes = "Sends a list of all the students currently registered in a queue for a course",
            response = QueueController.class)
    @GetMapping("/students/queues/{courseId}")
    public ResponseEntity<Object> getAllStudentsInQueue(@ApiParam(value = "ID value of the course")
                                                            @PathVariable("courseId") long courseId){
        return queueService.getAllStudentsInQueue(courseId);
    }

    /**
     *
     * @param dto contains:
     *            - typeOfLocation
     *            - assignmentNumber
     *            - helpOrApproving
     *            - campus
     *            - building
     *            - room
     *            - table
     * @return
     */
    @ApiOperation(value = "Add a student to a queue",
            notes = "Registrating a student in a queue for a course",
            response = QueueController.class)
    @PostMapping("/newSiq")
    ResponseEntity<Object> postStudentInQueue(@ApiParam(value = "StudentInQueueIn dto, information about student in queue object")
                                              @RequestBody StudentInQueueIn dto){
        return queueService.createStudentInQueueEntity(dto);
    }

    /**
     *
     * @param dto contains: courseId, active (boolean for the queue)
     * @return
     */
    @ApiOperation(value = "Change if a queue is active or not",
            notes = "Changing a boolean to show if a queue is active or not, meaning if a student can register itself in it or not",
            response = QueueController.class)
    @PostMapping("/status")
    ResponseEntity<Object> changeTypeOfActiveQueue(@ApiParam(value = "StudentInQueueIn dto, contains courseId and active boolean")
                                                   @RequestBody StudentInQueueIn dto){
        return queueService.setQueueActive(dto);
    }

    @ApiOperation(value = "Get status of queue (active or not)",
            notes = "Sending a boolean to show if a queue is active or not",
            response = QueueController.class)
    @GetMapping("/status/{courseId}")
    ResponseEntity<Object> getTypeActiveQueue(@ApiParam(value = "ID value of the course")
                                              @PathVariable("courseId") long courseId) {
        return queueService.isQueueActive(courseId);
    }

    @ApiOperation(value = "changing what state a student has in the queue",
            notes = "changing the state, either 'BUSY', 'WAITING' or 'AVAILIBLE' for the state of the student in the queue",
            response = QueueController.class)
    @PostMapping("/changeState")
    ResponseEntity<Object> changeStateInQueueForStudent(@ApiParam(value = "StudentInQueueIn dto, contains information about the student in queue object")
                                                        @RequestBody StudentInQueueIn dto) {
        return queueService.setStudentState(dto.getStudentId(),dto.getCourseId(),dto.getStatusInQueue());
    }

    @ApiOperation(value = "getting what state a student has in the queue",
            notes = "Sending back a string, either 'BUSY', 'WAITING' or 'AVAILIBLE' for the state of the student in the queue",
            response = QueueController.class)
    @PostMapping("/getState")
    ResponseEntity<Object> getStateInQueueForStudent(@ApiParam(value = "StudentInQueueIn dto, contains information about the student in queue object")
                                                     @RequestBody StudentInQueueIn dto) {
        return queueService.getStudentState(dto.getStudentId());
    }

    @ApiOperation(value = "removing a student from a queue in a course",
            notes = "removing a student by the studentId from a specific queue in a course",
            response = QueueController.class)
    @PostMapping("/deleteStudent")
    ResponseEntity<Object> deleteStudentFromQueue(@ApiParam(value = "StudentInQueueIn dto, contains studentId")
                                                  @RequestBody StudentInQueueIn dto) {
        return queueService.deleteStudentInQueueEntity(dto);
    }
}
