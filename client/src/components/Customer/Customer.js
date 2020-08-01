import React, {createRef} from 'react';
import {Container} from "@material-ui/core";
import MaterialTable from "material-table";
import CustomerAPI from "../../Service/CustomerAPI";
import TableIcons from "../TableIcons/TableIcons";
import AccountAPI from "../../Service/AccountAPI";

const Customer = () => {
    const tableRef = createRef();
    const loadCustomer = query => new Promise((resolve, reject) => {
        let {page, pageSize: limit} = query;
        loadData(page, limit)
            .then(r => {
                if (r.data.length === 0 && r.data.page !== 0) {
                    loadData(page - 1, limit)
                        .then(r => {
                            resolve(r);
                        })
                } else {
                    resolve(r)
                }
            })
            .catch(() => {
                reject();
            });
    })


    const loadData = (page, limit) => CustomerAPI.getAllCustomer({page, limit})
        .then(res => {
            const {content, totalElements: totalCount, number: page} = res;

            const data = [];

            for (let i = 0; i < content.length; i++) {
                data.push(content[i]);
                if (content[i].accounts.length > 0) {
                    for (let j = 0; j < content[i].accounts.length; j++) {
                        data.push({
                            ...content[i].accounts[j],
                            id: "id account" + content[i].accounts[j].id,
                            parentId: content[i].id
                        })
                    }
                }
            }

            return {data, totalCount, page};
        })

    const saveCustomer = (event, rowData) => {

    };

    const deleteCustomer = oldData => new Promise((resolve, reject) => {
        if (oldData.parentId) {
            AccountAPI.deleteAccount(oldData.id)
                .then(() => {
                    resolve();
                })
                .catch(() => {
                    reject();
                })
        } else {
            CustomerAPI.deleteCustomer(oldData.id)
                .then(() => {
                    resolve();
                })
                .catch(() => {
                    reject();
                })
        }


    });

    return (
        <Container>
            <MaterialTable
                tableRef={tableRef}
                icons={TableIcons}
                columns={[
                    {title: "Customer id", field: "id"},
                    {title: "Name", field: "name"},
                    {title: "Number", field: "number"},
                    {title: "Currency", field: "currency"},
                    {title: "Balance", field: "balance"}
                ]}
                parentChildData={(row, rows) => rows.find(a => a.id === row.parentId)}
                title={"Customer"}
                data={loadCustomer}
                options={{
                    search: false
                }}
                editable={{
                    onRowDelete: deleteCustomer
                }}
            />

        </Container>
    );
};

export default Customer;