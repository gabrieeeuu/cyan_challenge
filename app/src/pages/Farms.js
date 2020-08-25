import React, { Component } from 'react';
import { Table, ButtonGroup, Button, Container } from 'reactstrap';
import Navbar from '../assets/AppNavbar';
import { Link } from 'react-router-dom';
import api from "../services/Api";

class Farms extends Component{

    constructor(props){
        super(props);
        this.state = {farms:[], isLoading: true}
    }

    componentDidMount(){
        try {
            api.get("/api/farms")
                .then(res => {
                    const farms = res.data;
                    this.setState({farms: farms});
                })
        } catch (err) {
            this.setState({
                error:"Something went wrong :("
            })
        }
    }

    render(){

        const farmList = (this.state.farms).map(farm => {
            const name = `${farm.name || ''}`;
            return <tr key={farm.code}>
                <td style={{whiteSpace: 'nowrap'}}>{farm.code}</td>
                <td style={{whiteSpace: 'nowrap'}}>{name}</td>
                <td>{farm.fields.length}</td>
                <td>
                    <ButtonGroup>
                        <Button outline size="sm" color="info" tag={Link} to={"/farm/field/"+farm.code}>Add Field</Button>
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
                            <Button outline color="success" tag={Link} to="/newFarm/">Add Farm</Button>
                        </div>
                        <h3>Farms</h3>
                        <Table className="mt-3">
                            <thead>
                                <tr>
                                    <th width="20%">Code</th>
                                    <th width="15%">Name</th>
                                    <th width="15%">Fields</th>
                                    <th width="20%">Options</th>
                                </tr>
                            </thead>
                            <tbody>
                                {farmList}
                            </tbody>
                        </Table>
                    </Container>
                </div>
            </div>
        );
    }
    
}

export default Farms;