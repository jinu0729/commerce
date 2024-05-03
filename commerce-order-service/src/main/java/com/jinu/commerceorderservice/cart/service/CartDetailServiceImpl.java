package com.jinu.commerceorderservice.cart.service;

import com.jinu.commercecommon.exception.CustomException;
import com.jinu.commercecommon.exception.ErrorCode;
import com.jinu.commerceorderservice.cart.dto.request.CartItemEditRequestDto;
import com.jinu.commerceorderservice.cart.dto.request.CartRequestDto;
import com.jinu.commerceorderservice.cart.entity.Cart;
import com.jinu.commerceorderservice.cart.entity.CartItem;
import com.jinu.commerceorderservice.cart.repository.CartDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j(topic = "CartDetailServiceImpl")
@Service
@RequiredArgsConstructor
public class CartDetailServiceImpl implements CartDetailService {
    private final CartDetailRepository repository;

    @Override
    @Transactional
    public void addCartItems(Cart cart, List<CartRequestDto> requestDtos) {
        log.info("장바구니 아이템 생성");

        List<CartItem> cartItems = new ArrayList<>();

        for (CartRequestDto requestDto : requestDtos) {
            CartItem cartItem = CartItem.builder()
                    .cart(cart)
                    .product(requestDto.getProduct())
                    .qty(requestDto.getQty())
                    .build();

            cartItems.add(cartItem);
        }

        this.repository.saveAll(cartItems);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CartItem> getAllCartItemsByCart(Cart cart) {
        log.info("장바구니 조회");

        return this.repository.findAllByCart(cart);
    }

    @Override
    @Transactional
    public void editCartItemQty(CartItemEditRequestDto requestDto) {
        log.info("장바구니 아이템 수량 변경");

        CartItem cartItem = this.getCartItemById(requestDto.getCartItemId());
        cartItem.editQty(requestDto.getQty());
    }

    @Override
    @Transactional
    public void deleteCartItemById(Long cartItemId) {
        log.info("장바구나 이이템 삭제");

        this.repository.deleteById(cartItemId);
    }

    @Override
    @Transactional(readOnly = true)
    public CartItem getCartItemById(Long cartItemId) {
        log.info("장바구나 이이템 아이디로 조회");

        return this.repository.findById(cartItemId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ORDER));
    }
}