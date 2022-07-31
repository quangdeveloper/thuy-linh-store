import axios from 'axios';
import {Service} from 'axios-middleware';
import {toast} from "react-toastify";

const service = new Service(axios);

service.register({
  onSync(promise) {
    return promise;
  },
  onResponse(response) {
    return response;
  },
  onResponseError(error) {
    toast.error("Kết nỗi bị gián đoạn. Vui lòng thử lại sau.")
  }
});

const client = axios.create(
    {
      responseType: "json"
    }
)

const getApiBase = async () => {
  const response = await axios('../assets/app.setting.json'
      , {
        headers: {
          'Content-Type': 'application/json',
          'Accept': 'application/json'
        }
      }
  )
  return response
}

client.interceptors.request.use(async (config) => {
  const api_url = sessionStorage.getItem("APIUrl");
  if (!api_url) {
    const response = await getApiBase();
    sessionStorage.setItem("APIUrl", response.data.api_url);
    config.baseURL = response.data.api_url;
  } else {
    config.baseURL = api_url
  }
  const auth = sessionStorage.getItem("Auth");

  if (auth) {
    config.headers.common['Authorization'] = 'Bearer ' + JSON.parse(auth).token;
  }
  return config
})

export default client
