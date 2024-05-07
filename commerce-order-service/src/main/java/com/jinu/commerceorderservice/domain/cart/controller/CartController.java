package com.jinu.commerceorderservice.domain.cart.controller;

import com.jinu.commercecommon.dto.ResponseBodyDto;
import com.jinu.commerceorderservice.domain.cart.dto.request.CartItemEditRequestDto;
import com.jinu.commerceorderservice.domain.cart.dto.request.CartRequestDto;
import com.jinu.commerceorderservice.domain.cart.dto.response.CartItemResponseDto;
import com.jinu.commerceorderservice.domain.cart.entity.Cart;
import com.jinu.commerceorderservice.domain.cart.entity.CartItem;
import com.jinu.commerceorderservice.domain.cart.service.CartDetailService;
import com.jinu.commerceorderservice.domain.cart.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final CartDetailService cartDetailService;

    @PostMapping()
    public ResponseEntity<ResponseBodyDto> addCartItem(@RequestBody List<CartRequestDto> requestDtoList,
                                                       HttpServletRequest req) {
        Cart cart = this.cartService.getCart(req);
        this.cartDetailService.addCartItems(cart, requestDtoList);

        return ResponseEntity.ok(ResponseBodyDto.success("장바구니 등록"));
    }

    @GetMapping()
    public ResponseEntity<ResponseBodyDto> getAllOrders(HttpServletRequest req) {
        Cart cart = this.cartService.getCart(req);

        List<CartItem> cartItemList = this.cartDetailService.getAllCartItemsByCart(cart);
        List<CartItemResponseDto> responseDtoList = CartItemResponseDto.crateCartItemsIntoResponseDtoList(cartItemList);

        return ResponseEntity.ok(ResponseBodyDto.successWithResult("전체조회 완료", responseDtoList));
    }

    @PatchMapping("/edit")
    public ResponseEntity<ResponseBodyDto> editCartItemQty(@RequestBody CartItemEditRequestDto requestDto) {
        this.cartDetailService.editCartItemQty(requestDto);

        return ResponseEntity.ok(ResponseBodyDto.success("수량변경 완료"));
    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<ResponseBodyDto> deleteCartItem(@PathVariable(name = "cartItemId") Long cartItemId) {
        this.cartDetailService.deleteCartItemById(cartItemId);

        return ResponseEntity.ok(ResponseBodyDto.success("삭제완료"));
    }
}