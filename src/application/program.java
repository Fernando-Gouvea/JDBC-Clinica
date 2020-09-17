package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement st = null;
		
		try	{
			
			conn = DB.getConnection();
			st = conn.prepareStatement("INSERT INTO pessoa"
										+ "(nome, sobrenome, data_nascimento, profissao)"
										+ "VALUES"
										+ "(?,?,?,?)",
										Statement.RETURN_GENERATED_KEYS);
			
			System.out.print("Enter first name: ");
			String nome = sc.nextLine();
			st.setString(1,nome);
			
			System.out.print("Enter last nome: ");
			String sobrenome = sc.nextLine();
			st.setString(2,sobrenome);
			
			System.out.print("Enter birth date (dd/mm/yyyy): ");
			String data = sc.nextLine();
			st.setDate(3, new java.sql.Date(sdf.parse(data).getTime()));
			
			System.out.print("Enter profession: ");
			String prof = sc.nextLine();
			st.setString(4,prof);
			
			int line = st.executeUpdate();
			
			if (line > 0) {
				ResultSet rs = st.getGeneratedKeys();
				while (rs.next()) {
					int id = rs.getInt(1);
					System.out.print("Done!: "+ id);
				}
			}
			
			
		}
		
		catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		finally {
			
			DB.closeConnection();
		}
		
		
		
	}
	
}
		
		
		

	


