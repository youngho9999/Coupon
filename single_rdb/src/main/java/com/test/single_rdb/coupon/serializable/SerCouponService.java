package com.test.single_rdb.coupon.serializable;

import com.test.single_rdb.coupon.Coupon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SerCouponService {

    private final SerCouponRepository couponRepository;

    @Transactional
    public void getCoupon(Long id) {
        Coupon coupon = couponRepository.findById(id).orElseThrow(RuntimeException::new);

        if(coupon.getQuantity() <= 0) {
            throw new RuntimeException();
        }

        coupon.getCoupon();
    }
}
