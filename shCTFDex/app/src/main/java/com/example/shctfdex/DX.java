package com.example.shctfdex;

import android.util.Base64;

import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class DX {

    private static byte[] getK(int t, String s) {
        byte[] total = new UT().g(s);
        byte[] c = new byte[total.length / 2];
        if (t == 0) {
            for (int i = 0; i < total.length; i++) {
                if (i % 2 == 0)
                    c[i / 2] = total[i];
            }
        } else {
            for (int i = 0; i < total.length; i++) {
                if (i % 2 == 1)
                    c[i / 2] = total[i];
            }
        }
        return c;
    }

    public String ech(String message, String s) {
        try {
            byte[] srcBuff = message.getBytes(StandardCharsets.UTF_8);
            SecretKeySpec keySpec = new SecretKeySpec(getK(0, s + UT.gb("Mzg2OTM3NjEzNDc0MzYzMTM1MzUzMjM2MzMzMjMxMzA=")), "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(getK(1, s + UT.gb("Mzg2OTM3NjEzNDc0MzYzMTM1MzUzMjM2MzMzMjMxMzA=")));
            Cipher ecipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            ecipher.init(1, keySpec, ivSpec);
            return Base64.encodeToString(ecipher.doFinal(srcBuff), 0);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "encrypt :" + ex.getMessage();
        }
    }
}