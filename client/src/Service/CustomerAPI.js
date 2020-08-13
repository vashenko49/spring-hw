import axios from 'axios'

export default class CustomerAPI {
    static createCustomer = data => axios.post("/api0/customer", data).then(res => res.data);
    static editCustomer = data => axios.put("/api0/customer", data).then(res => res.data);
    static deleteCustomer = id => axios.delete("/api0/customer", {
        params: {
            id
        }
    }).then(res => res.data);
    static getCustomerById = id => axios.get(`/api0/customer/${id}`).then(res => res.data);
    static getAllCustomer = ({page = 0, limit = 10}) => axios.get("/api0/customer", {
        params: {
            page, limit
        }
    }).then(res => res.data);
    static signInCustomer = (email, password) => axios.post("/api0/customer/authenticate", {
        email,
        password
    }).then(res => res.data);
}