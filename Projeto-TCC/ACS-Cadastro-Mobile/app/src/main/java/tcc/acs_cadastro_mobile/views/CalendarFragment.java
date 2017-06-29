package tcc.acs_cadastro_mobile.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.controllers.CalendarController;
import tcc.acs_cadastro_mobile.interfaces.ICalendarListener;

public class CalendarFragment extends Fragment {


    public static CalendarFragment newInstance(int id, ICalendarListener listener){
        CalendarFragment fragment = new CalendarFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(CalendarActivity.DATA, listener);
        bundle.putInt(CalendarActivity.ID, id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.float_calendar_fragment, container, false);
        
        int id = getArguments().getInt(CalendarActivity.ID);
        ICalendarListener listener = (ICalendarListener) getArguments().getSerializable(CalendarActivity.DATA);
        CalendarController controller = new CalendarController(getActivity());
        controller.setShowCalendar(id, listener);

        NumberPicker npkrDay = (NumberPicker) view.findViewById(R.id.npkr_day);
        NumberPicker npkrMonth = (NumberPicker) view.findViewById(R.id.npkr_month);
        NumberPicker npkrYear = (NumberPicker) view.findViewById(R.id.npkr_year);

        setNumberPicker(npkrDay, R.array.days);
        setNumberPicker(npkrMonth, R.array.months);
        setNumberPicker(npkrYear, CalendarController.getYears());

        Button btnOk = (Button) view.findViewById(R.id.btn_calendar_ok);
        Button btnCancel = (Button) view.findViewById(R.id.btn_calendar_cancel);

        btnOk.setOnClickListener(controller.getClickListener());
        btnCancel.setOnClickListener(controller.getClickListener());

        return view;
    }

    private NumberPicker setNumberPicker(NumberPicker picker, int arrayResource){
        String [] values = getResources().getStringArray(arrayResource);
        return setNumberPicker(picker, values);
    }

    private NumberPicker setNumberPicker(NumberPicker picker, String [] values){
        picker.setMinValue(0);
        picker.setMaxValue(values.length - 1);
        picker.setDisplayedValues(values);
        return picker;
    }
}
