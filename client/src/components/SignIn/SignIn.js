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


const SignIn = ({customerSignIn, history}) => {
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
                        <div >
                            <Button href={GOOGLE_AUTH_URL}>
                                <img src={googleLogo} alt="Google" /> Log in with Google</Button>
                            <Button href={FACEBOOK_AUTH_URL}>
                                <img src={fbLogo} alt="Facebook" /> Log in with Facebook</Button>
                            <Button  href={GITHUB_AUTH_URL}>
                                <img src={githubLogo} alt="Github" /> Log in with Github</Button>
                        </div>
                    </ValidatorForm>
                </CardContent>
            </Card>
        </Container>
    );
};

const mapDispatchToProps = dispatch => {
    return {
        customerSignIn: bindActionCreators(SystemAction.customerSignIn, dispatch),
    };
};

export default connect(null, mapDispatchToProps)(SignIn);
