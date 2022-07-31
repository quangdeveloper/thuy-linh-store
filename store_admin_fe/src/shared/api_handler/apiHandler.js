const createApiHandler = (dispatch, getState) => async (apiCall) => {
    try {
        return apiCall
    } catch (e) {
        dispatch()
        throw e
    }
}

export default createApiHandler