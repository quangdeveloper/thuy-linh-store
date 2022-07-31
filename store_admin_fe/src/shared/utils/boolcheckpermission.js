import {toast} from "react-toastify";

const boolcheckpermission = (module, _function) => {
    const auth = JSON.parse(sessionStorage.getItem("Auth"));
    const permission = auth.permission.modules;
    const _module = permission.find(f => f.name_identified === module);
    if (!_module) {
        return false
    }
    const __function = _module.functions.find(f => f.name_identified === _function)
    if (!__function) {
        return false
    }
    return true
}

const messageNoPermission = () =>{
    toast.error("Bạn không có quyền truy cập chức năng này!");
}



export  {
    boolcheckpermission,
    messageNoPermission
}
