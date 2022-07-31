import React from 'react'
import Aside from './Aside'
import FormLogin from './FormLogin'
import './style.css'

const Login = props => {
    return (
        <div className="login login-1 login-signin-on d-flex flex-column flex-lg-row flex-column-fluid bg-white" id="kt_login">
            <Aside />
            <div className="login-content flex-row-fluid d-flex flex-column justify-content-center position-relative overflow-hidden p-7 mx-auto">
                <div className="d-flex flex-column-fluid flex-center">
                    <div className="login-form login-signin">
                        <FormLogin {...props} />
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Login