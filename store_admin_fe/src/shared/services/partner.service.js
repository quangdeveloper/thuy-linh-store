
import client from '../api_handler/http-client'

const getPartnerById = (id)=>{
    return client.request({
        url: 'v1/api/partner/get-by-id?id='+id,
        method: 'get'
    })
}

const getAllPartner = ()=>{
    return client.request({
        url: 'v1/api/partner/get-all',
        method: 'post'
    })
}


export {
    getPartnerById,
    getAllPartner,
}
