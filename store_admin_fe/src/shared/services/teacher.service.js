import client from '../api_handler/http-client'

const getApiUrlUploadTeacher = () =>{
    return 'upload/teacher';
}

const createTeacher = (data) => {
    return client.request({
        url: '/teacher/create',
        method: 'post',
        data: data
    })
}

const updateTeacher = (data) => {
    return client.request({
        url: '/teacher/update',
        method: 'post',
        data: data
    })
}
const changeStatusTeacher = (data) => {
    return client.request({
        url: '/teacher/update-status',
        method: 'post',
        data: data
    })
}

const getTeacherById = (request) => {
    return client.request({
        url: '/teacher/get-by-id?id=' + request.id,
        method: 'get',
    })
}

const searchTeacher = (data) => {
    return client.request({
        url: '/teacher/search',
        method: 'post',
        data: data
    })
}

export {
    getTeacherById,
    searchTeacher,
    createTeacher,
    updateTeacher,
    changeStatusTeacher,
    getApiUrlUploadTeacher,

}
