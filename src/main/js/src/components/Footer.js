import React from 'react'

class FooterComponent extends React.Component {
    render() {
        return (

            <footer className="page-footer font-small indigo pt-4">
                <div className="container text-center text-md-left">
                    <div className="row text-center text-md-left mt-3 pb-3">
                        <div className="col-md-3 col-lg-3 col-xl-3 mx-auto mt-3">
                            <h6 className="text-uppercase mb-4 font-weight-bold">Klinicki centar Hipokrat</h6>
                            <p>Privatni zdravstveni KC Hipokrat nastao je 2020. godine i postaje
                                najugledniji privatni klinicki centar u zemlji,
                                a sada postaje i jedan od najvećih zdravstvenih sistema u Srbiji i regionu.
                            </p>
                        </div>

                        <hr className="w-100 clearfix d-md-none"/>

                        <div className="col-md-2 col-lg-2 col-xl-2 mx-auto mt-3">
                            <h6 className="text-uppercase mb-4 font-weight-bold">Usluge</h6>
                            <p>
                                <a href="#!">Lekarski pregledi</a>
                            </p>
                            <p>
                                <a href="#!">Zakazivanje online</a>
                            </p>
                            <p>
                                <a href="#!">Nasi lekari</a>
                            </p>
                            <p>
                                <a href="#!">Cenovnik</a>
                            </p>
                        </div>

                        <hr className="w-100 clearfix d-md-none"/>

                        <div className="col-md-3 col-lg-2 col-xl-2 mx-auto mt-3">
                            <h6 className="text-uppercase mb-4 font-weight-bold">Korisni linkovi</h6>
                            <p>
                                <a href="#!">Vas nalog</a>
                            </p>
                            <p>
                                <a href="#!">Registrujte se</a>
                            </p>
                            <p>
                                <a href="#!">Cenovnik</a>
                            </p>
                            <p>
                                <a href="#!">O nama</a>
                            </p>
                        </div>

                        <hr className="w-100 clearfix d-md-none"/>

                        <div className="col-md-4 col-lg-3 col-xl-3 mx-auto mt-3">
                            <h6 className="text-uppercase mb-4 font-weight-bold">Kontakt</h6>
                            <p>
                                <i className="fas fa-home mr-3"></i> Novi Sad, Ulica 21000, RS</p>
                            <p>
                                <i className="fas fa-envelope mr-3"></i> kchipokrat@gmail.com</p>
                            <p>
                                <i className="fas fa-phone mr-3"></i>021/123-123</p>
                            <p>
                                <i className="fas fa-print mr-3"></i>021/456-456</p>
                        </div>
                    </div>
                    <div className="row d-flex align-items-center">
                        <div className="col-md-7 col-lg-8">
                            <p className="text-center text-md-left">© 2021 Copyright:
                                <strong> Team MIM</strong>
                            </p>
                        </div>
                        {/* <div className="col-md-5 col-lg-4 ml-lg-0">
                    <div className="text-center text-md-right">
                        <ul className="list-unstyled list-inline">
                            <li className="list-inline-item">
                                <a className="btn-floating btn-sm rgba-white-slight mx-1">
                                    <i className="fab fa-facebook-f"></i>
                                </a>
                            </li>
                            <li className="list-inline-item">
                                <a className="btn-floating btn-sm rgba-white-slight mx-1">
                                    <i className="fab fa-twitter"></i>
                                </a>
                            </li>
                            <li className="list-inline-item">
                                <a className="btn-floating btn-sm rgba-white-slight mx-1">
                                    <i className="fab fa-google-plus-g"></i>
                                </a>
                            </li>
                            <li className="list-inline-item">
                                <a className="btn-floating btn-sm rgba-white-slight mx-1">
                                    <i className="fab fa-linkedin-in"></i>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div> */}
                    </div>
                </div>
            </footer>

        )
    }
}

export default FooterComponent