package springapp.domain;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.apache.torque.NoRowsException;
import org.apache.torque.TooManyRowsException;
import org.apache.torque.Torque;
import org.apache.torque.TorqueException;
import org.apache.torque.map.MapBuilder;
import org.apache.torque.map.TableMap;
import org.apache.torque.om.ObjectKey;
import org.apache.torque.om.SimpleKey;
import org.apache.torque.util.BasePeer;
import org.apache.torque.util.Criteria;

import springapp.domain.map.NewBJLResultsMapBuilder;

import com.workingdogs.village.DataSetException;
import com.workingdogs.village.QueryDataSet;
import com.workingdogs.village.Record;

public abstract class NewBaseBJLResultsPeer extends BasePeer {
	 /** Serial version */
    private static final long serialVersionUID = 1230102311301L;


    /** the default database name for this class */
    public static final String DATABASE_NAME;

     /** the table name for this class */
    public static final String TABLE_NAME;

    /**
     * @return the map builder for this peer
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static MapBuilder getMapBuilder()
        throws TorqueException
    {
       // return getMapBuilder(BjlResultsMapBuilder.CLASS_NAME);
    	return getMapBuilder(NewBJLResultsMapBuilder.CLASS_NAME);
    }
    /** the column name for the result_id field */
    public static final String RESULT_ID;
      /** the column name for the user_search_id field */
    public static final String USER_SEARCH_ID;
      /** the column name for the action_type field */
    public static final String NAME_FILING_STATE;
      /** the column name for the address field */
    public static final String FIRSTNAME;
      /** the column name for the address_city field */
    public static final String MIDDLENAME;
      /** the column name for the address_state field */
    public static final String LASTNAME;
      /** the column name for the address_zip field */
    public static final String NAME_SUFFIX;
      /** the column name for the amount_liability field */
    public static final String NAME_TYPE;
      /** the column name for the assets field */
    public static final String NAME_TYPE_DESC;
      /** the column name for the assets_available field */
    public static final String SSN_TAXID_FLAG;
      /** the column name for the association_code field */
    public static final String SSN;
      /** the column name for the attorney field */
    public static final String COMMENT_SEQUENCE;
      /** the column name for the attorney_address field */
    public static final String COMMENT_TYPE;
      /** the column name for the attorney_city field */
    public static final String COMMENT_TYPE_DESC;
      /** the column name for the attorney_phone field */
    public static final String HOUSE_NUMBER;
      /** the column name for the attorney_state field */
    public static final String STREET_DIRECTION;
      /** the column name for the attorney_zip field */
    public static final String STREET_NAME;
      /** the column name for the bankruptcy_dismissal field */
    public static final String STREET_SUFFIX;
      /** the column name for the book field */
    public static final String APARTMENT_NUM;
      /** the column name for the case_number field */
    public static final String CITY;
      /** the column name for the court_id_code field */
    public static final String STATE;
      /** the column name for the court_address field */
    public static final String ZIPCODE;
      /** the column name for the court_city field */
    public static final String FILING_STATE;
      /** the column name for the court_name field */
    public static final String FILING_GROUP;
      /** the column name for the FILING_GROUP_DESC field */
    public static final String FILING_GROUP_DESC;
      /** the column name for the court_state field */
    public static final String FILING_TYPE;
      /** the column name for the court_zip field */
    public static final String FILING_TYPE_DESC;
      /** the column name for the defendant field */
    public static final String FILING_DATE;
      /** the column name for the entity_type field */
    public static final String FILING_NUMBER;
      /** the column name for the filing_date field */
    public static final String TAX_LIEN_TYPE;
      /** the column name for the filing_type field */
    public static final String TAX_LIEN_TYPE_DESC;
      /** the column name for the generation_code field */
    public static final String BANKRUPTCY_TYPE;
      /** the column name for the judges_initials field */
    public static final String BANKRUPTCY_TYPE_DESC;
      /** the column name for the original_book field */
    public static final String DOCKET_NUMBER;
      /** the column name for the original_case_number field */
    public static final String UNLAWFULl_DETAINER;
      /** the column name for the original_department field */
    public static final String INITIAL_DATE;
      /** the column name for the original_page field */
    public static final String INITIAL_AMOUNT;
      /** the column name for the other_case_number field */
    public static final String INITIAL_DOCKET;
      /** the column name for the page field */
    public static final String JUDGEMENT_DATE;
      /** the column name for the plaintiff field */
    public static final String JUDGEMENT_AMOUNT;
      /** the column name for the release_date field */
    public static final String JUDGEMENT_DOCKET;
      /** the column name for the residents_county field */
    public static final String REMOVE_DATE;
      /** the column name for the schedule_341_date field */
    public static final String REMOVE_DOCKET;
      /** the column name for the ssn_tax_id field */
    public static final String DISMISSAL_DATE;
      /** the column name for the ssn_verifier field */
    public static final String DISMISSAL_DOCKET;
      /** the column name for the unlawful_detainer field */
    public static final String ASSET_AMOUNT;
    public static final String LIABILITY_AMOUNT;
    public static final String PLAINTIFF;
	public static final String BENEFICIARY;
	public static final String SITUS;
	public static final String TRUSTEE;
	public static final String COUNTY_CODE;
	public static final String COUNTY_NAME;
	public static final String COUNTY_STATE;
	
	public static final String COURT_CODE;
	public static final String COURT_DESC;
	public static final String COURT_STATE;
	public static final String ASSETS_AVAILABLE;
	public static final String PERFECTED_DATE;
	public static final String ACTION_STATE_CODE;
	public static final String ACTION_DESC;
	public static final String DISPOSITION_STATE_CODE;
	public static final String DISPOSITION_DESC;
	public static final String AMOUNT;
	public static final String RELEASE_DATE;
	public static final String RELEASE_NUMBER;
	public static final String SUIT_CASE_NUMBER;
	public static final String SUIT_DATE;
	public static final String SUIT_AMOUNT;
	public static final String SATISFACTION_DATE;
	public static final String DISCHARGE_DATE;
	
	public static final String CLOSED_DATE;
	public static final String TRUST_DEED_NUMBER;
	public static final String TRUST_DEED_DATE;
	public static final String SALE_NUMBER;
	public static final String SALE_DATE;
	public static final String CANCELLATION_NUMBER;
	public static final String CANCELLATION_DATE;
	public static final String SCHED_341_DATE;
	public static final String UPDATE_DATE;
	
