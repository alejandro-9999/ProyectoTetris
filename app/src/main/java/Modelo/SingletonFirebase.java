package Modelo;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SingletonFirebase {
    private  final static  SingletonFirebase INSTANCE= new SingletonFirebase();
    private  static  DatabaseReference mDatabase;
    public  static SingletonFirebase getInstance(){
        return  INSTANCE;
    }

    public SingletonFirebase() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }
    public  void GuardarPlayer(player player){
        this.mDatabase.child("Players").child(player.getUser()).setValue(player);
    }
}
