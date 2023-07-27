import { Box, Container, Button, Stack, Typography } from '@mui/material';
import React from 'react';

function ViewProduct({ product_desc, price }) {
  const buyProduct = (e) => {
    e.preventDefault();
  };

  return (
    <Container sx={{ display: 'flex', margin: '5rem' }}>
      <Box>
        <img
          src='/images/image.jpg'
          style={{
            height: '309px',
            width: '309px',
            borderRadius: '8px',
          }}></img>
      </Box>
      <Stack sx={{ marginLeft: '100px' }}>
        <Typography sx={{ margin: '10px' }}>
          Header
          {product_desc}
        </Typography>
        <Typography sx={{ margin: '10px' }}>
          Price
          {price}
        </Typography>
        <Box sx={{ margin: '10px' }}>
          <Button
            variant='contained'
            onClick={buyProduct}>
            Buy Now
          </Button>
        </Box>
      </Stack>
    </Container>
  );
}

export default ViewProduct;
