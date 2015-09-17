/*
 * OffersDAO has to use the connection properties which has been declared in the beans.xml with the dataSource class
 * So we have to make sure that is injected here with @Autowired so that it can use the connection pool.*/

/*
 * If we like to place the ?(Placeholder) in sql query to supply any dynamic params then NamedParameterJdbcTemplate is used*/

package com.mySpringProjectWebBasics.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/*The @Component is necessary in here because we are using the Autowiring dataSource connection pool below. So this should be a component.
 * But the offerDao name is referred no where I guess. Not guess, Yes it is just named but no where used.*/
@Component("offersDao")
public class OffersDAO {
	//JdbcTemplate class works instead of NamedParameterJdbcTemplate for this method because we are not supplying any parameters dynamically
	private NamedParameterJdbcTemplate jdbc;
	
	public OffersDAO(){
		System.out.println("I am working from OffersDAO");
	}
	
	/*The below autowired is wiring with the id=dataSource in bean-context.xml which get the connection pool whenever the offersDAO class is called.*/
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	
	public List<Offer> getOffers() {
		
		return jdbc.query("select * from offers", new RowMapper<Offer>(){

			public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Offer offer = new Offer();
				offer.setId(rs.getInt("id"));
				offer.setName(rs.getString("name"));
				offer.setText(rs.getString("text"));
				offer.setEmail(rs.getString("email"));
				
				return offer;
			}
			
		});
	}
	
	public Offer getOffer(int id) {
		// NamedParameterJdbcTemplate should be used as we are passing dynamic param
		//JdbcTemplate class doesn't work here
		MapSqlParameterSource param = new MapSqlParameterSource("id", id);
		//param.addValue("id", id);
		return jdbc.queryForObject("select* from offers where id = :id", param, new RowMapper<Offer>(){

			public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Offer offer = new Offer();
				offer.setId(rs.getInt("id"));
				offer.setName(rs.getString("name"));
				offer.setText(rs.getString("text"));
				offer.setEmail(rs.getString("email"));
				
				return offer;
			}
			
		});
	}
	
	public boolean create(Offer offer){
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(offer);
		return jdbc.update("INSERT into offers (name, email, text) values (:name, :email, :text)", params) == 1;
	}
	
	/*Just like a prepared statement in sql
	 *@Transactional is used to rollback the batch of operations if any operation in that batch fails. If one insert violates
	 *then all the inserts are rolled back
	 *@Transactional can have an options in the braces saying isolation/propagation etc.. will have its own importance */
	
	@Transactional
	public int[] create(List<Offer> offers){
		SqlParameterSource[] listOfOffers = SqlParameterSourceUtils.createBatch(offers.toArray());
		return jdbc.batchUpdate("INSERT into offers (name, email, text) values (:name, :email, :text)", listOfOffers);
	}
	
	
	public boolean delete(int id){
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", id);
		
		return jdbc.update("delete from offers where id=:id", param) == 1? true:false;
		
	}
}
