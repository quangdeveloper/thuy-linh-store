import React, {Fragment} from "react";
import {getStatusCommon} from "../../../shared/utils/ConmonStatus";
import {CommonItemLocalStorage} from "../../../shared/utils/CommonItemLocalStorage";

const DetailUser = () => {

    const user = JSON.parse(sessionStorage.getItem(CommonItemLocalStorage.item_update));

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
                                <h4>Thông tin chi tiết người dùng</h4>
                            </div>
                            <div className="card-body ">
                                <form action="" className="form">
                                    <div className="row ">
                                        <div className="form-group col-lg-6">
                                            <label className="col-4 mb-0">Tài khoản</label>
                                            <label className="col-8 mb-0">{user.username}</label>
                                        </div>
                                        <div className="form-group col-lg-6">
                                            <label className="col-4 mb-0">Họ và tên</label>
                                            <label className="col-8 mb-0">{user.fullName}</label>
                                        </div>
                                        <div className="form-group col-lg-6">
                                            <label className="col-4 mb-0">Ngày sinh</label>
                                            <label className="col-8 mb-0">{user.dateBorn}</label>
                                        </div>
                                        <div className="form-group col-lg-6">
                                            <label className="col-4 mb-0">Địa chỉ</label>
                                            <label className="col-8 mb-0">{user.address}</label>
                                        </div>
                                        <div className="form-group col-lg-6">
                                            <label className="col-4 mb-0">Email</label>
                                            <label className="col-8 mb-0">{user.email}</label>
                                        </div>
                                        <div className="form-group col-lg-6">
                                            <label className="col-4 mb-0">Số điện thoại</label>
                                            <label className="col-8 mb-0">{user.mobile}</label>
                                        </div>
                                        <div className="form-group col-lg-6">
                                            <label className="col-4 mb-0">Phân quyền</label>
                                            <label className="col-8 mb-0">{user.groupRoleName}</label>
                                        </div>
                                        <div className="form-group col-lg-6">
                                            <label className="col-4 mb-0">Trạng thái</label>
                                            <label className="col-8 mb-0">{getStatusCommon(user.status)}</label>
                                        </div>
                                        <div className="form-group col-lg-6">
                                            <label className="col-4 mb-0">Ngày tạo</label>
                                            <label className="col-8 mb-0">{user.createdDate}</label>
                                        </div>
                                        <div className="form-group col-lg-6">
                                            <label className="col-4 mb-0">Người tạo</label>
                                            <label className="col-8 mb-0">{user.createdBy}</label>
                                        </div>
                                        <div className="form-group col-lg-6">
                                            <label className="col-4 mb-0">Ngày cập nhật</label>
                                            <label className="col-8 mb-0">{user.updatedDate}</label>
                                        </div>
                                        <div className="form-group col-lg-6">
                                            <label className="col-4 mb-0">Người cập nhật</label>
                                            <label className="col-8 mb-0">{user.updatedBy}</label>
                                        </div>
                                    </div>
                                </form>
                            </div>

                            <div className="col-lg-12">
                                <div className="modal-footer" style={{justifyContent: "right"}}>
                                    <button type="button"
                                            className="btn btn-bg-light"
                                            data-dismiss="modal"
                                            aria-label="Close"
                                            onClick={(event) => viewList()}>
                                        Quay lại
                                    </button>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </Fragment>
    );
}
export default DetailUser;
