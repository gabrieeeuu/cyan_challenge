import React, { Component } from 'react';
import './App.css';
import Table from './components/Table.js';
import Home from "./components/Home.js";
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";

class App extends Component {
    render(){
        return (
            <Router>
                <Switch>
                    <Route path='/' exact={true} component={Home}/>
                </Switch>
            </Router>
        )
    }
}

export default App;
