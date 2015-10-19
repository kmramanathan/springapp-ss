package springapp.web.findpeople;

import java.awt.Color;
import java.awt.print.PageFormat;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.searchsystems.limestone.bean.BGCAliasBean;
import net.searchsystems.limestone.bean.BGCOffenderBean;
import net.searchsystems.limestone.bean.BGCOffenseBean;
import net.searchsystems.limestone.bean.BGCOffenseSupplementBean;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import springapp.web.findpeople.BGxmlManager.DEASearch;
import springapp.web.findpeople.BGxmlManager.FAAPilotLicenses;
import springapp.web.findpeople.BGxmlManager.FCCLicenses;
import springapp.web.findpeople.BGxmlManager.FFLFirearmDealerLicenses;
import springapp.web.findpeople.BGxmlManager.OccupantData;
import springapp.web.findpeople.BGxmlManager.USDomainNameOwnership;
import springapp.web.findpeople.BGxmlManager.addressHistory;
import springapp.web.findpeople.BGxmlManager.aircraftSearch;
import springapp.web.findpeople.BGxmlManager.birthRecords;
import springapp.web.findpeople.BGxmlManager.businessSearch;
import springapp.web.findpeople.BGxmlManager.campaignContributors;
import springapp.web.findpeople.BGxmlManager.censusData;
import springapp.web.findpeople.BGxmlManager.concealedWeaponPermits;
import springapp.web.findpeople.BGxmlManager.deathRecords;
import springapp.web.findpeople.BGxmlManager.merchantVessels;
import springapp.web.findpeople.BGxmlManager.otherPeople;
import springapp.web.findpeople.SearchLandingForm.SearchFPFormCommand;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;


@Controller
public class pdfCreator extends AbstractPdfView {
	
	
	protected Logger logger = Logger.getLogger(getClass());
	
	@RequestMapping(value = "/findpeople/getPdf.do", method = RequestMethod.GET)
	 protected void buildPdfDocument(Map model,
             Document doc,
             PdfWriter writer, 
             HttpServletRequest request, 
             HttpServletResponse response)
             throws Exception
             {
			
				try
				{
			        logger.info("Printing Pdf for BG Result in findpeople");	
			        
			        HttpSession session = request.getSession();
			        
			        BGxmlManager bgResult = (BGxmlManager) session.getAttribute("pdfResult");
			        ArrayList<BGCOffenderBean> offenders = (ArrayList)session.getAttribute("BgcOffenders");
			        
			        if(bgResult == null)
			        {
			        	logger.info("Received object is null");
			        	
			        }
			        
			        Document document = new Document();
			        ByteArrayOutputStream baos = new ByteArrayOutputStream();
			        writer=PdfWriter.getInstance(document, baos);
			        document.setPageSize(PageSize.A4.rotate());
			        
			        document.open();
			        
			        Date date = new Date();
			        String[] months = {"January","February","March","April","May",
			        	      "June","July","August","September","October",
			        	      "November","December"};
			        int year = date.getYear()+1900;
			        String today = months[date.getMonth()]+" "+date.getDate()+", "+year;
			        		        
			        //do the process here 
			        //com.lowagie.text.Image image = com.lowagie.text.Image.getInstance("webapps/springapp/images/findpeople/logo_02.jpg");
					//document.add(image);
			        
			        //for header and footer
					//com.lowagie.text.Image imgfoot = com.lowagie.text.Image.getInstance("webapps/springapp/images/findpeople/logo_02.jpg");
					//com.lowagie.text.Image imghead = com.lowagie.text.Image.getInstance("webapps/springapp/images/findpeople/pdf_logo_02.jpg");

					         //  imgfoot.setAbsolutePosition(0, 0);
					        //   imghead.setAbsolutePosition(0, 0);


					        //   PdfContentByte cbhead = writer.getDirectContent();
					        //   PdfTemplate tp = cbhead.createTemplate(300, 175);
					         //  tp.addImage(imghead);


					           PdfContentByte cbfoot = writer.getDirectContent();
					           PdfTemplate tpl = cbfoot.createTemplate(300, 175);
					          // tpl.addImage(imgfoot);


					       //    cbhead.addTemplate(tp, 0, 800);
					          cbfoot.addTemplate(tpl, 0, 0);


				//	Phrase headPhraseImg = new Phrase(cbhead + "This is Header", FontFactory.getFont(FontFactory.TIMES_ROMAN, 7, com.lowagie.text.Font.NORMAL));
					Phrase footPhrase = new Phrase("This Background Report contains information gathered from public records sources. The information reported here is only as accurate as the agencies that contribute it.      Page :", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, com.lowagie.text.Font.NORMAL));

					         //  HeaderFooter header = new HeaderFooter(headPhraseImg, true);
					           HeaderFooter footer = new HeaderFooter(footPhrase, true);
					           footer.setAlignment(Element.ALIGN_CENTER);
					          
                 //   document.setHeader(header);
                    document.setFooter(footer); 
                    
//Start here
 //Background Report Details
			        
			        Phrase heading = new Phrase("Background Report Details", FontFactory.getFont(FontFactory.TIMES_BOLD, 20, com.lowagie.text.Font.BOLD,new Color(0x00, 0x33, 0x66)));
			        document.add(heading);
			        document.add(new Paragraph(new Phrase("As of "+today, FontFactory.getFont(FontFactory.TIMES_BOLD, 12, com.lowagie.text.Font.NORMAL,new Color(0x00, 0x33, 0x66)))));
			        document.add(new Paragraph(" "));
			        if(bgResult.getProRpt() != null)
			        {
			        	document.add(new Paragraph(bgResult.getProRpt().getFirstName()+" "+bgResult.getProRpt().getMiddleName()+" "+bgResult.getProRpt().getLastName()));
                    	document.add(new Paragraph(bgResult.getProRpt().getAddress()));
                    	document.add(new Paragraph(bgResult.getProRpt().getCity()+" "+bgResult.getProRpt().getState()+" "+bgResult.getProRpt().getZip()));
                    	document.add(new Paragraph(bgResult.getProRpt().getPhone()));
                    	document.add(new Paragraph(" "));
                    	document.add(new Paragraph("Validity Date : "+bgResult.getProRpt().getValidityDate()));
                    	document.add(new Paragraph("DOB : "+bgResult.getProRpt().getDOB()));
			        }
                    document.add(new Paragraph(" "));
			        document.add(new Paragraph(" "));
			     
 //Displaying address history
                    
			        PdfPTable table ;
			        PdfPCell cell ;
			        
                    ArrayList history= bgResult.getAddHisList();
                    
                    if(!history.isEmpty())
                    {
                    
                    table=new PdfPTable(5);
                    cell = new PdfPCell (new Phrase("Address History", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, com.lowagie.text.Font.BOLD,new Color(0x00, 0x33, 0x66))));
                    cell.setColspan (5);
                    cell.setHorizontalAlignment (Element.ALIGN_LEFT);
                    cell.setBackgroundColor (new Color (0xCC, 0xCC, 0xCC));
                    cell.setPadding (0);
                    cell.setBorder(0);
                    table.addCell (cell);
                    
                    cell = new PdfPCell();
                    cell.setColspan (5);
                    cell.setBorder(0);
                    table.addCell (cell);
                    cell.setColspan (5);
                    cell.setBorder(0);
                    table.addCell (cell);
                    
                    for(int m=0 ; m<history.size(); m++)
					  {
						  addressHistory adhis=(addressHistory) history.get(m);
						  if(adhis != null)
						  {
                            table.getDefaultCell().setBorder(0);
						  	table.addCell(adhis.getAddress());
						  	table.addCell("County");
						  	table.addCell(adhis.getCounty());
						  	table.addCell("Validity Date");
						  	table.addCell(adhis.getValidityDate());
						  	
						  	table.addCell(adhis.getCity()+ " "+ adhis.getState()+" "+adhis.getZip());
						  	table.addCell("Phone");
						  	table.addCell(adhis.getPhone());
						  	table.addCell(" ");
						  	table.addCell(" ");
						  	
						  	table.addCell("-------------- ");
						  	table.addCell("-------------- ");
						  	table.addCell("-------------- ");
						  	table.addCell("-------------- ");
						  	table.addCell("--------------");
						  }
						  	
                      }
                    document.add(table);
                    document.add(new Paragraph(" "));
				    document.add(new Paragraph(" "));
                    
                    }
                    
