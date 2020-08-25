import React, { Component } from 'react';
import {Table, ButtonGroup, Button, Container, Alert} from 'reactstrap';
import { Link } from 'react-router-dom';

import Navbar from '../assets/AppNavbar.js'

import api from '../services/Api'

class Mills extends Component{

    constructor(props) {
        super(props);
        this.state={
            mills : [],
            error: ""}
    }

    componentDidMount() {
        try {
            api.get("/api/mills")
                .then(res => {
                    const mills = res.data;
                    this.setState({mills: mills});
                })
        } catch (err) {
            const error = err.response.data.message;
            this.setState({
                error: error
            })
        }
    }

    render(){

        const millList = (this.state.mills).map(mill => {

            return <tr>
                <td style={{whiteSpace: 'nowrap'}}>{mill.name}</td>
                <td>{mill.harvests.length}</td>
                <td>
                    <ButtonGroup>
                        <Button outline size="sm" color="info" tag={Link} to={"/mill/harv/"+mill.name}>Add Harvest</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });

        return (
            <div>
                <Navbar />
                <div className="container">
                    {this.state.error && <Alert color="warning">
                        {this.state.error}
                    </Alert>}
                    <Container fluid>
                        <div>
                            <Button outline color="success" tag={Link} to="/newMill/">Add Mill</Button>
                        </div>
                        <h3>Mills</h3>
                        <Table className="mt-3">
                            <thead>
                                <tr>
                                    <th width="30%">Name</th>
                                    <th width="20%">Harvests</th>
                                    <th width="20%">Options</th>
                                </tr>
                            </thead>
                            <tbody>
                            {millList}
                            </tbody>
                        </Table>
                    </Container>
                </div>
            </div>
        );
    };

};

export default Mills;