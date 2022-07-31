import Popup from "reactjs-popup";
import React from "react";
import {CommonItemLocalStorage} from "../../../shared/utils/CommonItemLocalStorage";
import {getAcademicDegree, getStatusCommon} from "../../../shared/utils/ConmonStatus";
import Block_Unlock from "../block/Block_Unlock";
import DetailTeacher from "../detail/Detail";

const DataTable = (props) => {

    const updateTeacher = (course) => {
        localStorage.setItem(CommonItemLocalStorage.item_update, JSON.stringify(course));
        window.location.href = "/teacher-update"
    }


    return (
        <div className="table-responsive">
            <table className="table datatable-bordered no-wrap">
                <thead>
                <tr>
                    <th>STT</th>
                    <th>Họ và tên</th>
                    <th>Ngày sinh</th>
                    <th>Học vị</th>
                    <th>Địa chỉ</th>
                    <th>Số điện thoại</th>
                    <th>Email</th>
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
                            <td>{m.dateOfBirth.substring(0,10)}</td>
                            <td>{getAcademicDegree(m.academicDegree)}</td>
                            <td className="text-break">{m.address}</td>
                            <td>{m.phone}</td>
                            <td className="text-break">{m.email}</td>
                            <td>{getStatusCommon(m.status)}</td>
                            <td>
                                <span>
                                       <button className="btn btn-sm btn-clean btn-icon"
                                               onClick={event => updateTeacher(m)}>
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
                                       {close => <DetailTeacher close = {close}
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