 //Displaying address information
                    
                    ArrayList occList= bgResult.getOccupantList();
     
                    if(!occList.isEmpty())
                    {
                    	
                    	table=new PdfPTable(1);
            			cell = new PdfPCell (new Phrase("Address Information", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, com.lowagie.text.Font.BOLD,new Color(0x00, 0x33, 0x66))));
            			cell.setColspan (1);
            			cell.setHorizontalAlignment (Element.ALIGN_LEFT);
            			cell.setBackgroundColor (new Color (0xCC, 0xCC, 0xCC));
            			cell.setPadding (0);
            			cell.setBorder(0);
	        			table.addCell (cell);
	        	
	        			cell = new PdfPCell();
	        			cell.setColspan (1);
	        			cell.setBorder(0);
	        			table.addCell (cell);
	        			cell.setColspan (1);
	        			cell.setBorder(0);
	        			table.addCell (cell);
	        			document.add(table);
	        		   	document.add(new Paragraph(" "));
	        	
                    	for(int m=0 ; m<occList.size() ; m++)
                    	{
                    		table=new PdfPTable(1);
                    		OccupantData occObj = (OccupantData)occList.get(m);
                    		
                    			ArrayList otherList = occObj.getOtherList();
                    			ArrayList censusList = occObj.getCensusList();                    		
                    			table.getDefaultCell().setBorder(0);
                    			table.getDefaultCell().setBackgroundColor(new Color(0xFF, 0xCC, 0x99));
                    			table.addCell(occObj.getAddress());
						   	
                    			table.addCell(occObj.getCity()+" "+occObj.getState()+" "+occObj.getZip());
						  	
                    			String[] phoneArray = occObj.getListedPhones();
                    			String phones="";
                    			if(phoneArray != null)
                    			{
                    				for(int p=0; p<phoneArray.length; p++)
                    				{
                    					if(phoneArray[p]!=null) {
						  	 			phones = phones+phoneArray[p]+",";
                    					}
						  			}
                    			} 
                    			table.addCell(phones);
                    	        document.add(table);
						  	
			//displaying Others List			  	
						  	if(!otherList.isEmpty())
						  	{
						  		table=new PdfPTable(3);
						  		cell = new PdfPCell (new Phrase("Relatives/Roommates", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, com.lowagie.text.Font.BOLD,new Color(0x00, 0x33, 0x66))));
						  		cell.setColspan (3);
					        	cell.setHorizontalAlignment (Element.ALIGN_CENTER);
					        	cell.setBackgroundColor (new Color (0xFF, 0xFF, 0xCC));
					        	cell.setPadding (0);
					        	cell.setBorder(0);
					        	table.addCell(cell);
		                    
					        	cell = new PdfPCell();
					        	cell.setColspan (3);
					        	cell.setBorder(0);
					        	table.addCell (cell);
					        	cell.setColspan (3);
					        	cell.setBorder(0);
					        	table.addCell (cell);
					        
					        	for(int n=0 ; n<otherList.size(); n++)
					        	{
								  otherPeople otherObj=(otherPeople) otherList.get(n);
								  
								  if(otherObj!= null)
								  {
									  table.getDefaultCell().setBorder(0);
								  	
								  	table.addCell(otherObj.getFirstName()+" "+otherObj.getMiddleName()+" "+otherObj.getLastName());
								   	table.addCell("Validity Date:");
								  	table.addCell(otherObj.getDOB());
								  	
								  	table.addCell(otherObj.getPossibleRelative());
								  	table.addCell("DOB:");
								  	table.addCell(otherObj.getDOB());
								  									  	
								  	table.addCell("--------------  ");
								  	table.addCell("--------------  ");
								  	table.addCell("--------------  ");
								  }
								 }
					        				        		
		    					 document.add(table);
						  	}
			//Displaying CensusList			  	
						  	if(! censusList.isEmpty())
						  	{
						  		table=new PdfPTable(2);
						  		cell = new PdfPCell (new Phrase("Census Data", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, com.lowagie.text.Font.BOLD,new Color(0x00, 0x33, 0x66))));
						  		cell.setColspan (2);
					        	cell.setHorizontalAlignment (Element.ALIGN_CENTER);
					        	cell.setBackgroundColor (new Color (0xFF, 0xFF, 0xCC));
					        	cell.setPadding (0);
					        	cell.setBorder(0);
					        	table.addCell(cell);
		                    
					        	cell = new PdfPCell();
					        	cell.setColspan (2);
					        	cell.setBorder(0);
					        	table.addCell (cell);
					        	cell.setColspan (2);
					        	cell.setBorder(0);
					        	table.addCell (cell);
					        	censusData censusObj=(censusData) censusList.get(0);
					        		if(censusObj != null)
					        		{
					        		table.getDefaultCell().setBorder(0);
								  	
								  	table.addCell("Population:");
								  	table.addCell(censusObj.getPopulation());
								  	
								  	table.addCell("Households per zip:");
								  	table.addCell(censusObj.getHouseholds_per_zip());
								  	
								  	table.addCell("White population:");
								  	table.addCell(censusObj.getWhite_population());
								  	
								  	table.addCell("Black population:");
								  	table.addCell(censusObj.getBlack_population());
								  	
								  	table.addCell("Hispanic Population:");
								  	table.addCell(censusObj.getHispanic_population());
								  	
								  	table.addCell("Persons per house:");
								  	table.addCell(censusObj.getPersons_per_household());
								  	
								  	table.addCell("Average house value:");
								  	table.addCell(censusObj.getAvg_house_value());
								  	
								  	table.addCell("Income per household:");
								  	table.addCell(censusObj.getIncome_per_household());
								  	
								  	table.addCell("Lat:");
								  	table.addCell(censusObj.getLatitude());
								  	
								  	table.addCell("Long:");
								  	table.addCell(censusObj.getLongitude());
								  	
								  	table.addCell("Elevation:");
								  	table.addCell(censusObj.getElevation());
								  	
								  	table.addCell("State:");
								  	table.addCell(censusObj.getState());
								  	
								  	table.addCell("City type:");
								  	table.addCell(censusObj.getCity_type());
								  	
								  	table.addCell("Area code:");
								  	table.addCell(censusObj.getArea_code());
								  	
								  	table.addCell("--------------  ");
								  	table.addCell("--------------  ");
					        		}
								 
