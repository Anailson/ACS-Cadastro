package com.avisosms.iuri.avisasms.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.avisosms.iuri.avisasms.R;
import com.avisosms.iuri.avisasms.objetos.Consulta;
import com.avisosms.iuri.avisasms.suporte.Funcoes;

import java.util.Calendar;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by iuri on 7/5/2016.
 */
public class Consultas extends Fragment {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.consultas, container, false);
        view.refreshDrawableState();


//
//        <ImageView
//        android:
//        id = "@+id/icon_clipboard"
//        android:
//        layout_width = "30dp"
//        android:
//        layout_height = "30dp"
//        android:
//        layout_marginLeft = "10dp"
//        android:
//        src = "@drawable/icon_clipboard" / >

        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.fragment_tab_layout_indicadores);
        float pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics());
        /*LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
        params.height = (int) pixels;
        params.weight = (int) pixels;*/

        //static  int id = 1;// getResources().getIdentifier("gameover", "drawable", getPackageName());
        for (  int i = 0; i < 5; i++) {
            ImageView imgView = new ImageView(getContext());
            //imgView.setId(0x7f0d0079);
            imgView.setImageResource(R.drawable.icon_clipboard);
            imgView.setLayoutParams(new LinearLayout.LayoutParams((int)pixels,(int) pixels));//new LinearLayout.LayoutParams(30, 30));
            linearLayout.addView(imgView);
        }
        /////////////
        Realm realm = Realm.getDefaultInstance();

        Calendar calendar = Funcoes.dataHoje();

        RealmResults<Consulta> consultas = realm.where(Consulta.class)
                .equalTo("dataDoAtendimentoEmMilissegundo", calendar.getTimeInMillis())
                .findAll();


        for (Consulta consulta : consultas) {
            Log.i("Consulta", consulta.getDataDoAtendimentoEmMilissegundo() + " <> " + calendar.getTimeInMillis());
        }

        /////////////

        ConsultasListaFragmento mConsultasListaFragmento;
        ViewPager mViewPager;
        mConsultasListaFragmento = new ConsultasListaFragmento(getChildFragmentManager(), consultas.size() - 1);



        realm.close();


        // Set up the ViewPager with the sections adapter.
        //setContentView(R.layout.fragment_tab);
        mViewPager = (ViewPager) view.findViewById(R.id.container);
        mViewPager.setAdapter(mConsultasListaFragmento);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

              //  Toast.makeText(getContext(), imgView.getId() + "", Toast.LENGTH_SHORT).show();

//                ImageView lineColorCode = (ImageView) view.findViewById(R.id.icon_clipboard4);


                switch (position) {
                    case 1:
//                        lineColorCode = (ImageView) view.findViewById(R.id.icon_clipboard);
                        //Toast.makeText(getContext(), imgView.getId() + "", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                       // lineColorCode = (ImageView) view.findViewById(R.id.icon_clipboard2);
                        break;
                    case 3:
                        //lineColorCode = (ImageView) view.findViewById(R.id.icon_clipboard3);
                        break;
                    case 4:
                        //lineColorCode = (ImageView) view.findViewById(R.id.icon_clipboard4);
                        break;
                    case 5:
                        //lineColorCode = (ImageView) view.findViewById(R.id.icon_clipboard5);
                        break;
                    default:
                        break;
                }

//                int color = getResources().getColor(R.color.colorAccent); //The color u want
//                lineColorCode.setColorFilter(color);*/

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });
        return view;
    }

    static int id = 1;

    // Returns a valid id that isn't in use
    public int findId(){
        View v = view.findViewById(++id);

        while (v != null){
            v = view.findViewById(++id);
        }

        return id++;
    }
}
