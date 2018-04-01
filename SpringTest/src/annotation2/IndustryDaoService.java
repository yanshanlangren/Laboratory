package annotation2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class IndustryDaoService {

	@Autowired
	@Qualifier("FirstIndustryDao")
	private IndustryDao dao;
	
	public void insertIndustry(String str) {
		dao.getData(str);
	}
}
