package ntnu.karolisw.project_backend.controller;

import ntnu.karolisw.project_backend.dto.in.CourseIn;
import ntnu.karolisw.project_backend.dto.in.StudentInQueueIn;
import ntnu.karolisw.project_backend.service.interfaces.QueueServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/queues")
@CrossOrigin("http://localhost:8081/") // Vue address
public class QueueController {

    @Autowired
    QueueServiceI queueService;

    /**
     *
     * @param dto contains courseId, userId, assignmentNumber
     * @return
     */
    @PostMapping("/students/assignments")
    public ResponseEntity<Object> approveAssignmentForStudent(CourseIn dto) {
        return queueService.approveStudent(dto.getPersonId(),
                dto.getAssignmentNumber(), dto.getCourseId());
    }

    @GetMapping("/students/queues/{courseId}")
    public ResponseEntity<Object> getAllStudentsInQueue(@PathVariable("courseId") long courseId){
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
    @PostMapping("/newSiq")
    ResponseEntity<Object> postStudentInQueue(@RequestBody StudentInQueueIn dto){
        return queueService.createStudentInQueueEntity(dto);
    }

    /**
     *
     * @param dto contains: courseId, active (boolean for the queue)
     * @return
     */
    @PostMapping("/status")
    ResponseEntity<Object> changeTypeOfActiveQueue(@RequestBody StudentInQueueIn dto){
        return queueService.setQueueActive(dto);
    }

    @GetMapping("/status/{courseId}")
    ResponseEntity<Object> getTypeActiveQueue(@PathVariable("courseId") long courseId) {
        return queueService.isQueueActive(courseId);
    }

    @PostMapping("/changeState")
    ResponseEntity<Object> changeStateInQueueForStudent(@RequestBody StudentInQueueIn dto) {
        return queueService.setStudentState(dto.getStudentId(),dto.getCourseId(),dto.getStatusInQueue());
    }

    @PostMapping("/getState")
    ResponseEntity<Object> getStateInQueueForStudent(@RequestBody StudentInQueueIn dto) {
        return queueService.getStudentState(dto.getStudentId());
    }

    // DTO = studentId,
    @PostMapping("/deleteStudent")
    ResponseEntity<Object> deleteStudentFromQueue(@RequestBody StudentInQueueIn dto) {
        return queueService.deleteStudentInQueueEntity(dto);
    }
}
