import { createStore } from "vuex";

export default createStore({
  state: {
    typeOfUser : 0, // 1 is student, 2 is teacher, 3 is admin
    userId : 0,
    courseId : 0,
    email : '',
    course: {},
    studentInQueueApproval:[], //this array is for the info about a student that has been taken out of the queue for approval
    studentInQueueWait:[], //this array is for the info about a student that has waitlisted for approval
    allSubjectsToAStudent:[],
    allSubjectsToAStudentAss: [],
    queueStatus: false, //status of the queue, aka if the queue is activated or deactivated
  },
  mutations: {
    SET_COURSE(state, course) {
      state.course = course;
    },
    SET_COURSECODE(state, coursecode) {
      state.courseCode = coursecode
    },
    SET_COURSENAME(state, coursename) {
      state.courseName = coursename
    },
    SET_TYPEOFUSER(state, type) {
      state.typeOfUser = type
    },
    SET_USERID(state, userID) {
      state.userId = userID
    },
    SET_COURSEID(state, courseID) {
      state.courseId = courseID
    },
    SET_EMAIL(state, email) {
      state.email = email
    },
    SET_QUEUE_STATUS(state, status){
      state.queueStatus = status
      console.log(state.queueStatus)
    },
  },
  actions: {
  },
  modules: {},
});
