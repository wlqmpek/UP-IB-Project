import React from 'react'

class HeaderComponent extends React.Component {
    render() {
        return (
            <div>
                <header>
                    <nav className="navbar navbar-expand-md navbar-dark bg-dark">
                        <div><a href="" className="navbar-brand">Klinički Centar Hipokrat</a></div>
                    </nav>
                </header>
            </div>
        )
    }
}

export default HeaderComponent