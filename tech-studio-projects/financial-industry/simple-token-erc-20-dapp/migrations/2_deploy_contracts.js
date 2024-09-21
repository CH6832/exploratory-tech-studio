const SimpleToken = artifacts.require("SimpleToken");

module.exports = function (deployer) {
    deployer.deploy(SimpleToken, web3.utils.toWei('1000000', 'ether'));
};
