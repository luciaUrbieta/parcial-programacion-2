package Classes;

import Enums.Status;
import org.json.JSONObject;

public class ClinicHistory extends History<Visit>{
    private int fileNumber;
    private Status status;

    public ClinicHistory(int fileNumber, Status status) {
        super();
        this.fileNumber = fileNumber;
        this.status = status;
    }

    public boolean exists(Visit visit){
        boolean exists = false;

        for(int i = 0; i<this.size(); i++){
            if(visit.equals(this.get(i))){
                exists = true;
            }
        }

        return exists;
    }

    public int getFileNumber() {
        return fileNumber;
    }

    public String getStatus(){
        return status.getStatus();
    }

    @Override
    public String toString() {
        return "ClinicHistory{" +
                "fileNumber=" + fileNumber +
                ", status=" + status +
                "visitas=" + super.toString() +
                '}';
    }

    public JSONObject toJson(){
        JSONObject obj = new JSONObject();
        obj.put("fileNumber", fileNumber);
        obj.put("status", status.getStatus());
        obj.put("visits", this.listToJSON());

        return obj;
    }

}
