import React, {Component} from 'react';
import Navbar from "../assets/AppNavbar";
import { Container } from "reactstrap";
import { Link } from 'react-router-dom';

class FrontPage extends Component {

    render(){
        return (
            <div>
                <Navbar/>
                <Container>
                    <div className="jumbotron">
                        <h2>To use this site's functionalities, please <Link to="/signIn">Sign In</Link>.</h2>
                        <h4>Or, if you don't have an Account, <Link to="/signUp">Sign Up</Link> and create a FREE one.</h4>
                    </div>
                </Container>
            </div>
        );
    }
}

export default FrontPage;