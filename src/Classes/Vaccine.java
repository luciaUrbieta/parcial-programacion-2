package Classes;

import Interfaces.Jsonable;
import org.json.JSONObject;

import java.time.LocalDate;

public class Vaccine implements Jsonable {
    private String type;
    private LocalDate date;

    public Vaccine(String type, LocalDate date) {
        this.type = type;
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date.toString();
    }

    @Override
    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("type", type);
        obj.put("date", date.toString());
        return obj;
    }

    @Override
    public String toString() {
        return "Vaccine{" +
                "type='" + type + '\'' +
                ", date=" + date.toString() +
                '}';
    }
}
