package com.example.magic_cube_app;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Button turnR, turnRi, turnL, turnLi, turnU, turnUi, turnD, turnDi, turnF, turnFi, turnB, turnBi;

    View b1,b2,b3,b4,b5,b6,b7,b8,
            l1,l2,l3,l4,l5,l6,l7,l8,
            u1,u2,u3,u4,u5,u6,u7,u8,
            r1,r2,r3,r4,r5,r6,r7,r8,
            f1,f2,f3,f4,f5,f6,f7,f8,
            d1,d2,d3,d4,d5,d6,d7,d8;

    private char[][] cube = new char[6][8]; {
        Arrays.fill(cube[0], 'O');
        Arrays.fill(cube[1], 'B');
        Arrays.fill(cube[2], 'Y');
        Arrays.fill(cube[3], 'G');
        Arrays.fill(cube[4], 'R');
        Arrays.fill(cube[5], 'W');
    }

    private View[] stickers;

    private void faceStickersRotate(int face) {
        char tempEdge = cube[face][1];
        char tempCorner = cube[face][0];

        cube[face][0] = cube[face][6]; //for "corner stickers"
        cube[face][6] = cube[face][4];
        cube[face][4] = cube[face][2];
        cube[face][2] = tempCorner;

        cube[face][1] = cube[face][7]; //for "edge stickers"
        cube[face][7] = cube[face][5];
        cube[face][5] = cube[face][3];
        cube[face][3] = tempEdge;


    }

    private void printCube() {
        int count = 0;
        for(int i = 0; i < cube.length; i++) {
            for (int j = 0; j < cube[i].length; j++) {
                switch(cube[i][j]) {
                    case 'O':
                        stickers[count].setBackgroundColor(Color.parseColor("#FF8800"));
                        break;
                    case 'B':
                        stickers[count].setBackgroundColor(Color.parseColor("#3700B3"));
                        break;
                    case 'Y':
                        stickers[count].setBackgroundColor(Color.parseColor("#FFE400"));
                        break;
                    case 'G':
                        stickers[count].setBackgroundColor(Color.parseColor("#669900"));
                        break;
                    case 'R':
                        stickers[count].setBackgroundColor(Color.parseColor("#D60F0F"));
                        break;
                    case 'W':
                        stickers[count].setBackgroundColor(Color.parseColor("#FFFFFF"));
                        break;
                }
                count++;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        turnR = (Button) findViewById(R.id.btnR);
        turnRi = (Button) findViewById(R.id.btnRi);
        turnL = (Button) findViewById(R.id.btnL);
        turnLi = (Button) findViewById(R.id.btnLi);
        turnU = (Button) findViewById(R.id.btnU);
        turnUi = (Button) findViewById(R.id.btnUi);
        turnD = (Button) findViewById(R.id.btnD);
        turnDi = (Button) findViewById(R.id.btnDi);
        turnF = (Button) findViewById(R.id.btnF);
        turnFi = (Button) findViewById(R.id.btnFi);
        turnB = (Button) findViewById(R.id.btnB);
        turnBi = (Button) findViewById(R.id.btnBi);

        b1 = findViewById(R.id.viewB1);
        b2 = findViewById(R.id.viewB2);
        b3 = findViewById(R.id.viewB3);
        b4 = findViewById(R.id.viewB4);
        b5 = findViewById(R.id.viewB5);
        b6 = findViewById(R.id.viewB6);
        b7 = findViewById(R.id.viewB7);
        b8 = findViewById(R.id.viewB8);

        l1 = findViewById(R.id.viewL1);
        l2 = findViewById(R.id.viewL2);
        l3 = findViewById(R.id.viewL3);
        l4 = findViewById(R.id.viewL4);
        l5 = findViewById(R.id.viewL5);
        l6 = findViewById(R.id.viewL6);
        l7 = findViewById(R.id.viewL7);
        l8 = findViewById(R.id.viewL8);

        u1 = findViewById(R.id.viewU1);
        u2 = findViewById(R.id.viewU2);
        u3 = findViewById(R.id.viewU3);
        u4 = findViewById(R.id.viewU4);
        u5 = findViewById(R.id.viewU5);
        u6 = findViewById(R.id.viewU6);
        u7 = findViewById(R.id.viewU7);
        u8 = findViewById(R.id.viewU8);

        r1 = findViewById(R.id.viewR1);
        r2 = findViewById(R.id.viewR2);
        r3 = findViewById(R.id.viewR3);
        r4 = findViewById(R.id.viewR4);
        r5 = findViewById(R.id.viewR5);
        r6 = findViewById(R.id.viewR6);
        r7 = findViewById(R.id.viewR7);
        r8 = findViewById(R.id.viewR8);

        f1 = findViewById(R.id.viewF1);
        f2 = findViewById(R.id.viewF2);
        f3 = findViewById(R.id.viewF3);
        f4 = findViewById(R.id.viewF4);
        f5 = findViewById(R.id.viewF5);
        f6 = findViewById(R.id.viewF6);
        f7 = findViewById(R.id.viewF7);
        f8 = findViewById(R.id.viewF8);

        d1 = findViewById(R.id.viewD1);
        d2 = findViewById(R.id.viewD2);
        d3 = findViewById(R.id.viewD3);
        d4 = findViewById(R.id.viewD4);
        d5 = findViewById(R.id.viewD5);
        d6 = findViewById(R.id.viewD6);
        d7 = findViewById(R.id.viewD7);
        d8 = findViewById(R.id.viewD8);

        View[] tempStickers = {b1,b2,b3,b4,b5,b6,b7,b8,
                l1,l2,l3,l4,l5,l6,l7,l8,
                u1,u2,u3,u4,u5,u6,u7,u8,
                r1,r2,r3,r4,r5,r6,r7,r8,
                f1,f2,f3,f4,f5,f6,f7,f8,
                d1,d2,d3,d4,d5,d6,d7,d8};
        stickers = tempStickers;

        turnU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            faceStickersRotate(2);
            char tmp1 = cube[4][0];
            char tmp2 = cube[4][1];
            char tmp3 = cube[4][2];

            cube[4][0] = cube[3][6];
            cube[4][1] = cube[3][7];
            cube[4][2] = cube[3][0];

            cube[3][6] = cube[0][4];
            cube[3][7] = cube[0][5];
            cube[3][0] = cube[0][6];

            cube[0][4] = cube[1][2];
            cube[0][5] = cube[1][3];
            cube[0][6] = cube[1][4];

            cube[1][2] = tmp1;
            cube[1][3] = tmp2;
            cube[1][4] = tmp3;
            printCube();
            }
        });
        turnUi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                faceStickersRotate(2);
                char tmp1 = cube[4][0];
                char tmp2 = cube[4][1];
                char tmp3 = cube[4][2];

                cube[4][0] = cube[1][2];
                cube[4][1] = cube[1][3];
                cube[4][2] = cube[1][4];

                cube[1][2] = cube[0][4];
                cube[1][3] = cube[0][5];
                cube[1][4] = cube[0][6];

                cube[0][4] = cube[3][6];
                cube[0][5] = cube[3][7];
                cube[0][6] = cube[3][0];

                cube[3][6] = tmp1;
                cube[3][7] = tmp2;
                cube[3][0] = tmp3;



                printCube();
            }
        });
    }
}