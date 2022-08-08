import {Formik} from 'formik';
import classnames from 'classnames';
import {Link} from 'react-router-dom'
import {login} from '../../../shared/services/authentication.service';
import qs from 'query-string';
import {toast} from 'react-toastify';
import './style.css';

const FormLogin = props => {

    const {location} = props

    const _login = (request) => {

        const data = {
            ...request,
            type: 1
        }
        login(data).then(response => {

            if (response.data && response.data.code === "00") {
                const user = response.data.data;
                const user_info = {
                    id: user.id,
                    user_name: user.username,
                    full_name: user.fullName,
                    email: user.email,
                    phone: user.phone,
                    role_id: user.roleId,
                    status: user.status,
                    token: user.token
                }
                sessionStorage.setItem("Auth", JSON.stringify(user_info));
                const returnUrl = qs.parse(location.search).returnUrl;
                returnUrl ? window.location.href = returnUrl : window.location.href = "/";

            } else {
                toast.error(response.data.message ? response.data.message : '');
            }
        });
    }
    return (
        <Formik
            initialValues={{username: "", password: ""}}
            validate={values => {
                const errors = {};
                if (!values.username) {
                    errors.username = 'Vui lòng nhập tên đăng nhập';
                }
                if (values.username && values.username.length < 4) {
                    errors.username = 'Tên đăng nhập không hợp lệ';
                }
                if (!values.password) {
                    errors.password = 'Vui lòng nhập mật khẩu';
                }
                if (values.password && values.password.length < 6) {
                    errors.password = 'Mật khẩu không hợp lệ';
                }
                return errors;
            }}
            onSubmit={(values, {setSubmitting}) => {
                _login(values, setSubmitting);
            }}
        >{({
               values,
               errors,
               touched,
               handleChange,
               handleBlur,
               handleSubmit,
               isSubmitting
           }) => (
            <form className="form" onSubmit={handleSubmit} id="kt_login_signin_form">
                <div className="text-center pb-13 pt-lg-0 pt-5">
                    <h3 className="font-weight-bolder text-dark font-size-h4 font-size-h1-lg">Thủy Linh Store</h3>
                    <span className="text-muted">Hệ thống quản lý cửa hàng áo cưới Thủy Linh</span>
                </div>
                <div className="form-group">
                    <input
                        className={classnames("form-control h-auto py-4 px-5", {"is-invalid": errors.username && touched.username})}
                        placeholder="Tên đăng nhập"
                        maxLength="50"
                        type="text"
                        name="username"
                        onChange={handleChange}
                        onBlur={handleBlur}
                        value={values.username}/>
                    <div className="fv-plugins-message-container">
                        {errors.username && touched.username && (
                            <div className="fv-help-block">{errors.username}</div>
                        )}
                    </div>
                </div>
                <div className="form-group">
                    <input
                        className={classnames("form-control h-auto py-4 px-5", {"is-invalid": errors.password && touched.password})}
                        placeholder="Mật khẩu"
                        maxLength="50"
                        type="password"
                        name="password"
                        onChange={handleChange}
                        onBlur={handleBlur}
                        value={values.password}/>
                    <div className="fv-plugins-message-container">
                        {errors.password && touched.password && (
                            <div className="fv-help-block">
                                {errors.password}
                            </div>
                        )}
                    </div>
                </div>
                <div className="form-group">
                    <div className="row">
                        <div className="col text-left checkbox-inline">
                            <label className="checkbox">
                                <input type="checkbox" name="Checkboxes1"/>
                                <span></span>
                                Duy trì đăng nhập
                            </label>
                        </div>
                        <div className="col text-right">
                            <Link className="text-primary text-hover-primary pt-5"
                                  id="kt_login_forgot"
                                  to="/forgot-password">
                                Quên mật khẩu?
                            </Link>
                        </div>
                    </div>
                </div>
                <div className="pb-lg-0 pb-5">
                    <button type="submit"
                            id="kt_login_signin_submit"
                            className="btn btn-primary btn-block font-weight-bolder font-size-h6 px-8 py-4 my-3 mr-3">
                        Đăng nhập
                    </button>
                </div>
            </form>
        )}</Formik>
    )
}
export default FormLogin
