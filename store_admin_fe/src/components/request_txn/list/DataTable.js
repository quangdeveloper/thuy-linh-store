import Popup from "reactjs-popup";
import React from "react";
import {CommonItemLocalStorage} from "../../../shared/utils/CommonItemLocalStorage";
import {getStatusCommon} from "../../../shared/utils/ConmonStatus";

const DataTable = (props) => {

    const updateUser = (user) => {
        sessionStorage.setItem(CommonItemLocalStorage.item_update, JSON.stringify(user));
        window.location.href = "/user-update"
    }

    return (
        <div className="table-responsive">
            <table className="table datatable-bordered no-wrap">
                <thead>
                <tr>
                    <th>STT</th>
                    <th>Tên khách hàng</th>
                    <th>Số điện thoại</th>
                    <th>Thời gian hẹn</th>
                    <th>Ngày tạo</th>
                    <th>Ngày cập nhật</th>
                    <th>Người cập nhập</th>
                    <th>Trạng thái</th>
                    <th>Thao tác</th>
                </tr>
                </thead>
                <tbody>
                {props.dataTable && props.dataTable.length > 0 && props.dataTable.map((m, idx) => {
                    return (
                        <tr key={idx}>
                            <td>{idx + 1 + (props.page_config.page_index - 1) * props.page_config.page_size}</td>
                            <td>{m.customerName}</td>
                            <td>{m.customerMobile}</td>
                            <td>{m.dateRequest}</td>
                            <td>{m.createdDate}</td>
                            <td>{m.updatedDate}</td>
                            <td>{m.updatedBy}</td>
                            <td>{getStatusCommon(m.status)}</td>
                            <td>
                                <span>
                                       <button className="btn btn-sm btn-clean btn-icon"
                                               onClick={event => updateUser(m)}>
                                           <img src="assets/media/icon/Edit.svg" alt=""/>
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
