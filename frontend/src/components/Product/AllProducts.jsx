import React, { useEffect, useState } from 'react';
import SingleProduct from './SingleProduct';
import { Typography } from '@mui/material';

function AllProducts() {
  const [AllProducts, setAllProducts] = useState([]);

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const response = await fetch(`http://localhost:8080/product/`);
        const data = await response.json();
        console.log(data);
        setAllProducts((AllProducts) => data);
      } catch (err) {
        console.log(err);
      }
    };
    fetchProducts();
  }, []);

  return (
    <div>
      <Typography
        variant='h3'
        fontFamily={'Roboto'}
        style={{ display: 'flex', justifyContent: 'center' }}>
        All Products
      </Typography>
      <div style={{ display: 'flex', flexWrap: 'wrap' }}>
        {AllProducts.map((product) => {
          return (
            <div
              key={product.id}
              style={{ margin: '10px' }}>
              <SingleProduct product={product} />
            </div>
          );
        })}
      </div>
    </div>
  );
}

export default AllProducts;
