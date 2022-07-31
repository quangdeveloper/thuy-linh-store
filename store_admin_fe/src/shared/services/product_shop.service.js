import client from '../api_handler/http-client'



const updateProduct = (data) => {
    return client.request({
        url: 'v1/api/shop-product',
        method: 'put',
        data: data
    })
}


const searchProduct = (request) => {
    return client.request({
        url: 'v1/api/shop-product/get-all-by-paging',
        method: 'post',
        data: request
    })

}


const getProductById = (id) => {
    return client.request({
        url: 'v1/api/shop-product/get-by-id?id=' + id,
        method: 'get',
    })
}

export {
    updateProduct,
    searchProduct,
}
