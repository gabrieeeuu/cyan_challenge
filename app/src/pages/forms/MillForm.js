import React, { Component } from 'react';
import {Button, Form, FormGroup, Label, Input, Alert} from 'reactstrap';
import Navbar from '../../assets/AppNavbar.js'
import api from "../../services/Api";
import {Link} from "react-router-dom";


class MillForm extends Component {

    state = {
        name: "",
        harvs: [],
        error: ""
    };

    handleSubmit = async e => {
        e.preventDefault();
        const name = this.state.name;
        const harvs = this.state.harvs;
        if (!name) {
            this.setState({ error: "Fill all the spaces correctly to proceed" });
        } else {
            try {
                await api.post("/api/mill", { name, harvs });
                this.props.history.push("/mills");
            } catch (err) {
                this.setState({
                    error:
                        "Something went wrong when trying to register this Mill :("
                });
            }
        }
    };

    render(){
        return (
            <div>
                <Navbar />
                <div className="container">    
                    <Form onSubmit={this.handleSubmit}>
                        {this.state.error && <Alert color="warning">
                            {this.state.error}
                        </Alert>}
                        <FormGroup>
                            <Label for="name">Name</Label>
                            <Input
                                type="text"
                                name="name"
                                id="millFormName"
                                placeholder="Ex: MillGrau (white spaces will be removed)"
                                onChange={e => this.setState({ name: e.target.value.replace(/\s/g,'') })}
                            />
                        </FormGroup>
                        <Button outline color="success" type="submit">Submit</Button>
                        <Button outline color="warning" tag={Link} to="/mills" style={{marginLeft: '0.3rem'}}>Back to Mills</Button>
                    </Form>
                </div>
            </div>
        );
    }
}

export default MillForm;