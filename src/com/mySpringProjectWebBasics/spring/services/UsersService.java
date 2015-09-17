package com.mySpringProjectWebBasics.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.mySpringProjectWebBasics.spring.dao.OffersDAO;
import com.mySpringProjectWebBasics.spring.dao.Offer;
import com.mySpringProjectWebBasics.spring.dao.User;
import com.mySpringProjectWebBasics.spring.dao.UsersDAO;

@Service("usersService")
public class UsersService {
	private UsersDAO usersDao;

	@Autowired
	public void setOffersDao(UsersDAO usersDao) {
		this.usersDao = usersDao;
	}

	public void create(User user) {
		usersDao.create(user);
	}

	public boolean exists(String username) {
		return usersDao.exists(username);
	}
	
	
	/*able to use this because of annotations enabled.
	 * <security:global-method-security secured-annotations="enabled"></security:global-method-security> in security-context.xml
	 * */
	@Secured("ROLE_ADMIN")
	public List<User> getAllUsers() {
		return usersDao.getAllUsers();
	}
	
}
