package springapp.web.findpeople;

import java.util.ArrayList;

@SuppressWarnings("rawtypes")
public class BGxmlManager {
	
 
	private profileReport proRpt;
	
	ArrayList addHisList= new ArrayList();
	ArrayList merVessList= new ArrayList();
	ArrayList airSerList= new ArrayList();
	ArrayList birthList= new ArrayList();
	ArrayList deathList= new ArrayList();
	ArrayList DEAList= new ArrayList();
	ArrayList FAAList= new ArrayList();
	ArrayList WeaponList= new ArrayList();
	ArrayList FCCList= new ArrayList();
	ArrayList FFLList= new ArrayList();
	ArrayList USDomainList= new ArrayList();
	ArrayList campaignList= new ArrayList();
	ArrayList businessList= new ArrayList();
	ArrayList OccupantList= new ArrayList();
	
	
	public profileReport getProRpt() {
		return proRpt;
	}

	public void setProRpt(profileReport proRpt) {
		this.proRpt = proRpt;
	}
	
	
	public ArrayList getAddHisList() {
		return addHisList;
	}

	public ArrayList getMerVessList() {
		return merVessList;
	}

	public ArrayList getAirSerList() {
		return airSerList;
	}

	public ArrayList getBirthList() {
		return birthList;
	}

	public ArrayList getDeathList() {
		return deathList;
	}

	public ArrayList getDEAList() {
		return DEAList;
	}

	public ArrayList getFAAList() {
		return FAAList;
	}

	public ArrayList getWeaponList() {
		return WeaponList;
	}

	public ArrayList getFCCList() {
		return FCCList;
	}

	public ArrayList getFFLList() {
		return FFLList;
	}

	public ArrayList getUSDomainList() {
		return USDomainList;
	}

	public ArrayList getCampaignList() {
		return campaignList;
	}

	public ArrayList getBusinessList() {
		return businessList;
	}

	public ArrayList getOccupantList() {
		return OccupantList;
	}



	public static class profileReport
	{
		String ValidityDate;
		String LastName;
		String FirstName;
		String MiddleName;
		String DOB;
		String Address;;
		String City;
		String State;
		String Zip;
		String County;
		String Phone ;
		
		public profileReport(String validityDate, String lastName,
				String firstName, String middleName, String dob,
				String address, String city, String state, String zip,
				String county, String phone) {
		
			this.ValidityDate = validityDate;
			this.LastName = lastName;
			this.FirstName = firstName;
			this.MiddleName = middleName;
			this.DOB = dob;
			this.Address = address;
			this.City = city;
			this.State = state;
			this.Zip = zip;
			this.County = county;
			this.Phone = phone;
		}

		public String getValidityDate() {
			return ValidityDate;
		}

		public String getLastName() {
			return LastName;
		}

		public String getFirstName() {
			return FirstName;
		}

		public String getMiddleName() {
			return MiddleName;
		}

		public String getDOB() {
			return DOB;
		}

		public String getAddress() {
			return Address;
		}

		public String getCity() {
			return City;
		}

		public String getState() {
			return State;
		}

		public String getZip() {
			return Zip;
		}

		public String getCounty() {
			return County;
		}

		public String getPhone() {
			return Phone;
		}
	}
	
	public static class addressHistory
	{
		String ValidityDate;
		String address;
		String city;
		String state;
		String zip;
		String county;
		String phone;
		
		public addressHistory(String validityDate, String address, String city,
				String state, String zip, String county, String phone) {
			
			this.ValidityDate = validityDate;
			this.address = address;
			this.city = city;
			this.state = state;
			this.zip = zip;
			this.county = county;
			this.phone = phone;
		}

		public String getValidityDate() {
			return ValidityDate;
		}

		public String getAddress() {
			return address;
		}

		public String getCity() {
			return city;
		}

		public String getState() {
			return state;
		}

		public String getZip() {
			return zip;
		}

		public String getCounty() {
			return county;
		}

		public String getPhone() {
			return phone;
		}

	}
	
