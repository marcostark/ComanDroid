package br.com.qgdostark.comandroid.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.badoualy.datepicker.DatePickerTimeline;

import br.com.qgdostark.comandroid.R;

public class RelatoriosActivity extends AppCompatActivity {

    protected ActionBar action;
    private DatePickerTimeline timeline;
    private int dia;
    private int mes;
    private int ano;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorios);
        this.action = getSupportActionBar();
        this.action.setTitle("Relatórios");
        this.action.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void onResume() {
        super.onResume();
        initDateRelatorios();
    }

    //Iniciando componente Datepicker e eventos
    public void initDateRelatorios(){
        timeline = (DatePickerTimeline) findViewById(R.id.datepicker);
//      timeline.setFirstVisibleDate(2017, Calendar.JULY, 24);
        timeline.setOnDateSelectedListener(new DatePickerTimeline.OnDateSelectedListener(){
            @Override
            public void onDateSelected(int year, int month, int day, int index) {
                dia = day;
                mes = month;
                ano = year;
                Toast.makeText(RelatoriosActivity.this,"Data: "+ dia + " / " +mes+" / "+ano, Toast.LENGTH_SHORT).show();
            }
        });

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
