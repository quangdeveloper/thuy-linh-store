import { Formik } from 'formik';
import Modal from 'react-modal';
import classnames from 'classnames';

const customStyles = {
    overlay: {
        zIndex: "100",
        top: "-8%",
        backgroundColor: "rgb(0 0 0 / 20%)"
    },
    content: {
        border: "none",
        background: "transparent"
    }
};

const ResetPassword = ({ isOpen, save }) => {

    return (
        <Modal isOpen={isOpen} style={customStyles} ariaHideApp={false}>
            <div className="modal-dialog modal-md" member="document">
                <div className="modal-content">
                    <div className="modal-header">
                        <h5 className="modal-title">Đặt lại mật khẩu </h5>
                        <button type="button" className="close" onClick={() => {}}>
                            <i aria-hidden="true" className="ki ki-close"></i>
                        </button>
                    </div>
                    <Formik
                        initialValues={{ email: "" }}
                        validate={values => {
                            const errors = {};
                            if (!values.email) {
                                errors.email = 'Vui lòng nhập email';
                            }
                            if (values.email && !/^[a-zA-Z0-9]+@(?:[a-zA-Z0-9]+\.)+[A-Za-z]+$/.test(values.email)) {
                                errors.email = 'Email không hợp lệ';
                            }
                            return errors
                        }}
                        onSubmit={(values, { setSubmitting }) => {
                            save(values)
                        }}
                    >{({
                        values,
                        errors,
                        touched,
                        handleChange,
                        handleBlur,
                        handleSubmit,
                    }) => (
                            <form className="form" onSubmit={handleSubmit}>
                                <div className="modal-body">
                                    <div className="form-group row">
                                        <label className="col-lg-4 col-form-label text-left">Email: <span className="text-danger">*</span></label>
                                        <div className="col-lg-8">
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
                                    </div>
                                </div>
                                <div className="modal-footer">
                                    <button type="button" className="btn btn-bg-secondary font-weight-bold bg-hover-gray-400" onClick={() => {}}>Hủy</button>
                                    <button type="submit" className="btn btn-primary font-weight-bold" data-dismiss="modal">Lưu</button>
                                </div>
                            </form>
                        )}</Formik>
                </div>
            </div>
        </Modal>
    )
}

export default ResetPassword
