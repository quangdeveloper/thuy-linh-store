import GlobalStyle from './shared/themes/global-styled';
import { Helmet } from 'react-helmet';
import { BrowserRouter, Switch } from 'react-router-dom';
import { GuardProvider, GuardedRoute } from 'react-router-guards';
import NotFound from './containers/not_found/NotFound';
import AuthGuard from './shared/auth_guard/auth-guard';
import { Login, ForgotPassword } from './components/authentication';
import Layout from './containers/layout';
import { Component } from 'react';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

class App extends Component {

  render = () => (
    <BrowserRouter>
      <Helmet
        titleTemplate="Quản lí tuyển sinh"
        defaultTitle="Quản lí tuyển sinh"
      >
      <meta name="description" content="Online public service application" />
      </Helmet>
      <GuardProvider guards={[AuthGuard]} >
        <Switch>
          <GuardedRoute path="/login" exact meta={{ auth: false }} component={Login} />
          <GuardedRoute path="/forgot-password" exact meta={{ auth: false }} component={ForgotPassword} />
          <GuardedRoute path="/error" exact meta={{ auth: false }} component={NotFound} />
          <GuardedRoute path="/" meta={{ auth: true }} render={props => <Layout {...props} />} />
        </Switch>
      </GuardProvider>
      <GlobalStyle />
      <ToastContainer hidden
        position="top-right"
        autoClose={4000}
        hideProgressBar={false}
        newestOnTop={true}
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable
        pauseOnHover
      />
    </BrowserRouter>
  );
}

export default withRouter(App);
