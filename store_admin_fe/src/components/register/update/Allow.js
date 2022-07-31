import React, {Fragment, useState, useEffect} from 'react'
import {Formik} from "formik";
import {toast} from "react-toastify";
import {updateCourse} from "../../../shared/services/course.service";
import {changeStatusRegister} from "../../../shared/services/register.service";

const AllowRegister = (props) => {

    const register = props.item;

    const allowRegister = () =>{
        const data = {
            id: register.id,
            status: 2
        }
        changeStatusRegister(data).then(res => {
            if (res.data.code === "00")  toast.success(res.data.message);
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
                    <div className="modal-body text-center">
                        <svg xmlns="http://www.w3.org/2000/svg" width="80" height="80" fill="green"
                             className="bi bi-check-all" viewBox="0 0 16 16">
                            <path
                                d="M8.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L2.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093L8.95 4.992a.252.252 0 0 1 .02-.022zm-.92 5.14.92.92a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 1 0-1.091-1.028L9.477 9.417l-.485-.486-.943 1.179z"/>
                        </svg>
                        <p className="my-8">Chấp nhận hồ sơ đăng kí ?</p>
                    </div>
                    <div className="modal-footer justify-content-center border-0">
                        <Formik
                            initialValues={{}}
                            validate={values => {
                                let errors = {};
                                return errors
                            }}
                            onSubmit={(values) => {
                                allowRegister();
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

                                <button type="button"
                                        className="btn btn-light-primary"
                                        data-dismiss="modal"
                                        onClick={closePopup}
                                        style={{minWidth: 80}}>
                                    Hủy
                                </button>
                                &emsp;
                                <button type="submit"
                                        className="btn btn-light-primary"
                                        data-dismiss="modal"
                                        data-toggle="modal"
                                        style={{minWidth: 100}}>
                                    Đồng ý
                                </button>
                            </form>)}
                        </Formik>
                    </div>
                </div>
            </div>
        </Fragment>
    )
}
export default AllowRegister