	public static final String FULLNAME;
	public static final String BUSINESSNAME;
	public static final String DOB;		
	public static final String COURT_ADDRESS;
	public static final String COURT_CITY;
	public static final String COURT_ZIP;
	public static final String COURT_PHONE;
	//
	public static final String  SCHED_341_TIME;
	public static final String  JUDGE;
	public static final String  LAWFIRM;
	public static final String  BOOK;
	public static final String  PAGE;
	public static final String  ORIGDEPT;
	public static final String  ORIGCASE;
	public static final String  ORIGBOOK;
	public static final String  ORIGPAGE;
	public static final String  ASSOCCODE;
	public static final String  ACTIONTYPE;
	public static final String  ACTIONTYPEDESC;
	
  
    static
    {
        DATABASE_NAME = "services";
        TABLE_NAME = "new_bjl_results";

        RESULT_ID = "new_bjl_results.result_id";
        USER_SEARCH_ID = "new_bjl_results.user_search_id";
        NAME_FILING_STATE = "new_bjl_results.name_filing_state";
        FIRSTNAME = "new_bjl_results.firstname";
        MIDDLENAME = "new_bjl_results.middlename";
        LASTNAME = "new_bjl_results.lastname";
        NAME_SUFFIX = "new_bjl_results.name_suffix";
        NAME_TYPE = "new_bjl_results.name_type";
        NAME_TYPE_DESC = "new_bjl_results.name_type_desc";
        SSN_TAXID_FLAG = "new_bjl_results.ssn_taxid_flag";
        SSN = "new_bjl_results.ssn";
        COMMENT_SEQUENCE = "new_bjl_results.comment_sequence";
        COMMENT_TYPE = "new_bjl_results.comment_type";
        COMMENT_TYPE_DESC = "new_bjl_results.comment_type_desc";
        HOUSE_NUMBER = "new_bjl_results.house_number";
        STREET_DIRECTION = "new_bjl_results.street_direction";
        STREET_NAME = "new_bjl_results.street_name";
        STREET_SUFFIX = "new_bjl_results.street_suffix";
        APARTMENT_NUM = "new_bjl_results.apartment_num";
        CITY = "new_bjl_results.city";
        STATE = "new_bjl_results.state";
        ZIPCODE = "new_bjl_results.zipcode";
        FILING_STATE = "new_bjl_results.filing_state";
        FILING_GROUP = "new_bjl_results.filing_group";
        FILING_GROUP_DESC = "new_bjl_results.filing_group_desc";
        FILING_TYPE = "new_bjl_results.filing_type";
        FILING_TYPE_DESC = "new_bjl_results.filing_type_desc";
        FILING_DATE = "new_bjl_results.filing_date";
        FILING_NUMBER = "new_bjl_results.filing_number";
        TAX_LIEN_TYPE = "new_bjl_results.tax_lien_type";
        TAX_LIEN_TYPE_DESC = "new_bjl_results.tax_lien_type_desc";
        BANKRUPTCY_TYPE = "new_bjl_results.bankruptcy_type";
        BANKRUPTCY_TYPE_DESC = "new_bjl_results.bankruptcy_type_desc";
        DOCKET_NUMBER = "new_bjl_results.docket_number";
        UNLAWFULl_DETAINER = "new_bjl_results.unlawful_detainer";
        INITIAL_DATE = "new_bjl_results.initial_date";
        INITIAL_AMOUNT = "new_bjl_results.initial_amount";
        INITIAL_DOCKET = "new_bjl_results.initial_docket";
        JUDGEMENT_DATE = "new_bjl_results.judgement_date";
        JUDGEMENT_AMOUNT = "new_bjl_results.judgement_amount";
        JUDGEMENT_DOCKET = "new_bjl_results.judgement_docket";
        REMOVE_DATE = "new_bjl_results.remove_date";
        REMOVE_DOCKET = "new_bjl_results.remove_docket";
        DISMISSAL_DATE = "new_bjl_results.dismissal_date";
        DISMISSAL_DOCKET = "new_bjl_results.dismissal_docket";
        ASSET_AMOUNT="new_bjl_results.asset_amount";
        LIABILITY_AMOUNT="new_bjl_results.liability_amount";
        PLAINTIFF="new_bjl_results.plaintiff";
        BENEFICIARY="new_bjl_results.beneficiary";
        SITUS="new_bjl_results.situs";
        TRUSTEE="new_bjl_results.trustee";
        COUNTY_CODE="new_bjl_results.county_code";
        COUNTY_NAME="new_bjl_results.county_name";
        COUNTY_STATE="new_bjl_results.county_state";
        COURT_CODE="new_bjl_results.court_code";
        COURT_DESC="new_bjl_results.court_desc";
        COURT_STATE="new_bjl_results.court_state";
        ASSETS_AVAILABLE="new_bjl_results.assets_available";
        PERFECTED_DATE="new_bjl_results.perfected_date";
        ACTION_STATE_CODE="new_bjl_results.action_state_code";
        ACTION_DESC="new_bjl_results.action_desc";
        DISPOSITION_STATE_CODE="new_bjl_results.disposition_state_code";
        DISPOSITION_DESC="new_bjl_results.disposition_desc";
        AMOUNT="new_bjl_results.amount";
        RELEASE_DATE="new_bjl_results.release_date";
        RELEASE_NUMBER="new_bjl_results.release_number";
        SUIT_CASE_NUMBER="new_bjl_results.suit_case_number";
        SUIT_DATE="new_bjl_results.suit_date";
        SUIT_AMOUNT="new_bjl_results.suit_amount";
        SATISFACTION_DATE="new_bjl_results.satisfaction_date";
        DISCHARGE_DATE="new_bjl_results.discharge_date";
        CLOSED_DATE="new_bjl_results.closed_date";
        TRUST_DEED_NUMBER="new_bjl_results.trust_deed_number";
        TRUST_DEED_DATE="new_bjl_results.trust_deed_date";
        SALE_NUMBER="new_bjl_results.sale_number";
        SALE_DATE="new_bjl_results.sale_date";
        CANCELLATION_NUMBER="new_bjl_results.cancellation_number";
        CANCELLATION_DATE="new_bjl_results.cancellation_date";
        SCHED_341_DATE="new_bjl_results.sched_341_date";
        UPDATE_DATE="new_bjl_results.update_date";
        
        FULLNAME="new_bjl_results.fullname";
        BUSINESSNAME="new_bjl_results.businessname";
        DOB="new_bjl_results.dob";		
        COURT_ADDRESS="new_bjl_results.court_address";
        COURT_CITY="new_bjl_results.court_city";
        COURT_ZIP="new_bjl_results.court_zip";
        COURT_PHONE="new_bjl_results.court_phone";
        //
        SCHED_341_TIME="new_bjl_results.sched_341_time";
		JUDGE="new_bjl_results.judge";
		LAWFIRM="new_bjl_results.lawfirm";
		BOOK="new_bjl_results.book";
		PAGE="new_bjl_results.page";
		ORIGDEPT="new_bjl_results.origdept";
		ORIGCASE="new_bjl_results.origcase";
		ORIGBOOK="new_bjl_results.origbook";
		ORIGPAGE="new_bjl_results.origpage";
		ASSOCCODE="new_bjl_results.assoccode";
		ACTIONTYPE="new_bjl_results.actiontype";
		ACTIONTYPEDESC="new_bjl_results.actiontypedesc";
        
              if (Torque.isInit())
        {
            try
            {
                //getMapBuilder(BjlResultsMapBuilder.CLASS_NAME);
            	getMapBuilder(NewBJLResultsMapBuilder.CLASS_NAME);
            }
            catch (Exception e)
            {
                log.error("Could not initialize Peer", e);
                throw new RuntimeException(e);
            }
        }
        else
        {
            //Torque.registerMapBuilder(BjlResultsMapBuilder.CLASS_NAME);
        	Torque.registerMapBuilder(NewBJLResultsMapBuilder.CLASS_NAME);
        }
    }
 
    /** number of columns for this peer */
    public static final int numColumns =  80;

    /** A class that can be returned by this peer. */
    protected static final String CLASSNAME_DEFAULT =
        "springapp.domain.NewBJLResults";

    /** A class that can be returned by this peer. */
    protected static final Class CLASS_DEFAULT = initClass(CLASSNAME_DEFAULT);

    /**
     * Class object initialization method.
     *
     * @param className name of the class to initialize
     * @return the initialized class
     */
    private static Class initClass(String className)
    {
        Class c = null;
        try
        {
            c = Class.forName(className);
        }
        catch (Throwable t)
        {
            log.error("A FATAL ERROR has occurred which should not "
                + "have happened under any circumstance.  Please notify "
                + "the Torque developers <torque-dev@db.apache.org> "
                + "and give as many details as possible (including the error "
                + "stack trace).", t);

            // Error objects should always be propogated.
            if (t instanceof Error)
            {
                throw (Error) t.fillInStackTrace();
            }
        }
        return c;
    }

