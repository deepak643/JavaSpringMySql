package com.mySpringProjectWebBasics.spring.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mySpringProjectWebBasics.spring.dao.Offer;
import com.mySpringProjectWebBasics.spring.services.OffersService;

@Controller
public class OffersController {
	private OffersService offersService;
	
	@Autowired
	public void setOffersService(OffersService offersService) {
		this.offersService = offersService;
	}

	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String test(Model model, @RequestParam("id") String id){
		System.out.println("Id is: "+ id);
		return "home";
	}

/*	@ExceptionHandler(DataAccessException.class)
	public String handleDatabaseException(DataAccessException ex){
		return "error";
	}*/
	
	@RequestMapping("/offers")
	public String showOffers(Model model){
		List<Offer> offers = offersService.getCurrent();
		model.addAttribute("offers", offers);
		return "offers";
	}
	
	@RequestMapping("/createOffer")
	public String createOffer(Model model){
		model.addAttribute("offer", new Offer());
		return "createOffer";
	}
	
	@RequestMapping(value="/doCreate", method=RequestMethod.POST)
	public String doCreate(Model model, @Valid Offer offer, BindingResult result){
		if(result.hasErrors()){
			/*List<ObjectError> errors = result.getAllErrors();
			for(ObjectError error : errors){
				System.out.println(error.getDefaultMessage());
			}*/
			
			return "createOffer";
		}
		offersService.create(offer);
		return "offerCreated";
	}
}	