package lk.ijse.dep.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class EmployeeDTO {
    private String employeeId;
    private String salary;
    private String E_name;
    private String position;
    private String contact_num;
    private String email;



    /*public Employee() {
    }

    public Employee(String employeeId, String e_name, String position, String contact_num, String email, String salary) {
        this.employeeId = employeeId;
        this.salary = salary;
        this.E_name = e_name;
        this.position = position;
        this.contact_num = contact_num;
        this.email = email;

    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getE_name() {
        return E_name;
    }

    public void setE_name(String e_name) {
        E_name = e_name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getContact_num() {
        return contact_num;
    }

    public void setContact_num(String contact_num) {
        this.contact_num = contact_num;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId='" + employeeId + '\'' +
                ", E_name='" + E_name + '\'' +
                ", position='" + position + '\'' +
                ", contact_num='" + contact_num + '\'' +
                ", email='" + email + '\'' +
                ", salary='" + salary + '\'' +
                '}';
    }*/
}
