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
import android.widget.TextView;

import com.tp.gumo.activity.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import activities.DirectionChooserActivity;

public class DirectionChooserFragment extends ListFragment {
    private OnItemSelectedListener mCallback;
    String[] items = new String[] { };
    HashMap<String, String> languages;

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        String clickedKey = null;

        for (String key : languages.keySet()) {
            if (languages.get(key).equals(items[position])) {
                clickedKey = key;
                break;
            }
        }

        if (clickedKey != null) {
            mCallback.onDirectionSelected(clickedKey);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setListAdapter(new DirectionChooserAdapter(items));

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private class DirectionChooserAdapter extends ArrayAdapter<String> {
        public DirectionChooserAdapter(String[] objects) {
            super(getActivity(), 0, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.text = (TextView) convertView.findViewById(R.id.language_direction_text);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.text.setText(getItem(position));

            return convertView;
        }

        private class ViewHolder {
            public TextView text;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        Intent intent = activity.getIntent();

        languages = (HashMap<String, String>)intent.getSerializableExtra("languages");

        if (languages != null) {
            List<String> languageDirections = new ArrayList<String>(languages.values());
            List<String> languageDirectionsSorted = new ArrayList<String>();

            Collections.sort(languageDirections, new LanguageDirectionsComparator());

            for (String languageDirection : languageDirections) {
                languageDirectionsSorted.add(languageDirection);
            }

            items = languageDirectionsSorted.toArray(new String[0]);
        }

        try {
            mCallback = (OnItemSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnItemSelectedListener");
        }
    }

    public interface OnItemSelectedListener {
        public void onDirectionSelected(String direction);
    }

    class LanguageDirectionsComparator implements Comparator<String> {
        @Override
        public int compare(String lhs, String rhs) {
            String[] lhs_parsed = lhs.split(" -> ", 2);
            String[] rhs_parsed = rhs.split(" -> ", 2);

            if (lhs_parsed[0].equals("Русский") && !rhs_parsed[0].equals("Русский")) {
                return -1;
            } else if (!lhs_parsed[0].equals("Русский") && rhs_parsed[0].equals("Русский")) {
                return 1;
            }

            if (lhs_parsed[1].equals("Русский") && !rhs_parsed[1].equals("Русский")) {
                return -1;
            } else if (!lhs_parsed[1].equals("Русский") && rhs_parsed[1].equals("Русский")) {
                return 1;
            }

            int compare = lhs_parsed[0].compareTo(rhs_parsed[0]);

            if (compare == 0) {
                compare = lhs_parsed[1].compareTo(rhs_parsed[1]);
            }

            return compare;
        }
    }
}
