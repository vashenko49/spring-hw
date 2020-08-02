import React from 'react';
import PropTypes from 'prop-types';
import MaterialTable from "material-table";
import TableIcons from "../TableIcons/TableIcons";
import {FormControl} from "@material-ui/core";
import Select from "@material-ui/core/Select";
import MenuItem from "@material-ui/core/MenuItem";
import AccountAPI from "../../Service/AccountAPI";
import _ from 'lodash';

const Account = ({accounts, setAccounts, customerId, isNewCustomer}) => {
    const deleteAccounts = oldData => new Promise((resolve, reject) => {
        AccountAPI.deleteAccount(oldData.id)
            .then(() => {
                _.remove(accounts, a => a.id === oldData.id);
                setAccounts(accounts);
                resolve();
            })
            .catch(() => {
                reject();
            })
    });
    const createAccounts = newData => new Promise((resolve, reject) => {
        AccountAPI.createAccount({
            ...newData,
            customer: customerId
        })
            .then(e => {
                accounts.push(e);
                setAccounts(accounts);
                resolve();
            })
            .catch(() => {
                reject();
            })
    });
    const updateAccounts = newData => new Promise((resolve, reject) => {
        AccountAPI.updateAccount(newData)
            .then(e => {
                _.remove(accounts, a => a.id === e.id);
                accounts.push(e);
                setAccounts(accounts);
                resolve();
            })
            .catch(() => {
                reject();
            })
    });
    return (
        <MaterialTable
            title={"Accounts"}
            icons={TableIcons}
            columns={[
                {title: "Number", field: "number", editable: 'never'},
                {
                    title: "Currency",
                    field: "currency",
                    editComponent: props => {
                        return (<FormControl>
                            <Select
                                value={props.value}
                                defaultValue={"USD"}
                                onChange={e => props.onChange(e.target.value)}
                            >
                                <MenuItem value={"USD"}>USD</MenuItem>
                                <MenuItem value={"EUR"}>EUR</MenuItem>
                                <MenuItem value={"UAH"}>UAH</MenuItem>
                                <MenuItem value={"CHF"}>CHF</MenuItem>
                                <MenuItem value={"GBP"}>GBP</MenuItem>
                            </Select>
                        </FormControl>)
                    }
                },
                {title: "Balance", field: "balance", type: 'numeric'},
            ]}
            data={accounts}
            options={{
                search: false
            }}
            isFreeAction={true}
            editable={{
                onRowAdd: isNewCustomer ? () => new Promise((resolve, reject) => reject()) : createAccounts,
                onRowDelete: isNewCustomer ? () => new Promise((resolve, reject) => reject()) : deleteAccounts,
                onRowUpdate: isNewCustomer ? () => new Promise((resolve, reject) => reject()) : updateAccounts
            }}
        />
    );
};

Account.propTypes = {
    accounts: PropTypes.array.isRequired,
    setAccounts: PropTypes.func.isRequired,
    customerId: PropTypes.string.isRequired,
    isNewCustomer: PropTypes.bool.isRequired,
};

export default Account;