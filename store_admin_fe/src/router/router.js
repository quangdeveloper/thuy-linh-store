
import Home from "../components/home";
import UserManager from "../components/user/list";
import AddUser from "../components/user/add/AddUser";
import UpdateUser from "../components/user/update/UpdateUser";
import DetailUser from "../components/user/detail/Detail";
import RequestTxnManager from "../components/request_txn/list";

const routes = [
  { path: '/', name: 'home', exact: true, component: Home, meta: { auth: true }, title: "Trang chủ" },

    // user
  { path: '/user', name: 'order manager', exact: true, component: UserManager, meta: { auth: true }, title: "Quản lí user" },
  { path: '/user-add', name: 'order manager', exact: true, component: AddUser, meta: { auth: true }, title: "Quản lí user" },
  { path: '/user-update', name: 'order manager', exact: true, component: UpdateUser, meta: { auth: true }, title: "Quản lí user" },
  { path: '/user-detail', name: 'order manager', exact: true, component: DetailUser, meta: { auth: true }, title: "Quản lí user" },

    //request txn
  { path: '/request-txn', name: 'request txn manager', exact: true, component: RequestTxnManager, meta: { auth: true }, title: "Quản lí lịch hẹn" },
];
export default routes;
