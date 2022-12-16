package lab5;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.*;
import java.util.*;

public class Supplier implements Comparable<Supplier>{
    private String name;
    private String address;
    private Employee contactPerson;
    private List<Product> products;

    Supplier(){

    }

    /**
     * Supplier constructor
     * @param builder
     */
    private Supplier(SupplierBuilder builder){
        this.name = builder.name;
        this.address = builder.address;
        this.contactPerson = builder.contactPerson;
        this.products = new ArrayList<Product>();
//        this.products = builder.products;
    }

    /**
     * Overrided toString method
     * @return string describing class entity
     */
    @Override
    public String toString(){
        return name + " | " + address + " | Contact person: \n" + contactPerson;
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
        Supplier supplier = (Supplier) obj;
        return (supplier.name.equals(this.name) &&
                supplier.address.equals(this.address) &&
                supplier.contactPerson.equals(this.contactPerson)
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
        hash = 7 * hash + Objects.hashCode(this.contactPerson);
        return hash;
    }

    @Override
    public int compareTo(Supplier s) {
        return this.name.toLowerCase().compareTo(s.name.toLowerCase());
    }

    /**
     * Builder pattern
     */
    public static class SupplierBuilder{
        /**
         * @param name is mandatory, others are optional
         */
        @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters long")
        private String name;

        @NotBlank(message = "Address can't be empty")
        private String address;

        @NotNull(message = "Contact person can't be null")
        private Employee contactPerson;


        /**
         * Builder constructor with required parameters
         * @param name
         */
        public SupplierBuilder(String name){
            this.name = name;
        }

        /**
         * Builder name setter
         * @param name
         * @return object
         */
        public SupplierBuilder setName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Builder contact person setter
         * @param contactPerson
         * @return object
         */
        public SupplierBuilder setContactPerson(Employee contactPerson) {
            this.contactPerson= contactPerson;
            return this;
        }

        /**
         * Builder address setter
         * @param address
         * @return object
         */
        public SupplierBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        /**
         * Builder build method
         * @return instance of Supplier class
         */
        public Supplier build(){
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set<ConstraintViolation<Supplier.SupplierBuilder>> constraintViolations = validator.validate(this);

            StringBuilder exceptions = new StringBuilder("\n");
            for(ConstraintViolation constraintViolation : constraintViolations) {
                String fieldName = constraintViolation.getPropertyPath().toString().toUpperCase();
                exceptions.append(fieldName).append(" ").append(constraintViolation.getMessage()).append("\n");
            }
            if(constraintViolations.size() > 0)throw new IllegalArgumentException(String.valueOf(exceptions));

            return new Supplier(this);
        }

    }

    /**
     * name getter
     */
    public String getName(){
        return name;
    }

    /**
     * contactPerson getter
     */
    public Employee getContactPerson() { return contactPerson; }

    /**
     * address getter
     */
    public String getAddress() { return address; }

    /**
     * products getter
     */
    public List<Product> getProducts(){
        return products;
    }

    /**
     * name setter
     */
    public void setName(String name) { this.name = name; }

    /**
     * address setter
     */
    public void setAddress(String address) { this.address = address; }

    /**
     * contactPerson setter
     */
    public void setContactPerson(Employee contactPerson) { this.contactPerson = contactPerson; }

    /**
     * products setter
     */
    public void setProducts(List<Product> products) { this.products = products; }

    /**
     * product setter
     */
    public void setProduct(Product product){
        this.products.add(product);
    }
}