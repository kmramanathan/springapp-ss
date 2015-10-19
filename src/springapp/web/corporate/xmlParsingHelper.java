package springapp.web.corporate;

import java.util.ArrayList;

import springapp.web.corporate.xmlParsingHelper.officerObj.offAddressObj;
import springapp.web.corporate.xmlParsingHelper.officerObj.offNameObj;

/**
 * @author Ram Kumarappan
 *
 */

/* 
 * These class contains getter,setter and constructor for
 * all nodes and leaf node of XML received for corporate search
 */
 

public class xmlParsingHelper
{
	private String bankruptDate;
	private String capital;
	
	private String corpDescription;
	private String corpFileKey;
	private String corpStatus;
	private String corpStatusDate;
	private String corpType;
	
	private String DBAName;
	private String email1;
	private String expireDate;
	private String ext;
	private String fax;
	private String fileDataDate;
	private String filingDate;
	private String filingType;
	private String homeState;
	private String incorporationState;
	private String jurisdiction;
	private String lastReportDate;
	private String name;
	private String nonProfitType;
	private String phone;
	private String purpose;
	private String registryNumber;
	private String SIC;
	private String statute;
	private String taxID;
	private String term;
	
	ArrayList historyList=new ArrayList();
	ArrayList officerList=new ArrayList();
	ArrayList mergerList=new ArrayList();
	
	

	private addressObj aObj;
	private stockObj sObj;
	private mailingObj mObj;
	private prinBusObj prObj;
	private historyObj hObj;
	private officerObj ofObj;
	private mergerObj merObj;
	
	
	
	
	 		
	public void setMerObj(mergerObj merObj)
	{
		this.merObj = merObj;
	}
	public mergerObj getMerObj()
	{
		return merObj;
	}
	public officerObj getOfObj()
	{
		return ofObj;
	}
	public historyObj getHObj()
	{
		return hObj;
	}
	public void setHObj(historyObj obj)
	{
		hObj = obj;
	}
	public ArrayList getHistoryList()
	{
		return historyList;
	}
	
	public ArrayList getMergerList() {
		return mergerList;
	}

