package com.techelevator.npgeek.register;

import javax.sql.DataSource;

import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.npgeek.authentication.PasswordHasher;

@Component
public class JdbcRegisterDAO implements RegisterDAO {

	private JdbcTemplate jdbcTemplate;
	private PasswordHasher passwordHasher;
	
	@Autowired
    public JdbcRegisterDAO(DataSource dataSource, PasswordHasher passwordHasher) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.passwordHasher = passwordHasher;
    }
	
	@Override
	public void save(Register register) {
		 byte[] salt = passwordHasher.generateRandomSalt();
	        String hashedPassword = passwordHasher.computeHash(register.getPassword(), salt);
	        String saltString = new String(Base64.encode(salt));   
		
		String sqlInsertRegister = "INSERT INTO userinfo(userid, username, password, passwordhint, email, salt) "
							     + "VALUES(?, ?, ?, ?, ?, ?)";
		
		Long id = getNextId();	
		jdbcTemplate.update(sqlInsertRegister, id, register.getUserName(),hashedPassword ,saltString,  register.getPasswordHint(), register.getEmailAddress());
		register.setUserId(id);
	}
		
	private Long getNextId() {
		String sqlSelectNextId = "SELECT NEXTVAL('seq_userinfo_id')";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlSelectNextId);
		if (result.next()) {
			return result.getLong(1);
		} else {
			throw new RuntimeException("Something went wrong while getting the next product id");
		}
	}
}
