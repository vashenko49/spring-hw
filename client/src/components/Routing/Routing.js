import React from 'react';
import { Route, Switch } from 'react-router-dom';
import NotFond from '../NotFond/NotFond';
import HomePage from "../HomePage/HomePage";

const Routing = () => {
    return (
        <Switch>
            <Route exact path="/" component={HomePage} />
            <Route component={NotFond} />
        </Switch>
    );
};

export default Routing;