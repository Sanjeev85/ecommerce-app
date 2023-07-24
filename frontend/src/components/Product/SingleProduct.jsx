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
          image='./images/image.jpg'
          alt='green iguana'
        />
        <CardContent>
          <Typography
            gutterBottom
            variant='h5'
            component='div'>
            {product.name}
            {/* dummy Product */}
          </Typography>
          <Typography
            variant='body2'
            color='text.secondary'>
            {/* Lizards are a widespread group of squamate reptiles, with over 6,000
            species, ranging across all continents except Antarctica */}
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