    /**
     * Get the list of objects for a ResultSet.  Please not that your
     * resultset MUST return columns in the right order.  You can use
     * getFieldNames() in BaseObject to get the correct sequence.
     *
     * @param results the ResultSet
     * @return the list of objects
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static List<NewBJLResults> resultSet2Objects(java.sql.ResultSet results)
            throws TorqueException
    {
        try
        {
            QueryDataSet qds = null;
            List<Record> rows = null;
            try
            {
                qds = new QueryDataSet(results);
                rows = getSelectResults(qds);
            }
            finally
            {
                if (qds != null)
                {
                    qds.close();
                }
            }

            return populateObjects(rows);
        }
        catch (SQLException e)
        {
            throw new TorqueException(e);
        }
        catch (DataSetException e)
        {
            throw new TorqueException(e);
        }
    }


  
    /**
     * Method to do inserts.
     *
     * @param criteria object used to create the INSERT statement.
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static ObjectKey doInsert(Criteria criteria)
        throws TorqueException
    {
        return NewBaseBJLResultsPeer
            .doInsert(criteria, (Connection) null);
    }

    /**
     * Method to do inserts.  This method is to be used during a transaction,
     * otherwise use the doInsert(Criteria) method.  It will take care of
     * the connection details internally.
     *
     * @param criteria object used to create the INSERT statement.
     * @param con the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static ObjectKey doInsert(Criteria criteria, Connection con)
        throws TorqueException
    {
        correctBooleans(criteria);

        setDbName(criteria);

        if (con == null)
        {
            return BasePeer.doInsert(criteria);
        }
        else
        {
            return BasePeer.doInsert(criteria, con);
        }
    }

    /**
     * Add all the columns needed to create a new object.
     *
     * @param criteria object containing the columns to add.
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void addSelectColumns(Criteria criteria)
            throws TorqueException
    {
    	criteria.addSelectColumn(RESULT_ID);
        criteria.addSelectColumn(USER_SEARCH_ID);
        criteria.addSelectColumn(NAME_FILING_STATE);
        criteria.addSelectColumn(FIRSTNAME);
        criteria.addSelectColumn(MIDDLENAME);
        criteria.addSelectColumn(LASTNAME);
        criteria.addSelectColumn(NAME_SUFFIX);
        criteria.addSelectColumn(NAME_TYPE);
        criteria.addSelectColumn(NAME_TYPE_DESC);
        criteria.addSelectColumn(SSN_TAXID_FLAG);
        criteria.addSelectColumn(SSN);
        criteria.addSelectColumn(COMMENT_SEQUENCE);
        criteria.addSelectColumn(COMMENT_TYPE);
        criteria.addSelectColumn(COMMENT_TYPE_DESC);
        criteria.addSelectColumn(HOUSE_NUMBER);
        criteria.addSelectColumn(STREET_DIRECTION);
        criteria.addSelectColumn(STREET_NAME);
        criteria.addSelectColumn(STREET_SUFFIX);
        criteria.addSelectColumn(APARTMENT_NUM);
        criteria.addSelectColumn(CITY);
        criteria.addSelectColumn(STATE);
        criteria.addSelectColumn(ZIPCODE);
        criteria.addSelectColumn(FILING_STATE);
        criteria.addSelectColumn(FILING_GROUP);
        criteria.addSelectColumn(FILING_GROUP_DESC);
        criteria.addSelectColumn(FILING_TYPE);
        criteria.addSelectColumn(FILING_TYPE_DESC);
        criteria.addSelectColumn(FILING_DATE);
        criteria.addSelectColumn(FILING_NUMBER);
        criteria.addSelectColumn(TAX_LIEN_TYPE);
        criteria.addSelectColumn(TAX_LIEN_TYPE_DESC);
        criteria.addSelectColumn(BANKRUPTCY_TYPE);
        criteria.addSelectColumn(BANKRUPTCY_TYPE_DESC);
        criteria.addSelectColumn(DOCKET_NUMBER);
        criteria.addSelectColumn(UNLAWFULl_DETAINER);
        criteria.addSelectColumn(INITIAL_DATE);
        criteria.addSelectColumn(INITIAL_AMOUNT);
        criteria.addSelectColumn(INITIAL_DOCKET);
        criteria.addSelectColumn(JUDGEMENT_DATE);
        criteria.addSelectColumn(JUDGEMENT_AMOUNT);
        criteria.addSelectColumn(JUDGEMENT_DOCKET);
        criteria.addSelectColumn(REMOVE_DATE);
        criteria.addSelectColumn(REMOVE_DOCKET);
      	criteria.addSelectColumn(DISMISSAL_DATE);
        criteria.addSelectColumn(DISMISSAL_DOCKET);
        criteria.addSelectColumn(ASSET_AMOUNT);
        criteria.addSelectColumn(LIABILITY_AMOUNT);
        criteria.addSelectColumn(PLAINTIFF);
    	criteria.addSelectColumn(BENEFICIARY);
    	criteria.addSelectColumn(SITUS);
    	criteria.addSelectColumn(TRUSTEE);
    	criteria.addSelectColumn(COUNTY_CODE);
    	criteria.addSelectColumn(COUNTY_NAME);
    	criteria.addSelectColumn(COUNTY_STATE);
    	criteria.addSelectColumn(COURT_CODE);
    	criteria.addSelectColumn(COURT_DESC);
    	criteria.addSelectColumn(COURT_STATE);
    	criteria.addSelectColumn(ASSETS_AVAILABLE);
    	criteria.addSelectColumn(PERFECTED_DATE);
    	criteria.addSelectColumn(ACTION_STATE_CODE);
    	criteria.addSelectColumn(ACTION_DESC);
    	criteria.addSelectColumn(DISPOSITION_STATE_CODE);
    	criteria.addSelectColumn(DISPOSITION_DESC);
    	criteria.addSelectColumn(AMOUNT);
    	criteria.addSelectColumn(RELEASE_DATE);
    	criteria.addSelectColumn(RELEASE_NUMBER);
    	criteria.addSelectColumn(SUIT_CASE_NUMBER);
    	criteria.addSelectColumn(SUIT_DATE);
    	criteria.addSelectColumn(SUIT_AMOUNT);
    	criteria.addSelectColumn(SATISFACTION_DATE);
    	criteria.addSelectColumn(DISCHARGE_DATE);
    	criteria.addSelectColumn(CLOSED_DATE);
    	criteria.addSelectColumn(TRUST_DEED_NUMBER);
    	criteria.addSelectColumn(TRUST_DEED_DATE);
    	criteria.addSelectColumn(SALE_NUMBER);
    	criteria.addSelectColumn(SALE_DATE);
    	criteria.addSelectColumn(CANCELLATION_NUMBER);
    	criteria.addSelectColumn(CANCELLATION_DATE);
    	criteria.addSelectColumn(SCHED_341_DATE);
    	criteria.addSelectColumn(UPDATE_DATE);
    	//Uhday-Aug8 ==> Tracers new fields addedd
    	criteria.addSelectColumn(FULLNAME);
		criteria.addSelectColumn(BUSINESSNAME);
		criteria.addSelectColumn(DOB);		
		criteria.addSelectColumn(COURT_ADDRESS);
		criteria.addSelectColumn(COURT_CITY);
		criteria.addSelectColumn(COURT_ZIP);
		criteria.addSelectColumn(COURT_PHONE);
		//
		criteria.addSelectColumn(SCHED_341_TIME);
		criteria.addSelectColumn(JUDGE);
		criteria.addSelectColumn(LAWFIRM);
		criteria.addSelectColumn(BOOK);
		criteria.addSelectColumn(PAGE);
		criteria.addSelectColumn(ORIGDEPT);
		criteria.addSelectColumn(ORIGCASE);
		criteria.addSelectColumn(ORIGBOOK);
		criteria.addSelectColumn(ORIGPAGE);
		criteria.addSelectColumn(ASSOCCODE);
		criteria.addSelectColumn(ACTIONTYPE);
		criteria.addSelectColumn(ACTIONTYPEDESC);
		
      }

    /**
     * changes the boolean values in the criteria to the appropriate type,
     * whenever a booleanchar or booleanint column is involved.
     * This enables the user to create criteria using Boolean values
     * for booleanchar or booleanint columns
     * @param criteria the criteria in which the boolean values should be corrected
     * @throws TorqueException if the database map for the criteria cannot be 
               obtained.
     */
    public static void correctBooleans(Criteria criteria) throws TorqueException
    {
        correctBooleans(criteria, getTableMap());
    }

