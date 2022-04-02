import { createRouter, createWebHistory } from "vue-router";
import LoginStudent from "../views/LoginStudent.vue";
import LoginAdministrator from "../views/LoginAdministrator.vue";
import HomeAdministrator from "@/views/HomeAdministrator";
import AddNewCourse from "@/views/AddNewCourse";
import HomeStudent from "../views/HomeStudent.vue";
import HomeStudentAss from "../views/HomeStudentAss";
import AssigmentView from "../views/AssigmentView";
import RegisterInLine from "../views/RegisterInLine";
import QueueStudent from "../views/QueueStudent";
import StudentAssQueueApprove from "../views/StudentAssQueueApprove";
import Settings from "@/views/Settings";
import ShowAllStudentsInCourse from "@/views/ShowAllStudentsInCourse";
import LoginTeacher from "@/views/LoginTeacher";

const routes = [
  {
    path: "/",
    name: "Log in as student",
    component: LoginStudent,
  },
  {
    path: "/LogInAdministrator",
    name: "Log in as administrator",
    component: LoginAdministrator,
  },
  {
    path: "/administratorOrTeacher",
    name: "administrator",
    component: HomeAdministrator,
  },
  {
    path: "/addNewCourse",
    name: "addNewCourse",
    component: AddNewCourse,
  },
  {
    path: "/student",
    name: "student",
    component: HomeStudent,
  },
  {
    path: "/studentAss",
    name: "studentAss",
    component: HomeStudentAss,
  },
  {
    path: "/assigmentView",
    name: "assigmentView",
    component: AssigmentView,
  },
  {
    path: "/registerInLine",
    name: "registerInLine",
    component: RegisterInLine,
  },
  {
    path: "/queueStudent",
    name: "queueStudent",
    component: QueueStudent,
  },
  {
    path: "/studentAssQueueApprove",
    name: "studentAssQueueApprove",
    component: StudentAssQueueApprove,
  },
  {
    path: "/settings",
    name: "settings",
    component: Settings,
  },
  {
    path: "/allStudents",
    name: "allStudents",
    component: ShowAllStudentsInCourse,
  },
  {
    path: "/loginTeacher",
    name: "loginTeacher",
    component: LoginTeacher,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
