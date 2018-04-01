package annotation3;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CarService {

//    @Autowired
//    @Qualifier("sqliteCarDao")
	@Resource(name="sqliteCarDao")
    private CarDao carDao;
    
    public CarService(CarDao dao) {
    	this.carDao=dao;
    }
    
    public CarService() {
    }

    public void addCar(String car) {
        this.carDao.insertCar(car);
    }
}