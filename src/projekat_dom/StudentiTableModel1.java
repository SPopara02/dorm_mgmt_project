package projekat_dom;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class StudentiTableModel1 extends AbstractTableModel {
	private ArrayList<Student> lista;

	public StudentiTableModel1(ArrayList<Student> lista) {
		super();
		this.lista = lista;
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 11;
	}

	@Override
	public Object getValueAt(int r, int c) {
		Student s=lista.get(r);
		switch(c) {
		case 0: return s.getId_studenta();
		case 1: return s.getIme();
		case 2: return s.getPrezime();
		case 3: return s.getIndeks();
		case 4: return s.getPol();
		case 5: return s.getProsek();
		case 6: return s.getEspb();
		case 7: return s.getGod_st();
		case 8: return s.isStatus();
		case 9: return s.isOcistio();
		case 10:return s.getSkor();
		default: return "Greska!";
		}
	}
	@Override
	public String getColumnName(int c) {
		switch(c) {
		case 0: return "ID";
		case 1: return "Ime";
		case 2: return "Prezime";
		case 3: return "Br. Ind.";
		case 4: return "Pol";
		case 5: return "Prosek:";
		case 6: return "ESPB";
		case 7: return "God. st.";
		case 8: return "Status";
		case 9: return "Ocistio";
		case 10: return "Skor";
		default: return "Greska!";
		}
	}

}
