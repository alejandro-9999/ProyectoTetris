package Modelo;




import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class alerta
{
    Context context;
    public  alerta(Context context){
        this.context = context;
    }
    void show(String title, String message)
    {
        dialog = new AlertDialog.Builder((Activity)context) // Pass a reference to your main activity here
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        dialog.cancel();
                    }
                })
                .show();
    }

    private AlertDialog dialog;
}