package br.com.mest.quiz_qualopais;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnIniciarQuiz, btnSair;
    TextView txtNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        esconderTeclado();


        btnIniciarQuiz = (Button) findViewById(R.id.btnIniciarQuiz);
        btnSair = (Button) findViewById(R.id.btnSair);
        txtNome = (TextView) findViewById(R.id.txtNome);

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Botão iniciar quiz - Executa função ao ser precionado (se ativo)
        btnIniciarQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                //Verifica se nome foi digitado
                if(txtNome.getText().toString()!=""){
                    //chama tela do quiz
                    Intent itStartQuiz = new Intent( MainActivity.this, Quiz.class);
                    itStartQuiz.putExtra("nome",txtNome.getText().toString());
                    startActivity(itStartQuiz);
                    txtNome.setText(null);
                }

            }
        });


        txtNome.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence != null){
                    btnIniciarQuiz.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
               if(txtNome.getText().toString().isEmpty()==true){
                   btnIniciarQuiz.setEnabled(false);
               }
            }
        });

    }

    void esconderTeclado(){
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
    }
}