		    					 document.add(table);
						  	}
						  	 document.add(new Paragraph(" "));
	    				     document.add(new Paragraph(" "));
                    	}

                    }
                    
 // Displaying Statewide criminal record
                    
            					 
					if(!offenders.isEmpty())
					{
					 table=new PdfPTable(4);
                      
	                    cell = new PdfPCell (new Phrase("Statewide Criminal Records", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, com.lowagie.text.Font.BOLD,new Color(0x00, 0x33, 0x66))));
	                    cell.setColspan (4);
	                    cell.setHorizontalAlignment (Element.ALIGN_LEFT);
	                    cell.setBackgroundColor (new Color (0xCC, 0xCC, 0xCC));
	                    cell.setPadding (0);
	                    cell.setBorder(0);
	                    table.addCell (cell);
	                    
	                    cell = new PdfPCell();
	                    cell.setColspan (4);
	                    cell.setBorder(0);
	                    table.addCell (cell);
	                    cell.setColspan (4);
	                    cell.setBorder(0);
	                    table.addCell (cell);
	                    
	                    table.addCell("Detail");
	                    table.addCell("Name");
					   	table.addCell("Date of birth");
					  	table.addCell("Provider");
	                    Integer i = 0;
	                   for(BGCOffenderBean o: offenders)
	                   {        
	                	        i++;
	                	        table.getDefaultCell().setBorder(0);
	                	   		
	                	        table.addCell(i.toString());
	                	        table.addCell(o.getFullName());
							  	table.addCell(o.getDateOfBirth().toString());
							  	table.addCell(o.getProvider());
							  	
							  
						  }
	                    document.add(new Paragraph(" "));
					    document.add(new Paragraph(" "));
						document.add(table);
					}
                    
                    
  //Displaying Vital Records
         /*           ArrayList birthList= bgResult.getBirthList();
                    ArrayList deathList= bgResult.getDeathList();
                    
                    if(!birthList.isEmpty() || !deathList.isEmpty())
                    {
                    	table=new PdfPTable(2);
			           	cell = new PdfPCell (new Phrase("Vital Records", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, com.lowagie.text.Font.BOLD,new Color(0x00, 0x33, 0x66))));
			        	cell.setColspan (2);
			        	cell.setHorizontalAlignment (Element.ALIGN_LEFT);
			        	cell.setBackgroundColor (new Color (0xCC, 0xCC, 0xCC));
			        	cell.setPadding (0);
			        	cell.setBorder(0);
			        	table.addCell (cell);
			        	
			        	cell = new PdfPCell();
			        	cell.setColspan (2);
			        	cell.setBorder(0);
			        	table.addCell (cell);
			        	cell.setColspan (2);
			        	cell.setBorder(0);
			        	table.addCell (cell);
			        	
			        	cell = new PdfPCell();
			        	cell.setColspan (2);
			        	cell.setBorder(0);
			        	table.addCell (cell);
			        	cell.setColspan (2);
			        	cell.setBorder(0);
			        	table.addCell (cell);
                              
      //Displaying Birth Records
                    
			        if(!birthList.isEmpty())
			        {
			      
			        	cell = new PdfPCell (new Phrase("Birth Records", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, com.lowagie.text.Font.BOLD,new Color(0x00, 0x33, 0x66))));
			        	cell.setColspan (2);
			        	cell.setHorizontalAlignment (Element.ALIGN_CENTER);
			        	cell.setBackgroundColor (new Color (0xFF, 0xFF, 0xCC));
			        	cell.setPadding (0);
			        	cell.setBorder(0);
			        	table.addCell(cell);
                    
			        	cell = new PdfPCell();
			        	cell.setColspan (2);
			        	cell.setBorder(0);
			        	table.addCell (cell);
			        	cell.setColspan (2);
			        	cell.setBorder(0);
			        	table.addCell (cell);
			        
			        	for(int m=0 ; m<birthList.size(); m++)
			        	{
						  birthRecords birthObj=(birthRecords) birthList.get(m);
						  if(birthObj != null)
						  	{
						  table.getDefaultCell().setBorder(0);
						  	table.addCell("Name:");
						  	table.addCell(birthObj.getFirstname()+" "+birthObj.getMiddlename()+" "+birthObj.getLastname());
						  	
						  	table.addCell("Date of Birth:");
						  	table.addCell(birthObj.getDOB());
						  	
						  	table.addCell("Gender:");
						  	table.addCell(birthObj.getGender());
						  	
						  	table.addCell("Country:");
						  	table.addCell(birthObj.getBirthCounty());
						  	
						  	table.addCell("State:");
						  	table.addCell(birthObj.getSt());
						  	
						  	table.addCell("--------------  ");
						  	table.addCell("--------------  ");
						  }
			        	} 
					 document.add(new Paragraph(" "));
				     document.add(new Paragraph(" "));
					 document.add(table);
			        }
                    
			        
	  //Displaying Death Records
                     if(!deathList.isEmpty())
			        {
			        	table=new PdfPTable(2);
			                      
			        	cell = new PdfPCell (new Phrase("Death Records", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, com.lowagie.text.Font.BOLD,new Color(0x00, 0x33, 0x66))));
			        	cell.setColspan (2);
			        	cell.setHorizontalAlignment (Element.ALIGN_CENTER);
			        	cell.setBackgroundColor (new Color (0xFF, 0xFF, 0xCC));
			        	cell.setPadding (0);
			        	cell.setBorder(0);
			        	table.addCell (cell);
                    
			        	cell = new PdfPCell();
			        	cell.setColspan (2);
			        	cell.setBorder(0);
			        	table.addCell (cell);
			        	cell.setColspan (2);
			        	cell.setBorder(0);
			        	table.addCell (cell);
			        
			        	for(int m=0 ; m<deathList.size(); m++)
			        	{
						  deathRecords deathObj=(deathRecords) deathList.get(m);
						  if(deathObj != null)
						  {
						  table.getDefaultCell().setBorder(0);
						  	table.addCell("Name:");
						  	table.addCell(deathObj.getFirstname()+" "+deathObj.getMiddlename()+" "+deathObj.getLastname()+" "+deathObj.getSuffix());
						  	
						  	table.addCell("Date of Death:");
						  	table.addCell(deathObj.getDateofdeath());
						  	
						  	table.addCell("SSN:");
						  	table.addCell(deathObj.getSSN());
						  	
						  	table.addCell("Date of Birth:");
						  	table.addCell(deathObj.getDateofbirth());
						  							  	
						  	table.addCell("--- ");
						  	table.addCell("--- ");
						  }
						 }
					 document.add(table);
			        }
                 } */
