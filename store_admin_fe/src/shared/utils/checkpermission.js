import { toast } from 'react-toastify'

const checkPermission = (module, _function, next) => {
    debugger;
    const auth = JSON.parse(sessionStorage.getItem("Auth")).data;
    const permission = auth.permission.modules;
    const _module = permission.find(f => f.name_identified === module);
    if (!_module) {
        toast.error("Bạn không có quyền truy cập chức năng này!")
        return
    }
    const __function = _module.functions.find(f => f.name_identified === _function)
    if (!__function) {
        toast.error("Bạn không có quyền truy cập chức năng này!")
        return
    }
    next();
}

export default checkPermission