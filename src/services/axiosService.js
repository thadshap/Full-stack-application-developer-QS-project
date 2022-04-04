import axios from "axios";

const apiClient = axios.create({
  baseURL: "http://192.168.1.245:8080",
  withCredentials: false,
  headers: {
    Accept: "application/json",
    "Content-Type": "application/json",
  },
});

export default {
    /**
     * administrator and teacher methods
     */
    getTrueIfLoginSuccess(email, password, typeOfUser){
        return apiClient.post('/person/login', {email : email, password :password, typeOfUser : typeOfUser})
    },
    getAllCourses(){
        return apiClient.get('/courses')
    },
    getAllCoursesForTeacher(teacherId){
        return apiClient.get('/courses/teachers/' + teacherId)
    },
    postNewCourse(typeOfUser, courseCode, courseName, startDate, endDate, numOfPractices, numOfApprovedPractices,undergroupsArray){
        return apiClient.post('/courses/addNew', {
            typeOfUser : typeOfUser,
            courseCode : courseCode,
            name : courseName,
            startDate : startDate,
            expectedEndDate : endDate,
            numberOfAssignments : numOfPractices,
            minApprovedAssignments : numOfApprovedPractices,
            groupsOfAssignments : undergroupsArray
        })
    },
    getPronouns(userId, typeOfUser){
        return apiClient.post('/person/getPronouns', {id : userId, typeOfUser :typeOfUser})
    },

    postPronouns(userId, typeOfUser, pronouns){
        return apiClient.post('/person/postPronouns', {
            id : userId,
            typeOfUser : typeOfUser,
            pronouns : pronouns
        })
    },
    deleteCourse(courseId){
        return apiClient.delete('/courses/' + courseId)
    },
    archiveCourse(courseId){
        return apiClient.post('/courses/archive', {courseId :courseId})
    },
    addStudentToCourse(courseId, email, firstName, lastName){
        return apiClient.post('/courses/addStudent', {
            courseId : courseId,
            email : email,
            firstName : firstName,
            lastName : lastName})
    },
    addTeacherToCourse(courseId, email, firstName, lastName){
        return apiClient.post('/courses/addTeacher', {
            courseId : courseId,
            email : email,
            firstName : firstName,
            lastName : lastName
        })
    },
    addStudentAssistant(courseId, email, firstName, lastName){
        return apiClient.post('/courses/addStudentAssistant', {
            courseId : courseId,
            email : email,
            firstName : firstName,
            lastName : lastName
        })
    },
    deleteStudentFromCourse(courseId, email){
        return apiClient.post('/courses/removeStudent', {courseId : courseId, email : email})
    },
    getAllStudentsInCourse(courseId){
        return apiClient.get('/courses/students/' + courseId)
    },
    getCourseById(courseId){
        return apiClient.get('/courses/' + courseId)
    },
    /**
     * Student methods
     */
    getAllCoursesForStudent(id){
        return apiClient.get('/courses/student/' + id)
    },
    getAllCoursesForStudentAssistant(id){
        return apiClient.get('/courses/studentAssistant/' + id)
    },
    getAllStudentsInQueue(courseId){
        return apiClient.get('/students/queue/' + courseId)
    },
    getAllAssignmentsInCourseForStudentAndIfApproved(courseId, studentId){
        return apiClient.post('/queues/newSiq', {courseId : courseId, id : studentId})
    },
    postStudentInQueue(courseId, userId, typeOfLocation, helpOrApproving, campus, building, room, table){
        return apiClient.post('/courses/queue', {courseId : courseId, id : userId, digital : typeOfLocation, assessmentHelp : helpOrApproving, campus : campus, building : building, room : room, tableNumber : table})
    },
    changeTypeOfActiveQueue(courseId, activeBoolean){
        return apiClient.post('/courses/queue/status', {courseId : courseId, active : activeBoolean})

    },
    getTypeActiveQueue(courseId){
        return apiClient.get('/courses/queue/status/' + courseId)
    },
    approveAssignmentForStudent(courseId, userId, assignmentNumber){
        return apiClient.post('/students/assignments', {courseId : courseId, id : userId, assignmentNumber : assignmentNumber})

    },
    changeStateInQueueForStudent(userId, courseId, state){
        return apiClient.post('/students/queue/changeState', {courseId : courseId, id : userId, statusInQueue : state})
    },
    deleteStudentFromQueue(userId, courseId){
        return apiClient.post('/courses/queue/deleteStudent', {courseId : courseId, id : userId})
    }
}
