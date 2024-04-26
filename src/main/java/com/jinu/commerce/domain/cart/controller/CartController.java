package com.jinu.commerce.domain.cart.controller;

import com.jinu.commerce.domain.cart.dto.request.CartRequestDto;
import com.jinu.commerce.domain.cart.dto.request.CartItemEditRequestDto;
import com.jinu.commerce.domain.cart.dto.response.CartItemResponseDto;
import com.jinu.commerce.domain.cart.entity.Cart;
import com.jinu.commerce.domain.cart.entity.CartItem;
import com.jinu.commerce.domain.cart.service.CartDetailService;
import com.jinu.commerce.domain.cart.service.CartService;
import com.jinu.commerce.global.dto.ResponseBodyDto;
import com.jinu.commerce.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final CartDetailService cartDetailService;
    private final ResponseBodyDto responseBodyDto;

    @PostMapping()
    public ResponseEntity<ResponseBodyDto> addCartItem(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                       @RequestBody List<CartRequestDto> requestDtos) {
        Cart cart = this.cartService.getCart(userDetails);
        this.cartDetailService.addCartItems(cart, requestDtos);

        return ResponseEntity.ok(responseBodyDto.success("장바구니 등록"));
    }

    @GetMapping()
    public ResponseEntity<ResponseBodyDto> getAllOrders(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Cart cart = this.cartService.getCart(userDetails);

        List<CartItem> cartItems = this.cartDetailService.getAllCartItemsByCart(cart);
        List<CartItemResponseDto> responseDtos = CartItemResponseDto.crateCartItemsIntoResponseDtos(cartItems);

        return ResponseEntity.ok(responseBodyDto.successWithResult("전체조회 완료", responseDtos));
    }

    @PatchMapping("/edit")
    public ResponseEntity<ResponseBodyDto> editCartItemQty(@RequestBody CartItemEditRequestDto requestDto) {
        this.cartDetailService.editCartItemQty(requestDto);

        return ResponseEntity.ok(responseBodyDto.success("수량변경 완료"));
    }

    /*@PatchMapping("/return/{orderId}")
    public ResponseEntity<ResponseBodyDto> returnOrder(@PathVariable(name = "orderId") Long orderId) {
        this.cartService.changeStatusToReturn(orderId);

        return ResponseEntity.ok(responseBodyDto.success("반품완료"));
    }*/
}