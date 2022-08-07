import React, {Fragment, useState, useEffect} from 'react'
import {
    getAllBrand,
    getAllCategoryFeature, getAllCategoryProperties,
    getAllMaterial,
    getAllOrigin,
    getAllProductCategory, getShopId
} from "../../../shared/services/common.service";
import {Formik} from "formik";
import Select from "react-select";
import {getServerUrl, handleSubmissionFormData} from "../../../shared/form_data_handler/submit-formdata";
import FieldRequired from "../../../containers/required/FieldRequired";
import InvalidField from "../../../containers/required/InvalidField";
import {toast} from "react-toastify";
import {createUser} from "../../../shared/services/user.service";

const AddUser = (props) => {

    const validates = values => {
        const errors = {};


        if (!values.username) {
            errors.username = "Vui lòng nhập tài khoản"
        }

        if (!values.password) {
            errors.password = "Vui lòng nhập mật khẩu"
        }

        if (!values.re_password) {
            errors.re_password = "Vui lòng nhập lại mật khẩu"
        }
        if (values.password !== values.re_password) {
            errors.re_password = "Mật khẩu không khớp"
        }

        if (!values.full_name) {
            errors.full_name = "Vui lòng nhập họ và tên"
        }

        if (!values.email) {
            errors.email = "Vui lòng nhập địa chỉ mail"
        }

        if (!values.phone) {
            errors.phone = "Vui lòng nhập số điện thoại"
        }

        if (!values.role_id) {
            errors.role_id = "Vui lòng nhập chọn phân quyền"
        }
        return errors
    };

    const initValue = {
        username: null,
        password: null,
        re_password: null,
        full_name: null,
        email: null,
        phone: null,
        role_id: null
    };

    const [roleOptions, setRoleOptions] = useState([
        {label: "Quyền Admin", value: 1}
    ])


    const createNewUser = (user) => {
        createUser(user).then(res => {
            if (res.data.code === "00") {
                toast.success("Thêm mới người dùng thành công !")
            } else toast.error(res.data.message)
        })
    }

    const viewList = () => {
        window.location.href = "/user"
    }

    return (
        <Fragment>
            <div className="content d-flex flex-column flex-column-fluid" id="kt_content">
                <div className="d-flex flex-column-fluid">
                    <div className="container">
                        <div className="card card-custom">
                            <div className="card-header flex-wrap border-0 pt-6 pb-0">
                                <h4>Thêm mới người dùng</h4>
                            </div>
                            <div className="card-body">
                                <Formik
                                    initialValues={initValue}
                                    validate={validates}
                                    onSubmit={(values) => {

                                        createNewUser(values)
                                    }}
                                >{({
                                       values,
                                       errors,
                                       touched,
                                       handleChange,
                                       handleBlur,
                                       handleSubmit,
                                   }) => (
                                    <form className="form" onSubmit={handleSubmit}>
                                        <div className="row ">
                                            <div className="col-lg-6">
                                                <div className="form-group">
                                                    <label>Tài khoản</label>
                                                    <FieldRequired/>
                                                    <input type="text"
                                                           className="form-control"
                                                           name="username"
                                                           value={values.username}
                                                           onChange={handleChange}/>
                                                    <InvalidField
                                                        touch={touched.username}
                                                        error={errors.username}/>
                                                </div>
                                            </div>

                                            <div className="col-lg-6">
                                                <div className="form-group">
                                                    <label>Mật khẩu</label>
                                                    <FieldRequired/>
                                                    <input type="password"
                                                           className="form-control"
                                                           name="password"
                                                           value={values.password}
                                                           onChange={handleChange}/>
                                                    <InvalidField
                                                        touch={touched.password}
                                                        error={errors.password}/>
                                                </div>
                                            </div>

                                            <div className="col-lg-6">
                                                <div className="form-group">
                                                    <label>Họ và tên</label>
                                                    <FieldRequired/>
                                                    <input type="text"
                                                           className="form-control"
                                                           name="full_name"
                                                           value={values.full_name}
                                                           onChange={handleChange}/>
                                                    <InvalidField
                                                        touch={touched.full_name}
                                                        error={errors.full_name}/>
                                                </div>
                                            </div>
                                            <div className="col-lg-6">
                                                <div className="form-group">
                                                    <label>Nhâp lại mật khẩu</label>
                                                    <FieldRequired/>
                                                    <input type="password"
                                                           className="form-control"
                                                           name="re_password"
                                                           value={values.re_password}
                                                           onChange={handleChange}/>
                                                    <InvalidField
                                                        touch={touched.re_password}
                                                        error={errors.re_password}/>
                                                </div>
                                            </div>
                                            <div className="col-lg-6">
                                                <div className="form-group">
                                                    <label>Email</label>
                                                    <FieldRequired/>
                                                    <input type="text"
                                                           className="form-control"
                                                           name="email"
                                                           value={values.email}
                                                           onChange={handleChange}/>
                                                    <InvalidField
                                                        touch={touched.email}
                                                        error={errors.email}/>
                                                </div>
                                            </div>
                                            <div className="col-lg-6">
                                                <div className="form-group">
                                                    <label>Số điện thoại</label>
                                                    <FieldRequired/>
                                                    <input type="text"
                                                           className="form-control"
                                                           name="phone"
                                                           value={values.phone}
                                                           onChange={handleChange}/>
                                                    <InvalidField
                                                        touch={touched.phone}
                                                        error={errors.phone}/>
                                                </div>
                                            </div>
                                            <div className="col-lg-6">
                                                <div className="form-group">
                                                    <label>Phân quyền </label>
                                                    <FieldRequired/>
                                                    <Select placeholder="--Chọn phân quyền--"
                                                            options={roleOptions}
                                                            isSearchable={true}
                                                            onChange={selectedOption => {
                                                                let event = {
                                                                    target: {
                                                                        name: 'role_id',
                                                                        value: selectedOption.value
                                                                    }
                                                                }
                                                                handleChange(event)
                                                            }}
                                                            onBlur={handleBlur}>
                                                    </Select>

                                                    <InvalidField
                                                        touch={touched.role_id}
                                                        error={errors.role_id}/>
                                                </div>
                                            </div>
                                            <div className="col-lg-12">
                                                <div className="modal-footer" style={{justifyContent: "space-between", padding: "1.5rem 0rem"}}>
                                                    <button type="button"
                                                            className="btn btn-bg-light"
                                                            data-dismiss="modal"
                                                            aria-label="Close"
                                                            onClick={viewList}>
                                                        Xem danh sách
                                                    </button>
                                                    <button type="submit"
                                                            className="btn btn-light-primary">
                                                        <span>Thêm mới</span>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                )}</Formik>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </Fragment>
    )
}



export default AddUser
