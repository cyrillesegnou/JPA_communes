package fr.gtm.communes.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.gtm.communes.entities.Commune;

public class CommuneDao {
	private String url;
	private String user;
	private String pswd;
	
	public CommuneDao(String driver, String url, String user, String pswd) throws ClassNotFoundException {
		this.url = url;
		this.user = user;
		this.pswd = pswd;
		Class.forName(driver);
	}
	
	public List<Commune> findCommunesByCodePostal(String codePostal) throws SQLException{
		Connection connection = DriverManager.getConnection(url, user, pswd);
		String sql = "SELECT * FROM communes WHERE code_postal LIKE '"+codePostal+"%'";
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		List<Commune> communes = new ArrayList<Commune>();
		while(rs.next()) {
			communes.add(createCommune(rs));
		}
		connection.close();
		return communes;
	}
	
	private Commune createCommune(ResultSet rs) throws SQLException  {
		Commune c = new Commune();
		c.setCodePostal(rs.getString("code_postal"));
		c.setDepartement(rs.getString("departement"));
		c.setRegion(rs.getString("region"));
		c.setNom(rs.getString("nom"));
		c.setLatitude(rs.getDouble("latitude"));
		c.setLongitude(rs.getDouble("longitude"));
		c.setId(rs.getLong("id"));
		return c;
	}

	public List<Commune> findCommunesByNomDepartement(String dep) throws SQLException {
		Connection connection = DriverManager.getConnection(url, user, pswd);
		String sql = "SELECT * FROM communes WHERE departement = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, dep);
		ResultSet rs = ps.executeQuery();
		List<Commune> communes = new ArrayList<Commune>();
		while(rs.next()) {
			communes.add(createCommune(rs));
		}
		connection.close();
		return communes;
	}

}
