import React, {Fragment, useState, useEffect} from 'react'
import {Formik} from "formik";
import Select from "react-select";
import FieldRequired from "../../../containers/required/FieldRequired";
import InvalidField from "../../../containers/required/InvalidField";
import {toast} from "react-toastify";
import {createTeacher, getApiUrlUploadTeacher, updateTeacher} from "../../../shared/services/teacher.service";
import {CommonItemLocalStorage} from "../../../shared/utils/CommonItemLocalStorage";
import {formatStringDate} from "../../../shared/utils/DateUtil";
import {handleSubmissionUpload} from "../../../shared/form_data_handler/submit-formdata";

const UpdateTeacher = (props) => {

    const academic_degree_options = [
        {value: 1, label: "Thạc sĩ"},
        {value: 2, label: "Tiến sĩ"},
        {value: 3, label: "Phó giáo sư"},
        {value: 4, label: "Giáo sư"},
    ]
    const gender_options = [
        {value: 1, label: "Nam"},
        {value: 2, label: "Nữ"},
    ]

    const validates = values => {
        const errors = {};

        if (!values.full_name) {
            errors.full_name = "Vui lòng nhập họ và tên"
        }

        if (!values.gender) {
            errors.gender = "Vui lòng chọn giới tính"
        }

        if (!values.date_of_birth ) {
            errors.date_of_birth = "Vui lòng chọn ngày sinh"
        }

        if (!values.address) {
            errors.address = "Vui lòng nhập địa chỉ"
        }

        if (!values.email) {
            errors.email = "Vui lòng nhập địa chỉ mail"
        }

        if (!values.phone) {
            errors.phone = "Vui lòng nhập số điện thoại"
        }

        if (!values.fax) {
            errors.fax = "Vui lòng nhập số fax"
        }

        if (!values.academic_degree) {
            errors.academic_degree = "Vui lòng nhập chọn học vị"
        }

        console.log(values)
        return errors
    };

    const teacher = JSON.parse(localStorage.getItem(CommonItemLocalStorage.item_update));
    const default_academic_degree = teacher.academicDegree;
    const default_gender = teacher.gender;

    const initValue = {
        id: teacher.id,
        full_name: teacher.fullName,
        date_of_birth: formatStringDate(teacher.dateOfBirth),
        address: teacher.address,
        email: teacher.email,
        phone: teacher.phone,
        fax: teacher.fax,
        academic_degree: default_academic_degree,
        gender: default_gender,
        short_desc: teacher.shortDesc
    };

    const [logo, setLogo] = useState("");
    const [errorLogo, setErrorLogo] = useState(null);
    const [selectedFile, setSelectedFile] = useState(null);

    const updateOldTeacher = (teacher) => {
        if (selectedFile){
            uploadTeacherImage()
                .then((response) => response.json())
                .then((result) => {
                    if (result.code === "00") {
                        const _teacher = {
                            ...teacher,
                            image: result.message
                        }
                        updateTeacher(_teacher).then(res => {
                            if (res.data.code === "00") toast.success("Cập nhật thông tin giảng viên thành công")
                            else toast.error(res.data.message)
                        })
                    } else toast.error(result.message)
                })
                .catch((error) => {
                    toast.error("Kết nỗi bị gián đoạn. Vui lòng thử lại sau.")
                });
        }else{
            updateTeacher(teacher).then(res => {
                if (res.data.code === "00") toast.success("Cập nhật thông tin giảng viên thành công")
                else toast.error(res.data.message)
            })
        }
    }

    const uploadTeacherImage = () => {
        const form_data = new FormData();
        form_data.append("file", selectedFile);
        form_data.append("desc", "upload image teacher");
        return handleSubmissionUpload(form_data, getApiUrlUploadTeacher(), "POST");
    }

    const handelChangeLogo = (event) => {

        const file = event.target.files[0];
        if (file) {
            if (file.size > 200 * 1024) {
                setErrorLogo("File upload quá dung lượng ( > 200KB). Vui lòng kiểm tra lại")
            } else {
                const reader = new FileReader();
                reader.readAsDataURL(file)
                reader.onload = () => {
                    if (reader.readyState === 2) {
                        setLogo(reader.result);
                        setSelectedFile(file);
                    }
                }
            }
        }
    }

    const removeLogo = () => {
        setSelectedFile(null);
        setLogo(null);
    }
    const viewList = () => {
        window.location.href = "/teacher"
    }

    return (
        <Fragment>
            <div className="content d-flex flex-column flex-column-fluid" id="kt_content">
                <div className="d-flex flex-column-fluid">
                    <div className="container">
                        <div className="card card-custom">
                            <div className="card-header flex-wrap border-0 pt-6 pb-0">
                                <h4>Cập nhật thông tin giảng viên</h4>
                            </div>
                            <div className="card-body">
                                <Formik
                                    initialValues={initValue}
                                    validate={validates}
                                    onSubmit={(values) => {
                                        updateOldTeacher(values)
                                    }}
                                >{({
                                       values,
                                       errors,
                                       touched,
                                       handleChange,
                                       handleBlur,
                                       handleSubmit,
                                   }) => (
                                    <form className="form" onSubmit={handleSubmit}>
                                        <div className="row ">
                                            <div className="col-lg-12">
                                                <div className="form-group">
                                                    <label>Ảnh giảng viên</label>

                                                    <div className="files-upload">
                                                        <div className="image-input image-input-outline"
                                                             id="kt_image_4">
                                                            <div className="image-input-wrapper"
                                                                 style={{backgroundImage: `url(${logo})`}}>
                                                            </div>
                                                            <label
                                                                className="btn btn-xs btn-icon btn-circle btn-white btn-hover-text-primary btn-shadow"
                                                                data-action="change" data-toggle="tooltip" title=""
                                                                data-original-title="Change avatar">
                                                                <svg xmlns="http://www.w3.org/2000/svg" width="16"
                                                                     height="16"
                                                                     fill="currentColor" className="bi bi-pencil"
                                                                     viewBox="0 0 16 16">
                                                                    <path
                                                                        d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                                                                </svg>
                                                                <input type="file" name="logo"
                                                                       accept=".png, .jpg, .jpeg"
                                                                       onChange={e => {
                                                                           handelChangeLogo(e);
                                                                       }}/>
                                                            </label>
                                                            <span onClick={removeLogo}
                                                                  className="btn btn-xs btn-icon btn-circle btn-white btn-hover-text-primary btn-shadow"
                                                                  data-action="remove" data-toggle="tooltip"
                                                                  title="Remove avatar">
                                                                <svg xmlns="http://www.w3.org/2000/svg" width="16"
                                                                     height="16"
                                                                     fill="currentColor" className="bi bi-x"
                                                                     viewBox="0 0 16 16">
                                                                  <path
                                                                      d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                                                                </svg>
                                                            </span>
                                                        </div>
                                                        <InvalidField
                                                            touch={touched.logo}
                                                            error={errorLogo}/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div className="col-lg-6">
                                                <div className="form-group">
                                                    <label>Họ và tên</label>
                                                    <FieldRequired/>
                                                    <input type="text"
                                                           className="form-control"
                                                           name="full_name"
                                                           value={values.full_name}
                                                           onChange={handleChange}/>
                                                    <InvalidField
                                                        touch={touched.full_name}
                                                        error={errors.full_name}/>
                                                </div>
                                            </div>
                                            <div className="col-lg-6">
                                                <div className="form-group">
                                                    <label>Ngày sinh</label>
                                                    <FieldRequired/>
                                                    <input type="date"
                                                           className="form-control"
                                                           name="date_of_birth"
                                                           value={values.date_of_birth}
                                                           onChange={handleChange}/>
                                                    <InvalidField
                                                        touch={touched.date_of_birth}
                                                        error={errors.date_of_birth}/>
                                                </div>
                                            </div>
                                            <div className="col-lg-6">
                                                <div className="form-group">
                                                    <label>Giới tính</label>
                                                    <FieldRequired/>
                                                    <Select placeholder="--Chọn giới tính--"
                                                            options={gender_options}
                                                            isSearchable={true}
                                                            defaultValue={
                                                                gender_options.find(r => r.value = default_gender)
                                                            }
                                                            onChange={selectedOption => {
                                                                let event = {
                                                                    target: {
                                                                        name: 'gender',
                                                                        value: selectedOption.value
                                                                    }
                                                                }
                                                                handleChange(event)
                                                            }}
                                                            onBlur={handleBlur}>
                                                    </Select>
                                                    <InvalidField
                                                        touch={touched.gender}
                                                        error={errors.gender}/>
                                                </div>
                                            </div>
                                            <div className="col-lg-6">
                                                <div className="form-group">
                                                    <label>Địa chỉ</label>
                                                    <FieldRequired/>
                                                    <input type="text"
                                                           className="form-control"
                                                           name="address"
                                                           value={values.address}
                                                           onChange={handleChange}/>
                                                    <InvalidField
                                                        touch={touched.address}
                                                        error={errors.address}/>
                                                </div>
                                            </div>

                                            <div className="col-lg-6">
                                                <div className="form-group">
                                                    <label>Phone</label>
                                                    <FieldRequired/>
                                                    <input type="text"
                                                           className="form-control"
                                                           name="phone"
                                                           value={values.phone}
                                                           onChange={handleChange}/>
                                                    <InvalidField
                                                        touch={touched.phone}
                                                        error={errors.phone}/>
                                                </div>
                                            </div>
                                            <div className="col-lg-6">
                                                <div className="form-group">
                                                    <label>Email</label>
                                                    <FieldRequired/>
                                                    <input type="text"
                                                           className="form-control"
                                                           name="email"
                                                           value={values.email}
                                                           onChange={handleChange}/>
                                                    <InvalidField
                                                        touch={touched.email}
                                                        error={errors.email}/>
                                                </div>
                                            </div>
                                            <div className="col-lg-6">
                                                <div className="form-group">
                                                    <label>Số fax</label>
                                                    <FieldRequired/>
                                                    <input type="text"
                                                           className="form-control"
                                                           name="fax"
                                                           value={values.fax}
                                                           onChange={handleChange}/>
                                                    <InvalidField
                                                        touch={touched.fax}
                                                        error={errors.fax}/>
                                                </div>
                                            </div>

                                            <div className="col-lg-6">
                                                <div className="form-group">
                                                    <label>Học vị </label>
                                                    <FieldRequired/>
                                                    <Select placeholder="--Chọn học vị--"
                                                            options={academic_degree_options}
                                                            isSearchable={true}
                                                            defaultValue={
                                                                academic_degree_options.find(r => r.value = default_academic_degree)
                                                            }
                                                            onChange={selectedOption => {
                                                                let event = {
                                                                    target: {
                                                                        name: 'academic_degree',
                                                                        value: selectedOption.value
                                                                    }
                                                                }
                                                                handleChange(event)
                                                            }}
                                                            onBlur={handleBlur}>
                                                    </Select>

                                                    <InvalidField
                                                        touch={touched.academic_degree}
                                                        error={errors.academic_degree}/>
                                                </div>
                                            </div>
                                            <div className="col-lg-6">
                                                <div className="form-group">
                                                    <label>Mô tả ngắn</label>
                                                    <FieldRequired/>
                                                    <textarea type="text"
                                                              className="form-control"
                                                              name="short_desc"
                                                              value={values.short_desc}
                                                              onChange={handleChange}/>
                                                    <InvalidField
                                                        touch={touched.short_desc}
                                                        error={errors.short_desc}/>
                                                </div>
                                            </div>

                                            <div className="col-lg-12">
                                                <div className="modal-footer" style={{justifyContent: "space-between", padding: "1.5rem 0rem"}}>
                                                    <button type="button"
                                                            className="btn btn-bg-light"
                                                            data-dismiss="modal"
                                                            aria-label="Close"
                                                            onClick={viewList}>
                                                        Quay lại
                                                    </button>
                                                    <button type="submit"
                                                            className="btn btn-light-primary">
                                                        <span>Cập nhật</span>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                )}</Formik>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </Fragment>
    )
}
export default UpdateTeacher
