import {
  Autocomplete,
  Button,
  Card,
  OutlinedInput,
  TextField,
  Typography,
} from '@mui/material';
import React, { useEffect, useState } from 'react';
import Login from '../Users/Login';
import axios from 'axios';

let category_map = new Map();
function AddProduct() {
  const [categories, setCategories] = useState([]);
  const [selectedImage, setSelectedImage] = useState(null);

  const [productDto, setproductDto] = useState({
    name: '',
    price: 0,
    description: '',
    categoryId: null,
  });
  const token = localStorage.getItem('token');

  useEffect(() => {
    const fetchData = async () => {
      const res = await fetch('http://localhost:8080/category/');

      try {
        const jsonData = await res.json();

        const updated_category = [];
        // const category_map = [{}];
        jsonData.forEach((element) => {
          updated_category.push(element.categoryName);
          category_map.set(element.categoryName, element.id);
        });

        setCategories((categories) => updated_category);
      } catch (err) {
        console.log(err);
      }
    };

    fetchData();
  }, []);

  if (!token) return <Login />;
  console.log(token);

  const fileHandleChange = (event) => {
    setSelectedImage(event.target.files[0]);
  };

  const handleChange = (event) => {
    const { name, value } = event.target;
    setproductDto((prevproductDto) => ({
      ...prevproductDto,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {
    const formData = new FormData();
    formData.append('file', selectedImage);
    formData.append('productDto', JSON.stringify(productDto));

    try {
      const response = await axios.post(
        'http://localhost:8080/product/add',
        formData,
        {
          headers: {
            'Content-Type': 'multipart/form-data',
          },
        }
      );

      console.log('Response:', response.data);
    } catch (error) {
      console.error('Error:', error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <Card
        style={{
          display: 'flex',
          flexDirection: 'column',
          margin: '80px',
          borderBlockColor: 'black',
        }}>
        <Typography
          variant='h5'
          sx={{ display: 'flex', justifyContent: 'center', margin: '5px' }}>
          Add Product
        </Typography>
        <TextField
          label='Product Name'
          name='name'
          sx={{ margin: '5px' }}
          onChange={handleChange}
          value={productDto.name}
        />
        <TextField
          name='description'
          label='Product Description'
          value={productDto.description}
          sx={{ margin: '5px' }}
          onChange={handleChange}
        />

        <Autocomplete
          disablePortal
          id='combo-box-demo'
          options={categories}
          sx={{ margin: '5px' }}
          renderInput={(params) => (
            <TextField
              {...params}
              label='Categories'
            />
          )}
          name='category'
          onChange={(event, value) => {
            setproductDto((prevproductDto) => ({
              ...prevproductDto,
              categoryId: category_map.get(value),
            }));
          }}
        />

        <OutlinedInput
          sx={{ margin: '5px' }}
          type='file'
          label='Product'
          name='imageUrl'
          onChange={fileHandleChange}
          value={productDto.imageUrl}
        />
        <TextField
          sx={{ margin: '5px' }}
          label='Price ($)'
          type='number'
          name='price'
          onChange={handleChange}
          value={productDto.price}
        />
        <Button
          style={{ margin: '5px' }}
          variant='contained'
          type='submit'>
          <Typography>Add Product</Typography>
        </Button>
      </Card>
    </form>
  );
}

export default AddProduct;
