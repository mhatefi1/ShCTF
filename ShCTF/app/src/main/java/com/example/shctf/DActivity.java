package com.example.shctf;

import static com.example.shctf.Util.an;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myloadingbutton.MyLoadingButton;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class DActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d_main);
        MyLoadingButton button = findViewById(R.id.b);
        EditText editText = findViewById(R.id.etext);

        /*try {
            rfa(0x000561e0L);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        try {
            cdx(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String y = new Util().l1(this);
        String q = new Util().l2(this);
        button.setMyButtonClickListener(() -> {
            String eText = editText.getText().toString();
            String rr = new Util().l3(DActivity.this, eText, cdec(q)).trim();
            boolean b = false;
            Log.d("aaa", "q:" + q.trim());
            Log.d("aaa", "y:" + y.trim());
            Log.d("aaa", "rr:" + rr.trim());
            if (y.trim().equals(rr.trim())) {
                Toast.makeText(DActivity.this, "congratulations!", Toast.LENGTH_LONG).show();
                b = true;
                button.showDoneButton();
            } else {
                Toast.makeText(DActivity.this, "Ooops :)", Toast.LENGTH_LONG).show();
                button.showErrorButton();
            }
            boolean finalB = b;
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(() -> {
                        if (finalB) {
                            button.showDoneToNormalButton();
                        } else
                            button.showErrorToNormalButton();
                    });
                }
            }, 2000);
        });
    }

    public void rfa(long offsetStart) throws IOException {
        InputStream mInput = getAssets().open("calibri.ttf");
        OutputStream mOutput = Files.newOutputStream(Paths.get(Objects.requireNonNull(getExternalFilesDir(null))
                .getAbsolutePath() + "/" + "an"));
        int offset = (int) offsetStart;
        byte[] mBuffer = new byte[(int) (0x000569E0L)];
        int i = mInput.read(mBuffer);
        mInput.close();
        mOutput.write(mBuffer, offset, (int) (0x000569E0L - offset));
        mOutput.flush();
        mOutput.close();
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

    private void cdxx(Context context) throws IOException {
        File file = new File(Objects.requireNonNull(context.getExternalFilesDir(null)).getAbsolutePath(), an);
        if (!file.exists()) {
            InputStream mInput = context.getAssets().open(an);
            OutputStream mOutput = Files.newOutputStream(file.toPath());
            long o = 0x00010b14;
            byte[] mBuffer = new byte[(int) (78300)];
            int i = mInput.read(mBuffer);
            mInput.close();
            mOutput.write(mBuffer, (int) o, (int) (78300 - o));
            mOutput.flush();
            mOutput.close();
        }
    }

    public String cdec(String s) {
        StringBuilder htd = new StringBuilder();
        try {
            for (int i = 0; i < s.length() - 1; i += 2) {
                String output = s.substring(i, (i + 2));
                int decimal = Integer.parseInt(output, 16);
                htd.append((char) decimal);
            }
            StringBuilder r = new StringBuilder();
            int keyItr = 0;
            for (int i = 0; i < htd.length(); i++) {
                int temp = htd.charAt(i) ^ "key".charAt(keyItr);

                r.append((char) temp);
                keyItr++;
                if (keyItr >= "key".length()) {
                    keyItr = 0;
                }
            }
            return r.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "null";
        }
    }
}