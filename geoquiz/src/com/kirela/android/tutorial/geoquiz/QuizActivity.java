package com.kirela.android.tutorial.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends Activity {
    private static final String TAG = QuizActivity.class.toString();
    private static final String STATE_KEY = QuizActivity.class.toString() + ".STATE";
    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private Button cheatButton;
    private int currentQuestion = 0;
    private boolean cheater = false;

    private final TrueFalse[] questionBank = new TrueFalse[] {
        new TrueFalse(R.string.question_turkey, true),
        new TrueFalse(R.string.question_poland, false),
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            currentQuestion = savedInstanceState.getInt(STATE_KEY, 0);
        }
        setContentView(R.layout.activity_quiz);
        updateQuestion();
        trueButton = (Button) findViewById(R.id.true_button);
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                checkAnswer(true);
            }
        });
        falseButton = (Button) findViewById(R.id.false_button);
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                checkAnswer(false);
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
        cheatButton = (Button) findViewById(R.id.cheat_button);
        cheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                startActivityForResult(
                    new Intent(QuizActivity.this, CheatActivity.class)
                    .putExtra(CheatActivity.EXTRA_ANSWER, questionBank[currentQuestion].isTrueCorrect()),
                    0
                );
            }
        });
    }

    private void checkAnswer(boolean trueSelected) {

        int messageId;
        if (cheater) {
           messageId = R.string.judgment_toast;
        } else if (questionBank[currentQuestion].isTrueCorrect() && trueSelected) {
            messageId =  R.string.correct_toast;
        } else if (!questionBank[currentQuestion].isTrueCorrect() && !trueSelected) {
            messageId =  R.string.correct_toast;
        } else {
            messageId = R.string.incorrect_toast;
        }
        Toast.makeText(this, messageId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Log.i(TAG, "onSaveInstanceState");
        bundle.putInt(STATE_KEY, currentQuestion);
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        if (data == null) {
            return;
        }
        cheater = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false);
    }

    private void updateQuestion() {
        TextView question = (TextView) findViewById(R.id.question_text_view);
        question.setText(questionBank[currentQuestion].getQuestionId());
        cheater = false;
    }

}
