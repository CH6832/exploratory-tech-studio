package com.example.amazonwebshopclone.service;

import com.example.amazonwebshopclone.dto.CartDTO;
import com.example.amazonwebshopclone.model.Cart;
import com.example.amazonwebshopclone.model.Product;
import com.example.amazonwebshopclone.repository.CartRepository;
import com.example.amazonwebshopclone.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.List.of;

/**
 * Service class for managing shopping cart operations.
 */
@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    /**
     * Retrieves the cart for a specific user.
     *
     * @param userId The ID of the user whose cart is to be retrieved.
     * @return An Optional containing the Cart if found, or empty if not found.
     */
    public Optional<Cart> getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    /**
     * Adds a product to a user's cart. If the cart does not exist, it is created.
     *
     * @param userId    The ID of the user.
     * @param productId The ID of the product to be added.
     * @param quantity  The quantity of the product to be added.
     * @return The updated Cart.
     * @throws RuntimeException if the product is not found.
     */
    public Cart addProductToCart(Long userId, Long productId, int quantity) {
        // Retrieve or create a cart for the user
        Cart cart = cartRepository.findByUserId(userId)
                .orElse(new Cart(userId));

        // Find the product
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Add product to cart
//        Cart cartItem = cart.getItems().size()
//                .filter(item -> item.getProduct().getId().equals(productId))
//                .findFirst()
//                .orElse(null);

        Cart cartItem = null;
        if (cartItem == null) {
            // Create new CartItem if not present
            cartItem = new Cart(product, quantity);
            // cart.getItems().put(cartItem);
        } else {
            // Update quantity if CartItem already exists
            // cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }

        // Save the cart
        return cartRepository.save(cart);
    }

    /**
     * Updates the quantity of a product in a user's cart.
     *
     * @param userId    The ID of the user.
     * @param productId The ID of the product to be updated.
     * @param quantity  The new quantity of the product.
     * @return The updated Cart.
     * @throws RuntimeException if the product or cart is not found.
     */
    public Cart updateProductQuantityInCart(Long userId, Long productId, int quantity) {
        // Retrieve the cart for the user
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        // Find the CartItem
        int cartItem = cart.getItems().size();

        // Update quantity
        // cartItem.setQuantity(quantity);

        // Save the updated cart
        return cartRepository.save(cart);
    }

    /**
     * Removes a product from a user's cart.
     *
     * @param userId    The ID of the user.
     * @param productId The ID of the product to be removed.
     * @return The updated Cart.
     * @throws RuntimeException if the cart or product is not found.
     */
    public Cart removeProductFromCart(Long userId, Long productId) {
        // Retrieve the cart for the user
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        // Find and remove the CartItem
        int cartItem = cart.getItems().size();

        cart.getItems().remove(cartItem);

        // Save the updated cart
        return cartRepository.save(cart);
    }

    public Cart addProductToCart(CartDTO cartDTO) {
        return null;
    }

    public Cart updateProductQuantity(CartDTO cartDTO) {
        return null;
    }

    public List<Product> getCartItems(Long userId) {
        return of();
    }
}
