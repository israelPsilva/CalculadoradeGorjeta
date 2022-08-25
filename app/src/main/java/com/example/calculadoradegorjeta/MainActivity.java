package com.example.calculadoradegorjeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editValor;
    private TextView textPorgentagem, textGorjeta, textTotal;
    private SeekBar seekBarGorjeta;

    private double porcentagem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        editValor       = findViewById( R.id.editValor );
        textPorgentagem = findViewById( R.id.textPorcentagem );
        textGorjeta     = findViewById( R.id.textGorjeta );
        textTotal       = findViewById( R.id.textTotal );
        seekBarGorjeta  = findViewById( R.id.seekBarGorjeta );

        //Listener para SeekBar
        seekBarGorjeta.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                porcentagem = i;
                textPorgentagem.setText( Math.round( porcentagem ) + "%" );
                calcular();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        } );

    }

    public void calcular(){
        String valorDigitado = editValor.getText().toString();
        if(valorDigitado == null || valorDigitado.equals( "" )){

            Toast.makeText( getApplicationContext(),
            "Digite um valor", Toast.LENGTH_SHORT ).show();
        }else{
            //Converter Sring para double
            double valorInserido = Double.parseDouble( valorDigitado );

            //Calcular a gorjeta e soma no total
            double gorjeta = valorInserido * (porcentagem/100);
            double total   = gorjeta + valorInserido;

            //Exibir a gorjeta e o total
            textGorjeta.setText( "R$ " + Math.round( gorjeta ) );
            textTotal.setText( "R$ " + total );
        }
    }
}