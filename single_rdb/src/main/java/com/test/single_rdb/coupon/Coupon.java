package com.test.single_rdb.coupon;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
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

    @Version
    private Long version;

    public void getCoupon() {
        this.quantity--;
    }

    public Coupon(Long id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }
}
