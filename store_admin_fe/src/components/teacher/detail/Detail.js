import React from "react";
import {getAcademicDegree, getStatusCommon} from "../../../shared/utils/ConmonStatus";
import InvalidField from "../../../containers/required/InvalidField";

const divStyle = {
    minWidth: 1200,
    maxHeight: 500,
}

const contentStyle = {
    overflowY: 'scroll',
    position: 'relative',
    minWidth: 800,
    maxHeight: 500,
};

const DetailTeacher = (props) => {
    const api_url = "http://localhost:8083";
    const teacher = props.item;
    const closePopup = () => {
        props.close();
    }

    return (
        <div className="modal-dialog" role="document" style={divStyle}>
            <div className="modal-content">
                <div className="modal-header">
                    <h5 className="modal-title" id="exampleModalLabel">
                        <div className="row row-10">
                            <div className="text-left h3 font-weight-bold">
                                Thông tin giảng viên
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
                        <div className="row">
                            <div className="col-lg-9 ml-5">
                                <div className="form-group">
                                    <label>Ảnh giảng viên</label>
                                    <div className="files-upload">
                                        <div id="kt_image_4"
                                             className="image-input image-input-outline">
                                            <div className="image-input-wrapper"
                                                 style={{backgroundImage: `url(${api_url + teacher && teacher.imageUrl ?  teacher.imageUrl.replaceAll("\\","\/") : null})`}}>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div className="form-group col-lg-6">
                                <label className="col-4 mb-0">Họ và tên</label>
                                <label className="col-8 mb-0">{teacher.fullName}</label>
                            </div>
                            <div className="form-group col-lg-6">
                                <label className="col-4 mb-0">Ngày sinh</label>
                                <label className="col-8 mb-0">
                                    {teacher.dateOfBirth.substring(0, 10)}
                                </label>
                            </div>
                            <div className="form-group col-lg-6">
                                <label className="col-4 mb-0">Giới tính</label>
                                <label className="col-8 mb-0">
                                    {teacher.gender === 1 ? 'Nam' : 'Nữ'}
                                </label>
                            </div>
                            <div className="form-group col-lg-6">
                                <label className="col-4 mb-0">Địa chỉ</label>
                                <label className="col-8 mb-0">{teacher.address}</label>
                            </div>
                            <div className="form-group col-lg-6">
                                <label className="col-4 mb-0">Email</label>
                                <label className="col-8 mb-0">{teacher.email}</label>
                            </div>
                            <div className="form-group col-lg-6">
                                <label className="col-4 mb-0">Số điện thoại</label>
                                <label className="col-8 mb-0">{teacher.phone}</label>
                            </div>
                            <div className="form-group col-lg-6">
                                <label className="col-4 mb-0">Số fax</label>
                                <label className="col-8 mb-0">{teacher.fax}</label>
                            </div>
                            <div className="form-group col-lg-6">
                                <label className="col-4 mb-0">Học vị</label>
                                <label className="col-8 mb-0">{getAcademicDegree(teacher.academicDegree)}</label>
                            </div>
                            <div className="form-group col-lg-6">
                                <label className="col-4 mb-0">Trạng thái</label>
                                <label className="col-8 mb-0">{getStatusCommon(teacher.status)}</label>
                            </div>
                            <div className="form-group col-lg-12">
                                <label className="col-2 mb-0">Mô tả ngắn</label>
                                <label className="col-10 mb-0">{teacher.shortDesc}</label>
                            </div>
                            <div className="form-group col-lg-6">
                                <label className="col-4 mb-0">Ngày tạo</label>
                                <label className="col-8 mb-0">
                                    {teacher.createdDate ? teacher.createdDate.substring(0, 10) : null}
                                </label>
                            </div>
                            <div className="form-group col-lg-6">
                                <label className="col-4 mb-0">Người tạo</label>
                                <label className="col-8 mb-0">{teacher.createdBy}</label>
                            </div>
                            <div className="form-group col-lg-6">
                                <label className="col-4 mb-0">Ngày cập nhật</label>
                                <label className="col-8 mb-0">
                                    {teacher.updatedDate ? teacher.updatedDate.substring(0, 10) : null}
                                </label>
                            </div>
                            <div className="form-group col-lg-6">
                                <label className="col-4 mb-0">Người cập nhật</label>
                                <label className="col-8 mb-0">{teacher.updatedBy}</label>
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
export default DetailTeacher;
