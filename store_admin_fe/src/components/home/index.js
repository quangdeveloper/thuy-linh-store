import {Doughnut} from "react-chartjs-2";
import React, {Fragment, useState, useEffect} from 'react'


const Home = () => {

    const [partnerTable, setPartnerTable] = useState([]);
    const [providerTable, setProviderTable] = useState([]);


    return (<Fragment>
        <div className="content d-flex flex-column flex-column-fluid" id="kt_content">
            <div className="d-flex flex-column-fluid">
                <div className="container">
                    <div className="mb-10 mt-lg-n10 mt-10">
                        <h1>Trang chủ</h1>
                        <span>Dữ liệu tháng {new Date().getMonth() + 1}  năm {new Date().getFullYear()}</span>
                    </div>

                    <div className="form-group row ">

                        <div className="card card-custom form-group mr-5 col-lg-12">
                            <div className="card-header flex-wrap border-0 pt-6 pb-0">
                                <div className="card-title">
                                    <h3 className="card-label">Khóa học thịnh hành</h3>
                                </div>
                            </div>
                            <div className="card-body">
                                <div className="table-responsive">
                                    <table className="table datatable-bordered no-wrap">
                                        <thead>
                                        <tr>
                                            <th>STT</th>
                                            <th>Mã khóa học</th>
                                            <th>Tên khóa học</th>
                                            <th>Độ tuổi YC</th>
                                            <th>Số lượng HV</th>
                                            <th>Chi phí</th>
                                            <th>Ngày bắt đầu</th>
                                            <th>Ngày kết thúc</th>
                                            <th>Trạng thái</th>
                                            <th>Người tạo</th>
                                            <th>Thao tác</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>


                    </div>
                </div>
            </div>
        </div>
    </Fragment>);
}

export default Home
