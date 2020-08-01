import React, {useEffect, useState} from 'react';
import PropTypes from 'prop-types';
import {Container} from "@material-ui/core";
import EmployerAPI from "../../Service/EmployerAPI";

const Employer = ({employers}) => {
    const [totalPage, setTotalPage] = useState(0);
    const [currentPage, setCurrentPage] = useState(0)
    const [allEmployers, setAllEmployers] = useState([]);

    useEffect(() => {
        uploadAllEmployers();
    }, []);

    const uploadAllEmployers = () => {
        console.log("1")
        if (currentPage <= totalPage) {
            EmployerAPI.getAllEmployer({page: currentPage, limit: 6})
                .then(res => {
                    const {content, totalPages, number} = res;
                    setTotalPage(totalPages - 1);
                    setAllEmployers(content);
                    setCurrentPage(number);
                })
        }
    }

    console.log(employers);
    return (
        <Container>
            Employer
        </Container>
    );
};

Employer.propTypes = {
    employers: PropTypes.array.isRequired
};

export default Employer;