package com.example.tetris;



import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Modelo.Figure;
import Modelo.GamePanel;

import  Modelo.FigureTask;


public class MainActivity extends AppCompatActivity {

    private GridLayout GamePanelView ;
    private GridLayout ActualFigure ;
    private Button Bajar;
    private Button Derecha;
    private Button Izquierda;
    private Button Reset;
    private TextView puntaje;
    private TextView player_list;
    int total = 0;
    FigureTask hilo_logica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Write a message to the database

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        pedir_nombre(this);
        myRef.setValue("Hello, Dana!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GamePanelView =  (GridLayout) findViewById(R.id.GamePanel);
        ActualFigure =  (GridLayout) findViewById(R.id.ActualFigure);
        Bajar = (Button) findViewById(R.id.rotar);
        Derecha = (Button) findViewById(R.id.bdercha);
        Izquierda = (Button) findViewById(R.id.bizquierda);
        Reset =(Button) findViewById(R.id.reset);
        puntaje = (TextView) findViewById(R.id.puntaje);
        player_list = (TextView) findViewById(R.id.player_list);



    }

    @Override
    protected void onStart() {


        super.onStart();

    }




    public  String pedir_nombre(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Nombre");

        final String[] salida = {"Salida"};
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String m_Text = input.getText().toString();
                if(!m_Text.equals("") && !m_Text.isEmpty() ){
                    pedir_codigo(context,m_Text);
                }else{
                    pedir_nombre(context);
                }
            }
        });

        builder.show();
        return  salida[0];
    }


    public  String pedir_codigo(Context context,String name){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Codigo");

        final String[] salida = {"Salida"};
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String m_Text = input.getText().toString();
                if(!m_Text.equals("") && !m_Text.isEmpty() ){
                    GamePanel panelGame = new GamePanel(GamePanelView,ActualFigure,Bajar,Derecha,Izquierda,Reset,puntaje,total);
                    hilo_logica = new FigureTask
                            (context,GamePanelView,new Figure(ActualFigure.getContext()),panelGame.getGrid(),ActualFigure,Derecha,Izquierda,Bajar,Reset,puntaje,total,name,player_list,m_Text);

                    hilo_logica.execute(true);
                }else{
                    pedir_codigo(context,name);
                }
            }
        });

        builder.show();
        return  salida[0];
    }
}
