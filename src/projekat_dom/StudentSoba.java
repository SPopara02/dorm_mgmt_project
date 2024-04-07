package projekat_dom;

public class StudentSoba {
	private int id_studenta;
	private String ime;
	private String prezime;
	private gender pol;
	private boolean platio_dom;
	private int br_sobe;
	private int br_kreveta;
	private boolean novi;
	private boolean zenska;
	public int getId_studenta() {
		return id_studenta;
	}
	public String getIme() {
		return ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public gender getPol() {
		return pol;
	}
	public boolean isPlatio_dom() {
		return platio_dom;
	}
	public int getBr_sobe() {
		return br_sobe;
	}
	public int getBr_kreveta() {
		return br_kreveta;
	}
	public boolean isNovi() {
		return novi;
	}
	public boolean isZenska() {
		return zenska;
	}
	public StudentSoba(int id_studenta, String ime, String prezime, gender pol, boolean platio_dom, int br_sobe,
			int br_kreveta, boolean novi, boolean zenska) {
		super();
		this.id_studenta = id_studenta;
		this.ime = ime;
		this.prezime = prezime;
		this.pol = pol;
		this.platio_dom = platio_dom;
		this.br_sobe = br_sobe;
		this.br_kreveta = br_kreveta;
		this.novi = novi;
		this.zenska = zenska;
	}
	
}
