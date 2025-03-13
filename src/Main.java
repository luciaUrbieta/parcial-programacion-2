import Classes.*;
import Enums.Reason;
import Enums.Status;
import JSON.JSONUtiles;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        String fileName = "veterinaria";

        Veterinary veterinaria = new Veterinary("VETPET", "juncal 5050", "4754646", "vetpet@gmail.com");

        veterinaria.addPatient("Pepe", "Argento", "20695432", "2236473848", "pargento@gmail.com", "cruza", "perro", LocalDate.of(2004, 11, 23), "fatiga", 1234, Status.OBSERVATION);
        veterinaria.addPatient("Yaggi", "doo", "34567890", "2235463746", "shaggi@yahoo.com.ar", "Gran Danés", "perro", LocalDate.of(1985, 4, 15), "Scooby-doo", 1236, Status.HEALTHY);
        //agregar vacuna a mascota:
        veterinaria.addVaccine("20695432", "antitetanica", LocalDate.of(2024, 10, 20));
        veterinaria.addVaccine("20695432", "antirabica", LocalDate.of(2023,9,10));
        veterinaria.addVaccine("34567890", "antitetanica", LocalDate.of(2023, 9, 9));
        //agregar visita a mascota:
        veterinaria.addVisit("20695432", "nada nuevo", LocalDate.now(), Reason.CHECK);
        veterinaria.addVisit("34567890", "nada nuevo", LocalDate.now(), Reason.OTHER);
        //agregar visita repetida
        System.out.println(veterinaria.addVisit("20695432", "nada nuevo", LocalDate.now(), Reason.CHECK));


        System.out.println(veterinaria.toJson());

        JSONUtiles.uploadJSON(veterinaria.toJson(), fileName);

        Veterinary cargadaDeArchivo = fromJson(fileName);
        System.out.println("veterinaria cargada del archivo: "+cargadaDeArchivo.toJson());


        //aca podemos ver que al comparar la veterinaria que se creo y se subio al archivo es igual a la que se descargo del archivo
        System.out.println(veterinaria.toString().equals(cargadaDeArchivo.toString()));

    }

    //metodo para convertir lo del archivo en el sistema
    public static Veterinary fromJson(String fileName) {
        String fileContent = JSONUtiles.downloadJSON(fileName);

        JSONObject jsonVet = new JSONObject(fileContent);
        String vetName = jsonVet.getString("name");
        String vetPhone = jsonVet.getString("phone");
        String vetEmail = jsonVet.getString("email");
        String vetAddress = jsonVet.getString("address");

        Veterinary fromFile = new Veterinary(vetName, vetAddress, vetPhone, vetEmail);

        JSONArray JsonPets = jsonVet.getJSONArray("patients");
        String race;
        String animal;
        String name;
        LocalDate birthdate;
        Owner owner;
        History<Vaccine> vaccines;
        ClinicHistory visits;

        for (int i = 0; i < JsonPets.length(); i++) {

            //saco una mascota del jsonarray
            JSONObject jsonPet = JsonPets.getJSONObject(i);

            //saco los atributos de la mascota
            race = jsonPet.getString("race");
            animal = jsonPet.getString("animal");
            name = jsonPet.getString("name");
            birthdate = LocalDate.parse(jsonPet.getString("birthDate"));

            //llamo al metodo que recibe el JSONObject del dueño y me devuelve un Owner
            owner = ownerfromJson(jsonPet.getJSONObject("owner"));

            // armo la hisotira clinica
            JSONObject clinicHistoryJson = JsonPets.getJSONObject(i).getJSONObject("clinicHistory");
            int fileNumber = clinicHistoryJson.getInt("fileNumber");
            String statusString = clinicHistoryJson.getString("status");
            Status status;
            if(statusString.equals("en observación")) {
                status = Status.OBSERVATION;
            }else if(statusString.equals("sano")) {
                status = Status.HEALTHY;
            }else{
                status = Status.CRITIC;
            }

            fromFile.addPatient(owner, race, animal, birthdate, name, fileNumber, status);

            JSONArray visitsJson = clinicHistoryJson.getJSONArray("visits");
            for (int j = 0; j < visitsJson.length(); j++) {
                JSONObject jsonVisit = visitsJson.getJSONObject(j);
                LocalDate visitDate = LocalDate.parse(jsonVisit.getString("date"));
                String visitReason = jsonVisit.getString("reason");
                Reason reason;
                if(visitReason.equals("chequeo")) {
                    reason = Reason.CHECK;
                } else if (visitReason.equals("emergencia")) {
                    reason = Reason.EMERGENCY;
                } else {
                    reason = Reason.OTHER;
                }

                String observation = jsonVisit.getString("observation");
                fromFile.addVisit(owner.getDni(), observation, visitDate, reason);
            }

            //armo la historia de vacunas
            JSONArray vacsJson = jsonPet.getJSONArray("vaccineHistory");
            for (int j = 0; j < vacsJson.length(); j++) {
                JSONObject jsonVac = vacsJson.getJSONObject(j);
                String type = jsonVac.getString("type");
                LocalDate vacDate = LocalDate.parse(jsonVac.getString("date"));
                fromFile.addVaccine(owner.getDni(), type, vacDate);
            }

        }
        return fromFile;
    }

    public static Owner ownerfromJson(JSONObject ownerJson) {
        String name = ownerJson.getString("name");
        String lastName = ownerJson.getString("lastName");
        String phone  = ownerJson.getString("phone");
        String email = ownerJson.getString("email");
        String dni = ownerJson.getString("dni");

        return new Owner(name, lastName, dni, email, phone);
    }
}