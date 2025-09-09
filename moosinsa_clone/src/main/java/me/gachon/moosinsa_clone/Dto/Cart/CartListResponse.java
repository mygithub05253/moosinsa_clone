package me.gachon.moosinsa_clone.Dto.Cart;

import lombok.Getter;
import me.gachon.moosinsa_clone.Entity.Cart;

@Getter
public class CartListResponse {

    private Long cartId;
    private String userId;
    private Long itemId;
    private Integer itemSizeId;
    private Integer itemColorId;
    private Integer quantity;

    public CartListResponse(Cart cart) {
        this.cartId = cart.getCartId();
        this.userId = cart.getUser().getUserId();
        this.itemId = cart.getItem().getItemId();
        this.itemSizeId = cart.getItemSize().getItemSizeId();
        this.itemColorId = cart.getItemColor().getItemColorId();
        this.quantity = cart.getQuantity();
    }
}
