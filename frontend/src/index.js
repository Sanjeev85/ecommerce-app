import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import { RouterProvider } from 'react-router';
import { createBrowserRouter } from 'react-router-dom';
import SignUp from './components/Users/SignUp';
import Login from './components/Users/Login';
import SingleProduct from './components/Product/SingleProduct';
import AddProduct from './components/Admin/AddProduct';
import AllProducts from './components/Product/AllProducts';
import ViewProduct from './components/ProductPage/ViewProduct';

const router = createBrowserRouter([
  {
    path: '/',
    element: <App />,
  },
  {
    path: '/SignUp',
    element: <SignUp />,
  },
  {
    path: '/LogIn',
    element: <Login />,
  },
  {
    path: '/products/single',
    element: <SingleProduct />,
  },
  {
    path: '/products/add',
    element: <AddProduct />,
  },
  {
    path: '/products',
    element: <AllProducts />,
  },
  {
    path: '/view',
    element: <ViewProduct />,
  },
]);

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);
