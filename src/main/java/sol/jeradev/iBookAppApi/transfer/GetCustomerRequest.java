package sol.jeradev.iBookAppApi.transfer;

public class GetCustomerRequest {
    private String partialName;
    private String phone;

    public String getPartialName() {
        return partialName;
    }

    public void setPartialName(String partialName) {
        this.partialName = partialName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "GetCustomerRequest{" +
                "partianName='" + partialName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
