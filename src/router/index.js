import { createRouter, createWebHistory } from "vue-router";
import LoginStudent from "../views/LoginStudent.vue";
import LoginAdministrator from "../views/LoginAdministrator.vue";
import HomeAdministrator from "@/views/HomeAdministrator";
import AddNewCourse from "@/views/AddNewCourse";
import HomeStudent from "../views/HomeStudent.vue";


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
    path: "/addNewCourse",
    name: "addNewCourse",
    component: AddNewCourse,
  },
  {
    path: "/student",
    name: "student",
    component: HomeStudent,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
