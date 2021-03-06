package ru.avdeev.android.a3_4_2_switching_margins;

import androidx.annotation.StyleRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Spinner languageSpinner;
    Spinner marginSpinner;
    String choiceLanguage;
    int choiceMargin;
    TextView textView;
    private static @StyleRes int sTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTheme(sTheme);
        languageSpinner=findViewById(R.id.languageSpinner);
        marginSpinner=findViewById(R.id.marginSpinner);
        textView=findViewById(R.id.textView);
        givLanguage();
        givMargin();
    }

    public void onClick(View view) {
        if (choiceLanguage.equals("Russian")) {
            Locale locale = new Locale("ru");
            Configuration config = new Configuration();
            config.setLocale(locale);
            getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        } else {
            if (choiceLanguage.equals("Английский")) {
                Locale locale = new Locale("en");
                Configuration config = new Configuration();
                config.setLocale(locale);
                getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
            }
        }
        switch (choiceMargin) {
            case 0:
                sTheme = R.style.SmallMargin;
                break;
            case 1:
                sTheme = R.style.MeddleMargin;
                break;
            case 2:
                sTheme = R.style.BigMargin;
                break;
            default:
                break;
        }
        recreate();
    }
    private void givLanguage () {
        ArrayAdapter<CharSequence> adapterLanguages = ArrayAdapter.createFromResource(this, R.array.languages, android.R.layout.simple_spinner_item);
        adapterLanguages.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageSpinner.setAdapter(adapterLanguages);

        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String[] choiceLanguages = getResources().getStringArray(R.array.languages);
                choiceLanguage = choiceLanguages[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void givMargin() {
        ArrayAdapter<CharSequence> adapterMargins = ArrayAdapter.createFromResource(this, R.array.margins, android.R.layout.simple_spinner_item);
        adapterMargins.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        marginSpinner.setAdapter(adapterMargins);

        marginSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String[] choiceMargins = getResources().getStringArray(R.array.margins);
                choiceMargin = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //showMyMessage("Ничего не выбрано", MainActivity.this);
            }
        });
    }
}