package com.sachin.util;

import com.sachin.entity.User;

public class UtilMethods {
    public static boolean isAdmin(User user) {
        return user.getRole().equals("ADMIN");
    }
}
