import random
import time

class OrderExecution:
    """
    Simulates the order execution system for placing and managing trades.
    
    Attributes:
        order_id (str): A unique identifier for each order.
        symbol (str): The trading symbol for the order (e.g., 'AAPL').
        order_type (str): The type of order ('market' or 'limit').
        quantity (int): The number of shares/contracts to trade.
        price (float): The price at which to execute the trade (relevant only for limit orders).
        status (str): The current status of the order ('pending', 'executing', 'completed', 'cancelled').
    """

    def __init__(self):
        """
        Initializes the OrderExecution with no active orders.
        """
        self.order_id = None
        self.symbol = None
        self.order_type = None
        self.quantity = None
        self.price = None
        self.status = 'pending'
        self.execution_time = None

    def place_order(self, symbol, order_type, quantity, price=None):
        """
        Places an order (either market or limit).
        
        Args:
            symbol (str): The trading symbol for the order (e.g., 'AAPL').
            order_type (str): The type of order ('market' or 'limit').
            quantity (int): The number of shares/contracts to trade.
            price (float, optional): The price at which to execute the trade (relevant only for limit orders).
        
        Returns:
            dict: A dictionary containing order details and status.
        """
        self.order_id = f"ORD{random.randint(1000, 9999)}"
        self.symbol = symbol
        self.order_type = order_type
        self.quantity = quantity
        self.price = price
        self.status = 'pending'

        print(f"Placing {order_type} order for {quantity} shares of {symbol}...")

        # Simulate order processing delay
        time.sleep(2)

        # Randomly simulate whether the order is successfully placed or not
        if random.choice([True, False]):
            self.status = 'executing'
            self.execution_time = time.strftime('%Y-%m-%d %H:%M:%S', time.gmtime())
            print(f"Order {self.order_id} placed successfully. Executing now...")
            return self._execute_order()
        else:
            self.status = 'failed'
            print(f"Failed to place order {self.order_id}.")
            return self._get_order_details()

    def _execute_order(self):
        """
        Simulate the execution of an order (either market or limit).
        
        Returns:
            dict: A dictionary containing the order details after execution.
        """
        # Simulate some time to execute the order
        time.sleep(2)

        # Simulate success or failure of order execution
        if random.choice([True, False]):
            self.status = 'completed'
            print(f"Order {self.order_id} executed successfully.")
        else:
            self.status = 'failed'
            print(f"Order {self.order_id} execution failed.")

        return self._get_order_details()

    def cancel_order(self):
        """
        Simulate the cancellation of an order.
        
        Returns:
            dict: A dictionary containing the order status after cancellation.
        """
        if self.status == 'completed':
            print(f"Order {self.order_id} has already been completed. Cannot cancel.")
        elif self.status == 'cancelled':
            print(f"Order {self.order_id} has already been cancelled.")
        else:
            self.status = 'cancelled'
            print(f"Order {self.order_id} cancelled successfully.")

        return self._get_order_details()

    def _get_order_details(self):
        """
        Returns the current details of the order.
        
        Returns:
            dict: A dictionary containing the current order details.
        """
        return {
            "order_id": self.order_id,
            "symbol": self.symbol,
            "order_type": self.order_type,
            "quantity": self.quantity,
            "price": self.price,
            "status": self.status,
            "execution_time": self.execution_time
        }