//Displaying Business Search
                   ArrayList businessList= bgResult.getBusinessList();
			        if(!businessList.isEmpty())
			        {
			        table=new PdfPTable(6);
			                      
                    cell = new PdfPCell (new Phrase("Business Search", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, com.lowagie.text.Font.BOLD,new Color(0x00, 0x33, 0x66))));
                    cell.setColspan (6);
                    cell.setHorizontalAlignment (Element.ALIGN_LEFT);
                    cell.setBackgroundColor (new Color (0xCC, 0xCC, 0xCC));
                    cell.setPadding (0);
                    cell.setBorder(0);
                    table.addCell (cell);
                    
                    cell = new PdfPCell();
                    cell.setColspan (6);
                    cell.setBorder(0);
                    table.addCell (cell);
                    cell.setColspan (6);
                    cell.setBorder(0);
                    table.addCell (cell);
			        
					 for(int m=0 ; m<businessList.size(); m++)
					  {
						  businessSearch businessObj=(businessSearch) businessList.get(m);
						  if(businessObj != null)
						  {
						  table.getDefaultCell().setBorder(0);
						  	table.addCell("Company:");
						  	table.addCell(businessObj.getCompany());
						  	table.addCell("Phone:");
						  	table.addCell(businessObj.getPhone());
						  	table.addCell("Square Footage:");
						  	table.addCell(businessObj.getSquarefootage());
						  	
						  	table.addCell("Address:");
						  	table.addCell(businessObj.getAddress()+""+new Paragraph(businessObj.getCity()+" "+businessObj.getState()+" "+businessObj.getZip()) );
						  	table.addCell("Fax:");
						  	table.addCell(businessObj.getFax());
						  	table.addCell("Credit Score:");
						  	table.addCell(businessObj.getCreditscorelettergrade());
						  	
						  	table.addCell("Title:");
						  	table.addCell(businessObj.getOwnertitle());
						  	table.addCell("Annual Sales::");
						  	table.addCell(businessObj.getAnnualsales());
						  	table.addCell("SIC Code:");
						  	table.addCell(businessObj.getPrimarysic());
						  	
							table.addCell("Owner Name:");
						  	table.addCell(businessObj.getOwnerfirst()+" "+businessObj.getOwnerlast());
						  	table.addCell("# of Employees:");
						  	table.addCell(businessObj.getNumberofemployees());
						  	table.addCell("Description:");
						  	table.addCell(businessObj.getPrimarysicdescription());
						  	
						  	table.addCell("--------------  ");
						  	table.addCell("--------------  ");
						  	table.addCell("--------------  ");
						  	table.addCell("--------------  ");
						  	table.addCell("--------------  ");
						  	table.addCell("--------------  ");
						  
						  }
						  
					  }

					 document.add(new Paragraph(" "));
				     document.add(new Paragraph(" "));
					 document.add(table);
			        }
					 
 //Displaying Campaign Contributions
					 	ArrayList campaignList= bgResult.getCampaignList();
				        if(!campaignList.isEmpty())
				        {
				        table=new PdfPTable(5);
				                      
	                    cell = new PdfPCell (new Phrase("Campaign Contributions", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, com.lowagie.text.Font.BOLD,new Color(0x00, 0x33, 0x66))));
	                    cell.setColspan (5);
	                    cell.setHorizontalAlignment (Element.ALIGN_LEFT);
	                    cell.setBackgroundColor (new Color (0xCC, 0xCC, 0xCC));
	                    cell.setPadding (0);
	                    cell.setBorder(0);
	                    table.addCell (cell);
	                    
	                    cell = new PdfPCell();
	                    cell.setColspan (5);
	                    cell.setBorder(0);
	                    table.addCell (cell);
	                    cell.setColspan (5);
	                    cell.setBorder(0);
	                    table.addCell (cell);
	                    
	                    for(int m=0 ; m<campaignList.size(); m++)
						  {
							  campaignContributors campObj=(campaignContributors) campaignList.get(m);
							  if(campObj != null)
							  {
							  table.getDefaultCell().setBorder(0);
							  	table.addCell(campObj.getFirstname()+" "+campObj.getMiddlename()+" "+campObj.getLastname());
							  	table.addCell("Occupation");
							  	table.addCell(campObj.getOccupation());
							  	table.addCell("Candidate");
							  	table.addCell(campObj.getCandidate());
							  								  	
							  	table.addCell(campObj.getAddress()+""+new Paragraph(campObj.getCity()+" "+campObj.getState()+" "+campObj.getZip()));
							  	table.addCell("Contribution Date");
							  	table.addCell(campObj.getContributionDate());
							  	table.addCell("Term");
							  	table.addCell(campObj.getTerm());;
													  							  	
							  	table.addCell("--------------  ");
							  	table.addCell("--------------  ");
							  	table.addCell("--------------  ");
							  	table.addCell("--------------  ");
							  	table.addCell("--------------  ");
							  }
							  
						  }
	                    document.add(new Paragraph(" "));
					    document.add(new Paragraph(" "));
						document.add(table);
				        }
	                    
 //Displaying U.S. Domain Name Ownership     
						ArrayList USDomainList= bgResult.getUSDomainList();   
	                    
						if(!USDomainList.isEmpty())
						{
						 table=new PdfPTable(4);
	                      
		                    cell = new PdfPCell (new Phrase("U.S. Domain Name Ownership", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, com.lowagie.text.Font.BOLD,new Color(0x00, 0x33, 0x66))));
		                    cell.setColspan (4);
		                    cell.setHorizontalAlignment (Element.ALIGN_LEFT);
		                    cell.setBackgroundColor (new Color (0xCC, 0xCC, 0xCC));
		                    cell.setPadding (0);
		                    cell.setBorder(0);
		                    table.addCell (cell);
		                    
		                    cell = new PdfPCell();
		                    cell.setColspan (4);
		                    cell.setBorder(0);
		                    table.addCell (cell);
		                    cell.setColspan (4);
		                    cell.setBorder(0);
		                    table.addCell (cell);
		                    
		                    for(int m=0 ; m<USDomainList.size(); m++)
							  {
								  USDomainNameOwnership USDomainObj=(USDomainNameOwnership) USDomainList.get(m);
								  if(USDomainObj != null)
								  {
								  table.getDefaultCell().setBorder(0);
								  	table.addCell("Domain Name");
								  	table.addCell(USDomainObj.getDomain_Name());
								  	table.addCell("Phone");
								   	table.addCell(USDomainObj.getPhone());
								  								  	
								  	table.addCell("Owner Name");
								  	table.addCell(USDomainObj.getFirstname()+" "+USDomainObj.getMiddlename()+" "+USDomainObj.getLastname());
								  	table.addCell("Fax");
								  	table.addCell(USDomainObj.getFax());

								  	table.addCell("Address");
								  	table.addCell(USDomainObj.getAddress()+""+new Paragraph(USDomainObj.getCity()+" "+USDomainObj.getState()+" "+USDomainObj.getZip()));
								  	table.addCell("Email");
								  	table.addCell(USDomainObj.getEmailaddress());
														  							  	
								  	table.addCell("--------------  ");
								  	table.addCell("--------------  ");
								  	table.addCell("--------------  ");
								  	table.addCell("--------------  ");
								  }
							  }
		                    document.add(new Paragraph(" "));
						    document.add(new Paragraph(" "));
							document.add(table);
						}
							
//Display FCC Licenses
	                    
							ArrayList fccList= bgResult.getFCCList();
							if(!fccList.isEmpty())
							{
							 		                    
							 table=new PdfPTable(4);
		                      
			                    cell = new PdfPCell (new Phrase("FCC Licenses", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, com.lowagie.text.Font.BOLD,new Color(0x00, 0x33, 0x66))));
			                    cell.setColspan (4);
			                    cell.setHorizontalAlignment (Element.ALIGN_LEFT);
			                    cell.setBackgroundColor (new Color (0xCC, 0xCC, 0xCC));
			                    cell.setPadding (0);
			                    cell.setBorder(0);
			                    table.addCell (cell);
			                    
			                    cell = new PdfPCell();
			                    cell.setColspan (4);
			                    cell.setBorder(0);
			                    table.addCell (cell);
			                    cell.setColspan (4);
			                    cell.setBorder(0);
			                    table.addCell (cell);
			                    
			                    for(int m=0 ; m<fccList.size(); m++)
								  {
									  FCCLicenses fccObj=(FCCLicenses) fccList.get(m); 
									  if(fccObj != null)
									  {
									  table.getDefaultCell().setBorder(0);
									  	table.addCell("Name");
									  	table.addCell(fccObj.getFirstname()+" "+fccObj.getMiddlename()+" "+fccObj.getLastname());
									  	table.addCell("Callsign");
									   	table.addCell(fccObj.getCallsign());
									  								  	
									   	table.addCell("Address");
									  	table.addCell(fccObj.getAddress()+""+new Paragraph(fccObj.getCity()+" "+fccObj.getState()+" "+fccObj.getZip()));
									  	table.addCell("FRN Number:");
									  	table.addCell(fccObj.getFRNnumber());
															  							  	
									  	table.addCell("--------------  ");
									  	table.addCell("--------------  ");
									  	table.addCell("--------------  ");
									  	table.addCell("--------------  ");
									  }
								  }
			                    document.add(new Paragraph(" "));
							    document.add(new Paragraph(" "));
								document.add(table);
							}
								
//Display Firearm Dealer Licenses
								
								ArrayList fflList= bgResult.getFFLList();
								if(!fflList.isEmpty())
								{
	 		                    
								 table=new PdfPTable(4);
			                      
				                    cell = new PdfPCell (new Phrase("Dealer Licenses", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, com.lowagie.text.Font.BOLD,new Color(0x00, 0x33, 0x66))));
				                    cell.setColspan (4);
				                    cell.setHorizontalAlignment (Element.ALIGN_LEFT);
				                    cell.setBackgroundColor (new Color (0xCC, 0xCC, 0xCC));
				                    cell.setPadding (0);
				                    cell.setBorder(0);
				                    table.addCell (cell);
				                    
				                    cell = new PdfPCell();
				                    cell.setColspan (4);
				                    cell.setBorder(0);
				                    table.addCell (cell);
				                    cell.setColspan (4);
				                    cell.setBorder(0);
				                    table.addCell (cell);
				                    
				                    for(int m=0 ; m<fflList.size(); m++)
									  {
										  FFLFirearmDealerLicenses fflObj=(FFLFirearmDealerLicenses) fflList.get(m);
										  if(fflObj != null)
										  {
										  table.getDefaultCell().setBorder(0);
										  	table.addCell("Name");
										  	table.addCell(fflObj.getLicensee_name());
										  	table.addCell("Business Name:");
										   	table.addCell(fflObj.getBusiness_name());
										  								  	
										   	table.addCell("Address");
										  	table.addCell(fflObj.getPremise_street()+""+new Paragraph(fflObj.getPremise_city())+" "+fflObj.getPremise_state()+" "+fflObj.getPremise_zip());
										 	table.addCell("Mailing Address");
										  	table.addCell(fflObj.getMailing_address()+""+new Paragraph(fflObj.getMailing_city())+" "+fflObj.getMailing_state()+" "+fflObj.getMailing_zip());
										  	
										  	table.addCell("Phone");
										  	table.addCell(fflObj.getPremise_phone());
										  	table.addCell("Mailing Phone:");
										   	table.addCell(fflObj.getMailing_phone());
																  							  	
										  	table.addCell("--------------  ");
										  	table.addCell("--------------  ");
										  	table.addCell("--------------  ");
										  	table.addCell("--------------  ");
										  }
									  }
				                    document.add(new Paragraph(" "));
								    document.add(new Paragraph(" "));
									document.add(table);
								}
									
