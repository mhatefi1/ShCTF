package com.example.shctf;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Objects;

import dalvik.system.InMemoryDexClassLoader;

public class Util {
    public static final String an = "timesnewroman.ttf";
    public static final String pn1 = "Y29tLmV4YW1wbGUuc2hjdGZkZXguVVQ=";
    public static final String pn2 = "Y29tLmV4YW1wbGUuc2hjdGZkZXguRFg=";

    public static String gb(String s) {
        byte[] data = Base64.decode(s, Base64.DEFAULT);
        return new String(data, StandardCharsets.UTF_8);
    }

    public String l1(Context context) {
        String cn = gb(pn1);
        String s = Objects.requireNonNull(context.getExternalFilesDir(null)).getAbsolutePath() + "/" + an;
        Log.d("rrr", "l1:" + s);

        try {
            ByteBuffer bb = xxx(s);
            ClassLoader dLoader = new InMemoryDexClassLoader(bb, getClass().getClassLoader());
            bb.clear();
            //ClassLoader dLoader = getInst(s);
            Log.d("rrr",dLoader.getClass().getName());

            Class<?> loadedClass = dLoader.loadClass(cn);
            Object obj = loadedClass.newInstance();
            String re;
            Method m = loadedClass.getMethod(gb("Z2Y="), Context.class);
            re = (String) m.invoke(obj, context);
            return re;

        } catch (Exception e) {
            Log.d("rrr", "error l1:" + e.getMessage());
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public String l2(Context context) {

        String cn = gb(pn1);
        String s = Objects.requireNonNull(context.getExternalFilesDir(null)).getAbsolutePath() + "/" + an;
        try {
            ClassLoader dLoader = getInst(s);
            Class<?> loadedClass = dLoader.loadClass(cn);
            Object obj = loadedClass.newInstance();
            String re;
            Method m = loadedClass.getMethod(gb("Z2Zm"), Context.class);
            re = (String) m.invoke(obj, context);
            return re;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public String l3(Context context, String s1, String s2) {

        String cn = gb(pn2);
        String s = Objects.requireNonNull(context.getExternalFilesDir(null)).getAbsolutePath() + "/" + an;
        try {
            ClassLoader dLoader = getInst(s);
            Class<?> loadedClass = dLoader.loadClass(cn);
            Object obj = loadedClass.newInstance();
            String re;
            Method m = loadedClass.getMethod(gb("ZWNo"), String.class, String.class);
            re = (String) m.invoke(obj, s1, s2);
            return re;

        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public ClassLoader getInst(String s) {
        try {
            try (FileInputStream fis = new FileInputStream(s)) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                int bytesRead;
                byte[] buffer = new byte[4096];
                while ((bytesRead = fis.read(buffer, 0, buffer.length)) != -1) {
                    baos.write(buffer, 0, bytesRead);
                }
                baos.flush();
                Log.d("rrr", "getInst:" + baos.size() + "");
                byte[] dex = baos.toByteArray();
                Log.d("rrr", "getInst:" + dex.length + "");
                ByteBuffer bb = ByteBuffer.allocate(dex.length);
                bb.put(dex);
                bb.position(0);
                ClassLoader loader = new InMemoryDexClassLoader(bb, getClass().getClassLoader());
                bb.clear();
                return loader;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ByteBuffer xxx(String s) {
        try {
            try (FileInputStream fis = new FileInputStream(s)) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                int bytesRead;
                byte[] buffer = new byte[4096];
                while ((bytesRead = fis.read(buffer, 0, buffer.length)) != -1) {
                    baos.write(buffer, 0, bytesRead);
                }
                baos.flush();
                byte[] dex = baos.toByteArray();
                ByteBuffer bb = ByteBuffer.allocate(dex.length);
                bb.put(dex);
                bb.position(0);
                return bb;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}