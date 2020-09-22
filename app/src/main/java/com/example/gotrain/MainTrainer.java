package com.example.gotrain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainTrainer extends AppCompatActivity {

    ImageView correctImage, wrongImage, wellDone, imageView5, imageView4;
    ConstraintLayout myQuestions;
    GridLayout myGrid;
    TextView timerText, scoreBoard, questions;
    Button tryAgain, answer1, answer2, answer3, answer4;
    ArrayList<Integer> answers = new ArrayList<>();
    SeekBar seekBar;
    CountDownTimer countDownTimer;
    int locationOfCorrectAnswer;
    int numberOfQuestions = 0;
    int score = 0;
    private Boolean activeCounter = false;

    public void startAnswers() {
        answer1.setEnabled(true);
        answer2.setEnabled(true);
        answer3.setEnabled(true);
        answer4.setEnabled(true);
    }
    public void stopAnswers() {
        answer1.setEnabled(false);
        answer2.setEnabled(false);
        answer3.setEnabled(false);
        answer4.setEnabled(false);
    }

    public void myTimer() {

        countDownTimer = new CountDownTimer( seekBar.getProgress() * 1000 + 100, 1000) {
            @Override
            public void onTick(long l) {
                updateTimer((int) l / 1000);
                // timerText.setText(String.valueOf(l / 1000) + "s");
            }

            @Override
            public void onFinish() {
                correctImage.setVisibility(View.INVISIBLE);
                wrongImage.setVisibility(View.INVISIBLE);
                wellDone.setVisibility(View.VISIBLE);
                tryAgain.setVisibility(View.VISIBLE);
                seekBar.setEnabled(true);
                stopAnswers();
            }
        }.start();
    }


    public void PlayAgain(View view) {
        score = 0;
        numberOfQuestions = 0;
        //timerText.setText("30s");
        scoreBoard.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        newQuestion();
        tryAgain.setVisibility(View.INVISIBLE);
        correctImage.setVisibility(View.INVISIBLE);
        wrongImage.setVisibility(View.INVISIBLE);
        wellDone.setVisibility(View.INVISIBLE);
        startAnswers();

        myTimer();
        newQuestion();
    }

    public void chooseAnswer(View view) {
        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
            correctImage.setVisibility(View.VISIBLE);
            wrongImage.setVisibility(View.INVISIBLE);
            wellDone.setVisibility(View.INVISIBLE);
            score++;
        } else {
            correctImage.setVisibility(View.INVISIBLE);
            wellDone.setVisibility(View.INVISIBLE);
            wrongImage.setVisibility(View.VISIBLE);
        }
        numberOfQuestions++;
        scoreBoard.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        newQuestion();
    }

    public void newQuestion() {
        Random random = new Random();

        int a = random.nextInt(21);
        int b = random.nextInt(21);

        questions.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = random.nextInt(4);
        answers.clear();

        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(a + b);
            } else {
                int wrongAnswer = random.nextInt(41);

                while (wrongAnswer == a + b) {
                    wrongAnswer = random.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }

        answer1.setText(Integer.toString(answers.get(0)));
        answer2.setText(Integer.toString(answers.get(1)));
        answer3.setText(Integer.toString(answers.get(2)));
        answer4.setText(Integer.toString(answers.get(3)));

    }

    public void updateTimer(int secondsLeft) {
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - (minutes * 60);

        String secondsString = Integer.toString(seconds);

        if (seconds <= 9) {
            secondsString = "0" + secondsString;
        }

        timerText.setText(Integer.toString(minutes) + ":" + secondsString);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_trainer);

        timerText = findViewById(R.id.timerText);
        questions = findViewById(R.id.questions);
        scoreBoard = findViewById(R.id.scoreBoard);
        tryAgain = findViewById(R.id.tryAgain);
        correctImage = findViewById(R.id.correctImage);
        wrongImage = findViewById(R.id.wrongImage);
        wellDone = findViewById(R.id.wellDone);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        myQuestions = findViewById(R.id.myQuestions);
        myGrid = findViewById(R.id.myGrid);
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        answer4 = findViewById(R.id.answer4);
        seekBar = findViewById(R.id.seekBar);

        //myTimer();
        tryAgain.setVisibility(View.VISIBLE);
        wellDone.setVisibility(View.INVISIBLE);

        seekBar.setMax(600);
        seekBar.setProgress(30);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekBar.setEnabled(false);
            }
        });

    }
}