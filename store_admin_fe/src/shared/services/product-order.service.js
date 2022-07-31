
import client from '../api_handler/http-client'

const searchProductOrder =  (data) => {
    return  client.request({
        url: 'v1/api/product_order/get-all-by-paging',
        method: 'post',
        data: data
    })
}

const changeStatusProductOrder =  (data) => {
    return  client.request({
        url: 'v1/api/product_order/status',
        method: 'put',
        data: data
    })
}



export {
    searchProductOrder,
    changeStatusProductOrder
}
