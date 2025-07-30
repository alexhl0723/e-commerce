package com.devotion.ztita.controller;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordTest {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "ztitan";
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("Hash generado: " + encodedPassword);
    }
}