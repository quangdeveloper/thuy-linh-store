import {Formik} from 'formik';
import React, {useEffect, useState} from 'react'
import {toast} from "react-toastify";
import {changePassword, updateUser} from "../../shared/services/user.service";
import AppAside from "../../containers/menu/AppAside";
import AppHeader from "../../containers/header/AppHeader";
import { Fragment } from 'react'
const AccountInfo = (props) => {

    const validate = values => {

        const errors = {};

        if (!values.user_name || values.user_name == "") {
            errors.user_name = 'Vui lòng nhập tên đăng nhập';
        } else if (values.user_name.length < 3 || values.user_name.length > 50) {
            errors.user_name = 'Tên đăng nhập không hợp lệ';
        } else if (!(/^([^ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ ])+$/.test(values.user_name))) {
            errors.user_name = 'Tên đăng nhập không hợp lệ';
        }

        if (!values.full_name || values.full_name == "") {
            errors.full_name = 'Vui lòng nhập họ và tên';
        } else if (values.full_name.length < 4 || values.full_name.length > 200) {
            errors.full_name = 'Tên đăng nhập không hợp lệ';
        }

        if (!values.email || values.email == "") {
            errors.email = 'Vui lòng nhập email';
        } else if (!/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/.test(values.email)) {
            errors.email = 'Email không hợp lệ.';
        }

        if (!values.mobile || values.mobile == "") {
            errors.mobile = 'Vui lòng nhập số điện thoại';
        } else if (!/^[0-9]{3,11}$/.test(values.mobile)) {
            errors.mobile = 'Số điện thoại không hợp lệ';
        }

        if (isChangePassword) {

            if (!values.password_hash || values.password_hash == "") {
                errors.password_hash = 'Vui lòng nhập mật khẩu';
            } else if (!(/^([^ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ ])+$/.test(values.password_hash))) {
                errors.password_hash = 'Mật khẩu không hợp lệ.';
            } else if (values.password_hash.length > 50 || values.password_hash.length < 4) {
                errors.password_hash = 'Mật khẩu không hợp lệ.';
            }

            if (!values.old_password || values.old_password == "") {
                errors.old_password = 'Vui lòng nhập mật khẩu';
            } else if (!(/^([^ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ ])+$/.test(values.old_password))) {
                errors.old_password = 'Mật khẩu không hợp lệ.';
            } else if (values.old_password.length > 50 || values.old_password.length < 4) {
                errors.old_password = 'Mật khẩu không hợp lệ.';
            }

            if (values.re_password != values.password_hash) {
                errors.re_password = 'Mật khẩu nhập lại không khớp';
            }
        }
        return errors
    };

    const [user, setUser] = useState(JSON.parse(sessionStorage.getItem("Auth")));
    const [isChangePassword, setIsChangePassword] = useState(false);

    const initValue = {
        user_name: user ? user.user_name : "",
        full_name: user ? user.full_name : "",
        email: user ? user.email : "",
        mobile: user ? user.mobile : "",
        role_name: user ? user.permission.roles[0].name : "",
        old_password: "",
        password_hash: "",
        re_password: "",
    };

    const _changePage = () =>{
        window.location.href = "/"
    }

    const _handleChangePassword = () => {
        setIsChangePassword(!isChangePassword);
    }

    const createOrUpdateUser = (data) => {

        if (isChangePassword) {
            changePassword({...data, code: user.code}).then(res => {
                if (res.data.code == 0) {
                    updateUser({...data, code: user.code}).then(res => {
                        if (res.data.code == 0) toast.success(res.data.message);
                        else toast.error(res.data.message);
                    })
                }
                else toast.error(res.data.message);
            })
        } else {
            updateUser({...data, code: user.code}).then(res => {
                if (res.data.code == 0) toast.success(res.data.message);
                else toast.error(res.data.message);
            })
        }
    }

    return (
        <Fragment>
            <div className="d-flex flex-row flex-column-fluid page">
                <AppAside {...props} />
                <div className="d-flex flex-column flex-row-fluid wrapper" id="kt_wrapper">
                    <AppHeader {...props} />

                    <div className="content d-flex flex-column flex-column-fluid" id="kt_content">
                        <div className="d-flex flex-column-fluid">
                            <div className="container">
                                <div className="mb-10 mt-lg-n10 mt-10">
                                    <h1>Thông tin tài khoản</h1>
                                </div>

                                <div className="card card-custom">
                                    <div className="card-header flex-wrap border-0 pt-6 pb-0">
                                        <div className="card-title">
                                            <h3 className="card-label">Thông tin tài khoản</h3>
                                        </div>
                                    </div>
                                    <div className="card-body">
                                        <Formik
                                            initialValues={initValue}
                                            validate={validate}
                                            onSubmit={(values) => {
                                                const data = {
                                                    user_name: values.user_name,
                                                    old_password: isChangePassword ? values.old_password : null,
                                                    password_hash: isChangePassword ? values.password_hash : null,
                                                    role_code: user.permission.roles[0].code,
                                                    full_name: values.full_name,
                                                    email: values.email,
                                                    mobile: values.mobile
                                                };
                                                createOrUpdateUser(data);
                                            }}
                                        >{({
                                               values,
                                               errors,
                                               touched,
                                               handleChange,
                                               handleBlur,
                                               handleSubmit,
                                           }) => (
                                            <div className="modal-body">
                                                <form className="form" onSubmit={handleSubmit}>
                                                    <div className="row">

                                                        <div className="col-lg-6">
                                                            <div className="form-group">
                                                                <label>Tên đăng nhập</label>
                                                                <input type="text" readOnly={true}
                                                                       name="user_name"
                                                                       className="form-control"
                                                                       value={values.user_name}
                                                                       onBlur={handleBlur}
                                                                       onChange={handleChange}/>
                                                            </div>
                                                        </div>
                                                        <div className="col-lg-6">
                                                            <div className="form-group">
                                                                <label>Phân quyền</label>
                                                                <input type="text" readOnly={true}
                                                                       name="role_name"
                                                                       className="form-control"
                                                                       value={values.role_name}
                                                                       onBlur={handleBlur}
                                                                       onChange={handleChange}/>
                                                            </div>
                                                        </div>
                                                        <div className="col-lg-6">
                                                            <div className="form-group">
                                                                <label>Họ và tên</label>
                                                                <input type="text" placeholder="Nhập họ và tên"
                                                                       name="full_name"
                                                                       className="form-control"
                                                                       value={values.full_name}
                                                                       onBlur={handleBlur}
                                                                       onChange={handleChange}/>
                                                                <span
                                                                    style={{color: "red"}}>{touched.full_name && errors.full_name ? errors.full_name : null}</span>
                                                            </div>
                                                        </div>
                                                        <div className="col-lg-6">
                                                            <div className="form-group">
                                                                <label>Email</label>
                                                                <input type="text" placeholder="Nhập email"
                                                                       name="email"
                                                                       className="form-control"
                                                                       value={values.email}
                                                                       onBlur={handleBlur}
                                                                       onChange={handleChange}/>
                                                                <span
                                                                    style={{color: "red"}}>{touched.email && errors.email ? errors.email : null}</span>
                                                            </div>
                                                        </div>
                                                        <div className="col-lg-6">
                                                            <div className="form-group">
                                                                <label>Số điện thoại</label>
                                                                <input type="text" placeholder="Nhập số điện thoại"
                                                                       name="mobile"
                                                                       className="form-control"
                                                                       value={values.mobile}
                                                                       onBlur={handleBlur}
                                                                       onChange={handleChange}/>
                                                                <span
                                                                    style={{color: "red"}}>{touched.mobile && errors.mobile ? errors.mobile : null}</span>
                                                            </div>
                                                        </div>

                                                        <hr/>
                                                        <div className=" form-group col-lg-12">
                                                            <div className="form-check" onChange={_handleChangePassword}>
                                                                <input className="form-check-input" type="checkbox" value=""
                                                                       id="defaultCheck1"/>
                                                                <label className="form-check-label" htmlFor="defaultCheck1">
                                                                    Đổi mật khẩu
                                                                </label>
                                                            </div>
                                                        </div>


                                                        {!isChangePassword ? null : (
                                                            <>
                                                                <div className="col-lg-6">
                                                                    <div className="form-group">
                                                                        <label> <span style={{color: "red"}}>* </span>Mật khẩu hiện tại</label>
                                                                        <input type="password" placeholder="Mật khẩu hiện tại"
                                                                               name="old_password"
                                                                               className="form-control"
                                                                               value={values.old_password}
                                                                               onBlur={handleBlur}
                                                                               onChange={handleChange}/>
                                                                        <span
                                                                            style={{color: "red"}}>{touched.old_password && errors.old_password ? errors.old_password : null}</span>
                                                                    </div>
                                                                </div>
                                                                <div className="col-lg-6">
                                                                </div>
                                                                <div className="col-lg-6">
                                                                    <div className="form-group">
                                                                        <label><span style={{color: "red"}}>* </span>Mật khẩu mới</label>
                                                                        <input type="password" placeholder="Nhập mật khẩu"
                                                                               name="password_hash"
                                                                               className="form-control"
                                                                               value={values.password_hash}
                                                                               onBlur={handleBlur}
                                                                               onChange={handleChange}/>
                                                                        <span
                                                                            style={{color: "red"}}>{touched.password_hash && errors.password_hash ? errors.password_hash : null}</span>
                                                                    </div>
                                                                </div>
                                                                <div className="col-lg-6">
                                                                    <div className="form-group">
                                                                        <label><span style={{color: "red"}}>* </span>Nhập lại mật khẩu
                                                                            mới</label>
                                                                        <input type="password" placeholder="Nhập lại mật khẩu"
                                                                               name="re_password"
                                                                               className="form-control"
                                                                               value={values.re_password}
                                                                               onBlur={handleBlur}
                                                                               onChange={handleChange}/>
                                                                        <span
                                                                            style={{color: "red"}}>{touched.re_password && errors.re_password ? errors.re_password : null}</span>
                                                                    </div>
                                                                </div>
                                                            </>
                                                        )}

                                                    </div>
                                                    <div className="modal-footer"
                                                         style={{justifyContent: "space-between", padding: "1.5rem 0rem"}}>

                                                        <button type="button"  onClick={_changePage}
                                                                className="btn btn-bg-secondary font-weight-bold bg-hover-gray-400"
                                                                data-dismiss="modal">
                                                            <svg className="ic_svg" id="_24x24-ChevronLeft" data-name="24x24-ChevronLeft"
                                                                 xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
                                                                <rect id="Frame24" width="24" height="24" fill="#fff" opacity="0"></rect>
                                                                <path id="chevron-left"
                                                                      d="M15.254,16.089a.9.9,0,1,1-1.206,1.34l-5.3-4.8a.9.9,0,0,1,0-1.342L14,6.571a.9.9,0,0,1,1.2,1.344l-4.18,3.754a.4.4,0,0,0,0,.594l4.229,3.825Z"
                                                                      transform="translate(0.001 -0.041)" fill="#21262c"></path>
                                                            </svg>
                                                            Về trang chủ
                                                        </button>


                                                        <button type="submit" className="btn btn-primary font-weight-bold"
                                                                data-dismiss="modal">
                                                            <svg className="ic_svg" xmlns="http://www.w3.org/2000/svg" width="20"
                                                                 height="20" viewBox="0 0 20 20">
                                                                <path id="check-circle"
                                                                      d="M8.445,12.667a.9.9,0,0,0-1.3,1.243l2.573,2.745a.9.9,0,0,0,1.335-.036l5.591-7.037a.9.9,0,1,0-1.367-1.171l-4.655,5.913a.4.4,0,0,1-.607.025L8.445,12.668ZM2,12A10,10,0,1,1,12,22,10.029,10.029,0,0,1,2,12Z"
                                                                      transform="translate(-2 -2)" fill="#fff"/>
                                                            </svg>
                                                            &nbsp;
                                                            Lưu thay đổi
                                                        </button>
                                                    </div>
                                                </form>
                                            </div>
                                        )}</Formik>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </Fragment>

    )
}

export default AccountInfo
