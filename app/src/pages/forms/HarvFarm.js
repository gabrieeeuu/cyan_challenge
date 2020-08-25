import React, {Component} from "react";
import api from "../../services/Api";
import Navbar from "../../assets/AppNavbar";
import {Container, Alert, Button, Form, FormGroup, Input, Label} from "reactstrap";
import {Link} from "react-router-dom";

class HarvFarm extends Component {

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
                await api.put("/api/harv/farm/"+this.props.match.params.code+"/"+code);
                this.props.history.push("/harvs");
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
                    <h3>Add Harvest to {this.props.match.params.code}</h3>
                    <Form onSubmit={this.handleSubmit}>
                        {this.state.error && <Alert color="warning">
                            {this.state.error}
                        </Alert>}
                        <FormGroup>
                            <Label for="code">Code</Label>
                            <Input
                                type="text"
                                name="code"
                                id="harvFarmCode"
                                placeholder="Ex: farm0 (white spaces will be removed)"
                                style={{textTransform: 'lowercase'}}
                                onChange={e => this.setState({ code: e.target.value.replace(/\s/g,'') })}
                            />
                        </FormGroup>
                        <Button outline color="success" type="submit">Submit</Button>
                        <Button outline color="warning" tag={Link} to="/harvs" style={{marginLeft: '0.3rem'}}>Back to Harvests</Button>
                    </Form>
                </div>
            </div>
        );
    }
}

export default HarvFarm;