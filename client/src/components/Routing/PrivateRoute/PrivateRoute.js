import React from 'react';
import {Route, Redirect} from 'react-router-dom';
import {connect} from 'react-redux';

const PrivateRoute = ({component: Component, System: {isAuth}, ...rest}) => {
    return (
        <Route
            {...rest}
            render={props => (isAuth ? <Component {...props} /> : <Redirect to="/"/>)}
        />
    );
};

const mapStateToProps = state => {
    return {System: state.System};
};

export default connect(mapStateToProps, null)(PrivateRoute);