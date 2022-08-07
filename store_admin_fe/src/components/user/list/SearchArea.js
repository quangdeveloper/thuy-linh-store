import {Formik} from 'formik';
import React, { useState} from 'react'
import Select from "react-select";


const SearchArea = (props) => {

    const initValue = {
        keyword: "",
        status: null,
    };

    const [statusOptions, setStatusOption] = useState([
        {label: "Tất cả", value: null},
        {label: "Hoạt động", value: 1},
        {label: "Khóa", value: 2},
    ])

    return (
        <Formik
            initialValues={initValue}
            onSubmit={(values) => {

                const data = {
                    keyword: values.keyword,
                    status: values.status,
                };
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
