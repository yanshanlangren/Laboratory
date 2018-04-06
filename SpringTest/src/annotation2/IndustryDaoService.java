package annotation2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class IndustryDaoService {

	@Autowired
<<<<<<< HEAD
//	@Qualifier("secondIndustryDao")
=======
	@Qualifier("firstIndustryDao")
>>>>>>> 10ade6d4e52b7ae90262ff3b2a12a39fd974d43b
	private IndustryDao dao;
	
	public IndustryDaoService() {
		
	}
	
	public void insertIndustry(String str) {
		dao.getData(str);
	}
}
