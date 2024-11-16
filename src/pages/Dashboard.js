import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './Dashboard.css'; // Updated styling file

const Dashboard = () => {
  const [userData, setUserData] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    const storedUser = JSON.parse(localStorage.getItem('user'));
    
    if (storedUser) {
      setUserData(storedUser);
    } else {
      navigate('/login');
    }
  }, [navigate]);

  if (!userData) return <div>Loading...</div>;

  return (
    <div className="dashboard-container">
      <h1 className="dashboard-title">Welcome, {userData.name}</h1>
      
      <div className="account-details">
        <h2>Account Details</h2>
        <div className="account-info">
          <p><strong>Email:</strong> {userData.email}</p>
          <p><strong>Account Number:</strong> {userData.accountNumber}</p>
          <p><strong>City:</strong> {userData.city}</p>
          <h3><strong>Account Balance:</strong> ${userData.balance}</h3>
        </div>
      </div>

      <div className="transaction-section">
        <h2>Recent Transactions</h2>
        <ul>
          {userData.transactions && userData.transactions.length > 0 ? (
            userData.transactions.map((transaction, index) => (
              <li key={index} className="transaction-item">
                {transaction.details} - <span className="transaction-amount">${transaction.amount}</span>
              </li>
            ))
          ) : (
            <p>No recent transactions.</p>
          )}
        </ul>
      </div>
    </div>
  );
};

export default Dashboard;
