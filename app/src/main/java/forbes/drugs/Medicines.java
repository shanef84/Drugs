package forbes.drugs;

public class Medicines {
    private String ProductName;
    private String LicenseNumber;

    public Medicines() {
    }

    public Medicines(String name, String number) {
        this.ProductName = name;
        this.LicenseNumber = number;
    }

    public String getProductName() {
        return ProductName;
    }

    public String getLicenseNumber() {
        return LicenseNumber;
    }

}