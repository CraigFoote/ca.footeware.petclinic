package ca.footeware.petclinic.models;

public class BookingDTO {
    private String bookingId;
    private Pet pet;
    private Vet vet;
    private Procedure procedure;
    private LocalDateTime date;
    private List<Pet> pets;
    private List<Vet> vets;
    private List<Procedure> procedures;
    
    /**
     * Constructor for displaying bookings.
     */
    public BookingDTO(String bookingId, Pet pet, Vet vet, Procedure procedure){
        this.bookingId = bookingId;
        this.pet = pet;
        this.vet = vet;
        this.procedure = procedure;
    }
    
      /**
         * Constructor for editing bookings.
         */
        public BookingDTO(String bookingId, Pet pet, Vet vet, Procedure procedure, List<Pet> pets, List<Vet> vets, List<Procedure> procedures){
            this.bookingId = bookingId;
            this.pet = pet;
            this.vet = vet;
            this.procedure = procedure;
            this.pets = pets;
            this.vets = vets;
            this procedures = procedures;
        }
}