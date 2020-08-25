import React, { Component } from 'react';
import { Table, ButtonGroup, Button, Container } from 'reactstrap';
import Navbar from '../assets/AppNavbar';
import { Link } from 'react-router-dom';
import api from "../services/Api";

class Fields extends Component{

    constructor(props){
        super(props);
        this.state = {fields:[], isLoading: true}
    }

    componentDidMount(){
        try {
            api.get("/api/fields")
                .then(res => {
                    const fields = res.data;
                    this.setState({fields: fields});
                })
        } catch (err) {
            this.setState({
                error:"Something went wrong :("
            })
        }
    }

    render(){
        const {isLoading} = this.state.isLoading;

        if(isLoading){
            return <div>
                     <Navbar />
                <div className="container">
                    <p>Loading...</p>
                </div>
            </div>;
        }

        const fieldList = (this.state.fields).map(field => {
            return <tr key={field.code}>
                <td style={{whiteSpace: 'nowrap'}}>{field.code}</td>
                <td style={{whiteSpace: 'nowrap'}}>{field.latitude}</td>
                <td style={{whiteSpace: 'nowrap'}}>{field.longitude}</td>
                <td>
                    <ButtonGroup>
                        <Button outline size="sm" color="primary" tag={Link} to={"/fields/"}>View</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });
   
        return (
            <div>
                <Navbar />
                <div className="container">
                    <Container fluid>
                        <div>
                            <Button outline color="success" tag={Link} to="/newField">Add Field</Button>
                        </div>
                        <h3>Fields</h3>
                        <Table className="mt-3">
                            <thead>
                                <tr>
                                    <th width="20%">Code</th>
                                    <th width="15%">Latitude</th>
                                    <th width="15%">Longitude</th>
                                    <th width="20%">Options</th>
                                </tr>
                            </thead>
                            <tbody>
                                {fieldList}
                            </tbody>
                        </Table>
                    </Container>
                </div>
            </div>
        );
    }
    
}

export default Fields;