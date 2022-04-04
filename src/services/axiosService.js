import axios from 'axios'

const apiClient = axios.create({
    baseURL: 'http://192.168.1.245:8080',
    withCredentials: false,
    headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json'
    }
})

export default {
    //administrator and teacher methods
//want to get out{loggedIn : boolean, id : userId}
    getTrueIfLoginSuccess(email, password, typeOfUser){
        return apiClient.post('/person/login', {email : email, password :password, typeOfUser : typeOfUser})
    },
//want to get out entire course object
    getAllCourses(){
        return apiClient.get('/courses')
    },
//want to get the entire course object
    getAllCoursesForTeacher(teacherId){
        return apiClient.get('/courses/teachers' + teacherId)
    },
//no return needed
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
//want to get {pronouns : "string"}
    getPronouns(userId, typeOfUser){
        return apiClient.post('/person/getPronouns', {id : userId, typeOfUser :typeOfUser})
    },
//jeg sender ikke et objekt
//no return needed
    postPronouns(userId, typeOfUser, pronouns){
        return apiClient.post('/person/postPronouns', {
            id : userId,
            typeOfUser : typeOfUser,
            pronouns : pronouns
        })
    },
//no return needed
    deleteCourse(courseId){
        return apiClient.delete('/courses/' + courseId)
    },
//sender ikke inn et helt fag
//no return needed
    archiveCourse(courseId){
        return apiClient.post('/courses/archive', {courseId :courseId})
    },
//sender ikke inn et helt objekt
//no return needed
    addStudentToCourse(courseId, email, firstName, lastName){
        return apiClient.post('/courses/addStudent', {
            courseId : courseId,
            email : email,
            firstName : firstName,
            lastName : lastName})
    },
//sender ikke inn et helt objekt
//no return needed
    addTeacherToCourse(courseId, email, firstName, lastName){
        return apiClient.post('/courses/addTeacher', {
            courseId : courseId,
            email : email,
            firstName : firstName,
            lastName : lastName
        })
    },
//sender ikke inn et helt objekt
//no return needed
    addStudentAssistant(courseId, email, firstName, lastName){
        return apiClient.post('/courses/addStudentAssistant', {
            courseId : courseId,
            email : email,
            firstName : firstName,
            lastName : lastName
        })
    },
//sender ikke inn et helt objekt
//no return needed
    deleteStudentFromCourse(courseId, email){
        return apiClient.post('/courses/removeStudent', {courseId : courseId, email : email})
    },
//get list with the entire student object
    getAllStudentsInCourse(courseId){
        return apiClient.get('/courses/students/' + courseId)
    },
//get entire course
    getCourseById(courseId){
        return apiClient.get('/courses/' + courseId)
    },
//Student methods
// get list of entire course object
    getAllCoursesForStudent(id){
        return apiClient.get('/courses/student/' + id)
    },
// get list of entire course object
    getAllCoursesForStudentAssistant(id){
        return apiClient.get('/courses/studentAssistant/' + id)
    },
// get list of entire student object
    getAllStudentsInQueue(courseId){
        return apiClient.get('/students/queue/' + courseId)
    },
//get list of assignments with { assignmentNumber : number, approved : boolean}
    getAllAssignmentsInCourseForStudentAndIfApproved(courseId, studentId){
        return apiClient.post('/queues/newSiq', {courseId : courseId, id : studentId})
    },
//no return needed
    postStudentInQueue(courseId, userId, typeOfLocation, helpOrApproving, campus, building, room, table){
        return apiClient.post('/courses/queue', {courseId : courseId, id : userId, digital : typeOfLocation, assessmentHelp : helpOrApproving, campus : campus, building : building, room : room, tableNumber : table})
    },
//no return needed
    changeTypeOfActiveQueue(courseId, activeBoolean){
        return apiClient.post('/courses/queue/status', {courseId : courseId, active : activeBoolean})

    },
//{active : boolean}
    getTypeActiveQueue(courseId){
        return apiClient.get('/courses/queue/status' + courseId)
    },
//no return needed
    approveAssignmentForStudent(courseId, userId, assignmentNumber){
        return apiClient.post('/students/assignments', {courseId : courseId, id : userId, assignmentNumber : assignmentNumber})

    },
//no return needed
    changeStateInQueueForStudent(userId, courseId, state){
        return apiClient.post('/students/queue/changeState', {courseId : courseId, id : userId, statusInQueue : state})
    },
//no return needed
    deleteStudentFromQueue(userId, courseId){
        return apiClient.post('/courses/queue/deleteStudent', {courseId : courseId, id : userId})
    }
}
