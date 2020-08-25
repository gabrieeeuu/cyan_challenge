import React, { Component } from 'react';
import  {FormText, Alert, Button, Form, FormGroup, Label, Input } from 'reactstrap';
import Navbar from '../../assets/AppNavbar.js'
import api from "../../services/Api";
import {Link} from "react-router-dom";


class FieldForm extends Component {

    state = {
        code: "",
        latitude: "",
        longitude: "",
        error: "",
        latIsOk: false,
        lonIsOk: false
    };

    handleSubmit = async e => {
        e.preventDefault();
        const { code, latitude, longitude } = this.state;
        if (!code || !latitude || !longitude) {
            this.setState({ error: "Fill all the spaces correctly to proceed" });
        } else {
            try {
                await api.post("/api/field", { code, latitude, longitude });
                this.props.history.push("/fields");
            } catch (err) {
                const error = err.response.data.message;
                this.setState({
                    error:
                        error
                });
            }
        }
    };

    onLatitudeChange = e => {
        const latitude = e.target.value;

        if (latitude &&
            latitude.match(/^[+-]?\d{1,}\.\d{4}?$/) &&
            parseFloat(latitude) <= 100 &&
            parseFloat(latitude) >= -100) {
            this.setState(() => ({latitude: latitude, latIsOk: true}));
        } else {
            this.setState({latIsOk: false})
        }
    };

    onLongitudeChange = e => {
        const longitude = e.target.value;

        if (longitude &&
            longitude.match(/^[+-]?\d{1,}\.\d{4}?$/) &&
            parseFloat(longitude) <= 100 &&
            parseFloat(longitude) >= -100) {
            this.setState(() => ({longitude: longitude, lonIsOk: true}));
        } else {
            this.setState({lonIsOk: false})
        }
    };

    render(){
        return (
            <div>
                <Navbar />
                <div className="container">
                    <h3>Register a new Field</h3>
                    <Form onSubmit={this.handleSubmit}>

                        {this.state.error && <Alert color="warning">{this.state.error}</Alert>}

                        <FormGroup>
                            <Label for="code">Field Code:</Label>
                            <Input
                                type="text"
                                name="code"
                                id="fieldFormCode"
                                placeholder="Ex: f13ld4pt4 (white spaces will be removed)"
                                style={{textTransform: 'lowercase'}}
                                onChange={e => this.setState({ code: e.target.value.replace(/\s/g,'') })}
                            />
                        </FormGroup>
                        <FormGroup>
                            <Label for="latitude">Latitude:</Label>
                            <Input invalid={!this.state.latIsOk}
                                   valid={this.state.latIsOk}
                                   type="text"
                                   name="latitude"
                                   id="fieldFormLatitude"
                                   placeholder="ex: -20.4523"
                                   onChange={this.onLatitudeChange}
                            />
                            <FormText>Latitude must be as XX.XXXX or as -XX.XXXX</FormText>
                        </FormGroup>
                        <FormGroup>
                            <Label for="longitude">Longitude:</Label>
                            <Input invalid={!this.state.lonIsOk}
                                   valid={this.state.lonIsOk}
                                   type="text"
                                   name="longitude"
                                   id="fieldFormLongitude"
                                   placeholder="ex: 78.2342"
                                   onChange={this.onLongitudeChange}
                            />
                            <FormText>Longitude must be as XX.XXXX or as -XX.XXXX</FormText>
                        </FormGroup>
                        <Button outline color="success" type="submit">Submit</Button>
                        <Button outline color="warning" tag={Link} to="/fields" style={{marginLeft: '0.3rem'}}>Back to Fields</Button>
                    </Form>
                </div>
            </div>
        );
    }
}

export default FieldForm;