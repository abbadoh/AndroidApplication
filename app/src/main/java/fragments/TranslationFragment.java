package fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.tp.gumo.activity.R;

import java.util.HashMap;

import activities.TranslationActivity;
import api.YandexTranslateApi;

public class TranslationFragment extends Fragment implements View.OnClickListener {
    private String direction;
    private HashMap<String, String> languages;
    private EditText mEditText;
    private Button mTranslateButton;
    private Button mSwitchLangButton;
    private TextView mTranslatedText;

    public void setTranslatedText(String value) {
        mTranslatedText.setText(value);
    }

    public String getTranslatedText() {
        return mTranslatedText.getText().toString();
    }

    public HashMap<String, String> getLanguages() {
        return languages;
    }

    public String getDirection() {
        return direction;
    }

    public void setLanguages(HashMap<String, String> languages) {
        this.languages = languages;
    }

    public void setDirection(String direction) {
        this.direction = direction;

        View view = getView();

        if (view != null) {
            TextView languageDirection = (TextView) getView().findViewById(R.id.language_direction_textview);

            if (languageDirection != null) {
                String languageDirectionStr = languages.get(direction);
                languageDirection.setText(languageDirectionStr);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.translation_fragment, null);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTranslateButton = (Button) view.findViewById(R.id.translate_button);
        mTranslatedText = (TextView) view.findViewById(R.id.translated_text);
        mSwitchLangButton = (Button) view.findViewById(R.id.switch_lang_button);
        mEditText = (EditText) view.findViewById(R.id.text_to_translate);
        //mTranslatedText.setText("Ждите перевода...");
        mTranslateButton.setOnClickListener(this);
        mSwitchLangButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mTranslateButton) {
            new TranslateAsyncTask().execute();
        }
        else if(v == mSwitchLangButton) {
            String[] args = direction.split("-", 2);

            setDirection(String.format("%s-%s", args[1], args[0]));
        }
    }

    private class TranslateAsyncTask extends AsyncTask<Integer, Void, String> {
        @Override
        protected String doInBackground(Integer... params) {
            YandexTranslateApi api = new YandexTranslateApi();
            return api.translate(mEditText.getText().toString(), direction);
        }

        @Override
        protected void onPostExecute(String result) {
            mTranslatedText.setText(result);
        }
    }
}
