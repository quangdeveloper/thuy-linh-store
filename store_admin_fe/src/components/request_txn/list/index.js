import React, {Fragment, useState, useEffect} from 'react'
import SearchArea from "./SearchArea";
import DataTable from "./DataTable";
import Pagination from "react-js-pagination";
import {searchRequestTxn} from "../../../shared/services/request_txn.service";

const RequestTxnManager = (props) => {

    const [page_config, setPage_config] = useState({
        page_index: 1,
        page_size: 10
    });

    const [status, setStatus] = useState(null);
    const [keyword, setKeyword] = useState(null);
    const [from_date, setFrom_date] = useState(null);
    const [to_date, setTo_date] = useState(null);
    const [dataTable, setDataTable] = useState([]);
    const [totalItem, setTotalItem] = useState(0);

    const onChangePaging = event => {
        setPage_config({
            ...page_config, page_index: event
        })
    }

    const onChangeSize = event => {
        setPage_config({
            ...page_config, page_size: Number.parseInt(event.target.value)
        })
    }

    const search = (values) => {

        if (values) {
            setKeyword(values.keyword ? values.keyword : null);
            setStatus(values.status ? values.status : null);
            setFrom_date(values.from_date ? values.from_date : null)
            setTo_date(values.to_date ? values.to_date : null)
            if (values.page_index) {
                setPage_config({...page_config, page_index: values.page_index})
            }
        }

        const data = {
            keyword: values ? values.keyword : keyword,
            status: values ? values.status : status,
            from_date: values ? values.from_date : from_date,
            to_date: values ? values.to_date : to_date,
            page_index: page_config.page_index,
            page_size: page_config.page_size,

        };

        searchRequestTxn(data).then(response => {
            if (response.data.code === "00"){
                setDataTable(response.data.data);
                setTotalItem(response.data.total);
            }
        })
    }

    useEffect(()=>{
        search();
    }, [page_config])


    return (
        <Fragment>
            <div className="content d-flex flex-column flex-column-fluid" id="kt_content">
                <div className="d-flex flex-column-fluid">
                    <div className="container">
                        <div className="card card-custom">
                            <div className="card-header flex-wrap border-0 pt-6 pb-0">
                                <div className="card-title">
                                    <h3 className="card-label">Danh s??ch l???ch h???n</h3>
                                </div>
                            </div>
                            <div className="card-body">

                                <SearchArea search={search}/>

                                <DataTable search={search}
                                           dataTable={dataTable}
                                           page_config={page_config}/>

                                <div className="row">
                                    <div className="col-md-6">
                                        <Pagination
                                            activePage={page_config.page_index}
                                            itemsCountPerPage={page_config.page_size}
                                            innerClass="pagination"
                                            totalItemsCount={totalItem}
                                            itemClass="page-item m-1"
                                            linkClass="page-link rounded"
                                            disabledClass="bg-2 disabled"
                                            pageRangeDisplayed={5}
                                            onChange={onChangePaging}
                                        />
                                    </div>
                                    <div className="text-right col-md-6">
                                   <span>
                                    <label className="style_display">Hi???n th??? &ensp;
                                        <select onChange={onChangeSize} defaultValue="10"
                                                className="selectpicker col- mr-2">
                                                <option value="10">10</option>
                                                <option value="20">20</option>
                                                <option value="30">30</option>
                                                <option value="50">50</option>
                                                <option value="100">100</option>
                                            </select>
                                        c???a {totalItem} b???n ghi</label>
                                    </span>
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
export default RequestTxnManager;
