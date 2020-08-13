import React from 'react';
import {bindActionCreators} from 'redux';
import * as SystemAction from '../../actions/System/System';
import {connect} from 'react-redux';
import makeStyles from '@material-ui/core/styles/makeStyles';
import SwipeableDrawer from '@material-ui/core/SwipeableDrawer';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import StyledLink from '../StyledLink/StyledLink';
import AccessibilityIcon from '@material-ui/icons/Accessibility';
import AccountBalanceWalletIcon from '@material-ui/icons/AccountBalanceWallet';
import ExitToAppIcon from '@material-ui/icons/ExitToApp';

const useStyles = makeStyles({
    list: {
        width: 250
    },
    fullList: {
        width: 'auto'
    }
});

const LeftDrawer = ({
                        System: {
                            drawer, isAuth
                        },
                        closeDrawer,
                        openDrawer,
                        customerErrorOrSignOut
                    }) => {
    const classes = useStyles();
    return (
        <SwipeableDrawer anchor={'left'} open={drawer} onClose={closeDrawer} onOpen={openDrawer}>
            <div className={classes.list} onClick={closeDrawer} onKeyDown={closeDrawer}>
                {
                    !isAuth?(<List>
                        <StyledLink to={'/'}>
                            <ListItem button>
                                <ListItemIcon>
                                    <ExitToAppIcon/>
                                </ListItemIcon>
                                <ListItemText primary={'Sign In'}/>
                            </ListItem>
                        </StyledLink>
                    </List>):( <List>
                        <StyledLink to={'/customer'}>
                            <ListItem button>
                                <ListItemIcon>
                                    <AccessibilityIcon/>
                                </ListItemIcon>
                                <ListItemText primary={'Customer'}/>
                            </ListItem>
                        </StyledLink>
                        <StyledLink to={'/transfer'}>
                            <ListItem button>
                                <ListItemIcon>
                                    <AccountBalanceWalletIcon/>
                                </ListItemIcon>
                                <ListItemText primary={'Transfer'}/>
                            </ListItem>
                        </StyledLink>
                        <ListItem button onClick={customerErrorOrSignOut}>
                            <ListItemIcon>
                                <ExitToAppIcon/>
                            </ListItemIcon>
                            <ListItemText primary={"Sign Out"}/>
                        </ListItem>
                    </List>)
                }

            </div>
        </SwipeableDrawer>
    );
};

const mapStateToProps = state => {
    return {
        System: state.System
    };
};

const mapDispatchToProps = dispatch => {
    return {
        closeDrawer: bindActionCreators(SystemAction.closeDrawer, dispatch),
        openDrawer: bindActionCreators(SystemAction.openDrawer, dispatch),
        customerErrorOrSignOut: bindActionCreators(SystemAction.customerErrorOrSignOut, dispatch),
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(LeftDrawer);
