package model;

public class Flight extends Trip {

    private String flighNumber;
    private String airplaneModel;
    private String departureAirport;
    private String destinationAirport;
    private String airline;
    private Seat seat;

    public Flight(String flighNumber, String airplaneModel, String departureAirport, String destinationAirport, String airline, Seat seat, int tripID, String trip, TripType TripType, String origin, String destination, int departureTime, int arrivalTime, String date, int tripTime) {
        super(tripID, trip, TripType, origin, destination, departureTime, arrivalTime, date, tripTime);
        this.flighNumber = flighNumber;
        this.airplaneModel = airplaneModel;
        this.departureAirport = departureAirport;
        this.destinationAirport = destinationAirport;
        this.airline = airline;
        this.seat = seat;
    }

    public String getFlighNumber() {
        return this.flighNumber;
    }

    public void setFlighNumber(String flighNumber) {
        this.flighNumber = flighNumber;
    }

    public String getAirplaneModel() {
        return this.airplaneModel;
    }

    public void setAirplaneModel(String airplaneModel) {
        this.airplaneModel = airplaneModel;
    }

    public String getDepartureAirport() {
        return this.departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getDestinationAirport() {
        return this.destinationAirport;
    }

    public void setDestinationAirport(String destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public String getAirline() {
        return this.airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public Seat getSeat() {
        return this.seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    @Override
    public String toString() {
        return "{" +
            " flighNumber='" + getFlighNumber() + "'" +
            ", airplaneModel='" + getAirplaneModel() + "'" +
            ", departureAirport='" + getDepartureAirport() + "'" +
            ", destinationAirport='" + getDestinationAirport() + "'" +
            ", airline='" + getAirline() + "'" +
            ", seat='" + getSeat() + "'" +
            "}";
    }

}
