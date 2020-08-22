import {combineReducers} from 'redux';

import System from './System/System';
import {reducer as notifications} from 'react-notification-system-redux';


export default combineReducers({
    System,
    notifications
});
