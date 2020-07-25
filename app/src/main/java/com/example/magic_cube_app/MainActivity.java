//Author: Austin Tao 2020
package com.example.magic_cube_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button turnR, turnRi, turnL, turnLi, turnU, turnUi, turnD, turnDi, turnF, turnFi, turnB, turnBi, scr, reset, onlyTimer;

    View b1,b2,b3,b4,b5,b6,b7,b8,
            l1,l2,l3,l4,l5,l6,l7,l8,
            u1,u2,u3,u4,u5,u6,u7,u8,
            r1,r2,r3,r4,r5,r6,r7,r8,
            f1,f2,f3,f4,f5,f6,f7,f8,                               //    | 0 |        the faces of the cube are arranged as such: (B)ack face->0 , (L)eft face->1 ,
            d1,d2,d3,d4,d5,d6,d7,d8;                               //  =========      (U)p face->2 , (R)ight face->3 , (F)ront face->4 , (D)own face->5
                                                                   //  1 | 2 | 3
    TextView scrText;                                              //  =========
    Chronometer chrono;                                            //    | 4 |
                                                                   //  =========
    private boolean solving = false;                               //    | 5 |
    private boolean firstMove = true;

    private char[][] cube = new char[6][8]; {                      //  0 | 1 | 2      each face of the "cube" is arranged with indices like this and,
        Arrays.fill(cube[0], 'O');                            //  =========      for example: b1->cube[0][0] , b2->cube[0][1] , b3->cube[0][2] ,
        Arrays.fill(cube[1], 'B');                            //  7 |   | 3                   b4->cube[0][3], ... etc.
        Arrays.fill(cube[2], 'Y');                            //  =========
        Arrays.fill(cube[3], 'G');                            //  6 | 5 | 4
        Arrays.fill(cube[4], 'R');
        Arrays.fill(cube[5], 'W');
    }

    private View[] stickers;

    private void faceStickersRotate(int face) {//rotate the stickers on a certain given face clockwise. This isn't a complete turn because
        char tempEdge = cube[face][1];         //other stickers on other faces must be done to be a complete turn.
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

    private void scramble(String[] seq) {//given a scramble sequence (from the createScramble() method) performs the scramble on the cube.
        for (String s : seq) {
            switch(s) {
                case "R":
                    turnR.performClick();
                    break;
                case "R'":
                    turnRi.performClick();
                    break;
                case "R2":                    //any X2 move suffices by turning the designated face clockwise 2 times (180 degree turn)
                    turnR.performClick();
                    turnR.performClick();
                    break;
                case "L":
                    turnL.performClick();
                    break;
                case "L'":
                    turnLi.performClick();
                    break;
                case "L2":
                    turnL.performClick();
                    turnL.performClick();
                    break;
                case "U":
                    turnU.performClick();
                    break;
                case "U'":
                    turnUi.performClick();
                    break;
                case "U2":
                    turnU.performClick();
                    turnU.performClick();
                    break;
                case "D":
                    turnD.performClick();
                    break;
                case "D'":
                    turnDi.performClick();
                    break;
                case "D2":
                    turnD.performClick();
                    turnD.performClick();
                    break;
                case "F":
                    turnF.performClick();
                    break;
                case "F'":
                    turnFi.performClick();
                    break;
                case "F2":
                    turnF.performClick();
                    turnF.performClick();
                    break;
                case "B":
                    turnB.performClick();
                    break;
                case "B'":
                    turnBi.performClick();
                    break;
                case "B2":
                    turnB.performClick();
                    turnB.performClick();
                    break;
            }
        }
    }

    private void clockwiseU() {//simulates turning the whole designated face of the cube clockwise 90 degrees
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
    }

    private void clockwiseR() {
        faceStickersRotate(3);
        char tmp2 = cube[2][2];
        char tmp3 = cube[2][3];
        char tmp4 = cube[2][4];

        cube[2][2] = cube[4][2];
        cube[2][3] = cube[4][3];
        cube[2][4] = cube[4][4];

        cube[4][2] = cube[5][2];
        cube[4][3] = cube[5][3];
        cube[4][4] = cube[5][4];

        cube[5][2] = cube[0][2];
        cube[5][3] = cube[0][3];
        cube[5][4] = cube[0][4];

        cube[0][2] = tmp2;
        cube[0][3] = tmp3;
        cube[0][4] = tmp4;
    }

    private void clockwiseF() {
        faceStickersRotate(4);
        char tmp6 = cube[2][6];
        char tmp5 = cube[2][5];
        char tmp4 = cube[2][4];

        cube[2][6] = cube[1][6];
        cube[2][5] = cube[1][5];
        cube[2][4] = cube[1][4];

        cube[1][6] = cube[5][2];
        cube[1][5] = cube[5][1];
        cube[1][4] = cube[5][0];

        cube[5][2] = cube[3][6];
        cube[5][1] = cube[3][5];
        cube[5][0] = cube[3][4];

        cube[3][6] = tmp6;
        cube[3][5] = tmp5;
        cube[3][4] = tmp4;
    }

    private void clockwiseD() {
        faceStickersRotate(5);
        char tmp6 = cube[4][6];
        char tmp5 = cube[4][5];
        char tmp4 = cube[4][4];

        cube[4][6] = cube[1][0];
        cube[4][5] = cube[1][7];
        cube[4][4] = cube[1][6];

        cube[1][0] = cube[0][2];
        cube[1][7] = cube[0][1];
        cube[1][6] = cube[0][0];

        cube[0][2] = cube[3][4];
        cube[0][1] = cube[3][3];
        cube[0][0] = cube[3][2];

        cube[3][4] = tmp6;
        cube[3][3] = tmp5;
        cube[3][2] = tmp4;
    }
    private void clockwiseL() {
        faceStickersRotate(1);
        char tmp0 = cube[2][0];
        char tmp7 = cube[2][7];
        char tmp6 = cube[2][6];

        cube[2][0] = cube[0][0];
        cube[2][7] = cube[0][7];
        cube[2][6] = cube[0][6];

        cube[0][0] = cube[5][0];
        cube[0][7] = cube[5][7];
        cube[0][6] = cube[5][6];

        cube[5][0] = cube[4][0];
        cube[5][7] = cube[4][7];
        cube[5][6] = cube[4][6];

        cube[4][0] = tmp0;
        cube[4][7] = tmp7;
        cube[4][6] = tmp6;
    }
    private void clockwiseB() {
        faceStickersRotate(0);
        char tmp0 = cube[2][0];
        char tmp1 = cube[2][1];
        char tmp2 = cube[2][2];

        cube[2][0] = cube[3][0];
        cube[2][1] = cube[3][1];
        cube[2][2] = cube[3][2];

        cube[3][0] = cube[5][4];
        cube[3][1] = cube[5][5];
        cube[3][2] = cube[5][6];

        cube[5][4] = cube[1][0];
        cube[5][5] = cube[1][1];
        cube[5][6] = cube[1][2];

        cube[1][0] = tmp0;
        cube[1][1] = tmp1;
        cube[1][2] = tmp2;
    }

    private void startChrono() {
        chrono.setBase(SystemClock.elapsedRealtime());
        chrono.start();
    }
    private void stopChrono() {
        chrono.stop();
    }
    private void resetChrono() {
        stopChrono();
        chrono.setBase(SystemClock.elapsedRealtime());
    }

    private boolean isSolved() {//checks if the cube in its current state is solved.
        for (int i = 0; i < cube[0].length; i++) {
            if (cube[0][i] != 'O')
                return false;
        }
        for (int i = 0; i < cube[1].length; i++) {
            if (cube[1][i] != 'B')
                return false;
        }
        for (int i = 0; i < cube[2].length; i++) {
            if (cube[2][i] != 'Y')
                return false;
        }
        for (int i = 0; i < cube[3].length; i++) {
            if (cube[3][i] != 'G')
                return false;
        }
        for (int i = 0; i < cube[4].length; i++) {
            if (cube[4][i] != 'R')
                return false;
        }
        for (int i = 0; i < cube[5].length; i++) {
            if (cube[5][i] != 'W')
                return false;
        }
        return true;
    }

    private void timerConditions() {//checks if its right to either start or stop the timer
        if (solving && firstMove) {
            startChrono();
            firstMove = false;
        }
        if (solving && isSolved()) {
            stopChrono();
            solving = false;
        }
    }
    public void openOnlyTimer() {//brings you to Activity2 where it is just the manual timer.
        Intent intent = new Intent(this,MainActivity2.class);
        startActivity(intent);
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
        scr = (Button) findViewById(R.id.btnScr);
        reset = (Button) findViewById(R.id.btnReset);
        onlyTimer = (Button) findViewById(R.id.btnOnlyTimer);

        scrText = (TextView) findViewById(R.id.textViewScr);
        chrono = (Chronometer) findViewById(R.id.chronometer);

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
            clockwiseU();
            printCube();
            timerConditions();
            }
        });
        turnUi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {// for any turnXi (i denotes counter-clockwise) it is the same as turning that face clockwise 3 times
            clockwiseU();
            clockwiseU();
            clockwiseU();
            printCube();
            timerConditions();
            }
        });
        turnR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            clockwiseR();
            printCube();
            timerConditions();
            }
        });
        turnRi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            clockwiseR();
            clockwiseR();
            clockwiseR();
            printCube();
            timerConditions();
            }
        });
        turnF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            clockwiseF();
            printCube();
            timerConditions();
            }
        });
        turnFi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            clockwiseF();
            clockwiseF();
            clockwiseF();
            printCube();
            timerConditions();
            }
        });
        turnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            clockwiseD();
            printCube();
            timerConditions();
            }
        });
        turnDi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            clockwiseD();
            clockwiseD();
            clockwiseD();
            printCube();
            timerConditions();
            }
        });
        turnL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            clockwiseL();
            printCube();
            timerConditions();
            }
        });
        turnLi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            clockwiseL();
            clockwiseL();
            clockwiseL();
            printCube();
            timerConditions();
            }
        });
        turnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            clockwiseB();
            printCube();
            timerConditions();
            }
        });
        turnBi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            clockwiseB();
            clockwiseB();
            clockwiseB();
            printCube();
            timerConditions();
            }
        });
        scr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] seq = createScramble();
                reset.performClick(); //always starts scramble from solved state
                scramble(seq);
                String res = "";
                for (String s : seq) {
                    res += s+" ";
                }
                scrText.setText(res);
                solving = true;
                firstMove = true;
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//reset to solved state, resets timer to 0
                Arrays.fill(cube[0], 'O');
                Arrays.fill(cube[1], 'B');
                Arrays.fill(cube[2], 'Y');
                Arrays.fill(cube[3], 'G');
                Arrays.fill(cube[4], 'R');
                Arrays.fill(cube[5], 'W');
                printCube();
                resetChrono();
                solving = false;
            }
        });
        onlyTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOnlyTimer();
            }
        });
    }
}