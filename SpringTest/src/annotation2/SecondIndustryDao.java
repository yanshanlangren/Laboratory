package annotation2;

import org.springframework.stereotype.Component;

@Component
public class SecondIndustryDao implements IndustryDao {

	@Override
	public void getData(String id) {
		System.out.println("SecondIndustryDao: "+id);
	}

}
