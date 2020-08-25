import React, { Component } from "react";
import { Link, withRouter } from "react-router-dom";

import api from "../../services/Api";
import { login } from "../../services/Auth";

import Navbar from '../../assets/AppNavbar'

import { Button, Form, FormGroup, Container, Input, Label, Alert } from 'reactstrap';

class SignIn extends Component {
    state = {
        email: "",
        password: "",
        error: ""
    };

    handleSignIn = async e => {
        e.preventDefault();
        const { email, password } = this.state;
        if (!email || !password) {
            this.setState({ error: "Fill all the spaces correctly to proceed" });
        } else {
            try {
                const response = await api.post("/auth/login", { email, password });
                login(response.data.token);
                this.props.history.push("/home");
            } catch (err) {
                const error = err.response.data.message;
                this.setState({
                    error:
                        error
                });
            }
        }
    };

    render() {
        return (
            <div>
                <Navbar />
                <Container>
                    <div className="jumbotron">
                        <h1>Sign In</h1>
                    </div>
                    <Form onSubmit={this.handleSignIn}>
                        {this.state.error && <Alert color="warning">{this.state.error}</Alert>}
                        <FormGroup>
                            <Label for="email">Email</Label>
                            <Input
                                type="email"
                                name="email" id="email"
                                placeholder="Ex: gabriel@gmail.com"
                                onChange={e => this.setState({ email: e.target.value })}
                            />
                        </FormGroup>
                        <FormGroup>
                            <Label for="password">Password</Label>
                            <Input
                                type="password"
                                name="password"
                                id="password"
                                placeholder="Password"
                                onChange={e => this.setState({ password: e.target.value })}
                            />
                        </FormGroup>
                        <Button outline color="info" type="submit">Sign In</Button>
                    </Form>
                    <p>Don't have an Account yet? <Link to="/signUp">Create a FREE one</Link></p>
                </Container>
            </div>
        );
    };
};

export default withRouter(SignIn);