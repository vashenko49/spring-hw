import React from 'react';
import {Route, Switch} from 'react-router-dom';
import NotFond from '../NotFond/NotFond';
import Customer from "../Customer/Customer";
import CustomerDetail from "../CustomerDetail/CustomerDetail";

const Routing = () => {
    return (
        <Switch>
            <Route exact path="/" component={Customer} />
            <Route exact path="/customer" component={Customer} />
            <Route exact path="/customer-detail" component={CustomerDetail} />
            <Route component={NotFond} />
        </Switch>
    );
};

export default Routing;