import React, {createRef} from 'react';
import {Container, Typography} from "@material-ui/core";
import MaterialTable from "material-table";
import CustomerAPI from "../../Service/CustomerAPI";
import TableIcons from "../TableIcons/TableIcons";
import AccountAPI from "../../Service/AccountAPI";
import makeStyles from "@material-ui/core/styles/makeStyles";
import {bindActionCreators} from "redux";
import { connect } from 'react-redux';
import * as SystemAction from "../../actions/System/System"

const useStyles = makeStyles({
    root:{
        paddingTop:"25px"
    }
})

const Customer = ({history}) => {
    const classes = useStyles();
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
                            id: content[i].accounts[j].id * 951,
                            parentId: content[i].id
                        })
                    }
                }
            }

            return {data, totalCount, page};
        })

    const saveOrUpdateCustomer = (event, rowData, status) => {
        if(status==="update"){
            history.push(`/customer-detail?status=${status}&id=${rowData.id}`);
        }else {
            history.push(`/customer-detail?status=${status}`);
        }
    };

    const deleteCustomer = oldData => new Promise((resolve, reject) => {

        if (oldData.parentId) {
            AccountAPI.deleteAccount(oldData.id / 951)
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
        <Container className={classes.root}>
            <MaterialTable
                tableRef={tableRef}
                icons={TableIcons}
                columns={[
                    {
                        title: "Customer id", field: "id", render: rowData => {
                            return rowData.parentId ? (<></>) : (<Typography variant={"body1"}>{rowData.id}</Typography>)
                        }
                    },
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
                actions={[
                    {
                        icon: TableIcons.Refresh,
                        tooltip: 'Refresh Data',
                        isFreeAction: true,
                        onClick: () => tableRef.current && tableRef.current.onQueryChange()
                    },
                    {
                        icon: TableIcons.Edit,
                        tooltip: 'Edit Product',
                        onClick: (event, rowData)=>saveOrUpdateCustomer(event, rowData, "update")
                    },
                    {
                        icon: TableIcons.Add,
                        tooltip: 'Add Shipping Method',
                        isFreeAction: true,
                        onClick:  (event, rowData)=>saveOrUpdateCustomer(event, rowData, "save")
                    }
                ]}
            />

        </Container>
    );
};


const mapDispatchToProps = dispatch => {
    return {
        startLoad: bindActionCreators(SystemAction.startLoad, dispatch),
        stopLoad: bindActionCreators(SystemAction.stopLoad, dispatch),
    };
};

export default connect(null, mapDispatchToProps)(Customer);
