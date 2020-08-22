import * as SYSTEM from '../../config/System';
import axios from 'axios';
import CustomerAPI from "../../Service/CustomerAPI";
import Notifications from 'react-notification-system-redux';

export const startLoad = () => ({
    type: SYSTEM.START_LOAD
});

export const stopLoad = () => ({
    type: SYSTEM.STOP_LOAD
});

export const openDrawer = () => ({
    type: SYSTEM.OPEN_DRAWER
});

export const closeDrawer = () => ({
    type: SYSTEM.CLOSE_DRAWER
});

export const customerSignIn = (email, password, history) => dispatch => {
    dispatch({
        type: SYSTEM.START_LOAD
    });
    dispatch({
        type: "WEBSOCKET:CONNECT"
    })
    CustomerAPI.signInCustomer(email, password)
        .then(res => {
            const {accessToken} = res;
            const bearer = `Bearer ${accessToken}`;
            axios.defaults.headers.common['Authorization'] = bearer;
            localStorage.setItem("Authorization", bearer)
            dispatch({
                type: SYSTEM.USER_SIGN_IN
            })

            const notificationOpts = {
                title: 'Sign In',
                message: "Success",
                position: 'tr',
                autoDismiss: 0,
            };

            dispatch(Notifications.info(notificationOpts))

            history.push("/customer")
        })
        .catch(e => {
            const {
                response: {
                    data: {message}
                }
            } = e;

            const notificationOpts = {
                title: 'Sign In',
                message: message,
                position: 'tr',
                autoDismiss: 0,
            };

            dispatch(Notifications.error(notificationOpts))
        })
        .finally(() => {
            dispatch({
                type: SYSTEM.STOP_LOAD
            });
        })

};

export const customerErrorOrSignOut = () => dispatch => {
    delete axios.defaults.headers.common['Authorization'];
    localStorage.removeItem("Authorization")
    dispatch({
        type: "WEBSOCKET:DISCONNECT"
    })
    dispatch({
        type: SYSTEM.USER_IS_AUTH_ERROR_OR_SIGN_OUT
    })
};


export const customerSignInWithOAuth2 = token => dispatch => {
    const bearer = `Bearer ${token}`;
    axios.defaults.headers.common['Authorization'] = bearer;
    localStorage.setItem("Authorization", bearer)

    dispatch({
        type: "WEBSOCKET:CONNECT"
    })

    dispatch({
        type: SYSTEM.USER_SIGN_IN
    })
}


export const autoSignIn = () => dispatch => {
    dispatch({
        type: "WEBSOCKET:CONNECT"
    })
    dispatch({
        type: SYSTEM.USER_SIGN_IN
    })
};

export const topUp = message => {
    return {
        type: "WEBSOCKET:top-up",
        message: message
    };
}
export const withDraw = message => {
    return {
        type: "WEBSOCKET:with-draw",
        message: message
    };
}
export const transfer = message => {
    return {
        type: "WEBSOCKET:transfer",
        message: message
    };
}

export const notificationSuccess = (title, message) => dispatch => {
    const notificationOpts = {
        title,
        message,
        position: 'tr',
        autoDismiss: 0,
    };

    dispatch(Notifications.info(notificationOpts))
};

export const notificationError = (title, message) => dispatch => {
    const notificationOpts = {
        title,
        message,
        position: 'tr',
        autoDismiss: 0,
    };

    dispatch(Notifications.error(notificationOpts))
};

