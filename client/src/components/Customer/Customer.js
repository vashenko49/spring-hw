import React from 'react';
import {Container} from "@material-ui/core";
import MaterialTable from "material-table";
import CustomerAPI from "../../Service/CustomerAPI";
import TableIcons from "../TableIcons/TableIcons";

const Customer = () => {

    const loadCustomer = query => new Promise((resolve, reject) => {
        let {page, limit} = query;
        CustomerAPI.getAllCustomer({page, limit})
            .then(res => {
                const {content: data, totalElements: totalCount, number: page} = res;
                resolve({data, totalCount, page})
            })
            .catch(() => {
                reject();
            });
    })

    return (
        <Container>
            <MaterialTable
                icons={TableIcons}
                columns={[
                    {title: "Customer id", field: "id"},
                    {title: "Name", field: "name"},
                ]}
                title={"Customer"}
                data={loadCustomer}/>
        </Container>
    );
};

export default Customer;