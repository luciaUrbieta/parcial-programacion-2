package Classes;

import Enums.Reason;
import Interfaces.Jsonable;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.Objects;

public class Visit implements Jsonable {

    private LocalDate date;
    private String observation;
    private Reason reason;

    public Visit(LocalDate date, String observation, Reason reason) {
        this.date = date;
        this.observation = observation;
        this.reason = reason;
    }

    public LocalDate getDate() {
        return date;
    }
    public String getObservation() {
        return observation;
    }
    public String getReason() {
        return reason.getReason();
    }

    @Override
    public String toString() {
        return "Visit{" +
                "date=" + date.toString() +
                ", observation='" + observation + '\'' +
                ", reason=" + reason.getReason() +
                '}';
    }

    public JSONObject toJson(){
        JSONObject obj = new JSONObject();
        obj.put("date", date.toString());
        obj.put("observation", observation);
        obj.put("reason", reason.getReason());
        return obj;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Visit visit = (Visit) obj;
        return this.date.equals(visit.getDate()) & this.reason.getReason().equals(visit.getReason());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.date, this.reason);
    }
}
