
package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringTokenizer;
import utils.Inputter;


public class VehicleList {
    ArrayList<Vehicle>vehicleList = new ArrayList<>(); //danh sác các phương tiện
    BrandList brandList;//Anh quản lý danh sách các brand   
    //làm những hàm liên quan tới VehicleList 
    private Scanner sc = new Scanner(System.in);
    //constructor

    public VehicleList(BrandList brandList) {
        this.brandList = brandList;
    }
    
    
    //hàm đọc file
     public boolean loadFromFile(String url) {
        File file = new File(url);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            /*
                id, name, color, price, brand, type, productionYear
             
            */
            while (line != null) {
                StringTokenizer st = new StringTokenizer(line, "|"); // cai nay du khoang trang
                int id = Integer.parseInt(st.nextToken().trim());
                
                String name = st.nextToken().trim();          
                
                String color = st.nextToken().trim();
                String str = st.nextToken();
//                System.out.println(str);
                double price = Double.parseDouble(str.replace("$", "").trim());
                String brand = st.nextToken();   
                String type = st.nextToken();   
                
                int productYear = Integer.parseInt(st.nextToken().trim()); 

                Vehicle nVehicle = new Vehicle(id, name, color, price, brand, type, productYear);
                vehicleList.add(nVehicle);
                line = br.readLine();
            }
            return true;
        } catch (Exception e) {
            System.out.println("File error: " + e);
            return false;
        }
    }
     
     /*-------------------------------------------------*/
     /*Searching vehicle.*/
    private Vehicle searchByObject(int ID) {
        if (vehicleList.isEmpty()) {
            System.out.println("Have no any vehicle");
            return null;
        }
        for (int i = 0; i < vehicleList.size(); i++) {
            if (vehicleList.get(i).getId() == ID) {
                return vehicleList.get(i);
            }
        }
        return null;
    }
    /*Searching by id*/
    private int searchByID(int ID) {
        for (int i = 0; i < vehicleList.size(); i++) {
            if (vehicleList.get(i).getId() == ID) {
                return i;
            }
        }
        return -1;
    }
    
    
    //tạo id pro style Minh đẹp trai
     public int generateId() {
        int id;
        System.out.println("size = " + vehicleList.size());
        Comparator sortByID = new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle v1, Vehicle v2) {
                return v1.getId()- v2.getId();
            }
        };      
        Collections.sort(vehicleList, sortByID);   
        if (vehicleList.size() == 0) {
            id = 100;
        } else {
            id = vehicleList.get(vehicleList.size() - 1).getId() + 1;
        }
        return id;
    }
     
    public void searchVehicleByID(){
        //xin id cần tìm
        int keyId = Inputter.getAnInteger("Input vehicle id you wanna find: ", "That field is required");
        //tìm brand dựa vào id đã xin
        int pos = searchByID(keyId);//tìm ra vị trí
        Vehicle vehicle = pos == -1 ? null : vehicleList.get(pos);
        //nếu có thì update k thì báo k có
        if(vehicle == null){
            System.out.println("Not found");
        }else{
            System.out.println("--------------------------");
            System.out.println("Vehicle you want to find: ");
            System.out.println(vehicle);//.toString
      
        }
    }
    
     //Searching by name.
    public void searchVehicleByName() {
        if (vehicleList.isEmpty()) {
            System.out.println("Have no any vehicle!!!");
        }
        String name = Inputter.getString("Input name of vehicle: ", "Invalid!!!").trim();
        ArrayList<Vehicle> vehicleListByName = new ArrayList<>();

        for (Vehicle vehicle : vehicleListByName) {
            if(vehicle.getName().contains(name)){
                vehicleListByName.add(vehicle);
            }
        }

        Comparator getProductionYear = new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle v1, Vehicle v2) {
                return v1.getProductionYear()- v2.getProductionYear();
            }
        };
        Collections.sort(vehicleListByName, getProductionYear);
        if (vehicleListByName.isEmpty()) {
            System.out.println("Have no any product found!!!");
        } else {
            for (Vehicle vehicle : vehicleListByName) {
                System.out.println(vehicle);
            }
        }
    }
    //Adding new vehicle. 
    /*
        id, name, color, price, brand, type, productYear..
    */
    public void addNewVehicle() {
        boolean checkExit = true;
        do {
            int ID = generateId();
            String name = Inputter.getString("Input name of vehicle: ", "That field is required!!!");
            String color = Inputter.getString("Input color of vehicle: ", "That field is required!!!");
            double price = Inputter.getADouble("Input price of vehicle  : ", "That field is required!!!", 0, 10000000);
            String brand = Inputter.getString("Input brand of vehicle: ", "That field is required!!!");
            System.out.println("brandName = " + brand);
            String type = Inputter.getString("Input type of vehicle: ", "That field is required!!!");
            System.out.println("Type = " + type);
            int productYear = Inputter.getAnInteger("Input productYear of vehicle: ", "That field is required!!!", 1975, 2030);
            Vehicle nProduct = new Vehicle(ID, name, color, price, brand, type, productYear);
            
            vehicleList.add(nProduct);

            int choice = Inputter.getAnInteger("Do you want to add more product 0.No || 1.Yes ", "Invalid!!!", 0, 1);
            checkExit = choice == 1 ? true : false;

        } while (checkExit);

        System.out.println("Add new product successfully!");
    }
    
    /*--------------------------------------*/
    
    //Displaying vehicle list.
     public void listVehicle() {
        if (vehicleList.isEmpty()) {
            System.out.println("Have no any vehicle!!!");
            return;
        }

        Comparator<Vehicle> price = new Comparator<Vehicle>() {
        @Override
        public int compare(Vehicle v1, Vehicle v2) {
            int comparation = Double.compare(v2.getPrice(), v1.getPrice()); // Đổi thứ tự so sánh để giảm dần
            if (comparation == 0) {
                return v1.getName().compareToIgnoreCase(v2.getName());
            }
            return comparation;
        }
        };
        Collections.sort(vehicleList, price);
        System.out.println("______productList_______");
        for (Vehicle vehicle : vehicleList) {
             System.out.println(vehicle);
        }
    }
    //Delete Vehicle
    public void deleteVehicle() {
        listVehicle();
        boolean checkExit = true;
        do {
            int keyID = Inputter.getAnInteger("Input ID of vehicle u wanna delete: ",
                    "That field is required!!!");
            int pos = searchByID(keyID);
            Vehicle p = pos == -1 ? null : vehicleList.get(pos);
            if (p == null) {
                System.out.println("vehicle does not exist!!!");
            } else {
                System.out.println("Here is your vehicle");
                System.out.println(p);
                System.out.println();
                int choice = Inputter.getAnInteger("Are you sure to delete? 0.No 1.Yes: ", "Please input Integer 0 - 1", 0, 1);
                if (choice == 1) {
                    vehicleList.remove(p);
                    System.out.println("removing successfully!!!");
                }

                int choiceExit = Inputter.getAnInteger("Do you want to add more vehicle 0.No || 1.Yes ", "Invalid!!!");
                checkExit = choiceExit == 1 ? true : false;
            }

        } while (checkExit);

    }
    //Updating vehicle  
    public void updateVehicle() {
        int id = Inputter.getAnInteger("Enter Vehicle ID to update: ", "ID is required");
        Vehicle vehicle = searchByObject(id);
        
        if (vehicle != null) {
            System.out.println("Updating vehicle: " + vehicle);
            // Input new details, skip empty ones
            String newName = Inputter.updateString(vehicle.getName(), "Enter new name (or leave empty to keep current): ");
            String newColor = Inputter.updateString(vehicle.getColor(), "Enter new color: ");
            double newPrice = Inputter.updateDouble(vehicle.getPrice(), "Enter new price: ");
            // Update fields
            vehicle.setName(newName);
            vehicle.setColor(newColor);
            vehicle.setPrice(newPrice);
            System.out.println("Vehicle updated successfully!");
        } else {
            System.out.println("Vehicle does not exist.");
        }
    }

    //Save To File
    public boolean saveToFile(String url){
        File f  = new File(url);
        try{
            //xử lý
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(f));
            for (Vehicle vehicle : vehicleList) {
                writer.write(vehicle.toString());// đọc file
                writer.write("\n"); // xuống dòng   
            }
            writer.flush();// phòng ngừa trường hợp viết file chưa xong chương trình đã tắt
            //xong thì 
            return true;
        }catch(Exception e){
            System.out.println("Save Vehicle file Error: " + e);
            return false;
        }
    }
    
}
