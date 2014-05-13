package com.kirela.android.tutorial.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends Activity {
    private TextView answerTextView;
    private Button answerButton;

    public static final String EXTRA_ANSWER = CheatActivity.class.toString() + ".answerTrue";
    public static final String EXTRA_ANSWER_SHOWN = CheatActivity.class.toString() + ".answerShown";

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_cheat);
        final boolean answer = getIntent().getBooleanExtra(EXTRA_ANSWER, false);

        answerShown(false);
        answerTextView = (TextView) findViewById(R.id.answerText);
        answerButton = (Button) findViewById(R.id.showAnswer);
        answerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (answer) {
                    answerTextView.setText(R.string.true_button);
                } else {
                    answerTextView.setText(R.string.false_button);
                }
                answerShown(true);
            }
        });
    }

    private void answerShown(boolean shown) {
        setResult(RESULT_OK, new Intent().putExtra(EXTRA_ANSWER_SHOWN, shown));
    }
}
