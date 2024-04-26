package com.jinu.commerce.domain.cart.service;

import com.jinu.commerce.domain.cart.entity.Cart;
import com.jinu.commerce.domain.cart.repository.CartRepository;
import com.jinu.commerce.domain.user.entity.User;
import com.jinu.commerce.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j(topic = "CartServiceImpl")
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository repository;

    @Override
    @Transactional
    public Cart createCart(User user) {
        log.info("장바구니 생성");

        Cart cart = Cart.builder()
                .user(user)
                .build();

        this.repository.save(cart);

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Cart getCart(UserDetailsImpl userDetails) {
        log.info("장바구니 가져오기");

        User user = userDetails.getUser();

        return this.repository.findByUser(user)
                .orElseGet(() -> this.createCart(user));
    }

    /*

    @Override
    @Transactional(readOnly = true)
    public List<Cart> getAllOrders(UserDetailsImpl userDetails) {
        log.info("주문 전체조회");

        return this.repository.findAllByUser(userDetails.getUser());
    }

    @Override
    @Transactional(readOnly = true)
    public Cart getOrderByOrderId(Long orderId) {
        log.info("orderId로 주문 조회");

        return this.repository.findById(orderId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ORDER));
    }

    @Override
    @Transactional
    public void changeStatusToCancel(Long orderId) {
        log.info("주문취소");

        Cart cart = this.getOrderByOrderId(orderId);

        if (cart.getStatus().equals("주문완료")) {
            cart.updateStatus("주문취소");
            return;
        }

        throw new CustomException(ErrorCode.CAN_NOT_CANCEL);
    }

    @Override
    @Transactional
    public void changeStatusToReturn(Long orderId) {
        log.info("주문취소");

        Cart cart = this.getOrderByOrderId(orderId);

        if (cart.getStatus().equals("배송중") || cart.getStatus().equals("배송완료")) {
            cart.updateStatus("반품완료");
            return;
        }

        throw new CustomException(ErrorCode.CAN_NOT_RETURN);
    }*/
}