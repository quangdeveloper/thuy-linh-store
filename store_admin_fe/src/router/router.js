
import Home from "../components/home";
import UserManager from "../components/user/list";
import AddUser from "../components/user/add/AddUser";
import UpdateUser from "../components/user/update/UpdateUser";
import CourseManager from "../components/course/list";
import AddCourse from "../components/course/add/AddCourse";
import UpdateCourse from "../components/course/update/UpdateCourse";
import DetailCourse from "../components/course/detail/DetailCourse";
import RegisterManager from "../components/register/list";
import RegisterNewManager from "../components/register/list_new_register";
import RegisterAccepted from "../components/register/register-accept";
import RegisterCancel from "../components/register/register-cancel";
import TeacherManager from "../components/teacher/list";
import UpdateTeacher from "../components/teacher/update/UpdateTeacher";
import AddTeacher from "../components/teacher/add/AddTeacher";
import DetailRegister from "../components/register/detail/DetailRegister";

const routes = [
  { path: '/', name: 'home', exact: true, component: Home, meta: { auth: true }, title: "Trang chủ" },
  { path: '/user', name: 'order manager', exact: true, component: UserManager, meta: { auth: true }, title: "Quản lí user" },
  { path: '/user-add', name: 'order manager', exact: true, component: AddUser, meta: { auth: true }, title: "Quản lí user" },
  { path: '/user-update', name: 'order manager', exact: true, component: UpdateUser, meta: { auth: true }, title: "Quản lí user" },
  { path: '/course', name: 'order manager', exact: true, component: CourseManager, meta: { auth: true }, title: "Quản lí user" },
  { path: '/course-add', name: 'order manager', exact: true, component: AddCourse, meta: { auth: true }, title: "Quản lí user" },
  { path: '/course-update', name: 'order manager', exact: true, component: UpdateCourse, meta: { auth: true }, title: "Quản lí user" },
  { path: '/course-detail/:id', name: 'order manager', exact: false, component: DetailCourse, meta: { auth: true }, title: "Quản lí user" },
  { path: '/register', name: 'order manager', exact: true, component: RegisterManager, meta: { auth: true }, title: "Quản lí user" },
  { path: '/register-new', name: 'order manager', exact: true, component: RegisterNewManager, meta: { auth: true }, title: "Quản lí user" },
  { path: '/register-accept', name: 'order manager', exact: true, component: RegisterAccepted, meta: { auth: true }, title: "Quản lí user" },
  { path: '/register-cancel', name: 'order manager', exact: true, component: RegisterCancel, meta: { auth: true }, title: "Quản lí user" },
  { path: '/register-detail', name: 'order manager', exact: true, component: DetailRegister, meta: { auth: true }, title: "Quản lí user" },
  { path: '/teacher', name: 'order manager', exact: true, component: TeacherManager, meta: { auth: true }, title: "Quản lí user" },
  { path: '/teacher-add', name: 'order manager', exact: true, component: AddTeacher, meta: { auth: true }, title: "Quản lí user" },
  { path: '/teacher-update', name: 'order manager', exact: true, component: UpdateTeacher, meta: { auth: true }, title: "Quản lí user" },
];
export default routes;
