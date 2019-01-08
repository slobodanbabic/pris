package customBeans;

import java.util.ArrayList;
import java.util.List;

import managers.PolasciManager;
import model.Linija;

public class SveRazliciteLinije {

	private List<Linija> sveRazliciteLinije = new ArrayList<>();
	
	public SveRazliciteLinije(){
		sveRazliciteLinije = PolasciManager.sveRazliciteLinije();
	}

	public List<Linija> getSveRazliciteLinije() {
		return sveRazliciteLinije;
	}

	public void setSveRazliciteLinije(List<Linija> sveRazliciteLinije) {
		this.sveRazliciteLinije = sveRazliciteLinije;
	}
	
}
