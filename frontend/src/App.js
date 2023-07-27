import { Link } from 'react-router-dom';

function App() {
  return (
    <div className='App'>
      <Link
        to={`SignUp`}
        style={{ margin: '5px' }}>
        Sign Up
      </Link>
      <Link
        to={`Login`}
        style={{ margin: '5px' }}>
        Log In
      </Link>
      <Link
        to={`products/single`}
        style={{ margin: '5px' }}>
        Single Product
      </Link>
      <Link
        to={`products/add`}
        style={{ margin: '5px' }}>
        Add Product
      </Link>
      <Link
        to={`products`}
        style={{ margin: '5px' }}>
        Products Page
      </Link>
      <Link
        to={`view`}
        style={{ margin: '5px' }}>
        View Product
      </Link>
    </div>
  );
}
export default App;