	public static class merchantVessels
	{
				String vesselname;
				String hullnumber;
				String hullidnumber;
				String companyowner;
				String companytype;
				String ownerlastname;
				String ownerfirstname;
				String servicetype;
				String address;
				String address2;
				String address3;
				String address4 ;
				String city;
				String state;
				String province;
				String zip;
				String callsign;
				String imonumber;
				String boatmaker;
				String yearbuilt;
				String hullmaterial;
				String motortype;
				String horsepower;
				String grosstons;
				String length;
				String width;
				String depth;
				String countrybuilt;
				String shipyard;
				String homeport;
				String stateofport;
				
				public merchantVessels(String vesselname, String hullnumber,
						String hullidnumber, String companyowner,
						String companytype, String ownerlastname,
						String ownerfirstname, String servicetype,
						String address, String address2, String address3,
						String address4, String city, String state,
						String province, String zip, String callsign,
						String imonumber, String boatmaker, String yearbuilt,
						String hullmaterial, String motortype,
						String horsepower, String grosstons, String length,
						String width, String depth, String countrybuilt,
						String shipyard, String homeport, String stateofport) {
					
					this.vesselname = vesselname;
					this.hullnumber = hullnumber;
					this.hullidnumber = hullidnumber;
					this.companyowner = companyowner;
					this.companytype = companytype;
					this.ownerlastname = ownerlastname;
					this.ownerfirstname = ownerfirstname;
					this.servicetype = servicetype;
					this.address = address;
					this.address2 = address2;
					this.address3 = address3;
					this.address4 = address4;
					this.city = city;
					this.state = state;
					this.province = province;
					this.zip = zip;
					this.callsign = callsign;
					this.imonumber = imonumber;
					this.boatmaker = boatmaker;
					this.yearbuilt = yearbuilt;
					this.hullmaterial = hullmaterial;
					this.motortype = motortype;
					this.horsepower = horsepower;
					this.grosstons = grosstons;
					this.length = length;
					this.width = width;
					this.depth = depth;
					this.countrybuilt = countrybuilt;
					this.shipyard = shipyard;
					this.homeport = homeport;
					this.stateofport = stateofport;
				}

				public String getVesselname() {
					return vesselname;
				}

				public String getHullnumber() {
					return hullnumber;
				}

				public String getHullidnumber() {
					return hullidnumber;
				}

				public String getCompanyowner() {
					return companyowner;
				}

				public String getCompanytype() {
					return companytype;
				}

				public String getOwnerlastname() {
					return ownerlastname;
				}

				public String getOwnerfirstname() {
					return ownerfirstname;
				}

				public String getServicetype() {
					return servicetype;
				}

				public String getAddress() {
					return address;
				}

				public String getAddress2() {
					return address2;
				}

				public String getAddress3() {
					return address3;
				}

				public String getAddress4() {
					return address4;
				}

				public String getCity() {
					return city;
				}

				public String getState() {
					return state;
				}

				public String getProvince() {
					return province;
				}

				public String getZip() {
					return zip;
				}

				public String getCallsign() {
					return callsign;
				}

				public String getImonumber() {
					return imonumber;
				}

				public String getBoatmaker() {
					return boatmaker;
				}

				public String getYearbuilt() {
					return yearbuilt;
				}

				public String getHullmaterial() {
					return hullmaterial;
				}

				public String getMotortype() {
					return motortype;
				}

				public String getHorsepower() {
					return horsepower;
				}

				public String getGrosstons() {
					return grosstons;
				}

				public String getLength() {
					return length;
				}

				public String getWidth() {
					return width;
				}

				public String getDepth() {
					return depth;
				}

				public String getCountrybuilt() {
					return countrybuilt;
				}

				public String getShipyard() {
					return shipyard;
				}

				public String getHomeport() {
					return homeport;
				}

				public String getStateofport() {
					return stateofport;
				}
		
	}
	
