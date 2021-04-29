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
import CreateClinicComponent from './components/klinika/Create';
import ListClinicsComponent from './components/klinika/ListClinics';
import UpdateClinicComponent from './components/klinika/Update';
import ViewClinicComponent from './components/klinika/ViewClinic';
import HeaderComponent from './components/Header';
import FrontPage from './components/FrontPage'
import FooterComponent from './components/Footer';
import GetPacijenti from './services/pacijent/GetPacijenti';
import PacijentLogin from "./components/pacijent/Login";
import AllPacijents from "./components/administrator/AllPacijents";


function App() {

  return (
  	<div>
        <Router>
            <HeaderComponent />
              <div className="container-fluid">
                    <Switch>
                      <Route path="/registracija" component={PacijentRegistration}></Route>
                      <Route path="/pacijenti/prijava" component={PacijentLogin}></Route>
                    </Switch>
                    <Switch>
                      <Route path='/zahtevi' component={RegistrationRequsets}></Route>
                    </Switch>
                    <Switch>
                        <Route exact path="/klinike" component={ListClinicsComponent} ></Route>
                        <Route exact path="/klinike/dodaj" component={CreateClinicComponent} ></Route>
                        <Route exact path="/klinike/pregled/:id" component={ViewClinicComponent} ></Route>
                        <Route exact path="/klinike/izmeni/:id" component={UpdateClinicComponent} ></Route>
                    </Switch>
                    <Switch>
                        <Route path="/PrikazSvihPacijenata" component={GetPacijenti} ></Route>
                    </Switch>
                    <Switch>
                        <Route path="/pacijenti" component={AllPacijents}></Route>
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
