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
import AddAdminToClinicComponent from "./components/klinika/admin/AddAdmin";
import UpdateAdminComponent from "./components/klinika/admin/UpdateAdmin";
import ListAdminsComponent from "./components/administrator/ListAdmins";
import AddAdminComponent from './components/administrator/Create';
import UpdateAdminKCComponent from './components/administrator/Update';
import MSHomePageComponent from './components/medicinskaSestra/HomePage';
import ListReceiptsComponent from './components/medicinskaSestra/ListReceipts';
import ViewWorkCalendar from './components/klinika/WorkCalendar';
import ViewPregledComponent from './components/klinika/pregledi/ViewPregled';

function App() {

  return (
  	<div>
        <Router>
            <HeaderComponent />
              <div className="container-fluid">
                    <Switch>
                      <Route exact path="/registracija" component={PacijentRegistration}></Route>
                      <Route exact path="/pacijenti/prijava" component={PacijentLogin}></Route>
                    </Switch>
                    <Switch>
                      <Route exact path='/registracija/zahtevi' component={RegistrationRequsets}></Route>
                    </Switch>
                    <Switch>
                        <Route exact path="/klinike" component={ListClinicsComponent} ></Route>
                        <Route exact path="/klinike/dodaj" component={CreateClinicComponent} ></Route>  
                        <Route exact path="/klinike/pregled/:id" component={ViewClinicComponent} ></Route>
                        <Route exact path="/klinike/izmeni/:id" component={UpdateClinicComponent} ></Route>
                        <Route exact path="/klinike/:id/dodajAdmina" component={AddAdminToClinicComponent} ></Route>
                        <Route exact path='/klinike/:idKlinike/izmeniAdmina/:idAdmina' component={UpdateAdminComponent}></Route>
                    </Switch>
                    <Switch>
                        <Route exact path="/admini" component={ListAdminsComponent} ></Route>
                        <Route exact path="/admini/dodaj" component={AddAdminComponent} ></Route>
                        <Route exact path="/admini/izmeni/:id" component={UpdateAdminKCComponent} ></Route>
                    </Switch>

                    <Switch>
                      <Route exact path="/pregledi/:id/azuriraj" component={ViewPregledComponent}></Route>
                    </Switch>
                    <Switch>
                        <Route exact path="/:idKorisnika/radniKalendar/:idKlinike" component={ViewWorkCalendar}></Route>
                    </Switch>
                    <Switch>
                        <Route exact path="/pacijenti" component={GetPacijenti} ></Route>
                        <Route exact path="/medicinskaSestra/:id/klinika/:idKlinike" component={MSHomePageComponent} ></Route>
                        <Route exact path="/medicinskaSestra/:idMedSestre/klinika/:idKlinike/recepti" component={ListReceiptsComponent} ></Route>
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
