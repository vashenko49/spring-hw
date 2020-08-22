import SockJS from 'sockjs-client';
import Stomp from 'stompjs';
import Notifications from 'react-notification-system-redux';
import * as SystemConfig from '../config/System'

let websocket = null;

const WebSocketMiddleWare = store => next => action => {
    const {dispatch} = store;
    switch (action.type) {
        // User request to connect
        case 'WEBSOCKET:CONNECT':
            // Configure the object

            const socket = new SockJS('http://127.0.0.1:9000/ws', null, {
                transports: ['xhr-streaming'],
                headers: {'Authorization': localStorage.getItem("Authorization")}
            })

            const headers = {Authorization: localStorage.getItem("Authorization")};

            websocket = Stomp.over(socket);
            websocket.connect(headers, function (frame) {
                console.log('Connected: ' + frame);
                websocket.subscribe('/response/data', function (greeting) {

                    let response = JSON.parse(greeting.body);
                    const notificationOpts = {
                        title: response.type,
                        message: response.type === 'transfer' ?
                            `From ${response.from} to ${response.to} on sum ${response.sum}` :
                            `To ${response.to} on sum ${response.sum}`,
                        position: 'tr',
                        autoDismiss: 0,
                    };

                    dispatch(Notifications.info(notificationOpts))
                });
            });


            // Attach the callbacks
            websocket.onopen = () => dispatch({type: 'WEBSOCKET:OPEN'});
            websocket.onclose = (event) => dispatch({type: 'WEBSOCKET:CLOSE', payload: event});
            websocket.onmessage = (event) => {
                console.log(event);
            }

            break;

        // User request to send a message
        case 'WEBSOCKET:top-up':
            dispatch({
                type: SystemConfig.START_LOAD
            })
            websocket.send("/app/top-up", {}, JSON.stringify(action.message));
            dispatch({
                type: SystemConfig.STOP_LOAD
            })
            break;
        case 'WEBSOCKET:with-draw':
            dispatch({
                type: SystemConfig.START_LOAD
            })
            websocket.send("/app/with-draw", {}, JSON.stringify(action.message));
            dispatch({
                type: SystemConfig.STOP_LOAD
            })
            break;
        case 'WEBSOCKET:transfer':
            dispatch({
                type: SystemConfig.START_LOAD
            })
            websocket.send("/app/transfer", {}, JSON.stringify(action.message));
            dispatch({
                type: SystemConfig.STOP_LOAD
            })
            break;

        // User request to disconnect
        case 'WEBSOCKET:DISCONNECT':
            websocket.close();
            break;

        default:
            break;
    }
    return next(action);
}

export default WebSocketMiddleWare;