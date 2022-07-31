import { Formik } from 'formik';
import classnames from 'classnames';
import { Link } from 'react-router-dom';
import { toast } from 'react-toastify';
import { resetPassword } from '../../../shared/services/user.service'

const FormForgotPassword = props => {

    const reset_password = async (request) =>  {
        try {
            const response = await resetPassword(request)
            if (response.data) {
                if (response.data.code === 0) {
                    if (response.data.message) {
                        toast.success(response.data.message)
                        setTimeout(() => {
                            window.location.href = "/login"
                        }, 3500)
                    }
                } else {
                    if (response.data.message) {
                        toast.error(response.data.message)
                    }
                }
            }

        }
        catch (e) {
            window.location.href = "/error?error=404"
        }
        finally { }
    }

    return (
        <Formik
            initialValues={{ email: "" }}
            validate={values => {
                const errors = {};
                if (!values.email) {
                    errors.email = 'Vui lòng nhập email';
                }
                if (values.email && !/^[a-zA-Z0-9_\.\+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-\.]+/.test(values.email)) {
                    errors.email = 'Email không hợp lệ';
                }
                return errors
            }}
            onSubmit={(values, { setSubmitting, reserForm }) => {
                reset_password(values)
            }}
        >{({
               values,
               errors,
               touched,
               handleChange,
               handleBlur,
               handleSubmit,
           }) => (<form className="form" onSubmit={handleSubmit} id="kt_login_forgot_form">
            <div className="pb-13 pt-lg-0 pt-5 text-center">
                <h3 className="font-weight-bolder text-dark font-size-h4 font-size-h1-lg">Yêu cầu cấp mật khẩu mới</h3>
                <p className="text-muted">Vui lòng nhập email đăng ký tài khoản vào ô dưới để được cấp lại mật khẩu mới</p>
            </div>
            <div className="form-group">
                <input type="text"
                       maxLength="50"
                       onChange={handleChange}
                       onBlur={handleBlur}
                       value={values.email || ""}
                       className={classnames("form-control", { "is-invalid": errors.email && touched.email })}
                       placeholder="Nhập email"
                       name="email" />
                <div className="fv-plugins-message-container">{errors.email && touched.email && (
                    <div className="fv-help-block">{errors.email}</div>
                )}</div>
            </div>
            <div className="form-group pb-lg-0 text-center">
                <button type="submit" id="kt_login_forgot_submit" className="btn btn-block btn-primary font-weight-bolder font-size-h6 px-8 py-4 my-3 mr-4">Gửi yêu cầu</button>
                <Link id="kt_login_forgot_cancel" className="text-primary text-hover-primary d-inline-block px-8 py-4 my-3" to="/login">Quay lại đăng nhập</Link>
            </div>
        </form>)}</Formik>)
}

export default FormForgotPassword
