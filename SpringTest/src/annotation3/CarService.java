package annotation3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    @Autowired
//    @Qualifier("CarDao")
    private CarDao carDao;

    public void addCar(String car) {
        this.carDao.insertCar(car);
    }
}