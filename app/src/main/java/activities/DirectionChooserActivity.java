package activities;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import java.util.HashMap;
import com.tp.gumo.activity.R;
import fragments.DirectionChooserFragment;

public class DirectionChooserActivity extends FragmentActivity implements DirectionChooserFragment.OnItemSelectedListener {
    private String direction;
    private HashMap<String, String> languages;
    public HashMap<String, String> getLanguages() {
        return languages;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.direction_chooser_activity);

        Intent intent = getIntent();
        HashMap<String, String> languages = (HashMap<String, String>)intent.getSerializableExtra("languages");

        if (languages != null) {
            this.languages = languages;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onDirectionSelected(String direction) {
        Fragment tt = getFragmentManager().findFragmentById(R.id.direction_chooser_fragment);
        View tt2 = findViewById(R.id.direction_chooser_fragment);

        Intent i = new Intent(DirectionChooserActivity.this, TranslationActivity.class);

        i.putExtra("direction", direction);
        i.putExtra("languages", languages);
        startActivity(i);
        this.direction = direction;

//        DetailFragment newFragment = DetailFragment.getInstance(position);
//
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//
//        transaction.add(R.id.fragment_container, newFragment);
//        //transaction.addToBackStack(null);
//
//        transaction.commit();
    }
}
