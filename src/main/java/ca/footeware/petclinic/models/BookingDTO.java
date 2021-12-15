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
    
    /**
     * Constructor displaying bookings.
     */
    public BookingDTO(String bookingId, String petId, String vetId, String procedureId, String petName, String vetName, String procedureName){
        this.bookingId = bookingId;
        this.petId = petId;
        this.vetId = vetId;
        this.procedureId = procedureId;
        this petNme = petName;
        this.vetName = vetName;
        this.procedureName = procedureName;
    }
}