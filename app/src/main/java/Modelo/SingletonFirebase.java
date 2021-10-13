package Modelo;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SingletonFirebase {
    private  final static  SingletonFirebase INSTANCE= new SingletonFirebase();
    private  static  DatabaseReference mDatabase;
    public  static SingletonFirebase getInstance(){
        return  INSTANCE;
    }

    public SingletonFirebase() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }
    public  void GuardarPlayer(player player,String code_game){
        this.mDatabase.child("Games").child(code_game).child("Players").child(player.getUser()).setValue(player);
    }

    public  void GuardarPoder(player player,String code_game){
        this.mDatabase.child("Games").child(code_game).child("Players").child(player.getUser()).setValue(player);
    }

    public  void UsarPoder(ArrayList<player> players ,player player, String code_game){
        for(player p : players) {
            if (!player.getUser().equals(p.getUser()))
            {
                p.setPower(p.getPower()-4);
                this.mDatabase.child("Games").child(code_game).child("Players").child(player.getUser()).setValue(p);

            }
        }
    }
}
