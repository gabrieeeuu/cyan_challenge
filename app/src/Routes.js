import React from "react";
import { BrowserRouter, Route, Switch, Redirect } from "react-router-dom";

import Home from './pages/Home';

import Mills from './pages/Mills';
import Harvs from './pages/Harvests';
import Farms from './pages/Farms';
import Fields from './pages/Fields';

import MillForm from './pages/forms/MillForm';
import HarvForm from "./pages/forms/HarvForm";
import FarmForm from "./pages/forms/FarmForm";
import FieldForm from "./pages/forms/FieldForm";

import FrontPage from "./pages/FrontPage";
import SignUp from "./pages/forms/SignUp";
import SignIn from "./pages/forms/SignIn";

import { isAuthenticated } from "./services/Auth";

import MillHarv from "./pages/forms/MillHarv";
import HarvFarm from "./pages/forms/HarvFarm";
import FarmField from "./pages/forms/FarmField";

const PrivateRoute = ({ component: Component, ...rest }) => (
  <Route
    {...rest}
    render={props =>
      isAuthenticated() ? (
        <Component {...props} />
      ) : (
        <Redirect to={{ pathname: "/", state: { from: props.location } }} />
      )
    }
  />
);

const NoAuthRoute = ({ component: Component, ...rest }) => (
    <Route
        {...rest}
        render={props =>
            !isAuthenticated() ? (
                <Component {...props} />
            ) : (
                <Redirect to={{ pathname: "/home", state: { from: props.location } }} />
            )
        }
    />
);

const Routes = () => (
    <BrowserRouter>
        <Switch>
            <Route exact path="/" component={FrontPage} />

            <NoAuthRoute path="/signUp" component={SignUp} />
            <NoAuthRoute path="/signIn" component={SignIn} />

            <PrivateRoute path='/home' exact={true} component={Home}/>
            <PrivateRoute path='/mills' exact={true} component={Mills}/>
            <PrivateRoute path='/harvs' exact={true} component={Harvs}/>
            <PrivateRoute path='/farms' exact={true} component={Farms}/>
            <PrivateRoute path='/fields' exact={true} component={Fields}/>

            <PrivateRoute path='/newMill' exact={true} component={MillForm}/>
            <PrivateRoute path='/newHarv' exact={true} component={HarvForm}/>
            <PrivateRoute path='/newFarm' exact={true} component={FarmForm}/>
            <PrivateRoute path='/newField' exact={true} component={FieldForm}/>

            <PrivateRoute path='/mill/harv/:name' exact={true} component={MillHarv}/>
            <PrivateRoute path='/harv/farm/:code' exact={true} component={HarvFarm}/>
            <PrivateRoute path='/farm/field/:code' exact={true} component={FarmField}/>

            <Route path="*" component={() => <h1>Page not found</h1>} />
       
        </Switch>
    </BrowserRouter>
);

export default Routes;