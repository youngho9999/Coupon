package com.test.single_rdb.coupon;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {

    @Id
    private Long id;
    private int quantity;

    public void getCoupon() {
        this.quantity--;
    }
}
