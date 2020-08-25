import React, {Component} from "react";
import api from "../../services/Api";
import Navbar from "../../assets/AppNavbar";
import {Alert, Button, Form, FormGroup, Input, Label} from "reactstrap";
import {Link} from "react-router-dom";

class MillHarv extends Component {

    state = {
        code: "",
        error: ""
    };

    handleSubmit = async e => {
        e.preventDefault();
        const { code } = this.state;
        if (!code) {
            this.setState({ error: "Fill all the spaces correctly to proceed" });
        } else {
            try {
                await api.put("/api/mill/harv/"+this.props.match.params.name+"/"+code);
                this.props.history.push("/mills");
            } catch (err) {
                const error = err.response.data.message;
                this.setState({
                    error:
                        error
                });
            }
        }
    };

    render(){
        return (
            <div>
                <Navbar />
                <div className="container">
                    <h3>Add Harvest to {this.props.match.params.name}</h3>
                    <Form onSubmit={this.handleSubmit}>
                        {this.state.error && <Alert color="warning">
                            {this.state.error}
                        </Alert>}
                        <FormGroup>
                            <Label for="code">Code</Label>
                            <Input
                                type="text"
                                name="code"
                                id="millHarvCode"
                                placeholder="Ex: harv0 (white spaces will be removed)"
                                style={{textTransform: 'lowercase'}}
                                onChange={e => this.setState({ code: e.target.value.replace(/\s/g,'') })}
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

export default MillHarv;