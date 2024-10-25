
package runtime;

import data.BrandList;
import data.Brand;
import data.VehicleList;
import ui.Menu;
import utils.Inputter;

public class VehicleManagement {
    public static void main(String[] args) {
   
        String urlVehicle = "vehicle.txt";
        String urlBrand = "01_brand.txt";
        
        //tạo danh sách và dữ liệu từ tệp
        BrandList brl = new BrandList();
        brl.loadFromFile(urlBrand);
        
        VehicleList v = new VehicleList(brl);
        v.loadFromFile(urlVehicle);
        
                
        Menu menuVehicle = new Menu("Vehicle Management");
        menuVehicle.addNewOption("Add new Vehicle"); //1
        menuVehicle.addNewOption("List of Vehicles"); //2
        menuVehicle.addNewOption("Update Vehicle"); //3
        menuVehicle.addNewOption("Search Vehicle"); //4
        menuVehicle.addNewOption("Delete Vehicle"); //5
        menuVehicle.addNewOption("Save to file");
        menuVehicle.addNewOption("Quit");

        int choice;
        do {
            menuVehicle.print();
            choice = menuVehicle.getChoice();
            switch (choice) {
                case 1:
                    v.addNewVehicle(); //1
                    break;
                case 2:
                    v.listVehicle(); //2
                    break;
                case 3:
                    v.updateVehicle(); //3
                    break;
                case 4:
                    v.searchVehicleByID();
                    break;
                case 5: 
                    v.deleteVehicle();
                    break;
                case 6:
                    v.saveToFile(urlVehicle);
                    break;   
                default:
                    System.out.println("See you again!!!");
            }
        } while (choice != menuVehicle.getSize());

    }
    
}
