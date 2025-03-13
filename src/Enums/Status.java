package Enums;

public enum Status {
    HEALTHY("sano"),
    OBSERVATION("en observación"),
    CRITIC("crítico");

    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
