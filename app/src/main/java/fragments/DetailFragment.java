package fragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.tp.gumo.androidapplication.R;

import java.util.HashMap;

import network.YandexTranslateApi;

/**
 * Created by r.kildiev on 11.09.2014.
 */
public class DetailFragment extends Fragment {

    public static String NEWS_NO = "news_no";
    TextView newsDetailTextview;

    public static DetailFragment getInstance(int news_no) {
        DetailFragment detailFragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(NEWS_NO, news_no);
        detailFragment.setArguments(bundle);
        return detailFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.news_detail_fargment, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        newsDetailTextview = (TextView) view.findViewById(R.id.news_detail_textview);
        AsyncTaskExample task = new AsyncTaskExample();
        task.execute();
        Button backButton = (Button) view.findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(DetailFragment.this).commit();
            }
        });
        newsDetailTextview.setText("Детализация новости номер " + getArguments().getInt(NEWS_NO));
    }

    public class AsyncTaskExample extends AsyncTask<Integer,Integer, Void> {
        ProgressDialog progressDialog;
        String wind;
        @Override
        protected void onPreExecute() {
//            super.onPreExecute();
//
//            progressDialog = new ProgressDialog(MainActivity.this);
//            progressDialog.setMax(10);
//            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//            //progressDialog.setIndeterminate(true);
//
//            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Integer... params) {
            YandexTranslateApi yandexTranslateApi = new YandexTranslateApi();
//                wind = yandexTranslateApi.urlConnection();
            HashMap<String, String> languages = yandexTranslateApi.getLanguages("ru");

//            for(int i = 0; i < 10; i++) {
//                try {
//                    publishProgress(i);
//                    // progressDialog.setProgress(i);
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
            return null;
        }
//        protected void onProgressUpdate(Integer... values) {
//            progressDialog.setProgress(values[0]);

//        }

        @Override
        protected void onPostExecute(Void aVoid) {
              newsDetailTextview.setText(wind);
//            mEditText.setText("AsyncTask завершен");
//            progressBar.setVisibility(View.INVISIBLE);
//            progressDialog.dismiss();
        }
    }
}
