import React, {Fragment, useState, useEffect} from 'react'
import {Formik} from "formik";
import Select from "react-select";
import FieldRequired from "../../../containers/required/FieldRequired";
import InvalidField from "../../../containers/required/InvalidField";
import {toast} from "react-toastify";
import {createUser, updateUser} from "../../../shared/services/user.service";
import {CommonItemLocalStorage} from "../../../shared/utils/CommonItemLocalStorage";
import {formatStringDate} from "../../../shared/utils/DateUtil";
import {updateCourse} from "../../../shared/services/course.service";
const UpdateCourse = (props) => {

    const validates = values => {

        const errors = {};

        if (!values.code) {
            errors.code = "Vui lòng nhập mã khóa học"
        }

        if (!values.name) {
            errors.name = "Vui lòng nhập tên khóa học"
        }

        if (!values.description) {
            errors.description = "Vui lòng nhập mô tả ngắn"
        }
        if (!values.content) {
            errors.content = "Vui lòng nhập nối dung"
        }

        if (!values.start_date) {
            errors.start_date = "Vui lòng nhập ngày bắt đầu"
        }

        if (!values.end_date) {
            errors.end_date = "Vui lòng nhập ngày kết thúc"
        }

        if (!values.amount) {
            errors.amount = "Vui lòng nhập số lượng học viên"
        } else if (values.amount < 0 || values.amount > 25) {
            errors.amount = "Vui lòng nhập số lượng học viên hợp lệ (10-25)"
        }

        if (!values.age) {
            errors.age = "Vui lòng nhập độ tuổi yêu cầu"
        } else if (values.age < 0 || values.age > 150) {
            errors.age = "Vui lòng nhập độ tuổi yêu cầu hợp lệ (10-25)"
        }

        if (!values.price) {
            errors.price = "Vui lòng nhập chi phí đào tạo"
        }

        return errors
    };

    const course = JSON.parse(localStorage.getItem(CommonItemLocalStorage.item_update));



    const initValue = {
        id: course.id,
        code: course.code,
        name: course.name,
        description: course.description,
        content: course.content,
        start_date: formatStringDate(course.startDate),
        end_date: formatStringDate(course.endDate),
        amount: course.amount,
        age: course.age,
        price: course.price,
    };

    const updateOldCourse = (course) => {
        updateCourse(course).then(res => {
            if (res.data.code === "00") {
                toast.success("Cập nhật thông tin khóa học thành công")
            } else {
                toast.error(res.data.message)
            }
        })
    }

    const viewList = () => {
        window.location.href = "/course"
    }

    return (
        <Fragment>
            <div className="content d-flex flex-column flex-column-fluid" id="kt_content">
                <div className="d-flex flex-column-fluid">
                    <div className="container">
                        <div className="card card-custom">
                            <div className="card-header flex-wrap border-0 pt-6 pb-0">
                                <h4>Cập nhật khóa học</h4>
                            </div>
                            <div className="card-body">
                                <Formik
                                    initialValues={initValue}
                                    validate={validates}
                                    onSubmit={(values) => {
                                        updateOldCourse(values)
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
                                                    <label>Mã khóa học</label>
                                                    <FieldRequired/>
                                                    <input type="text"
                                                           className="form-control"
                                                           name="code"
                                                           value={values.code}
                                                           onChange={handleChange}/>
                                                    <InvalidField
                                                        touch={touched.code}
                                                        error={errors.code}/>
                                                </div>
                                            </div>

                                            <div className="col-lg-6">
                                                <div className="form-group">
                                                    <label>Tên khóa học</label>
                                                    <FieldRequired/>
                                                    <input type="name"
                                                           className="form-control"
                                                           name="name"
                                                           value={values.name}
                                                           onChange={handleChange}/>
                                                    <InvalidField
                                                        touch={touched.name}
                                                        error={errors.name}/>
                                                </div>
                                            </div>

                                            <div className="col-lg-12">
                                                <div className="form-group">
                                                    <label>Mô tả ngắn</label>
                                                    <FieldRequired/>
                                                    <textarea type="text"
                                                              className="form-control"
                                                              name="description"
                                                              value={values.description}
                                                              onChange={handleChange}/>
                                                    <InvalidField
                                                        touch={touched.description}
                                                        error={errors.description}/>
                                                </div>
                                            </div>
                                            <div className="col-lg-6">
                                                <div className="form-group">
                                                    <label>Ngày bắt đầu</label>
                                                    <FieldRequired/>
                                                    <input type="date"
                                                           className="form-control"
                                                           name="start_date"
                                                           value={values.start_date}
                                                           onChange={handleChange}/>
                                                    <InvalidField
                                                        touch={touched.start_date}
                                                        error={errors.start_date}/>
                                                </div>
                                            </div>
                                            <div className="col-lg-6">
                                                <div className="form-group">
                                                    <label>Ngày kết thúc</label>
                                                    <FieldRequired/>
                                                    <input type="date"
                                                           className="form-control"
                                                           name="end_date"
                                                           value={values.end_date}
                                                           onChange={handleChange}/>
                                                    <InvalidField
                                                        touch={touched.end_date}
                                                        error={errors.end_date}/>
                                                </div>
                                            </div>
                                            <div className="col-lg-6">
                                                <div className="form-group">
                                                    <label>Số lượng học viên</label>
                                                    <FieldRequired/>
                                                    <input type="text"
                                                           className="form-control"
                                                           name="amount"
                                                           value={values.amount}
                                                           onChange={handleChange}/>
                                                    <InvalidField
                                                        touch={touched.amount}
                                                        error={errors.amount}/>
                                                </div>
                                            </div>
                                            <div className="col-lg-6">
                                                <div className="form-group">
                                                    <label>Độ tuổi yêu cầu</label>
                                                    <FieldRequired/>
                                                    <input type="text"
                                                           className="form-control"
                                                           name="age"
                                                           value={values.age}
                                                           onChange={handleChange}/>
                                                    <InvalidField
                                                        touch={touched.age}
                                                        error={errors.age}/>
                                                </div>
                                            </div>
                                            <div className="col-lg-6">
                                                <div className="form-group">
                                                    <label>Chi phí đào tạo</label>
                                                    <FieldRequired/>
                                                    <input type="text"
                                                           className="form-control"
                                                           name="price"
                                                           value={values.price}
                                                           onChange={handleChange}/>
                                                    <InvalidField
                                                        touch={touched.price}
                                                        error={errors.price}/>
                                                </div>
                                            </div>

                                            <div className="col-lg-12">
                                                <div className="form-group">
                                                    <label>Nội dung khóa học</label>
                                                    <FieldRequired/>
                                                    <textarea type="text"
                                                              className="form-control"
                                                              name="content"
                                                              value={values.content}
                                                              onChange={handleChange}/>
                                                    <InvalidField
                                                        touch={touched.content}
                                                        error={errors.content}/>
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
export default UpdateCourse
