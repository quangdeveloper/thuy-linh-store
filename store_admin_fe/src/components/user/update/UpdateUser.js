import React, {Fragment, useEffect, useState} from 'react'
import {Formik} from "formik";
import Select from "react-select";
import FieldRequired from "../../../containers/required/FieldRequired";
import InvalidField from "../../../containers/required/InvalidField";
import {toast} from "react-toastify";
import {updateUser} from "../../../shared/services/user.service";
import {CommonItemLocalStorage} from "../../../shared/utils/CommonItemLocalStorage";
import {getAllGroupRole} from "../../../shared/services/group_role.service";

const UpdateUser = (props) => {

    const validates = values => {
        const errors = {};

        if (!values.username) {
            errors.username = "Vui lòng nhập tài khoản"
        }

        if (!values.full_name) {
            errors.full_name = "Vui lòng nhập họ và tên"
        }

        if (!values.email) {
            errors.email = "Vui lòng nhập địa chỉ mail"
        }

        if (!values.mobile) {
            errors.mobile = "Vui lòng nhập số điện thoại"
        }

        if (!values.gr_role_id) {
            errors.gr_role_id = "Vui lòng nhập chọn phân quyền"
        }

        if (!values.date_born) {
            errors.date_born = "Vui lòng nhập ngày sinh"
        }

        if (!values.addess) {
            errors.addess = "Vui lòng nhập địa chỉ"
        }
        console.log("pass validate: ", JSON.stringify(values));
        return errors
    };

    const user = JSON.parse(sessionStorage.getItem(CommonItemLocalStorage.item_update));
    const role_default = {value: user.groupRoleId, label: user.groupRoleName};

    const initValue = {
        id: user.id,
        username: user.username,
        full_name: user.fullName,
        email: user.email,
        mobile: user.mobile,
        gr_role_id: user.groupRoleId,
        date_born: user.dateBorn,
        address: user.address
    };

    const [roleOptions, setRoleOptions] = useState([])

    const updateOldUser = (user) => {
        console.log("values: ", JSON.stringify(user));
        updateUser(user).then(res => {
            if (res.data.code === "02") {
                toast.success(res.data.message)
            } else {
                toast.error(res.data.message)
            }
        })
    }

    const viewList = () => {
        window.location.href = "/user"
    }

    useEffect(() => {
        getAllGroupRole().then(response => {
            if (response.data.code === "00") {
                setRoleOptions(response.data.data.map(item => {
                    return {value: item.id, label: item.name}
                }));
            }
        });
    }, [])

    return (
        <Fragment>
            <div className="content d-flex flex-column flex-column-fluid" id="kt_content">
                <div className="d-flex flex-column-fluid">
                    <div className="container">
                        <div className="card card-custom">
                            <div className="card-header flex-wrap border-0 pt-6 pb-0">
                                <h4>Cập nhật thông tin người dùng</h4>
                            </div>
                            <div className="card-body">
                                <Formik
                                    initialValues={initValue}
                                    validate={validates}
                                    onSubmit={(values) => {
                                        updateOldUser(values)
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
                                                    <label>Ngày sinh</label>
                                                    <FieldRequired/>
                                                    <input type="date"
                                                           className="form-control"
                                                           name="date_born"
                                                           value={values.date_born}
                                                           onChange={handleChange}/>
                                                    <InvalidField
                                                        touch={touched.date_born}
                                                        error={errors.date_born}/>
                                                </div>
                                            </div>
                                            <div className="col-lg-6">
                                                <div className="form-group">
                                                    <label>Địa chỉ</label>
                                                    <FieldRequired/>
                                                    <input type="text"
                                                           className="form-control"
                                                           name="address"
                                                           value={values.address}
                                                           onChange={handleChange}/>
                                                    <InvalidField
                                                        touch={touched.address}
                                                        error={errors.address}/>
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
                                                           name="mobile"
                                                           value={values.mobile}
                                                           onChange={handleChange}/>
                                                    <InvalidField
                                                        touch={touched.mobile}
                                                        error={errors.mobile}/>
                                                </div>
                                            </div>
                                            <div className="col-lg-6">
                                                <div className="form-group">
                                                    <label>Phân quyền </label>
                                                    <FieldRequired/>
                                                    <Select placeholder="--Chọn phân quyền--"
                                                            options={roleOptions}
                                                            isSearchable={true}
                                                            defaultValue={role_default}
                                                            onChange={selectedOption => {
                                                                let event = {
                                                                    target: {
                                                                        name: 'gr_role_id',
                                                                        value: selectedOption.value
                                                                    }
                                                                }
                                                                handleChange(event)
                                                            }}
                                                            onBlur={handleBlur}>
                                                    </Select>

                                                    <InvalidField
                                                        touch={touched.gr_role_id}
                                                        error={errors.gr_role_id}/>
                                                </div>
                                            </div>
                                            <div className="col-lg-12">
                                                <div className="modal-footer"
                                                     style={{justifyContent: "space-between", padding: "1.5rem 0rem"}}>
                                                    <button type="button"
                                                            className="btn btn-bg-light"
                                                            data-dismiss="modal"
                                                            aria-label="Close"
                                                            onClick={viewList}>
                                                        Quay lại
                                                    </button>
                                                    <button type="submit"
                                                            className="btn btn-light-primary">
                                                        <span>Cập nhật</span>
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


export default UpdateUser