    /**
     * Create a new object of type cls from a resultset row starting
     * from a specified offset  This is done so that you can select
     * other rows than just those needed for this object.  You may
     * for example want to create two objects from the same row.
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static NewBJLResults row2Object(Record row,
                                             int offset,
                                             Class cls)
        throws TorqueException
    {
        try
        {
            NewBJLResults obj = (NewBJLResults) cls.newInstance();
            NewBJLResultsPeer.populateObject(row, offset, obj);
                  obj.setModified(false);
              obj.setNew(false);

            return obj;
        }
        catch (InstantiationException e)
        {
            throw new TorqueException(e);
        }
        catch (IllegalAccessException e)
        {
            throw new TorqueException(e);
        }
    }

    /**
     * Populates an object from a resultset row starting
     * from a specified offset  This is done so that you can select
     * other rows than just those needed for this object.  You may
     * for example want to create two objects from the same row.
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void populateObject(Record row,
                                      int offset,
                                      NewBJLResults obj)
        throws TorqueException
    {
        try
        {
        	obj.setResultId(row.getValue(offset + 0).asLong());
            obj.setUserSearchId(row.getValue(offset + 1).asLong());
            obj.setName_filing_state(row.getValue(offset + 2).asString());
            obj.setFirstname(row.getValue(offset + 3).asString());
            obj.setMiddlename(row.getValue(offset + 4).asString());
            obj.setLastname(row.getValue(offset + 5).asString());
            obj.setName_suffix(row.getValue(offset + 6).asString());
            obj.setName_type(row.getValue(offset + 7).asString());
            obj.setName_type_desc(row.getValue(offset + 8).asString());
            obj.setSsn_taxid_flag(row.getValue(offset + 9).asString());
            obj.setSsn(row.getValue(offset + 10).asString());
            obj.setComment_sequence(row.getValue(offset + 11).asString());
            obj.setComment_type(row.getValue(offset + 12).asString());
            obj.setComment_type_desc(row.getValue(offset + 13).asString());
            obj.setHouse_number(row.getValue(offset + 14).asString());
            obj.setStreet_direction(row.getValue(offset + 15).asString());
            obj.setStreet_name(row.getValue(offset + 16).asString());
            obj.setStreet_suffix(row.getValue(offset + 17).asString());
            obj.setApartment_num(row.getValue(offset + 18).asString());
            obj.setCity(row.getValue(offset + 19).asString());
            obj.setState(row.getValue(offset + 20).asString());
            obj.setZipcode(row.getValue(offset + 21).asString());
            obj.setFiling_state(row.getValue(offset + 22).asString());
            obj.setFiling_group(row.getValue(offset + 23).asString());
            obj.setFiling_group_desc(row.getValue(offset + 24).asString());
            obj.setFiling_type(row.getValue(offset + 25).asString());
            obj.setFiling_type_desc(row.getValue(offset + 26).asString());
            obj.setFiling_date(row.getValue(offset + 27).asString());
            obj.setFiling_number(row.getValue(offset + 28).asString());
            obj.setTax_lien_type(row.getValue(offset + 29).asString());
            obj.setTax_lien_type_desc(row.getValue(offset + 30).asString());
            obj.setBankruptcy_type(row.getValue(offset + 31).asString());
            obj.setBankruptcy_type_desc(row.getValue(offset + 32).asString());
            obj.setDocket_number(row.getValue(offset + 33).asString());
            obj.setUnlawful_detainer(row.getValue(offset + 34).asString());
            obj.setInitial_date(row.getValue(offset + 35).asString());
            obj.setInitial_amount(row.getValue(offset + 36).asString());
            obj.setInitial_docket(row.getValue(offset + 37).asString());
            obj.setJudgement_date(row.getValue(offset + 38).asString());
            obj.setJudgement_amount(row.getValue(offset + 39).asString());
            obj.setJudgement_docket(row.getValue(offset + 40).asString());
            obj.setRemove_date(row.getValue(offset + 41).asString());
            obj.setRemove_docket(row.getValue(offset + 42).asString());
            obj.setDismissal_date(row.getValue(offset + 43).asString());
            obj.setDismissal_docket(row.getValue(offset + 44).asString());
            obj.setAsset_amount(row.getValue(offset + 45).asString());
            
            obj.setLiability_amount(row.getValue(offset + 46).asString());
            obj.setPlaintiff(row.getValue(offset + 47).asString());
            obj.setBeneficiary(row.getValue(offset + 48).asString());
            obj.setSitus(row.getValue(offset + 49).asString());
            
            obj.setTrustee(row.getValue(offset + 50).asString());
            obj.setCounty_code(row.getValue(offset + 51).asString());
            obj.setCounty_name(row.getValue(offset + 52).asString());
            obj.setCounty_state(row.getValue(offset + 53).asString());
            obj.setCourt_code(row.getValue(offset + 54).asString());
            obj.setCourt_desc(row.getValue(offset + 55).asString());
            obj.setCourt_state(row.getValue(offset + 56).asString());
            obj.setAssets_available(row.getValue(offset + 57).asString());
            obj.setPerfected_date(row.getValue(offset + 58).asString());
            obj.setAction_state_code(row.getValue(offset + 59).asString());
            obj.setAction_desc(row.getValue(offset + 60).asString());
            obj.setDisposition_state_code(row.getValue(offset + 61).asString());
            obj.setDisposition_desc(row.getValue(offset + 62).asString());
            obj.setAmount(row.getValue(offset + 63).asString());
            
            obj.setRelease_date(row.getValue(offset + 64).asString());
            obj.setRelease_number(row.getValue(offset + 65).asString());
            obj.setSuit_case_number(row.getValue(offset + 66).asString());
            obj.setSuit_date(row.getValue(offset + 67).asString());
            obj.setSuit_amount(row.getValue(offset + 68).asString());
            obj.setSatisfaction_date(row.getValue(offset + 69).asString());
            obj.setDischarge_date(row.getValue(offset + 70).asString());
            obj.setClosed_date(row.getValue(offset + 71).asString());
            obj.setTrust_deed_number(row.getValue(offset + 72).asString());
            obj.setTrust_deed_date(row.getValue(offset + 73).asString());
            obj.setSale_number(row.getValue(offset + 74).asString());
            obj.setSale_date(row.getValue(offset + 75).asString());
            obj.setCancellation_number(row.getValue(offset + 76).asString());
            obj.setCancellation_date(row.getValue(offset + 77).asString());
            obj.setSched_341_date(row.getValue(offset + 78).asString());
            obj.setUpdate_date(row.getValue(offset + 79).asString());
            
            //Udhay-Aug8 ==> New Tracers tag addedd
            obj.setFullname(row.getValue(offset + 80).asString());
            obj.setBusinessname(row.getValue(offset + 81).asString());
            obj.setDob(row.getValue(offset + 82).asString());
            obj.setCourt_address(row.getValue(offset + 83).asString());
            obj.setCourt_city(row.getValue(offset + 84).asString());
            obj.setCourt_zip(row.getValue(offset + 85).asString());
            obj.setCourt_phone(row.getValue(offset + 86).asString());
            //
            obj.setSched_341_time(row.getValue(offset + 87).asString());
            obj.setJudge(row.getValue(offset + 88).asString());
            obj.setLawfirm(row.getValue(offset + 89).asString());
            obj.setBook(row.getValue(offset + 90).asString());
            obj.setPage(row.getValue(offset + 91).asString());
            obj.setOrigdept(row.getValue(offset + 92).asString());
            obj.setOrigcase(row.getValue(offset + 93).asString());
            obj.setOrigbook(row.getValue(offset + 94).asString());
            obj.setOrigpage(row.getValue(offset + 95).asString());
            obj.setAssoccode(row.getValue(offset + 96).asString());
            obj.setActiontype(row.getValue(offset + 97).asString());
            obj.setActiontypedesc(row.getValue(offset + 98).asString());
            
        }
        catch (DataSetException e)
        {
            throw new TorqueException(e);
        }
    }

    /**
     * Method to do selects.
     *
     * @param criteria object used to create the SELECT statement.
     * @return List of selected Objects
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static List<NewBJLResults> doSelect(Criteria criteria) throws TorqueException
    {
        return populateObjects(doSelectVillageRecords(criteria));
    }

    /**
     * Method to do selects within a transaction.
     *
     * @param criteria object used to create the SELECT statement.
     * @param con the connection to use
     * @return List of selected Objects
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static List<NewBJLResults> doSelect(Criteria criteria, Connection con)
        throws TorqueException
    {
        return populateObjects(doSelectVillageRecords(criteria, con));
    }

    /**
     * Grabs the raw Village records to be formed into objects.
     * This method handles connections internally.  The Record objects
     * returned by this method should be considered readonly.  Do not
     * alter the data and call save(), your results may vary, but are
     * certainly likely to result in hard to track MT bugs.
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static List<Record> doSelectVillageRecords(Criteria criteria)
        throws TorqueException
    {
        return NewBaseBJLResultsPeer
            .doSelectVillageRecords(criteria, (Connection) null);
    }

    /**
     * Grabs the raw Village records to be formed into objects.
     * This method should be used for transactions
     *
     * @param criteria object used to create the SELECT statement.
     * @param con the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static List<Record> doSelectVillageRecords(Criteria criteria, Connection con)
        throws TorqueException
    {
        if (criteria.getSelectColumns().size() == 0)
        {
            addSelectColumns(criteria);
        }
        correctBooleans(criteria);

        setDbName(criteria);

        // BasePeer returns a List of Value (Village) arrays.  The array
        // order follows the order columns were placed in the Select clause.
        if (con == null)
        {
            return BasePeer.doSelect(criteria);
        }
        else
        {
            return BasePeer.doSelect(criteria, con);
        }
    }

    /**
     * The returned List will contain objects of the default type or
     * objects that inherit from the default.
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static List<NewBJLResults> populateObjects(List<Record> records)
        throws TorqueException
    {
        List<NewBJLResults> results = new ArrayList<NewBJLResults>(records.size());

        // populate the object(s)
        for (int i = 0; i < records.size(); i++)
        {
            Record row =  records.get(i);
              results.add(NewBJLResultsPeer.row2Object(row, 1,
                NewBJLResultsPeer.getOMClass()));
          }
        return results;
    }
 

    /**
     * The class that the Peer will make instances of.
     * If the BO is abstract then you must implement this method
     * in the BO.
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static Class getOMClass()
        throws TorqueException
    {
        return CLASS_DEFAULT;
    }

    /**
     * Method to do updates.
     *
     * @param criteria object containing data that is used to create the UPDATE
     *        statement.
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doUpdate(Criteria criteria) throws TorqueException
    {
         NewBaseBJLResultsPeer
            .doUpdate(criteria, (Connection) null);
    }

    /**
     * Method to do updates.  This method is to be used during a transaction,
     * otherwise use the doUpdate(Criteria) method.  It will take care of
     * the connection details internally.
     *
     * @param criteria object containing data that is used to create the UPDATE
     *        statement.
     * @param con the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doUpdate(Criteria criteria, Connection con)
        throws TorqueException
    {
        Criteria selectCriteria = new Criteria(DATABASE_NAME, 2);
        correctBooleans(criteria);  
        selectCriteria.put(RESULT_ID, criteria.remove(RESULT_ID));     
        setDbName(criteria);

        if (con == null)
        {
            BasePeer.doUpdate(selectCriteria, criteria);
        }
        else
        {
            BasePeer.doUpdate(selectCriteria, criteria, con);
        }
    }

    /**
     * Method to do deletes.
     *
     * @param criteria object containing data that is used DELETE from database.
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
     public static void doDelete(Criteria criteria) throws TorqueException
     {
         NewBJLResultsPeer
            .doDelete(criteria, (Connection) null);
     }

    /**
     * Method to do deletes.  This method is to be used during a transaction,
     * otherwise use the doDelete(Criteria) method.  It will take care of
     * the connection details internally.
     *
     * @param criteria object containing data that is used DELETE from database.
     * @param con the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
     public static void doDelete(Criteria criteria, Connection con)
        throws TorqueException
     {
        correctBooleans(criteria);

        setDbName(criteria);

        if (con == null)
        {
            BasePeer.doDelete(criteria);
        }
        else
        {
            BasePeer.doDelete(criteria, con);
        }
     }

    /**
     * Method to do selects
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static List<NewBJLResults> doSelect(NewBJLResults obj) throws TorqueException
    {
        return doSelect(buildSelectCriteria(obj));
    }

    /**
     * Method to do inserts
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doInsert(NewBJLResults obj) throws TorqueException
    {
          obj.setPrimaryKey(doInsert(buildCriteria(obj)));
          obj.setNew(false);
        obj.setModified(false);
    }

    /**
     * @param obj the data object to update in the database.
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doUpdate(NewBJLResults obj) throws TorqueException
    {
        doUpdate(buildCriteria(obj));
        obj.setModified(false);
    }

    /**
     * @param obj the data object to delete in the database.
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doDelete(NewBJLResults obj) throws TorqueException
    {
        doDelete(buildSelectCriteria(obj));
    }

    /**
     * Method to do inserts.  This method is to be used during a transaction,
     * otherwise use the doInsert(NewBJLResults) method.  It will take
     * care of the connection details internally.
     *
     * @param obj the data object to insert into the database.
     * @param con the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doInsert(NewBJLResults obj, Connection con)
        throws TorqueException
    {
          obj.setPrimaryKey(doInsert(buildCriteria(obj), con));
          obj.setNew(false);
        obj.setModified(false);
    }

    /**
     * Method to do update.  This method is to be used during a transaction,
     * otherwise use the doUpdate(NewBJLResults) method.  It will take
     * care of the connection details internally.
     *
     * @param obj the data object to update in the database.
     * @param con the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doUpdate(NewBJLResults obj, Connection con)
        throws TorqueException
    {
        doUpdate(buildCriteria(obj), con);
        obj.setModified(false);
    }

    /**
     * Method to delete.  This method is to be used during a transaction,
     * otherwise use the doDelete(NewBJLResults) method.  It will take
     * care of the connection details internally.
     *
     * @param obj the data object to delete in the database.
     * @param con the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doDelete(NewBJLResults obj, Connection con)
        throws TorqueException
    {
        doDelete(buildSelectCriteria(obj), con);
    }

    /**
     * Method to do deletes.
     *
     * @param pk ObjectKey that is used DELETE from database.
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doDelete(ObjectKey pk) throws TorqueException
    {
        NewBaseBJLResultsPeer
           .doDelete(pk, (Connection) null);
    }

    /**
     * Method to delete.  This method is to be used during a transaction,
     * otherwise use the doDelete(ObjectKey) method.  It will take
     * care of the connection details internally.
     *
     * @param pk the primary key for the object to delete in the database.
     * @param con the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doDelete(ObjectKey pk, Connection con)
        throws TorqueException
    {
        doDelete(buildCriteria(pk), con);
    }

    /** Build a Criteria object from an ObjectKey */
    public static Criteria buildCriteria( ObjectKey pk )
    {
        Criteria criteria = new Criteria();
              criteria.add(RESULT_ID, pk);
          return criteria;
     }

