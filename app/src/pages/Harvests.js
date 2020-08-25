import React, { Component } from 'react';
import { Table, ButtonGroup, Button, Container } from 'reactstrap';
import Navbar from '../assets/AppNavbar.js'
import { Link } from 'react-router-dom';
import api from "../services/Api";

class Harvests extends Component{

    constructor(props){
        super(props);
        this.state = {harvs: []};
    }

    componentDidMount(){
        try {
            api.get("/api/harvs")
                .then(res => {
                    const harvs = res.data;
                    this.setState({harvs: harvs});
                })
        } catch (err) {
            const error = err.response.data.message;
            this.setState({
                error: error
            })
        }
    }

    render(){

        const harvList = (this.state.harvs).map(harv => {
            const code = `${harv.code || ''}`;
            return <tr key={harv.code}>
                <td style={{whiteSpace: 'nowrap'}}>{code}</td>
                <td>{harv.start}</td>
                <td>{harv.end}</td>
                <td>{harv.farms.length}</td>
                <td>
                    <ButtonGroup>
                        <Button outline size="sm" color="info" tag={Link} to={"/harv/farm/"+harv.code}>Add Farm</Button>
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
                            <Button outline color="success" tag={Link} to="/newHarv/">Add Harvest</Button>
                        </div>
                        <h3>Harvests</h3>
                        <Table className="mt-3">
                            <thead>
                                <tr>
                                    <th width="20%">Code</th>
                                    <th width="10%">Start</th>
                                    <th width="10%">End</th>
                                    <th width="10%">Farms</th>
                                    <th width="20%">Options</th>
                                </tr>
                            </thead>
                            <tbody>
                                {harvList}
                            </tbody>
                        </Table>
                    </Container>
                </div>
            </div>
        )
    }

}

export default Harvests;