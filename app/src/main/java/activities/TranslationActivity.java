package activities;


import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.tp.gumo.activity.R;

import java.util.HashMap;

import fragments.TranslationFragment;


public class TranslationActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.translation_activity);
        Intent intent = getIntent();

        String direction = (String)intent.getSerializableExtra("direction");
        HashMap<String, String> languages = (HashMap<String, String>)intent.getSerializableExtra("languages");

        TranslationFragment translationFragment = getFragment();

        translationFragment.setLanguages(languages);
        translationFragment.setDirection(direction);
    }

    private TranslationFragment getFragment() {
        return (TranslationFragment)getFragmentManager().findFragmentById(R.id.traslation_fragment);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        TranslationFragment translationFragment = getFragment();

        outState.putString("direction", translationFragment.getDirection());
        outState.putSerializable("languages", translationFragment.getLanguages());
        outState.putSerializable("translated_text", translationFragment.getTranslatedText());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        String direction = savedInstanceState.getString("direction");
        HashMap<String, String> languages = (HashMap<String, String>)savedInstanceState.getSerializable("languages");
        String translatedText = savedInstanceState.getString("translated_text");

        TranslationFragment translationFragment = getFragment();

        translationFragment.setLanguages(languages);
        translationFragment.setDirection(direction);
        translationFragment.setTranslatedText(translatedText);
    }
}
