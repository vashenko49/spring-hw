import React, {Component} from 'react';
import {Redirect} from "react-router";
import {bindActionCreators} from "redux";
import * as SystemAction from "../../actions/System/System";
import {connect} from "react-redux";


class OAuth2Redirect extends Component {
    getUrlParameter(name) {
        // eslint-disable-next-line
        name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
        let regex = new RegExp('[\\?&]' + name + '=([^&#]*)');

        let results = regex.exec(this.props.location.search);
        return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
    };

    render() {
        const token = this.getUrlParameter('token');
        const error = this.getUrlParameter('error');

        if (token) {
            this.props.customerSignInWithOAuth2(token);
            return <Redirect to={{
                pathname: "/customer",
                state: {from: this.props.location}
            }}/>;
        } else {
            return <Redirect to={{
                pathname: "/",
                state: {
                    from: this.props.location,
                    error: error
                }
            }}/>;
        }
    }
}


const mapDispatchToProps = dispatch => {
    return {
        customerSignInWithOAuth2: bindActionCreators(SystemAction.customerSignInWithOAuth2, dispatch),
    };
};

export default connect(null, mapDispatchToProps)(OAuth2Redirect);

