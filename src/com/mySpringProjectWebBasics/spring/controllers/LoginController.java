package com.mySpringProjectWebBasics.spring.controllers;

import java.util.List;
import java.util.ListIterator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mySpringProjectWebBasics.spring.dao.Offer;
import com.mySpringProjectWebBasics.spring.dao.User;
import com.mySpringProjectWebBasics.spring.services.UsersService;

@Controller
public class LoginController {
	
	private UsersService usersService;
	
	@Autowired
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}

	@RequestMapping("/login")
	public String showLogin(){
		return "login";
	}
	
	@RequestMapping("/denied")
	public String showDenied(){
		return "denied";
	}
	
	@RequestMapping("/admin")
	public String showAmin(Model model){
		List<User> users = usersService.getAllUsers();
		ListIterator<User> list = users.listIterator();
		while(list.hasNext()){
			System.out.println(list.next());
		}
		model.addAttribute("users", users);
		return "admin";
	}
	
	@RequestMapping("/logout")
	public String showLogout(){
		return "logout";
	}
	
	@RequestMapping("/newAccount")
	public String newAccount(Model model){
		model.addAttribute("user", new User());
		return "newAccount";
	}
	
	@RequestMapping(value="/createAccount", method=RequestMethod.POST)
	public String createAccount(User user, BindingResult result){
		if(result.hasErrors()){
			/*List<ObjectError> errors = result.getAllErrors();
			for(ObjectError error : errors){
				System.out.println(error.getDefaultMessage());
			}*/
			
			return "newAccount";
		}

		user.setAuthority("ROLE_USER");
		user.setEnabled(true);
		
		if(usersService.exists(user.getUsername())){
			result.rejectValue("username"/*this is the path attribute name for the username in newAccount.jsp*/, "DuplicateKey.user.username", "The username already exists!");
			return "newAccount";
		}
		
		try{
		usersService.create(user);
		}catch(DuplicateKeyException ex){
			result.rejectValue("username"/*this is the path attribute name for the username in newAccount.jsp*/, "DuplicateKey.user.username", "The username already exists!");
			return "newAccount";
		}
		return "accountCreated";
	}
}
