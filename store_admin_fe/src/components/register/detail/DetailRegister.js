import React, {Fragment, useState, useEffect} from 'react'
import {CommonItemLocalStorage} from "../../../shared/utils/CommonItemLocalStorage";
import {getClassify, getStatusRegister} from "../../../shared/utils/ConmonStatus";
import {getAllCourse} from "../../../shared/services/course.service";
import {getRegisterByID} from "../../../shared/services/register.service";

const DetailRegister = (props) => {

    const budgetOptions = [
        {value: 1, label: "Full scholarship"},
        {value: 2, label: "Partial scholarship"},
        {value: 3, label: "Self finance"},
    ]

    const applicantTypeOptions = [
        {value: 1, label: "Government scholarship"},
        {value: 2, label: "Scholarship from organizations"},
        {value: 3, label: "Self-financing"},
    ]

    // 1 = Excellent, 2 = Very good, 3 = Good, 4 = Fair, 5 = Poor
    const languageDegree = [
        {value: 1, label: "Excellent"},
        {value: 2, label: "Very good"},
        {value: 3, label: "Good"},
        {value: 4, label: "Fair"},
        {value: 5, label: "Poor"},
    ]

    const getLanguageDegree = (val) =>{
        if (val){
           const opt =  languageDegree.find(l => l.value = val);
           return opt.label;
        }
        return 'NA'
    }


    const register = JSON.parse(localStorage.getItem(CommonItemLocalStorage.item_detail));
    const [register_details, setRegister_detail] = useState(null);
    const [courseList, setCourseList] = useState([])

    useEffect(() => {

        getRegisterByID(register.id).then(res =>{
            if (res.data.code === "00"){
                setRegister_detail(res.data.data);
            }
        })

        getAllCourse().then(res => {
            if (res.data.code === "00" && res.data.data.data && res.data.data.data.length > 0) {
                setCourseList(res.data.data.data)
            }
        })
    }, [])


    const viewList = () => {
        window.location.href = "/register"
    }

    const getNameCourse = (course_id) => {
        if (courseList.length > 0) {
            const course = courseList.find(c => c.id === course_id);
            if (course) {
                return course.name;
            }
        }
        return 'NA'
    }

    const getApplicationType = (app) => {
        if (app) {
            const application = applicantTypeOptions.find(c => c.value === app);
            if (application) {
                return application.label;
            }
        }
        return 'NA'
    }

    const getBudget = (bud) => {
        if (bud) {
            const budget = budgetOptions.find(c => c.value === bud);
            if (budget) {
                return budget.label;
            }
        }
        return 'NA'
    }

    return (
        <Fragment>
            <div className="content d-flex flex-column flex-column-fluid" id="kt_content">
                <div className="d-flex flex-column-fluid">
                    <div className="container">
                        <div className="card card-custom">
                            <div className="card-header flex-wrap border-0 pt-6 pb-0">
                                <h4>Thông tin chi tiết hồ sơ đăng kí</h4>
                            </div>
                            <div className="card-body">
                                <div className="row">
                                    <div className="form-group col-lg-12">Thông tin cơ bản</div>

                                    <div className="form-group col-lg-6">
                                        <label className="col-4 mb-0">Họ và tên</label>
                                        <label className="col-8 mb-0">{register.fullName}</label>
                                    </div>
                                    <div className="form-group col-lg-6">
                                        <label className="col-4 mb-0">Ngày sinh</label>
                                        <label className="col-8 mb-0">{register.dateOfBirth.substring(0, 10)}</label>
                                    </div>
                                    <div className="form-group col-lg-6">
                                        <label className="col-4 mb-0">Giới tính</label>
                                        <label className="col-8 mb-0">
                                            {register.gender === 1 ? 'Nam' : 'Nữ'}
                                        </label>
                                    </div>
                                    <div className="form-group col-lg-6">
                                        <label className="col-4 mb-0">Nơi sinh</label>
                                        <label className="col-8 mb-0">{register.placeOfBirth}</label>
                                    </div>
                                    <div className="form-group col-lg-6">
                                        <label className="col-4 mb-0">Quốc gia</label>
                                        <label className="col-8 mb-0">{register.national}</label>
                                    </div>

                                    <div className="form-group col-lg-6">
                                        <label className="col-4 mb-0">Số hộ chiếu</label>
                                        <label className="col-8 mb-0">{register.passport}</label>
                                    </div>

                                    <div className="form-group col-lg-6">
                                        <label className="col-4 mb-0">Trường THPT</label>
                                        <label className="col-8 mb-0">{register.highSchool}</label>
                                    </div>

                                    <div className="form-group col-lg-6">
                                        <label className="col-4 mb-0">Năm tốt nghiệp</label>
                                        <label className="col-8 mb-0">{register.graduatedYear}</label>
                                    </div>

                                    <div className="form-group col-lg-6">
                                        <label className="col-4 mb-0">Xếp loại tốt nghiệp</label>
                                        <label className="col-8 mb-0">{getClassify(register.classify)}</label>
                                    </div>

                                    <div className="form-group col-lg-6">
                                        <label className="col-4 mb-0">Thành tích đặc biệt </label>
                                        <label className="col-8 mb-0">{register.specialAchievements}</label>
                                    </div>

                                    <hr width={1200}/>
                                    <div className="form-group col-lg-12">Thông tin liên hệ trước khi tới Việt Nam</div>
                                    <div className="form-group col-lg-6">
                                        <label className="col-4 mb-0">Địa chỉ</label>
                                        <label className="col-8 mb-0">{register.priorAddress}</label>
                                    </div>
                                    <div className="form-group col-lg-6">
                                        <label className="col-4 mb-0">Số điện thoại</label>
                                        <label className="col-8 mb-0">{register.priorPhone}</label>
                                    </div>
                                    <div className="form-group col-lg-6">
                                        <label className="col-4 mb-0">Số Fax</label>
                                        <label className="col-8 mb-0">{register.priorFax}</label>
                                    </div>
                                    <div className="form-group col-lg-6">
                                        <label className="col-4 mb-0">Email</label>
                                        <label className="col-8 mb-0">{register.priorEmail}</label>
                                    </div>

                                    <hr width={1200}/>
                                    <div className="form-group col-lg-12">Thông tin liên hệ tại Việt Nam</div>
                                    <div className="form-group col-lg-6">
                                        <label className="col-4 mb-0">Địa chỉ</label>
                                        <label className="col-8 mb-0">{register.address}</label>
                                    </div>
                                    <div className="form-group col-lg-6">
                                        <label className="col-4 mb-0">Số điện thoại</label>
                                        <label className="col-8 mb-0">{register.phone}</label>
                                    </div>
                                    <div className="form-group col-lg-6">
                                        <label className="col-4 mb-0">Số Fax</label>
                                        <label className="col-8 mb-0">{register.fax}</label>
                                    </div>
                                    <div className="form-group col-lg-6">
                                        <label className="col-4 mb-0">Email</label>
                                        <label className="col-8 mb-0">{register.email}</label>
                                    </div>

                                    <hr width={1200}/>
                                    <div className="form-group col-lg-12">Người liên hệ khẩn cấp</div>
                                    <div className="form-group col-lg-6">
                                        <label className="col-4 mb-0">Họ và tên</label>
                                        <label className="col-8 mb-0">
                                            {register_details
                                            && register_details.personNotify
                                            && register_details.personNotify.name ?
                                                register_details.personNotify.name : null}
                                        </label>
                                    </div>
                                    <div className="form-group col-lg-6">
                                        <label className="col-4 mb-0">Địa chỉ</label>
                                        <label className="col-8 mb-0">{register.address}</label>
                                    </div>
                                    <div className="form-group col-lg-6">
                                        <label className="col-4 mb-0">Số điện thoại</label>
                                        <label className="col-8 mb-0">
                                            {register_details
                                            && register_details.personNotify
                                            && register_details.personNotify.phone ?
                                                register_details.personNotify.phone : null}
                                        </label>
                                    </div>
                                    <div className="form-group col-lg-6">
                                        <label className="col-4 mb-0">Số Fax</label>
                                        <label className="col-8 mb-0">
                                            {register_details
                                            && register_details.personNotify
                                            && register_details.personNotify.fax ?
                                                register_details.personNotify.fax : null}
                                        </label>
                                    </div>
                                    <div className="form-group col-lg-6">
                                        <label className="col-4 mb-0">Email</label>
                                        <label className="col-8 mb-0">
                                            {register_details
                                            && register_details.personNotify
                                            && register_details.personNotify.email ?
                                                register_details.personNotify.email : null}
                                        </label>
                                    </div>

                                    <hr width={1200}/>
                                    <div className="form-group col-lg-12">Danh sách thành viên gia đình</div>
                                    <div className="col-md-12">
                                        <div className="table-responsive">
                                            <table className="table table-bordered datatable-bordered no-wrap">
                                                <thead>
                                                <tr>
                                                    <th>STT</th>
                                                    <th>Họ và tên</th>
                                                    <th>Tuổi</th>
                                                    <th>Quan hệ</th>
                                                    <th>Địa chỉ</th>
                                                    <th>Công việc</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                {register_details
                                                && register_details.family_list
                                                && register_details.family_list.length > 0
                                                && register_details.family_list.map((p, id) => {
                                                    return <tr key={id}>
                                                        <td>{id + 1}</td>
                                                        <td>{p.name }</td>
                                                        <td>{p.age}</td>
                                                        <td>{p.relation}</td>
                                                        <td>{p.address}</td>
                                                        <td>{p.job}</td>
                                                    </tr>
                                                })}
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>


                                    <hr width={1200}/>
                                    <div className="form-group col-lg-12">Danh sách ngoại ngữ</div>
                                    <div className="col-md-12">
                                        <div className="table-responsive">
                                            <table className="table table-bordered datatable-bordered no-wrap">
                                                <thead>
                                                <tr>
                                                    <th>STT</th>
                                                    <th>Ngôn ngữ</th>
                                                    <th>Trình độ nghe</th>
                                                    <th>Trình độ nói</th>
                                                    <th>Trình độ đọc</th>
                                                    <th>Trình độ viết</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                {register_details
                                                && register_details.language_list
                                                && register_details.language_list.length > 0
                                                && register_details.language_list.map((p, id) => {
                                                    return <tr key={id}>
                                                        <td>{id + 1}</td>
                                                        <td>{p.name}</td>
                                                        <td>{getLanguageDegree(p.listening)}</td>
                                                        <td>{getLanguageDegree(p.speaking)}</td>
                                                        <td>{getLanguageDegree(p.reading)}</td>
                                                        <td>{getLanguageDegree(p.writing)}</td>

                                                    </tr>
                                                })}
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>

                                    <hr width={1200}/>
                                    <div className="form-group col-lg-12">Thông tin đăng kí</div>
                                    <div className="form-group col-lg-6">
                                        <label className="col-4 mb-0">Khoa đăng kí</label>
                                        <label className="col-8 mb-0">{register.department}</label>
                                    </div>
                                    <div className="form-group col-lg-6">
                                        <label className="col-4 mb-0">Khóa học</label>
                                        <label className="col-8 mb-0">
                                            {register.courseId ? getNameCourse(register.courseId) : null}
                                        </label>
                                    </div>
                                    <div className="form-group col-lg-6">
                                        <label className="col-4 mb-0">Chi nhánh</label>
                                        <label className="col-8 mb-0">{register.branch}</label>
                                    </div>
                                    <div className="form-group col-lg-6">
                                        <label className="col-4 mb-0">Năm học </label>
                                        <label className="col-8 mb-0">{register.academicYear}</label>
                                    </div>
                                    <div className="form-group col-lg-6">
                                        <label className="col-4 mb-0">Loại ứng viên</label>
                                        <label className="col-8 mb-0">
                                            {register.applicantType ? getApplicationType(register.applicantType) : null}
                                        </label>
                                    </div>
                                    <div className="form-group col-lg-6">
                                        <label className="col-4 mb-0">Ngân sách</label>
                                        <label className="col-8 mb-0">
                                            {register.budget ? getBudget(register.budget) : null}
                                        </label>
                                    </div>
                                    <div className="form-group col-lg-6">
                                        <label className="col-4 mb-0">Trang thái hồ sơ</label>
                                        <label className="col-8 mb-0">{getStatusRegister(register.status)}</label>
                                    </div>
                                    {register.status === 3 ?
                                        <div className="form-group col-lg-6">
                                            <label className="col-4 mb-0">Lí do hủy</label>
                                            <label className="col-8 mb-0">{register.reasonCancel}</label>
                                        </div>
                                        : null
                                    }

                                    <div className="col-lg-12">
                                        <div className="modal-footer"
                                             style={{justifyContent: "space-between", padding: "1.5rem 0rem"}}>
                                            <button type="button"
                                                    className="btn btn-bg-light"
                                                    data-dismiss="modal"
                                                    aria-label="Close"
                                                    onClick={viewList}>
                                                Quay lại
                                            </button>
                                        </div>
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
export default DetailRegister
