import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './components/App';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Route, RouterProvider, createHashRouter, createRoutesFromElements } from 'react-router-dom';

const root = ReactDOM.createRoot(
    document.getElementById('react')
);

const router = createHashRouter(
    createRoutesFromElements(
        <React.Fragment>
            <Route path="/*" element={<App />} />
        </React.Fragment>       
    )
)

root.render(
    <React.StrictMode>
        <RouterProvider router={router} />
    </React.StrictMode>
)