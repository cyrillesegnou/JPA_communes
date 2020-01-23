package fr.gtm.communes.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

public class CommuneDaoTest {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/communes-france";
	String user = "root";
	String pswd = "P@ssw0rd";
	
	@Test
	public void testCommuneDao() throws ClassNotFoundException {
		CommuneDao dao = new CommuneDao(driver, url, user, pswd);
		assertNotNull(dao);
	}
	
	@Test(expected = ClassNotFoundException.class)
	public void testCommuneDaoException() throws ClassNotFoundException {
		CommuneDao dao = new CommuneDao("foo", url, user, pswd);
		assertNull(dao);
	}

	@Test
	public void testFindCommunesByCodePostal() throws ClassNotFoundException, SQLException{
		CommuneDao dao = new CommuneDao(driver, url, user, pswd);
		assertTrue(dao.findCommunesByCodePostal("999").size()==0);
		assertTrue(dao.findCommunesByCodePostal("358").size()>0);
	}

	@Test
	public void testFindCommunesByNomDepartement() throws ClassNotFoundException, SQLException {
		CommuneDao dao = new CommuneDao(driver, url, user, pswd);
		assertTrue(dao.findCommunesByNomDepartement("ain").size()>0);
		assertTrue(dao.findCommunesByNomDepartement("foo").size()==0);
	}

}