	public static class aircraftSearch
	{
		String nnumber;
		String serialnumber;
		String yearbuilt;
		String model;
		String ownername;
		String certdate;
		String address;
		String address2;
		String city;
		String state;
		String zip;
		
		public aircraftSearch(String nnumber, String serialnumber,
				String yearbuilt, String model, String ownername,
				String certdate, String address, String address2, String city,
				String state, String zip) {
		
			this.nnumber = nnumber;
			this.serialnumber = serialnumber;
			this.yearbuilt = yearbuilt;
			this.model = model;
			this.ownername = ownername;
			this.certdate = certdate;
			this.address = address;
			this.address2 = address2;
			this.city = city;
			this.state = state;
			this.zip = zip;
		}

		public String getNnumber() {
			return nnumber;
		}

		public String getSerialnumber() {
			return serialnumber;
		}

		public String getYearbuilt() {
			return yearbuilt;
		}

		public String getModel() {
			return model;
		}

		public String getOwnername() {
			return ownername;
		}

		public String getCertdate() {
			return certdate;
		}

		public String getAddress() {
			return address;
		}

		public String getAddress2() {
			return address2;
		}

		public String getCity() {
			return city;
		}

		public String getState() {
			return state;
		}

		public String getZip() {
			return zip;
		}
	}
	
	public static class birthRecords
	{
		String lastname;
		String firstname;
		String middlename;
		String DOB;
		String gender;
		String birthCounty;
		String St;
		public birthRecords(String lastname, String firstname,
				String middlename, String dob, String gender,
				String birthCounty, String st) {
			
			this.lastname = lastname;
			this.firstname = firstname;
			this.middlename = middlename;
			this.DOB = dob;
			this.gender = gender;
			this.birthCounty = birthCounty;
			this.St = st;
		}
		public String getLastname() {
			return lastname;
		}
		public String getFirstname() {
			return firstname;
		}
		public String getMiddlename() {
			return middlename;
		}
		public String getDOB() {
			return DOB;
		}
		public String getGender() {
			return gender;
		}
		public String getBirthCounty() {
			return birthCounty;
		}
		public String getSt() {
			return St;
		}
			
	}
	
	public static class deathRecords
	{
		String lastname;
		String suffix;
		String firstname;
		String middlename;
		String dateofdeath;
		String SSN;
		String dateofbirth;
		public deathRecords(String lastname, String suffix, String firstname,
				String middlename, String dateofdeath, String ssn,
				String dateofbirth) {
			
			this.lastname = lastname;
			this.suffix = suffix;
			this.firstname = firstname;
			this.middlename = middlename;
			this.dateofdeath = dateofdeath;
			this.SSN = ssn;
			this.dateofbirth = dateofbirth;
		}
		public String getLastname() {
			return lastname;
		}
		public String getSuffix() {
			return suffix;
		}
		public String getFirstname() {
			return firstname;
		}
		public String getMiddlename() {
			return middlename;
		}
		public String getDateofdeath() {
			return dateofdeath;
		}
		public String getSSN() {
			return SSN;
		}
		public String getDateofbirth() {
			return dateofbirth;
		}
			
	}
	
	public static class DEASearch
	{
		String lastname;
		String firstname;
		String DEANumber;
		String address;
		String city;
		String state;
		String zip;
		String businesstype;
		String expirationdate;
		String county;
		String schedules;
		public DEASearch(String lastname, String firstname, String number,
				String address, String city, String state, String zip,
				String businesstype, String expirationdate, String county,
				String schedules) {
			this.lastname = lastname;
			this.firstname = firstname;
			this.DEANumber = number;
			this.address = address;
			this.city = city;
			this.state = state;
			this.zip = zip;
			this.businesstype = businesstype;
			this.expirationdate = expirationdate;
			this.county = county;
			this.schedules = schedules;
		}
		public String getLastname() {
			return lastname;
		}
		public String getFirstname() {
			return firstname;
		}
		public String getDEANumber() {
			return DEANumber;
		}
		public String getAddress() {
			return address;
		}
		public String getCity() {
			return city;
		}
		public String getState() {
			return state;
		}
		public String getZip() {
			return zip;
		}
		public String getBusinesstype() {
			return businesstype;
		}
		public String getExpirationdate() {
			return expirationdate;
		}
		public String getCounty() {
			return county;
		}
		public String getSchedules() {
			return schedules;
		}
		
	}
	
