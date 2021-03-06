package Modelo;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.gridlayout.widget.GridLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class FigureTask extends AsyncTask<Boolean,String,Boolean> {
    Figure figure;
    Quadrate [][] Grid;
    Quadrate [][] GridAnterior;
    GridLayout ActualFigure;
    Button Derecha;
    Button Izquierda;
    Button Rotar;
    Context context;
    Button button_reset;
    TextView puntaje;
    TextView player_list;
    GridLayout GamePanelView;
    int puntos;
    private float x2,x1,y2,y1;
    boolean perdio =  true;
    player player;
    ArrayList<player> Players_list;
    TextView player_listView;
    int total;
    DatabaseReference PlayersReference;
    String code_game;

    public FigureTask(Context context,GridLayout GamePanelView,Figure figure, Quadrate[][] grid,
                      GridLayout actualFigure, Button derecha, Button izquierda, Button rotar,Button button_reset,
                      TextView puntaje,int total,String name_user,TextView player_list,String code_game) {
        this.total = total;
        this.puntaje = puntaje;
        this.figure = figure;
        this.GamePanelView = GamePanelView;
        Grid = grid;
        GridAnterior = grid.clone();
        ActualFigure = actualFigure;
        Derecha = derecha;
        Izquierda = izquierda;
        Rotar = rotar;
        this.button_reset = button_reset;
        puntos = 0;
        this.context = context;
        player = new player(name_user,0);
        this.code_game = code_game;
        SingletonFirebase.getInstance().GuardarPlayer(player,this.code_game);
        System.out.println("NOMBRE =>"+ name_user);
        Players_list = new ArrayList<>();
        player_listView = player_list;
        PlayersReference = FirebaseDatabase.getInstance().getReference("Games").child(code_game).child("Players");
    }
    private void soltarPantalla(float x, float y) {
        this.x2= x;
        this.y2 = y;
    }


    private void tocarPantalla(float x, float y) {
        this.x1= x;
        this.y1= y;

    }


    private void  movimiento() {
        float  dx = Math.abs(this.x2- this.x1);
        float  dy = Math.abs(this.y2- this.y1);
        String dir;


        if(dx >dy){
            //horizontal
            if(this.x2 > this.x1){
                //derecha
                //this.juego.derecha();
                dir="Derecha";
                figure.Derecha(Grid,figure);

            }else{
                dir="Izquierda";
                // i.Izquierda( tablero_back.getTablero() );
                //izquierda
                figure.Izquierda(Grid,figure);
            }

        }else{
            //vertical
            if (this.y2 >this.y1){
                //hacia abajo
                figure.Bajar(Grid,figure);
                dir="HASTA ABAJO SOY YO";

            }else{
                dir="Arriba";
                figure.Rotar90(Grid,figure);
            }

        }


    }

    @Override
    protected void onPreExecute() {
        figure = null;
        figure = RandomFigure();
        this.Rotar.setEnabled(false);
        LoadActualFigure(ActualFigure,figure);

    }



    @Override
    protected Boolean doInBackground(Boolean... booleans) {

        Derecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                figure.Derecha(Grid,figure);

            }
        });
        Izquierda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                figure.Izquierda(Grid,figure);
            }
        });
        Rotar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SingletonFirebase.getInstance().UsarPoder(Players_list,player,code_game);
            }
        });
        button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                figure = RandomFigure();
                Reset();
                perdio = !perdio;
                puntos = 0;
                puntaje.setText(""+0);
                player.setPuntos(puntos);
                SingletonFirebase.getInstance().GuardarPlayer(player,code_game);
            }
        });
        PlayersReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Players_list.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String user  = (String) snapshot.child("user").getValue();
                    Long lo = snapshot.child("puntos").getValue()!=null? (long) snapshot.child("puntos").getValue():0;
                    Long power = snapshot.child("puntos").getValue()!=null? (long) snapshot.child("power").getValue():0;
                    if(player.getUser().equals(user)){
                        player = new player(user,lo.intValue(),power.intValue());
                    }
                   Players_list.add(new player(user,lo.intValue()));
                }
                onProgressUpdate("print_players");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        View pantalla = ((Activity)context).getWindow().getDecorView();

        pantalla.setOnTouchListener( new View.OnTouchListener() {


            @Override
            public boolean onTouch(View view, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    tocarPantalla( event.getX(), event.getY() );
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    soltarPantalla( event.getX(), event.getY() );

                    publishProgress("movimiento");
                }
                return false;
            }
        });

        while(true){
            try {
                if (!perdio) {
                    if (figure.getStoped_time() < 2) {
                        publishProgress("bajar_normal");

                    } else {
                        if (validar_perdio()) {
                            publishProgress("perdio");
                            try {
                                Thread.sleep(450);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {
                            if (figure.ChocaAbajo(figure, Grid)) {
                                publishProgress("pausa");
                            } else {
                                figure.setStoped_time(0);
                            }
                        }

                    }
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
            }catch (Exception e){

            }
        }
    }



    public boolean validar_perdio(){
        for (int x = 0; x < Grid[0].length; x++) {
            if(Grid[0][x].isFill()){
                return  true;
            }
        }
        return false;
    }



    @Override
    protected void onProgressUpdate(String... values) {
        this.Rotar.setEnabled(player.getPower()>0);

        try {


            if(values[0].equals("perdio")){
                Toast toast = Toast.makeText(context, "USTED PEDIO", Toast.LENGTH_LONG);


                alerta alerta = new alerta(context);
                alerta.show("PERDIO","usted perdio");

                toast.show();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                perdio = true;
                puntos = 0;
                puntaje.setText(""+puntos);
                Reset();
            }else if(values[0].equals("pausa")){

                validate_points(Grid);


                figure = null;
                figure = RandomFigure();
                LoadActualFigure(ActualFigure,figure);

            }else if(values[0].equals("movimiento")){
                movimiento();
            }
            else if(values[0].equals("puntaje")){
               puntaje.setText(""+puntos);
            }
            else if(values[0].equals("bajar_normal")){
                figure.Bajar(Grid, figure);
            }
            else  if(values[0].equals("print_players")){
                PrintJugadores();
            }

        }catch (Exception e){

        }

    }

    private void PrintJugadores() {
        player_listView.setText("Jugadores");

        Collections.sort(Players_list,new PlayerOrder());
        int i = 1;
        for(player player : Players_list){
            player_listView.append("\n"+i+"." +player.getUser()+" : "+player.getPuntos());
            i++;
        }

    }

    public Quadrate[][] LoadActualFigure(GridLayout ActualFigure, Figure figure){
        ActualFigure.removeAllViews();
        ActualFigure.setColumnCount(3);
        ActualFigure.setRowCount(3);


        Quadrate[][] Frames = figure.getFrames();
        int tam = Math.max(figure.getFigureHeight(), figure.getFigureWidth());
        int ActualQuadreateWidth = (int)ActualFigure.getLayoutParams().width/tam;
        int ActualQuadreateHeight = (int)ActualFigure.getLayoutParams().height/tam;
        Quadrate[][] ActualGrid = new Quadrate[tam][tam];
        ActualFigure.setColumnCount(tam);
        ActualFigure.setRowCount(tam);
        ActualFigure.getLayoutParams().width = ActualQuadreateWidth * tam;
        ActualFigure.getLayoutParams().height = ActualQuadreateHeight * tam;


        for (int i = 0; i < tam ; i++) {
            for (int j = 0; j < tam ; j++) {
                ActualGrid[i][j] = new Quadrate(ActualFigure.getContext(),"#c7f9cc","#57cc99",ActualQuadreateWidth,ActualQuadreateHeight);
                ActualGrid[i][j].setEmpty("#c7f9cc","#57cc99");
                ActualFigure.addView(ActualGrid[i][j]);
            }
        }


        for (int i = 0; i < figure.getDataFrame().length; i++) {
            for (int j = 0; j < figure.getDataFrame()[0].length; j++) {
                if(Frames[i][j].fill){
                    ActualGrid[i][j].setFill(figure.getBackgroundColor(),figure.getBorderColor());
                }
            }
        }

        return  ActualGrid;
    }


    public Figure RandomFigure(){
        Random r = new Random();
        int x = r.nextInt(7)+1;
        switch (x){
            case 1: return new Figure_I(ActualFigure.getContext());
            case 2: return new Figure_J(ActualFigure.getContext());
            case 3: return new Figure_L(ActualFigure.getContext());
            case 4: return new Figure_O(ActualFigure.getContext());
            case 5: return new Figure_S(ActualFigure.getContext());
            case 6: return new Figure_T(ActualFigure.getContext());
            case 7: return new Figure_Z(ActualFigure.getContext());
        }
        return new Figure_I(ActualFigure.getContext());
    }


    public  void validate_points(Quadrate[][] grid ){

        int total_l = 0;

        for (int i = 0; i <grid.length ; i++) {

            if(linea_llena(i,grid)){
                total_l++;
                Quadrate[][] auxiliar= new Quadrate[i][grid[0].length];
                for (int k = 0; k < i; k++) {
                    for (int j = 0; j < grid[0].length; j++) {
                        auxiliar[k][j] = new Quadrate(context);
                        auxiliar[k][j]= grid[k][j];
                     System.out.println(grid[k][j].getBackgroundColor());
                    }
                }
                for (int z = i-1; z > 0; z--) {
                    for (int j = 0; j < grid[0].length; j++) {
                        if(auxiliar[z][j].isFill()){
                            grid[z+1][j].setFill(grid[z][j].getBackgroundColor(),grid[z][j].getBorderColor(),auxiliar[z][j].getPos_y(),auxiliar[z][j].getPos_x());
                        }else{
                            grid[z+1][j].setEmpty("#c7f9cc","#57cc99",auxiliar[z][j].getPos_y(),auxiliar[z][j].getPos_x());
                        }

                    }
                }


            }
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(!grid[i][j].isFill()){
                    grid[i][j].setEmpty("#c7f9cc","#57cc99");
                }
            }
        }

        if(total_l>0) {
            puntos += factorial(total_l);
            player.setPuntos(puntos);
            if(puntos >= 3) player.setPower(player.getPower()+1);
            SingletonFirebase.getInstance().GuardarPlayer(player,this.code_game);

            publishProgress("puntaje");

        }
    }

    public void Reset() {
        int CuadradoWidth = (int)GamePanelView.getLayoutParams().width/10;
        int CuadradoHeight = (int)GamePanelView.getLayoutParams().height/20;
        GamePanelView.removeAllViews();
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                Grid[i][j] = new Quadrate(GamePanelView.getContext(),"#c7f9cc","#57cc99",CuadradoWidth,CuadradoHeight);
                GamePanelView.addView(Grid[i][j]);
                Grid[i][j].setEmpty("#c7f9cc","#57cc99");
            }
        }


    }

    public  boolean linea_llena(int i,Quadrate[][] grid){
        for (int j = 0; j < grid[0].length; j++) {
            if(!grid[i][j].isFill()) return  false;
        }

        return true;
    }

    public static long factorial(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }




}
