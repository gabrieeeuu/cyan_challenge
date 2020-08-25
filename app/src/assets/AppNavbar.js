import React, { Component } from 'react';
import { Button, Collapse, Nav, Navbar, NavbarBrand, NavbarToggler, NavItem, NavLink } from 'reactstrap';
import { Link } from 'react-router-dom';

import {logout, isAuthenticated} from "../services/Auth";

function showLogout() {
    if(!isAuthenticated()) {
        return null;
    }

    return (<Button outline color="danger" onClick={logout} tag={Link} to="/">Logout</Button>);
}

export default class AppNavbar extends Component {
    constructor(props) {
        super(props);
        this.state = {isOpen: false};
        this.toggle = this.toggle.bind(this);
    }

    toggle() {
        this.setState({
            isOpen: !this.state.isOpen
        });
    }

    render() {

        return <Navbar color="dark" dark expand="md">
            <div className="container" id="containerNav">
                <NavbarBrand tag={Link} to="/home">Home</NavbarBrand>
                <NavbarToggler onClick={this.toggle}/>
                <Collapse isOpen={this.state.isOpen} navbar>
                    <Nav className="ml-auto" navbar>
                        <NavItem>
                            <NavLink href="https://github.com/gabrieeeuu/cyan_challenge" target="_blank">Github</NavLink>
                        </NavItem>
                        <NavItem>
                            {showLogout()}
                        </NavItem>
                    </Nav>
                </Collapse>
            </div>
        </Navbar>;
    }
}