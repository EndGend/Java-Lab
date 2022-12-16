package lab5;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

public class Product implements Comparable<Product>{
    private final int barcode;
    private String name;
    private double price;
    private String category;
    private int quantity;

    /**
     * Product constructor
     * @param builder
     */
    private Product(ProductBuilder builder){
        this.barcode= builder.barcode;
        this.name = builder.name;
        this.price = builder.price;
        this.category = builder.category;
        this.quantity = builder.quantity;
    }

    /**
     * Overrided toString method
     * @return string describing class entity
     */
    @Override
    public String toString(){
        return barcode + " | " + name + " | " + quantity + " | " + price + " | " + category;
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
        Product product = (Product) obj;
        return (product.barcode == this.barcode);
    }

    /**
     * Overrided hashCode method
     * @return hash
     */
    @Override
    public int hashCode(){
        return 31 * 7 + this.barcode;
    }

    @Override
    public int compareTo(Product p) {
        return Double.compare(this.price, p.getPrice());
    }

    /**
     * Builder pattern
     */
    public static class ProductBuilder{
        /**
         * params name, barcode are mandatory, others are optional
         */
        @NotNull
        @Min(value = 1, message = "Barcode must be greater than 0")
        @Max(value = 100000, message = "Barcode must be less than 100000")
        private int barcode;

        @NotEmpty(message = "Name can't be empty")
        private String name;

        @NotNull
        @Min(value = 0, message = "Price can't be less than 0")
        private double price;

        @NotEmpty(message = "Category can't be empty")
        private String category;

        @NotNull
        @Min(value = 0, message = "Quantity can't be less than 0")
        @Max(value = 100000, message = "Quantity must be less than 100000")
        private int quantity;

        /**
         * Builder constructor with required parameters
         * params name, barcode
         */
        public ProductBuilder(int barcode, String name){
            this.barcode= barcode;
            this.name = name;
        }

        /**
         * Builder barcode setter
         * @param barcode
         * @return object
         */
        public ProductBuilder setBarcode(int barcode) {
            this.barcode = barcode;
            return this;
        }

        /**
         * Builder name setter
         * @param name
         * @return object
         */
        public ProductBuilder setName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Builder price setter
         * @param price
         * @return object
         */
        public ProductBuilder setPrice(double price) {
            this.price = price;
            return this;
        }

        /**
         * Builder category setter
         * @param category
         * @return object
         */
        public ProductBuilder setCategory(String category) {
            this.category = category;
            return this;
        }

        /**
         * Builder quantity setter
         * @param quantity
         * @return object
         */
        public ProductBuilder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        /**
         * Builder build method
         * @return instance of Employee class
         */
        public Product build(){
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set<ConstraintViolation<ProductBuilder>> constraintViolations = validator.validate(this);

            StringBuilder exceptions = new StringBuilder("\n");
            for(ConstraintViolation constraintViolation : constraintViolations) {
                String fieldName = constraintViolation.getPropertyPath().toString().toUpperCase();
                exceptions.append(fieldName).append(" ").append(constraintViolation.getMessage()).append("\n");
            }
            if(constraintViolations.size() > 0)throw new IllegalArgumentException(String.valueOf(exceptions));

            return new Product(this);
        }

    }

    /**
     * name getter
     */
    public String getName(){
        return name;
    }

    /**
     * price getter
     */
    public double getPrice(){
        return price;
    }

    /**
     * barcode getter
     */
    public int getBarcode(){
        return barcode;
    }

    /**
     * category getter
     */
    public String getCategory(){
        return category;
    }

    /**
     *  quantity getter
     */
    public int getQuantity(){
        return quantity;
    }

    /**
     * name setter
     */
    public void setName(String name) { this.name = name; }

    /**
     * category setter
     */
    public void setCategory(String category) { this.category = category; }

    /**
     * price setter
     */
    public void setPrice(double price) { this.price = price; }

    /**
     * quantity setter
     */
    public void setQuantity(int quantity) { this.quantity = quantity; }
}