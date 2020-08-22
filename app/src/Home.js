import React, { Component } from 'react';
import './App.css';
import Navbar from './AppNavbar.js'
import { Link } from 'react-router-dom';
import { Button, Container } from 'reactstrap';

class Home extends Component{
    render() {
        return (
            <div>
                <Navbar />
                <div className="container">
                    <div className="jumbotron">
                        <h1>Home</h1>
                    </div>
                    <Container fluid>
                        <Button outline color="primary" size='lg' tag={Link} to="/mills/">Mills</Button>
                        <Button outline color="primary" size='lg' tag={Link} to="/harvs/">Harvests</Button>
                    </Container>
                </div>
            </div>
        );
    }

}

export default Home;