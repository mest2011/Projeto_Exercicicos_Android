package br.com.mest.systemaderodizo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnVerificar;
    TextView txtLetra, txtNumero, txtResposta, txtSeuDia;

    private int digito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnVerificar = (Button) findViewById(R.id.btnVerificar);
        txtLetra = (TextView) findViewById(R.id.txtLetra);
        txtNumero = (TextView) findViewById(R.id.txtNumero);
        txtResposta = (TextView) findViewById(R.id.txtResposta);
        txtSeuDia = (TextView) findViewById(R.id.txtSeuDia);


        btnVerificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               digito = Integer.parseInt(txtNumero.getText().toString());

               if(digito<9999){
                   digito = (((digito%1000)%100)%10);


                   if(digito == 1 || digito == 2){
                       txtSeuDia.setText("O dia do rodizío é:");
                       txtResposta.setText("Segunda-feira!");
                   }
                   if(digito == 3 || digito == 4){
                       txtSeuDia.setText("O dia do rodizío é:");
                       txtResposta.setText("Terça-feira!");
                   }
                   if(digito == 5 || digito == 6){
                       txtSeuDia.setText("O dia do rodizío é:");
                       txtResposta.setText("Quarta-feira!");
                   }
                   if(digito == 7 || digito == 8){
                       txtSeuDia.setText("O dia do rodizío é:");
                       txtResposta.setText("Quinta-feira!");
                   }
                   if(digito == 9 || digito == 0){
                       txtSeuDia.setText("O dia do rodizío é:");
                       txtResposta.setText("Sexta-feira!");
                   }

               }else{
                   txtResposta.setText("Placa inválida!");
               }

            }
        });
    }
}
