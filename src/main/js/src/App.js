import 'jquery'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap'
import '@fortawesome/fontawesome-free/css/all.min.css';
import 'bootstrap-css-only/css/bootstrap.min.css';
import 'mdbreact/dist/css/mdb.css';
import './App.css'
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import PacijentRegistration from './components/pacijent/Registration'
import RegistrationRequsets from './components/pacijent/RegistrationRequsets'
import CreateClinicComponent from './components/klinika/Create';
import ListClinicsComponent from './components/klinika/ListClinics';
import UpdateClinicComponent from './components/klinika/Update';
import ViewClinicComponent from './components/klinika/ViewClinic';
import HeaderComponent from './components/Header';
import FrontPage from './components/FrontPage'
import FrontPageLekar from "./pages/lekar/FrontPageLekar";
import FooterComponent from './components/Footer';
import Login from "./pages/Login";
import PatientProfile from "./pages/patients/EditPatient";
import AddAdminToClinicComponent from "./components/klinika/admin/AddAdmin";
import UpdateAdminComponent from "./components/klinika/admin/UpdateAdmin";
import ListAdminsComponent from "./components/administrator/ListAdmins";
import AddAdminComponent from './components/administrator/Create';
import UpdateAdminKCComponent from './components/administrator/Update';
import MSHomePageComponent from './components/medicinskaSestra/HomePage';
import ListReceiptsComponent from './components/medicinskaSestra/ListReceipts';
import ViewWorkCalendar from './components/klinika/WorkCalendar';
import ViewPregledComponent from './components/klinika/pregledi/ViewPregled';
import FirstTimeLoginComponent from './components/administrator/FirstTimeLogin';
import UpdateZKComponent from './components/klinika/zdravstveniKarton/Update';
import AllPacijents from "./components/administrator/AllPacijents";
import IzmenaPacijenta from "./components/pacijent/IzmenaPacijenta";
import AllDoctors from "./components/administrator/AllDoctors";
import AllMSestre from "./components/administrator/AllMSestre";
import AddDoctor from "./components/lekar/AddDoctor";
import EditDoctor from "./components/lekar/EditDoctor";
import EditMedicinkaSestra from "./components/medicinskaSestra/EditMedicinkaSestra";
import AddMedicinskaSestra from "./components/medicinskaSestra/AddMedicinskaSestra";
import AfterAcceptRegistration from "./components/pacijent/AfterAcceptRegistration";
import EmailLogin from './pages/EmailLogin';
import PrivateRoute from './components/PrivateRoute';
import Page403 from './components/Page-403';

import EditLoggedUser from "./pages/EditLoggedUser";
import PasswordChange from "./pages/PasswordChange";
import CreateFreeAppointment from "./pages/lekar/CreateFreeAppointment";
import AddAppointment from "./pages/klinicki_administrator/AddAppointment";
import LekariKlinike from "./pages/klinicki_administrator/LekariKlinike";
import CAHomePage from "./pages/klinicki_administrator/CAHomePage";
import AddLekar from "./pages/klinicki_administrator/AddLekar";
import AllAppointments from "./pages/klinicki_administrator/AllAppointments";
import PriceList from "./pages/klinicki_administrator/PriceList";
import AddPriceList from "./pages/klinicki_administrator/AddPriceList";
import EditAppointment from "./pages/klinicki_administrator/EditAppointment";

