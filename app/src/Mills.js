import React, { Component } from 'react';
import { Table, ButtonGroup, Button, Container } from 'reactstrap';
import Navbar from './AppNavbar';
import { Link } from 'react-router-dom';

class Mills extends Component{

    constructor(props) {
        super(props);
        this.state = {mills: [], isLoading: true};
    }

    componentDidMount(){
        this.setState({isLoading: true});

        fetch('http://localhost:8080/api/mills')
            .then(response => response.json())
            .then(data => this.setState({mills: data, isLoading: false}));
    }

    render(){
        const {mills, isLoading} = this.state;

        if(isLoading){
            return <p>Loading...</p>;
        }

        const millList = mills.map(mill => {
            const name = `${mill.name || ''}`;
            return <tr key={mill.name}>
                <td style={{whiteSpace: 'nowrap'}}>{mill.name}</td>
                <td>{mill.harvests.length}</td>
                <td>
                    <ButtonGroup>
                        <Button outline size="sm" color="primary" tag={Link} to={"/mills/"}>View</Button>
                        <Button outline size="sm" color="info" tag={Link} to={"/mills/"}>Add Harvest</Button>
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
                            <Button outline color="success" tag={Link} to="/mills/">Add Mill</Button>
                        </div>
                        <h3>Mills</h3>
                        <Table className="mt-3">
                            <thead>
                            <tr>
                                <th width="60%">Name</th>
                                <th wisth="40%">Harvests</th>
                                <th>Options</th>
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
    }

}

export default Mills;