package servlets;

import static java.lang.Math.pow;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import java.io.IOException;

import java.util.ArrayList;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;

import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


@WebServlet("/pdf")
public class DownloadServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setStatus(418);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException {
		
			res.getWriter().println("Zaraz bedzie tu pdf");
			double wielkoscKred = Double.parseDouble(req.getParameter("wKred"));
			double iloscRat = Double.parseDouble(req.getParameter("iRat"));
			double oProcent = Double.parseDouble(req.getParameter("oProc"));
			double oplataStala = Double.parseDouble(req.getParameter("oStala"));
			String rRaty =req.getParameter("rRaty");
			double ostWkred = wielkoscKred - oplataStala; //ostateczna wielkosc kredytu
			
			java.text.DecimalFormat df=new java.text.DecimalFormat();
			df.setMaximumFractionDigits(2);
			
			ByteArrayOutputStream pdfTemp = new ByteArrayOutputStream();
			
			Document pdf = new Document();
			
			try {
				PdfWriter pdfWriter = PdfWriter.getInstance(pdf, pdfTemp);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			
			pdf.open();
	
			PdfPTable pdfTable = new PdfPTable(4);
			try {
				pdf.add(new Paragraph("Harmonogram rat"));
				
				pdf.add(new Chunk("\n"));
			} catch (DocumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			ArrayList<String> kolumny = new ArrayList<String>();
			kolumny.add("Nr Raty");
			kolumny.add("Rata");
			kolumny.add("Czesc kapitalowa");
			kolumny.add("Czesc odsetkowa");
			 
			for (String nazwa : kolumny) {
			
				PdfPCell pdfPcell = new PdfPCell();
				pdfPcell.setPhrase(new Phrase(nazwa));
				pdfTable.addCell(pdfPcell);
			
			}

			if(rRaty.equals("rStala")) {
				double q = 1 +(oProcent/1200);
				double rataStala = ostWkred * pow(q,iloscRat)* (q-1)/(pow(q,iloscRat)-1); 		
				 	
					
					for (int i = 0; i < iloscRat; i++) {
						double czKap = ostWkred/iloscRat;
						pdfTable.addCell(String.valueOf(i+1));
						pdfTable.addCell(String.valueOf(df.format(rataStala)));
						pdfTable.addCell(String.valueOf(df.format(czKap)));
						pdfTable.addCell(String.valueOf(df.format(rataStala-czKap)));
			
				}
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
						
						pdfTable.addCell(String.valueOf(ktoraRata));
						pdfTable.addCell(String.valueOf(df.format(rO+rK)));
						pdfTable.addCell(String.valueOf(df.format(rK)));
						pdfTable.addCell(String.valueOf(df.format(rO)));
					}															
				}		

			try {
				pdf.add(pdfTable);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			
			
			//zamykam pdf'a i tworze procedura pobierania z animacja postepu
			pdf.close();
			res.reset();
			res.setContentType("application/pdf");
			res.addHeader("Content-Disposition", "attachement; filename=harmonogramRat.pdf");
			res.setContentLength((int)pdfTemp.size());
			byte[] bytePdf = pdfTemp.toByteArray();
			ByteArrayInputStream pdfOut = new ByteArrayInputStream(bytePdf);
			int bytes;
			while ((bytes = pdfOut.read()) != -1) {
				res.getOutputStream().write(bytes);
			}
	}
		
	}





