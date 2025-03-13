package Classes;

import Interfaces.Jsonable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class History<T extends Jsonable> {

    private ArrayList<T> list;

    public History() {
        list = new ArrayList<>();
    }

    public void add(T t) {
        list.add(t);
    }
    public T get(int index) {
        return list.get(index);
    }
    public int size() {
        return list.size();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<list.size(); i++){
            sb.append(list.get(i).toString());
        }
        return sb.toString();
    }

    public JSONArray listToJSON() {
        JSONArray jsonArray = new JSONArray();
        for(int i = 0; i<list.size(); i++){
            jsonArray.put(list.get(i).toJson());
        }
        return jsonArray;
    }

}
