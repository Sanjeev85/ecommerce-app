import {
  Button,
  Card,
  CardActionArea,
  CardContent,
  CardMedia,
  Divider,
  Typography,
} from '@mui/material';
import React from 'react';

function SingleProduct({ product }) {
  return (
    <Card sx={{ maxWidth: 345 }}>
      <CardActionArea>
        <CardMedia
          component='img'
          height='140'
          image={product.imageURL}
          alt='green iguana'
        />
        <CardContent>
          <Typography
            gutterBottom
            variant='h5'
            component='div'>
            {product.name}
          </Typography>
          <Typography
            variant='body2'
            color='text.secondary'>
            {product.description}
          </Typography>
        </CardContent>
        <CardContent>
          <Typography
            gutterBottom
            color='#0492C2'
            sx={{ fontSize: '15px' }}>
            {/* ₹156 */}₹{product.price}
          </Typography>
        </CardContent>
      </CardActionArea>
      <Divider
        gutterBottom
        sx={{ margin: '10px' }}
      />
      <Button
        variant='contained'
        sx={{ margin: '5px' }}>
        Buy Now
      </Button>
      <Button
        variant='text'
        sx={{ margin: '5px' }}>
        Add To Cart
      </Button>
    </Card>
  );
}

export default SingleProduct;
