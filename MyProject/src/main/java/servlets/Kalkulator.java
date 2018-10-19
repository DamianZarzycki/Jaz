package servlets;

import java.io.IOException;
import static java.lang.Math.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/kalkulator")
public class Kalkulator extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	

	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		double wielkoscKred = Double.parseDouble(req.getParameter("wKred"));
		int iloscRat = Integer.parseInt(req.getParameter("iRat"));
		double oProcent = Double.parseDouble(req.getParameter("oProc"));
		double oplataStala = Double.parseDouble(req.getParameter("oStala"));
		String rRaty =req.getParameter("rRaty");
		double ostWkred = wielkoscKred - oplataStala; //ostateczna wielkosc kredytu
		
		java.text.DecimalFormat df=new java.text.DecimalFormat();
		df.setMaximumFractionDigits(2);
		df.setMaximumFractionDigits(2);
		
		if(rRaty.equals("rStala")) {
			
			
		double q = 1 +(oProcent/1200);
		
		double rataStala = ostWkred * pow(q,iloscRat)* (q-1)/(pow(q,iloscRat)-1); 	
		/*for(int i=iloscRat;i>=0;i--)
		{
		double czKap = ostWkred/i;
		}*/
		res.setContentType("text/html");
		
		res.getWriter().println("<div><table border=\"1\"><td> Nr raty </td><td>Wartosc raty</td><td> Czesc kapitalowa  </td><td> Czesc odsetkowa  </td><p>");
		for(int i=0;i<iloscRat;i++) {
			double czKap = ostWkred/iloscRat;
		res.getWriter().println("<tr><td>" + (i+1) + "</td><td>"
				+ df.format(rataStala) +" pln</td><td>"
				+ df.format(czKap) +" pln</td><td>"
				+ df.format(rataStala-czKap) +" pln</td></tr>");	
		}
		res.getWriter().println("</table></div>");
		
		 
		}
		else {
						
			double iloscMsc;
			int ktoraRata=0;
			double rK=ostWkred/iloscRat;
					
			double[] malejacaTab = new double[(int) iloscRat];
			for(iloscMsc=iloscRat;iloscMsc>0;iloscMsc--){
				double rO=(((ostWkred-ktoraRata*rK)*oProcent/100)/12);
				malejacaTab[ktoraRata]=rO+rK;
				ktoraRata++;
			}			
			res.getWriter().println("<div><table border=\"1\"><tr><td>Nr raty </td><td>Rata </td><td>Czesc kapitalowa </td><td>Czesc odsetkowa </td></tr>");
			for(int i=0;i<iloscRat;i++) {
			res.getWriter().println( "<tr><td>" 
					+ (i+1) + ".</td>" 
					+"<td>"	+	df.format(malejacaTab[i]) 
					+" pln</td>" + "<td>"
					+ df.format(rK)+" pln </td>" 
					+ "</td>" 
					+ "<td>"+ df.format(((ostWkred-i*rK)*(oProcent/100/12))) 
					+" pln</tr>");	
				}
			res.getWriter().println("</table></div>");
		
			}
		res.getWriter().println("<form action=\"pdf\" method=\"post\">\r\n" + 
				"<label><p><input type=\"hidden\" id=\"wK\" value='"+wielkoscKred+"' name=\"wKred\" required=\"required\"/></label></p>\r\n" + 
				"		<p><input type=\"hidden\" id=\"iR\" value='"+iloscRat+"' name=\"iRat\" required=\"required\"/></label></p>\r\n" + 
				"		<p><input type=\"hidden\" id=\"oP\" value='"+oProcent+"' name=\"oProc\" step=\"any\" required=\"required\"/></label></p>\r\n" + 
				"		\r\n" + 
				"		<p><input type=\"hidden\" id=\"oS\" value='"+oplataStala+"' name=\"oStala\" required=\"required\"/></label></p>\r\n" + 
				"		<p><input type=\"hidden\" name=\"rRaty\" value='"+rRaty+"' checked></p>\r\n" +	 
				"<p><input type=\"submit\" value=\"Drukuj!\"/></form></p>");
		}
}

