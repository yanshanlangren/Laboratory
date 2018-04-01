package annotation2;

import org.springframework.stereotype.Component;

@Component
public class FirstIndustryDao implements IndustryDao{

	@Override
	public void getData(String id) {
		System.out.println("FirstIndustryDao: "+id);
	}

}
