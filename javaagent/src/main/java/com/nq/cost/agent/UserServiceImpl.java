package com.nq.cost.agent;

import java.util.concurrent.TimeUnit;

public class UserServiceImpl {
    public void findUser(String username) {
        System.out.println("userName: " + username);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
