import {AuthenticationService} from "../services/AuthenticationService";
import {Redirect, Route} from "react-router-dom";

const PrivateRoute = ({path, roles, component, ...rest}) => (

    <Route {...rest} render={props => {
        const currentUser = AuthenticationService.getRole();
        console.log("Current user: " + currentUser);
        console.log("Allowed roles:");
        console.log(roles);
        if (currentUser === "") {
            // not logged in so redirect to login page with the return url
            return <Redirect to={{ pathname: '/prijava', state: { from: props.location } }} />
        }

        // check if route is restricted by role
        if (roles && !roles.includes(currentUser)) {
            // role not authorised so redirect to home page
            return <Redirect to={{ pathname: '/403'}} />
        }

        // authorised so return component
        return <Route exact path={path} component={component}/>
    }} />
)

export default PrivateRoute