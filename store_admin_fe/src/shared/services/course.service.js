import client from '../api_handler/http-client'


const createCourse =  (data) => {
    return  client.request({
        url: '/course/create',
        method: 'post',
        data: data
    })
}

const updateCourse =  (data) => {
    return  client.request({
        url: '/course/update',
        method: 'post',
        data: data
    })
}
const changeStatusCourse =  (data) => {
    return  client.request({
        url: '/course/update-status',
        method: 'post',
        data: data
    })
}


const searchCourse =  (data) => {
    return  client.request({
        url: '/course/search',
        method: 'post',
        data: data
    })
}

const getAllCourse =  () => {
    return  client.request({
        url: '/course/get-all',
        method: 'get'
    })
}

export {
    searchCourse,
    createCourse,
    updateCourse,
    changeStatusCourse,
    getAllCourse,

}
