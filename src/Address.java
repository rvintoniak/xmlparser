/**
 * Created by roman on 21.10.14.
 */
public class Address {
    private double latitude;
    private double longitude;
    private String description;

    public Address(double latitude, double longitude, String description) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getDescription() {
        return description;
    }
}
