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
    getTrueIfLoginSuccess(email, password, typeOfUser){
        return apiClient.post('/person/login', {email : email, password :password, typeOfUser : typeOfUser})
    },
    getAllCourses(){
        return apiClient.get('/courses')
    },
    getAllCoursesForTeacher(teacherId){
        return apiClient.get('/courses/' + teacherId)
    },
    postNewCourse(courseCode, courseName, startDate, endDate, numOfPractices, numOfApprovedPractices,undergroupsArray){
        return apiClient.post('/courses/addNew', {
            courseCode : courseCode,
            courseName : courseName,
            startDate : startDate,
            endDate : endDate,
            numOfPractices : numOfPractices,
            numOfApprovedPractices : numOfApprovedPractices,
            undergroupsArray : undergroupsArray
        })
    },
    getPronouns(userId, typeOfUser){
        return apiClient.post('/person/getPronouns', {userId : userId, typeOfUser :typeOfUser})
    },
    postPronouns(userId, typeOfUser, pronouns){
        return apiClient.post('/person/postPronouns', {
            userId : userId,
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
        return apiClient.post('/person/addStudent', {
            courseId : courseId,
            email : email,
            firstName : firstName,
            lastName : lastName})
    },
    addTeacherToCourse(courseId, email, firstName, lastName){
        return apiClient.post('/person/addTeacher', {
            courseId : courseId,
            email : email,
            firstName : firstName,
            lastName : lastName
        })
    },
    addStudentAssistant(courseId, email, firstName, lastName){
        return apiClient.post('/person/addStudentAssistant', {
            courseId : courseId,
            email : email,
            firstName : firstName,
            lastName : lastName
        })
    },
    deleteStudentFromCourse(courseId, email){
        return apiClient.post('/courses/deleteStudent', {courseId : courseId, email : email})
    },
    getAllStudentsInCourse(courseId){
        return apiClient.get('/courses/students' + courseId)
    },
    getCourseById(courseId){
        return apiClient.get('/courses/' + courseId)
    }
}
