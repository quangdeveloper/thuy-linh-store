import client from '../api_handler/http-client'

const getAllGroupRole =  () => {
   return  client.request({
        url: '/group-role/get-all',
        method: 'post'
    })
}

export {
    getAllGroupRole
}
