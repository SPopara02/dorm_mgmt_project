package projekat_dom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAO {

	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	private void connect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost/dom", "root", "");
	}

	public ArrayList<Student> vratiStudente() throws ClassNotFoundException, SQLException {
		ArrayList<Student> lista = new ArrayList<Student>();

		connect();
		preparedStatement=connect.prepareStatement("UPDATE studenti\r\n"
				+ "SET ocistio=CASE\r\n"
				+ "	WHEN espb/god_st=60 THEN true\r\n"
				+ "    ELSE false\r\n"
				+ "    END");
		preparedStatement.executeUpdate();
		statement = connect.createStatement();
		resultSet = statement.executeQuery("select * from studenti");

		while (resultSet.next()) {
			
			int id = resultSet.getInt("id_studenta");
			String ime = resultSet.getString("ime");
			String prezime = resultSet.getString("prezime");
			String indeks=resultSet.getString("indeks");
			gender pol=gender.valueOf(resultSet.getString("pol"));
			double prosek=resultSet.getDouble("prosek");
			int espb=resultSet.getInt("espb");
			int god_st=resultSet.getInt("god_st");
			boolean status=resultSet.getBoolean("status");
			boolean ocistio=resultSet.getBoolean("ocistio");
			double skor=resultSet.getDouble("skor");
			boolean platio_dom=resultSet.getBoolean("platio_dom");
			int br_sobe=resultSet.getInt("soba");
			
			Student s=new Student(id, ime, prezime, indeks, pol, prosek, espb, god_st, status, ocistio, skor,platio_dom,br_sobe);

			

			lista.add(s);
		}
		close();
		return lista;
	}
	public ArrayList<Student> vratiStudentePoSkoru() throws ClassNotFoundException, SQLException {
		ArrayList<Student> lista = new ArrayList<Student>();
		
		connect();
		preparedStatement=connect.prepareStatement("UPDATE studenti\r\n"
				+ "SET ocistio=CASE\r\n"
				+ "	WHEN espb/god_st=60 THEN true\r\n"
				+ "    ELSE false\r\n"
				+ "    END");
		preparedStatement.executeUpdate();
		statement = connect.createStatement();
		resultSet = statement.executeQuery("SELECT * FROM `studenti` ORDER BY `studenti`.`skor` DESC");
		
		
		while (resultSet.next()) {
			
			int id = resultSet.getInt("id_studenta");
			String ime = resultSet.getString("ime");
			String prezime = resultSet.getString("prezime");
			String indeks=resultSet.getString("indeks");
			gender pol=gender.valueOf(resultSet.getString("pol"));
			double prosek=resultSet.getDouble("prosek");
			int espb=resultSet.getInt("espb");
			int god_st=resultSet.getInt("god_st");
			boolean status=resultSet.getBoolean("status");
			boolean ocistio=resultSet.getBoolean("ocistio");
			double skor=resultSet.getDouble("skor");
			boolean platio_dom=resultSet.getBoolean("platio_dom");
			int br_sobe=resultSet.getInt("soba");
			
			Student s=new Student(id, ime, prezime, indeks, pol, prosek, espb, god_st, status, ocistio, skor,platio_dom,br_sobe);
			
			
			
			lista.add(s);
		}
		close();
		return lista;
	}
	
	public void obracunajSkor(Student s) throws SQLException,ClassNotFoundException {
		connect();
		preparedStatement = connect.prepareStatement("UPDATE `studenti` SET `skor` = ? WHERE `studenti`.`id_studenta` = ?;");
		double newSkor=s.getProsek()*5+s.getEspb()*0.8/s.getGod_st();
		if(s.isOcistio()) newSkor+=1;
		if(s.isStatus()) newSkor+=1;
		preparedStatement.setDouble(1, newSkor);
		preparedStatement.setInt(2, s.getId_studenta());
		preparedStatement.executeUpdate();
		close();
	}
	public void resetujSkor(Student s) throws SQLException,ClassNotFoundException {
		connect();
		preparedStatement = connect.prepareStatement("UPDATE `studenti` SET `skor` = 0 WHERE `studenti`.`id_studenta` = ?;");
		preparedStatement.setInt(1, s.getId_studenta());
		preparedStatement.executeUpdate();
		close();
	}
	//DATABASE TRANSACTION METHODS:
	public ArrayList<Soba> vratiSobeM() throws ClassNotFoundException, SQLException{
		ArrayList<Soba> lista=new ArrayList<Soba>();
		connect();
		preparedStatement=connect.prepareStatement("UPDATE sobe\r\n"
				+ "SET tez_skor=CASE\r\n"
				+ "WHEN novi=1 THEN 1.01+1/br_kreveta\r\n"
				+ "ELSE 0.5+1/br_kreveta\r\n"
				+ "END");
		preparedStatement.executeUpdate();
		statement=connect.createStatement();
		resultSet=statement.executeQuery("SELECT `sobe`.*, COUNT(`soba`) AS `br_stanara` FROM `sobe` LEFT JOIN `studenti` ON(`sobe`.`br_sobe`=`studenti`.`soba`) WHERE zenska=0 GROUP BY `tez_skor` DESC;");
		while(resultSet.next()) {
			int br_sobe=resultSet.getInt("br_sobe");
			int br_stanara=resultSet.getInt("br_stanara");
			int br_kreveta=resultSet.getInt("br_kreveta");
			boolean novi=resultSet.getBoolean("novi");
			boolean zenska=resultSet.getBoolean("zenska");
			Soba pom=new Soba(br_sobe, br_kreveta, br_stanara, novi, zenska);
			lista.add(pom);
		}
		close();
		return lista;
	}
	public ArrayList<Soba> vratiSobeZ() throws ClassNotFoundException, SQLException{
		ArrayList<Soba> lista=new ArrayList<Soba>();
		connect();
		preparedStatement=connect.prepareStatement("UPDATE sobe\r\n"
				+ "SET tez_skor=CASE\r\n"
				+ "WHEN novi=1 THEN 1.01+1/br_kreveta\r\n"
				+ "ELSE 0.5+1/br_kreveta\r\n"
				+ "END");
		preparedStatement.executeUpdate();
		statement=connect.createStatement();
		resultSet=statement.executeQuery("SELECT `sobe`.*, COUNT(`soba`) AS `br_stanara` FROM `sobe` LEFT JOIN `studenti` ON(`sobe`.`br_sobe`=`studenti`.`soba`) WHERE zenska=1 GROUP BY `tez_skor` DESC;");
		while(resultSet.next()) {
			int br_sobe=resultSet.getInt("br_sobe");
			int br_stanara=resultSet.getInt("br_stanara");
			int br_kreveta=resultSet.getInt("br_kreveta");
			boolean novi=resultSet.getBoolean("novi");
			boolean zenska=resultSet.getBoolean("zenska");
			Soba pom=new Soba(br_sobe, br_kreveta, br_stanara, novi, zenska);
			//if pom.isEmpty() then:
			lista.add(pom);
		}
		close();
		return lista;
	}
	
	public void dodeliSobu(Student stu, Soba sob) throws SQLException, ClassNotFoundException {
		connect();
		preparedStatement=connect.prepareStatement("UPDATE `studenti` SET `soba` = ? WHERE `studenti`.`id_studenta` = ?;");
		preparedStatement.setInt(1, sob.getBr_sobe());
		preparedStatement.setInt(2, stu.getId_studenta());
		preparedStatement.execute();
		close();
	}
	
	public ArrayList<StudentSoba> vratiEvidenciju() throws SQLException, ClassNotFoundException{
		ArrayList<StudentSoba> lista=new ArrayList<StudentSoba>();
		connect();
		statement=connect.createStatement();
		resultSet=statement.executeQuery("SELECT `studenti`.*,`sobe`.`br_kreveta`,`sobe`.`novi`,`sobe`.`zenska` FROM `studenti` INNER JOIN `sobe` ON(`studenti`.`soba`=`sobe`.`br_sobe`) GROUP BY `id_studenta` ASC;");
		while(resultSet.next()) {
			int id_studenta=resultSet.getInt("id_studenta");
			String ime=resultSet.getString("ime");
			String prezime=resultSet.getString("prezime");
			gender pol=gender.valueOf(resultSet.getString("pol"));
			boolean platio_dom=resultSet.getBoolean("platio_dom");
			int br_sobe=resultSet.getInt("soba");
			int br_kreveta=resultSet.getInt("br_kreveta");
			boolean novi=resultSet.getBoolean("novi");
			boolean zenska=resultSet.getBoolean("zenska");
			StudentSoba pom=new StudentSoba(id_studenta, ime, prezime, pol, platio_dom, br_sobe, br_kreveta, novi, zenska);
			lista.add(pom);
		}
		close();
		return lista;
	}
	
	public int vratiKapacitet() throws SQLException, ClassNotFoundException {
		int kapacitet=0;
		connect();
		statement=connect.createStatement();
		resultSet=statement.executeQuery("SELECT SUM(`br_kreveta`) FROM sobe");
		while(resultSet.next()) {
			kapacitet=resultSet.getInt(1);
		}
		close();
		return kapacitet;
	}

	
	
	/*public ArrayList<Osoba> vratiOsobePSTM() throws ClassNotFoundException, SQLException {
		// POMOCNE PROMENLJIVE ZA KONKRETNU METODU
		ArrayList<Osoba> lista = new ArrayList<Osoba>();

		connect();
		preparedStatement = connect.prepareStatement("select * from osobe");

		// DOPUNJAVANJE SQL STRINGA, SVAKI ? SE MORA PODESITI 
		//preparedStatement.setString(1, o.getJmbg());
		
		preparedStatement.execute();
		
		//****POCETAK	AKO UPIT VRACA REZULTAT TREBA SLEDECI DEO 
		resultSet = preparedStatement.getResultSet();
		
		while (resultSet.next()) {
			String ime = resultSet.getString("ime");
			String prezime = resultSet.getString("prezime");
			int godiste = resultSet.getInt("godiste");

			Osoba o = new Osoba(ime, prezime, godiste);

			lista.add(o);
		}
		//****KRAJ		KRAJ OBRADE ResultSet-a	
		
		close();
		return lista;
	}*/

	/*public void unesiOsobu(Osoba o) throws SQLException, ClassNotFoundException {
		connect();
		preparedStatement = connect.prepareStatement("insert into osobe (ime, prezime, godiste) values (?, ?, ?)");

		preparedStatement.setString(1, o.getIme());
		preparedStatement.setString(2, o.getPrezime());
		preparedStatement.setInt(3, o.getGodiste());
		preparedStatement.executeUpdate();
		close();
	}*/
	
	/*public int unesiOsobuIVratiId(Osoba o) throws SQLException, ClassNotFoundException {
		connect();
		preparedStatement = connect.prepareStatement("insert into osobe (ime, prezime, godiste) values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

		preparedStatement.setString(1, o.getIme());
		preparedStatement.setString(2, o.getPrezime());
		preparedStatement.setInt(3, o.getGodiste());
		preparedStatement.executeUpdate();
		
		
		resultSet = preparedStatement.getResultSet();
		ResultSet keys = preparedStatement.getGeneratedKeys();    
		keys.next();  
		int id = keys.getInt(1);
		close();
		
		return id;
	}*/
	

	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
