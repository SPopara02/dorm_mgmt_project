package projekat_dom;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class StudentiTableModel2 extends AbstractTableModel{
	private ArrayList<Student> lista;
	
	public StudentiTableModel2(ArrayList<Student> lista) {
		super();
		this.lista = lista;
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public int getColumnCount() {
		return 6;
	}

	@Override
	public Object getValueAt(int r, int c) {
		Student s=lista.get(r);
		switch(c) {
		case 0: return s.getId_studenta();
		case 1: return s.getIme();
		case 2: return s.getPrezime();
		case 3: return s.getPol();
		case 4: return s.getSkor();
		case 5: return s.getBr_sobe();
		default: return "Greska!";
		}
	}
	
	@Override
	public String getColumnName(int c) {
		switch(c) {
		case 0: return "ID";
		case 1: return "Ime";
		case 2: return "Prezime";
		case 3: return "Pol";
		case 4: return "Skor";
		case 5: return "Br. sobe";
		default: return "Greska!";
		}
	}

}