//Display Concealed Weapons Permits"
									
									ArrayList weaponList= bgResult.getWeaponList();
									 if(!weaponList.isEmpty())
									 {
									 table=new PdfPTable(7);
				                      
					                    cell = new PdfPCell (new Phrase("Concealed Weapons Permits", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, com.lowagie.text.Font.BOLD,new Color(0x00, 0x33, 0x66))));
					                    cell.setColspan (7);
					                    cell.setHorizontalAlignment (Element.ALIGN_LEFT);
					                    cell.setBackgroundColor (new Color (0xCC, 0xCC, 0xCC));
					                    cell.setPadding (0);
					                    cell.setBorder(0);
					                    table.addCell (cell);
					                    
					                    cell = new PdfPCell();
					                    cell.setColspan (7);
					                    cell.setBorder(0);
					                    table.addCell (cell);
					                    cell.setColspan (7);
					                    cell.setBorder(0);
					                    table.addCell (cell);
					                    
					                    for(int m=0 ; m<weaponList.size(); m++)
										  {
					                    	concealedWeaponPermits weaponObj=(concealedWeaponPermits) weaponList.get(m);
											  if(weaponObj != null)
											  {
											  table.getDefaultCell().setBorder(0);
											    												  	
											  	table.addCell(weaponObj.getFirstname()+" "+weaponObj.getMiddlename()+" "+weaponObj.getLastname());
											  	table.addCell("Date of Birth:");
											   	table.addCell(weaponObj.getDateofbirth());
											   	table.addCell("License Number:");
											  	table.addCell(weaponObj.getLicenseNumber());
											  	table.addCell("County:");
											   	table.addCell(weaponObj.getCountyName());
											   	
												table.addCell(weaponObj.getAddress());
											  	table.addCell("Race:");
											   	table.addCell(weaponObj.getRace());
											   	table.addCell("Expires:");
											   	table.addCell(weaponObj.getExpirationdate());
											   	table.addCell("State:");
											   	table.addCell(weaponObj.getState1());
											   	
											   	table.addCell(weaponObj.getCity()+" "+weaponObj.getState()+" "+weaponObj.getZip());
											   	table.addCell("Sex:");
											   	table.addCell(weaponObj.getSex());
											   	table.addCell(" ");
											  	table.addCell(" ");
											  	table.addCell(" ");
											  	table.addCell(" ");
																	  							  	
											  	table.addCell("-------------- ");
											  	table.addCell("--------------  ");
											  	table.addCell("--------------  ");
											  	table.addCell("--------------  ");
											  	table.addCell("--------------  ");
											  	table.addCell("--------------  ");
											  	table.addCell("--------------  ");
											  }
										  }
					                    document.add(new Paragraph(" "));
									    document.add(new Paragraph(" "));
										document.add(table);
										
									 }
//Display DEA Search"
										
										ArrayList DeaList= bgResult.getDEAList();
										 
										if(!DeaList.isEmpty())
										{
										 table=new PdfPTable(5);
					                      
						                    cell = new PdfPCell (new Phrase("DEA Search", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, com.lowagie.text.Font.BOLD,new Color(0x00, 0x33, 0x66))));
						                    cell.setColspan (5);
						                    cell.setHorizontalAlignment (Element.ALIGN_LEFT);
						                    cell.setBackgroundColor (new Color (0xCC, 0xCC, 0xCC));
						                    cell.setPadding (0);
						                    cell.setBorder(0);
						                    table.addCell (cell);
						                    
						                    cell = new PdfPCell();
						                    cell.setColspan (5);
						                    cell.setBorder(0);
						                    table.addCell (cell);
						                    cell.setColspan (5);
						                    cell.setBorder(0);
						                    table.addCell (cell);
						                    
						                    for(int m=0 ; m<DeaList.size(); m++)
											  {
												  DEASearch DeaObj=(DEASearch) DeaList.get(m);
												  if(DeaObj != null)
												  {
												  table.getDefaultCell().setBorder(0);
												    
												  	table.addCell("Name:");
												  	table.addCell(DeaObj.getFirstname()+" "+DeaObj.getLastname());
												   	table.addCell("DEA Number:");
												  	table.addCell(DeaObj.getDEANumber());
												  	table.addCell("County: "+DeaObj.getCounty());
												   
												   	
													table.addCell("Address");
												  	table.addCell(DeaObj.getAddress()+""+new Paragraph(DeaObj.getCity()+" "+DeaObj.getState()+" "+DeaObj.getZip()));
												   	table.addCell("Business Type:");
												   	table.addCell(DeaObj.getBusinesstype());
												   	table.addCell(" ");
												   	
												   	table.addCell(" ");
												   	table.addCell(DeaObj.getCity()+" "+DeaObj.getState()+" "+DeaObj.getZip());
												   	table.addCell("Expires:");
												   	table.addCell(DeaObj.getExpirationdate());
												   	table.addCell(" ");
												  
												   	table.addCell("Schedules:");
												   	table.addCell(DeaObj.getSchedules());
												   	table.addCell(" ");
												  	table.addCell(" ");
												  	table.addCell(" ");
																													  							  	
												  	table.addCell("--------------  ");
												  	table.addCell("--------------  ");
												  	table.addCell("--------------  ");
												  	table.addCell("--------------  ");
												  	table.addCell("--------------  ");
												  }
											  }
						                    document.add(new Paragraph(" "));
										    document.add(new Paragraph(" "));
											document.add(table);
										}
											
