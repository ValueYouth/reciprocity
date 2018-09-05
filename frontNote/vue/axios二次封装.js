import axios from 'axios'
import { Toast } from 'vant'
function getToken () {
    const token = localStorage.getItem('token')
    if (token) {
        return token
    }
    return null
}
let axiosIns = axios.create({
    headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    },
    timeout: 30000
})

if (process.env.NODE_ENV === 'development') {
    axiosIns.defaults.baseURL = 'http://apptest.gyenno.com/service_im_test/v3'
} else if (process.env.NODE_ENV === 'testing') {
    axiosIns.defaults.baseURL = '***'
} else if (process.env.NODE_ENV === 'production') {
    axiosIns.defaults.baseURL = '***'
}

axiosIns.defaults.validateStatus = function (status) {
    return true
}
// 添加请求拦截器
axiosIns.interceptors.request.use((config) => {
    // 发送请求前做某些事情
    // 配置config
    if (getToken()) {
        config.headers.Authorization = 'Bearer ' + getToken()
    }
    return config
})
// ↑↑↑↑↑↑第二参数，发送请求错误后做某些事情↑↑↑↑↑↑

// 添加响应拦截器
axiosIns.interceptors.response.use((response) => {
    let status = response.status
    if (status === 200 || status === 401 || status === 403) {
        return Promise.resolve(response)
    } else {
        return Promise.reject(response)
    }
})
let ajaxMethod = ['get', 'post', 'delete', 'put']
let api = {}
ajaxMethod.forEach((method) => {
    // 数组取值的两种方式
    api[method] = function (uri, data, config) {
        return new Promise(function (resolve, reject) {
            axiosIns[method](uri, data, config).then((response) => {
                let status = response.status
                console.log(response)
                if (status === 403) {
                    Toast('权限不足！')
                } else if (status === 401) {
                    Toast(response.data.message)
                } else {
                    resolve(response)
                }
            }).catch((response) => {
                console.log(response)
                Toast('请求失败')
                reject(response)
            })
        })
    }
})

export default api
