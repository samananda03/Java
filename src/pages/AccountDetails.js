import React, { useState, useEffect } from 'react';
import axios from 'axios';

const AccountDetails = () => {
  const [userData, setUserData] = useState(null);
  const [email, setEmail] = useState(""); // Make sure you have the logged-in user's email

  useEffect(() => {
    // Fetch user data from the backend
    const storedUser = JSON.parse(localStorage.getItem('user'));
    if (storedUser) {
      setEmail(storedUser.email); // Set the email from the stored user data
    }
  }, []);

  useEffect(() => {
    if (email) {
      axios
        .get(`http://localhost:8080/api/account-details?email=${email}`)
        .then((response) => {
          setUserData(response.data); // Set the fetched user data
        })
        .catch((error) => console.error("Error fetching account details:", error));
    }
  }, [email]);

  if (!userData) return <div>Loading...</div>;

  return (
    <div style={{ padding: '2rem' }}>
      <h1>Account Details</h1>
      <p>Name: {userData.name}</p>
      <p>Email: {userData.email}</p>
      <p>Account Number: {userData.accountNumber}</p>
      <p>City: {userData.city}</p>
      <p>Balance: ${userData.balance}</p>
    </div>
  );
};

export default AccountDetails;
