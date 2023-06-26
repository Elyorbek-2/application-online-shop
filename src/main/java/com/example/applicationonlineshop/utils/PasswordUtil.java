package com.example.applicationonlineshop.utils;

import lombok.NonNull;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
    public static String encode(@NonNull String password) {
        return BCrypt.hashpw(password,BCrypt.gensalt());
    }
    public static boolean check(@NonNull String password,@NonNull String checkPassword) {
        return BCrypt.checkpw(password,checkPassword);
    }

}
