package br.com.mest.quiz_qualopais;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Random;

public class Quiz extends AppCompatActivity {

    TextView txtStatus;
    Button btnResponder;
    Bundle nome;
    ImageView imgBandeira;
    private int contadorDeRespostas=1, temporarioQuestao=0, temporarioAlternativa=0, pontuacao=0;
    private String[] bandeiras= {"Brasil","Bulgaria","Canada","China","Coreia do Sul","Cuba","Guatemala","Hungria","Italia","Estados Unidos - USA"};
    private String[] ordemQuestoes = new String[10];
    Random random = new Random();
    Drawable[] drawable= new Drawable[10];
    RadioButton[] rdbResposta=new RadioButton[5];
    RadioGroup rbgAlternativas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        //Atribui bandeiras
        setDrawable();
        //atribui campos a variaveis
        txtStatus = (TextView) findViewById(R.id.txtStatus);
        btnResponder = (Button) findViewById(R.id.btnResponder);
        imgBandeira = (ImageView) findViewById(R.id.imgBandeira);
        rdbResposta[0] = (RadioButton) findViewById(R.id.rdbResposta1);
        rdbResposta[1] = (RadioButton) findViewById(R.id.rdbResposta2);
        rdbResposta[2] = (RadioButton) findViewById(R.id.rdbResposta3);
        rdbResposta[3] = (RadioButton) findViewById(R.id.rdbResposta4);
        rdbResposta[4] = (RadioButton) findViewById(R.id.rdbResposta5);
        rbgAlternativas = (RadioGroup) findViewById(R.id.rbgAlternativas);

        //Altera o nome do tittulo do app
        getSupportActionBar().setTitle(R.string.que_pais);

        geradorDeQuestoes(0,temporarioAlternativa=temporarioAlternativa());

        rbgAlternativas.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                btnResponder.setEnabled(true);
            }
        });
        //açoes parte apartir da ação de clicar o botao responder
        btnResponder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contadorDeRespostas++;
                verificaResposta(temporarioAlternativa);

                if(contadorDeRespostas<11){
                    temporarioQuestao++; //= random.nextInt(10);
                    temporarioAlternativa();
                    geradorDeQuestoes(temporarioQuestao, temporarioAlternativa=temporarioAlternativa());



                }
                if (contadorDeRespostas>10){
                    Intent itRanking = new Intent( Quiz.this, Ranking.class);
                    itRanking.putExtra("nome", getIntent().getExtras().getString("nome"));
                    itRanking.putExtra("pontuacao", String.valueOf(pontuacao));
                    startActivity(itRanking);
                    finish();
                }
                //desmarca caixa de seleção apos a questao ser verificada
                deseleciona();
                status(contadorDeRespostas);
                btnResponder.setEnabled(false);
            }
        });

    }

    private int temporarioAlternativa() {
        return random.nextInt(4);
           }

    private void deseleciona() {
        for(int x=0;x<4;x++){
            rdbResposta[x].setChecked(false);
            //rbgAlternativas.setContextClickable(false);
        }
        rdbResposta[4].setChecked(true);
    }

    private void verificaResposta( int temporarioAlternativa) {
        if(rdbResposta[temporarioAlternativa].isChecked()){
            pontuacao++;
        }
    }

    private void geradorDeQuestoes(int temporarioQuestao, int temporarioAlternativa) {


        boolean[] alternativaGerada = {false,false,false,false};
        imgBandeira.setImageDrawable(drawable[temporarioQuestao]);
        switch (temporarioAlternativa){
            case 0:
                rdbResposta[0].setText(bandeiras[temporarioQuestao]);
                break;
            case 1:
                rdbResposta[1].setText(bandeiras[temporarioQuestao]);
                break;
            case 2:
                rdbResposta[2].setText(bandeiras[temporarioQuestao]);
                break;
            case 3:
                rdbResposta[3].setText(bandeiras[temporarioQuestao]);
                break;
        }
        alternativaGerada[temporarioAlternativa]=true;
        for(int x=0; x<4; x++){
            if(alternativaGerada[x]==false){


                int temp=random.nextInt(10);

                if(temp!=temporarioQuestao){
                    for(int y=0; y<4; y++){
                        if(rdbResposta[y].getText().toString()==bandeiras[temp]){
                            temp=random.nextInt(10);
                            y=0;
                        }
                        if(y==3){
                            rdbResposta[x].setText(bandeiras[temp]);
                        }
                    }
                    alternativaGerada[x]=true;
                }else{
                    x--;
                }


            }

        }


    }






    private void setDrawable(){
        drawable[0]= getResources().getDrawable(R.drawable.brasil);
        drawable[1]= getResources().getDrawable(R.drawable.bulgaria);
        drawable[2]= getResources().getDrawable(R.drawable.canada);
        drawable[3]= getResources().getDrawable(R.drawable.china);
        drawable[4]= getResources().getDrawable(R.drawable.coreia_sul);
        drawable[5]= getResources().getDrawable(R.drawable.cuba);
        drawable[6]= getResources().getDrawable(R.drawable.guatemala);
        drawable[7]= getResources().getDrawable(R.drawable.hungria);
        drawable[8]= getResources().getDrawable(R.drawable.italia);
        drawable[9]= getResources().getDrawable(R.drawable.usa);
    }

    private void status(int progresso){
        txtStatus.setText("Progresso: " + progresso +" de 10");
    }
}
