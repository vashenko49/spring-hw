import * as SYSTEM from '../../config/System';

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
