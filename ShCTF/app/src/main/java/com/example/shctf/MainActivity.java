package com.example.shctf;

import static com.example.shctf.Util.an;
import static com.example.shctf.Util.pn1;
import static com.example.shctf.Util.pn2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Base64;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myloadingbutton.MyLoadingButton;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import dalvik.system.InMemoryDexClassLoader;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d_main);
        MyLoadingButton button =findViewById(R.id.b);
        EditText editText = findViewById(R.id.etext);

        try {
            cdx(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String y = l1(this);
        String q = l2(this);
        button.setMyButtonClickListener(() -> {
            String eText = editText.getText().toString();
            String rr = l3(MainActivity.this, eText, q.trim());
            boolean b =false;
            if (y.trim().equals(rr.trim())) {
                Toast.makeText(MainActivity.this, "congratulations!", Toast.LENGTH_LONG).show();
                b = true;
                button.showDoneButton();
            } else {
                Toast.makeText(MainActivity.this, "Ooops :)", Toast.LENGTH_LONG).show();
                button.showErrorButton();
            }
            boolean finalB = b;
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(() -> {
                        if (finalB)
                            button.showDoneToNormalButton();
                        else
                            button.showErrorToNormalButton();
                    });
                }
            },2000);
        });
    }


    public String l1(Context context) {

        String cn = gb(pn1);
        String s = Objects.requireNonNull(context.getExternalFilesDir(null)).getAbsolutePath() + "/" + an;
        try {
            ClassLoader dLoader = getInst(s);
            Class<?> loadedClass = dLoader.loadClass(cn);
            Object obj = loadedClass.newInstance();
            String re;
            Method m = loadedClass.getMethod(gb("Z2Y="), Context.class); //gf
            re = (String) m.invoke(obj, context);
            return re;

        } catch (Exception e) {
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
            Method m = loadedClass.getMethod(gb("Z2Zm"), Context.class); //gff
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
            Method m = loadedClass.getMethod(gb("ZWNo"), String.class, String.class); //ech
            re = (String) m.invoke(obj, s1, s2);
            return re;

        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public static String gb(String s) {
        byte[] data = Base64.decode(s, Base64.DEFAULT);
        return new String(data, StandardCharsets.UTF_8);
    }

    private void cdx(Context context) throws IOException {
        File file = new File(Objects.requireNonNull(context.getExternalFilesDir(null)).getAbsolutePath(), an);
        if (!file.exists()) {
            InputStream mInput = context.getAssets().open(an);
            OutputStream mOutput = Files.newOutputStream(file.toPath());
            byte[] mBuffer = new byte[4096];
            int mLength;
            while ((mLength = mInput.read(mBuffer)) > 0) {
                mOutput.write(mBuffer, 0, mLength);
            }
            mOutput.flush();
            mOutput.close();
            mInput.close();
        }
    }

    public ClassLoader getInst(String s) {
        try {
            FileInputStream fis = new FileInputStream(s);
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
            ClassLoader loader = new InMemoryDexClassLoader(bb, null);
            bb.clear();
            return loader;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String cdec(String s) {
        StringBuilder htd = new StringBuilder();
        try {
            for (int i = 0; i < s.length()-1; i+=2) {
                String output = s.substring(i, (i+2));
                int decimal = Integer.parseInt(output, 16);
                htd.append((char) decimal);
            }
            StringBuilder r = new StringBuilder();
            int keyItr = 0;
            for (int i = 0; i < htd.length(); i++) {
                int temp = htd.charAt(i) ^ "key".charAt(keyItr);

                r.append((char) temp);
                keyItr++;
                if(keyItr >= "key".length()){
                    keyItr = 0;
                }
            }
            return r.toString();
        }catch (Exception e){
            e.printStackTrace();
            return "null";
        }
    }
}