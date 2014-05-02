package com.kirela.android.tutorial.geoquiz;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends Activity {
    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private int currentQuestion = 0;

    private final TrueFalse[] questionBank = new TrueFalse[] {
        new TrueFalse(R.string.question_turkey, true),
        new TrueFalse(R.string.question_poland, false),
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        updateQuestion();
        trueButton = (Button) findViewById(R.id.true_button);
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (questionBank[currentQuestion].isTrueCorrect()) {
                    Toast.makeText(QuizActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(QuizActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
                }
            }
        });
        falseButton = (Button) findViewById(R.id.false_button);
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (!questionBank[currentQuestion].isTrueCorrect()) {
                    Toast.makeText(QuizActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(QuizActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
                }
            }
        });
        nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                currentQuestion = (currentQuestion + 1) % questionBank.length;
                updateQuestion();
            }
        });
    }

    private void updateQuestion() {
        TextView question = (TextView) findViewById(R.id.question_text_view);
        question.setText(questionBank[currentQuestion].getQuestionId());
    }
}
