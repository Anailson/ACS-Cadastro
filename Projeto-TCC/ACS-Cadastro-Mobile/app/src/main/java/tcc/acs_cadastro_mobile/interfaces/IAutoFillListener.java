package tcc.acs_cadastro_mobile.interfaces;

import android.widget.EditText;

public interface IAutoFillListener {
    void searching(EditText editText);

    void selectItem(EditText editText);

    void changedAfterSelected(EditText editText);
}
