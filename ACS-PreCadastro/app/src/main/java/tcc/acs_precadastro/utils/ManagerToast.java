package tcc.acs_precadastro.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by anail on 12/10/2016.
 */
public class ManagerToast {

    private static Toast toast = null;

    private ManagerToast(){}

    private static void showToast(Context context, String message, int duration){

        if(toast == null){
            toast = Toast.makeText(context, message, duration);
        } else {

            if (toast.getView().isShown()) {
                toast.cancel();
            }
            toast = Toast.makeText(context, message, duration);
        }
        toast.show();
    }

    public static void showShortToast(Context context, int id){
        showToast(context, context.getString(id), Toast.LENGTH_SHORT);
    }

    public static void showShortToast(Context context, String mensage){
        showToast(context, mensage, Toast.LENGTH_SHORT);
    }

    public static void showLongToast(Context context, int id){
        showToast(context, context.getString(id), Toast.LENGTH_LONG);
    }

    public static void showLongToast(Context context, String mensage){
        showToast(context, mensage, Toast.LENGTH_LONG);
    }
}