    /** Build a Criteria object from the data object for this peer */
    public static Criteria buildCriteria( NewBJLResults obj )
    {
        Criteria criteria = new Criteria(DATABASE_NAME);
              if (!obj.isNew())
              criteria.add(RESULT_ID, obj.getResultId());
              criteria.add(USER_SEARCH_ID, obj.getUserSearchId());
              criteria.add(NAME_FILING_STATE, obj.getName_filing_state());
              criteria.add(FIRSTNAME, obj.getFirstname());
              criteria.add(MIDDLENAME, obj.getMiddlename());
              criteria.add(LASTNAME, obj.getLastname());
              criteria.add(NAME_SUFFIX, obj.getName_suffix());
              criteria.add(NAME_TYPE, obj.getName_type());
              criteria.add(NAME_TYPE_DESC, obj.getName_type_desc());
              criteria.add(SSN_TAXID_FLAG, obj.getSsn_taxid_flag());
              criteria.add(SSN, obj.getSsn());
              criteria.add(COMMENT_SEQUENCE, obj.getComment_sequence());
              criteria.add(COMMENT_TYPE, obj.getComment_type());
              criteria.add(COMMENT_TYPE_DESC, obj.getComment_type_desc());
              criteria.add(HOUSE_NUMBER, obj.getHouse_number());
              criteria.add(STREET_DIRECTION, obj.getStreet_direction());
              criteria.add(STREET_NAME, obj.getStreet_name());
              criteria.add(STREET_SUFFIX, obj.getStreet_suffix());
              criteria.add(APARTMENT_NUM, obj.getApartment_num());
              criteria.add(CITY, obj.getCity());
              criteria.add(STATE, obj.getState());
              criteria.add(ZIPCODE, obj.getZipcode());
              criteria.add(FILING_STATE, obj.getFiling_state());
              criteria.add(FILING_GROUP, obj.getFiling_group());
              criteria.add(FILING_GROUP_DESC, obj.getFiling_group_desc());
              criteria.add(FILING_TYPE, obj.getFiling_type());
              criteria.add(FILING_TYPE_DESC, obj.getFiling_type_desc());
              criteria.add(FILING_DATE, obj.getFiling_date());
              criteria.add(FILING_NUMBER, obj.getFiling_number());
              criteria.add(TAX_LIEN_TYPE, obj.getTax_lien_type());
              criteria.add(TAX_LIEN_TYPE_DESC, obj.getTax_lien_type_desc());
              criteria.add(BANKRUPTCY_TYPE, obj.getBankruptcy_type());
              criteria.add(BANKRUPTCY_TYPE_DESC, obj.getBankruptcy_type_desc());
              criteria.add(DOCKET_NUMBER, obj.getDocket_number());
              criteria.add(UNLAWFULl_DETAINER, obj.getUnlawful_detainer());
              criteria.add(INITIAL_DATE, obj.getInitial_date());
              criteria.add(INITIAL_AMOUNT, obj.getInitial_amount());
              criteria.add(INITIAL_DOCKET, obj.getInitial_docket());
              criteria.add(JUDGEMENT_DATE, obj.getJudgement_date());
              criteria.add(JUDGEMENT_AMOUNT, obj.getJudgement_amount());
              criteria.add(JUDGEMENT_DOCKET, obj.getJudgement_docket());
              criteria.add(REMOVE_DATE, obj.getRemove_date());
              criteria.add(REMOVE_DOCKET, obj.getRemove_docket());
              criteria.add(DISMISSAL_DATE, obj.getDismissal_date());
              criteria.add(DISMISSAL_DOCKET, obj.getDismissal_docket());
              criteria.add(ASSET_AMOUNT, obj.getAsset_amount());
              criteria.add(LIABILITY_AMOUNT,obj.getLiability_amount());
              criteria.add(PLAINTIFF, obj.getPlaintiff());
              criteria.add(BENEFICIARY, obj.getBeneficiary());
              criteria.add(SITUS, obj.getSitus());
              criteria.add(TRUSTEE, obj.getTrustee());
              criteria.add(COUNTY_CODE, obj.getCounty_code());
              criteria.add(COUNTY_NAME, obj.getCounty_name());
              criteria.add(COUNTY_STATE, obj.getCounty_state());
              criteria.add(COURT_CODE, obj.getCourt_code());
              criteria.add(COURT_DESC, obj.getCourt_desc());
              criteria.add(COURT_STATE, obj.getCourt_state());
              criteria.add(ASSETS_AVAILABLE, obj.getAssets_available());;
              criteria.add(PERFECTED_DATE, obj.getPerfected_date());
              criteria.add(ACTION_STATE_CODE, obj.getAction_state_code());
              criteria.add(ACTION_DESC, obj.getAction_desc());
              criteria.add(DISPOSITION_STATE_CODE, obj.getDisposition_state_code());
              criteria.add(DISPOSITION_DESC, obj.getDisposition_desc());
              criteria.add(AMOUNT, obj.getAmount());
              criteria.add(RELEASE_DATE, obj.getRelease_date());
              criteria.add(RELEASE_NUMBER, obj.getRelease_number());
              criteria.add(SUIT_CASE_NUMBER, obj.getSuit_case_number());;
              criteria.add(SUIT_DATE, obj.getSuit_date());
              criteria.add(SUIT_AMOUNT, obj.getSuit_amount());
              criteria.add(SATISFACTION_DATE, obj.getSatisfaction_date());
              criteria.add(DISCHARGE_DATE, obj.getDischarge_date());
              criteria.add(CLOSED_DATE, obj.getClosed_date());
              criteria.add(TRUST_DEED_NUMBER, obj.getTrust_deed_number());
              criteria.add(TRUST_DEED_DATE, obj.getTrust_deed_date());
              criteria.add(SALE_NUMBER, obj.getSale_number());;
              criteria.add(SALE_DATE, obj.getSale_date());
              criteria.add(CANCELLATION_NUMBER, obj.getCancellation_number());
              criteria.add(CANCELLATION_DATE, obj.getCancellation_date());
              criteria.add(SCHED_341_DATE, obj.getSched_341_date());
              criteria.add(UPDATE_DATE, obj.getUpdate_date());
              //Udhay-Aug8 ==> New Tracers fields added
              criteria.add(FULLNAME, obj.getFullname());
              criteria.add(BUSINESSNAME, obj.getBusinessname());
              criteria.add(DOB, obj.getDob());
              criteria.add(COURT_ADDRESS, obj.getCourt_address());
              criteria.add(COURT_CITY, obj.getCourt_city());
              criteria.add(COURT_ZIP, obj.getCourt_zip());
              criteria.add(COURT_PHONE, obj.getCourt_phone());
              //
              criteria.add(SCHED_341_TIME, obj.getSched_341_time());
              criteria.add(JUDGE, obj.getJudge());
              criteria.add(LAWFIRM, obj.getLawfirm());
              criteria.add(BOOK, obj.getBook());
              criteria.add(PAGE, obj.getPage());
              criteria.add(ORIGDEPT, obj.getOrigdept());
              criteria.add(ORIGCASE, obj.getOrigcase());
              criteria.add(ORIGBOOK, obj.getOrigbook());
              criteria.add(ORIGPAGE, obj.getOrigpage());
              criteria.add(ASSOCCODE, obj.getAssoccode());
              criteria.add(ACTIONTYPE, obj.getActiontype());
              criteria.add(ACTIONTYPEDESC, obj.getActiontypedesc());

          return criteria;
    }

