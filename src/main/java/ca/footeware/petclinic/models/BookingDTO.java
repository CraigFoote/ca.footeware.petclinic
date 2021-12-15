package ca.footeware.petclinic.models;

public class BookingDTO {
    private String bookingId;
    private String petId;
    private String vetId;
    private String procedureId;
    private String petName;
    private LocalDateTime date;
    private Map<String, String> petIdToNameMap;
    private String vetName;
    private Map<String, String> vetIdToNameMap;
    private String procedureName;
    private Map<String, String> procedureIdToNameMap;
}