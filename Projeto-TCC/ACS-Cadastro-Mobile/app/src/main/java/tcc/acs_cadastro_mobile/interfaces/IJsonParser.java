package tcc.acs_cadastro_mobile.interfaces;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public interface IJsonParser<E> {

    JSONObject parserFrom(E object);

    E parserFrom(JSONObject json);

    List<E> parserFrom(JSONArray array);

    JSONArray parserFrom(List<E> list);
}