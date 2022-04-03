import { createStore } from "vuex";

export default createStore({
  state: {
    typeOfUser : 0, // 1 is student, 2 is teacher, 3 is admin
    userId : 0,
    courseId : 0,
    email : '',
    allSubjectsToAStudent:[],
    allSubjectsToAStudentAss: [],
    queueStatus: false, //status of the queue, aka if the queue is activated or deactivated
  },
  mutations: {
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