	//need to implement
	public static class FAAPilotLicenses
	{
		String lastname;
		String firstname;
		String middlename;
		String address;
		String address2;
		String city;
		String state;
		String zip;
		String FAANumber;
		String medicalExpDate;
		String certificationType;
		String certlevel;
		String ratings;
		public FAAPilotLicenses(String lastname, String firstname,
				String middlename, String address, String address2,
				String city, String state, String zip, String number,
				String medicalExpDate, String certificationType,
				String certlevel, String ratings) {
			super();
			this.lastname = lastname;
			this.firstname = firstname;
			this.middlename = middlename;
			this.address = address;
			this.address2 = address2;
			this.city = city;
			this.state = state;
			this.zip = zip;
			this.FAANumber = number;
			this.medicalExpDate = medicalExpDate;
			this.certificationType = certificationType;
			this.certlevel = certlevel;
			this.ratings = ratings;
		}
		public String getLastname() {
			return lastname;
		}
		public String getFirstname() {
			return firstname;
		}
		public String getMiddlename() {
			return middlename;
		}
		public String getAddress() {
			return address;
		}
		public String getAddress2() {
			return address2;
		}
		public String getCity() {
			return city;
		}
		public String getState() {
			return state;
		}
		public String getZip() {
			return zip;
		}
		public String getFAANumber() {
			return FAANumber;
		}
		public String getMedicalExpDate() {
			return medicalExpDate;
		}
		public String getCertificationType() {
			return certificationType;
		}
		public String getCertlevel() {
			return certlevel;
		}
		public String getRatings() {
			return ratings;
		}
		
				
	}
	
	public static class concealedWeaponPermits
	{
		String lastname;
		String firstname;
		String middlename;
		String dateofbirth;
		String licenseNumber;
		String state;
		String expirationdate;
		String address;
		String city;
		String state1;
		String zip;
		String countyName;
		String race;
		String sex;
		
		public concealedWeaponPermits(String lastname, String firstname,
				String middlename, String dateofbirth, String licenseNumber,
				String state, String expirationdate, String address,
				String city, String state1, String zip, String countyName,
				String race, String sex) {
			super();
			this.lastname = lastname;
			this.firstname = firstname;
			this.middlename = middlename;
			this.dateofbirth = dateofbirth;
			this.licenseNumber = licenseNumber;
			this.state = state;
			this.expirationdate = expirationdate;
			this.address = address;
			this.city = city;
			this.state1 = state1;
			this.zip = zip;
			this.countyName = countyName;
			this.race = race;
			this.sex = sex;
		}

		public String getLastname() {
			return lastname;
		}

		public String getFirstname() {
			return firstname;
		}

		public String getMiddlename() {
			return middlename;
		}

		public String getDateofbirth() {
			return dateofbirth;
		}

		public String getLicenseNumber() {
			return licenseNumber;
		}

		public String getState() {
			return state;
		}

		public String getExpirationdate() {
			return expirationdate;
		}

		public String getAddress() {
			return address;
		}

		public String getCity() {
			return city;
		}

		public String getState1() {
			return state1;
		}

		public String getZip() {
			return zip;
		}

		public String getCountyName() {
			return countyName;
		}

		public String getRace() {
			return race;
		}

		public String getSex() {
			return sex;
		}
		
				
	}
	
