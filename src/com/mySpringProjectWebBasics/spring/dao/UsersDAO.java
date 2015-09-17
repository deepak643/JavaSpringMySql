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
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/*The @Component is necessary in here because we are using the Autowiring dataSource connection pool below. So this should be a component.
 * But the offerDao name is referred no where I guess. Not guess, Yes it is just named but no where used.*/
@Component("usersDao")
public class UsersDAO {
	//JdbcTemplate class works instead of NamedParameterJdbcTemplate for this method because we are not supplying any parameters dynamically
	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public UsersDAO(){
		System.out.println("I am working from UsersDAO");
	}
	
	/*The below autowired is wiring with the id=dataSource in bean-context.xml which get the connection pool whenever the offersDAO class is called.*/
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	
	@Transactional
	public boolean create(User user){
		//BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("username", user.getUsername());
		params.addValue("password", passwordEncoder.encode(user.getPassword()));
		params.addValue("email", user.getEmail());
		params.addValue("enabled", user.isEnabled());
		params.addValue("authority", user.getAuthority());
		
		System.out.println("In JDBC");
		jdbc.update("INSERT into users (username, password, enabled, email) values (:username, :password, :enabled, :email)", params);
		System.out.println("After inserting User");
		return jdbc.update("INSERT into authorities (username, authority) values (:username, :authority)", params) == 1;
	}

	public boolean exists(String username) {
		return jdbc.queryForObject("select count(*) from users where username=:username", new MapSqlParameterSource("username", username), Integer.class) > 0;
	}

	public List<User> getAllUsers() {
		return jdbc.query("select * from users, authorities where users.username=authorities.username", BeanPropertyRowMapper.newInstance(User.class));
	}
}

