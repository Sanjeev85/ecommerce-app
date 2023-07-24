import {
  Autocomplete,
  Button,
  Card,
  Input,
  OutlinedInput,
  TextField,
  Typography,
} from '@mui/material';
import React, { useEffect, useState } from 'react';
import Login from '../Users/Login';

function AddProduct() {
  const [categories, setCategories] = useState([]);
  const [categoryId, setCategoryId] = useState([{}]);
  const [formData, setFormData] = useState({
    id: '',
    name: '',
    imageUrl: '',
    price: 0,
    description: '',
    category: '',
  });
  const token = localStorage.getItem('token');

  useEffect(() => {
    const fetchData = async () => {
      const res = await fetch('http://localhost:8080/category/');

      try {
        const jsonData = await res.json();

        const updated_category = [];
        const category_map = [{}];
        jsonData.forEach((element) => {
          updated_category.push(element.categoryName);
          category_map.push({ category: element.categoryName, id: element.id });
        });
        setCategories((categories) => updated_category);
        setCategoryId((categoryId) => category_map);
      } catch (err) {
        console.log(err);
      }
    };

    fetchData();
  }, []);

  if (!token) return <Login />;

  const handleChange = (event) => {
    const { name, value } = event.target;
    setFormData((prevFormData) => ({
      ...prevFormData,
      [name]: value,
    }));
  };

  // request making to localhost:8080
  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(e);
    // setFormData(e.);
  };

  return (
    <form onSubmit={handleSubmit}>
      <Card
        style={{
          display: 'flex',
          flexDirection: 'column',
          margin: '80px',
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
          value={formData.name}
        />
        <TextField
          name='description'
          label='Product Description'
          value={formData.description}
          sx={{ margin: '5px' }}
          onChange={handleChange}
        />
        <Autocomplete
          disablePortal
          id='combo-box-demo'
          options={categories}
          sx={{ margin: '5px' }}
          value={formData.category}
          renderInput={(params) => (
            <TextField
              {...params}
              label='Category'
            />
          )}
          name='category'
          onChange={handleChange}
        />

        <OutlinedInput
          sx={{ margin: '5px' }}
          type='file'
          label='Product'
          name='imageUrl'
          onChange={handleChange}
          value={formData.imageUrl}
        />
        <TextField
          sx={{ margin: '5px' }}
          label='Price ($)'
          type='number'
          name='price'
          onChange={handleChange}
          value={formData.price}
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
