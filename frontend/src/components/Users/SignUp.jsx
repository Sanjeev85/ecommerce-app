import { Alert, Button, TextField, Typography } from '@mui/material';
import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import Card from '../Card';

function SignUp() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [name, setName] = useState('');
  const [serverMessage, setSetserverMessage] = useState('');

  const handleUsernameChange = (e) => {
    setUsername(e.target.value);
  };

  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
  };

  const handleNameChange = (e) => {
    setName(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    // Create an object with the form data
    const formData = {
      email: username,
      password: password,
      name: name,
    };

    // Make a POST request to the dummy URL
    fetch('http://localhost:8080/users/signup', {
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
        setSetserverMessage(data.message);
        // return <p>{data.message}</p>;
      })
      .catch((error) => {
        // Handle any errors that occurred during the request
        console.error(`error ${error}`);
      });

    setUsername('');
    setPassword('');
  };

  return (
    <Card>
      <form
        onSubmit={handleSubmit}
        style={{ display: 'flex', flexDirection: 'column' }}>
        <Typography>
          <h2 style={{ display: 'flex', justifyContent: 'center' }}>
            Register User
          </h2>
        </Typography>
        <TextField
          type='text'
          id='name'
          label='Name'
          value={name}
          onChange={handleNameChange}
          required
          style={{
            margin: '5px',
          }}
        />
        <TextField
          type='text'
          id='username'
          label='Email'
          value={username}
          onChange={handleUsernameChange}
          style={{
            margin: '5px',
          }}
          required
        />
        <TextField
          variant='outlined'
          label='Password'
          type='password'
          style={{
            margin: '5px',
          }}
          id='password'
          value={password}
          onChange={handlePasswordChange}
          required
        />

        <Button
          style={{ margin: '5px' }}
          variant='contained'
          type='submit'>
          <Typography>Sign Up</Typography>
        </Button>
        <Link to={`../Login`}> Already have an account</Link>
      </form>
      {serverMessage.toString().length > 1 ? (
        <Alert severity='info'>{serverMessage}</Alert>
      ) : (
        <></>
      )}
    </Card>
  );
}

export default SignUp;
