import React from "react";
import {CommonItemLocalStorage} from "../../../shared/utils/CommonItemLocalStorage";
import {getStatusRegister} from "../../../shared/utils/ConmonStatus";
import Popup from "reactjs-popup";
import AllowRegister from "../update/Allow";
import UnAllowRegister from "../update/UnAllow";

const DataTable = (props) => {

    const viewDetail = (course) => {
        localStorage.setItem(CommonItemLocalStorage.item_detail, JSON.stringify(course));
        window.location.href = "/register-detail"
    }

    return (
        <div className="table-responsive">
            <table className="table datatable-bordered no-wrap">
                <thead>
                <tr>
                    <th>STT</th>
                    <th>Họ và tên</th>
                    <th>Ngày sinh</th>
                    <th>Giới tính</th>
                    <th>Quốc gia</th>
                    <th>Số điện thoại</th>
                    <th>Email</th>
                    <th>Ngày đăng kí</th>
                    <th>Trạng thái</th>
                    <th>Thao tác</th>
                </tr>
                </thead>
                <tbody>
                {props.dataTable && props.dataTable.length > 0 && props.dataTable.map((m, idx) => {
                    return (
                        <tr key={idx}>
                            <td>{idx + 1 + (props.page_config.page_index - 1) * props.page_config.page_size}</td>
                            <td>{m.fullName}</td>
                            <td>{m.dateOfBirth ? m.dateOfBirth.substring(0, 10) : null}</td>
                            <td>{m.gender === 1 ? 'Nam' : 'Nữ'}</td>
                            <td>{m.national}</td>
                            <td>{m.phone}</td>
                            <td>{m.email}</td>
                            <td>{m.createdDate ? m.createdDate.substring(0, 10) : null}</td>
                            <td>{getStatusRegister(m.status)}</td>
                            <td>


                                {m.status === 1 || m.status === 3 ?
                                    <span data-toggle="tooltip" title="Chấp nhận">
                                         <Popup modal
                                                closeOnDocumentClick={false}
                                                trigger={
                                                    <button className="btn btn-sm btn-clean btn-icon">
                                                        <img src="assets/media/icon/Unlock.svg" alt=""/>
                                                    </button>}>
                                                  {close => <AllowRegister close={close}
                                                                             search={props.search}
                                                                             item={m}/>}
                                              </Popup>
                                    </span> : null}

                                {m.status === 1 ?
                                    <span data-toggle="tooltip" title="Hủy bỏ">
                                         <Popup modal
                                                closeOnDocumentClick={false}
                                                trigger={
                                                    <button className="btn btn-sm btn-clean btn-icon ">
                                                        <img src="assets/media/icon/Lock.svg" alt=""/>
                                                    </button>}>
                                                   {close => <UnAllowRegister close={close}
                                                                            search={props.search}
                                                                            item={m}/>}
                                              </Popup>

                                    </span> : null}

                                <span data-toggle="tooltip" title="Xem chi tiết">
                                    <button className="btn btn-sm btn-clean btn-icon"
                                            onClick={event => viewDetail(m)}>
                                        <img src="assets/media/icon/info-circle.svg" alt=""/>
                                    </button>
                                </span>
                            </td>
                        </tr>
                    );
                })}
                </tbody>
            </table>
        </div>
    );
};

export default DataTable;
