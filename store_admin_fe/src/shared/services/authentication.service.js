import client from '../api_handler/http-client'

const login =  (data) => {
    return client.post('/login', data)
}

const logout = () => {
    sessionStorage.clear()
    return Promise.resolve();
}


export {
    login, logout
}