	public static class FCCLicenses
	{
		String lastname;
		String firstname;
		String middlename;
		String callsign;
		String address;
		String city;
		String state;
		String zip;
		String FRNnumber;
		public FCCLicenses(String lastname, String firstname,
				String middlename, String callsign, String address,
				String city, String state, String zip, String nnumber) {
			
			this.lastname = lastname;
			this.firstname = firstname;
			this.middlename = middlename;
			this.callsign = callsign;
			this.address = address;
			this.city = city;
			this.state = state;
			this.zip = zip;
			this.FRNnumber = nnumber;
		}
		public String getLastname() {
			return lastname;
		}
		public String getFirstname() {
			return firstname;
		}
		public String getMiddlename() {
			return middlename;
		}
		public String getCallsign() {
			return callsign;
		}
		public String getAddress() {
			return address;
		}
		public String getCity() {
			return city;
		}
		public String getState() {
			return state;
		}
		public String getZip() {
			return zip;
		}
		public String getFRNnumber() {
			return FRNnumber;
		}
		
	}
	
	public static class FFLFirearmDealerLicenses
	{
		String Expires;
		String licensee_name;
		String business_name;
		String premise_street;
		String premise_city;
		String premise_state;
		String premise_zip;
		String premise_phone;
		String mailing_address;
		String mailing_city;
		String mailing_state;
		String mailing_zip;
		String mailing_phone;
		public FFLFirearmDealerLicenses(String expires, String licensee_name,
				String business_name, String premise_street,
				String premise_city, String premise_state, String premise_zip,
				String premise_phone, String mailing_address,
				String mailing_city, String mailing_state, String mailing_zip,
				String mailing_phone) {
			
			this.Expires = expires;
			this.licensee_name = licensee_name;
			this.business_name = business_name;
			this.premise_street = premise_street;
			this.premise_city = premise_city;
			this.premise_state = premise_state;
			this.premise_zip = premise_zip;
			this.premise_phone = premise_phone;
			this.mailing_address = mailing_address;
			this.mailing_city = mailing_city;
			this.mailing_state = mailing_state;
			this.mailing_zip = mailing_zip;
			this.mailing_phone = mailing_phone;
		}
		public String getExpires() {
			return Expires;
		}
		public String getLicensee_name() {
			return licensee_name;
		}
		public String getBusiness_name() {
			return business_name;
		}
		public String getPremise_street() {
			return premise_street;
		}
		public String getPremise_city() {
			return premise_city;
		}
		public String getPremise_state() {
			return premise_state;
		}
		public String getPremise_zip() {
			return premise_zip;
		}
		public String getPremise_phone() {
			return premise_phone;
		}
		public String getMailing_address() {
			return mailing_address;
		}
		public String getMailing_city() {
			return mailing_city;
		}
		public String getMailing_state() {
			return mailing_state;
		}
		public String getMailing_zip() {
			return mailing_zip;
		}
		public String getMailing_phone() {
			return mailing_phone;
		}
				
	}
	
	public static class USDomainNameOwnership
	{
		String domain_Name;
		String lastname;
		String firstname;
		String middlename;
		String company;
		String address;
		String city;
		String state;
		String zip;
		String phone;
		String fax;
		String emailaddress;
		public USDomainNameOwnership(String domain_Name, String lastname,
				String firstname, String middlename, String company,
				String address, String city, String state, String zip,
				String phone, String fax, String emailaddress) {
			this.domain_Name = domain_Name;
			this.lastname = lastname;
			this.firstname = firstname;
			this.middlename = middlename;
			this.company = company;
			this.address = address;
			this.city = city;
			this.state = state;
			this.zip = zip;
			this.phone = phone;
			this.fax = fax;
			this.emailaddress = emailaddress;
		}
		public String getDomain_Name() {
			return domain_Name;
		}
		public String getLastname() {
			return lastname;
		}
		public String getFirstname() {
			return firstname;
		}
		public String getMiddlename() {
			return middlename;
		}
		public String getCompany() {
			return company;
		}
		public String getAddress() {
			return address;
		}
		public String getCity() {
			return city;
		}
		public String getState() {
			return state;
		}
		public String getZip() {
			return zip;
		}
		public String getPhone() {
			return phone;
		}
		public String getFax() {
			return fax;
		}
		public String getEmailaddress() {
			return emailaddress;
		}
				
	}
	
