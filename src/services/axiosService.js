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
        return apiClient.get('/person/login',  {email : email, password :password, typeOfUser : typeOfUser})
    },
    getAllCourses(){
        return apiClient.get('/courses')
    },
    getAllCoursesForTeacher(teacherId){
        return apiClient.get('/courses/' + teacherId)
    },
    postNewCourse(courseCode, courseName, startDate, endDate, numOfPractices, numOfApprovedPractices, numOfUnderGroups, undergroupsArray){
        return apiClient.post('/courses/addNew', {courseCode, courseName, startDate, endDate, numOfPractices, numOfApprovedPractices, numOfUnderGroups, undergroupsArray})
    },
    getPronouns(userId){
        return apiClient.get('/person/pronouns', userId)
    },
    postPronouns(userId){
        return apiClient.post('/person/pronouns', userId)
    },
    deleteCourse(courseId){
        return apiClient.delete('/courses/' + courseId)
    },
    archiveCourse(courseId){
        return apiClient.post('/courses/archive', courseId)
    },
    addStudentToCourse(courseId, email, firstName, lastName){
        return apiClient.post('/person/addStudent', {courseId, email, firstName, lastName})
    },
    addTeacherToCourse(courseId, email, firstName, lastName){
        return apiClient.post('/person/addTeacher', {courseId, email, firstName, lastName})
    },
    addStudentAssistant(courseId, email, firstName, lastName){
        return apiClient.post('/person/addStudentAssistant', {courseId, email, firstName, lastName})
    },
    deleteStudentFromCourse(courseId, email){
        return apiClient.delete('/courses/deleteStudent', {courseId : courseId, email : email})
    },
    getAllStudentsInCourse(courseId){
        return apiClient.get('/courses/students' + courseId)
    },
    getCourseById(courseId){
        return apiClient.get('/courses/' + courseId)
    }
}
