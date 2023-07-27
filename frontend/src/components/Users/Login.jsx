import { Alert, Button, TextField, Typography } from '@mui/material';
import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import Card from '../Card';

function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [token, setToken] = useState('');

  const handleUsernameChange = (e) => {
    setUsername(e.target.value);
  };

  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    const formData = {
      email: username,
      password: password,
    };
    console.log(formData);

    // Make a POST request to the dummy URL
    fetch('http://localhost:8080/users/signIn', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(formData),
    })
      .then((response) => response.json())
      .then((data) => {
        // Handle the response from the server
        console.log(data);
        localStorage.setItem('token', data.token);
        setToken(data.token);
      })
      .catch((error) => {
        // Handle any errors that occurred during the request
        console.error(error);
      });

    setUsername('');
    setPassword('');
  };

  return (
    <Card>
      <form
        onSubmit={handleSubmit}
        style={{ display: 'flex', flexDirection: 'column' }}>
        <Typography style={{ display: 'flex', justifyContent: 'center' }}>
          <h2>Sign In</h2>
        </Typography>
        <TextField
          type='text'
          id='username'
          label='Username'
          style={{ margin: '5px' }}
          value={username}
          onChange={handleUsernameChange}
          required
        />
        <TextField
          label='Password'
          type='password'
          id='password'
          style={{ margin: '5px' }}
          value={password}
          onChange={handlePasswordChange}
          required
        />

        <Button
          style={{ margin: '5px' }}
          variant='contained'
          type='submit'>
          <Typography>Sign In</Typography>
        </Button>
        <Link to={`../SignUp`}>New User</Link>
        {token ? <Alert severity='info'>{token}</Alert> : <></>}
      </form>
    </Card>
  );
}

export default Login;
