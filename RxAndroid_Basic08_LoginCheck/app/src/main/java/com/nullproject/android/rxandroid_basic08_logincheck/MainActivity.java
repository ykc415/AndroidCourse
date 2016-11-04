package com.nullproject.android.rxandroid_basic08_logincheck;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;

import rx.Observable;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSign = (Button) findViewById(R.id.button_signin);

        btnSign.setEnabled(false);

        Observable<TextViewTextChangeEvent> idObs = RxTextView.textChangeEvents((EditText) findViewById(R.id.editText_id));
        Observable<TextViewTextChangeEvent> pwObs = RxTextView.textChangeEvents((EditText) findViewById(R.id.editText_pw));

        Observable.combineLatest(idObs, pwObs,
                (idChanges, pwChanges) -> {
                    boolean idCheck = idChanges.text().length() >= 5;
                    boolean pwCheck = pwChanges.text().length() >= 8;
                    return idCheck && pwCheck;
                })
                .subscribe(
                        checkFlag -> btnSign.setEnabled(checkFlag)
                );
    }
}
