import React, { Component } from 'react';
import { Table, ButtonGroup, Button, Container } from 'reactstrap';
import Navbar from './AppNavbar';
import { Link } from 'react-router-dom';

class Harvests extends Component{

    constructor(props){
        super(props);
        this.state = {harvs: [], isLoading: true};
    }

    componentDidMount(){
        this.setState({isLoading: true});

        fetch('http://localhost:8080/api/harvs')
            .then(response => response.json())
            .then(data => this.setState({harvs: data, isLoading: false}));
    }

    render(){
        const {harvs, isLoading} = this.state;

        if(isLoading){
            return <p>Loading...</p>
        }

        const harvList = harvs.map(harv => {
            const code = `${harv.code || ''}`;
            return <tr key={harv.code}>
                <td style={{whiteSpace: 'nowrap'}}>{harv.code}</td>
                <td>{harv.start}</td>
                <td>{harv.end}</td>
                <td>{harv.farms.length}</td>
                <td>
                    <ButtonGroup>
                        <Button outline size="sm" color="primary" tag={Link} to={"/harvs/"}>View</Button>
                        <Button outline size="sm" color="info" tag={Link} to={"/harvs/"}>Add Farm</Button>
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
                            <Button outline color="success" tag={Link} to="/harvs/">Add Harvest</Button>
                        </div>
                        <h3>Harvests</h3>
                        <Table className="mt-3">
                            <thead>
                            <tr>
                                <th width="50%">Code</th>
                                <th width="20%">Start</th>
                                <th width="20%">End</th>
                                <th width="10%">Farms</th>
                                <th>Options</th>
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