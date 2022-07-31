import client from '../api_handler/http-client'

const changePassword =  (data) => {
   return  client.request({
        url: '/user/change-password',
        method: 'post',
        data: data
    })
}

const createUser =  (data) => {
    return  client.request({
        url: '/user/create',
        method: 'post',
        data: data
    })
}

const updateUser =  (data) => {
    return  client.request({
        url: '/user/update',
        method: 'post',
        data: data
    })
}
const changeStatusUser =  (data) => {
    return  client.request({
        url: '/user/update-status',
        method: 'post',
        data: data
    })
}

const getUserById =  (request) => {
    return  client.request({
        url: '/user/get-by-id?id=' + request.id,
        method: 'get',
    })
}

const resetPassword =  (user) => {
    return client.request({
        url: '/user/reset-password',
        method: 'post',
        data: user
    })
}

const searchUser =  (data) => {
    return  client.request({
        url: '/user/search',
        method: 'post',
        data: data
    })
}

export {
    changePassword,
    getUserById,
    resetPassword,
    searchUser,
    createUser,
    updateUser,
    changeStatusUser
}