function App() {

  return (
  	<div>
        <Router>
            <HeaderComponent />
              <div className="container-fluid">

                  <Switch>
                      <Route exact path="/403" component={Page403}></Route>
                      <Route exact path="/registracija" component={PacijentRegistration}></Route>
                      <Route exact path="/prijava" component={Login}></Route>
                      <Route exact path="/email-prijava/:token" component={EmailLogin}></Route>
                  </Switch>


                  <Switch>
                      <PrivateRoute roles={["ROLE_ADMINISTRATOR"]} path="/registracija/zahtevi" component={RegistrationRequsets} />
                      <PrivateRoute roles={["ROLE_PACIJENT"]} path="/registracija/prihvati/:path" component={AfterAcceptRegistration} />
                  </Switch>


                  <Switch>
                      <PrivateRoute roles={["ROLE_ADMINISTRATOR"]} path="/klinike" component={ListClinicsComponent} />
                      <PrivateRoute roles={["ROLE_ADMINISTRATOR"]} path="/klinike/dodaj" component={CreateClinicComponent} />
                      <PrivateRoute roles={["ROLE_ADMINISTRATOR"]} path="/klinike/pregled/:id" component={ViewClinicComponent} />
                      <PrivateRoute roles={["ROLE_ADMINISTRATOR"]} path="/klinike/izmeni/:id" component={UpdateClinicComponent} />
                      <PrivateRoute roles={["ROLE_ADMINISTRATOR"]} path="/klinike/:id/dodajAdmina" component={AddAdminToClinicComponent} />
                      <PrivateRoute roles={["ROLE_ADMINISTRATOR"]} path="/klinike/:idKlinike/izmeniAdmina/:idAdmina" component={UpdateAdminComponent} />
                  </Switch>


                  <Switch>
                      <PrivateRoute roles={["ROLE_ADMINISTRATOR"]} path="/admini" component={ListAdminsComponent} />
                      <PrivateRoute roles={["ROLE_ADMINISTRATOR"]} path="/admini/dodaj" component={AddAdminComponent} />
                      <PrivateRoute roles={["ROLE_ADMINISTRATOR"]} path="/admini/izmeni/:id" component={UpdateAdminKCComponent} />
                      <PrivateRoute roles={["ROLE_KLINICKI_ADMINISTRATOR"]} path="/admini/:id/initial" component={FirstTimeLoginComponent} />
                  </Switch>


                  <Switch>
                      <PrivateRoute roles={["ROLE_ADMINISTRATOR"]} path="/pacijenti" component={AllPacijents} />
                      <PrivateRoute roles={["ROLE_ADMINISTRATOR"]} path="/pacijenti/:id" component={IzmenaPacijenta} />
                  </Switch>


                  <Switch>
                      <PrivateRoute roles={["ROLE_ADMINISTRATOR"]} path="/medicinske-sestre" component={AllMSestre} />
                      <PrivateRoute roles={["ROLE_ADMINISTRATOR"]} path="/medicinske-sestre/dodaj" component={AddMedicinskaSestra} />
                      <PrivateRoute roles={["ROLE_ADMINISTRATOR","ROLE_MEDICINSKA_SESTRA"]} path="/medicinske-sestre/:id" component={EditMedicinkaSestra} />
                  </Switch>


                  <Switch>
                      <PrivateRoute roles={["ROLE_ADMINISTRATOR"]} path="/lekari" component={AllDoctors} />
                      <PrivateRoute roles={["ROLE_ADMINISTRATOR", "ROLE_KLINICKI_ADMINISTRATOR"]} path="/lekari/dodaj" component={AddDoctor} />
                      <PrivateRoute roles={["ROLE_ADMINISTRATOR", "ROLE_LEKAR"]} path="/lekari/:id" component={EditDoctor} />
                  </Switch>


                    </Switch>
                    <Switch>
                      <Route exact path='/registracija/zahtevi' component={RegistrationRequsets}></Route>
                      <Route exact path="/registracija/prihvati/:path" component={AfterAcceptRegistration}/>
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
                        <Route exact path="/admini/:id/initial" component={FirstTimeLoginComponent} ></Route>
                        <Route exact path="/admini/pregledi/dodaj" component={AddAppointment}/>
                        <Route exact path="/pregled/izmeni/:id" component={EditAppointment}/>
                    </Switch>
                    <Switch>
                        <Route exact path="/pacijenti" component={AllPacijents}/>
                        <Route exact path="/pacijenti/:id" component={IzmenaPacijenta}/>
                    </Switch>
                    <Switch>
                        <Route exact path="/medicinske-sestre" component={AllMSestre} />
                        <Route exact path="/medicinske-sestre/dodaj" component={AddMedicinskaSestra} />
                        <Route exact path="/medicinske-sestre/izmeni" component={EditLoggedUser}/>
                        <Route exact path="/medicinske-sestre/:id" component={EditMedicinkaSestra} />
                        <Route exact path="/medicinske-sestre/:id/klinika/:idKlinike" component={MSHomePageComponent} ></Route>
                        <Route exact path="/medicinske-sestre/:idMedSestre/klinika/:idKlinike/recepti" component={ListReceiptsComponent} ></Route>
                    </Switch>
                    <Switch>
                        <Route exact path="/lekar-pocetna" component={FrontPageLekar}/>
                        <Route exact path="/lekari" component={AllDoctors}/>
                        <Route exact path="/lekari/dodaj" component={AddDoctor}/>
                        <Route exact path="/lekari/klinika" component={LekariKlinike}/>
                        <Route exact path="/lekari/:id" component={EditDoctor}/>
                        <Route exact path="/lekar/pregledi/dodaj" component={CreateFreeAppointment}/>
                    </Switch>
                  <Switch>
                      <PrivateRoute roles={["ROLE_LEKAR"]} path="/zdravstveniKarton/:id/azuriraj" component={UpdateZKComponent} />
                  </Switch>


                  <Switch>
                      <PrivateRoute roles={["ROLE_LEKAR"]} path="/pregledi/:id/azuriraj" component={ViewPregledComponent} />
                  </Switch>


                  <Switch>
                      <PrivateRoute roles={["ROLE_LEKAR", "ROLE_MEDICINSKA_SESTRA"]} path="/:idKorisnika/radniKalendar/:idKlinike" component={ViewWorkCalendar} />
                  </Switch>


                  <Switch>
                      <PrivateRoute roles={["ROLE_PACIJENT"]} path="/pacijenti/profil/:id" component={PatientProfile} />
                  </Switch>


                  <Switch>
                      <Route path="/" exact component={FrontPage}></Route>
                      <PrivateRoute roles={["ROLE_MEDICINSKA_SESTRA"]} path="/medicinske-sestre/:id/klinika/:idKlinike" component={MSHomePageComponent} />
                      <PrivateRoute roles={["ROLE_MEDICINSKA_SESTRA"]} path="/medicinske-sestre/:idMedSestre/klinika/:idKlinike/recepti" component={ListReceiptsComponent} />
                    <Switch>
                      <Route exact path="/pregledi/:id/azuriraj" component={ViewPregledComponent}></Route>
                    </Switch>
                    <Switch>
                        <Route exact path="/:idKorisnika/radniKalendar/:idKlinike" component={ViewWorkCalendar}></Route>
                    </Switch>
                    <Switch>
                        <Route exact path="/pacijenti/profil/:id" component={PatientProfile} ></Route>
                    </Switch>
                    <Switch>
                        <Route exact path="/klinicki-administrator" component={CAHomePage}/>
                        <Route exact path="/klinika/lekar" component={AddLekar}/>
                        <Route exact path="/klinika/pregledi" component={AllAppointments}/>
                        <Route exact path="/cenovnik" component={PriceList}/>
                        <Route exact path="/cenovnik/dodaj" component={AddPriceList}/>
                        {/*<Route exact path="/cenovnik/:id" component={}/>*/}
                    </Switch>
                    <Switch>
                        <Route exact path="/korisnik/promena-lozinke" component={PasswordChange}/>
                        <Route exact path="/korisnik/izmena" component={EditLoggedUser}/>
                        <Route path="/" exact component={FrontPage}></Route>
                  </Switch>
	            </div>
            <FooterComponent />
        </Router>
	</div>
  );
}

export default App;
