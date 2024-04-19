package com.test.single_rdb.coupon;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Coupon {

    @Id
    private Long id;
    private int quantity;

    public void getCoupon() {
        this.quantity--;
    }
}
