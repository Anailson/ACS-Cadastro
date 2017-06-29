package tcc.acs_cadastro_mobile.views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import io.realm.RealmList;
import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.controllers.AccompanyStepTwoController;
import tcc.acs_cadastro_mobile.interfaces.IAccompany;
import tcc.acs_cadastro_mobile.interfaces.IRequiredFields;
import tcc.acs_cadastro_mobile.models.ConditionsModel;
import tcc.acs_cadastro_mobile.models.RealmInt;
import tcc.acs_cadastro_mobile.persistence.ConditionPersistence;
import tcc.acs_cadastro_mobile.subModels.CommunicableDisease;
import tcc.acs_cadastro_mobile.subModels.ConditionDiseases;
import tcc.acs_cadastro_mobile.subModels.TrackingDiseases;

public class AccompanyStepTwoFragment extends Fragment implements IRequiredFields {

    private static final String CONDITIONS_MODEL = "CONDITIONS_MODEL";

    private ConditionsModel conditions;
    private IAccompany accompany;
    private AccompanyStepTwoController controller;
    private CheckBox chbAsthma, chbMalnutrition, chbDiabetes, chbDpoc, chbHypertension, chbObesity,
            chbPrenatal, chbChildcare, chbPuerperium, chbSexualHealth, chbSmoking, chbAlcohol, chbDrugs,
            chbMentalHealth, chbRehabilitation, chbTuberculosis, chbLeprosy, chbDengue, chbDst,
            chbCervicalCancer, chbBreastCancer, chbCardiovascular;
    private EditText edtAnothers;

    public static AccompanyStepTwoFragment newInstance(ConditionsModel conditions){
        AccompanyStepTwoFragment fragment = new AccompanyStepTwoFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(CONDITIONS_MODEL, conditions);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        accompany = (IAccompany) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.content_acc_add_2, container, false);
        conditions = (ConditionsModel) getArguments().getSerializable(CONDITIONS_MODEL);
        controller = new AccompanyStepTwoController(this);

        chbAsthma = (CheckBox) view.findViewById(R.id.chb_acc_asthma);
        chbMalnutrition = (CheckBox) view.findViewById(R.id.chb_acc_malnutrition);
        chbDiabetes = (CheckBox) view.findViewById(R.id.chb_acc_diabetis);
        chbDpoc = (CheckBox) view.findViewById(R.id.chb_acc_dpoc);
        chbHypertension = (CheckBox) view.findViewById(R.id.chb_acc_hypertension);
        chbObesity = (CheckBox) view.findViewById(R.id.chb_acc_obesity);
        chbPrenatal = (CheckBox) view.findViewById(R.id.chb_acc_prenatal);
        chbChildcare = (CheckBox) view.findViewById(R.id.chb_acc_childcare);
        chbPuerperium = (CheckBox) view.findViewById(R.id.chb_acc_puerperium);
        chbSexualHealth = (CheckBox) view.findViewById(R.id.chb_acc_sexual_health);
        chbSmoking = (CheckBox) view.findViewById(R.id.chb_acc_smoking);
        chbAlcohol = (CheckBox) view.findViewById(R.id.chb_acc_alcohol);
        chbDrugs = (CheckBox) view.findViewById(R.id.chb_acc_drugs);
        chbMentalHealth = (CheckBox) view.findViewById(R.id.chb_acc_mental_health);
        chbRehabilitation = (CheckBox) view.findViewById(R.id.chb_acc_rehabilitation);
        chbTuberculosis = (CheckBox) view.findViewById(R.id.chb_acc_tuberculosis);
        chbLeprosy = (CheckBox) view.findViewById(R.id.chb_acc_leprosy);
        chbDengue = (CheckBox) view.findViewById(R.id.chb_acc_dengue);
        chbDst = (CheckBox) view.findViewById(R.id.chb_acc_dst);
        chbCervicalCancer = (CheckBox) view.findViewById(R.id.chb_acc_cervical_cancer);
        chbBreastCancer = (CheckBox) view.findViewById(R.id.chb_acc_breast_cancer);
        chbCardiovascular = (CheckBox) view.findViewById(R.id.chb_acc_cardiovascular);
        edtAnothers = (EditText) view.findViewById(R.id.edt_acc_anothers);

        if(conditions != null){
            fillField();
        }

        return view;
    }

    @Override
    public void onDetach() {
        getFields();
        super.onDetach();
    }

    @Override
    public boolean isRequiredFieldsFilled() {

        return controller.isRequiredFieldsFilled(chbAsthma, chbMalnutrition, chbDiabetes, chbDpoc,
                chbHypertension, chbObesity, chbPrenatal, chbChildcare, chbPuerperium, chbSexualHealth,
                chbSmoking, chbAlcohol, chbDrugs, chbMentalHealth, chbRehabilitation, chbTuberculosis,
                chbLeprosy, chbDengue, chbDst, chbCervicalCancer, chbBreastCancer, chbCardiovascular);
    }

    private void getFields(){

        boolean[] diseases = controller.getFields(chbAsthma, chbMalnutrition, chbDiabetes, chbDpoc,
                chbHypertension, chbObesity, chbPrenatal, chbChildcare, chbPuerperium, chbSexualHealth,
                chbSmoking, chbAlcohol, chbDrugs, chbMentalHealth, chbRehabilitation);
        boolean[] communicables = controller.getFields(chbTuberculosis, chbLeprosy, chbDengue, chbDst);
        boolean[] trackings = controller.getFields(chbCervicalCancer, chbBreastCancer, chbCardiovascular);
        RealmInt[] cids = controller.getAnotherCids(edtAnothers);

        ConditionDiseases condition = ConditionPersistence.getConditionDiseases(diseases);
        CommunicableDisease communicable = ConditionPersistence.getCommunicableDisease(communicables);
        TrackingDiseases tracking = ConditionPersistence.getTrackingDiseases(trackings);
        RealmList<RealmInt> anothers = ConditionPersistence.getAnotherCids(cids);

        conditions = ConditionPersistence.get(condition, communicable, tracking, anothers);
        accompany.send(conditions);
    }

    private void fillField() {

        controller.fillField(conditions.getCondition().getValues(), chbAsthma, chbMalnutrition,
                chbDiabetes, chbDpoc, chbHypertension, chbObesity, chbPrenatal, chbChildcare, chbPuerperium,
                chbSexualHealth, chbSmoking, chbAlcohol, chbDrugs, chbMentalHealth, chbRehabilitation);
        controller.fillField(conditions.getCommunicables(), chbTuberculosis, chbLeprosy, chbDengue, chbDst);
        controller.fillField(conditions.getTrackings(), chbCervicalCancer, chbBreastCancer, chbCardiovascular);
        controller.fillCids(conditions.getAnothers(), edtAnothers);
    }
}
