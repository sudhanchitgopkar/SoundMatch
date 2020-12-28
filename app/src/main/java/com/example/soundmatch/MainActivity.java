package com.example.soundmatch;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.os.Build;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String generateCodeVerifier() {
        String candidates = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_.-~";
        Random rando = new Random();
        int rand = 0;
        String codeVerifier = "";
        for(int i = 0; i < 128; i++) {
            char temp = candidates.charAt(rando.nextInt(66));
            codeVerifier += temp;
        } // for
        generateCodeChallenge(codeVerifier);
        return codeVerifier;
    } // generateCodeVerifier


    // THIS METHOD DOES NOT WORK FOR MULTIPLE THREADS AND WILL MAKE A NEW INSTANCE FOR EACH THREAD
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void generateCodeChallenge(String codeVerifier) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encoded = digest.digest(codeVerifier.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } //try
    } // generateCodeChallenge

}