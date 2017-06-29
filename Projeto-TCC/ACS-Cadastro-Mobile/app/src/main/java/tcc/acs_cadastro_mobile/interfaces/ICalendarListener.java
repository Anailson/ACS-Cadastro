package tcc.acs_cadastro_mobile.interfaces;

import java.io.Serializable;

import tcc.acs_cadastro_mobile.views.CalendarActivity;

public interface ICalendarListener extends Serializable{

    void onOk(int id, CalendarActivity.Date date);

    void onCancel();
}
