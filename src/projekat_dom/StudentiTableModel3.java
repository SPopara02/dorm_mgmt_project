package projekat_dom;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class StudentiTableModel3 extends AbstractTableModel {
	private ArrayList<StudentSoba> lista;

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public int getColumnCount() {
		return 9;
	}

	@Override
	public Object getValueAt(int r, int c) {
		StudentSoba s=lista.get(r);
		switch(c) {
		case 0: return s.getId_studenta();
		case 1: return s.getIme();
		case 2: return s.getPrezime();
		case 3: return s.getPol();
		case 5: return s.getBr_sobe();
		case 4: return s.isPlatio_dom();
		case 6: return s.getBr_kreveta();
		case 7: return s.isNovi();
		case 8: return s.isZenska();
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
		case 5: return "Br. sobe";
		case 4: return "Platio/la dom";
		case 6: return "Br. kreveta";
		case 7: return "Novi dom";
		case 8: return "Zenski paviljon";
		default: return "Greska!";
		}
	}

	public StudentiTableModel3(ArrayList<StudentSoba> lista) {
		super();
		this.lista = lista;
	}

}
