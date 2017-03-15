package com.example.valdas.memorium;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static android.R.attr.value;

public class MainActivity extends AppCompatActivity {

    final int[] pressed = {-1};
    final int[] hsCount = {0};
    final int[] clicks = {0};

    private static boolean isGameOver(Button[] buttons) {
        boolean isOver = true;
        for(Button button : buttons) {
            if(button.getVisibility() == View.VISIBLE) {
                isOver = false;
                break;
            }
        }
        return isOver;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final int[] firsthighscore = new int[1];
        firsthighscore[0] = -1;

        final int[] drawables = new int[]{
                R.drawable.facebook,
                R.drawable.firefox,
                R.drawable.insta,
                R.drawable.tweeter,
                R.drawable.apple,
                R.drawable.windows,
        };


        final int[] array = new int[12];

        array[0] = 1;
        array[1] = 1;
        array[2] = 2;
        array[3] = 2;
        array[4] = 3;
        array[5] = 3;
        array[6] = 4;
        array[7] = 4;
        array[8] = 5;
        array[9] = 5;
        array[10] = 6;
        array[11] = 6;


        final Activity home = this;

        final TextView hsValue = (TextView) findViewById(R.id.hsValue);
        final TextView scoreValue = (TextView) findViewById(R.id.scoreValue);
        final TextView clickCount = (TextView) findViewById(R.id.clickCount);

        final Button button = (Button) findViewById(R.id.button);
        final Button button2 = (Button) findViewById(R.id.button2);
        final Button button3 = (Button) findViewById(R.id.button3);
        final Button button4 = (Button) findViewById(R.id.button4);
        final Button button5 = (Button) findViewById(R.id.button5);
        final Button button6 = (Button) findViewById(R.id.button6);
        final Button button7 = (Button) findViewById(R.id.button7);
        final Button button8 = (Button) findViewById(R.id.button8);
        final Button button9 = (Button) findViewById(R.id.button9);
        final Button button10 = (Button) findViewById(R.id.button10);
        final Button button11 = (Button) findViewById(R.id.button11);
        final Button button12 = (Button) findViewById(R.id.button12);
        final Button button17 = (Button) findViewById(R.id.button17);

        final Button[] buttons = new Button[12];
        buttons[0] = button;
        buttons[1] = button2;
        buttons[2] = button3;
        buttons[3] = button4;
        buttons[4] = button5;
        buttons[5] = button6;
        buttons[6] = button7;
        buttons[7] = button8;
        buttons[8] = button9;
        buttons[9] = button10;
        buttons[10] = button11;
        buttons[11] = button12;


        for(Button button1 : buttons) {
            button1.setText("");
            button1.setBackgroundResource(R.drawable.back1);
        }

        button17.setBackgroundResource(R.drawable.reset);

        final SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        final int count = sharedPref.getInt(getString(R.string.counter), 100);
        Log.i("read", "pavyko nuskaityt" + count);

        hsValue.setText(count + "");
        hsCount[0] = count;

        final HashMap map = new HashMap<Integer, String>();
        map.put(array[0], drawables[0]);
        map.put(array[1], drawables[0]);
        map.put(array[2], drawables[1]);
        map.put(array[3], drawables[1]);
        map.put(array[4], drawables[2]);
        map.put(array[5], drawables[2]);
        map.put(array[6], drawables[3]);
        map.put(array[7], drawables[3]);
        map.put(array[8], drawables[4]);
        map.put(array[9], drawables[4]);
        map.put(array[10], drawables[5]);
        map.put(array[11], drawables[5]);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicks[0]++;
                clickCount.setText(clicks[0] + "");
                if(pressed[0] == -1){
                    Log.d("Button", "Click1-->> *" + array[0]);
                    button.setBackgroundResource((Integer) map.get(array[0]));

                    pressed[0] = array[0];
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            home.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    button.setBackgroundResource(R.drawable.back1);
                                }
                            });


                        }
                    }, 500);


                }else{
                    if (pressed[0] == array[0]){
                        Log.d("Button", "valio");
                        button.setBackgroundResource((Integer) map.get(array[0]));

                        for(int i = 0; i < array.length; i++){
                            if(array[i] == pressed[0]){
                                Timer timer = new Timer();
                                final int finalI = i;
                                timer.schedule(new TimerTask() {

                                    @Override
                                    public void run() {
                                        home.runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                buttons[finalI].setVisibility(View.GONE);
                                            }
                                        });


                                    }
                                }, 500);

                            }
                        }
                        pressed[0] = -1;
                    }else{
                        Log.d("Button", "nepataikei");
                        button.setBackgroundResource((Integer) map.get(array[0]));

                        pressed[0] = -1;
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {

                            @Override
                            public void run() {
                                home.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        button.setBackgroundResource(R.drawable.back1);
                                    }
                                });


                            }
                        }, 500);

                    }
                }
                if(isGameOver(buttons)) {
                    if(count>clicks[0]){
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putInt(getString(R.string.counter), clicks[0]);
                        editor.commit();
                        hsValue.setText(clicks[0] +"");
                    }
                    Log.d("ygthygh", "true");
                } else {
                    Log.d("ygthygh", "false");
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicks[0]++;
                clickCount.setText(clicks[0] + "");
                if(pressed[0] == -1){
                    Log.d("Button", "Click1-->> *" + array[1]);
                    button2.setBackgroundResource((Integer) map.get(array[1]));
                    pressed[0] = array[1];
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            home.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    button2.setBackgroundResource(R.drawable.back1);
                                }
                            });


                        }
                    }, 500);


                }else{
                    if (pressed[0] == array[1]){
                        Log.d("Button", "valio");
                        button2.setBackgroundResource((Integer) map.get(array[1]));
                        for(int i = 0; i < array.length; i++){
                            if(array[i] == pressed[0]){
                                Timer timer = new Timer();
                                final int finalI = i;
                                timer.schedule(new TimerTask() {

                                    @Override
                                    public void run() {
                                        home.runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                buttons[finalI].setVisibility(View.GONE);
                                            }
                                        });


                                    }
                                }, 500);
                            }
                        }
                        pressed[0] = -1;
                    }else{
                        Log.d("Button", "nepataikei");
                        button2.setBackgroundResource((Integer) map.get(array[1]));
                        pressed[0] = -1;
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {

                            @Override
                            public void run() {
                                home.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        button2.setBackgroundResource(R.drawable.back1);
                                    }
                                });


                            }
                        }, 500);

                    }
                }
                if(isGameOver(buttons)) {
                    if(count>clicks[0]){
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putInt(getString(R.string.counter), clicks[0]);
                        editor.commit();
                        hsValue.setText(clicks[0] +"");
                    }
                    Log.d("ygthygh", "true");
                } else {
                    Log.d("ygthygh", "false");
                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicks[0]++;
                clickCount.setText(clicks[0] + "");
                if(pressed[0] == -1){
                    Log.d("Button", "Click1-->> *" + array[2]);
                    button3.setBackgroundResource((Integer) map.get(array[2]));
                    pressed[0] = array[2];
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            home.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    button3.setBackgroundResource(R.drawable.back1);
                                }
                            });


                        }
                    }, 500);


                }else{
                    if (pressed[0] == array[2]){
                        Log.d("Button", "valio");
                        button3.setBackgroundResource((Integer) map.get(array[2]));
                        for(int i = 0; i < array.length; i++){
                            if(array[i] == pressed[0]){
                                Timer timer = new Timer();
                                final int finalI = i;
                                timer.schedule(new TimerTask() {

                                    @Override
                                    public void run() {
                                        home.runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                buttons[finalI].setVisibility(View.GONE);
                                            }
                                        });


                                    }
                                }, 500);
                            }
                        }
                        pressed[0] = -1;
                    }else{
                        Log.d("Button", "nepataikei");
                        button3.setBackgroundResource((Integer) map.get(array[2]));
                        pressed[0] = -1;
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {

                            @Override
                            public void run() {
                                home.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        button3.setBackgroundResource(R.drawable.back1);
                                    }
                                });


                            }
                        }, 500);

                    }
                }
                if(isGameOver(buttons)) {
                    if(count>clicks[0]){
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putInt(getString(R.string.counter), clicks[0]);
                        editor.commit();
                        hsValue.setText(clicks[0] +"");
                    }
                    Log.d("ygthygh", "true");
                } else {
                    Log.d("ygthygh", "false");
                }
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicks[0]++;
                clickCount.setText(clicks[0] + "");
                if(pressed[0] == -1){
                    Log.d("Button", "Click1-->> *" + array[3]);
                    button4.setBackgroundResource((Integer) map.get(array[3]));
                    pressed[0] = array[3];
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            home.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    button4.setBackgroundResource(R.drawable.back1);
                                }
                            });


                        }
                    }, 500);


                }else{
                    if (pressed[0] == array[3]){
                        Log.d("Button", "valio");
                        button4.setBackgroundResource((Integer) map.get(array[3]));
                        for(int i = 0; i < array.length; i++){
                            if(array[i] == pressed[0]){
                                Timer timer = new Timer();
                                final int finalI = i;
                                timer.schedule(new TimerTask() {

                                    @Override
                                    public void run() {
                                        home.runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                buttons[finalI].setVisibility(View.GONE);
                                            }
                                        });


                                    }
                                }, 500);
                            }
                        }
                        pressed[0] = -1;
                    }else{
                        Log.d("Button", "nepataikei");
                        button4.setBackgroundResource((Integer) map.get(array[3]));
                        pressed[0] = -1;
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {

                            @Override
                            public void run() {
                                home.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        button4.setBackgroundResource(R.drawable.back1);
                                    }
                                });


                            }
                        }, 500);

                    }
                }
                if(isGameOver(buttons)) {
                    if(count>clicks[0]){
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putInt(getString(R.string.counter), clicks[0]);
                        editor.commit();
                        hsValue.setText(clicks[0] +"");
                    }
                    Log.d("ygthygh", "true");
                } else {
                    Log.d("ygthygh", "false");
                }
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicks[0]++;
                clickCount.setText(clicks[0] + "");
                if(pressed[0] == -1){
                    Log.d("Button", "Click1-->> *" + array[4]);
                    button5.setBackgroundResource((Integer) map.get(array[4]));
                    pressed[0] = array[4];
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            home.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    button5.setBackgroundResource(R.drawable.back1);
                                }
                            });


                        }
                    }, 500);


                }else{
                    if (pressed[0] == array[4]){
                        Log.d("Button", "valio");
                        button5.setBackgroundResource((Integer) map.get(array[4]));
                        for(int i = 0; i < array.length; i++){
                            if(array[i] == pressed[0]){
                                Timer timer = new Timer();
                                final int finalI = i;
                                timer.schedule(new TimerTask() {

                                    @Override
                                    public void run() {
                                        home.runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                buttons[finalI].setVisibility(View.GONE);
                                            }
                                        });


                                    }
                                }, 500);
                            }
                        }
                        pressed[0] = -1;
                    }else{
                        Log.d("Button", "nepataikei");
                        button5.setBackgroundResource((Integer) map.get(array[4]));
                        pressed[0] = -1;
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {

                            @Override
                            public void run() {
                                home.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        button5.setBackgroundResource(R.drawable.back1);
                                    }
                                });


                            }
                        }, 500);

                    }
                }
                if(isGameOver(buttons)) {
                    if(count>clicks[0]){
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putInt(getString(R.string.counter), clicks[0]);
                        editor.commit();
                        hsValue.setText(clicks[0] +"");
                    }
                    Log.d("ygthygh", "true");
                } else {
                    Log.d("ygthygh", "false");
                }
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicks[0]++;
                clickCount.setText(clicks[0] + "");
                if(pressed[0] == -1){
                    Log.d("Button", "Click1-->> *" + array[5]);
                    button6.setBackgroundResource((Integer) map.get(array[5]));
                    pressed[0] = array[5];
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            home.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    button6.setBackgroundResource(R.drawable.back1);
                                }
                            });


                        }
                    }, 500);


                }else{
                    if (pressed[0] == array[5]){
                        Log.d("Button", "valio");
                        button6.setBackgroundResource((Integer) map.get(array[5]));
                        for(int i = 0; i < array.length; i++){
                            if(array[i] == pressed[0]){
                                Timer timer = new Timer();
                                final int finalI = i;
                                timer.schedule(new TimerTask() {

                                    @Override
                                    public void run() {
                                        home.runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                buttons[finalI].setVisibility(View.GONE);
                                            }
                                        });


                                    }
                                }, 500);
                            }
                        }
                        pressed[0] = -1;
                    }else{
                        Log.d("Button", "nepataikei");
                        button6.setBackgroundResource((Integer) map.get(array[5]));
                        pressed[0] = -1;
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {

                            @Override
                            public void run() {
                                home.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        button6.setBackgroundResource(R.drawable.back1);
                                    }
                                });


                            }
                        }, 500);

                    }
                }
                if(isGameOver(buttons)) {
                    if(count>clicks[0]){
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putInt(getString(R.string.counter), clicks[0]);
                        editor.commit();
                        hsValue.setText(clicks[0] +"");
                    }
                    Log.d("ygthygh", "true");
                } else {
                    Log.d("ygthygh", "false");
                }
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicks[0]++;
                clickCount.setText(clicks[0] + "");
                if(pressed[0] == -1){
                    Log.d("Button", "Click1-->> *" + array[6]);
                    button7.setBackgroundResource((Integer) map.get(array[6]));
                    pressed[0] = array[6];
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            home.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    button7.setBackgroundResource(R.drawable.back1);
                                }
                            });


                        }
                    }, 500);


                }else{
                    if (pressed[0] == array[6]){
                        Log.d("Button", "valio");
                        button7.setBackgroundResource((Integer) map.get(array[6]));
                        for(int i = 0; i < array.length; i++){
                            if(array[i] == pressed[0]){
                                Timer timer = new Timer();
                                final int finalI = i;
                                timer.schedule(new TimerTask() {

                                    @Override
                                    public void run() {
                                        home.runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                buttons[finalI].setVisibility(View.GONE);
                                            }
                                        });


                                    }
                                }, 500);
                            }
                        }
                        pressed[0] = -1;
                    }else{
                        Log.d("Button", "nepataikei");
                        button7.setBackgroundResource((Integer) map.get(array[6]));
                        pressed[0] = -1;
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {

                            @Override
                            public void run() {
                                home.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        button7.setBackgroundResource(R.drawable.back1);
                                    }
                                });


                            }
                        }, 500);

                    }
                }
                if(isGameOver(buttons)) {
                    if(count>clicks[0]){
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putInt(getString(R.string.counter), clicks[0]);
                        editor.commit();
                        hsValue.setText(clicks[0] +"");
                    }
                    Log.d("ygthygh", "true");
                } else {
                    Log.d("ygthygh", "false");
                }
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicks[0]++;
                clickCount.setText(clicks[0] + "");
                if(pressed[0] == -1){
                    Log.d("Button", "Click1-->> *" + array[7]);
                    button8.setBackgroundResource((Integer) map.get(array[7]));
                    pressed[0] = array[7];
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            home.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    button8.setBackgroundResource(R.drawable.back1);
                                }
                            });


                        }
                    }, 500);


                }else{
                    if (pressed[0] == array[7]){
                        Log.d("Button", "valio");
                        button8.setBackgroundResource((Integer) map.get(array[7]));
                        for(int i = 0; i < array.length; i++){
                            if(array[i] == pressed[0]){
                                Timer timer = new Timer();
                                final int finalI = i;
                                timer.schedule(new TimerTask() {

                                    @Override
                                    public void run() {
                                        home.runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                buttons[finalI].setVisibility(View.GONE);
                                            }
                                        });


                                    }
                                }, 500);
                            }
                        }
                        pressed[0] = -1;
                    }else{
                        Log.d("Button", "nepataikei");
                        button8.setBackgroundResource((Integer) map.get(array[7]));
                        pressed[0] = -1;
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {

                            @Override
                            public void run() {
                                home.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        button8.setBackgroundResource(R.drawable.back1);
                                    }
                                });


                            }
                        }, 500);

                    }
                }
                if(isGameOver(buttons)) {
                    if(count>clicks[0]){
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putInt(getString(R.string.counter), clicks[0]);
                        editor.commit();
                        hsValue.setText(clicks[0] +"");
                    }
                    Log.d("ygthygh", "true");
                } else {
                    Log.d("ygthygh", "false");
                }
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicks[0]++;
                clickCount.setText(clicks[0] + "");
                if(pressed[0] == -1){
                    Log.d("Button", "Click1-->> *" + array[8]);
                    button9.setBackgroundResource((Integer) map.get(array[8]));
                    pressed[0] = array[8];
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            home.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    button9.setBackgroundResource(R.drawable.back1);
                                }
                            });


                        }
                    }, 500);


                }else{
                    if (pressed[0] == array[8]){
                        Log.d("Button", "valio");
                        button9.setBackgroundResource((Integer) map.get(array[8]));
                        for(int i = 0; i < array.length; i++){
                            if(array[i] == pressed[0]){
                                Timer timer = new Timer();
                                final int finalI = i;
                                timer.schedule(new TimerTask() {

                                    @Override
                                    public void run() {
                                        home.runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                buttons[finalI].setVisibility(View.GONE);
                                            }
                                        });


                                    }
                                }, 500);
                            }
                        }
                        pressed[0] = -1;
                    }else{
                        Log.d("Button", "nepataikei");
                        button9.setBackgroundResource((Integer) map.get(array[8]));
                        pressed[0] = -1;
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {

                            @Override
                            public void run() {
                                home.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        button9.setBackgroundResource(R.drawable.back1);
                                    }
                                });


                            }
                        }, 500);

                    }
                }
                if(isGameOver(buttons)) {
                    if(count>clicks[0]){
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putInt(getString(R.string.counter), clicks[0]);
                        editor.commit();
                        hsValue.setText(clicks[0] +"");
                    }
                    Log.d("ygthygh", "true");
                } else {
                    Log.d("ygthygh", "false");
                }
            }
        });

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicks[0]++;
                clickCount.setText(clicks[0] + "");
                if(pressed[0] == -1){
                    Log.d("Button", "Click1-->> *" + array[9]);
                    button10.setBackgroundResource((Integer) map.get(array[9]));
                    pressed[0] = array[9];
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            home.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    button10.setBackgroundResource(R.drawable.back1);
                                }
                            });


                        }
                    }, 500);


                }else{
                    if (pressed[0] == array[9]){
                        Log.d("Button", "valio");
                        button10.setBackgroundResource((Integer) map.get(array[9]));
                        for(int i = 0; i < array.length; i++){
                            if(array[i] == pressed[0]){
                                Timer timer = new Timer();
                                final int finalI = i;
                                timer.schedule(new TimerTask() {

                                    @Override
                                    public void run() {
                                        home.runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                buttons[finalI].setVisibility(View.GONE);
                                            }
                                        });


                                    }
                                }, 500);
                            }
                        }
                        pressed[0] = -1;
                    }else{
                        Log.d("Button", "nepataikei");
                        button10.setBackgroundResource((Integer) map.get(array[9]));
                        pressed[0] = -1;
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {

                            @Override
                            public void run() {
                                home.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        button10.setBackgroundResource(R.drawable.back1);
                                    }
                                });


                            }
                        }, 500);

                    }
                }
                if(isGameOver(buttons)) {
                    if(count>clicks[0]){
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putInt(getString(R.string.counter), clicks[0]);
                        editor.commit();
                        hsValue.setText(clicks[0] +"");
                    }
                    Log.d("ygthygh", "true");
                } else {
                    Log.d("ygthygh", "false");
                }
            }
        });

        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicks[0]++;
                clickCount.setText(clicks[0] + "");
                if(pressed[0] == -1){
                    Log.d("Button", "Click1-->> *" + array[10]);
                    button11.setBackgroundResource((Integer) map.get(array[10]));
                    pressed[0] = array[10];
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            home.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    button11.setBackgroundResource(R.drawable.back1);
                                }
                            });


                        }
                    }, 500);


                }else{
                    if (pressed[0] == array[10]){
                        Log.d("Button", "valio");
                        button11.setBackgroundResource((Integer) map.get(array[10]));
                        for(int i = 0; i < array.length; i++){
                            if(array[i] == pressed[0]){
                                Timer timer = new Timer();
                                final int finalI = i;
                                timer.schedule(new TimerTask() {

                                    @Override
                                    public void run() {
                                        home.runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                buttons[finalI].setVisibility(View.GONE);
                                            }
                                        });


                                    }
                                }, 500);
                            }
                        }
                        pressed[0] = -1;
                    }else{
                        Log.d("Button", "nepataikei");
                        button11.setBackgroundResource((Integer) map.get(array[10]));
                        pressed[0] = -1;
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {

                            @Override
                            public void run() {
                                home.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        button11.setBackgroundResource(R.drawable.back1);
                                    }
                                });


                            }
                        }, 500);

                    }
                }
                if(isGameOver(buttons)) {
                    if(count>clicks[0]){
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putInt(getString(R.string.counter), clicks[0]);
                        editor.commit();
                        hsValue.setText(clicks[0] +"");
                    }
                    Log.d("ygthygh", "true");
                } else {
                    Log.d("ygthygh", "false");
                }
            }
        });

        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicks[0]++;
                clickCount.setText(clicks[0] + "");
                if(pressed[0] == -1){
                    Log.d("Button", "Click1-->> *" + array[11]);
                    button12.setBackgroundResource((Integer) map.get(array[11]));
                    pressed[0] = array[11];
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            home.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    button12.setBackgroundResource(R.drawable.back1);
                                }
                            });


                        }
                    }, 500);


                }else{
                    if (pressed[0] == array[11]){
                        Log.d("Button", "valio");
                        button12.setBackgroundResource((Integer) map.get(array[11]));
                        for(int i = 0; i < array.length; i++){
                            if(array[i] == pressed[0]){
                                Timer timer = new Timer();
                                final int finalI = i;
                                timer.schedule(new TimerTask() {

                                    @Override
                                    public void run() {
                                        home.runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                buttons[finalI].setVisibility(View.GONE);
                                            }
                                        });


                                    }
                                }, 500);
                            }
                        }
                        pressed[0] = -1;
                    }else{
                        Log.d("Button", "nepataikei");
                        button12.setBackgroundResource((Integer) map.get(array[11]));
                        pressed[0] = -1;
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {

                            @Override
                            public void run() {
                                home.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        button12.setBackgroundResource(R.drawable.back1);
                                    }
                                });


                            }
                        }, 500);

                    }
                }
                if(isGameOver(buttons)) {
                    if(count>clicks[0]){
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putInt(getString(R.string.counter), clicks[0]);
                        editor.commit();
                        hsValue.setText(clicks[0] +"");
                    }
                    Log.d("ygthygh", "true");
                } else {
                    Log.d("ygthygh", "false");
                }
            }
        });

        button17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RotateAnimation rotate = new RotateAnimation(0, 720,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                rotate.setDuration(1000);
                rotate.setInterpolator(new AccelerateDecelerateInterpolator());
                button17.startAnimation(rotate);


                pressed[0] = -1;

                for (int i = array.length-1; i>0; i-- ) {
                    Random random = new Random();
                    int randomInt = random.nextInt(i);

                    int tempInt = array[randomInt];
                    array[randomInt] = array[i];
                    array[i] = tempInt;

                }


                if(firsthighscore[0] == -1 ){
                    hsCount[0] = clicks[0];
                    firsthighscore[0] = 0;
                    scoreValue.setText(clicks[0] + "");
                    hsValue.setText(clicks[0] + "");
                    Log.d("Reset", "Your Score is " + clicks[0]);

                }else {
                    if(hsCount[0]<clicks[0]) {

                        scoreValue.setText(clicks[0] + "");
                    }else{
                        hsCount[0]=clicks[0];

                        firsthighscore[0] = 0;

                        hsValue.setText(hsCount[0] + "");
                        scoreValue.setText(clicks[0] + "");
                        Log.d("Reset", "Your High Score is " + hsCount[0]);
                    }
                }


                clicks[0] = 0;

                clickCount.setText(clicks[0] + "");


                Timer timer = new Timer();
                timer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        home.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                buttons[0].setVisibility(View.VISIBLE);
                                buttons[1].setVisibility(View.VISIBLE);
                                buttons[2].setVisibility(View.VISIBLE);
                                buttons[3].setVisibility(View.VISIBLE);
                                buttons[4].setVisibility(View.VISIBLE);
                                buttons[5].setVisibility(View.VISIBLE);
                                buttons[6].setVisibility(View.VISIBLE);
                                buttons[7].setVisibility(View.VISIBLE);
                                buttons[8].setVisibility(View.VISIBLE);
                                buttons[9].setVisibility(View.VISIBLE);
                                buttons[10].setVisibility(View.VISIBLE);
                                buttons[11].setVisibility(View.VISIBLE);
                            }
                        });


                    }
                }, 1000);





                for(Button button1 : buttons) {
                    button1.setText("");
                    button1.setBackgroundResource(R.drawable.back1);
                }



                /*hsCount[0] = count;
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt(getString(R.string.counter), 100);
                editor.commit();
                Log.i("save", "pavyko irasyt" + count);*/


            }
        });



    }



}
