package br.com.mest.balaiodelenharestaurante;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Console;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

   Button btnCalcular;
   EditText txtConsumoTotal, txtCouvert, txtDividir, txtTaxaServiço, txtTotal, txtValorPessoa;
   private double consumoTotal=0, couvert=0, dividir=0, taxaServico=0, total=0, valorPessoa=0;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtConsumoTotal = (EditText) findViewById(R.id.txtConsumoTotal);
        txtCouvert = (EditText) findViewById(R.id.txtCouvert);
        txtDividir = (EditText) findViewById(R.id.txtDividir);
        txtTaxaServiço = (EditText) findViewById(R.id.txtTaxaServiço);
        txtTotal = (EditText) findViewById(R.id.txtTotal);
        txtValorPessoa = (EditText) findViewById(R.id.txtValorPessoa);
        btnCalcular = (Button) findViewById(R.id.btnCalcular);
        final NumberFormat number = NumberFormat.getCurrencyInstance();

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(txtConsumoTotal.getText().toString()!="" && txtDividir.getText().toString()!= "") {
                   try {
                       consumoTotal = Double.parseDouble(txtConsumoTotal.getText().toString());
                       if (consumoTotal > 0) {
                           if (txtCouvert.getText().toString() != "") {
                               try {
                                   couvert = Double.parseDouble(txtCouvert.getText().toString());
                               } catch (Exception e) {
                                   couvert = 0;
                               }
                           }

                           try {
                               dividir = Double.parseDouble(txtDividir.getText().toString());
                           } catch (Exception e) {
                               dividir = 1;
                           }

                           total = consumoTotal;
                           if (couvert > 0) {
                               total += couvert;
                           }
                           taxaServico = (consumoTotal * 0.1);
                           txtTaxaServiço.setText(String.valueOf(number.format(taxaServico)));
                           total += taxaServico;
                           txtTotal.setText(String.valueOf(number.format(total)));
                           valorPessoa = (total / dividir);
                           txtValorPessoa.setText(String.valueOf(number.format(valorPessoa)));
                       }

                   } catch (Exception e) {
                       txtConsumoTotal.setHint("Digite algum valor!");
                       consumoTotal = 0;
                   }
               }
            }

    });
    }
}
