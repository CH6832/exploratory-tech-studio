void TradeAlert::generateAlert(Order order) {
    Logger::log("Suspicious trade detected: " + order.toString());
    // Additional actions like sending notifications
}
