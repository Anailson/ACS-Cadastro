package tcc.acs_cadastro_mobile.interfaces;

import android.widget.TextView;

public interface IRequired {

    boolean isInvalid(String index);

    void setTitle(TextView title);

    void setError(CharSequence text);

    void clearError();
}
