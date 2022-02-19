package nguyenhoanganhkhoa.com.models;

public class Address {

    private String namePeopleAddress;
    private String locationAddress;
    private String phoneAddress;
    private boolean isDefaultAddress = false;

    public Address(String namePeopleAddress, String locationAddress, String phoneAddress) {
        this.namePeopleAddress = namePeopleAddress;
        this.locationAddress = locationAddress;
        this.phoneAddress = phoneAddress;
    }

    public String getNamePeopleAddress() {
        return namePeopleAddress;
    }

    public void setNamePeopleAddress(String namePeopleAddress) {
        this.namePeopleAddress = namePeopleAddress;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }

    public String getPhoneAddress() {
        return phoneAddress;
    }

    public void setPhoneAddress(String phoneAddress) {
        this.phoneAddress = phoneAddress;
    }

    public boolean isDefaultAddress() {
        return isDefaultAddress;
    }

    public void setDefaultAddress(boolean defaultAddress) {
        isDefaultAddress = defaultAddress;
    }
}
