package servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.PolasciManager;
import model.Polazak;

/**
 * Servlet implementation class PretragaServlet
 */
public class PretragaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public PretragaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {			
			try{				
				int idGrad = Integer.parseInt(request.getParameter("destinacija"));
				String datumStr = request.getParameter("datumPolaska");
				Date datumP = null;
				String porukaPretraga = null;				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				datumP = sdf.parse(datumStr);
				List<Polazak> polasci = new PolasciManager().vratiPolazakeZaDatumIDestinaciju(idGrad, datumP);
				if(polasci.size() == 0){
					porukaPretraga = "Nema polazaka za zadate parametre!";
					request.setAttribute("porukaPretraga", porukaPretraga);
					request.getRequestDispatcher("PretragaPolazaka.jsp").forward(request, response);
				}else{					
					request.setAttribute("polasci", polasci);	
					request.setAttribute("datum", datumStr);
					request.getRequestDispatcher("PretragaPolazaka.jsp").forward(request, response);
				}		
			}catch(Exception e){
				e.printStackTrace();
			}	
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
