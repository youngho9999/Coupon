package com.test.single_rdb.coupon.repeatable;

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
import java.util.concurrent.atomic.AtomicInteger;


@SpringBootTest
class RepCouponControllerTest {

    @Autowired
    private RepCouponController couponController;
    @Autowired
    private RepCouponRepository couponRepository;

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

        int threadCount = 3;
        int customerCount = 1000;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(customerCount);

        AtomicInteger sCount = new AtomicInteger();
        AtomicInteger fCount = new AtomicInteger();

        for(int i = 0; i < customerCount; i++) {
            executorService.execute(() -> {
                try {
                    couponController.getCoupon(coupon.getId());
                    sCount.getAndIncrement();
                } catch (Exception e) {
                    fCount.getAndIncrement();
                } finally {
                    latch.countDown();
                }
            });
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Coupon c = couponRepository.findById(coupon.getId()).get();
        System.out.println("*************************************************");
        System.out.println("remainCoupon = " + c.getQuantity());
        System.out.println("sCount = " + sCount);
        System.out.println("fCount = " + fCount);
        Assertions.assertThat(c.getQuantity()).isEqualTo(coupon.getQuantity() - sCount.get());
    }

}