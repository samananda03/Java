import React, { useState, useEffect } from 'react';
import axios from 'axios';

const Transactions = () => {
  const [transactions, setTransactions] = useState([]);
  const [amount, setAmount] = useState('');
  const [type, setType] = useState('deposit'); // 'deposit' or 'withdrawal'
  const [details, setDetails] = useState('');
  const [userId, setUserId] = useState(null);
  const [balance, setBalance] = useState(0); // To track user balance
  const [error, setError] = useState(null); // For handling errors

  useEffect(() => {
    // Fetch user data (could be from localStorage or API)
    const storedUser = JSON.parse(localStorage.getItem('user'));
    setUserId(storedUser ? storedUser.id : null);

    if (storedUser && storedUser.id) {
      // Fetch user's transactions
      axios
        .get(`http://localhost:8080/api/transactions?userId=${storedUser.id}`)
        .then(response => {
          setTransactions(response.data);
        })
        .catch(err => {
          setError('Error fetching transactions.');
          console.error(err);
        });

      // Fetch user's balance
      axios
        .get(`http://localhost:8080/api/user-balance?userId=${storedUser.id}`)
        .then(response => {
          setBalance(response.data);  // Balance is returned directly as a number
        })
        .catch(err => {
          setError('Error fetching balance.');
          console.error(err);
        });
    }
  }, []);

  const handleTransactionSubmit = (e) => {
    e.preventDefault();

    // Validate amount (for withdrawal, ensure sufficient balance)
    if (type === 'withdrawal' && parseFloat(amount) > balance) {
      setError('Insufficient balance for withdrawal.');
      return;
    }

    if (parseFloat(amount) <= 0) {
      setError('Amount must be greater than 0.');
      return;
    }

    const transaction = {
      amount: parseFloat(amount),
      type,
      details,
    };

    axios
      .post(`http://localhost:8080/api/add-transaction?userId=${userId}`, transaction)
      .then(response => {
        // Add the new transaction to the list and update the balance if necessary
        setTransactions([...transactions, response.data]);
        setBalance(balance + (type === 'deposit' ? parseFloat(amount) : -parseFloat(amount))); // Update balance
        setAmount('');
        setDetails('');
        setError(null); // Clear error
      })
      .catch(err => {
        setError('Error adding transaction.');
        console.error('Error adding transaction:', err);
      });
  };

  return (
    <div>
      <h1>Recent Transactions</h1>
      {error && <p style={{ color: 'red' }}>{error}</p>}

      <ul>
        {transactions.length ? (
          transactions.map((transaction, index) => {
            return (
              <li key={index}>
                {transaction.details} - ${transaction.amount} ({transaction.type})
                <br />
                {/* Format the date */}
              </li>
            );
          })
        ) : (
          <p>No transactions found.</p>
        )}
      </ul>

      <h2>Add a New Transaction</h2>
      <form onSubmit={handleTransactionSubmit}>
        <div>
          <label>Amount: </label>
          <input
            type="number"
            value={amount}
            onChange={(e) => setAmount(e.target.value)}
            required
            min="0.01"
          />
        </div>
        <div>
          <label>Type: </label>
          <select
            value={type}
            onChange={(e) => setType(e.target.value)}
            required
          >
            <option value="deposit">Deposit</option>
            <option value="withdrawal">Withdrawal</option>
          </select>
        </div>
        <div>
          <label>Details: </label>
          <input
            type="text"
            value={details}
            onChange={(e) => setDetails(e.target.value)}
            required
          />
        </div>
        <button type="submit">Submit Transaction</button>
      </form>

      <h3>Current Balance: ${balance}</h3>
    </div>
  );
};

export default Transactions;
