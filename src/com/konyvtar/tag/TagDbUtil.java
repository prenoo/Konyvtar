package com.konyvtar.tag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class TagDbUtil {
	
	private static TagDbUtil instance;
	private DataSource dataSource;
	private String jndiName = "java:comp/env/jdbc/konyvtar";
	
	public static TagDbUtil getInstance() throws Exception {
		if (instance == null) {
			instance = new TagDbUtil();
		}
		
		return instance;
	}
	
	private TagDbUtil() throws Exception {		
		dataSource = getDataSource();
	}

	private DataSource getDataSource() throws NamingException {
		Context context = new InitialContext();
		
		DataSource theDataSource = (DataSource) context.lookup(jndiName);
		
		return theDataSource;
	}
		
	public List<Tag> getTagok() throws Exception {

		List<Tag> tagok = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();

			String sql = "select * from tag";

			myStmt = myConn.createStatement();

			myRs = myStmt.executeQuery(sql);

			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				int id = myRs.getInt("tag_ID");
				String nev = myRs.getString("nev");
				String cim = myRs.getString("cim");
				String telefonszam = myRs.getString("telefonszam");

				// create new student object
				Tag tempTag = new Tag(id, nev, cim, telefonszam);

				// add it to the list of students
				tagok.add(tempTag);
			}
			
			return tagok;		
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}

	public void addTag(Tag theTag) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "insert into tag (tag_ID, nev, cim, telefonszam) values (?, ?, ?, ?)";

			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, theTag.getId());
			myStmt.setString(2, theTag.getNev());
			myStmt.setString(3, theTag.getCim());
			myStmt.setString(4, theTag.getTelefonszam());
			
			myStmt.execute();			
		}
		finally {
			close (myConn, myStmt);
		}
		
	}
	
	public Tag getTag(int tagID) throws Exception {
	
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();

			String sql = "select * from tag where id=?";

			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, tagID);
			
			myRs = myStmt.executeQuery();

			Tag theTag = null;
			
			// retrieve data from result set row
			if (myRs.next()) {
				int id = myRs.getInt("tag_ID");
				String nev = myRs.getString("nev");
				String cim = myRs.getString("cim");
				String telefonszam = myRs.getString("telefonszam");

				theTag = new Tag(id, nev, cim, telefonszam);
			}
			else {
				throw new Exception("Could not find student id: " + tagID);
			}

			return theTag;
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
	
	public void updateTag(Tag theTag) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "update tag "
						+ " set first_name=?, last_name=?, email=?"
						+ " where tag_ID=?";

			myStmt = myConn.prepareStatement(sql);

			// set params

			myStmt.setString(1, theTag.getNev());
			myStmt.setString(2, theTag.getCim());
			myStmt.setString(3, theTag.getTelefonszam());
			myStmt.setInt(4, theTag.getId());
			myStmt.execute();
		}
		finally {
			close (myConn, myStmt);
		}
		
	}
	
	public void deleteTag(int tagID) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "delete from tag where tag_ID=?";

			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, tagID);
			
			myStmt.execute();
		}
		finally {
			close (myConn, myStmt);
		}		
	}	
	
	private Connection getConnection() throws Exception {

		Connection theConn = dataSource.getConnection();
		
		return theConn;
	}
	
	private void close(Connection theConn, Statement theStmt) {
		close(theConn, theStmt, null);
	}
	
	private void close(Connection theConn, Statement theStmt, ResultSet theRs) {

		try {
			if (theRs != null) {
				theRs.close();
			}

			if (theStmt != null) {
				theStmt.close();
			}

			if (theConn != null) {
				theConn.close();
			}
			
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}	

}
