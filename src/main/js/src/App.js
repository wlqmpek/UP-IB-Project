import 'bootstrap/dist/css/bootstrap.min.css'
import './App.css'
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import { useState } from 'react';
import PacijentRegistration from './components/pacijent/Registration'
import CreateClinicsService from './services/ClinicsService'
import HeaderComponent from './components/Header';
import FooterComponent from './components/Footer';

function App() {

  const [pacijenti, setPacijenti] = useState([])

  const onAdd = async (pacijent) => {
    const res = await fetch('http://localhost:8080/KlinickiCentar/Pacijenti',
    {
      method: 'POST',
      headers: {
        'Content-type': 'application/json'
      },
      body: JSON.stringify(pacijent)
    })

    const data = await res.json()

    setPacijenti(data)
  }

  return (
  	<div>
        <Router>
            <HeaderComponent />
                <div className="container">
                    <PacijentRegistration onAdd={onAdd} />
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
