package tcc.acs_cadastro_mobile.controllers;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import tcc.acs_cadastro_mobile.models.CitizenModel;

public class StepsController {

    public final boolean isYesGroup(RadioGroup radioGroup, int id) {
        return radioGroup.getCheckedRadioButtonId() == id;
    }

    public String[] getFields(boolean checked, EditText editText) {

        if (checked) {
            return new String[]{"1", editText.getText().toString()};
        }
        return new String[]{"" + CitizenModel.INT_DEFAULT_VALUE, CitizenModel.STRING_DEFAULT_VALUE};
    }

    public final String[] getFields(boolean checked, Spinner spinner){
        if(checked){
            return new String[]{""+spinner.getSelectedItemPosition(), spinner.getSelectedItem().toString()};
        }
        return new String[]{"" + CitizenModel.INT_DEFAULT_VALUE, CitizenModel.STRING_DEFAULT_VALUE};
    }


    public boolean[] getFields(boolean checked, CheckBox... checkBoxes) {
        if (checked) {
            boolean[] values = new boolean[checkBoxes.length + 1];
            values[0] = true;
            for (int i = 0; i < checkBoxes.length; i++) {
                values[i + 1] = checkBoxes[i].isChecked();
            }
            return values;
        }
        return new boolean[checkBoxes.length + 1];
    }

    public final void enableView(View view, boolean enable) {
        view.setEnabled(enable);
    }

    public final void enableView(boolean enable, CheckBox... checkBoxes) {
        for (CheckBox checkBox : checkBoxes) {
            enableView(checkBox, enable);
            if (!enable) {
                fillField(checkBox, false);
            }
        }
    }

    public void fillField(EditText editText, String text) {
        editText.setText(text);
    }

    public final void fillField(Spinner spinner, int position) {
        spinner.setSelection(position);
    }

    public final void fillField(Spinner spinner, String position) {

        spinner.setSelection(Integer.parseInt(position));
    }

    public final void fillField(CheckBox checkBox, boolean checked) {
        checkBox.setChecked(checked);
    }

    public final void fillField(CheckBox[] checkBoxes, boolean[] values){
        for(int i = 0; i < checkBoxes.length; i++){
            checkBoxes[i].setChecked(values[i + 1]);
        }
    }

    public final void fillField(RadioGroup radioGroup, boolean checked, int yes, int no) {
        int id = getYesOrNo(checked, yes, no);

        RadioGroup.OnCheckedChangeListener listener;
        if ((listener = (RadioGroup.OnCheckedChangeListener) radioGroup.getTag(radioGroup.getId())) != null) {
            /*TODO What happens when Listener is enabled before RadioGroup check up your child?*/
            radioGroup.setOnCheckedChangeListener(null);
            radioGroup.check(id);
            radioGroup.setOnCheckedChangeListener(listener);
        } else {
            radioGroup.check(id);
        }
    }

    public final void fillField(RadioGroup radioGroup, boolean checked, EditText editText, String value, int yes, int no) {

        String text = "";
        if (checked) {
            text = value;
        }
        fillField(editText, text);
        enableView(editText, checked);
        fillField(radioGroup, checked, yes, no);
    }

    public final void fillField(RadioGroup radioGroup, boolean checked, Spinner spinner, String value, int yes, int no) {
        int position = 0;
        if(checked){
            position = Integer.parseInt(value);
        }
        fillField(spinner, position);
        fillField(radioGroup, checked, yes, no);
    }

    public final void fillField(RadioGroup radioGroup, boolean checked, CheckBox[] checkBoxes, boolean[] values, int yes, int no) {
        fillField(checkBoxes, values);

        if(!checked){
            for (CheckBox checkBox : checkBoxes) {
                enableView(checkBox, false);
            }
        }
        fillField(radioGroup, checked, yes, no);
    }


    public final String[] getIndexAndValue(Spinner spinner) {
        return new String[]{spinner.getSelectedItemPosition() + "", spinner.getSelectedItem().toString()};
    }

    private int getYesOrNo(boolean checked, int yes, int no){
        if (checked) {
            return yes;
        }
        return no;
    }
}
