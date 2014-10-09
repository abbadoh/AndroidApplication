package activities;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import java.util.HashMap;
import com.tp.gumo.activity.R;
import fragments.DirectionChooserFragment;

public class DirectionChooserActivity extends FragmentActivity implements DirectionChooserFragment.OnItemSelectedListener {
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
        System.out.println(direction);
        Intent i = new Intent(DirectionChooserActivity.this, TranslationActivity.class);
        i.putExtra("direction", direction);
        startActivity(i);

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
