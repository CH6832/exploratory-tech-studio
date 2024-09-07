package com.example.amazonwebshopclone.controller;

import com.example.amazonwebshopclone.dto.CartDTO;
import com.example.amazonwebshopclone.model.Cart;
import com.example.amazonwebshopclone.model.Product;
import com.example.amazonwebshopclone.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * CartController handles operations related to the user's shopping cart,
 * including adding, updating, and removing items from the cart.
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    @Autowired
    private CartService cartService;

    /**
     * Adds a product to the cart.
     *
     * @param cartDTO Data transfer object containing cart details.
     * @return ResponseEntity containing the updated cart.
     */
    @PostMapping("/add")
    public ResponseEntity<Cart> addProductToCart(@RequestBody CartDTO cartDTO) {
        logger.info("Adding product to cart: {}", cartDTO.getProductId());
        Cart updatedCart = cartService.addProductToCart(cartDTO);
        return new ResponseEntity<>(updatedCart, HttpStatus.OK);
    }

    /**
     * Updates the quantity of a product in the cart.
     *
     * @param cartDTO Data transfer object containing updated cart details.
     * @return ResponseEntity containing the updated cart.
     */
    @PutMapping("/update")
    public ResponseEntity<Cart> updateProductQuantity(@RequestBody CartDTO cartDTO) {
        logger.info("Updating product quantity in cart: {}", cartDTO.getProductId());
        Cart updatedCart = cartService.updateProductQuantity(cartDTO);
        return new ResponseEntity<>(updatedCart, HttpStatus.OK);
    }

    /**
     * Removes a product from the cart.
     *
     * @param productId ID of the product to be removed.
     * @param userId ID of the user whose cart is being modified.
     * @return ResponseEntity with the status of the operation.
     */
    @DeleteMapping("/remove/{userId}/{productId}")
    public ResponseEntity<Void> removeProductFromCart(@PathVariable Long userId, @PathVariable Long productId) {
        logger.info("Removing product with ID {} from user {}'s cart", productId, userId);
        cartService.removeProductFromCart(userId, productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Fetches the current cart for a user.
     *
     * @param userId ID of the user.
     * @return ResponseEntity containing the cart details.
     */
    @GetMapping("/{userId}")
    public ResponseEntity<Optional<Cart>> getCartByUserId(@PathVariable Long userId) {
        logger.info("Fetching cart for user ID: {}", userId);
        Optional<Cart> cart = cartService.getCartByUserId(userId);
        return new ResponseEntity<Optional<Cart>>(cart, HttpStatus.OK);
    }

    /**
     * Retrieves all items in a user's cart.
     *
     * @param userId ID of the user.
     * @return ResponseEntity containing the list of products in the cart.
     */
    @GetMapping("/items/{userId}")
    public ResponseEntity<List<Product>> getCartItems(@PathVariable Long userId) {
        logger.info("Fetching all items in cart for user ID: {}", userId);
        List<Product> products = cartService.getCartItems(userId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
