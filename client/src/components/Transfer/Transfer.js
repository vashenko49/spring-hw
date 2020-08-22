import React, {useState} from 'react';
import {Container, Typography} from "@material-ui/core";
import {TextValidator, ValidatorForm} from "react-material-ui-form-validator";
import FormControl from "@material-ui/core/FormControl";
import makeStyles from "@material-ui/core/styles/makeStyles";
import Button from "@material-ui/core/Button";
import {bindActionCreators} from "redux";
import * as SystemAction from "../../actions/System/System";

import {connect} from "react-redux";

const useStyles = makeStyles({
    root: {
        paddingTop: "25px"
    },
    inputData: {
        margin: '10px 0'
    },
})


const Transfer = ({ topUp, withDraw, transfer}) => {
    const classes = useStyles();
    const [topUpData, setTopUpData] = useState({
        toNum: "",
        sum: 0
    })
    const handleChangeTopUpData = e => {
        setTopUpData({
            ...topUpData,
            [`${e.target.name}`]: e.target.value
        });
    }
    const handleSubmitTopUp = e => {
        e.preventDefault();
        topUp(topUpData);
    }

    const [withdrawData, setWithdrawData] = useState({
        toNum: "",
        sum: 0
    })
    const handleChangeWithdrawData = e => {
        setWithdrawData({
            ...withdrawData,
            [`${e.target.name}`]: e.target.value
        });
    }
    const handleSubmitWithdraw = e => {
        e.preventDefault();
        withDraw(withdrawData)
    }

    const [transferData, setTransferData] = useState({
        fromNum: "",
        toNum: "",
        sum: 0
    })
    const handleChangeTransferData = e => {
        setTransferData({
            ...transferData,
            [`${e.target.name}`]: e.target.value
        });
    }
    const handleSubmitTransfer = e => {
        e.preventDefault();
        transfer(transferData);
    }

    return (
        <Container>
            <Typography variant={"h4"}>Top Up</Typography>
            <ValidatorForm
                onSubmit={handleSubmitTopUp}
                onError={e => console.log(e)}
            >
                <FormControl fullWidth variant="outlined">
                    <TextValidator
                        className={classes.inputData}
                        label={'Number account'}
                        onChange={handleChangeTopUpData}
                        name="toNum"
                        value={topUpData.toNum}
                        fullWidth
                        variant="outlined"
                        validators={['required']}
                        errorMessages={['This field is required']}
                    />
                    <TextValidator
                        className={classes.inputData}
                        label={'Summa'}
                        onChange={handleChangeTopUpData}
                        name="sum"
                        type={"number"}
                        value={topUpData.sum}
                        fullWidth
                        variant="outlined"
                        validators={['required', "minNumber:1"]}
                        errorMessages={['This field is required', "This field must be more than 1"]}
                    />
                </FormControl>
                <Button
                    className={classes.inputData}
                    type={'submit'}
                    fullWidth={true}
                    variant="contained"
                    color="primary"
                >
                    Confirm
                </Button>
            </ValidatorForm>
            <Typography variant={"h4"}>With Draw</Typography>
            <ValidatorForm
                onSubmit={handleSubmitWithdraw}
                onError={e => console.log(e)}
            >
                <FormControl fullWidth variant="outlined">
                    <TextValidator
                        className={classes.inputData}
                        label={'Number account'}
                        onChange={handleChangeWithdrawData}
                        name="toNum"
                        value={withdrawData.toNum}
                        fullWidth
                        variant="outlined"
                        validators={['required']}
                        errorMessages={['This field is required']}
                    />
                    <TextValidator
                        className={classes.inputData}
                        label={'Summa'}
                        onChange={handleChangeWithdrawData}
                        name="sum"
                        type={"number"}
                        value={withdrawData.sum}
                        fullWidth
                        variant="outlined"
                        validators={['required', "minNumber:1"]}
                        errorMessages={['This field is required', "This field must be more than 1"]}
                    />
                </FormControl>
                <Button
                    className={classes.inputData}
                    type={'submit'}
                    fullWidth={true}
                    variant="contained"
                    color="primary"
                >
                    Confirm
                </Button>
            </ValidatorForm>
            <Typography variant={"h4"}>Transfer</Typography>
            <ValidatorForm
                onSubmit={handleSubmitTransfer}
                onError={e => console.log(e)}
            >
                <FormControl fullWidth variant="outlined">
                    <TextValidator
                        className={classes.inputData}
                        label={'From Number account'}
                        onChange={handleChangeTransferData}
                        name="fromNum"
                        value={transferData.fromNum}
                        fullWidth
                        variant="outlined"
                        validators={['required']}
                        errorMessages={['This field is required']}
                    />
                    <TextValidator
                        className={classes.inputData}
                        label={'To Number account'}
                        onChange={handleChangeTransferData}
                        name="toNum"
                        value={transferData.toNum}
                        fullWidth
                        variant="outlined"
                        validators={['required']}
                        errorMessages={['This field is required']}
                    />
                    <TextValidator
                        className={classes.inputData}
                        label={'Summa'}
                        onChange={handleChangeTransferData}
                        name="sum"
                        type={"number"}
                        value={transferData.sum}
                        fullWidth
                        variant="outlined"
                        validators={['required', "minNumber:1"]}
                        errorMessages={['This field is required', "This field must be more than 1"]}
                    />
                </FormControl>
                <Button
                    className={classes.inputData}
                    type={'submit'}
                    fullWidth={true}
                    variant="contained"
                    color="primary"
                >
                    Confirm
                </Button>
            </ValidatorForm>
        </Container>
    );
};

const mapDispatchToProps = dispatch => {
    return {
        topUp: bindActionCreators(SystemAction.topUp, dispatch),
        withDraw: bindActionCreators(SystemAction.withDraw, dispatch),
        transfer: bindActionCreators(SystemAction.transfer, dispatch)
    };
};

export default connect(null, mapDispatchToProps)(Transfer);
