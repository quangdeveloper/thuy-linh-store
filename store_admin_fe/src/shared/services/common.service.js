import client from '../api_handler/http-client'


const getShopId = ()  => {
    return  sessionStorage.getItem("Auth") ? JSON.parse(sessionStorage.getItem("Auth")).user_info.id : null;
}


const getAllBrand = () => {
    return client.request({
        url: 'v1/api/product-brand/get-all',
        method: 'get'
    })
}

const getAllMaterial = () => {
    return client.request({
        url: 'v1/api/product-material/get-all',
        method: 'get'
    })
}

const getAllOrigin = () => {
    return client.request({
        url: 'v1/api/product-origin/get-all',
        method: 'get'
    })
}

const getAllProductCategory = ()=>{
    return client.request({
        url: 'v1/api/product-category/get-all',
        method: 'get'
    })
}

const getAllCategoryFeature =  () => {
    return  client.request({
        url: 'v1/api/category-feature/get-all',
        method: 'post',
    })
}


const getAllCategoryProperties =  () => {
    return  client.request({
        url: 'v1/api/category-properties/get-all',
        method: 'post',
    })
}


export {
    getShopId,
    getAllBrand,
    getAllMaterial,
    getAllOrigin,
    getAllCategoryProperties,
    getAllCategoryFeature,
    getAllProductCategory,
}
