import React from 'react';
import {Route, Switch} from 'react-router-dom';
import NotFond from '../NotFond/NotFond';
import Customer from "../Customer/Customer";
import CustomerDetail from "../CustomerDetail/CustomerDetail";
import Transfer from "../Transfer/Transfer";
import SignIn from "../SignIn/SignIn";
import PrivateRoute from "./PrivateRoute/PrivateRoute";
import OAuth2Redirect from "../OAuth2Redirect/OAuth2Redirect";

const Routing = () => {
    return (
        <Switch>
            <Route exact path="/" component={SignIn}/>
            <PrivateRoute exact path="/customer" component={Customer}/>
            <PrivateRoute exact path="/customer-detail" component={CustomerDetail}/>
            <PrivateRoute exact path="/transfer" component={Transfer}/>
            <Route exact path={"/oauth2/redirect"} component={OAuth2Redirect}/>
            <Route component={NotFond}/>
        </Switch>
    );
};

export default Routing;