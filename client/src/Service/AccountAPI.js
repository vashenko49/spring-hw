import axios from 'axios'

export default class AccountAPI {
    static createAccount = data => axios.post("/api0/account", data).then(res => res.data);
    static updateAccount = data => axios.put("/api0/account", data).then(res => res.data);
    static deleteAccount = id => axios.delete("/api0/account", {
        params: {
            id
        }
    }).then(res => res.data);
    static getAccountById = id => axios.get(`/api0/account/${id}`).then(res => res.data);
    static getAllAccount = ({page = 0, limit = 10}) => axios.get("/api0/account", {
        params: {
            page, limit
        }
    }).then(res => res.data);
    static getAccountByNumber = number => axios.get(`/api0/account/number/${number}`).then(res => res.data);
}