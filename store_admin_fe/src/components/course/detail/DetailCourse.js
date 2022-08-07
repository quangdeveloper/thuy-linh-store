import React, {Fragment, useState, useEffect} from 'react'
import {getStatusCommon} from "../../../shared/utils/ConmonStatus";

const DetailCourse = (props) => {

    const course = {};
    const viewList = () => {
        props.history.push("/course");
    }

    useEffect(() => {
        console.log("Course Id: ", props.match.params.id)
    })

    return (
        <Fragment>
            <div className="content d-flex flex-column flex-column-fluid" id="kt_content">
                <div className="d-flex flex-column-fluid">
                    <div className="container">
                        <div className="card card-custom">
                            <div className="card-header flex-wrap border-0 pt-6 pb-0">
                                <h4>Thông tin chi tiết khóa học</h4>
                            </div>
                            <div className="card-body">
                                <div className="row">
                                    <div className="form-group col-lg-6">
                                        <label className="col-4 mb-0">Mã khóa học</label>
                                        <label className="col-8 mb-0">{course.code}</label>
                                    </div>
                                    <div className="form-group col-lg-6">
                                        <label className="col-4 mb-0">Tên khóa học</label>
                                        <label className="col-8 mb-0">{course.name}</label>
                                    </div>
                                    <div className="form-group col-lg-6">
                                        <label className="col-4 mb-0">Mô tả ngắn</label>
                                        <label className="col-8 mb-0">{course.description}</label>
                                    </div>
                                    <div className="form-group col-lg-6">
                                        <label className="col-4 mb-0">Độ tuổi yêu cầu</label>
                                        <label className="col-8 mb-0">{course.age}</label>
                                    </div>
                                    <div className="form-group col-lg-6">
                                        <label className="col-4 mb-0">Số lượng học viên</label>
                                        <label className="col-8 mb-0">{course.amount}</label>
                                    </div>

                                    <div className="form-group col-lg-6">
                                        <label className="col-4 mb-0">Ngày bắt đầu</label>
                                        <label className="col-8 mb-0">
                                            {course.startDate ? course.startDate.substring(0, 10) : null}
                                        </label>
                                    </div>
                                    <div className="form-group col-lg-6">
                                        <label className="col-4 mb-0">Ngày kết thúc</label>
                                        <label className="col-8 mb-0">
                                            {course.endDate ? course.endDate.substring(0, 10) : null}
                                        </label>
                                    </div>
                                    <div className="form-group col-lg-6">
                                        <label className="col-4 mb-0">Chi phí đào tạo</label>
                                        <label className="col-8 mb-0">{course.price}</label>
                                    </div>

                                    <div className="form-group col-lg-6">
                                        <label className="col-4 mb-0">Trạng thái</label>
                                        <label className="col-8 mb-0">{getStatusCommon(course.status)}</label>
                                    </div>

                                    <div className="form-group col-lg-12">
                                        <label className="col-4 mb-0">Nội dung chi tiết</label>
                                    </div>
                                    <div className="form-group col-lg-12">
                                        <span className="col-12 mb-0">
                                            {course.content}
                                        </span>
                                    </div>
                                    <div className="col-lg-12">
                                        <div className="modal-footer"
                                             style={{justifyContent: "space-between", padding: "1.5rem 0rem"}}>
                                            <button type="button"
                                                    className="btn btn-bg-light"
                                                    data-dismiss="modal"
                                                    aria-label="Close"
                                                    onClick={viewList}>
                                                Xem danh sách
                                            </button>
                                        </div>
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
export default DetailCourse;
