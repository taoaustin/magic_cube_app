package com.example.magic_cube_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity {//for only timing, providing a scramble. Use a physical cube instead.

    Button control;
    TextView scrambleText;
    Chronometer timer;

    private boolean timing = false;

    private String[] createScramble() {//creates a scramble sequence of length 20 of random faces and directions, where the same face isn't turned twice consecutively.
        String[] faces = {"R","U","F","L","D","B"};
        String[] dirs = {"","'","2"};
        Random rand = new Random();
        String[] result = new String[20];
        int face = rand.nextInt(6);
        int dir = rand.nextInt(3);
        result[0] = faces[face]+dirs[dir];
        int i = 1;
        while (i < 20) {
            face = rand.nextInt(6);
            dir = rand.nextInt(3);
            if (!result[i-1].contains(faces[face])) {
                result[i] = faces[face]+dirs[dir];
                i++;
            }
        }
        return result;
    }

    private void startChrono() {
        timer.setBase(SystemClock.elapsedRealtime());
        timer.start();
    }
    private void stopChrono() {
        timer.stop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        control = (Button) findViewById(R.id.control);
        scrambleText = (TextView) findViewById(R.id.scrambleText);
        timer = (Chronometer) findViewById(R.id.timer);

        String[] seq = createScramble(); //creating initial scramble.
        String res = "";
        for (String s : seq) {
            res += s+" ";
        }
        scrambleText.setText(res);

        control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!timing) {
                    startChrono();
                    timing = true;
                    control.setText("STOP");
                }
                else {
                    stopChrono();
                    timing = false;
                    control.setText("START");
                    String[] seq = createScramble();
                    String res = "";
                    for (String s : seq) {
                        res += s+" ";
                    }
                    scrambleText.setText(res);
                }
            }
        });
    }
}