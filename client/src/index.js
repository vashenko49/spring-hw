import React from 'react';
import ReactDOM from 'react-dom';
import {createMuiTheme, ThemeProvider} from '@material-ui/core/styles';
import {blueGrey, yellow} from '@material-ui/core/colors';
import {Provider} from 'react-redux';
import {BrowserRouter} from 'react-router-dom';
import configureStore from './store/store';
import Routing from './components/Routing/Routing';
import {SnackbarProvider} from 'notistack';
import Loader from './components/Loader/Loader';
import Header from './components/Header/Header';
import LeftDrawer from './components/LeftDrawer/LeftDrawer';
import './index.css';
import NotificationsCustom from "./components/Notifications/NotificationsCustom";

const outerTheme = createMuiTheme({
    palette: {
        secondary: yellow,
        primary: blueGrey
    }
});


ReactDOM.render(
    <ThemeProvider theme={outerTheme}>
        <Provider store={configureStore()}>
            <SnackbarProvider>
                <BrowserRouter>
                    <Header/>
                    <NotificationsCustom/>
                    <Routing/>
                    <Loader/>
                    <LeftDrawer/>
                </BrowserRouter>
            </SnackbarProvider>
        </Provider>
    </ThemeProvider>,
    document.getElementById('root')
);

