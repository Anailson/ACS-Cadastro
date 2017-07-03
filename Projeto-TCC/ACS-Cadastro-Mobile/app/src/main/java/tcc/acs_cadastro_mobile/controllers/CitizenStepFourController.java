package tcc.acs_cadastro_mobile.controllers;

import android.support.v4.app.Fragment;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.interfaces.IRequiredView;
import tcc.acs_cadastro_mobile.models.StreetSituationModel;
import tcc.acs_cadastro_mobile.persistence.StreetSituationPersistence;
import tcc.acs_cadastro_mobile.subModels.AnotherInstitution;
import tcc.acs_cadastro_mobile.subModels.FamilyVisit;
import tcc.acs_cadastro_mobile.subModels.Feeding;
import tcc.acs_cadastro_mobile.subModels.Hygiene;
import tcc.acs_cadastro_mobile.subModels.StreetSituation;

public class CitizenStepFourController extends StepsController {

    private Fragment fragment;
    private RadioGroup.OnCheckedChangeListener onChangeListener;

    public CitizenStepFourController(Fragment fragment){
        super(fragment.getContext());
        this.fragment = fragment;
    }

    public RadioGroup.OnCheckedChangeListener getOnChangeListener() {

        if(onChangeListener == null){
            onChangeListener = new OnChangeListener();
        }
        return onChangeListener;
    }

    public StreetSituationModel get(StreetSituation street, boolean benefit, boolean family, Feeding feeding,
                                    AnotherInstitution anotherInstitution, FamilyVisit familyVisit, Hygiene hygiene) {
        return StreetSituationPersistence.get(street, benefit, family, feeding, anotherInstitution, familyVisit, hygiene);
    }

    public StreetSituation getStreetSituation(RadioGroup radioGroup, Spinner spinner){
        boolean isStreetSituation = isYesGroup(radioGroup, R.id.rgrp_ctz_street_y);
        return StreetSituationPersistence.getStreetSituation(isStreetSituation, getFields(spinner));
    }

    public boolean isBenefit(RadioGroup radioGroup){
        int id = R.id.rgrp_ctz_any_benefit_y;
        return isYesGroup(radioGroup, id);
    }

    public boolean isFamily(RadioGroup radioGroup){
        int id = R.id.rgrp_ctz_family_y;
        return isYesGroup(radioGroup, id);
    }

    public Feeding getFoodOrigin(Spinner spinner, CheckBox... checkBoxes){
        return StreetSituationPersistence.getFeeding(getFields(spinner), getFields(checkBoxes));
    }

    public AnotherInstitution getInstitutionAnother(RadioGroup radioGroup, EditText editText){
        boolean isAnotherInstitution = isYesGroup(radioGroup, R.id.rgrp_ctz_institution_another_y);
        return StreetSituationPersistence.getAnotherInstitution(isAnotherInstitution, getFields(editText));
    }

    public FamilyVisit getFamilyVisit(RadioGroup radioGroup, EditText editText){
        boolean isFamilyVisit = isYesGroup(radioGroup, R.id.rgrp_ctz_family_visit_y);
        return StreetSituationPersistence.getFamilyVisit(isFamilyVisit, getFields(editText));
    }

    public Hygiene getHygiene(RadioGroup radioGroup, CheckBox... checkBoxes){
        boolean isHygiene = isYesGroup(radioGroup, R.id.rgrp_ctz_hygiene_y);
        return StreetSituationPersistence.getHygiene(isHygiene, getFields(checkBoxes));
    }

    public void fillStreetSituation(RadioGroup radioGroup, boolean checked){
        fillField(radioGroup, checked, R.id.rgrp_ctz_street_y, R.id.rgrp_ctz_street_n);
    }

    public void fillStreetTime(Spinner spinner, int position){
        fillField(spinner, position);
    }

    public void fillBenefit(RadioGroup radioGroup, boolean checked){
        fillField(radioGroup, checked, R.id.rgrp_ctz_any_benefit_y, R.id.rgrp_ctz_any_benefit_n);
    }

    public void fillFamily(RadioGroup radioGroup, boolean checked){
        fillField(radioGroup, checked, R.id.rgrp_ctz_family_y, R.id.rgrp_ctz_family_n);
    }

    public void fillFoodPerDay(Spinner spinner, int position){
        fillField(spinner, position);
    }

    public void fillFoodOrigin(boolean[] values, CheckBox... checkBoxes){
        fillField(values, checkBoxes);
    }

    public void fillInstitutionAnother(RadioGroup radioGroup, boolean checked, EditText editText, String value){
        int yes = R.id.rgrp_ctz_institution_another_y;
        int no = R.id.rgrp_ctz_institution_another_n;
        fillField(radioGroup, checked, editText, value, yes, no);
    }

    public void fillFamilyVisit(RadioGroup radioGroup, boolean checked, EditText editText, String value){
        int yes = R.id.rgrp_ctz_family_visit_y;
        int no = R.id.rgrp_ctz_family_visit_n;
        fillField(radioGroup, checked, editText, value, yes, no);
    }

    public void fillHygiene(RadioGroup radioGroup, boolean checked, boolean[] values, CheckBox... checkBoxes){
        int yes = R.id.rgrp_ctz_hygiene_y;
        int no = R.id.rgrp_ctz_hygiene_n;
        fillField(radioGroup, checked, checkBoxes, values, yes, no);
    }

    public boolean isRequiredFieldsFilled(IRequiredView rgrpStreet) {
        startErrors();
        applyError(rgrpStreet);
        return hasError();
    }

    private class OnChangeListener implements RadioGroup.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int id) {
            switch (radioGroup.getId()){
                case R.id.rgrp_ctz_institution_another: enableInstitutionAnother(id); break;
                case R.id.rgrp_ctz_family_visit: enableFamilyVisit(id); break;
                case R.id.rgrp_ctz_hygiene: enableHygiene(id); break;
            }
        }

        private void enableInstitutionAnother(int id){
            boolean checked = id == R.id.rgrp_ctz_institution_another_y;
            EditText editText = (EditText)fragment.getActivity().findViewById(R.id.edt_ctz_institution_another);
            enableView(editText, checked);
            if(!checked){
                fillField(editText, "");
            }
        }

        private void enableFamilyVisit(int id){
            boolean checked = id == R.id.rgrp_ctz_family_visit_y;
            EditText editText = (EditText)fragment.getActivity().findViewById(R.id.edt_ctz_family_visit);
            enableView(editText, checked);
            if(!checked){
                fillField(editText, "");
            }
        }

        private void enableHygiene(int id){
            boolean checked = id == R.id.rgrp_ctz_hygiene_y;
            CheckBox chbBath = (CheckBox) fragment.getActivity().findViewById(R.id.chb_ctz_bath);
            CheckBox chbSanitary = (CheckBox) fragment.getActivity().findViewById(R.id.chb_ctz_sanitary);
            CheckBox chbOral = (CheckBox) fragment.getActivity().findViewById(R.id.chb_ctz_oral);
            CheckBox chbAnother = (CheckBox) fragment.getActivity().findViewById(R.id.chb_ctz_hygiene_another);
            enableView(checked, chbBath, chbSanitary, chbOral, chbAnother);
        }
    }
}
