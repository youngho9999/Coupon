package com.test.single_rdb.coupon.repeatable;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/repeatable/coupon")
@RequiredArgsConstructor
public class RepCouponController {

    private final RepCouponService couponService;

    @GetMapping("/{id}")
    public String getCoupon(@PathVariable Long id) {
        couponService.getCoupon(id);
        return "coupon";
    }
}
