package tcc.acs_cadastro_mobile.interfaces;

import android.widget.TextView;

public interface IRequiredView {

    boolean isInvalid(String... text);

    void setTitle(TextView title);

    void setError(CharSequence text);

    void clearError();
}
