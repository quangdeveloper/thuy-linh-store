import ReactDOM from 'react-dom';
import reportWebVitals from './reportWebVitals';
import './assets/css/popoverlay.css'
import React, {Suspense} from "react";

const AppComponent = React.lazy(() => import('./App'));
const Loading = () => {
    return (
        <>
            <h1 style={{textAlign: 'center', paddingTop: '35vh'}}>
                <img src={"https://www.vban.vn/Resources/images/preload.svg"} width={250}/>
            </h1>
        </>
    );
}

ReactDOM.render(
    <Suspense fallback={<Loading />}>
        <AppComponent />
    </Suspense>,
    document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
