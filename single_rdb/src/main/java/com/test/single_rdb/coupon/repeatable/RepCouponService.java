package com.test.single_rdb.coupon.repeatable;

import com.test.single_rdb.coupon.Coupon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RepCouponService {

    private final RepCouponRepository couponRepository;

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void getCoupon(Long id) {
        Coupon coupon = couponRepository.findByIdForUpdate(id).orElseThrow(RuntimeException::new);

        if(coupon.getQuantity() <= 0) {
            throw new RuntimeException();
        }

        coupon.getCoupon();
    }
}
