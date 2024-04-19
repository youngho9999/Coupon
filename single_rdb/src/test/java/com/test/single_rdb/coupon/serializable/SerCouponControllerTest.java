package com.test.single_rdb.coupon.serializable;

import com.test.single_rdb.coupon.Coupon;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SerCouponControllerTest {

    @Autowired
    private SerCouponController couponController;
    @Autowired
    private SerCouponRepository couponRepository;

    Coupon coupon;

    @BeforeEach
    void setUp() {
        coupon = new Coupon(5L, 50000);
        couponRepository.save(coupon);
    }

    @AfterEach
    void tearDown() {
        couponRepository.deleteAll();
    }

    @Test
    void getCoupon() {

        int threadCount = 10;
        int customerCount = 1000;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(customerCount);

        for(int i = 0; i < customerCount; i++) {
            executorService.execute(() -> {
                couponController.getCoupon(coupon.getId());
                latch.countDown();
            });
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Coupon c = couponRepository.findById(coupon.getId()).get();
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        System.out.println("c.getQuantity() = " + c.getQuantity());
        
        Assertions.assertThat(c.getQuantity()).isEqualTo(coupon.getQuantity() - customerCount);
    }
}