import React, {Fragment, useState, useEffect} from 'react'
import {Formik} from "formik";
import {toast} from "react-toastify";
import {updateCourse} from "../../../shared/services/course.service";
import {changeStatusRegister} from "../../../shared/services/register.service";
import FieldRequired from "../../../containers/required/FieldRequired";
import InvalidField from "../../../containers/required/InvalidField";

const UnAllowRegister = (props) => {

    const register = props.item;

    const validate = values => {
        let errors = {};

        if (!values.reason_cancel) {
            errors.reason_cancel = "Vui lòng nhập lí do hủy"
        }
        return errors
    }

    const initValue = {
        reason_cancel: ""
    }


    const unAllowRegister = (values) => {
        const data = {
            id: register.id,
            status: 3,
            reason_cancel: values.reason_cancel
        }
        changeStatusRegister(data).then(res => {
            if (res.data.code === "00") toast.success(res.data.message);
            else toast.error(res.data.message);
            props.search();
        })
        closePopup()
    }

    const closePopup = () => {
        props.close();
    }

    return (
        <Fragment>
            <Formik
                initialValues={initValue}
                validate={validate}
                onSubmit={(values) => {
                    unAllowRegister(values);
                }}
            >{({
                   errors,
                   values,
                   touched,
                   handleChange,
                   handleBlur,
                   handleSubmit,
               }) => (
                <form onSubmit={handleSubmit} className="form">
                    <div className="modal-dialog" role="document" style={{width: 800}}>
                        <div className="modal-content">
                            <div className="modal-header border-0 justify-content-end">
                                <button type="button"
                                        className="close"
                                        data-dismiss="modal"
                                        aria-label="Close"
                                        onClick={closePopup}>
                                    <i aria-hidden="true" className="ki ki-close"></i>
                                </button>
                            </div>
                            <div className="modal-body">
                        <span className="text-center">
                            <p className="my-8 ml-3 h4" style={{color: "red"}}>Hủy hồ sơ đăng kí</p>
                        </span>

                                <div className="row ">
                                    <div className="col-lg-12">
                                        <div className="form-group">
                                            <label>Nhâp lí do hủy</label>
                                            <FieldRequired/>
                                            <textarea type="text"
                                                      className="form-control"
                                                      name="reason_cancel"
                                                      value={values.reason_cancel}
                                                      onChange={handleChange}/>
                                            <InvalidField
                                                touch={touched.reason_cancel}
                                                error={errors.reason_cancel}/>
                                        </div>
                                    </div>
                                </div>

                            </div>
                            <div className="modal-footer justify-content-center border-0">
                                <button type="button"
                                        className="btn btn-light-primary"
                                        data-dismiss="modal"
                                        onClick={closePopup}
                                        style={{minWidth: 80}}>
                                    Quay lại
                                </button>
                                &emsp;
                                <button type="submit"
                                        className="btn btn-danger"
                                        data-dismiss="modal"
                                        data-toggle="modal"
                                        style={{minWidth: 100}}>
                                    Hủy hồ sơ
                                </button>
                            </div>
                        </div>
                    </div>
                </form>)}
            </Formik>

        </Fragment>
    )
}
export default UnAllowRegister
