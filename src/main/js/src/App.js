import 'jquery'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap'
import '@fortawesome/fontawesome-free/css/all.min.css';
import 'bootstrap-css-only/css/bootstrap.min.css';
import 'mdbreact/dist/css/mdb.css';
import './App.css'
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import PacijentRegistration from './components/pacijent/Registration'
import RegistrationRequsets from './components/pacijent/RegistrationRequsets'
import CreateClinicsService from './services/ClinicsService'
import HeaderComponent from './components/Header';
import FrontPage from './components/FrontPage'
import FooterComponent from './components/Footer';
import GetPacijenti from './services/pacijent/GetPacijenti';


function App() {

  return (
  	<div>
        <Router>
            <HeaderComponent />
              <div className="container-fluid">
                    <Switch>
                      <Route path="/Registration" component={PacijentRegistration}></Route>
                    </Switch>
                    <Switch>
                      <Route path='/Requests' component={RegistrationRequsets}></Route>
                    </Switch>
                    <Switch>
                        <Route path="/CreateClinic" component={CreateClinicsService} ></Route>
                    </Switch>
                    <Switch>
                        <Route path="/PrikazSvihPacijenata" component={GetPacijenti} ></Route>
                    </Switch>
                    <Switch>
                        <Route path="/" exact component={FrontPage}></Route>
                    </Switch>
                  
	            </div>
            <FooterComponent />
        </Router>
	</div>
  );
}

export default App;
