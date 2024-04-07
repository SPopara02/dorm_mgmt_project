package projekat_dom;

enum gender{M,Z}
public class Student {
	private int id_studenta;
	private String ime;
	private String prezime;
	private String indeks;
	private gender pol;
	private double prosek;
	private int espb;
	private int god_st;
	private boolean status;
	private boolean ocistio;
	private double skor;
	private boolean platio_dom;
	private int br_sobe;
	public Student(int id_studenta, String ime, String prezime, String indeks, gender pol, double prosek, int espb,
			int god_st, boolean status, boolean ocistio, double skor,boolean platio_dom,int br_sobe) {
		super();
		this.id_studenta = id_studenta;
		this.ime = ime;
		this.prezime = prezime;
		this.indeks = indeks;
		this.pol = pol;
		this.prosek = prosek;
		this.espb = espb;
		this.god_st = god_st;
		this.status = status;
		this.ocistio = ocistio;
		this.skor = skor;
		this.platio_dom=platio_dom;
		this.br_sobe=br_sobe;
	}
	public boolean isPlatio_dom() {
		return platio_dom;
	}
	public int getBr_sobe() {
		return br_sobe;
	}
	public int getId_studenta() {
		return id_studenta;
	}
	public void setId_studenta(int id_studenta) {
		this.id_studenta = id_studenta;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getIndeks() {
		return indeks;
	}
	public void setIndeks(String indeks) {
		this.indeks = indeks;
	}
	public gender getPol() {
		return pol;
	}
	public void setPol(gender pol) {
		this.pol = pol;
	}
	public double getProsek() {
		return prosek;
	}
	public void setProsek(double prosek) {
		this.prosek = prosek;
	}
	public int getEspb() {
		return espb;
	}
	public void setEspb(int espb) {
		this.espb = espb;
	}
	public int getGod_st() {
		return god_st;
	}
	public void setGod_st(int god_st) {
		this.god_st = god_st;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public boolean isOcistio() {
		return ocistio;
	}
	public void setOcistio(boolean ocistio) {
		this.ocistio = ocistio;
	}
	public double getSkor() {
		return skor;
	}
	public void setSkor(double skor) {
		this.skor = skor;
	}


	
}
