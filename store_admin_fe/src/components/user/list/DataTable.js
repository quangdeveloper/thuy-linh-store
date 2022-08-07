import Popup from "reactjs-popup";
import React from "react";
import {CommonItemLocalStorage} from "../../../shared/utils/CommonItemLocalStorage";
import DetailUser from "../detail/Detail";
import {getStatusCommon} from "../../../shared/utils/ConmonStatus";
import Block_Unlock from "../block/Block_Unlock";

const DataTable = (props) => {

    const viewDetail = (course) => {
        localStorage.setItem(CommonItemLocalStorage.item_update, JSON.stringify(course));
        window.location.href = "/user-update"
    }


    return (
        <div className="table-responsive">
            <table className="table datatable-bordered no-wrap">
                <thead>
                <tr>
                    <th>STT</th>
                    <th>Tài khoản</th>
                    <th>Phân quyền</th>
                    <th>Họ và tên</th>
                    <th>Email</th>
                    <th>Số điện thoại</th>
                    <th>Trạng thái</th>
                    <th>Người tạo</th>
                    <th>Thao tác</th>
                </tr>
                </thead>
                <tbody>
                {props.dataTable && props.dataTable.length > 0 && props.dataTable.map((m, idx) => {
                    return (
                        <tr key={idx}>
                            <td>{idx + 1 + (props.page_config.page_index - 1) * props.page_config.page_size}</td>
                            <td>{m.username}</td>
                            <td>{m.groupRoleName}</td>
                            <td>{m.fullName}</td>
                            <td>{m.email}</td>
                            <td>{m.mobile}</td>
                            <td>{getStatusCommon(m.status)}</td>
                            <td>{m.createdBy}</td>
                            <td>
                                <span>
                                       <button className="btn btn-sm btn-clean btn-icon"
                                               onClick={event => viewDetail(m)}>
                                           <img src="assets/media/icon/Edit.svg" alt=""/>
                                       </button>
                                </span>
                                {m.status === 1 ?
                                    <span data-toggle="tooltip" title="Khóa bản ghi">
                                         <Popup modal
                                                closeOnDocumentClick={false}
                                                trigger={
                                             <button className="btn btn-sm btn-clean btn-icon ">
                                                 <img src="assets/media/icon/Lock.svg" alt=""/>
                                             </button>}>
                                                   {close => <Block_Unlock close = {close}
                                                                           search = {props.search}
                                                                           item = {m}/>}
                                              </Popup>

                                    </span>
                                    : <span data-toggle="tooltip" title="Mở khóa bản ghi">
                                         <Popup modal
                                                closeOnDocumentClick={false}
                                                trigger={
                                             <button className="btn btn-sm btn-clean btn-icon">
                                                 <img src="assets/media/icon/Unlock.svg" alt=""/>
                                             </button>}>
                                                  {close => <Block_Unlock close = {close}
                                                                          search = {props.search}
                                                                          item = {m}/>}
                                              </Popup>
                                    </span>}
                                <span data-toggle="tooltip" title="Xem chi tiết">
                                   <Popup modal
                                          closeOnDocumentClick={false}
                                          trigger={
                                              <button className="btn btn-sm btn-clean btn-icon">
                                                  <img src="assets/media/icon/info-circle.svg" alt=""/>
                                              </button>}>
                                       {close => <DetailUser close = {close}
                                                             item = {m}/>}
                                   </Popup>
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
