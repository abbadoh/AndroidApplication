package fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.HashMap;

import activities.DirectionChooserActivity;

public class DirectionChooserFragment extends ListFragment {
    private OnItemSelectedListener mCallback;
    String[] items = new String[] { };

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        mCallback.onDirectionSelected(position);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1, items);

        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        Intent intent = activity.getIntent();
        HashMap<String, String> languages = (HashMap<String, String>)intent.getSerializableExtra("languages");

        if (languages != null) {
            items = languages.values().toArray(new String[0]);
        }

        try {
            mCallback = (OnItemSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnItemSelectedListener");
        }
    }

    public interface OnItemSelectedListener {
        public void onDirectionSelected(int position);
    }
}
