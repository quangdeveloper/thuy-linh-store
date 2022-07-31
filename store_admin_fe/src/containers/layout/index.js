import AppHeader from '../header/AppHeader'
import AppAside from '../menu/AppAside'
import {
    Route,
    Switch
} from 'react-router-dom'
import routes from '../../router/router'
import React, { Fragment } from 'react'

const Layout = props => {
    return (
        <Fragment>
            <div className="d-flex flex-row flex-column-fluid page">
                <AppAside {...props} />
                <div className="d-flex flex-column flex-row-fluid wrapper" id="kt_wrapper">
                    <AppHeader {...props} />
                    <Switch>
                        {routes.map((route, idx) => {
                            return route.component ? (
                                <Route
                                    key={idx}
                                    path={route.path}
                                    exact={route.exact}
                                    meta={route.meta}
                                    name={route.name}
                                    render={props => (
                                        <route.component {...props} />
                                    )} />
                            ) : (null);
                        })}
                    </Switch>
                </div>
            </div>
        </Fragment>
    )
}

export default Layout