//Display Merchant Vessels
											
											ArrayList mess= bgResult.getMerVessList();
											 if(!mess.isEmpty())
											 {
											 table=new PdfPTable(6);
						                      
							                    cell = new PdfPCell (new Phrase("Merchant Vessels", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, com.lowagie.text.Font.BOLD,new Color(0x00, 0x33, 0x66))));
							                    cell.setColspan (6);
							                    cell.setHorizontalAlignment (Element.ALIGN_LEFT);
							                    cell.setBackgroundColor (new Color (0xCC, 0xCC, 0xCC));
							                    cell.setPadding (0);
							                    cell.setBorder(0);
							                    table.addCell (cell);
							                    
							                    cell = new PdfPCell();
							                    cell.setColspan (6);
							                    cell.setBorder(0);
							                    table.addCell (cell);
							                    cell.setColspan (6);
							                    cell.setBorder(0);
							                    table.addCell (cell);
							                    
							                    for(int m=0 ; m<mess.size(); m++)
												  {
													  merchantVessels mes=(merchantVessels) mess.get(m);
													  if(mes!=null)
													  {
													  table.getDefaultCell().setBorder(0);
													    
													  	table.addCell("Vessel Name:");
													  	table.addCell(mes.getVesselname());
													  	table.addCell("Callsign:");
													  	table.addCell(mes.getCallsign());
													  	table.addCell("Shipyard:");
													  	table.addCell(mes.getShipyard());
													   
													   	
													  	table.addCell("Hull Number:");
													  	table.addCell(mes.getHullnumber());
													  	table.addCell("IMO Number:");
													  	table.addCell(mes.getImonumber());
													  	table.addCell("Home Port:");
													  	table.addCell(mes.getProvince());
													  	
													  	
														table.addCell("Hull ID:");
													  	table.addCell(mes.getHullidnumber());
													  	table.addCell("Service Type:");
													  	table.addCell(mes.getServicetype());
													  	table.addCell("State of Port:");
													  	table.addCell(mes.getStateofport());
													   
													   	
													  	table.addCell("Company Owner:");
													  	table.addCell(mes.getCompanyowner());
													  	table.addCell("Boat Maker:");
													  	table.addCell(mes.getBoatmaker());
													  	table.addCell("Horsepower:");
													  	table.addCell(mes.getHorsepower());
													  	
														table.addCell("Company Type:");
													  	table.addCell(mes.getCompanytype());
													  	table.addCell("Year Built:");
													  	table.addCell(mes.getYearbuilt());
													  	table.addCell("Gross Tons:");
													  	table.addCell(mes.getGrosstons());
													   
													   	
													  	table.addCell("Owner Name:");
													  	table.addCell(mes.getOwnerfirstname()+" "+mes.getOwnerlastname());
													  	table.addCell("Hull Material:");
													  	table.addCell(mes.getHullmaterial());
													  	table.addCell("Length:");
													  	table.addCell(mes.getLength());
													  	
													  	table.addCell("Address:");
													  	table.addCell(mes.getAddress());
													  	table.addCell("Motor Type:");
													  	table.addCell(mes.getMotortype());
													  	table.addCell("Width:");
													  	table.addCell(mes.getWidth());
													   
													   	
													  	table.addCell(" ");
													  	table.addCell(mes.getCity()+" "+mes.getState()+" "+mes.getZip());
													  	table.addCell("Country Built:");
													  	table.addCell(mes.getCountrybuilt());
													  	table.addCell("Depth:");
													  	table.addCell(mes.getDepth());
																														  							  	
													  	table.addCell("--------------  ");
													  	table.addCell("--------------  ");
													  	table.addCell("--------------  ");
													  	table.addCell("--------------  ");
													  	table.addCell("--------------  ");
													  	table.addCell("--------------  ");
													  }
												  }
							                    document.add(new Paragraph(" "));
											    document.add(new Paragraph(" "));
												document.add(table);
											
											 }
												
//Display Aircraft Search"
												
												ArrayList airList= bgResult.getAirSerList();
												 if(!airList.isEmpty())
												 {
												 table=new PdfPTable(6);
							                      
								                    cell = new PdfPCell (new Phrase("Aircraft Search", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, com.lowagie.text.Font.BOLD,new Color(0x00, 0x33, 0x66))));
								                    cell.setColspan (6);
								                    cell.setHorizontalAlignment (Element.ALIGN_LEFT);
								                    cell.setBackgroundColor (new Color (0xCC, 0xCC, 0xCC));
								                    cell.setPadding (0);
								                    cell.setBorder(0);
								                    table.addCell (cell);
								                    
								                    cell = new PdfPCell();
								                    cell.setColspan (6);
								                    cell.setBorder(0);
								                    table.addCell (cell);
								                    cell.setColspan (6);
								                    cell.setBorder(0);
								                    table.addCell (cell);
								                    
								                    for(int m=0 ; m<airList.size(); m++)
													  {
														  aircraftSearch airObj=(aircraftSearch) airList.get(m);
														  if(airObj != null)
														  {
														  table.getDefaultCell().setBorder(0);
														    
														  	table.addCell("Owner Name:");
														  	table.addCell(airObj.getOwnername());
														  	table.addCell("N Number:");
														  	table.addCell(airObj.getNnumber());
														  	table.addCell("Model:");
														   	table.addCell(airObj.getModel());
														   
														   	
															table.addCell("Address");
														  	table.addCell(airObj.getAddress()+" "+airObj.getAddress2());
														  	table.addCell("Serial Number:");
														  	table.addCell(airObj.getSerialnumber());
														  	table.addCell("Year Built:");
														   	table.addCell(airObj.getYearbuilt());
														   	
														   	table.addCell(" ");
														   	table.addCell(airObj.getCity()+" "+airObj.getState()+" "+airObj.getZip());
														   	table.addCell("Certification Date:");
														   	table.addCell(airObj.getCertdate());
														   	table.addCell(" ");
														   	table.addCell(" ");
														  
														   																															  							  	
														  	table.addCell("--------------  ");
														  	table.addCell("--------------  ");
														  	table.addCell("--------------  ");
														  	table.addCell("--------------  ");
														  	table.addCell("--------------  ");
														  	table.addCell("--------------  ");
														  }
													  }
								                    document.add(new Paragraph(" "));
												    document.add(new Paragraph(" "));
													document.add(table);
												 }
													
