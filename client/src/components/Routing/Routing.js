import React from 'react';
import {Route, Switch} from 'react-router-dom';
import NotFond from '../NotFond/NotFond';
import Customer from "../Customer/Customer";
import Account from "../Account/Account";
import Employer from "../Employer/Employer";
import CustomerDetail from "../CustomerDetail/CustomerDetail";

const Routing = () => {
    return (
        <Switch>
            <Route exact path="/" component={Customer} />
            <Route exact path="/customer" component={Customer} />
            <Route exact path="/account" component={Account} />
            <Route exact path="/employer" component={Employer} />
            <Route exact path="/customer-detail" component={CustomerDetail} />
            <Route component={NotFond} />
        </Switch>
    );
};

export default Routing;