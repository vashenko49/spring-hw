import { combineReducers } from 'redux';

import Notistack from './Notistack/Notistack';
import System from './System/System';

export default combineReducers({
  Notistack,
  System
});
