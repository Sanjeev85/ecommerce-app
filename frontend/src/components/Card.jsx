import React from 'react';

function Card({ children }) {
  return (
    <div
      style={{
        backgroundColor: '#f0f0f0',
        padding: '20px',
        borderRadius: '5px',
        boxShadow: '0 2px 4px rgba(0, 0, 0, 0.1)',
        display: 'flex',
        minHeight: '100vh',
        alignItems: 'space-around',
        justifyContent: 'center',
      }}>
      {children}
    </div>
  );
}

export default Card;
