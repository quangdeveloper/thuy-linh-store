import client from '../api_handler/http-client'



const searchEvaluate = async (request) => {
    const response = await client.request({
        url: 'api/v1/role/get-all-by-filter',
        method: 'post',
        data: request
    })
    return {data: response.data}
}

const searchReport = async (request) => {
    const response = await client.request({
        url: 'api/v1/role/get-all-by-filter',
        method: 'post',
        data: request
    })
    return {data: response.data}
}


export {
    searchEvaluate,
    searchReport
}
