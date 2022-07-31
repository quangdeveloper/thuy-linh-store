const getStatusCommon = (status) => {

    if (status === 1) {
        return <span className="label font-weight-bold label-lg label-inline label-light-success">Hoạt động</span>
    } else if (status === 2) {
        return <span className="label font-weight-bold label-lg label-inline label-light-danger">Khóa</span>
    } else {
        return 'NA'
    }
}

const getStatusRegister = (status) => {

    if (status === 1) {
        return <span className="label font-weight-bold label-lg label-inline label-light-warning">Chờ duyệt</span>
    } else if (status === 2) {
        return <span className="label font-weight-bold label-lg label-inline label-light-success">Chấp nhận</span>
    } else if (status === 3) {
        return <span className="label font-weight-bold label-lg label-inline label-light-danger">Hủy bỏ</span>
    } else {
        return 'NA'
    }
}

const getAcademicDegree = (degree) => {
    // 1: Thac si    2: Tien si      3: pho giao su      4: giao su
    if (degree === 1) {
        return "Thạc sĩ"
    } else if (degree === 2) {
        return "Tiến sĩ"
    } else if (degree === 3) {
        return "Phó giáo sư"
    } else if (degree === 4) {
        return "Giáo sư"
    } else {
        return 'NA'
    }
}


const getClassify = (cla) => {

    if (cla === 1) {
        return "Giỏi"
    } else if (cla === 2) {
        return "Khá"
    } else if (cla === 3) {
        return "Trung bình"
    } else if (cla === 4) {
        return "Yếu kém"
    } else {
        return 'NA'
    }
}


export {
    getStatusCommon,
    getStatusRegister,
    getAcademicDegree,
    getClassify,

}