

const FrontPage = () => {
    return (
        <div className="row">
            <div className="col-md-3">
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <h4 className="text-center font-weight-bold">Hipokrat tim</h4>
                <br/>
                <br/>
                <p>Naš tim čine svetski priznati stručnjaci koji već godinama rade na najprestižnijim svetskim klinikama</p>
                <br/>
                <div className="row">
                    <div className="col-md-12 d-flex justify-content-center">
                        <button className="btn btn-primary justify-content-center">Upoznaj naš tim</button>
                    </div>
                </div>                    
            </div>
            <div className="col-md-9">
                <img src="/images/doktori.jpg" alt="Nas tim doktora"></img>
            </div>
            <div className="container-fluid">
                <br/>
                <br/>
                <br/>
                <br/>
                <div className="row">
                    <div className="col-md-6 offset-md-6">
                        <h2 className="font-weight-bold">Naši zaposleni</h2>
                    </div>
                </div>
                <div className="row">
                    <div className="col-md-4">
                        <div className="card">
                            <img className="card-img-top" src="/images/doktor1.jpg" alt="Doktor"></img>
                            <div className="card-body">
                                <h5 class="card-title">Marina Jevremovic</h5>
                                <p className="card-text">Lorem ipsum dolor sit amet, consectetur adipiscing elit, 
                                sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. 
                                Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
                            </div>
                        </div>
                    </div>
                    <div className="col-md-4">
                        <div className="card">
                            <img className="card-img-top" src="/images/doktor2.jpg" alt="Doktor"></img>
                            <div className="card-body">
                                <h5 class="card-title">Jovan Tirnanic</h5>
                                <p className="card-text">Lorem ipsum dolor sit amet, consectetur adipiscing elit, 
                                sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. 
                                Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
                            </div>
                        </div>
                    </div>
                    <div className="col-md-4">
                        <div className="card">
                            <img className="card-img-top" src="/images/doktor3.jpg" alt="Doktor"></img>
                            <div className="card-body">
                                <h5 class="card-title">Dusko Zivanovic</h5>
                                <p className="card-text">Lorem ipsum dolor sit amet, consectetur adipiscing elit, 
                                sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. 
                                Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
                            </div>
                        </div>
                    </div>
                </div>
                <br/>
                <br/>
            </div>
        </div>   
    )
}

export default FrontPage
