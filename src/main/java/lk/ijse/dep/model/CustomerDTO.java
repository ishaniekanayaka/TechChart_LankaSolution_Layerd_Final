package lk.ijse.dep.model;

public class CustomerDTO {
    private String customerId;
    private String C_name;
    private String email;
    private String contact_num;
    private String address;

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", C_name='" + C_name + '\'' +
                ", email='" + email + '\'' +
                ", contact_num='" + contact_num + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getC_name() {
        return C_name;
    }

    public void setC_name(String c_name) {
        C_name = c_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact_num() {
        return contact_num;
    }

    public void setContact_num(String contact_num) {
        this.contact_num = contact_num;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CustomerDTO() {
    }

    public CustomerDTO(String customerId, String c_name, String email, String contact_num, String address) {
        this.customerId = customerId;
        this.C_name = c_name;
        this.email = email;
        this.contact_num = contact_num;
        this.address = address;
    }

}