    /** Build a Criteria object from the data object for this peer, skipping all binary columns */
    public static Criteria buildSelectCriteria( NewBJLResults obj )
    {
        Criteria criteria = new Criteria(DATABASE_NAME);
              if (!obj.isNew())
        {
                    criteria.add(RESULT_ID, obj.getResultId());
                }
              criteria.add(USER_SEARCH_ID, obj.getUserSearchId());
              criteria.add(NAME_FILING_STATE, obj.getName_filing_state());
              criteria.add(FIRSTNAME, obj.getFirstname());
              criteria.add(MIDDLENAME, obj.getMiddlename());
              criteria.add(LASTNAME, obj.getLastname());
              criteria.add(NAME_SUFFIX, obj.getName_suffix());
              criteria.add(NAME_TYPE, obj.getName_type());
              criteria.add(NAME_TYPE_DESC, obj.getName_type_desc());
              criteria.add(SSN_TAXID_FLAG, obj.getSsn_taxid_flag());
              criteria.add(SSN, obj.getSsn());
              criteria.add(COMMENT_SEQUENCE, obj.getComment_sequence());
              criteria.add(COMMENT_TYPE, obj.getComment_type());
              criteria.add(COMMENT_TYPE_DESC, obj.getComment_type_desc());
              criteria.add(HOUSE_NUMBER, obj.getHouse_number());
              criteria.add(STREET_DIRECTION, obj.getStreet_direction());
              criteria.add(STREET_NAME, obj.getStreet_name());
              criteria.add(STREET_SUFFIX, obj.getStreet_suffix());
              criteria.add(APARTMENT_NUM, obj.getApartment_num());
              criteria.add(CITY, obj.getCity());
              criteria.add(STATE, obj.getState());
              criteria.add(ZIPCODE, obj.getZipcode());
              criteria.add(FILING_STATE, obj.getFiling_state());
              criteria.add(FILING_GROUP, obj.getFiling_group());
              criteria.add(FILING_GROUP_DESC, obj.getFiling_group_desc());
              criteria.add(FILING_TYPE, obj.getFiling_type());
              criteria.add(FILING_TYPE_DESC, obj.getFiling_type_desc());
              criteria.add(FILING_DATE, obj.getFiling_date());
              criteria.add(FILING_NUMBER, obj.getFiling_number());
              criteria.add(TAX_LIEN_TYPE, obj.getTax_lien_type());
              criteria.add(TAX_LIEN_TYPE_DESC, obj.getTax_lien_type_desc());
              criteria.add(BANKRUPTCY_TYPE, obj.getBankruptcy_type());
              criteria.add(BANKRUPTCY_TYPE_DESC, obj.getBankruptcy_type_desc());
              criteria.add(DOCKET_NUMBER, obj.getDocket_number());
              criteria.add(UNLAWFULl_DETAINER, obj.getUnlawful_detainer());
              criteria.add(INITIAL_DATE, obj.getInitial_date());
              criteria.add(INITIAL_AMOUNT, obj.getInitial_amount());
              criteria.add(INITIAL_DOCKET, obj.getInitial_docket());
              criteria.add(JUDGEMENT_DATE, obj.getJudgement_date());
              criteria.add(JUDGEMENT_AMOUNT, obj.getJudgement_amount());
              criteria.add(JUDGEMENT_DOCKET, obj.getJudgement_docket());
              criteria.add(REMOVE_DATE, obj.getRemove_date());
              criteria.add(REMOVE_DOCKET, obj.getRemove_docket());
              criteria.add(DISMISSAL_DATE, obj.getDismissal_date());
              criteria.add(DISMISSAL_DOCKET, obj.getDismissal_docket());
              criteria.add(ASSET_AMOUNT, obj.getAsset_amount());
              criteria.add(LIABILITY_AMOUNT,obj.getLiability_amount());
              criteria.add(PLAINTIFF, obj.getPlaintiff());
              criteria.add(BENEFICIARY, obj.getBeneficiary());
              criteria.add(SITUS, obj.getSitus());
              criteria.add(TRUSTEE, obj.getTrustee());
              criteria.add(COUNTY_CODE, obj.getCounty_code());
              criteria.add(COUNTY_NAME, obj.getCounty_name());
              criteria.add(COUNTY_STATE, obj.getCounty_state());
              criteria.add(COURT_CODE, obj.getCourt_code());
              criteria.add(COURT_DESC, obj.getCourt_desc());
              criteria.add(COURT_STATE, obj.getCourt_state());
              criteria.add(ASSETS_AVAILABLE, obj.getAssets_available());;
              criteria.add(PERFECTED_DATE, obj.getPerfected_date());
              criteria.add(ACTION_STATE_CODE, obj.getAction_state_code());
              criteria.add(ACTION_DESC, obj.getAction_desc());
              criteria.add(DISPOSITION_STATE_CODE, obj.getDisposition_state_code());
              criteria.add(DISPOSITION_DESC, obj.getDisposition_desc());
              criteria.add(AMOUNT, obj.getAmount());
              criteria.add(RELEASE_DATE, obj.getRelease_date());
              criteria.add(RELEASE_NUMBER, obj.getRelease_number());
              criteria.add(SUIT_CASE_NUMBER, obj.getSuit_case_number());;
              criteria.add(SUIT_DATE, obj.getSuit_date());
              criteria.add(SUIT_AMOUNT, obj.getSuit_amount());
              criteria.add(SATISFACTION_DATE, obj.getSatisfaction_date());
              criteria.add(DISCHARGE_DATE, obj.getDischarge_date());
              criteria.add(CLOSED_DATE, obj.getClosed_date());
              criteria.add(TRUST_DEED_NUMBER, obj.getTrust_deed_number());
              criteria.add(TRUST_DEED_DATE, obj.getTrust_deed_date());
              criteria.add(SALE_NUMBER, obj.getSale_number());;
              criteria.add(SALE_DATE, obj.getSale_date());
              criteria.add(CANCELLATION_NUMBER, obj.getCancellation_number());
              criteria.add(CANCELLATION_DATE, obj.getCancellation_date());
              criteria.add(SCHED_341_DATE, obj.getSched_341_date());
              criteria.add(UPDATE_DATE, obj.getUpdate_date());
              //Udhay-Aug8 ==> New Tracers fields added
              criteria.add(FULLNAME, obj.getFullname());
              criteria.add(BUSINESSNAME, obj.getBusinessname());
              criteria.add(DOB, obj.getDob());
              criteria.add(COURT_ADDRESS, obj.getCourt_address());
              criteria.add(COURT_CITY, obj.getCourt_city());
              criteria.add(COURT_ZIP, obj.getCourt_zip());
              criteria.add(COURT_PHONE, obj.getCourt_phone());
              //
              criteria.add(SCHED_341_TIME, obj.getSched_341_time());
              criteria.add(JUDGE, obj.getJudge());
              criteria.add(LAWFIRM, obj.getLawfirm());
              criteria.add(BOOK, obj.getBook());
              criteria.add(PAGE, obj.getPage());
              criteria.add(ORIGDEPT, obj.getOrigdept());
              criteria.add(ORIGCASE, obj.getOrigcase());
              criteria.add(ORIGBOOK, obj.getOrigbook());
              criteria.add(ORIGPAGE, obj.getOrigpage());
              criteria.add(ASSOCCODE, obj.getAssoccode());
              criteria.add(ACTIONTYPE, obj.getActiontype());
              criteria.add(ACTIONTYPEDESC, obj.getActiontypedesc());
              
                  return criteria;
    }
 
    
        /**
     * Retrieve a single object by pk
     *
     * @param pk the primary key
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     * @throws NoRowsException Primary key was not found in database.
     * @throws TooManyRowsException Primary key was not found in database.
     */
    public static NewBJLResults retrieveByPK(long pk)
        throws TorqueException, NoRowsException, TooManyRowsException
    {
        return retrieveByPK(SimpleKey.keyFor(pk));
    }

