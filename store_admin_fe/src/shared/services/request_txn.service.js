import client from '../api_handler/http-client'

const searchRequestTxn =  (data) => {
    return  client.request({
        url: '/request-txn/search',
        method: 'post',
        data: data
    })
}

const updateRequestTxn =  (data) => {
    return  client.request({
        url: '/request-txn/update',
        method: 'post',
        data: data
    })
}

export {
    searchRequestTxn,
    updateRequestTxn
}
