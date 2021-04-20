import 'bootstrap/dist/css/bootstrap.min.css'
import './App.css'
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import PacijentRegistration from './components/pacijent/Registration'
import RegistrationRequsets from './components/pacijent/RegistrationRequsets'
import CreateClinicsService from './services/ClinicsService'
import HeaderComponent from './components/Header';
import FooterComponent from './components/Footer';

function App() {

  return (
  	<div>
        <Router>
            <HeaderComponent />
                <div className="container">
                    <Switch>
                      <Route path="/Registration" component={PacijentRegistration}></Route>
                    </Switch>
                    <Switch>
                      <Route path='/Requests' component={RegistrationRequsets}></Route>
                    </Switch>
                    <Switch>
                        <Route path="/CreateClinic" component={CreateClinicsService} ></Route>
                    </Switch>
	              </div>
            <FooterComponent />
        </Router>
	</div>
  );
}

export default App;
