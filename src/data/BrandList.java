package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.StringTokenizer;
import utils.Inputter;

public class BrandList {

    ArrayList<Brand> brandList = new ArrayList<>();

    public BrandList() {
    }

    public boolean loadFromFile(String url) {
        File file = new File(url);
        try {
            BufferedReader bf = new BufferedReader(new FileReader(file));
            String line = bf.readLine();
            while (line != null) {
                StringTokenizer st = new StringTokenizer(line, ",");
                String brandID = st.nextToken().trim();
                String brandName = st.nextToken().trim();
                String brandCountry = st.nextToken().trim();
                Brand nBrand = new Brand(brandID, brandName, brandCountry);
                brandList.add(nBrand);
                line = bf.readLine();
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return false;
        }
    }

    public void listBrand() {
        if (brandList.isEmpty()) {
            System.out.println("Brand List has nothing to print");
            return;
        }
        System.out.println("_____brandList_____");
        for (Brand brand : brandList) {
            System.out.println(brand);
        }
    }

    public int searchBrandByID(String ID) {
        for (int i = 0; i < brandList.size(); i++) {
            if (brandList.get(i).getBrandID().equals(ID)) {
                return i;
            }
        }
        return -1;
    }

    public void addBrand() {
        boolean isDup;
        String ID;
        do {
            isDup = false;
            ID = Inputter.getString("Input brand id: ",
                    "that field is required!!!");
            int pos = searchBrandByID(ID);
            if (pos != -1) {
                System.out.println("Brand id is has been used");
                isDup = true;
            }
        } while (isDup);
        String brandName = Inputter.getString("Input brand name: ", "That field is required!!!");
        String brandCountry = Inputter.getString("Input brand country: ", "That field is required!!!");
        Brand nBrand = new Brand(ID, brandName, brandCountry);
        brandList.add(nBrand);
        System.out.println("Add new brand successfully!!!");
    }

    public String getBrandID() {
        String brandID;
        System.out.println("_____BrandList_____");
        for (Brand brand : brandList) {
            System.out.println(brand);
        }

        while (true) {
            brandID = Inputter.getID("Input brand ID: ", "That field is required!!!", "B\\d{3}");
            for (int i = 0; i < brandList.size(); i++) {
                String tmp = brandList.get(i).getBrandID();
                if (tmp.equalsIgnoreCase(brandID)) {
                    return brandList.get(i).getBrandID();
                }
            }
            System.out.println("This ID is not exist, Please try again!!!");
        }
    }

    public String getBrandName(String brandID) {
        while (true) {
            for (int i = 0; i < brandList.size(); i++) {
                String tmp = brandList.get(i).getBrandID();
                if (tmp.equalsIgnoreCase(brandID)) {
                    return brandList.get(i).getBrandName();
                }
            }
        }
    }

}
