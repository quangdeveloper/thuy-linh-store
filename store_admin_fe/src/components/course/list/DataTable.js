import Popup from "reactjs-popup";
import React from "react";
import {CommonItemLocalStorage} from "../../../shared/utils/CommonItemLocalStorage";
import {getStatusCommon} from "../../../shared/utils/ConmonStatus";
import Block_Unlock from "../block/Block_Unlock";

const DataTable = (props) => {

    const updateCourse = (course) => {
        localStorage.setItem(CommonItemLocalStorage.item_update, JSON.stringify(course));
        window.location.href = "/course-update"
    }

    const viewDetail = (course) => {
        localStorage.setItem(CommonItemLocalStorage.item_detail, JSON.stringify(course));

        props.history.push("/course-detail/" + course.code);
    }

    return (
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
                {props.dataTable && props.dataTable.length > 0 && props.dataTable.map((m, idx) => {
                    return (
                        <tr key={idx}>
                            <td>{idx + 1 + (props.page_config.page_index - 1) * props.page_config.page_size}</td>
                            <td>{m.code}</td>
                            <td>{m.name}</td>
                            <td>{m.age}</td>
                            <td>{m.amount}</td>
                            <td>{m.price}</td>
                            <td>{m.startDate.substring(0, 10)}</td>
                            <td>{m.endDate.substring(0, 10)}</td>
                            <td>{getStatusCommon(m.status)}</td>
                            <td>{m.createdBy}</td>
                            <td>
                                <span>
                                       <button className="btn btn-sm btn-clean btn-icon"
                                               onClick={event => updateCourse(m)}>
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
                                                   {close => <Block_Unlock close={close}
                                                                           search={props.search}
                                                                           item={m}/>}
                                              </Popup>

                                    </span>
                                    : <span data-toggle="tooltip" title="Mở khóa bản ghi">
                                         <Popup modal
                                                closeOnDocumentClick={false}
                                                trigger={
                                                    <button className="btn btn-sm btn-clean btn-icon">
                                                        <img src="assets/media/icon/Unlock.svg" alt=""/>
                                                    </button>}>
                                                  {close => <Block_Unlock close={close}
                                                                          search={props.search}
                                                                          item={m}/>}
                                              </Popup>
                                    </span>}
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
