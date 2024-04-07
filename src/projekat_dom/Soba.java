package projekat_dom;

public class Soba {
	private int br_sobe;
	private int br_kreveta;
	private int br_stanara;
	private boolean novi;
	private boolean zenska;
	
	public Soba(int br_sobe,int br_kreveta,int br_stanara,boolean novi, boolean zenska) {
		this.br_sobe=br_sobe;
		this.br_stanara=br_stanara;
		this.br_kreveta=br_kreveta;
		this.novi=novi;
		this.zenska=zenska;
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
	/*public float tezinska_vr_dev_only() {
		if(novi) return (float) (1.01+br_kreveta/3);
		else
			return (float) (0.5+br_kreveta/3);
	}*/
	public boolean isEmpty() {
		if(br_stanara<br_kreveta)
			return true;
		else return false;
	}
	public void populate() {
		br_stanara++;
	}
	
}
