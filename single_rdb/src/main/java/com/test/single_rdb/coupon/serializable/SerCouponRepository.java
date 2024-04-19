package com.test.single_rdb.coupon.serializable;

import com.test.single_rdb.coupon.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerCouponRepository extends JpaRepository<Coupon,Long> {
}
