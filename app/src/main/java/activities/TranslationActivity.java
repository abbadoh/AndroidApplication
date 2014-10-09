package activities;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.tp.gumo.activity.R;

import java.util.HashMap;


public class TranslationActivity extends FragmentActivity {

    private String direction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.translation_activity);
        Intent intent = getIntent();
        direction = (String)intent.getSerializableExtra("direction");
    }

}
