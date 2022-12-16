package lab4;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;
import java.util.Set;

public class Employee {
    private String name;
    private double salary;
    private String address;
    private String phoneNumber;
    private boolean isInsuranced;

    /**
     * Employee constructor
     * @param builder
     */
    private Employee(EmployeeBuilder builder){
        this.name = builder.name;
        this.salary = builder.salary;
        this.address = builder.address;
        this.phoneNumber = builder.phoneNumber;
        this.isInsuranced = builder.isInsuranced;
    }

    /**
     * Overrided toString method
     * @return string describing class entity
     */
    @Override
    public String toString(){
        return name + " | " + salary + " | " + address + " | " + phoneNumber + " | " + isInsuranced;
    }

    /**
     * Overrided equals method
     * @param obj
     * @return boolean value
     */
    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null || obj.getClass() != this.getClass()) return false;
        Employee employee = (Employee) obj;
        return (employee.name.equals(this.name) &&
                employee.address.equals(this.address) &&
                employee.phoneNumber.equals(this.phoneNumber)
        );
    }

    /**
     * Overrided hashCode method
     * @return hash
     */
    @Override
    public int hashCode(){
        int hash = 31;
        hash = 7 * hash + Objects.hashCode(this.name);
        hash = 7 * hash + Objects.hashCode(this.address);
        hash = 7 * hash + Objects.hashCode(this.phoneNumber);
        return hash;
    }

    /**
     * Builder pattern
     */
    public static class EmployeeBuilder{
        /**
         * @param name is mandatory, others are optional
         */
        @Pattern(regexp = "^[A-Z][a-z]*\\s[A-Z][a-z]*$", message = "Incorrect name")
        private String name;

        @NotNull
        @Min(value = 6000, message = "Salary can't be less than 6000")
        private double salary;

        @NotEmpty(message = "Address can't be empty")
        private String address;

        @Pattern(regexp = "^(380(50|95|99|67|66|68)[0-9]{7})$", message = "Incorrect phone number format")
        private String phoneNumber;
        private boolean isInsuranced;

        /**
         * Builder constructor with required parameters
         * @param name
         */
        public EmployeeBuilder(String name){
            this.name = name;
        }

        /**
         * Builder name setter
         * @param name
         * @return object
         */

        public EmployeeBuilder setName(String name) {
            this.name = name;
            return this;
        }
        /**
         * Builder salary setter
         * @param salary
         * @return object
         */
        public EmployeeBuilder setSalary(double salary) {
            this.salary = salary;
            return this;
        }

        /**
         * Builder address setter
         * @param address
         * @return object
         */
        public EmployeeBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        /**
         * Builder phoneNumber setter
         * @param phoneNumber
         * @return object
         */
        public EmployeeBuilder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        /**
         * Builder isInsuranced setter
         * @param isInsuranced
         * @return object
         */
        public EmployeeBuilder setIsInsuranced(boolean isInsuranced) {
            this.isInsuranced = isInsuranced;
            return this;
        }

        /**
         * Builder build method
         * @return instance of Employee class
         */
        public Employee build(){
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set<ConstraintViolation<EmployeeBuilder>> constraintViolations = validator.validate(this);
            StringBuilder exceptions = new StringBuilder("\n");
            for(ConstraintViolation constraintViolation : constraintViolations) {
                String fieldName = constraintViolation.getPropertyPath().toString().toUpperCase();
                exceptions.append(fieldName).append(" ").append(constraintViolation.getMessage()).append("\n");
            }
            if(constraintViolations.size() > 0)throw new IllegalArgumentException(String.valueOf(exceptions));

            return new Employee(this);
        }

    }

    /**
     * name getter
     */
    public String getName(){
        return name;
    }

    /**
     * salary getter
     */
    public double getSalary(){
        return salary;
    }

    /**
     * address getter
     */
    public String getAddress(){
        return address;
    }

    /**
     * phoneNumber getter
     */
    public String getPhoneNumber(){
        return phoneNumber;
    }

    /**
     * isInsuranced getter
     */
    public boolean getIsInsuranced(){
        return isInsuranced;
    }

    /**
     * name setter
     */
    public void setName(String name) { this.name = name; }

    /**
     * salary setter
     */
    public void setSalary(double salary) { this.salary = salary; }

    /**
     * phoneNumber setter
     */
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    /**
     * address setter
     */
    public void setAddress(String address) { this.address = address; }

    /**
     * isInsuranced setter
     */
    public void setIsInsuranced(boolean isInsuranced) { this.isInsuranced = isInsuranced; }
}