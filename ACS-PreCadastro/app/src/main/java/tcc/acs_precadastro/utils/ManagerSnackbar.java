package tcc.acs_precadastro.utils;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

/**
 * Created by anail on 12/10/2016.
 */
public class ManagerSnackbar {

    private static Snackbar snackbar = null;

    private ManagerSnackbar(){}

    private static Snackbar showToast(View view, String mensage, int duration){

        if(snackbar == null){
            snackbar = Snackbar.make(view, mensage, duration);

        } else {

            if (snackbar.getView().isShown()) {
                snackbar.dismiss();
            }
            snackbar = Snackbar.make(view, mensage, duration);
        }
        return snackbar;
    }

    public static Snackbar getSnackbar() {
        return snackbar;
    }

    public static void showShortSnackbar(View view, int id){
        showToast(view, view.getContext().getString(id), Snackbar.LENGTH_SHORT).show();
    }

    public static void showShortSnackbar(View view, String mensage){
        showToast(view, mensage, Snackbar.LENGTH_SHORT).show();
    }

    public static void showLongSnackbar(View view, int id){
        showToast(view, view.getContext().getString(id), Snackbar.LENGTH_LONG).setAction("", null).show();
    }

    public static void showLongSnackbar(View view, String message){
        showToast(view, message, Snackbar.LENGTH_LONG).show();
    }

    public static void showIndefiniteSnackbar(View view, int id){
        showToast(view, view.getContext().getString(id), Snackbar.LENGTH_INDEFINITE).show();
    }

    public static void showIndefiniteSnackbar(View view, int id, String action, View.OnClickListener listener){
        showToast(view, view.getContext().getString(id), Snackbar.LENGTH_INDEFINITE).setAction(action, listener).show();
    }

    public static void showIndefiniteSnackbar(View view, int id, int action, View.OnClickListener listener){
        showToast(view, view.getContext().getString(id), Snackbar.LENGTH_INDEFINITE).setAction(action, listener).show();
    }

    public static void showIndefiniteSnackbar(View view, String message){
        showToast(view, message, Snackbar.LENGTH_INDEFINITE).show();
    }

    public static void showIndefiniteSnackbar(View view, String message, String action, View.OnClickListener listener){
        showToast(view, message, Snackbar.LENGTH_INDEFINITE).setAction(action, listener).show();
    }

    public static void showIndefiniteSnackbar(View view, String message, int action, View.OnClickListener listener){
        showToast(view, message, Snackbar.LENGTH_INDEFINITE).setAction(action, listener).show();
    }
}
