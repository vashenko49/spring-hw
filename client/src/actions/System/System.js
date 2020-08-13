import * as SYSTEM from '../../config/System';
import axios from 'axios';
import CustomerAPI from "../../Service/CustomerAPI";
import * as NOTISTACK from "../../config/Notistack";

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
    CustomerAPI.signInCustomer(email, password)
        .then(res => {
            const {jwt} = res;
            const bearer = `Bearer ${jwt}`;
            axios.defaults.headers.common['Authorization'] = bearer;
            localStorage.setItem("Authorization", bearer)
            dispatch({
                type: SYSTEM.USER_SIGN_IN
            })
            dispatch({
                type: NOTISTACK.ENQUEUE_SNACKBAR,
                notification: {
                    ...{
                        message: "Success"
                    }
                }
            });
            history.push("/customer")
        })
        .catch(e => {
            const {
                response: {
                    data: {message}
                }
            } = e;
            dispatch({
                type: NOTISTACK.ENQUEUE_SNACKBAR,
                notification: {
                    ...{
                        message: message
                    }
                }
            });
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
        type: SYSTEM.USER_IS_AUTH_ERROR_OR_SIGN_OUT
    })
};

