import 'jquery'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap'
import '@fortawesome/fontawesome-free/css/all.min.css';
import 'bootstrap-css-only/css/bootstrap.min.css';
import 'mdbreact/dist/css/mdb.css';
import 'react-datepicker/dist/react-datepicker.css';
import './App.css'

import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import FrontPagePacijent from "./pages/pacijent/FrontPagePacijent";
import PacijentRegistration from './components/pacijent/Registration'
import RegistrationRequsets from './components/pacijent/RegistrationRequsets'
import CreateClinicComponent from './components/klinika/Create';
import ListClinicsComponent from './components/klinika/ListClinics';
import UpdateClinicComponent from './components/klinika/Update';
import ViewClinicComponent from './components/klinika/ViewClinic';
import ViewPreglediPacijenta from "./components/klinika/pregledi/ViewPreglediPacijenta";
import HeaderComponent from './components/Header';
import ViewTerminiKlinike from "./components/termin/ViewTerminiKlinike";
import FrontPage from './components/FrontPage'
import FrontPageLekar from "./pages/lekar/FrontPageLekar";
import FooterComponent from './components/Footer';
import Login from "./pages/Login";
import PretragaKlinika from "./components/klinika/PretragaKlinika";
import EditPatient from "./pages/pacijent/EditPatient";
import AddAdminToClinicComponent from "./components/klinika/admin/AddAdmin";
import UpdateAdminComponent from "./components/klinika/admin/UpdateAdmin";
import ListAdminsComponent from "./components/administrator/ListAdmins";
import AddAdminComponent from './components/administrator/Create';
import UpdateAdminKCComponent from './components/administrator/Update';
import MSHomePageComponent from './components/medicinskaSestra/HomePage';
import ListReceiptsComponent from './components/medicinskaSestra/ListReceipts';
import ViewWorkCalendar from './components/klinika/WorkCalendar';
import ViewPregledComponent from './components/klinika/pregledi/ViewPregled';
import ZdravstveniKartonView from "./components/klinika/zdravstveniKarton/ZdravstveniKartonView";
import FirstTimeLoginComponent from './components/administrator/FirstTimeLogin';
import UpdateZKComponent from './components/klinika/zdravstveniKarton/Update';
import AllPacijents from "./components/administrator/AllPacijents";
import IzmenaPacijenta from "./components/pacijent/IzmenaPacijenta";
import ViewZakazivanjePregleda from "./components/klinika/pregledi/ViewZakazivanjePregleda";
import AllDoctors from "./components/administrator/AllDoctors";
import AllMSestre from "./components/administrator/AllMSestre";
import AddDoctor from "./components/lekar/AddDoctor";
import EditDoctor from "./components/lekar/EditDoctor";
import EditMedicinkaSestra from "./components/medicinskaSestra/EditMedicinkaSestra";
import AddMedicinskaSestra from "./components/medicinskaSestra/AddMedicinskaSestra";
import AfterAcceptRegistration from "./components/pacijent/AfterAcceptRegistration";
import EmailLogin from './pages/EmailLogin';
import ViewAllClinics from "./components/klinika/ViewAllClinics";
import ViewPotvrdaTermina from "./components/termin/ViewPotvrdaTermina";
import ViewSlobodniLekari from "./components/pacijent/ViewSlobodniLekari";
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
                      <PrivateRoute roles={["ROLE_ADMINISTRATOR"]} path="/klinike/dodaj" component={CreateClinicComponent} />
                      <PrivateRoute roles={["ROLE_ADMINISTRATOR"]} path="/klinike/pregled/:id" component={ViewClinicComponent} />
                      <PrivateRoute roles={["ROLE_ADMINISTRATOR", "ROLE_KLINICKI_ADMINISTRATOR"]} path="/klinike/izmeni/:id" component={UpdateClinicComponent} />
                      <PrivateRoute roles={["ROLE_ADMINISTRATOR"]} path="/klinike/:id/dodajAdmina" component={AddAdminToClinicComponent} />
                      <PrivateRoute roles={["ROLE_ADMINISTRATOR"]} path="/klinike/:idKlinike/izmeniAdmina/:idAdmina" component={UpdateAdminComponent} />
                      <PrivateRoute roles={["ROLE_ADMINISTRATOR", "ROLE_PACIJENT"]} path="/klinike" component={ListClinicsComponent} />
                  </Switch>

                  <Switch>
                      <PrivateRoute roles={["ROLE_ADMINISTRATOR"]} exact path="/admini" component={ListAdminsComponent} />
                      <PrivateRoute roles={["ROLE_ADMINISTRATOR"]} exact path="/admini/dodaj" component={AddAdminComponent} />
                      <PrivateRoute roles={["ROLE_ADMINISTRATOR"]} exact path="/admini/izmeni/:id" component={UpdateAdminKCComponent} />
                      <PrivateRoute roles={["ROLE_KLINICKI_ADMINISTRATOR"]} path="/admini/:id/initial" component={FirstTimeLoginComponent} />
                      <PrivateRoute roles={["ROLE_KLINICKI_ADMINISTRATOR"]} exact path="/admini/pregledi/dodaj" component={AddAppointment} />
                      <PrivateRoute roles={["ROLE_KLINICKI_ADMINISTRATOR"]} path="/pregled/izmeni/:id" component={EditAppointment} />
                  </Switch>

                  <Switch>
                      <PrivateRoute roles={["ROLE_PACIJENT"]} path="/pacijent-pocetna" component={FrontPagePacijent}/>
                      <PrivateRoute roles={["ROLE_PACIJENT"]} path="/zdravstveni-karton/:id" component={ZdravstveniKartonView}/>
                      <PrivateRoute roles={["ROLE_PACIJENT", "ROLE_MEDICINSKA_SESTRA"]} path="/pacijenti/profil/izmeni/:id" component={EditPatient} />
                      <PrivateRoute roles={["ROLE_PACIJENT"]} path="/pregledi" component={ViewPreglediPacijenta} />
                      <PrivateRoute roles={["ROLE_PACIJENT"]} path="/zakazivanje-pregleda/:id" component={ViewZakazivanjePregleda} />
                      <PrivateRoute roles={["ROLE_PACIJENT"]} path="/pretraga-klinika" component={PretragaKlinika} />}/>
                      <PrivateRoute roles={["ROLE_PACIJENT"]} path="/prikaz-lekara" component={ViewSlobodniLekari} />
                      <PrivateRoute roles={["ROLE_PACIJENT"]} path="/lista-klinika" component={ViewAllClinics} />
                      <PrivateRoute roles={["ROLE_PACIJENT"]} path="/prikaz-termina-klinike" component={ViewTerminiKlinike} />
                      <PrivateRoute roles={["ROLE_PACIJENT"]} path="/potvrda-termina/idKorisnika/:idKorisnika/idTermina/:idTermina" component={ViewPotvrdaTermina} />
                      <PrivateRoute roles={["ROLE_ADMINISTRATOR"]} path="/pacijenti/:id" component={IzmenaPacijenta} />
                      <PrivateRoute roles={["ROLE_ADMINISTRATOR","ROLE_LEKAR"]} path="/pacijenti" component={AllPacijents} />
                  </Switch>

                  <Switch>
                      <PrivateRoute roles={["ROLE_MEDICINSKA_SESTRA"]} path="/medicinske-sestre/:idMedSestre/klinika/:idKlinike/recepti" component={ListReceiptsComponent} />
                      <PrivateRoute roles={["ROLE_MEDICINSKA_SESTRA"]} path="/medicinske-sestre/:id/klinika/:idKlinike" component={MSHomePageComponent} />
                      <PrivateRoute roles={["ROLE_MEDICINSKA_SESTRA"]} path="/medicinske-sestre/izmeni" component={EditLoggedUser} />
                      <PrivateRoute roles={["ROLE_ADMINISTRATOR"]} path="/medicinske-sestre" component={AllMSestre} />
                      <PrivateRoute roles={["ROLE_ADMINISTRATOR"]} path="/medicinske-sestre/dodaj" component={AddMedicinskaSestra} />
                      <PrivateRoute roles={["ROLE_ADMINISTRATOR"]} path="/medicinske-sestre/:id" component={EditMedicinkaSestra} />
                  </Switch>

                  <Switch>
                      <PrivateRoute roles={["ROLE_LEKAR"]} path="/lekar-pocetna" component={FrontPageLekar} />
                      <PrivateRoute roles={["ROLE_ADMINISTRATOR", "ROLE_KLINICKI_ADMINISTRATOR"]} path="/lekari/dodaj" component={AddDoctor} />
                      <PrivateRoute roles={["ROLE_LEKAR", "ROLE_KLINICKI_ADMINISTRATOR"]} path="/lekari/klinika" component={LekariKlinike} />
                      <PrivateRoute roles={["ROLE_LEKAR"]} path="/lekar/pregledi/dodaj" component={CreateFreeAppointment} />
                      <PrivateRoute roles={["ROLE_ADMINISTRATOR"]} path="/lekari/:id" component={EditDoctor} />
                      <PrivateRoute roles={["ROLE_ADMINISTRATOR"]} path="/lekari" component={AllDoctors} />
                  </Switch>

                  <Switch>
                      <PrivateRoute roles={["ROLE_LEKAR"]} path="/pregled/:idPregleda/zdravstveniKarton/:id/azuriraj" component={UpdateZKComponent} />
                  </Switch>

                  <Switch>
                      <PrivateRoute roles={["ROLE_LEKAR"]} path="/pregledi/:id/azuriraj" component={ViewPregledComponent} />
                  </Switch>

                  <Switch>
                      <PrivateRoute roles={["ROLE_LEKAR", "ROLE_MEDICINSKA_SESTRA"]} path="/:idKorisnika/radniKalendar/:idKlinike" component={ViewWorkCalendar} />
                  </Switch>

                  <Switch>
                      <Route path="/" exact component={FrontPage}></Route>
                  </Switch>

                  <Switch>
                      <PrivateRoute roles={["ROLE_KLINICKI_ADMINISTRATOR"]} path="/klinicki-administrator" component={CAHomePage} />
                      <PrivateRoute roles={["ROLE_KLINICKI_ADMINISTRATOR"]} path="/klinika/lekar" component={AddLekar} />
                      <PrivateRoute roles={["ROLE_KLINICKI_ADMINISTRATOR"]} path="/klinika/pregledi" component={AllAppointments} />
                      <PrivateRoute roles={["ROLE_KLINICKI_ADMINISTRATOR"]} path="/cenovnik/dodaj" component={AddPriceList} />
                      <PrivateRoute roles={["ROLE_KLINICKI_ADMINISTRATOR"]} path="/cenovnik" component={PriceList} />
                        {/*<Route exact path="/cenovnik/:id" component={}/>*/}
                  </Switch>

                  <Switch>
                      <Route exact path="/korisnik/promena-lozinke" component={PasswordChange}/>
                      <Route exact path="/korisnik/izmena" component={EditLoggedUser}/>
                  </Switch>

	            </div>
            <FooterComponent />
        </Router>
	</div>
  );
}

export default App;
