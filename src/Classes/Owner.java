package Classes;

import org.json.JSONObject;

public class Owner {
    private String name;
    private String lastName;
    private String dni;
    private String email;
    private String phone;

    public Owner(String name, String lastName, String dni, String email, String phone) {
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
        this.email = email;
        this.phone = phone;

    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dni='" + dni + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public JSONObject toJson(){
        JSONObject obj = new JSONObject();
        obj.put("name", name);
        obj.put("lastName", lastName);
        obj.put("dni", dni);
        obj.put("email", email);
        obj.put("phone", phone);
        return obj;
    }

}