	public static class campaignContributors  {
		
		String lastname;
		String firstname;
		String middlename;
		String occupation;
		String address;
		String city;
		String state;
		String zip;
		String contributionDate;
		String candidate;
		String term;
		public campaignContributors(String lastname, String firstname,
				String middlename, String occupation, String address,
				String city, String state, String zip, String contributionDate,
				String candidate, String term) {
			
			this.lastname = lastname;
			this.firstname = firstname;
			this.middlename = middlename;
			this.occupation = occupation;
			this.address = address;
			this.city = city;
			this.state = state;
			this.zip = zip;
			this.contributionDate = contributionDate;
			this.candidate = candidate;
			this.term = term;
		}
		public String getLastname() {
			return lastname;
		}
		public String getFirstname() {
			return firstname;
		}
		public String getMiddlename() {
			return middlename;
		}
		public String getOccupation() {
			return occupation;
		}
		public String getAddress() {
			return address;
		}
		public String getCity() {
			return city;
		}
		public String getState() {
			return state;
		}
		public String getZip() {
			return zip;
		}
		public String getContributionDate() {
			return contributionDate;
		}
		public String getCandidate() {
			return candidate;
		}
		public String getTerm() {
			return term;
		}
				
	}
	
	public static class businessSearch{
		
		String company;
		String address;
		String city;
		String state;
		String zip;
		String county;
		String phone;
		String fax;
		String ownertitle;
		String ownerfirst;
		String ownerlast;
		String annualsales;
		String numberofemployees;
		String squarefootage;
		String creditscorelettergrade;
		String creditscorenumericgrade;
		String primarysic;
		String primarysicdescription;
		String website;
		public businessSearch(String company, String address, String city,
				String state, String zip, String county, String phone,
				String fax, String ownertitle, String ownerfirst,
				String ownerlast, String annualsales, String numberofemployees,
				String squarefootage, String creditscorelettergrade,
				String creditscorenumericgrade, String primarysic,
				String primarysicdescription, String website) {
			
			this.company = company;
			this.address = address;
			this.city = city;
			this.state = state;
			this.zip = zip;
			this.county = county;
			this.phone = phone;
			this.fax = fax;
			this.ownertitle = ownertitle;
			this.ownerfirst = ownerfirst;
			this.ownerlast = ownerlast;
			this.annualsales = annualsales;
			this.numberofemployees = numberofemployees;
			this.squarefootage = squarefootage;
			this.creditscorelettergrade = creditscorelettergrade;
			this.creditscorenumericgrade = creditscorenumericgrade;
			this.primarysic = primarysic;
			this.primarysicdescription = primarysicdescription;
			this.website = website;
		}
		public String getCompany() {
			return company;
		}
		public String getAddress() {
			return address;
		}
		public String getCity() {
			return city;
		}
		public String getState() {
			return state;
		}
		public String getZip() {
			return zip;
		}
		public String getCounty() {
			return county;
		}
		public String getPhone() {
			return phone;
		}
		public String getFax() {
			return fax;
		}
		public String getOwnertitle() {
			return ownertitle;
		}
		public String getOwnerfirst() {
			return ownerfirst;
		}
		public String getOwnerlast() {
			return ownerlast;
		}
		public String getAnnualsales() {
			return annualsales;
		}
		public String getNumberofemployees() {
			return numberofemployees;
		}
		public String getSquarefootage() {
			return squarefootage;
		}
		public String getCreditscorelettergrade() {
			return creditscorelettergrade;
		}
		public String getCreditscorenumericgrade() {
			return creditscorenumericgrade;
		}
		public String getPrimarysic() {
			return primarysic;
		}
		public String getPrimarysicdescription() {
			return primarysicdescription;
		}
		public String getWebsite() {
			return website;
		}
			
	}
	
	public static class OccupantData
	{
		String address;
		String city;
		String state;
		String zip;
		String[] listedPhones;
		
