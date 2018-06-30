package com.iitd.sahyog;

import java.util.Random;

public class PasswordGenerator {
    public char[] generatedPassword(){
        int len= 8;
        char[] Password;
        Password = new char[len];
        String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String Small_chars = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String symbols = "!@#$%^&*_=+-/.?<>)";


        String values = Capital_chars + Small_chars +
                numbers + symbols;

        // Using random method
        Random rndm_method = new Random();

        char[] password = new char[len];

        for (int i = 0; i < len; i++)
        {
            // Use of charAt() method : to get character value
            // Use of nextInt() as it is scanning the value as int
            Password[i] = values.charAt(rndm_method.nextInt(values.length()));

        }
       System.out.print(Password);
       return Password;
    }
}
