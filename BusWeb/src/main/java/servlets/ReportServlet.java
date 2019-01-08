package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customBeans.OcenePrevoznika;
import managers.KomentarManager;
import managers.PrevoznikManager;
import managers.RezervacijaKarteManager;
import model.Prevoznik;
import model.Radnik;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Servlet implementation class ReportServlet
 */
public class ReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			HashMap<String, Object> parametri = new HashMap<String, Object>();
			JREmptyDataSource emptyDateSource = new JREmptyDataSource();			
			JRDataSource dataSource = null;
			ServletContext context = this.getServletContext();
			String repotsDirectory = context.getRealPath("/")+"WEB-INF/classes/jasper/";
			System.out.println("repotsDirectory jsp:" +repotsDirectory);
			String jasperFile = null;
			JasperPrint jasperPrint = null;
			
			String dnevniProfit = request.getParameter("dnevniProfit");
			String ocenePrevoznika = request.getParameter("ocenePrevoznika");
			
			if(dnevniProfit != null){
				Date datum = new Date();
				Radnik radnik = (Radnik)request.getSession().getAttribute("radnik");				
				double profit = RezervacijaKarteManager.getDnevniProfit(datum, radnik);				
				parametri.put("datum", datum);
				parametri.put("ime", radnik.getIme());
				parametri.put("prezime", radnik.getPrezime());
				parametri.put("iznos", profit);
				jasperFile = repotsDirectory+"prikazDnevnogProfitaRadnika.jasper";
				if(profit == -1){
					jasperPrint = JasperFillManager.fillReport(jasperFile, parametri, emptyDateSource);
				}else{
					//dataSource = new JRBeanCollectionDataSource(profitlista);
					jasperPrint = JasperFillManager.fillReport(jasperFile, parametri,emptyDateSource);
				}
				ServletOutputStream servletOutputStream = response.getOutputStream();
				response.setContentType("application/pdf");
				JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
				servletOutputStream.flush();
				servletOutputStream.close();
			}
			if(ocenePrevoznika != null){
				Date datum = new Date();
						
				parametri.put("datum", datum);
				
				jasperFile = repotsDirectory+"najboljeOcenjeniPrevoznici.jasper";
				Map<Prevoznik, Double> oceneMapa = PrevoznikManager.najboljeOcenjeniPrevoznici();
				List<OcenePrevoznika> ocene = new ArrayList<OcenePrevoznika>();
				for(Map.Entry<Prevoznik, Double> entry : oceneMapa.entrySet()){
					String naziv = entry.getKey().getNaziv();
					double ocena = entry.getValue();
					OcenePrevoznika op = new OcenePrevoznika(naziv, ocena);
					ocene.add(op);
				}
				dataSource = new JRBeanCollectionDataSource(ocene);
				jasperPrint = JasperFillManager.fillReport(jasperFile, parametri,dataSource);
				
				ServletOutputStream servletOutputStream = response.getOutputStream();
				response.setContentType("application/pdf");
				JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
				servletOutputStream.flush();
				servletOutputStream.close();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
