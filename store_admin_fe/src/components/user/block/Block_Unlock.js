import React, {useState, useEffect, Fragment} from "react";
import {Formik} from "formik";
import {updateProduct} from "../../../shared/services/product_shop.service";
import {toast} from "react-toastify";
import {changeStatusUser} from "../../../shared/services/user.service";

const Block_Unlock = (props) => {

    const user = props.item;
    const closePopup = () => {
        props.close();
    }

    const updateUser = () => {

        const data = {
            id: user.id,
            status: user.status === 1 ? 2 : 1
        }
        changeStatusUser(data).then(res => {
            if (res.data.code === "02")  toast.success(res.data.message);
            else toast.error(res.data.message);
            props.search();
        })

        closePopup();
    }

    return (
        <Fragment>
            <div className="modal-dialog" role="document" style={{width: 400}}>
                <div className="modal-content">
                    <div className="modal-header border-0 justify-content-end">
                        <button type="button" className="close" data-dismiss="modal" aria-label="Close"
                                onClick={closePopup}>
                            <i aria-hidden="true" className="ki ki-close"></i>
                        </button>
                    </div>
                    <div className="modal-body text-center">
                        <svg xmlns="http://www.w3.org/2000/svg" width="80" height="80" viewBox="0 0 16 16">
                            <g id="Group_310" data-name="Group 310" transform="translate(-1326 -572)">
                                <rect id="Frame" width="16" height="16" transform="translate(1326 572)"
                                      fill="#f44336" opacity="0"/>
                                <path id="Padlock"
                                      d="M7.419,4.567V2.987a3.244,3.244,0,0,0-.125-.852c-.005-.019,0-.04-.01-.059s-.015-.027-.019-.043c-.022-.07-.034-.144-.06-.213a3.14,3.14,0,0,0-.3-.542L6.9,1.259c-.027-.041-.064-.071-.093-.111A3.136,3.136,0,0,0,6.59.878C6.572.86,6.55.846,6.532.828A2.729,2.729,0,0,0,6.278.61,2.339,2.339,0,0,0,6.06.455C6,.417,5.955.369,5.893.335S5.787.3,5.737.273C5.716.265,5.7.251,5.679.241,5.624.217,5.567.206,5.51.185a2.535,2.535,0,0,0-.326-.11,2.562,2.562,0,0,0-1.234,0,2.535,2.535,0,0,0-.326.11c-.056.021-.114.032-.169.056-.021.01-.037.023-.058.032C3.346.3,3.291.307,3.241.335s-.11.082-.167.121A2.339,2.339,0,0,0,2.855.61,2.729,2.729,0,0,0,2.6.828c-.018.018-.04.032-.058.049a3.136,3.136,0,0,0-.214.27c-.029.04-.066.07-.093.111l-.01.021a3.139,3.139,0,0,0-.3.542c-.026.069-.038.143-.06.213,0,.015-.015.027-.019.043s0,.04-.01.059a3.244,3.244,0,0,0-.125.852v1.58H0v5.558a1.922,1.922,0,0,0,.5,1.319A1.586,1.586,0,0,0,1.728,12H7.406a1.586,1.586,0,0,0,1.233-.555,1.922,1.922,0,0,0,.5-1.319V4.567ZM2.853,2.987a1.977,1.977,0,0,1,.108-.614,2.385,2.385,0,0,1,.119-.263,1.765,1.765,0,0,1,.239-.377.971.971,0,0,1,.069-.081,1.69,1.69,0,0,1,.469-.34A1.513,1.513,0,0,1,4,1.255a1.6,1.6,0,0,1,.569-.117,1.6,1.6,0,0,1,.569.117,1.513,1.513,0,0,1,.141.056,1.69,1.69,0,0,1,.469.34.971.971,0,0,1,.069.081,1.765,1.765,0,0,1,.239.377,2.385,2.385,0,0,1,.119.263,1.977,1.977,0,0,1,.108.614v1.58H2.853ZM8,10.125a.782.782,0,0,1-.181.523.528.528,0,0,1-.409.214H1.728a.528.528,0,0,1-.409-.214.782.782,0,0,1-.181-.523V5.719H8Z"
                                      transform="translate(1329.333 573.333)" fill="#f44336"/>
                            </g>
                        </svg>
                        {user.status === 1 ?
                            <p className="my-8">Bạn muốn khóa bản ghi này?</p>
                            : <p className="my-8">Bạn muốn mở khóa bản ghi này?</p>}
                    </div>
                    <div className="modal-footer justify-content-center border-0">
                        <Formik
                            initialValues={{}}
                            validate={values => {
                                let errors = {};
                                return errors
                            }}
                            onSubmit={(values) => {
                                updateUser();
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

                                <button type="button" className="btn btn-light-primary" data-dismiss="modal"
                                        onClick={closePopup}
                                        style={{minWidth: 80}}>
                                    Hủy
                                </button>
                                &emsp;
                                <button type="submit" className="btn btn-primary" data-dismiss="modal"
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
export default Block_Unlock;
