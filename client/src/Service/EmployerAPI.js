import axios from 'axios'

export default class EmployerAPI {
    static createEmployer = data => axios.post("/api0/employer", data).then(res => res.data);
    static updateEmployer = data => axios.put("/api0/employer", data).then(res => res.data);
    static deleteEmployer = id => axios.delete("/api0/employer", {
        params: {
            id
        }
    }).then(res => res.data);
    static getEmployerById = id => axios.get(`/api0/employer/${id}`).then(res => res.data);
    static getAllEmployer = ({page = 0, limit = 10}) => axios.get("/api0/employer", {
        params: {
            page, limit
        }
    }).then(res => res.data);
}