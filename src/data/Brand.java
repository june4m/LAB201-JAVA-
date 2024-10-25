
package data;

//brand: là các dòng xe mà các hãng xe có

public class Brand {

    private String brandID;
    private String brandName;
    private String brandCountry;

    public Brand(String brandID, String brandName, String brandCountry) {
        this.brandID = brandID;
        this.brandName = brandName;
        this.brandCountry = brandCountry;
    }

    public Brand() {
    }

    public String getBrandID() {
        return brandID;
    }

    public void setBrandID(String brandID) {
        this.brandID = brandID;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandCountry() {
        return brandCountry;
    }

    public void setBrandCountry(String brandCountry) {
        this.brandCountry = brandCountry;
    }

    @Override
    public String toString() {
        String str = String.format("%s, %s, %s ", brandID, brandName, brandCountry);
        return str;
    }

}
