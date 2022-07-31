import React from "react";
import {getStatusCommon} from "../../../shared/utils/ConmonStatus";

const divStyle = {
    minWidth: 1000,
    maxHeight: 500,
}

const contentStyle = {
    overflowY: 'scroll',
    position: 'relative',
    minWidth: 800,
    maxHeight: 500,
};

const DetailUser = (props) => {

    const user = props.item;
    const  closePopup = () => {
        props.close();
    }

    return (
        <div className="modal-dialog" role="document" style={divStyle}>
            <div className="modal-content">
                <div className="modal-header">
                    <h5 className="modal-title" id="exampleModalLabel">
                        <div className="row row-10">
                            <div className="text-left h3 font-weight-bold">
                                Thông tin người dùng
                            </div>
                        </div>
                    </h5>
                    <button type="button"
                            className="close"
                            data-dismiss="modal"
                            aria-label="Close"
                            onClick={closePopup}>
                        <i aria-hidden="true" className="ki ki-close"></i>
                    </button>
                </div>
                <div className="modal-body " style={contentStyle}>
                    <form action="" className="form">
                        <div className="col-lg-12">
                            <div className="form-group col-lg-6">
                                <label className="col-4 mb-0">Tài khoản</label>
                                <label className="col-8 mb-0">{user.username}</label>
                            </div>
                            <div className="form-group col-lg-6">
                                <label className="col-4 mb-0">Họ và tên</label>
                                <label className="col-8 mb-0">{user.fullName}</label>
                            </div>
                            <div className="form-group col-lg-6">
                                <label className="col-4 mb-0">Email</label>
                                <label className="col-8 mb-0">{user.email}</label>
                            </div>
                            <div className="form-group col-lg-6">
                                <label className="col-4 mb-0">Số điện thoại</label>
                                <label className="col-8 mb-0">{user.phone}</label>
                            </div>
                            <div className="form-group col-lg-6">
                                <label className="col-4 mb-0">Phân quyền</label>
                                <label className="col-8 mb-0">{'Quyền Admin'}</label>
                            </div>
                            <div className="form-group col-lg-6">
                                <label className="col-4 mb-0">Trạng thái</label>
                                <label className="col-8 mb-0">{getStatusCommon(user.status)}</label>
                            </div>

                        </div>
                    </form>
                </div>
                <div className="modal-footer">
                    <button type="button"
                            className="btn btn-bg-light"
                            data-dismiss="modal"
                            aria-label="Close"
                            onClick={closePopup}>
                        Quay lại
                    </button>
                </div>
            </div>
        </div>
    )
}
export default DetailUser;
