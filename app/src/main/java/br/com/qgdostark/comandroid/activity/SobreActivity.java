package br.com.qgdostark.comandroid.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import br.com.qgdostark.comandroid.R;

public class SobreActivity extends AppCompatActivity {

    protected ActionBar action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);
        this.action = getSupportActionBar();
        this.action.setDisplayHomeAsUpEnabled(true);
        this.action.setTitle("Sobre");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
