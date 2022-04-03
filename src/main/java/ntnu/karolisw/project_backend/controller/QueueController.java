package ntnu.karolisw.project_backend.controller;

import ntnu.karolisw.project_backend.dto.in.CourseIn;
import ntnu.karolisw.project_backend.service.interfaces.QueueServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    getAllStudentsInQueue(courseId){
        return apiClient.get('/students/queue' + courseId)
    },
    getAllAssignmentsInCourseForStudentAndIfApproved(courseId, studentId){
        return apiClient.post('/courses/assignments', {courseId : courseId, studentId : studentId})
    },
    postStudentInQueue(typeOfLocation, assignmentNumber, helpOrApproving, campus, building, room, table){
        return apiClient.post('/courses/queue', {typeOfLocation : typeOfLocation, assignmentNumber : assignmentNumber, helpOrApproving : helpOrApproving, campus : campus, building : building, room : room, table : table})

    },
    changeTypeOfActiveQueue(courseId, activeBoolean){
        return apiClient.post('/courses/queue/status', {courseId : courseId, activeBoolean : activeBoolean})

    },
    getTypeActiveQueue(courseId){
        return apiClient.get('/courses/queue/status' + courseId)
    },

        changeStateInQueueForStudent(userId, courseId, state){
        return apiClient.post('/students/queue/changeState', {courseId : courseId, userId : userId, state : state})
        },
        getStateInQueueForStudent(userId, courseId){
        return apiClient.post('/students/queue/getState', {courseId : courseId, userId : userId})
        },
        deleteStudentFromQueue(userId, courseId){
        return apiClient.post('/courses/queue/deleteStudent', {courseId : courseId, userId : userId})
        }

        }
