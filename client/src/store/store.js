import {createStore, applyMiddleware} from 'redux';
import thunk from 'redux-thunk';
import rootReducer from '../reducers/rootReducer';
import {composeWithDevTools} from 'redux-devtools-extension';
import {createLogger} from 'redux-logger';
import CustomerAPI from "../Service/CustomerAPI";
import * as SystemAction from '../actions/System/System'
import axios from "axios";

const logger = createLogger();
export default function configureStore() {
    let store = createStore(rootReducer, {}, composeWithDevTools(applyMiddleware(logger, thunk)));
    let token = localStorage.getItem("Authorization");

    if (token) {
        CustomerAPI.checkingForTokenActivity(token)
            .then(() => {
                axios.defaults.headers.common['Authorization'] = token;
                localStorage.setItem("Authorization", token)
                store.dispatch(SystemAction.autoSignIn())
            })
            .catch(()=>{
                localStorage.removeItem("Authorization");
            })
    }


    return store;
}
