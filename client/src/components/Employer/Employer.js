import React, {useEffect, useRef, useState} from 'react';
import PropTypes from 'prop-types';
import MaterialTable from "material-table";
import EmployerAPI from "../../Service/EmployerAPI";
import TableIcons from "../TableIcons/TableIcons";
import _ from 'lodash';


const Employer = ({employers, setEmployer}) => {
    const tableRef = useRef();
    const [currentData, setCurrentData] = useState([])
    const loadEmployers = query => new Promise((resolve, reject) => {
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


    const handleSelect = rows => {
        setEmployer(rows.concat(employers.filter(e => _.isUndefined(_.find(currentData, o => o.id === e.id)))));
    }


    useEffect(() => {
        tableRef.current.onQueryChange();
    }, [employers])

    const loadData = (page, limit) => EmployerAPI.getAllEmployer({page, limit})
        .then(res => {
            let {content: data, totalElements: totalCount, number: page} = res;
            data = data.map(e => {
                const {id} = e;

                if (!_.isUndefined(_.find(employers, o => o.id === id))) {
                    e.tableData = {
                        checked: true
                    };
                } else {
                    e.tableData = {
                        checked: false
                    };
                }

                return e;
            })

            setCurrentData(data);
            return {data, totalCount, page};
        })


    const addEmployers = newData => new Promise((resolve, reject) => {
        EmployerAPI.createEmployer(newData)
            .then(() => {
                resolve();
            })
            .catch(() => {
                reject();
            })
    })
    const deleteEmployers = oldData => new Promise((resolve, reject) => {
        EmployerAPI.deleteEmployer(oldData.id)
            .then(() => {
                resolve();
            })
            .catch(() => {
                reject();
            })
    })
    const updateEmployers = newData => new Promise((resolve, reject) => {
        EmployerAPI.updateEmployer(newData)
            .then(() => {
                resolve();
            })
            .catch(() => {
                reject();
            })
    })


    return (
        <MaterialTable
            tableRef={tableRef}
            title="Employers"
            icons={TableIcons}
            columns={[
                {title: "Name", field: "name"},
                {title: "Address", field: "address"},
            ]}
            data={loadEmployers}
            options={{
                search: false,
                selection: true,
            }}
            onSelectionChange={handleSelect}
            editable={{
                onRowAdd: addEmployers,
                onRowDelete: deleteEmployers,
                onRowUpdate: updateEmployers
            }}
        />
    );
};

Employer.propTypes = {
    employers: PropTypes.array.isRequired,
    setEmployer: PropTypes.func.isRequired
};

export default Employer;