package com.demo.process.user.domain;

import org.junit.Test;

public class UserTests {

    @Test(expected = IllegalArgumentException.class)
    public void testUser() {
        User.builder()
                .username("")
                .password("")
                .email("test@test.com")
                .build();
    }


}