	public ArrayList getOfficerList()
	{
		return officerList;
	}
	public mailingObj getMObj()
	{
		return mObj;
	}
	public void setMObj(mailingObj obj)
	{
		mObj = obj;
	}
	public prinBusObj getPrObj()
	{
		return prObj;
	}
	public void setPrObj(prinBusObj prObj)
	{
		this.prObj = prObj;
	}
	public addressObj getAObj()
	{
		return aObj;
	}
	public void setAObj(addressObj obj)
	{
		aObj = obj;
	}
	public stockObj getSObj()
	{
		return sObj;
	}
	public void setSObj(stockObj obj)
	{
		sObj = obj;
	}
	public String getBankruptDate()
	{
		return bankruptDate;
	}
	public void setBankruptDate(String bankruptDate)
	{
		this.bankruptDate = bankruptDate;
	}
	public String getCapital()
	{
		return capital;
	}
	public void setCapital(String capital)
	{
		this.capital = capital;
	}
	public String getCorpDescription()
	{
		return corpDescription;
	}
	public void setCorpDescription(String corpDescription)
	{
		this.corpDescription = corpDescription;
	}
	public String getCorpFileKey()
	{
		return corpFileKey;
	}
	public void setCorpFileKey(String corpFileKey)
	{
		this.corpFileKey = corpFileKey;
	}
	public String getCorpStatus()
	{
		return corpStatus;
	}
	public void setCorpStatus(String corpStatus)
	{
		this.corpStatus = corpStatus;
	}
	public String getCorpStatusDate()
	{
		return corpStatusDate;
	}
	public void setCorpStatusDate(String corpStatusDate)
	{
		this.corpStatusDate = corpStatusDate;
	}
	public String getCorpType()
	{
		return corpType;
	}
	public void setCorpType(String corpType)
	{
		this.corpType = corpType;
	}
	public String getDBAName()
	{
		return DBAName;
	}
	public void setDBAName(String name)
	{
		DBAName = name;
	}
	public String getEmail1()
	{
		return email1;
	}
	public void setEmail1(String email1)
	{
		this.email1 = email1;
	}
	public String getExpireDate()
	{
		return expireDate;
	}
	public void setExpireDate(String expireDate)
	{
		this.expireDate = expireDate;
	}
	public String getExt()
	{
		return ext;
	}
	public void setExt(String ext)
	{
		this.ext = ext;
	}
	public String getFax()
	{
		return fax;
	}
	public void setFax(String fax)
	{
		this.fax = fax;
	}
	public String getFileDataDate()
	{
		return fileDataDate;
	}
	public void setFileDataDate(String fileDataDate)
	{
		this.fileDataDate = fileDataDate;
	}
	public String getFilingDate()
	{
		return filingDate;
	}
	public void setFilingDate(String filingDate)
	{
		this.filingDate = filingDate;
	}
	public String getFilingType()
	{
		return filingType;
	}
	public void setFilingType(String filingType)
	{
		this.filingType = filingType;
	}
	public String getHomeState()
	{
		return homeState;
	}
	public void setHomeState(String homeState)
	{
		this.homeState = homeState;
	}
	public String getIncorporationState()
	{
		return incorporationState;
	}
	public void setIncorporationState(String incorporationState)
	{
		this.incorporationState = incorporationState;
	}
	public String getJurisdiction()
	{
		return jurisdiction;
	}
	public void setJurisdiction(String jurisdiction)
	{
		this.jurisdiction = jurisdiction;
	}
	public String getLastReportDate()
	{
		return lastReportDate;
	}
	public void setLastReportDate(String lastReportDate)
	{
		this.lastReportDate = lastReportDate;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getNonProfitType()
	{
		return nonProfitType;
	}
	public void setNonProfitType(String nonProfitType)
	{
		this.nonProfitType = nonProfitType;
	}
	public String getPhone()
	{
		return phone;
	}
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	public String getPurpose()
	{
		return purpose;
	}
	public void setPurpose(String purpose)
	{
		this.purpose = purpose;
	}
	public String getRegistryNumber()
	{
		return registryNumber;
	}
	public void setRegistryNumber(String registryNumber)
	{
		this.registryNumber = registryNumber;
	}
	public String getSIC()
	{
		return SIC;
	}
	public void setSIC(String sic)
	{
		SIC = sic;
	}
	public String getStatute()
	{
		return statute;
	}
	public void setStatute(String statute)
	{
		this.statute = statute;
	}
	public String getTaxID()
	{
		return taxID;
	}
	public void setTaxID(String taxID)
	{
		this.taxID = taxID;
	}
	public String getTerm()
	{
		return term;
	}
	public void setTerm(String term)
	{
		this.term = term;
	}
	
	
	public static class addressObj{
		private String address1;
		private String address2;
		private String city;
		private String country;
		private String county;
		private String state;
		private String stateCode;
		private String zip;
		private String zip4;
		public addressObj(String address1, String address2, String city,
				String country, String county, String state, String stateCode,
				String zip, String zip4)
			{
				this.address1 = address1;
				this.address2 = address2;
				this.city = city;
				this.country = country;
				this.county = county;
				this.state = state;
				this.stateCode = stateCode;
				this.zip = zip;
				this.zip4 = zip4;
			}
		public String getAddress1()
		{
			return address1;
		}
		public String getAddress2()
		{
			return address2;
		}
		public String getCity()
		{
			return city;
		}
		public String getCountry()
		{
			return country;
		}
		public String getCounty()
		{
			return county;
		}
		public String getState()
		{
			return state;
		}
		public String getStateCode()
		{
			return stateCode;
		}
		public String getZip()
		{
			return zip;
		}
		public String getZip4()
		{
			return zip4;
		}
	
	}
	
	
	public static class stockObj{
		
		private String corpFileKey;
		private String stateCode;
		private String stockClass;
		private String stockDate;
		private String stockParValue;
		private String stockRestrictInd;
		private String stockShareAuth;
		
		
		public stockObj(String corpFileKey, String stateCode,
				String stockClass, String stockDate, String stockParValue,
				String stockRestrictInd, String stockShareAuth)
			{
				this.corpFileKey = corpFileKey;
				this.stateCode = stateCode;
				this.stockClass = stockClass;
				this.stockDate = stockDate;
				this.stockParValue = stockParValue;
				this.stockRestrictInd = stockRestrictInd;
				this.stockShareAuth = stockShareAuth;
			}


		public String getCorpFileKey()
		{
			return corpFileKey;
		}


		public String getStateCode()
		{
			return stateCode;
		}


		public String getStockClass()
		{
			return stockClass;
		}


		public String getStockDate()
		{
			return stockDate;
		}


		public String getStockParValue()
		{
			return stockParValue;
		}


		public String getStockRestrictInd()
		{
			return stockRestrictInd;
		}


		public String getStockShareAuth()
		{
			return stockShareAuth;
		}
		
	}
	
	
	public static class mergerObj
	{
		private String corpFileKey;
		private String eventDescription;
		private String mergeDate;
		private String mergedCorpID;
		private String mergedCorpName;
		private String stateCode;
		private String survivingCorpID;
		
		public mergerObj(String corpFileKey, String eventDescription,
				String mergeDate, String mergedCorpID, String mergedCorpName,
				String stateCode, String survivingCorpID) {
			super();
			this.corpFileKey = corpFileKey;
			this.eventDescription = eventDescription;
			this.mergeDate = mergeDate;
			this.mergedCorpID = mergedCorpID;
			this.mergedCorpName = mergedCorpName;
			this.stateCode = stateCode;
			this.survivingCorpID = survivingCorpID;
		}

		public String getCorpFileKey() {
			return corpFileKey;
		}

		public String getEventDescription() {
			return eventDescription;
		}

		public String getMergeDate() {
			return mergeDate;
		}

		public String getMergedCorpID() {
			return mergedCorpID;
		}

		public String getMergedCorpName() {
			return mergedCorpName;
		}

		public String getStateCode() {
			return stateCode;
		}

		public String getSurvivingCorpID() {
			return survivingCorpID;
		}
			
			

	}
	
	
	public static class mailingObj{
		
		private String address1;
		private String address2;
		private String city;
		private String state;
		private String zip;
		private String zip4;
		public mailingObj(String address1, String address2, String city,
				String state, String zip, String zip4)
			{
				this.address1 = address1;
				this.address2 = address2;
				this.city = city;
				this.state = state;
				this.zip = zip;
				this.zip4 = zip4;
			}
		public String getAddress1()
		{
			return address1;
		}
		public String getAddress2()
		{
			return address2;
		}
		public String getCity()
		{
			return city;
		}
		public String getState()
		{
			return state;
		}
		public String getZip()
		{
			return zip;
		}
		public String getZip4()
		{
			return zip4;
		}
	
	}
	
	public static class prinBusObj{
		
		private String address1;
		private String address2;
		private String city;
		private String country;
		private String county;
		private String state;
		private String zip;
		private String zip4;
		public prinBusObj(String address1, String address2, String city,
				String country, String county, String state, String zip,
				String zip4)
			{
				this.address1 = address1;
				this.address2 = address2;
				this.city = city;
				this.country = country;
				this.county = county;
				this.state = state;
				this.zip = zip;
				this.zip4 = zip4;
			}
		public String getAddress1()
		{
			return address1;
		}
		public String getAddress2()
		{
			return address2;
		}
		public String getCity()
		{
			return city;
		}
		public String getCountry()
		{
			return country;
		}
		public String getCounty()
		{
			return county;
		}
		public String getState()
		{
			return state;
		}
		public String getZip()
		{
			return zip;
		}
		public String getZip4()
		{
			return zip4;
		}
	
	}
	
	public static class historyObj
	{
		private String amendmentDate;
		private String amendmentType;
		private String corpFileKey;
		private String corporationName;
		private String event;
		private String stateCode;
		public historyObj(String amendmentDate, String amendmentType,
				String corpFileKey, String corporationName, String event,
				String stateCode)
			{
				this.amendmentDate = amendmentDate;
				this.amendmentType = amendmentType;
				this.corpFileKey = corpFileKey;
				this.corporationName = corporationName;
				this.event = event;
				this.stateCode = stateCode;
			}
		public String getAmendmentDate()
		{
			return amendmentDate;
		}
		public String getAmendmentType()
		{
			return amendmentType;
		}
		public String getCorpFileKey()
		{
			return corpFileKey;
		}
		public String getCorporationName()
		{
			return corporationName;
		}
		public String getEvent()
		{
			return event;
		}
		public String getStateCode()
		{
			return stateCode;
		}

	}
	
	public static class officerObj{
		
		private String corpFileKey;
		private String fax;
		private String fein;
		private String startDate;
		private String status;
		
		private offAddressObj oaObj;
		private offNameObj onObj;
		
		
		public officerObj(String corpFileKey, String fax, String fein,
				String startDate, String status, offAddressObj oaObj,
				offNameObj onObj)
			{
				
				this.corpFileKey = corpFileKey;
				this.fax = fax;
				this.fein = fein;
				this.startDate = startDate;
				this.status = status;
				this.oaObj = oaObj;
				this.onObj = onObj;
			}
		
		public String getCorpFileKey()
		{
			return corpFileKey;
		}

		public String getFax()
		{
			return fax;
		}

		public String getFein()
		{
			return fein;
		}

		public String getStartDate()
		{
			return startDate;
		}

		public String getStatus()
		{
			return status;
		}

		public offAddressObj getOaObj()
		{
			return oaObj;
		}

		public offNameObj getOnObj()
		{
			return onObj;
		}





		public static class offAddressObj
		{
			private String address1;
			private String address2;
			private String city;
			private String state;
			private String zip;
			private String zip4;
			
			public offAddressObj(String address1, String address2, String city,
					String state, String zip, String zip4)
				{
					this.address1 = address1;
					this.address2 = address2;
					this.city = city;
					this.state = state;
					this.zip = zip;
					this.zip4 = zip4;
				}

			public String getAddress1()
			{
				return address1;
			}

			public String getAddress2()
			{
				return address2;
			}

			public String getCity()
			{
				return city;
			}

			public String getState()
			{
				return state;
			}

			public String getZip()
			{
				return zip;
			}

			public String getZip4()
			{
				return zip4;
			}
			
					
		}
	
		public static class offNameObj
		{
			
			private String first;
			private String firstRest;
			private String last;
			private String lastRest;
			private String middle;
			private String suffix;
			private String title;
			private String unparsedName;
			
			public offNameObj(String first, String firstRest, String last,
					String lastRest, String middle, String suffix,
					String title, String unparsedName)
				{
					this.first = first;
					this.firstRest = firstRest;
					this.last = last;
					this.lastRest = lastRest;
					this.middle = middle;
					this.suffix = suffix;
					this.title = title;
					this.unparsedName = unparsedName;
				}

			public String getFirst()
			{
				return first;
			}

			public String getFirstRest()
			{
				return firstRest;
			}

			public String getLast()
			{
				return last;
			}

			public String getLastRest()
			{
				return lastRest;
			}

			public String getMiddle()
			{
				return middle;
			}

			public String getSuffix()
			{
				return suffix;
			}

			public String getTitle()
			{
				return title;
			}

			public String getUnparsedName()
			{
				return unparsedName;
			}

		}

	}

}



 

