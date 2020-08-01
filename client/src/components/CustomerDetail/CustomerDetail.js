import React, {useEffect, useState} from 'react';
import {CardContent, Container, Typography} from "@material-ui/core";
import CustomerAPI from "../../Service/CustomerAPI";
import Card from "@material-ui/core/Card";
import makeStyles from "@material-ui/core/styles/makeStyles";
import FormControl from '@material-ui/core/FormControl';
import {TextValidator, ValidatorForm} from 'react-material-ui-form-validator';
import InputAdornment from "@material-ui/core/InputAdornment";
import {RemoveRedEye, VisibilityOff} from "@material-ui/icons";
import Button from "@material-ui/core/Button";
import {bindActionCreators} from "redux";
import * as SystemAction from "../../actions/System/System";
import {connect} from "react-redux";
import Grid from "@material-ui/core/Grid";
import Employer from "../Employer/Employer";
import Account from "../Account/Account";

const useStyles = makeStyles({
    root: {
        paddingTop: "25px"
    },
    inputData: {
        margin: '10px 0'
    },
    employerAndAccountBlock: {
        margin: '25px 0 0 0'
    }
})

const CustomerDetail = ({history, startLoad, stopLoad}) => {
    const classes = useStyles();
    const [isNewCustomer, setIsNewCustomer] = useState(true);
    const [customerId, setCustomerId] = useState(null);
    const [customerData, setCustomerData] = useState({
        showPassword: false,
        showRepeatPassword: false,
        password: '',
        repeatPassword: '',
        name: "",
        email: "",
        age: 30,
        phone: 30,
        accounts: [],
        employers: []
    })

    useEffect(() => {
        ValidatorForm.addValidationRule('isPasswordMatch', value => {
            return value === customerData.password;
        });
    });

    // eslint-disable-next-line
    useEffect(() => {
        const params = new URLSearchParams(window.location.search);
        if (params.get('status') === "update") {
            setIsNewCustomer(false);
            setCustomerId(params.get('id'))
            CustomerAPI.getCustomerById(params.get('id'))
                .then(res => {
                    setCustomerData({
                        ...customerData,
                        ...res
                    })
                })
        }
        ValidatorForm.removeValidationRule('isPasswordMatch');
    }, []);

    const handleChange = e => {
        setCustomerData({
            ...customerData,
            [`${e.target.name}`]: e.target.value
        });
    };

    const handleClickShowPassword = name => {
        setCustomerData({
            ...customerData,
            [`${name}`]: !customerData[`${name}`]
        });
    };

    const handleSubmit = e => {
        e.preventDefault();
        startLoad();
        if (isNewCustomer) {
            CustomerAPI.createCustomer({
                name: customerData.name,
                age: customerData.age,
                email: customerData.email,
                phone: customerData.phone,
                password: customerData.password
            })
                .then(res => {
                    setIsNewCustomer(false);
                    setCustomerId(res.id);
                    history.push(`/customer-detail?status=update&id=${res.id}`);
                })
        } else {
            CustomerAPI.editCustomer({
                id: customerId,
                name: customerData.name,
                age: customerData.age,
                email: customerData.email,
                phone: customerData.phone,
                password: customerData.password
            })
                .then(res => {
                    setCustomerData({
                        ...customerData,
                        ...res
                    })
                })
        }
        stopLoad();
    }

    return (
        <Container className={classes.root}>
            <Card>
                <CardContent>
                    <Typography variant={"h3"}>{isNewCustomer ? "Create new customer" : "Edit customer"}</Typography>
                    <ValidatorForm onSubmit={handleSubmit} onError={e => console.log(e)}>
                        <FormControl fullWidth variant="outlined">
                            <TextValidator
                                className={classes.inputData}
                                label={'Name'}
                                onChange={handleChange}
                                name="name"
                                value={customerData.name}
                                fullWidth
                                variant="outlined"
                                validators={['required', "minStringLength:2"]}
                                errorMessages={['This field is required', "Name must be more than 2 letter"]}
                            />
                            <TextValidator
                                className={classes.inputData}
                                label={'Email'}
                                onChange={handleChange}
                                name="email"
                                value={customerData.email}
                                fullWidth
                                variant="outlined"
                                validators={['required', 'isEmail']}
                                errorMessages={['This field is required', 'Email is not valid']}
                            />
                            <TextValidator
                                className={classes.inputData}
                                label={'Age'}
                                onChange={handleChange}
                                name="age"
                                value={customerData.age}
                                type={"number"}
                                fullWidth
                                variant="outlined"
                                validators={['required', "minNumber:18", "maxNumber:150"]}
                                errorMessages={['This field is required', "This field must be more than 18","This field must be less than 150"]}
                            />
                            <TextValidator
                                className={classes.inputData}
                                label={'Phone'}
                                onChange={handleChange}
                                name="phone"
                                value={customerData.phone}
                                fullWidth
                                variant="outlined"
                                validators={['required', 'matchRegexp:^(\\+)?(\\(\\d{2,3}\\) ?\\d|\\d)(([ \\-]?\\d)|( ?\\(\\d{2,3}\\) ?)){5,12}\\d$']}
                                errorMessages={['This field is required', 'Phone is not valid']}
                            />
                            <TextValidator
                                className={classes.inputData}
                                label="Password"
                                onChange={handleChange}
                                name="password"
                                type={customerData.showPassword ? 'text' : 'password'}
                                fullWidth
                                variant="outlined"
                                validators={['required', 'matchRegexp:^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$']}
                                errorMessages={['This field is required', 'Password must be at least 8 characters and contain at least 1 uppercase and 1 lowercase letter']}
                                value={customerData.password}
                                InputProps={{
                                    endAdornment: (
                                        <InputAdornment
                                            position="end"
                                            onClick={() => {
                                                handleClickShowPassword('showPassword');
                                            }}
                                        >
                                            {customerData.showPassword ? <RemoveRedEye/> : <VisibilityOff/>}
                                        </InputAdornment>
                                    )
                                }}
                            />
                            <TextValidator
                                className={classes.inputData}
                                label="Repeat password"
                                onChange={handleChange}
                                name="repeatPassword"
                                type={customerData.showRepeatPassword ? 'text' : 'password'}
                                fullWidth
                                variant="outlined"
                                validators={['isPasswordMatch', 'required']}
                                errorMessages={['Password mismatch', 'This field is required']}
                                value={customerData.repeatPassword}
                                InputProps={{
                                    endAdornment: (
                                        <InputAdornment
                                            position="end"
                                            onClick={() => {
                                                handleClickShowPassword('showRepeatPassword');
                                            }}
                                        >
                                            {customerData.showRepeatPassword ? <RemoveRedEye/> : <VisibilityOff/>}
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
                                Confirm
                            </Button>
                        </FormControl>
                    </ValidatorForm>
                </CardContent>
            </Card>

            <Grid
                className={classes.employerAndAccountBlock}
                container
                spacing={3}
            >
                <Grid item lg={6} md={6} sm={12} xs={12}>
                    <Card>
                        <CardContent>
                            <Employer employers={customerData.employers}/>
                        </CardContent>
                    </Card>
                </Grid>
                <Grid item lg={6} md={6} sm={12} xs={12}>
                    <Card>
                        <CardContent>
                            <Account accounts={customerData.accounts}/>
                        </CardContent>
                    </Card>
                </Grid>
            </Grid>
        </Container>
    );
};

const mapDispatchToProps = dispatch => {
    return {
        startLoad: bindActionCreators(SystemAction.startLoad, dispatch),
        stopLoad: bindActionCreators(SystemAction.stopLoad, dispatch),
    };
};

export default connect(null, mapDispatchToProps)(CustomerDetail);
