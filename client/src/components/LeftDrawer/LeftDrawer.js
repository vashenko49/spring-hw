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
import LocationCityIcon from '@material-ui/icons/LocationCity';
import AccountBalanceWalletIcon from '@material-ui/icons/AccountBalanceWallet';

const useStyles = makeStyles({
    list: {
        width: 250
    },
    fullList: {
        width: 'auto'
    }
});

const LeftDrawer = ({
                        System: { drawer },
                        closeDrawer,
                        openDrawer,
                    }) => {
    const classes = useStyles();
    return (
        <SwipeableDrawer anchor={'left'} open={drawer} onClose={closeDrawer} onOpen={openDrawer}>
            <div className={classes.list} onClick={closeDrawer} onKeyDown={closeDrawer}>
                <List>
                    <StyledLink to={'/customer'}>
                        <ListItem button>
                            <ListItemIcon>
                                <AccessibilityIcon />
                            </ListItemIcon>
                            <ListItemText primary={'Customer'} />
                        </ListItem>
                    </StyledLink>
                    <StyledLink to={'/account'}>
                        <ListItem button>
                            <ListItemIcon>
                                <AccountBalanceWalletIcon />
                            </ListItemIcon>
                            <ListItemText primary={'Account'} />
                        </ListItem>
                    </StyledLink>
                    <StyledLink to={'/employer'}>
                        <ListItem button>
                            <ListItemIcon>
                                <LocationCityIcon />
                            </ListItemIcon>
                            <ListItemText primary={'Employer'} />
                        </ListItem>
                    </StyledLink>
                </List>
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
        openDrawer: bindActionCreators(SystemAction.openDrawer, dispatch)
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(LeftDrawer);
