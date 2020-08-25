import React, { Component } from 'react';
import '../styles/App.css';
import Navbar from '../assets/AppNavbar.js'
import { Link } from 'react-router-dom';
import { Button, Container } from 'reactstrap';

class Home extends Component{
    render() {
        return (
            <div>
                <Navbar/>
                <div className="container">
                    <div className="jumbotron">
                        <h1>Home</h1>
                    </div>
                    <Container fluid>
                        <Button outline color="primary" size='lg' tag={Link} to="/mills/">Mills</Button>
                        <Button outline color="primary" size='lg' tag={Link} to="/harvs/">Harvests</Button>
                        <Button outline color="primary" size='lg' tag={Link} to="/farms/">Farms</Button>
                        <Button outline color="primary" size='lg' tag={Link} to="/fields/">Fields</Button>
                    </Container>
                </div>
            </div>
        );
    }
    
}

export default Home;