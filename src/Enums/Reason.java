package Enums;

public enum Reason {
    EMERGENCY("emergencia"),
    CHECK("chequeo"),
    OTHER("otro");

    private final String reason;

    Reason(String reason){
        this.reason = reason;
    }

    public String getReason(){
        return reason;
    }

}
