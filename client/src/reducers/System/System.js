import * as SYSTEM from '../../config/System';

export const initialState = {
  load: false,
  drawer: false
};

export default (state = initialState, action) => {
  switch (action.type) {
    case SYSTEM.START_LOAD:
      return {
        ...state,
        load: true
      };
    case SYSTEM.STOP_LOAD:
      return {
        ...state,
        load: false
      };
    case SYSTEM.OPEN_DRAWER:
      return {
        ...state,
        drawer: true
      };
    case SYSTEM.CLOSE_DRAWER:
      return {
        ...state,
        drawer: false
      };
    default:
      return state;
  }
};
