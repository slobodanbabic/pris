package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.RezervacijaKarteManager;
import model.Putnik;

/**
 * Servlet implementation class RezervisiKartuServlet
 */
public class RezervisiKartuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RezervisiKartuServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idpolaska = Integer.parseInt(request.getParameter("idPolaska"));		
		int brkarata = Integer.parseInt(request.getParameter("brKarata"));		
		Putnik putnik = (Putnik)request.getSession().getAttribute("korisnik");
		int cenaKarte = Integer.parseInt(request.getParameter("cenaKarte"));
		RezervacijaKarteManager rk = new RezervacijaKarteManager();
		String poruka=null;
		String popust=null;
		if(rk.rezervisiKartu(idpolaska, brkarata, putnik)){
			if(putnik.getBrrezervacija() % 3 == 0){
				int cena=cenaKarte*brkarata;
				popust="Dobili ste popust 10%, puna cena: "+cena+" umanjena cena je:"+(cena - cena*0.1);
			}
			poruka="uspestno ste rezervisali karte.";
		}else{
			poruka="Doslo je do greske, niste rezervisali kartu.";
		}
		request.setAttribute("poruka", poruka);
		request.setAttribute("popust", popust);
		request.getRequestDispatcher("RezervacijaMesta.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
