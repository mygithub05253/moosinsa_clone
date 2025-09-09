package me.gachon.moosinsa_clone.Controller.Cart;

import lombok.RequiredArgsConstructor;
import me.gachon.moosinsa_clone.Dto.Cart.CartListResponse;
import me.gachon.moosinsa_clone.Service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;

    @GetMapping("/")
    public ResponseEntity<List<CartListResponse>> getCart() {
        return ResponseEntity.ok()
                .body(cartService.getAllUserCart());
    }
}
