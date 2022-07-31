import logout_icon from '../../assets/images/icons/logout.svg'
import {logout} from '../../shared/services/authentication.service'
import {toast} from 'react-toastify';
import {changePassword} from '../../shared/services/user.service'
import {useState} from 'react';

const AppHeader = props => {

    const {history} = props

    const  logoutOut = (e) => {
        e.preventDefault();
        logout().then().catch().finally(() => {
            history.push("/login")
        })
    }

    const [user, setUser] = useState(
        sessionStorage.getItem("Auth") ?
        JSON.parse(sessionStorage.getItem("Auth"))
        : {});

    const pageProfile = () => {
        window.location.href = "/profile";
    }

    return (
        <div id="kt_header" className="header header-fixed">
            <div className="container-fluid d-flex align-items-stretch justify-content-between">
                <div className="header-menu-wrapper header-menu-wrapper-left" id="kt_header_menu_wrapper">
                </div>
                <div className="topbar">
                    <div className="dropdown">
                        <div className="topbar-item" data-toggle="dropdown" data-offset="10px,0px">
                            <div className="btn btn-icon w-auto btn-clean d-flex align-items-center btn-lg px-2"
                                 id="kt_quick_user_toggle">
                                <span className="text-muted font-weight-bold font-size-base d-md-inline mr-1">Xin chào, {user.full_name} </span>
                                <span className="text-dark-50 font-weight-bolder font-size-base d-md-inline mr-3"></span>
                                <span className="symbol symbol-35 symbol-light-success">
                                <span className="symbol-label font-size-h5 font-weight-bold">{user && user.full_name ? user.full_name[0] : "A"}</span>
                              </span>
                            </div>
                        </div>
                        <div
                            className="dropdown-menu p-0 m-0 dropdown-menu-anim-up dropdown-menu-sm dropdown-menu-right">
                            <ul className="navi navi-hover py-4">
                                {/*bo xung code*/}
                                <li className="navi-item" onClick={pageProfile}>
                                    <div className="navi-link">
                                        <button type="button" className="btn btn-sm btn-clean btn-icon">
                                              <span className="svg-icon svg-icon-primary svg-icon-2x mr-3">
                                                <img src="assets/media/icon/Key.svg" alt=""/>
                                              </span>
                                        </button>
                                        <span className="navi-text">Tài khoản</span>
                                    </div>
                                </li>
                                <li className="navi-item active">
                                    <div className="navi-link" onClick={logoutOut}>
                                        <span className="svg-icon svg-icon-primary svg-icon-2x mr-3">
                                          <img src={logout_icon} alt=""/>
                                        </span>
                                        <span className="navi-text">Đăng xuất</span>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}
export default AppHeader