		ArrayList otherList = new ArrayList() ;
		ArrayList censusList = new ArrayList() ;
		
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getZip() {
			return zip;
		}
		public void setZip(String zip) {
			this.zip = zip;
		}
		public String[] getListedPhones() {
			return listedPhones;
		}
		public void setListedPhones(String[] listedPhones) {
			this.listedPhones = listedPhones;
		}
		public ArrayList<otherPeople> getOtherList() {
			return otherList;
		}
		public ArrayList<censusData> getCensusList() {
			return censusList;
		}
		
					
	}	
	
	public static class otherPeople{
			String ValidityDate;
			String possibleRelative;
			String LastName;
			String FirstName;
			String MiddleName;
			String DOB;
			public otherPeople(String validityDate, String possibleRelative,
					String lastName, String firstName, String middleName,
					String dob) {
				this.ValidityDate = validityDate;
				this.possibleRelative = possibleRelative;
				this.LastName = lastName;
				this.FirstName = firstName;
				this.MiddleName = middleName;
				this.DOB = dob;
			}
			public String getValidityDate() {
				return ValidityDate;
			}
			public String getPossibleRelative() {
				return possibleRelative;
			}
			public String getLastName() {
				return LastName;
			}
			public String getFirstName() {
				return FirstName;
			}
			public String getMiddleName() {
				return MiddleName;
			}
			public String getDOB() {
				return DOB;
			}

	}
			
	//need to implement for zip code which is meta data
	public static class censusData{	
			String zip1;
			String city;
			String city_alias_name;
			String county_name;
			String population;
			String households_per_zip;
			String white_population;
			String black_population;
			String hispanic_population;
			String persons_per_household;
			String avg_house_value;
			String income_per_household;
			String latitude;
			String longitude;
			String elevation;
			String state;
			String city_type;
			String city_alias_abbrev;
			String area_code;
			public censusData(String zip, String city, String city_alias_name,
					String county_name, String population,
					String households_per_zip, String white_population,
					String black_population, String hispanic_population,
					String persons_per_household, String avg_house_value,
					String income_per_household, String latitude,
					String longitude, String elevation, String state,
					String city_type, String city_alias_abbrev, String area_code) {
				this.zip1 = zip;
				this.city = city;
				this.city_alias_name = city_alias_name;
				this.county_name = county_name;
				this.population = population;
				this.households_per_zip = households_per_zip;
				this.white_population = white_population;
				this.black_population = black_population;
				this.hispanic_population = hispanic_population;
				this.persons_per_household = persons_per_household;
				this.avg_house_value = avg_house_value;
				this.income_per_household = income_per_household;
				this.latitude = latitude;
				this.longitude = longitude;
				this.elevation = elevation;
				this.state = state;
				this.city_type = city_type;
				this.city_alias_abbrev = city_alias_abbrev;
				this.area_code = area_code;
			}
			public String getZip1() {
				return zip1;
			}
			public String getCity() {
				return city;
			}
			public String getCity_alias_name() {
				return city_alias_name;
			}
			public String getCounty_name() {
				return county_name;
			}
			public String getPopulation() {
				return population;
			}
			public String getHouseholds_per_zip() {
				return households_per_zip;
			}
			public String getWhite_population() {
				return white_population;
			}
			public String getBlack_population() {
				return black_population;
			}
			public String getHispanic_population() {
				return hispanic_population;
			}
			public String getPersons_per_household() {
				return persons_per_household;
			}
			public String getAvg_house_value() {
				return avg_house_value;
			}
			public String getIncome_per_household() {
				return income_per_household;
			}
			public String getLatitude() {
				return latitude;
			}
			public String getLongitude() {
				return longitude;
			}
			public String getElevation() {
				return elevation;
			}
			public String getState() {
				return state;
			}
			public String getCity_type() {
				return city_type;
			}
			public String getCity_alias_abbrev() {
				return city_alias_abbrev;
			}
			public String getArea_code() {
				return area_code;
			}
	}
}
