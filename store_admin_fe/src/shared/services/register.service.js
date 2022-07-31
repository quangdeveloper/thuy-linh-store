import client from '../api_handler/http-client'

const changeStatusRegister = (data) => {
    return client.request({
        url: '/register/update-status',
        method: 'post',
        data: data
    })
}


const searchRegister = (data) => {
    return client.request({
        url: '/register/search',
        method: 'post',
        data: data
    })
}

const getRegisterByID = (id) => {
    return client.request({
        url: '/register/get-by-id?id=' + id,
        method: 'get',
    })
}

export {
    searchRegister,
    changeStatusRegister,
    getRegisterByID,
}
