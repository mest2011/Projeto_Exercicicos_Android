package br.com.mest.quiz_qualopais;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Ranking extends AppCompatActivity {

    TextView txtNome, txtAcertos;
    Bundle nome;
    Button btnTelaPrincipal, btnResponderNovamente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        esconderTeclado();

        txtNome = (TextView) findViewById(R.id.txtNome);
        btnResponderNovamente = (Button) findViewById(R.id.btnResponderNovamente);
        btnTelaPrincipal = (Button) findViewById(R.id.btnTelaPrincipal);
        txtAcertos = (TextView) findViewById(R.id.txtAcertos);




        //traz o nome do jogador
        nome =getIntent().getExtras();
        final String sNome = nome.getString("nome");
        txtNome.setText(sNome);
        //traz a pontuação
        txtAcertos.setText(getIntent().getExtras().getString("pontuacao"));

        //botoes
        btnTelaPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnResponderNovamente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itQuiz = new Intent(Ranking.this, Quiz.class);
                itQuiz.putExtra("nome",  sNome);
                startActivity(itQuiz);
                finish();
            }
        });




    }

    void esconderTeclado(){
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
    }
}