    /**
     * Retrieve a single object by pk
     *
     * @param pk the primary key
     * @param con the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     * @throws NoRowsException Primary key was not found in database.
     * @throws TooManyRowsException Primary key was not found in database.
     */
    public static NewBJLResults retrieveByPK(long pk, Connection con)
        throws TorqueException, NoRowsException, TooManyRowsException
    {
        return retrieveByPK(SimpleKey.keyFor(pk), con);
    }
  
    /**
     * Retrieve a single object by pk
     *
     * @param pk the primary key
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     * @throws NoRowsException Primary key was not found in database.
     * @throws TooManyRowsException Primary key was not found in database.
     */
    public static NewBJLResults retrieveByPK(ObjectKey pk)
        throws TorqueException, NoRowsException, TooManyRowsException
    {
        Connection db = null;
        NewBJLResults retVal = null;
        try
        {
            db = Torque.getConnection(DATABASE_NAME);
            retVal = retrieveByPK(pk, db);
        }
        finally
        {
            Torque.closeConnection(db);
        }
        return retVal;
    }

    /**
     * Retrieve a single object by pk
     *
     * @param pk the primary key
     * @param con the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     * @throws NoRowsException Primary key was not found in database.
     * @throws TooManyRowsException Primary key was not found in database.
     */
    public static NewBJLResults retrieveByPK(ObjectKey pk, Connection con)
        throws TorqueException, NoRowsException, TooManyRowsException
    {
        Criteria criteria = buildCriteria(pk);
        List<NewBJLResults> v = doSelect(criteria, con);
        if (v.size() == 0)
        {
            throw new NoRowsException("Failed to select a row.");
        }
        else if (v.size() > 1)
        {
            throw new TooManyRowsException("Failed to select only one row.");
        }
        else
        {
            return (NewBJLResults)v.get(0);
        }
    }

    /**
     * Retrieve a multiple objects by pk
     *
     * @param pks List of primary keys
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static List<NewBJLResults> retrieveByPKs(List<ObjectKey> pks)
        throws TorqueException
    {
        Connection db = null;
        List<NewBJLResults> retVal = null;
        try
        {
           db = Torque.getConnection(DATABASE_NAME);
           retVal = retrieveByPKs(pks, db);
        }
        finally
        {
            Torque.closeConnection(db);
        }
        return retVal;
    }

    /**
     * Retrieve a multiple objects by pk
     *
     * @param pks List of primary keys
     * @param dbcon the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static List<NewBJLResults> retrieveByPKs( List<ObjectKey> pks, Connection dbcon )
        throws TorqueException
    {
        List<NewBJLResults> objs = null;
        if (pks == null || pks.size() == 0)
        {
            objs = new LinkedList<NewBJLResults>();
        }
        else
        {
            Criteria criteria = new Criteria();
              criteria.addIn( RESULT_ID, pks );
          objs = doSelect(criteria, dbcon);
        }
        return objs;
    }

    /**
     * selects a collection of NewBJLResults objects pre-filled with their
     * BjlSearches objects.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in NewBJLResultsPeer.
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    protected static List<NewBJLResults> doSelectJoinBjlSearches(Criteria criteria)
        throws TorqueException
    {
        return doSelectJoinBjlSearches(criteria, null);
    }

    /**
     * selects a collection of NewBJLResults objects pre-filled with their
     * BjlSearches objects.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in NewBJLResultsPeer.
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    protected static List<NewBJLResults> doSelectJoinBjlSearches(Criteria criteria, Connection conn)
        throws TorqueException
    {
        setDbName(criteria);

        NewBJLResultsPeer.addSelectColumns(criteria);
        int offset = numColumns + 1;
        NewBJLSearchesPeer.addSelectColumns(criteria);

                        criteria.addJoin(NewBJLResultsPeer.USER_SEARCH_ID,
                        		NewBJLSearchesPeer.USER_SEARCH_ID);
        
        correctBooleans(criteria);

        List<Record> rows;
        if (conn == null)
        {
            rows = BasePeer.doSelect(criteria);
        }
        else
        {
            rows = BasePeer.doSelect(criteria,conn);
        }

        List<NewBJLResults> results = new ArrayList<NewBJLResults>();

        for (int i = 0; i < rows.size(); i++)
        {
            Record row =  rows.get(i);

                            Class omClass = NewBJLResultsPeer.getOMClass();
                    NewBJLResults obj1 = NewBJLResultsPeer
                .row2Object(row, 1, omClass);
                     omClass = NewBJLSearchesPeer.getOMClass();
                    NewBJLSearches obj2 = NewBJLSearchesPeer
                .row2Object(row, offset, omClass);

            boolean newObject = true;
            for (int j = 0; j < results.size(); j++)
            {
                NewBJLResults temp_obj1 =  results.get(j);
                NewBJLSearches temp_obj2 = temp_obj1.getBjlSearches();
                if (temp_obj2.getPrimaryKey().equals(obj2.getPrimaryKey()))
                {
                    newObject = false;
                              temp_obj2.addBjlResults(obj1);
                              break;
                }
            }
                      if (newObject)
            {
                obj2.initBjlResultss();
                obj2.addBjlResults(obj1);
            }
                      results.add(obj1);
        }
        return results;
    }
                    
  
    
  
      /**
     * Returns the TableMap related to this peer.
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static TableMap getTableMap()
        throws TorqueException
    {
        return Torque.getDatabaseMap(DATABASE_NAME).getTable(TABLE_NAME);
    }
   
    private static void setDbName(Criteria crit)
    {
        // Set the correct dbName if it has not been overridden
        // crit.getDbName will return the same object if not set to
        // another value so == check is okay and faster
        if (crit.getDbName() == Torque.getDefaultDB())
        {
            crit.setDbName(DATABASE_NAME);
        }
    }
    

    // The following methods wrap some methods in BasePeer
    // to have more support for Java5 generic types in the Peer
    
    /**
     * Utility method which executes a given sql statement.  This
     * method should be used for select statements only.  Use
     * executeStatement for update, insert, and delete operations.
     *
     * @param queryString A String with the sql statement to execute.
     * @return List of Record objects.
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     * @see org.apache.torque.util.BasePeer#executeQuery(String)
     */
    public static List<Record> executeQuery(String queryString) throws TorqueException
    {
        return BasePeer.executeQuery(queryString);
    }

