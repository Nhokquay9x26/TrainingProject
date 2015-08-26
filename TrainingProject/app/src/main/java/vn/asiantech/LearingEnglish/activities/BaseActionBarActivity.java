package vn.asiantech.LearingEnglish.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

/**
 * Created by tientun on 3/5/15.
 */
@EActivity
public abstract class BaseActionBarActivity extends ActionBarActivity {
    protected String tag = this.getClass().getSimpleName();

    @AfterViews
    protected void initView(){
        this.afterView();
    }

    abstract void afterView();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
