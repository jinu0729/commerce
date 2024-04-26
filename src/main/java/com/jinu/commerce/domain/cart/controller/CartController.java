package com.jinu.commerce.domain.cart.controller;

import com.jinu.commerce.domain.cart.dto.request.CartRequestDto;
import com.jinu.commerce.domain.cart.entity.Cart;
import com.jinu.commerce.domain.cart.service.CartDetailService;
import com.jinu.commerce.domain.cart.service.CartService;
import com.jinu.commerce.global.dto.ResponseBodyDto;
import com.jinu.commerce.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    /*@GetMapping()
    public ResponseEntity<ResponseBodyDto> getAllOrders(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<Cart> carts = this.cartService.getAllOrders(userDetails);
        List<CartResponseDto> responseDtos = CartResponseDto.crateOrdersIntoResponseDtos(carts);

        return ResponseEntity.ok(responseBodyDto.successWithResult("전체조회 완료", responseDtos));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<ResponseBodyDto> getOrderDetailsByOrder(@PathVariable(name = "orderId") Long orderId) {
        Cart cart = this.cartService.getOrderByOrderId(orderId);

        List<CartItem> cartDetails = this.cartDetailService.getOrderDetailsByOrder(cart);
        List<CartDetailResponseDto> responseDtos =
                CartDetailResponseDto.createOrderDetailsIntoOrderDetailResponseDtos(cartDetails);

        return ResponseEntity.ok(responseBodyDto.successWithResult("상세조회 완료", responseDtos));
    }

    @PatchMapping("/cancel/{orderId}")
    public ResponseEntity<ResponseBodyDto> cancelOrder(@PathVariable(name = "orderId") Long orderId) {
        this.cartService.changeStatusToCancel(orderId);

        return ResponseEntity.ok(responseBodyDto.success("취소완료"));
    }

    @PatchMapping("/return/{orderId}")
    public ResponseEntity<ResponseBodyDto> returnOrder(@PathVariable(name = "orderId") Long orderId) {
        this.cartService.changeStatusToReturn(orderId);

        return ResponseEntity.ok(responseBodyDto.success("반품완료"));
    }*/
}