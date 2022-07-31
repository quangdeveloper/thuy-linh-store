import {Formik} from 'formik';
import React, {Fragment, useState, useEffect} from 'react'
import Select from "react-select";


const SearchArea = (props) => {

    const initValue = {
        keyword: "",
        status: null,
        from_date: null,
        to_date: null,
    };

    const [statusOptions, setStatusOption] = useState([
        {label: "Tất cả", value: null},
        {label: "Chưa duyệt", value: 1},
        {label: "Chấp nhận", value: 2},
        {label: "Loại bỏ", value: 3},
    ])

    return (
        <Formik
            initialValues={initValue}
            onSubmit={(values) => {

                const data = {
                    keyword: values.keyword,
                    status: values.status,
                    from_date: values.from_date,
                    to_date: values.to_date
                };

                console.log(data)
                props.search(data);
            }}
        >{({
               errors,
               values,
               touched,
               handleChange,
               handleBlur,
               handleSubmit,
               handleReset
           }) => (
            <form className="form" onSubmit={handleSubmit}>
                <div className="row">
                    <div className="form-group col-lg-4">
                        <label className="mr-3 mb-0 d-none d-md-block no-wrap">
                            Tìm kiếm
                        </label>
                        <div className="input-icon">
                            <input type="text"
                                   className="form-control"
                                   name="keyword"
                                   onBlur={handleBlur}
                                   onChange={handleChange}
                                   value={values.keyword}/>
                        </div>
                    </div>

                    <div className="form-group col-lg-4">
                        <label className="mr-3 mb-0 d-none d-md-block no-wrap">
                            Trạng thái
                        </label>
                        <Select placeholder="Tất cả"
                                options={statusOptions}
                                isSearchable={true}
                                onChange={selectedOption => {
                                    let event = {
                                        target: {
                                            name: 'status',
                                            value: selectedOption.value
                                        }
                                    }
                                    handleChange(event)
                                }}
                                onBlur={handleBlur}>
                        </Select>
                    </div>

                    <div className="form-group col-lg-4">
                        <label className="mr-3 mb-0 d-none d-md-block no-wrap">
                           Từ ngày
                        </label>
                        <div className="input-icon">
                            <input type="date"
                                   className="form-control"
                                   name="from_date"
                                   onBlur={handleBlur}
                                   onChange={handleChange}
                                   value={values.from_date}/>
                        </div>
                    </div>
                    <div className="form-group col-lg-4">
                        <label className="mr-3 mb-0 d-none d-md-block no-wrap">
                            Đến ngày
                        </label>
                        <div className="input-icon">
                            <input type="date"
                                   className="form-control"
                                   name="to_date"
                                   onBlur={handleBlur}
                                   onChange={handleChange}
                                   value={values.to_date}/>
                        </div>
                    </div>

                    <div className="form-group col-lg-4">
                        <label className="mr-3 mb-0 d-none d-md-block no-wrap">&ensp;</label>
                        <button onChange={handleChange}
                                type="submit"
                                className="btn btn-light-primary btn-primary--icon "
                                id="kt_search">
                                        <span>
                                            <i className="la la-search"></i>
                                            <span>Tìm kiếm</span>
                                        </span>
                        </button>
                    </div>

                </div>
            </form>
        )}</Formik>)
}
export default SearchArea
