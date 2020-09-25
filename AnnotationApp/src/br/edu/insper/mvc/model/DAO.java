package br.edu.insper.mvc.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO {
	
	private Connection connection = null;
	
	public DAO() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		connection  = DriverManager.getConnection("jdbc:mysql://localhost/anotacao", "root", "PASSWORD");
		
	}
	public List<Note> getLista(String ordenacao, String filter) throws SQLException{
		List<Note> notes = new ArrayList<Note>();
		String sql = "SELECT * FROM Note WHERE notes LIKE \"%"+ filter +"%\" ORDER BY " + ordenacao;
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			Note note = new Note();
			note.setId(rs.getInt("id"));
			note.setNotes(rs.getString("notes"));
			note.setType(rs.getString("type"));
			note.setCreation(rs.getDate("creation"));
			note.setDeadline(rs.getDate("deadline"));
			note.setUserid(rs.getString("userid"));
			notes.add(note);
		}
		rs.close();
		stmt.close();
		return notes;
	}
	

	
	
	
	public void adiciona (Note note) throws SQLException {
		
		String sql = "INSERT INTO Note (notes, type, creation, deadline, userid) VALUES (?,?,?,?,?)";
		PreparedStatement stmt =  connection.prepareStatement(sql);
		stmt.setString(1, note.getNotes());
		stmt.setString(2, note.getType());
		stmt.setDate(3, (java.sql.Date) note.getCreation());
		stmt.setDate(4, (java.sql.Date) note.getDeadline());
		stmt.setString(5, note.getUserid());
		stmt.execute();
		stmt.close();
	}
	
	public void adicionaLogin(User user) throws SQLException {
		
		String sql = "INSERT INTO User (firstname, password) VALUES (?,?)";
		PreparedStatement stmt =  connection.prepareStatement(sql);
		stmt.setString(1, user.getFirstname());
		stmt.setString(2, user.getPassword());
		stmt.execute();
		stmt.close();
		
	}
	
	
	public void deleta (String id) throws SQLException {
		String sql = "DELETE FROM Note WHERE id =" + id;
		PreparedStatement stmt =  connection.prepareStatement(sql);
		stmt.execute();
		stmt.close();
	}
	
	public void atualiza (String id, String tarefa, String tipo, String deadline, String userid) throws SQLException {
		String sql = "UPDATE Note SET notes = '" + tarefa + "', type ='" + tipo + "',deadline = '" + deadline + "', userid = '" + userid + "' WHERE id =  '" + id + "' AND userid ='" + userid + "'";
		
		System.out.println(sql);
		PreparedStatement stmt =  connection.prepareStatement(sql);
		stmt.execute();
		stmt.close();
	}
	
	public void ordena (String ordenacao) throws SQLException {
	
		String sql = "SELECT * FROM Note ORDER BY" + ordenacao;
		PreparedStatement stmt =  connection.prepareStatement(sql);
		stmt.execute();
		stmt.close();
	}
	
	public String valida (String nome, String password) throws SQLException {
		String sql = "SELECT * FROM user WHERE firstname = '" + nome + "' AND password = '" + password + "'";
		PreparedStatement stmt =  connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		String user2 = null;
		while(rs.next()) {
			user2 = rs.getString("id");
		}
		rs.close();
		stmt.close();
		
		return user2;
	}
	
	public void close() throws SQLException {
		connection.close();
	}
}