    /**
     * Utility method which executes a given sql statement.  This
     * method should be used for select statements only.  Use
     * executeStatement for update, insert, and delete operations.
     *
     * @param queryString A String with the sql statement to execute.
     * @param dbName The database to connect to.
     * @return List of Record objects.
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     * @see org.apache.torque.util.BasePeer#executeQuery(String,String)
     */
    public static List<Record> executeQuery(String queryString, String dbName)
        throws TorqueException
    {
        return BasePeer.executeQuery(queryString,dbName);
    }
    

    /**
     * Method for performing a SELECT.  Returns all results.
     *
     * @param queryString A String with the sql statement to execute.
     * @param dbName The database to connect to.
     * @param singleRecord Whether or not we want to select only a
     * single record.
     * @return List of Record objects.
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     * @see org.apache.torque.util.BasePeer#executeQuery(String,String,boolean)
     */
    public static List<Record> executeQuery(
        String queryString,
        String dbName,
        boolean singleRecord)
        throws TorqueException
    {
        return BasePeer.executeQuery(queryString,dbName,singleRecord);
    }

    /**
     * Method for performing a SELECT.  Returns all results.
     *
     * @param queryString A String with the sql statement to execute.
     * @param singleRecord Whether or not we want to select only a
     * single record.
     * @param con A Connection.
     * @return List of Record objects.
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     * @see org.apache.torque.util.BasePeer#executeQuery(String,boolean,Connection)
     */
    public static List<Record> executeQuery(
        String queryString,
        boolean singleRecord,
        Connection con)
        throws TorqueException
    {
        return BasePeer.executeQuery(queryString,singleRecord,con);
    }

    /**
     * Method for performing a SELECT.
     *
     * @param queryString A String with the sql statement to execute.
     * @param start The first row to return.
     * @param numberOfResults The number of rows to return.
     * @param dbName The database to connect to.
     * @param singleRecord Whether or not we want to select only a
     * single record.
     * @return List of Record objects.
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     * @see org.apache.torque.util.BasePeer#executeQuery(String,int,int,String,boolean)
     */
    public static List<Record> executeQuery(
        String queryString,
        int start,
        int numberOfResults,
        String dbName,
        boolean singleRecord)
        throws TorqueException
    {
        return BasePeer.executeQuery(queryString,start,numberOfResults,dbName,singleRecord);
    }

    /**
     * Method for performing a SELECT.  Returns all results.
     *
     * @param queryString A String with the sql statement to execute.
     * @param start The first row to return.
     * @param numberOfResults The number of rows to return.
     * @param singleRecord Whether or not we want to select only a
     * single record.
     * @param con A Connection.
     * @return List of Record objects.
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     * @see org.apache.torque.util.BasePeer#executeQuery(String,int,int,String,boolean,Connection)
     */
    public static List<Record> executeQuery(
        String queryString,
        int start,
        int numberOfResults,
        boolean singleRecord,
        Connection con)
        throws TorqueException
    {
        return BasePeer.executeQuery(queryString,start,numberOfResults,singleRecord,con);
    }

    /**
     * Returns all records in a QueryDataSet as a List of Record
     * objects.  Used for functionality like util.LargeSelect.
     *
     * @see #getSelectResults(QueryDataSet, int, int, boolean)
     * @param qds the QueryDataSet
     * @return a List of Record objects
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     * @see org.apache.torque.util.BasePeer#getSelectResults(QueryDataSet)
     */
    public static List<Record> getSelectResults(QueryDataSet qds)
        throws TorqueException
    {
        return BasePeer.getSelectResults(qds);
    }
    
    /**
     * Returns all records in a QueryDataSet as a List of Record
     * objects.  Used for functionality like util.LargeSelect.
     *
     * @see #getSelectResults(QueryDataSet, int, int, boolean)
     * @param qds the QueryDataSet
     * @param singleRecord
     * @return a List of Record objects
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     * @see org.apache.torque.util.BasePeer#getSelectResults(QueryDataSet,boolean)
     */
    public static List<Record> getSelectResults(QueryDataSet qds, boolean singleRecord)
        throws TorqueException
    {
        return BasePeer.getSelectResults(qds,singleRecord);
    }
    
    /**
     * Returns numberOfResults records in a QueryDataSet as a List
     * of Record objects.  Starting at record 0.  Used for
     * functionality like util.LargeSelect.
     *
     * @see #getSelectResults(QueryDataSet, int, int, boolean)
     * @param qds the QueryDataSet
     * @param numberOfResults
     * @param singleRecord
     * @return a List of Record objects
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     * @see org.apache.torque.util.BasePeer#getSelectResults(QueryDataSet,int,boolean)
     */
    public static List<Record> getSelectResults(
        QueryDataSet qds,
        int numberOfResults,
        boolean singleRecord)
        throws TorqueException
    {
        return BasePeer.getSelectResults(qds,numberOfResults,singleRecord);
    }

    /**
     * Returns numberOfResults records in a QueryDataSet as a List
     * of Record objects.  Starting at record start.  Used for
     * functionality like util.LargeSelect.
     *
     * @param qds The <code>QueryDataSet</code> to extract results
     * from.
     * @param start The index from which to start retrieving
     * <code>Record</code> objects from the data set.
     * @param numberOfResults The number of results to return (or
     * <code> -1</code> for all results).
     * @param singleRecord Whether or not we want to select only a
     * single record.
     * @return A <code>List</code> of <code>Record</code> objects.
     * @exception TorqueException If any <code>Exception</code> occurs.
     * @see org.apache.torque.util.BasePeer#getSelectResults(QueryDataSet,int,int,boolean)
     */
    public static List getSelectResults(
        QueryDataSet qds,
        int start,
        int numberOfResults,
        boolean singleRecord)
        throws TorqueException
    {
        return BasePeer.getSelectResults(qds,start,numberOfResults,singleRecord);
    }

    /**
     * Performs a SQL <code>select</code> using a PreparedStatement.
     * Note: this method does not handle null criteria values.
     *
     * @param criteria
     * @param con
     * @return a List of Record objects.
     * @throws TorqueException Error performing database query.
     * @see org.apache.torque.util.BasePeer#doPSSelect(Criteria,Connection)
     */
    public static List<Record> doPSSelect(Criteria criteria, Connection con)
        throws TorqueException
    {
        return BasePeer.doPSSelect(criteria,con);
    }

    /**
     * Do a Prepared Statement select according to the given criteria
     *
     * @param criteria
     * @return a List of Record objects.
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     * @see org.apache.torque.util.BasePeer#doPSSelect(Criteria)
     */
    public static List<Record> doPSSelect(Criteria criteria) throws TorqueException
    {
        return BasePeer.doPSSelect(criteria);
    }

}
