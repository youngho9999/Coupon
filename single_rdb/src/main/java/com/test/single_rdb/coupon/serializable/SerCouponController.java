package com.test.single_rdb.coupon.serializable;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/serializable/coupon")
@RequiredArgsConstructor
public class SerCouponController {

    private final SerCouponService couponService;

    @GetMapping("/{id}")
    public String getCoupon(@PathVariable Long id) {
        couponService.getCoupon(id);
        return "coupon";
    }
}
