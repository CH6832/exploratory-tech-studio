const Web3 = require('web3');
const IPFS = require('ipfs-http-client');
const web3 = new Web3(Web3.givenProvider || 'http://localhost:7545');
const ipfs = IPFS.create({ url: 'https://ipfs.infura.io:5001/api/v0' });

let contract;
const contractAddress = 'YOUR_CONTRACT_ADDRESS'; // Replace with your deployed contract address
const abi = [ /* ABI from compiled contract */ ];

window.addEventListener('load', async () => {
    if (window.ethereum) {
        window.web3 = new Web3(window.ethereum);
        await window.ethereum.enable();
    }

    contract = new web3.eth.Contract(abi, contractAddress);

    const accounts = await web3.eth.getAccounts();
    document.getElementById('account').textContent = accounts[0];

    updateBalance(accounts[0]);

    document.getElementById('sendTokens').addEventListener('click', async () => {
        const recipient = document.getElementById('recipient').value;
        const amount = document.getElementById('amount').value;
        await contract.methods.transfer(recipient, web3.utils.toWei(amount, 'ether')).send({ from: accounts[0] });
        updateBalance(accounts[0]);
    });
});

async function updateBalance(account) {
    const balance = await contract.methods.balanceOf(account).call();
    document.getElementById('balance').textContent = web3.utils.fromWei(balance, 'ether');
}

async function uploadToIPFS(file) {
    try {
        const result = await ipfs.add(file);
        console.log('IPFS Hash:', result.path);
        return result.path;
    } catch (error) {
        console.error('Error uploading file to IPFS:', error);
    }
}
