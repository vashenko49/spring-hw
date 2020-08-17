import React, {useState} from 'react';
import {Container} from "@material-ui/core";
import makeStyles from "@material-ui/core/styles/makeStyles";
import Typography from "@material-ui/core/Typography";
import {ValidatorForm, TextValidator} from 'react-material-ui-form-validator';
import Button from "@material-ui/core/Button";
import {RemoveRedEye, VisibilityOff} from "@material-ui/icons";
import InputAdornment from "@material-ui/core/InputAdornment";
import Card from "@material-ui/core/Card";
import CardContent from "@material-ui/core/CardContent";
import {bindActionCreators} from "redux";
import * as SystemAction from "../../actions/System/System";
import {connect} from "react-redux";
import FacebookIcon from '@material-ui/icons/Facebook';
import GitHubIcon from '@material-ui/icons/GitHub';
import {FACEBOOK_AUTH_URL, GITHUB_AUTH_URL, GOOGLE_AUTH_URL} from "../../config/OAuth2URL";
import {Redirect} from "react-router";
import GTranslateIcon from '@material-ui/icons/GTranslate';

const useStyles = makeStyles({
    root: {
        margin: '12px auto',
        maxWidth: '478px',
        padding: '24px'
    },
    inputData: {
        margin: '10px 0'
    },
    clickForgot: {
        color: 'blue'
    },
    iconEyes: {
        cursor: 'pointer'
    }
});


const SignIn = ({customerSignIn, history, System: {isAuth}}) => {
    const classes = useStyles();
    const [userData, setUserData] = useState({
        email: "",
        password: ''
    })
    const [showPassword, setShowPassword] = useState(false);

    const handleSubmit = e => {
        e.preventDefault();
        customerSignIn(userData.email, userData.password, history);
    }

    return (
        <>
            {
                isAuth ?
                    (
                        <Redirect to={{
                            pathname: "/customer"
                        }}/>
                    ) : (
                        <Container>
                            <Card className={classes.root}>
                                <CardContent>
                                    <Typography variant={'h5'} align={'center'}>
                                        Sign In your account
                                    </Typography>
                                    <ValidatorForm onSubmit={handleSubmit} onError={e => console.log(e)}>
                                        <TextValidator
                                            className={classes.inputData}
                                            label={'Email'}
                                            onChange={e => setUserData({...userData, email: e.target.value})}
                                            name="email"
                                            value={userData.email}
                                            fullWidth
                                            variant="outlined"
                                            validators={['required', 'isEmail']}
                                            errorMessages={['This field is required', 'Email is not valid']}
                                        />
                                        <TextValidator
                                            className={classes.inputData}
                                            label={'Password'}
                                            onChange={e => setUserData({...userData, password: e.target.value})}
                                            name="password"
                                            value={userData.password}
                                            fullWidth
                                            variant="outlined"
                                            validators={['required', 'matchRegexp:^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$']}
                                            errorMessages={['This field is required', 'Password must be at least 8 characters and contain at least 1 uppercase and 1 lowercase letter']}
                                            type={showPassword ? 'text' : 'password'}
                                            InputProps={{
                                                endAdornment: (
                                                    <InputAdornment
                                                        position="end"
                                                        onClick={() => {
                                                            setShowPassword(!showPassword);
                                                        }}
                                                        className={classes.iconEyes}
                                                    >
                                                        {showPassword ? <RemoveRedEye/> : <VisibilityOff/>}
                                                    </InputAdornment>
                                                )
                                            }}
                                        />
                                        <Button
                                            className={classes.inputData}
                                            type={'submit'}
                                            fullWidth={true}
                                            variant="contained"
                                            color="primary"
                                        >
                                            Log In
                                        </Button>

                                    </ValidatorForm>
                                    <div>
                                        <Typography align={"center"} variant={"h4"}>Or use social</Typography>
                                        <Button
                                            fullWidth
                                            href={FACEBOOK_AUTH_URL}
                                            variant="contained"
                                            color="primary"
                                            className={classes.inputData}
                                            endIcon={<FacebookIcon>Facebook</FacebookIcon>}
                                        >
                                            Facebook
                                        </Button>
                                        <Button
                                            fullWidth
                                            href={GITHUB_AUTH_URL}
                                            variant="contained"
                                            color="primary"
                                            className={classes.inputData}
                                            endIcon={<GitHubIcon>GitHub</GitHubIcon>}
                                        >
                                            GitHub
                                        </Button>
                                        <Button
                                            fullWidth
                                            href={GOOGLE_AUTH_URL}
                                            variant="contained"
                                            color="primary"
                                            className={classes.inputData}
                                            endIcon={<GTranslateIcon>Google</GTranslateIcon>}
                                        >
                                            Google
                                        </Button>
                                    </div>
                                </CardContent>
                            </Card>
                        </Container>)
            }
        </>
    );
};

const mapStateToProps = state => {
    return {
        System: state.System
    };
};

const mapDispatchToProps = dispatch => {
    return {
        customerSignIn: bindActionCreators(SystemAction.customerSignIn, dispatch),
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(SignIn);
