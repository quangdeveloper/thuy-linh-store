import {toast} from "react-toastify";


const handleSubmissionFormData = (form_data, api_url, method, fn_callback) => {

    const url = sessionStorage.getItem("APIUrl") + api_url;

    fetch(url, {
        headers: {
            'Accept': 'application/json',
            'Authorization': 'Bearer ' + JSON.parse(sessionStorage.getItem("Auth")).token
        },
        method: method,
        body: form_data,
    })
        .then((response) => response.json())
        .then((result) => {
            if (result.code == 0) {
                toast.success(result.message);
                fn_callback();
            } else {
                toast.error(result.message)
                fn_callback();
            }
        }).catch((error) => {
        toast.error("Kết nỗi bị gián đoạn. Vui lòng thử lại sau.")
    })
}


const handleSubmissionUpload = (form_data, api_url, method) => {

    const url = sessionStorage.getItem("APIUrl") + api_url;

    return fetch(url, {
        headers: {
            'Accept': 'application/json',
            'Authorization': 'Bearer ' + JSON.parse(sessionStorage.getItem("Auth")).token
        },
        method: method,
        body: form_data,
    })
}


const getServerUrl = () => {
    return sessionStorage.getItem("APIUrl") ? sessionStorage.getItem("APIUrl") : "localhost:8080";
}

export {
    handleSubmissionFormData,
    getServerUrl,
    handleSubmissionUpload,

}