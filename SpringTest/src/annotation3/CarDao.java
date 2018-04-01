package annotation3;

import org.springframework.stereotype.Repository;

@Repository
public class CarDao {
	
	private String driverName="mysql";
	
	public CarDao(String driver) {
		this.driverName=driver;
	}

	public CarDao() {
	}
	
    public void insertCar(String car) {
        String insertMsg = String.format("inserting car %s into "+this.driverName, car);
        System.out.println(insertMsg);
    }

}