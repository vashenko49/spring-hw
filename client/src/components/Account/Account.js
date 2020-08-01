import React from 'react';
import PropTypes from 'prop-types';
import {Container} from "@material-ui/core";

const Account = ({accounts}) => {
    console.log(accounts);
    return (
        <Container>
            Account
        </Container>
    );
};

Account.propTypes = {
    accounts: PropTypes.array.isRequired
};

export default Account;