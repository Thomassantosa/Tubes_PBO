package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import model.Airlines;
import model.Airport;
import model.Flight;
import model.Partner;
import model.Station;
import model.TrainTrip;
import model.Trip;
import model.TripTypesEnum;
import model.User;
import model.Vehicle;

public class QueryController {

    DatabaseHandler conn = new DatabaseHandler();
    
    public User selectUserByEmail(String email, String password) {
        conn.connect();
        String query = "SELECT `user_id`, `fullname`, `username`, `email`, `password`, `address`, `user_type`, `company_name` FROM `users` WHERE email='" + email + "' AND password='" + password + "'";
        try {
            Statement stmt = conn.conn.createStatement();
            ResultSet result1 = stmt.executeQuery(query);
            
            int id = 0;
            String companyName = "";
            while (result1.next()) {
                id = result1.getInt("user_id");
                companyName = result1.getString("company_name");
            }

            // Check if user found
            if (id != 0) {
                
                // Check selected query is user or partner
                if (companyName == null) {

                    System.out.println("TEST1");
                    User user = new User();
                    ResultSet result2 = stmt.executeQuery(query);
                    while (result2.next()) {
                        user.setUserID(result2.getInt("user_id"));
                        user.setFullname(result2.getString("fullname"));
                        user.setUsername(result2.getString("username"));
                        user.setEmail(result2.getString("email"));
                        user.setPassword(result2.getString("password"));
                        user.setAddress(result2.getString("address"));
                        user.setUserType(result2.getString("user_type"));
                    }

                    return user;
                } else {

                    System.out.println("TEST2");
                    Partner partner = new Partner();
                    ResultSet result3 = stmt.executeQuery(query);
                    while (result3.next()) {
                        partner.setUserID(result3.getInt("user_id"));
                        partner.setFullname(result3.getString("fullname"));
                        partner.setUsername(result3.getString("username"));
                        partner.setEmail(result3.getString("email"));
                        partner.setPassword(result3.getString("password"));
                        partner.setAddress(result3.getString("address"));
                        partner.setUserType(result3.getString("user_type"));
                        partner.setCompanyName(result3.getString("company_name"));
                    }
                    return partner;
                }

            // If user not found, then try select by username
            } else {
                return selectUserByUsername(email, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public User selectUserByUsername(String username, String password) {
        conn.connect();
        String query = "SELECT `user_id`, `fullname`, `username`, `email`, `password`, `address`, `user_type`, `company_name` FROM `users` WHERE username='" + username + "' AND password='" + password + "'";
        try {
            Statement stmt = conn.conn.createStatement();
            ResultSet result1 = stmt.executeQuery(query);
            
            int id = 0;
            String companyName = "";
            while (result1.next()) {
                id = result1.getInt("user_id");
                companyName = result1.getString("company_name");
            }
            
            // Check if user found
            if (id != 0) {

                // Check selected query is user or partner
                if (companyName == null) {
                    
                    User user = new User();
                    ResultSet result2 = stmt.executeQuery(query);
                    while (result2.next()) {
                        user.setUserID(result2.getInt("user_id"));
                        user.setFullname(result2.getString("fullname"));
                        user.setUsername(result2.getString("username"));
                        user.setEmail(result2.getString("email"));
                        user.setPassword(result2.getString("password"));
                        user.setAddress(result2.getString("address"));
                        user.setUserType(result2.getString("user_type"));
                    }

                    return user;
                } else {
                    
                    Partner partner = new Partner();
                    ResultSet result3 = stmt.executeQuery(query);
                    while (result3.next()) {
                        partner.setUserID(result3.getInt("user_id"));
                        partner.setFullname(result3.getString("fullname"));
                        partner.setUsername(result3.getString("username"));
                        partner.setEmail(result3.getString("email"));
                        partner.setPassword(result3.getString("password"));
                        partner.setAddress(result3.getString("address"));
                        partner.setUserType(result3.getString("user_type"));
                        partner.setCompanyName(result3.getString("company_name"));
                    }
                    return partner;
                }

            // If user not found, then return null
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean insertUser(User user) {
        conn.connect();
        String query = "INSERT INTO `users`(`fullname`, `username`, `email`, `password`, `address`, `user_type`, `date_created`) VALUES  (?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = conn.conn.prepareStatement(query);
            stmt.setString(1, user.getFullname());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getAddress());
            stmt.setString(6, user.getUserType());

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();
            String currentDate = dtf.format(now);
            stmt.setString(7, currentDate);
            stmt.executeUpdate();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    public boolean insertPartner(Partner user) {
        conn.connect();
        String query = "INSERT INTO `users`(`fullname`, `username`, `email`, `password`, `address`, `user_type`, `partner_type`, `company_name`, `date_created`) VALUES  (?,?,?,?,?,?,?,?,?)";
        try {   
            PreparedStatement stmt = conn.conn.prepareStatement(query);
            stmt.setString(1, user.getFullname());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getAddress());
            stmt.setString(6, user.getUserType());
            stmt.setString(7, user.getPartnerType());
            stmt.setString(8, user.getCompanyName());

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();
            String currentDate = dtf.format(now);
            stmt.setString(9, currentDate);
            stmt.executeUpdate();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    public int isEmailTaken(String email) {
        conn.connect();
        String query = "SELECT `user_id` FROM `users` WHERE email='" + email + "'";
        try {
            Statement stmt = conn.conn.createStatement();
            ResultSet result = stmt.executeQuery(query);
            int userId = 0;
            
            while (result.next()) {
                userId = result.getInt("user_id");
            }

            if (userId == 0) {
                return 0;
            } else {
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int isUsernameTaken(String username) {
        conn.connect();
        String query = "SELECT `user_id` FROM `users` WHERE username='" + username + "'";
        try {
            Statement stmt = conn.conn.createStatement();
            ResultSet result = stmt.executeQuery(query);
            int userId = 0;
            
            while (result.next()) {
                userId = result.getInt("user_id");
            }

            if (userId == 0) {
                return 0;
            } else {
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getTotalUser() {
        conn.connect();
        String query = "SELECT COUNT(user_id) FROM users WHERE user_type='User'";
        try {
            Statement stmt = conn.conn.createStatement();
            ResultSet result = stmt.executeQuery(query);
            int userCount = 0;
            
            while (result.next()) {
                userCount = result.getInt("COUNT(user_id)");
            }
            return userCount;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getTotalPartner() {
        conn.connect();
        String query = "SELECT COUNT(user_id) FROM users WHERE user_type='Partner'";
        try {
            Statement stmt = conn.conn.createStatement();
            ResultSet result = stmt.executeQuery(query);
            int userCount = 0;
            
            while (result.next()) {
                userCount = result.getInt("COUNT(user_id)");
            }
            return userCount;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getNewUser() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String currentDate = dtf.format(now);

        conn.connect();
        String query = "SELECT COUNT(user_id) FROM users WHERE user_type='User' AND date_created='" + currentDate + "'";
        try {
            Statement stmt = conn.conn.createStatement();
            ResultSet result = stmt.executeQuery(query);
            
            int userCount = 0;
            
            while (result.next()) {
                userCount = result.getInt("COUNT(user_id)");
            }
            return userCount;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getNewPartner() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String currentDate = dtf.format(now);

        conn.connect();
        String query = "SELECT COUNT(user_id) FROM users WHERE user_type='Partner' AND date_created='" + currentDate + "'";
        try {
            Statement stmt = conn.conn.createStatement();
            ResultSet result = stmt.executeQuery(query);
            
            int userCount = 0;
            
            while (result.next()) {
                userCount = result.getInt("COUNT(user_id)");
            }
            return userCount;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getNewTransaction() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String currentDate = dtf.format(now);

        conn.connect();
        String query = "SELECT COUNT(order_id) FROM orders WHERE order_date='" + currentDate + "'";
        try {
            Statement stmt = conn.conn.createStatement();
            ResultSet result = stmt.executeQuery(query);
            
            int orderCount = 0;
            
            while (result.next()) {
                orderCount = result.getInt("COUNT(order_id)");
            }
            return orderCount;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    // public int getTodayIncome() {
    //     DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    //     LocalDateTime now = LocalDateTime.now();
    //     String currentDate = dtf.format(now);

    //     conn.connect();
    //     String query = "SELECT COUNT(order_id) FROM orders WHERE order_date='" + currentDate + "'";
    //     try {
    //         Statement stmt = conn.conn.createStatement();
    //         ResultSet result = stmt.executeQuery(query);
            
    //         int orderCount = 0;
            
    //         while (result.next()) {
    //             orderCount = result.getInt("COUNT(order_id)");
    //         }
    //         return orderCount;
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //         return -1;
    //     }
    // }

    public ArrayList<Trip> selectAllFlight() {
        conn.connect();

        String query = "SELECT a.flight_id, " +
        "a.flight_type, " +
        "a.flight_number, " +
        "a.departure_time, " +
        "a.arrival_time, " +
        "a.departure_date, " +
        "a.arrival_date, " +
        "a.travel_time, " +
        
        "b.airplane_id, " +
        "b.airplane_model, " +
        
        "e.airline_id, " +
        "e.airline_name, " +
        "e.airline_contact, " +
        
        "c.airport_id AS departure_airport_id, " +
        "c.airport_code AS departure_airport_code, " +
        "c.airport_name AS departure_airport_name, " +
        "c.airport_city AS departure_airport_city, " +
        "c.airport_country AS departure_airport_country, " +
        
        "d.airport_id AS destination_airport_id, " +
        "d.airport_code AS destination_airport_code, " +
        "d.airport_name AS destination_airport_name, " +
        "d.airport_city AS destination_airport_city, " +
        "d.airport_country AS destination_airport_country " +
        
        "FROM flights a " +
        "JOIN airplanes b " +
        "ON a.flight_id = b.airplane_id " +
        "JOIN airports c " +
        "ON a.departure_airport = c.airport_id " +
        "JOIN airports d " +
        "ON a.destination_airport = d.airport_id " +
        "JOIN airlines e " +
        "ON b.airline_id = e.airline_id " +
        "WHERE 1 " +
        "ORDER BY flight_id Desc";

        try {
            Statement stmt = conn.conn.createStatement();
            ResultSet result = stmt.executeQuery(query);
            ArrayList<Trip> flights = new ArrayList<>();

            while (result.next()) {
                Flight flight = new Flight();
                Vehicle airplane = new Vehicle();
                Airport departureAirport = new Airport();
                Airport destinationAirport = new Airport();
                Airlines airline = new Airlines();

                flight.setTripID(result.getInt("flight_id"));
                flight.setFlightType(result.getString("flight_type"));
                flight.setFlightNumber(result.getString("flight_number"));
                flight.setTripTypes(TripTypesEnum.FLIGHT);
                flight.setDepartureTime(result.getString("departure_time"));
                flight.setArrivalTime(result.getString("arrival_time"));
                flight.setDepartureDate(result.getString("departure_date"));
                flight.setArrivalDate(result.getString("arrival_date"));
                flight.setTripTime(result.getInt("travel_time"));

                airplane.setVehicleID(result.getInt("airplane_id"));
                airplane.setModel(result.getString("airplane_model"));

                departureAirport.setAirportID(result.getInt("departure_airport_id"));
                departureAirport.setName(result.getString("departure_airport_name"));
                departureAirport.setCode(result.getString("departure_airport_code"));
                departureAirport.setCity(result.getString("departure_airport_city"));
                departureAirport.setCountry(result.getString("departure_airport_country"));

                destinationAirport.setAirportID(result.getInt("destination_airport_id"));
                destinationAirport.setName(result.getString("destination_airport_name"));
                destinationAirport.setCode(result.getString("destination_airport_code"));
                destinationAirport.setCity(result.getString("destination_airport_city"));
                destinationAirport.setCountry(result.getString("destination_airport_country"));

                airline.setAirlineID(result.getInt("airline_id"));
                airline.setName(result.getString("airline_name"));
                airline.setContact(result.getString("airline_contact"));

                flight.setAirplane(airplane);
                flight.setDepartureAirport(departureAirport);
                flight.setDestinationAirport(destinationAirport);
                flight.setAirline(airline);
                
                flights.add(flight);
            }

            return flights;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Trip> selectAllTrainTrip() {
        conn.connect();

        String query = "SELECT a.traintrip_id, " +
        "a.traintrip_number, " +
        "a.departure_time, " +
        "a.arrival_time, " +
        "a.departure_date, " +
        "a.arrival_date, " +
        "a.travel_time, " +
        
        "b.train_id, " +
        "b.train_model, " +
        
        "c.station_id AS departure_station_id, " +
        "c.station_code AS departure_station_code, " +
        "c.station_name AS departure_station_name, " +
        "c.station_city AS departure_station_city, " +
        
        "d.station_id AS destination_station_id, " +
        "d.station_code AS destination_station_code, " +
        "d.station_name AS destination_station_name, " +
        "d.station_city AS destination_station_city " +
        
        "FROM traintrips a " +
        "JOIN trains b " +
        "ON a.traintrip_id = b.train_id " +
        "JOIN stations c " +
        "ON a.departure_station = c.station_id " +
        "JOIN stations d " +
        "ON a.destination_station = d.station_id " +
        "WHERE 1 " +
        "ORDER BY traintrip_id Desc";

        try {
            Statement stmt = conn.conn.createStatement();
            ResultSet result = stmt.executeQuery(query);
            ArrayList<Trip> trainTrips = new ArrayList<>();

            while (result.next()) {
                TrainTrip trainTrip = new TrainTrip();
                Vehicle train = new Vehicle();
                Station departureStation = new Station();
                Station destinationStation = new Station();

                trainTrip.setTripID(result.getInt("traintrip_id"));
                trainTrip.setTrainTripNumber(result.getString("traintrip_number"));
                trainTrip.setTripTypes(TripTypesEnum.TRAIN);
                trainTrip.setDepartureTime(result.getString("departure_time"));
                trainTrip.setArrivalTime(result.getString("arrival_time"));
                trainTrip.setDepartureDate(result.getString("departure_date"));
                trainTrip.setArrivalDate(result.getString("arrival_date"));
                trainTrip.setTripTime(result.getInt("travel_time"));

                train.setVehicleID(result.getInt("train_id"));
                train.setModel(result.getString("train_model"));

                departureStation.setStationID(result.getInt("departure_station_id"));
                departureStation.setName(result.getString("departure_station_name"));
                departureStation.setCode(result.getString("departure_station_code"));
                departureStation.setCity(result.getString("departure_station_city"));

                destinationStation.setStationID(result.getInt("destination_station_id"));
                destinationStation.setName(result.getString("destination_station_name"));
                destinationStation.setCode(result.getString("destination_station_code"));
                destinationStation.setCity(result.getString("destination_station_city"));

                trainTrip.setTrain(train);
                trainTrip.setDepartureStation(departureStation);
                trainTrip.setDestinationStation(destinationStation);
                
                trainTrips.add(trainTrip);
            }

            return trainTrips;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
