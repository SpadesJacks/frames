package dao;

import com.hibernate.dao.IUserDao;
import com.hibernate.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
// defaultRollback=true不会改变数据库，false会改变数据库
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional(readOnly = true)
public class UserDaoTest {

	@Resource
	private IUserDao userDao;

	@Test
	public void testIsExist() {
		UserEntity user = new UserEntity();
		user.setUsername("zhangsan");
		user.setPassword("123456");
		boolean result = userDao.isExist(user);
		System.out.println(result);
		System.out.println(userDao.findOne(1L));
		
	}

}
