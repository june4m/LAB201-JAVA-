
package data;

/*
    id, name, color, price, brand, type, productYear..
 */
public class Vehicle {
    private int id;
    private String name;
    private String color;
    private double price;
    private String brand;
    private String type;
    private int productionYear;
    
    //Phễu
    
    public Vehicle() {
    }

    public Vehicle(int id, String name, String color, double price, String brand, String type, int productionYear) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.price = price;
        this.brand = brand;
        this.type = type;
        this.productionYear = productionYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }
    
    
    
    
    /*
    id, name, color, price, brand, type, productYear..
 */

    @Override
    public String toString() {
        String str = String.format(" %3d | %10s | %15s | %.2f $ | %10s | %10s | %4d", id, name, color, price, brand, type, productionYear);
        return str;
    }
    
}
