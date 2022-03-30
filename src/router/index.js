import { createRouter, createWebHistory } from "vue-router";
import LoginStudent from "../views/LoginStudent.vue";
import LoginAdministrator from "../views/LoginAdministrator.vue";
import HomeAdministrator from "@/views/HomeAdministrator";
import Footer from "@/components/Footer";
import HomeStudent from "../views/HomeStudent.vue";
import HomeStudentAss from "../views/HomeStudentAss";
import AssigmentView from "../views/AssigmentView";
import RegisterInLine from "../views/RegisterInLine";
import QueueStudent from "../views/QueueStudent";
import QueueStudentAss from "../views/QueueStudentAss";

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
    path: "/administrator",
    name: "administrator",
    component: HomeAdministrator,
  },
  {
    path: "/test",
    name: "t",
    component: Footer,
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
    path: "/queueStudentAss",
    name: "queueStudentAss",
    component: QueueStudentAss,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
