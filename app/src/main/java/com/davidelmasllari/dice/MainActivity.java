package com.davidelmasllari.dice;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.crashlytics.android.Crashlytics;
import com.davidelmasllari.dice.observers.DiceLifeCycleObserver;
import com.davidelmasllari.dice.utils.LogUtil;
import com.davidelmasllari.dice.viewModel.DiceViewModel;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private DiceViewModel viewModel;
    private static final int DEFAULT_DICE_SELECTION = 3;

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fabric.with(this, new Crashlytics());
        //add drawable resources to arrayList, depending on what color the user..
        // ..has selected for the dice
        imageView = findViewById(R.id.imageView);
        //Init viewModel.
        viewModel = ViewModelProviders.of(this).get(DiceViewModel.class);

        viewModel.getDiceValue().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                imageView.setImageResource(integer);
                LogUtil.d("dave_log", "MainActivity: onChanged: ");
            }
        });

        imageView.setImageResource(viewModel.getDiceArray().get(DEFAULT_DICE_SELECTION));
        //Init lifecycle observer.
        getLifecycle().addObserver(new DiceLifeCycleObserver());
    }

    //imageview onclick
    public void diceOnclick(View view) {
        viewModel.rollDice();
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.onResumeEvent();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewModel.onDestroyEvent();
    }

}
