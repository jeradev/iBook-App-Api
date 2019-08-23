package sol.jeradev.iBookAppApi.transfer;

import javax.validation.constraints.NotNull;

public class UpdateCustomerRequest {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String phone;
    @NotNull
    private String period;
    @NotNull
    private double price;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public double getPrice() {return price;}

    public void setPrice(double price) {this.price = price;}

    @Override
    public String toString() {
        return "CreateCustomerRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", period='" + period + '\'' +
                ", price=" + price +
                '}';
    }
}
