package com.tp.gumo.androidapplication;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import fragments.DetailFragment;
import fragments.NewsListFragment;
import network.Network;


public class MyActivity extends FragmentActivity implements NewsListFragment.OnItemSelectedListener {

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
