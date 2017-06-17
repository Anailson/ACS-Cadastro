package tcc.acs_cadastro_mobile.controllers;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import tcc.acs_cadastro_mobile.interfaces.IRequiredView;
import tcc.acs_cadastro_mobile.models.CitizenModel;

public class StepsController {

    private Context context;
    private boolean hasError;

    StepsController(Context context){
        this.context = context;
    }

    public int getIndex(String value, int idArray) {

        String[] array = context.getResources().getStringArray(idArray);
        for(int i = 0; i < array.length; i++){
            if(value.toUpperCase().equals(array[i].toUpperCase())){
                return i;
            }
        }
        throw new IllegalArgumentException("Value " + value + " was not founded");
    }

    final void startErrors(){
        hasError = true;
    }

    final boolean hasError(){
        return hasError;
    }

    final boolean applyError(IRequiredView required){
        return applyError(required, "", "");
    }

    final boolean  applyError(IRequiredView required, String match){
        return applyError(required, match, "");
    }

    final boolean  applyError(IRequiredView required, String match, String msg){

        if (required.isInvalid(match)) {
            required.setError(msg);
            hasError = false;
        } else {
            required.clearError();
        }
        return hasError;
    }

    final boolean isYesGroup(RadioGroup radioGroup, int id) {
        return radioGroup.getCheckedRadioButtonId() == id;
    }

    final int getInt(EditText edtNumber) {
        String text = edtNumber.getText().toString();
        return text.matches("[0-9]+") ? Integer.parseInt(text) : 0;
    }

    final long getLong(EditText editText) {
        String text = editText.getText().toString();
        return text.matches("[0-9]+") ? Long.parseLong(text) : 0;
    }

    public String getFields(EditText editText){
        return editText.getText().toString();
    }

    public String[] getFields(boolean checked, EditText editText) {

        String value = CitizenModel.STRING_DEFAULT_VALUE;
        if (checked) {
            value = editText.getText().toString();
        }
        return new String[]{Boolean.toString(checked), value};
    }

    public final String[] getFields(boolean checked, Spinner spinner){
        String value = CitizenModel.STRING_DEFAULT_VALUE;
        if(checked){
            value = spinner.getSelectedItem().toString();
        }
        return new String[]{Boolean.toString(checked), value};
    }

    public boolean[] getFields(boolean checked, CheckBox... checkBoxes) {
        if (checked) {
           return getFields(checkBoxes);
        }
        return new boolean[checkBoxes.length];
    }

    public boolean getFields(CheckBox checkBox){
        return checkBox.isChecked();
    }

    public boolean[] getFields(CheckBox... checkBoxes) {

        boolean[] values = new boolean[checkBoxes.length];
        for (int i = 0; i < checkBoxes.length; i++) {
            values[i] = getFields(checkBoxes[i]);
        }
        return values;
    }

    public final void enableView(View view, boolean enable) {
        view.setEnabled(enable);
    }

    final void enableView(boolean enable, CheckBox... checkBoxes) {
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

    public final void fillField(Spinner spinner, String position, String unmatch){
        if(position.equals(unmatch)){
            fillField(spinner, 0);
        } else {
            fillField(spinner, position);
        }
    }

    public final void fillField(CheckBox checkBox, boolean checked) {
        checkBox.setChecked(checked);
    }

    public final void fillField(boolean[] values, CheckBox... checkBoxes){
        for(int i = 0; i < checkBoxes.length; i++){
            fillField(checkBoxes[i], values[i]);
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

    public final void fillField(RadioGroup radioGroup, boolean checked, Spinner spinner, int index, int yes, int no) {
        int position = 0;
        if(checked){
            position = index;
        }
        fillField(spinner, position);
        fillField(radioGroup, checked, yes, no);
    }

    public final void fillField(RadioGroup radioGroup, boolean checked, CheckBox[] checkBoxes, boolean[] values, int yes, int no) {
        fillField(values, checkBoxes);

        if(!checked){
            for (CheckBox checkBox : checkBoxes) {
                enableView(checkBox, false);
            }
        }
        fillField(radioGroup, checked, yes, no);
    }
    
    public final String getFields(Spinner spinner) {
        return spinner.getSelectedItem().toString();
    }

    private int getYesOrNo(boolean checked, int yes, int no){
        if (checked) {
            return yes;
        }
        return no;
    }
}
