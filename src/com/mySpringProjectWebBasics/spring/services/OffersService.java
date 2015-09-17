package com.mySpringProjectWebBasics.spring.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mySpringProjectWebBasics.spring.dao.OffersDAO;
import com.mySpringProjectWebBasics.spring.dao.Offer;

@Service("offersService")
public class OffersService {
	private OffersDAO offersDao;

	@Autowired
	public void setOffersDao(OffersDAO offersDao) {
		this.offersDao = offersDao;
	}
	
	public List<Offer> getCurrent(){
		return offersDao.getOffers();
	}

	public void create(Offer offer) {
		offersDao.create(offer);
	}
	
}