//Display FAA Pilot Licenses
													
													ArrayList faaList= bgResult.getFAAList();
													 if(!faaList.isEmpty())
													 {
													 table=new PdfPTable(5);
								                      
									                    cell = new PdfPCell (new Phrase("FAA Pilot Licenses", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, com.lowagie.text.Font.BOLD,new Color(0x00, 0x33, 0x66))));
									                    cell.setColspan (5);
									                    cell.setHorizontalAlignment (Element.ALIGN_LEFT);
									                    cell.setBackgroundColor (new Color (0xCC, 0xCC, 0xCC));
									                    cell.setPadding (0);
									                    cell.setBorder(0);
									                    table.addCell (cell);
									                    
									                    cell = new PdfPCell();
									                    cell.setColspan (5);
									                    cell.setBorder(0);
									                    table.addCell (cell);
									                    cell.setColspan (5);
									                    cell.setBorder(0);
									                    table.addCell (cell);
									                    
									                    for(int m=0 ; m<faaList.size(); m++)
														  {
															  FAAPilotLicenses faaObj=(FAAPilotLicenses) faaList.get(m);
															  if(faaObj != null)
															  {
															  table.getDefaultCell().setBorder(0);
															    
															  	table.addCell(faaObj.getFirstname()+" "+faaObj.getLastname());
															   	table.addCell("FAA Number:");
															  	table.addCell(faaObj.getFAANumber());
															  	table.addCell("Certification Level:");
															   	table.addCell(faaObj.getCertlevel());
															   
															   	
																table.addCell(faaObj.getAddress()+" "+faaObj.getAddress2());
															  	table.addCell("Medical Exp:");
															  	table.addCell(faaObj.getMedicalExpDate());
															  	table.addCell("Ratings:");
															   	table.addCell(faaObj.getRatings());
															   	
															   	table.addCell(faaObj.getCity()+" "+faaObj.getState()+" "+faaObj.getZip());
															   	table.addCell("Certification Type:");
															  	table.addCell(faaObj.getCertificationType());
															  	table.addCell(" ");
															   	table.addCell(" ");
															  
															   																															  							  	
															  	table.addCell("--------------  ");
															  	table.addCell("-------------- ");
															  	table.addCell("--------------  ");
															  	table.addCell("---------------  ");
															  	table.addCell("---------------  ");
															  }
															  	
														  }
									                    document.add(new Paragraph(" "));
													    document.add(new Paragraph(" "));
														document.add(table);					
													 }

	                    
			        document.close();
			        response.setContentType("application/pdf");
			        response.setContentLength(baos.size());
			        response.setHeader("Expires", "0");
			        response.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");
			        response.setHeader("Content-Disposition", "attachement; filename=BGResult.pdf");
			        //response.setHeader("Pragma", "public");
			        ServletOutputStream out = response.getOutputStream();
			        baos.writeTo(out);
			        out.flush();
				}catch(Exception e)
				{
					logger.info("Error while printing pdf for BGC");
					logger.info(e.getMessage()+" Cause :"+e.getCause()+" Class :"+e.getClass());
				}
			 }

	
	@RequestMapping(value = "/findpeople/getBgcPdf.do", method = RequestMethod.POST)
	 protected void buildBgcDocument(Map model,
            Document doc,
            PdfWriter writer, 
            HttpServletRequest request, 
            HttpServletResponse response)
            throws Exception
            {
				logger.info("Printing Pdf for BGC Result in findpeople");
				try
				{
			        logger.info("Enter into printing PDF");	
			        HttpSession session = request.getSession();
			        ArrayList<Integer> offToFetch = (ArrayList) session.getAttribute("offToFetch");
			        
			        if(offToFetch == null)      {
			        	logger.info("Received object is null");
			        	
			        }
			        else{
			        	
			        	  logger.info("Received Arraylist Size :"+offToFetch.size());
				    					        
					        BGxmlManager bgResult = (BGxmlManager) session.getAttribute("pdfResult");
					        ArrayList<BGCOffenderBean> offenders = (ArrayList)session.getAttribute("BgcOffenders");
					        HashMap aliasesMap = (HashMap) session.getAttribute("BgcAliases");
					        HashMap offensesMap = (HashMap) session.getAttribute("BgcOffenses");
					        String searchDob = (String) session.getAttribute("searchDob");
			        	
			        Document document = new Document();
			        ByteArrayOutputStream baos = new ByteArrayOutputStream();
			        writer=PdfWriter.getInstance(document, baos);
			        document.setPageSize(PageSize.A4.rotate());
			        
			        document.open();
			        
			        Date date = new Date();
			        String[] months = {"January","February","March","April","May",
			        	      "June","July","August","September","October",
			        	      "November","December"};
			        int year = date.getYear()+1900;
			        String today = months[date.getMonth()]+" "+date.getDate()+", "+year;
			        		        
			        //do the process here 
			        //com.lowagie.text.Image image = com.lowagie.text.Image.getInstance("webapps/springapp/images/findpeople/logo_02.jpg");
					//document.add(image);
			        
			        //for header and footer
					//com.lowagie.text.Image imgfoot = com.lowagie.text.Image.getInstance("webapps/springapp/images/findpeople/logo_02.jpg");
					//com.lowagie.text.Image imghead = com.lowagie.text.Image.getInstance("webapps/springapp/images/findpeople/pdf_logo_02.jpg");

					         //  imgfoot.setAbsolutePosition(0, 0);
					        //   imghead.setAbsolutePosition(0, 0);


					        //   PdfContentByte cbhead = writer.getDirectContent();
					        //   PdfTemplate tp = cbhead.createTemplate(300, 175);
					         //  tp.addImage(imghead);


					           PdfContentByte cbfoot = writer.getDirectContent();
					           PdfTemplate tpl = cbfoot.createTemplate(300, 175);
					          // tpl.addImage(imgfoot);


					       //    cbhead.addTemplate(tp, 0, 800);
					          cbfoot.addTemplate(tpl, 0, 0);


				//	Phrase headPhraseImg = new Phrase(cbhead + "This is Header", FontFactory.getFont(FontFactory.TIMES_ROMAN, 7, com.lowagie.text.Font.NORMAL));
					Phrase footPhrase = new Phrase("This Background Report contains information gathered from public records sources. The information reported here is only as accurate as the agencies that contribute it.      Page :", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, com.lowagie.text.Font.NORMAL));

					         //  HeaderFooter header = new HeaderFooter(headPhraseImg, true);
					           HeaderFooter footer = new HeaderFooter(footPhrase, true);
					           footer.setAlignment(Element.ALIGN_CENTER);
					          
                //   document.setHeader(header);
                   document.setFooter(footer); 
                   
//Start here
//Background Report Details
			        
			        Phrase heading = new Phrase("Statewide Criminal Records", FontFactory.getFont(FontFactory.TIMES_BOLD, 20, com.lowagie.text.Font.BOLD,new Color(0x00, 0x33, 0x66)));
			        document.add(heading);
			        document.add(new Paragraph(new Phrase("As of "+today, FontFactory.getFont(FontFactory.TIMES_BOLD, 12, com.lowagie.text.Font.NORMAL,new Color(0x00, 0x33, 0x66)))));
			        document.add(new Paragraph(" "));
			        String middle = "";
			        
			        if(bgResult.getProRpt().getDOB().equals("")){ middle = bgResult.getProRpt().getMiddleName(); }
			        
			        if(bgResult.getProRpt() != null)
			        {
			        	document.add(new Paragraph("Name Searched :"+bgResult.getProRpt().getFirstName()+" "+middle+" "+bgResult.getProRpt().getLastName()));
                   	document.add(new Paragraph(searchDob));
                   	document.add(new Paragraph("State :"+bgResult.getProRpt().getState()));
                   	 }
			        document.add(new Paragraph(" "));
                    document.add(new Paragraph("Results include records where no DOB is listed. Those records may or may not pertain to your subject. "));
			        document.add(new Paragraph(" "));
			     
//Displaying address history
                   
			        PdfPTable table ;
			        PdfPCell cell ;
			        
                  
                    
			        if(offToFetch != null && offensesMap != null && offenders != null && aliasesMap != null)
					{
			        	ArrayList<BGCOffenderBean> checkedOff = new ArrayList<BGCOffenderBean>(); 
			        	for(BGCOffenderBean o : offenders)	{
			        		if(offToFetch.contains(o.getBgcOffenderId())){
			        			checkedOff.add(o);
			        		}
			        	}
			        	
						table=new PdfPTable(4);
						table.getDefaultCell().setBorder(0);  
	                    cell = new PdfPCell (new Phrase("Statewide Criminal Records", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, com.lowagie.text.Font.BOLD,new Color(0x00, 0x33, 0x66))));
	                    cell.setColspan (4);
	                    cell.setHorizontalAlignment (Element.ALIGN_LEFT);
	                    cell.setBackgroundColor (new Color (0xCC, 0xCC, 0xCC));
	                    cell.setPadding (0);
	                    cell.setBorder(0);
	                    table.addCell (cell);
	                    
	                    cell = new PdfPCell();
	                    cell.setColspan (4);
	                    cell.setBorder(0);
	                    table.addCell (cell);
	                    cell.setColspan (4);
	                    cell.setBorder(0);
	                    table.addCell (cell);
						for(BGCOffenderBean off : checkedOff)
						{
													
							cell = new PdfPCell (new Phrase("Offender Info", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, com.lowagie.text.Font.BOLD,new Color(0x00, 0x33, 0x66))));
		                    cell.setColspan (4);
		                    cell.setHorizontalAlignment (Element.ALIGN_LEFT);
		                    cell.setBackgroundColor (new Color (0xFF, 0xCC, 0x99));
		                    cell.setPadding (0);
		                    cell.setBorder(0);
		                    table.addCell (cell);
		                    
		                    cell = new PdfPCell();
		                    cell.setColspan (4);
		                    cell.setBorder(0);
		                    table.addCell (cell);
		                    cell.setColspan (4);
		                    cell.setBorder(0);
		                    table.addCell (cell);
		                    
		                    table.addCell("Full Name");
						  	table.addCell("DOB");
						  	table.addCell("ID");
						  	table.addCell("");
						  	
		                    table.addCell(off.getFullName());
		                    if(off.getDateOfBirth() != null){
		                    	table.addCell(off.getDateOfBirth().toString());
		                    }
		                    else{
		                    	table.addCell("");
		                    }
						  	table.addCell(off.getRecordOffenderId());
						  	table.addCell("");
						  	
						  	table.addCell("Address");
						  	table.addCell("City");
						  	table.addCell("State");
						  	table.addCell("Postal Code");
						  	
						  	table.addCell(off.getStreet());
						  	table.addCell(off.getCity());
						  	table.addCell(off.getState());
						  	table.addCell(off.getPostalCode());
						  	
						  	table.addCell("Gender");
						  	table.addCell("Race");
						  	table.addCell("County");
						  	table.addCell("Country");
						  	
						  	table.addCell(off.getGender());
						  	table.addCell(off.getRace());
						  	table.addCell(off.getCounty());
						  	table.addCell(off.getCountry());
						  	
						  	table.addCell("Height");
						  	table.addCell("Weight");
						  	table.addCell("Eye Color");
						  	table.addCell("Hair Color");
						  	
						  	table.addCell(off.getHeightFeet()+"'"+off.getHeightInches()+"''");
						  	table.addCell(off.getWeight());
						  	table.addCell(off.getEyeColor());
						  	table.addCell(off.getHairColor());
						  	
						  	table.addCell("Record Source");
						  	table.addCell("Record State");
						  	table.addCell("");
						  	table.addCell("");
						  	
						  	table.addCell(off.getRecordSource());
						  	table.addCell(off.getRecordState());
						  	table.addCell("");
						  	table.addCell("");
						  	
//printing Aliases						  	
							if(aliasesMap != null)
							{
								BGCAliasBean aliasBean[] = (BGCAliasBean[]) aliasesMap.get(off.getBgcOffenderId());
								if(aliasBean.length != 0)
								{
									cell = new PdfPCell (new Phrase("Aliases", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, com.lowagie.text.Font.BOLD,new Color(0x00, 0x33, 0x66))));
									
				                    cell.setColspan (4);
				                    cell.setHorizontalAlignment (Element.ALIGN_LEFT);
				                    cell.setBackgroundColor (new Color (0xFF, 0xCC, 0x99));
				                    cell.setPadding (0);
				                    cell.setBorder(0);
				                    table.addCell (cell);
				                    
				                    cell = new PdfPCell();
				                    cell.setColspan (4);
				                    cell.setBorder(0);
				                    table.addCell (cell);
				                    cell.setColspan (4);
				                    cell.setBorder(0);
				                    table.addCell (cell);
				                    table.addCell("First Name");
								  	table.addCell("Last Name");
								  	table.addCell("Middle Name");
								  	table.addCell("Suffix");
								  	
								for (BGCAliasBean alias : aliasBean) 
	                			{ 
								  	table.addCell(alias.getFirstName());
								  	table.addCell(alias.getLastName());
								  	table.addCell(alias.getMiddleName());
								  	table.addCell(alias.getSuffix());
								  	
	                			}
								
							}
							}else
							{
								cell = new PdfPCell (new Phrase("Aliases", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, com.lowagie.text.Font.BOLD,new Color(0x00, 0x33, 0x66))));
								
			                    cell.setColspan (4);
			                    cell.setHorizontalAlignment (Element.ALIGN_LEFT);
			                    cell.setBackgroundColor (new Color (0xFF, 0xCC, 0x99));
			                    cell.setPadding (0);
			                    cell.setBorder(0);
			                    table.addCell (cell);
			                    
			                    cell = new PdfPCell(new Phrase("No Aliases Found", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, com.lowagie.text.Font.BOLD,new Color(0xFF, 0xCC, 0x99))));
			                    cell.setColspan (4);
			                    cell.setBorder(0);
			                    table.addCell (cell);
			                    cell.setColspan (4);
			                    cell.setBorder(0);
			                    table.addCell (cell);
			                    
			                    table.addCell(" ");
							  	table.addCell(" ");
							  	table.addCell(" ");
							  	table.addCell(" ");
							}
							
							if(offensesMap != null)
							{
								BGCOffenseBean offenses[] = (BGCOffenseBean[]) offensesMap.get(off.getBgcOffenderId());
								cell = new PdfPCell (new Phrase("Offenses", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, com.lowagie.text.Font.BOLD,new Color(0x00, 0x33, 0x66))));
			                    cell.setColspan (4);
			                    cell.setHorizontalAlignment (Element.ALIGN_LEFT);
			                    cell.setBackgroundColor (new Color (0xFF, 0xCC, 0x99));
			                    cell.setPadding (0);
			                    cell.setBorder(0);
			                    table.addCell (cell);
			                    
			                    cell = new PdfPCell();
			                    cell.setColspan (4);
			                    cell.setBorder(0);
			                    table.addCell (cell);
			                    cell.setColspan (4);
			                    cell.setBorder(0);
			                    table.addCell (cell);
			                    int i = 0;
								for (BGCOffenseBean offense : offenses) 
	                			{ 
									i++;
									cell = new PdfPCell (new Phrase("Result#"+i, FontFactory.getFont(FontFactory.TIMES_BOLD, 15, com.lowagie.text.Font.BOLD)));
				                    cell.setColspan (4);
				                    cell.setHorizontalAlignment (Element.ALIGN_LEFT);
				                    cell.setBackgroundColor (new Color (0xFF, 0xCC, 0x99));
				                    cell.setPadding (0);
				                    cell.setBorder(0);
				                    table.addCell (cell);
				                    
				                    table.addCell("Description");
								  	table.addCell(offense.getDescription());
								  	table.addCell("");
								  	table.addCell("");
								 
								  	table.addCell("Degree of Offense");
								  	table.addCell(offense.getDegreeOfOffense());
								  	table.addCell("");
								  	table.addCell("");
								  	
								  	table.addCell("Arresting Agency");
								  	table.addCell(offense.getArrestingAgency());
								  	table.addCell("");
								  	table.addCell("");
								  	
								  	table.addCell("Disposition");
								  	table.addCell(offense.getDisposition());
								  	table.addCell("");
								  	table.addCell("");
								  	
								  	table.addCell("Disposition Date");
								  	table.addCell(offense.getDispositionDate());
								  	table.addCell("");
								  	table.addCell("");
								  	
								  	table.addCell("Violation Date");
								  	table.addCell("");
								  	table.addCell("");
								  	table.addCell("");
								  	
								  	table.addCell("General Amount");
								  	table.addCell("");
								  	table.addCell("");
								  	table.addCell("");
								  	
								  	table.addCell("Violation Case");
								  	table.addCell("");
								  	table.addCell("");
								  	table.addCell("");
								  	
								  	table.addCell("Victim of Crime");
								  	table.addCell("");
								  	table.addCell("");
								  	table.addCell("");
								  	
								  	table.addCell("Points");
								  	table.addCell("");
								  	table.addCell("");
								  	table.addCell("");
								  	
								  	table.addCell("Total Amount");
								  	table.addCell("");
								  	table.addCell("");
								  	table.addCell("");
								  	
								  	table.addCell("Status");
								  	table.addCell("");
								  	table.addCell("");
								  	table.addCell("");
								  	
								  	table.addCell("Original Plea");
								  	table.addCell(offense.getPlea());
								  	table.addCell("");
								  	table.addCell("");
								  	
								  	table.addCell("Next Date");
								  	table.addCell("");
								  	table.addCell("");
								  	table.addCell("");
								  	
								  	table.addCell("Final Due");
								  	table.addCell("");
								  	table.addCell("");
								  	table.addCell("");
								  	
								  	table.addCell("Section Code");
								  	table.addCell("");
								  	table.addCell("");
								  	table.addCell("");
								  	
								  	
								  	table.addCell("Case Number");
								  	table.addCell("");
								  	table.addCell("");
								  	table.addCell("");
								  	
								  	table.addCell(" ");
								  	table.addCell(" ");
								  	table.addCell(" ");
								  	table.addCell(" ");
								  
	                			}
								
							}
							
							table.addCell("-----------------");
							table.addCell("-----------------");
							table.addCell("-----------------");
							table.addCell("-----------------");
							
						  	
						  	table.addCell(" ");
						  	table.addCell(" ");
						  	table.addCell(" ");
						  	table.addCell(" ");
						}
						document.add(new Paragraph(" "));
					    document.add(new Paragraph(" "));
						document.add(table);	
						
					}
                   	document.close();
			        response.setContentType("application/pdf");
			        response.setContentLength(baos.size());
			        response.setHeader("Expires", "0");
			        response.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");
			        response.setHeader("Content-Disposition", "attachement; filename=BGCResult.pdf");
			        //response.setHeader("Pragma", "public");
			        ServletOutputStream out = response.getOutputStream();
			        baos.writeTo(out);
			        out.flush();
			        }
				}catch(Exception e)
				{
					logger.info("Error while printing pdf for BGC");
					logger.info(e.getMessage()+" Cause :"+e.getCause()+" Class :"+e.getClass());
				}
			 }
            
}
