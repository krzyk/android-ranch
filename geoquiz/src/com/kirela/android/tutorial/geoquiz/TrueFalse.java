package com.kirela.android.tutorial.geoquiz;

public class TrueFalse {
    private final int questionId;
    private final boolean trueCorrect;

    public TrueFalse(final int id, final boolean correct) {
        questionId = id;
        trueCorrect = correct;
    }

    public int getQuestionId() {
        return questionId;
    }

    public boolean isTrueCorrect() {
        return trueCorrect;
    }
}
