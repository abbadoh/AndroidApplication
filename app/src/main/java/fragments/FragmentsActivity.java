package fragments;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.tp.gumo.androidapplication.R;


/**
 * Created by r.kildiev on 11.09.2014.
 */
public class FragmentsActivity extends FragmentActivity implements NewsListFragment.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragments_activity);
    }

    @Override
    public void onArticleSelected(int position) {
        DetailFragment newFragment = DetailFragment.getInstance(position);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.add(R.id.fragment_container, newFragment);
        //transaction.addToBackStack(null);

        transaction.commit();
    }
}
