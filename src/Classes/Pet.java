package Classes;

import Enums.Reason;
import Enums.Status;
import Exceptions.VisitaRepetidaException;
import org.json.JSONObject;

import java.time.LocalDate;

public class Pet {
    private String name;
    private LocalDate birthDate;
    private String animal;
    private String race;

    private Owner owner;

    private History<Vaccine> vaccineHistory;
    private ClinicHistory clinicHistory;

    public Pet(String ownerName, String lastName, String dni, String email, String phone , String race, String animal, LocalDate birthDate, String name, int fileNumber, Status status) {
        this.owner = new Owner(ownerName, lastName, dni, email, phone);
        this.race = race;
        this.animal = animal;
        this.birthDate = birthDate;
        this.name = name;
        vaccineHistory = new History<>();
        clinicHistory = new ClinicHistory(fileNumber, status);
    }

    public Pet(Owner owner, String race, String animal, LocalDate birthDate, String name, int fileNumber, Status status) {
        this.owner = owner;
        this.race = race;
        this.animal = animal;
        this.birthDate = birthDate;
        this.name = name;
        vaccineHistory = new History<>();
        clinicHistory = new ClinicHistory(fileNumber, status);
    }

    public String getOwnerDni(){
        return owner.getDni();
    }

    public void AddVaccine(String type, LocalDate date) {
        vaccineHistory.add(new Vaccine(type, date));
    }

    public boolean AddVisit(String observation, LocalDate date, Reason reason) throws VisitaRepetidaException {
        boolean added = false;
        Visit toAdd = new Visit(date, observation, reason);
        if(clinicHistory.exists(toAdd)){
            throw new VisitaRepetidaException();
        }else{
            clinicHistory.add(toAdd);
            added = true;
        }
        return added;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getAnimal() {
        return animal;
    }

    public String getRace() {
        return race;
    }

    public Owner getOwner() {
        return owner;
    }

    public String getVaccineHistory() {
        return vaccineHistory.toString();
    }

    public String getClinicHistory() {
        return clinicHistory.toString();
    }

    public JSONObject toJson(){
        JSONObject obj = new JSONObject();
        obj.put("name", name);
        obj.put("birthDate", birthDate.toString());
        obj.put("animal", animal);
        obj.put("race", race);
        obj.put("owner", owner.toJson());
        obj.put("vaccineHistory", vaccineHistory.listToJSON());
        obj.put("clinicHistory", clinicHistory.toJson());
        return obj;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", animal='" + animal + '\'' +
                ", race='" + race + '\'' +
                ", owner=" + owner +
                ", vaccineHistory=" + vaccineHistory.toString() +
                ", clinicHistory=" + clinicHistory.toString() +
                '}';
    }
}
