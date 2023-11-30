package com.example.shctfdex;

import android.content.Context;
import android.util.Base64;

import java.nio.charset.StandardCharsets;

public class UT {
    public static String gb(String s) {
        byte[] data = Base64.decode(s, Base64.DEFAULT);
        return new String(data, StandardCharsets.UTF_8);
    }

    public String gf(Context context) {
        DH dh = new DH(context);
        dh.cd();
        dh.open();
        String r = dh.gd();
        dh.close();
        return r;
    }

    public String gff(Context context) {
        DH dh = new DH(context);
        dh.cd();
        dh.open();
        String r = dh.gdd();
        dh.close();
        return r;
    }

    public byte[] g(String hs) {
        if (hs.length() % 2 == 1) {
            throw new IllegalArgumentException("Invalid");
        }

        byte[] bytes = new byte[hs.length() / 2];
        for (int i = 0; i < hs.length(); i += 2) {
            bytes[i / 2] = hb(hs.substring(i, i + 2));
        }
        return bytes;
    }

    public byte hb(String hs) {
        int firstDigit = td(hs.charAt(0));
        int secondDigit = td(hs.charAt(1));
        return (byte) ((firstDigit << 4) + secondDigit);
    }

    private int td(char hc) {
        int digit = Character.digit(hc, 16);
        if (digit == -1) {
            throw new IllegalArgumentException("Invalid :" + hc);
        }
        return digit;
    }
}
