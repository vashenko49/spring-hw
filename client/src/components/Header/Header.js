import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import IconButton from '@material-ui/core/IconButton';
import Typography from '@material-ui/core/Typography';
import MenuIcon from '@material-ui/icons/Menu';
import { connect } from 'react-redux';
import StyledLink from '../StyledLink/StyledLink';
import { bindActionCreators } from 'redux';
import * as SystemAction from '../../actions/System/System';

const useStyles = makeStyles(theme => ({
    grow: {
        flexGrow: 1
    },
    menuButton: {
        marginRight: theme.spacing(2)
    }
}));

const Header = ( {openDrawer }) => {
    const classes = useStyles();

    return (
        <div className={classes.grow}>
            <AppBar position="static">
                <Toolbar>
                    <IconButton
                        edge="start"
                        className={classes.menuButton}
                        color="inherit"
                        aria-label="open drawer"
                        onClick={openDrawer}
                    >
                        <MenuIcon />
                    </IconButton>
                    <Typography variant="h6" noWrap>
                        <StyledLink to={'/'}>Bank</StyledLink>
                    </Typography>
                </Toolbar>
            </AppBar>
        </div>
    );
};

const mapDispatchToProps = dispatch => {
    return {
        openDrawer: bindActionCreators(SystemAction.openDrawer, dispatch)
    };
};

export default connect(null, mapDispatchToProps)(Header);
