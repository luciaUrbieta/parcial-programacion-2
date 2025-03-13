package Classes;

import Enums.Reason;
import Enums.Status;
import Exceptions.VisitaRepetidaException;
import org.json.JSONArray;
import org.json.JSONObject;
import java.time.LocalDate;
import java.util.ArrayList;

public class Veterinary {

    private String name;
    private String adress;
    private String phone;
    private String email;
    private ArrayList<Pet> patients;

    public Veterinary(String name, String adress, String phone, String email) {
        this.name = name;
        this.adress = adress;
        this.phone = phone;
        this.email = email;
        this.patients = new ArrayList<>();
    }

    public int getPatientByDni(String dni) {
        int pos = -1;
        for (int i = 0; i < this.patients.size(); i++) {
            if(this.patients.get(i).getOwnerDni().equals(dni)){
                pos = i;
            }
        }
        return pos;
    }

    public String getName() {
        return name;
    }
    public String getAdress() {
        return adress;
    }
    public String getPhone() {
        return phone;
    }
    public String getEmail() {
        return email;
    }
    public String getPatients(){
        StringBuilder sb = new StringBuilder();
        for (Pet p : this.patients){
            sb.append(p.toString());
        }

        return sb.toString();
    }

    public String getPatient(String ownerDni){
        int pos = this.getPatientByDni(ownerDni);
        String answer = "not found";
        if(pos != -1){
            answer = this.patients.get(pos).toString();
        }

        return answer;
    }

    public void addPatient(String ownerName, String ownerLastName, String dni, String phone, String email, String race, String animal, LocalDate birthDate, String petname, int fileNumber, Status status){
        this.patients.add(new Pet(ownerName, ownerLastName, dni,email,phone,race,animal,birthDate, petname, fileNumber, status));
    }

    public void addPatient(Owner owner, String race, String animal, LocalDate birthDate, String petname, int fileNumber, Status status){
        this.patients.add(new Pet(owner, race,animal,birthDate, petname , fileNumber, status));
    }

    public boolean addVaccine(String dni, String type, LocalDate date){
        int pos = this.getPatientByDni(dni);
        boolean added = false;
        if(pos != -1){
            this.patients.get(pos).AddVaccine(type, date);
            added = true;
        }
        return added;
    }

    public String addVisit(String dni, String observation, LocalDate date, Reason reason){
        int pos = this.getPatientByDni(dni);
        String answer = "not found";

        if(pos != -1){

            try {
                this.patients.get(pos).AddVisit(observation, date, reason);
                answer = "added";
            } catch (VisitaRepetidaException e) {
                answer = e.getMessage();
            }
        }

        return answer;
    }
1
    public JSONArray patientsToJSON(){
        JSONArray jsonArray = new JSONArray();
        for (Pet p : this.patients){
            jsonArray.put(p.toJson());
        }
        return jsonArray;
    }

    public JSONObject toJson(){
        JSONObject json = new JSONObject();
        json.put("name", this.name);
        json.put("address", this.adress);
        json.put("phone", this.phone);
        json.put("email", this.email);

        json.put("patients", this.patientsToJSON());

        return json;
    }

    @Override
    public String toString() {
        return "Veterinary{" +
                "name='" + name + '\'' +
                ", address='" + adress + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", patients=" + patients.toString() +
                '}';
    }
}
