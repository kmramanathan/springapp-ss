package springapp.domain.eviction;

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

import springapp.domain.eviction.map.EvictionResultsMapBuilder;


import com.workingdogs.village.DataSetException;
import com.workingdogs.village.QueryDataSet;
import com.workingdogs.village.Record;

public abstract class BaseEvictionResultsPeer extends BasePeer {
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
    	return getMapBuilder(EvictionResultsMapBuilder.CLASS_NAME);
    }
    /** the column name for the result_id field */
    public static final String RESULT_ID;
      /** the column name for the user_search_id field */
    public static final String USER_SEARCH_ID;
	public static final String TIS_SIG_CASE;
	public static final String TIS_VENDOR_CODE;
	public static final String CASE_NUMBER;
	public static final String CASE_SEQUENCE_NUMBER;
	public static final String CASE_YEAR_DATE;
	public static final String BOOK_NUMBER;
	public static final String PAGE_NUMBER;
	public static final String UNIQUE_ID;
	public static final String RESULT_CODE;
	public static final String RESULT_DESC;
	public static final String RESULT_DATE;
	public static final String FILE_DATE;
	public static final String INPUT_BY;
	public static final String INPUT_DATE;
	public static final String EXECUTED_DATE;
	public static final String RECEIVED_DATE;
	public static final String SATISFIED_DATE;
	public static final String VERIFIED_DATE;
	public static final String SOURCE_ID;
	public static final String RECORD_STATUS;
	public static final String JUDGEMENT_AMOUNT;
	public static final String JUDGEMENT_FLAG;
	public static final String STATE_ABBR;
	public static final String COURT_TYPE_CODE;
	
	public static final String COURT_TYPE_DESC;
	public static final String COURTHOUSE_SUB_CODE;
	public static final String COUNTY_FIPS_CODE;
	public static final String COUNTY_FIPS_DESC;
	public static final String STATE_FIPS_CODE;
	public static final String STATE_FIPS_DESC;
	public static final String AFFILIATE;
	public static final String POSSESSION_FLAG;
	public static final String POSSESSION_FLAG_DESC;
	public static final String REGION;
	/** EVICTIONS_DEFENDANT_LISTE*/
	public static final String TIS_SIG_DEFENDANT;
	public static final String DEFENDANT_NUMBER;
	public static final String D_COUNTY_FIPS_CODE;
	public static final String D_COUNTY_FIPS_DESC;
	public static final String D_STATE_FIPS_CODE;
	public static final String D_STATE_FIPS_DESC;
	public static final String D_SSN;
	public static final String D_FULLNAME;
	public static final String D_FIRSTNAME;
	public static final String D_MIDDLENAME;
	public static final String D_LASTNAME;
	public static final String D_SUFFIX;
	public static final String D_ALIAS_FLAG;
	public static final String D_ALIAS_FIRSTNAME;
	public static final String D_ALIAS_MIDDLENAME;
	public static final String D_ALIAS_LASTNAME;
	public static final String D_ALIAS_SUFFIX;
	public static final String D_BIRTH_DATE;
	public static final String D_ADDRESS_LINE_1;
	public static final String D_ADDRESS_LINE_2;
	public static final String D_APARTMENT_NUMBER;
	
	public static final String D_CITY;
	public static final String D_STATE;
	public static final String D_ZIPCODE;
	public static final String D_PHONE;
	public static final String D_CLEAN_AREA_CODE;
	public static final String D_CLEAN_PHONE_NUMBER;
	
	/** EVICTIONS_PLAINTIFF_LIST */
	
	public static final String TIS_SIG_PLAINTIFF;
	public static final String PLAINTIFF_TYPE;
	public static final String PLAINTIFF_DESC;
	public static final String P_COUNTY_FIPS_CODE;
	public static final String P_COUNTY_FIPS_DESC;
	public static final String P_STATE_FIPS_CODE;
	public static final String P_STATE_FIPS_DESC;
	public static final String P_FULLNAME;
	public static final String P_FIRSTNAME;
	public static final String P_MIDDLENAME;
	public static final String P_LASTNAME;
	public static final String P_ADDRESS_LINE_1;
	public static final String P_ADDRESS_LINE_2;
	public static final String P_CITY;
	public static final String P_STATE;
	public static final String P_ZIPCODE;
	public static final String P_PHONE_NUMBER;
	public static final String P_CLEAN_AREA_CODE;
	public static final String P_CLEAN_PHONE_NUMBER;
	
	/** COURTINFO*/
	
	public static final String NAME;
	public static final String ADDRESS1;
	public static final String ADDRESS2;
	public static final String CITY;
	public static final String STATE;
	public static final String ZIP;
    static
    {
        DATABASE_NAME = "services";
        TABLE_NAME = "eviction_results";

        RESULT_ID = "eviction_results.result_id";
        USER_SEARCH_ID = "eviction_results.user_search_id";
        TIS_SIG_CASE = "eviction_results.tis_sig_case";
        TIS_VENDOR_CODE = "eviction_results.tis_vendor_code";
        CASE_NUMBER = "eviction_results.case_number";
        CASE_SEQUENCE_NUMBER = "eviction_results.case_sequence_number";
        CASE_YEAR_DATE = "eviction_results.case_year_date";
        BOOK_NUMBER = "eviction_results.book_number";
        PAGE_NUMBER = "eviction_results.page_number";
        UNIQUE_ID = "eviction_results.unique_id";
        RESULT_CODE = "eviction_results.result_code";
        RESULT_DESC = "eviction_results.result_desc";
        RESULT_DATE = "eviction_results.result_date";
        FILE_DATE = "eviction_results.file_date";
        INPUT_BY = "eviction_results.input_by";
        INPUT_DATE = "eviction_results.input_date";
        EXECUTED_DATE = "eviction_results.executed_date";
        RECEIVED_DATE = "eviction_results.received_date";
        SATISFIED_DATE = "eviction_results.satisfied_date";
        VERIFIED_DATE = "eviction_results.verified_date";
        SOURCE_ID = "eviction_results.source_id";
        RECORD_STATUS = "eviction_results.record_status";
        JUDGEMENT_AMOUNT = "eviction_results.judgement_amount";
        JUDGEMENT_FLAG = "eviction_results.judgement_flag";
        STATE_ABBR = "eviction_results.state_abbr";
        COURT_TYPE_CODE = "eviction_results.court_type_code";
        COURT_TYPE_DESC = "eviction_results.court_type_desc";
        COURTHOUSE_SUB_CODE = "eviction_results.courthouse_sub_code";
        COUNTY_FIPS_CODE = "eviction_results.county_fips_code";
        COUNTY_FIPS_DESC = "eviction_results.county_fips_desc";
        STATE_FIPS_CODE = "eviction_results.state_fips_code";
        STATE_FIPS_DESC = "eviction_results.state_fips_desc";
        AFFILIATE = "eviction_results.affiliate";
        POSSESSION_FLAG = "eviction_results.possession_flag";
        POSSESSION_FLAG_DESC = "eviction_results.possession_flag_desc";
        REGION = "eviction_results.region";
        TIS_SIG_DEFENDANT = "eviction_results.tis_sig_defendant";
        DEFENDANT_NUMBER = "eviction_results.defendant_number";
        D_COUNTY_FIPS_CODE = "eviction_results.d_county_fips_code";
        D_COUNTY_FIPS_DESC = "eviction_results.d_county_fips_desc";
        D_STATE_FIPS_CODE = "eviction_results.d_state_fips_code";
        D_STATE_FIPS_DESC = "eviction_results.d_state_fips_desc";
        D_SSN = "eviction_results.d_ssn";
        D_FULLNAME = "eviction_results.d_fullname";
        D_FIRSTNAME = "eviction_results.d_firstname";
        D_MIDDLENAME="eviction_results.d_middlename";
        D_LASTNAME="eviction_results.d_lastname";
        D_SUFFIX="eviction_results.d_suffix";
        D_ALIAS_FLAG="eviction_results.d_alias_flag";
        D_ALIAS_FIRSTNAME="eviction_results.d_alias_firstname";
        D_ALIAS_MIDDLENAME="eviction_results.d_alias_middlename";
        D_ALIAS_LASTNAME="eviction_results.d_alias_lastname";
        D_ALIAS_SUFFIX="eviction_results.d_alias_suffix";
        D_BIRTH_DATE="eviction_results.d_birth_date";
        D_ADDRESS_LINE_1="eviction_results.d_address_line_1";
        D_ADDRESS_LINE_2="eviction_results.d_address_line_2";
        D_APARTMENT_NUMBER="eviction_results.d_apartment_number";
        D_CITY="eviction_results.d_city";
        D_STATE="eviction_results.d_state";
        D_ZIPCODE="eviction_results.d_zipcode";
        D_PHONE="eviction_results.d_phone";
        D_CLEAN_AREA_CODE="eviction_results.d_clean_area_code";
        D_CLEAN_PHONE_NUMBER="eviction_results.d_clean_phone_number";
        TIS_SIG_PLAINTIFF="eviction_results.tis_sig_plaintiff";
        PLAINTIFF_TYPE="eviction_results.plaintiff_type";
        PLAINTIFF_DESC="eviction_results.plaintiff_desc";
        P_COUNTY_FIPS_CODE="eviction_results.p_county_fips_code";
        P_COUNTY_FIPS_DESC="eviction_results.p_county_fips_desc";
        P_STATE_FIPS_CODE="eviction_results.p_state_fips_code";
        P_STATE_FIPS_DESC="eviction_results.p_state_fips_desc";
        P_FULLNAME="eviction_results.p_fullname";
        P_FIRSTNAME="eviction_results.p_firstname";
        P_MIDDLENAME="eviction_results.p_middlename";
        P_LASTNAME="eviction_results.p_lastname";
        P_ADDRESS_LINE_1="eviction_results.p_address_line_1";
        P_ADDRESS_LINE_2="eviction_results.p_address_line_2";
        P_CITY="eviction_results.p_city";
        P_STATE="eviction_results.p_state";
        P_ZIPCODE="eviction_results.p_zipcode";
        P_PHONE_NUMBER="eviction_results.p_phone_number";
        
        P_CLEAN_AREA_CODE="eviction_results.p_clean_area_code";
        P_CLEAN_PHONE_NUMBER="eviction_results.p_clean_phone_number";
        NAME="eviction_results.name";
        ADDRESS1="eviction_results.address1";
        ADDRESS2="eviction_results.address2";
        CITY="eviction_results.city";
        STATE="eviction_results.state";
        ZIP="eviction_results.zip";
        
              if (Torque.isInit())
        {
            try
            {
                //getMapBuilder(BjlResultsMapBuilder.CLASS_NAME);
            	getMapBuilder(EvictionResultsMapBuilder.CLASS_NAME);
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
        	Torque.registerMapBuilder(EvictionResultsMapBuilder.CLASS_NAME);
        }
    }
 
    /** number of columns for this peer */
    public static final int numColumns =  88;

    /** A class that can be returned by this peer. */
    protected static final String CLASSNAME_DEFAULT =
        "springapp.domain.eviction.EvictionResults";

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
    public static List<EvictionResults> resultSet2Objects(java.sql.ResultSet results)
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
        return BaseEvictionResultsPeer
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
        criteria.addSelectColumn(TIS_SIG_CASE);
        criteria.addSelectColumn(TIS_VENDOR_CODE);
        criteria.addSelectColumn(CASE_NUMBER);
        criteria.addSelectColumn(CASE_SEQUENCE_NUMBER);
        criteria.addSelectColumn(CASE_YEAR_DATE);
        criteria.addSelectColumn(BOOK_NUMBER);
        criteria.addSelectColumn(PAGE_NUMBER);
        criteria.addSelectColumn(UNIQUE_ID);
        criteria.addSelectColumn(RESULT_CODE);
        criteria.addSelectColumn(RESULT_DESC);
        criteria.addSelectColumn(RESULT_DATE);
        criteria.addSelectColumn(FILE_DATE);
        criteria.addSelectColumn(INPUT_BY);
        criteria.addSelectColumn(INPUT_DATE);
        criteria.addSelectColumn(EXECUTED_DATE);
        criteria.addSelectColumn(RECEIVED_DATE);
        criteria.addSelectColumn(SATISFIED_DATE);
        criteria.addSelectColumn(VERIFIED_DATE);
        criteria.addSelectColumn(SOURCE_ID);
        criteria.addSelectColumn(RECORD_STATUS);
        criteria.addSelectColumn(JUDGEMENT_AMOUNT);
        criteria.addSelectColumn(JUDGEMENT_FLAG);
        criteria.addSelectColumn(STATE_ABBR);
        criteria.addSelectColumn(COURT_TYPE_CODE);
        criteria.addSelectColumn(COURT_TYPE_DESC);
        criteria.addSelectColumn(COURTHOUSE_SUB_CODE);
        criteria.addSelectColumn(COUNTY_FIPS_CODE);
        criteria.addSelectColumn(COUNTY_FIPS_DESC);
        criteria.addSelectColumn(STATE_FIPS_CODE);
        criteria.addSelectColumn(STATE_FIPS_DESC);
        criteria.addSelectColumn(AFFILIATE);
        criteria.addSelectColumn(POSSESSION_FLAG);
        criteria.addSelectColumn(POSSESSION_FLAG_DESC);
        criteria.addSelectColumn(REGION);
        criteria.addSelectColumn(TIS_SIG_DEFENDANT);
        criteria.addSelectColumn(DEFENDANT_NUMBER);
        criteria.addSelectColumn(D_COUNTY_FIPS_CODE);
        criteria.addSelectColumn(D_COUNTY_FIPS_DESC);
        criteria.addSelectColumn(D_STATE_FIPS_CODE);
        criteria.addSelectColumn(D_STATE_FIPS_DESC);
        criteria.addSelectColumn(D_SSN);
      	criteria.addSelectColumn(D_FULLNAME);
        criteria.addSelectColumn(D_FIRSTNAME);
        criteria.addSelectColumn(D_MIDDLENAME);
        criteria.addSelectColumn(D_LASTNAME);
        criteria.addSelectColumn(D_SUFFIX);
    	criteria.addSelectColumn(D_ALIAS_FLAG);
    	criteria.addSelectColumn(D_ALIAS_FIRSTNAME);
    	criteria.addSelectColumn(D_ALIAS_MIDDLENAME);
    	criteria.addSelectColumn(D_ALIAS_LASTNAME);
    	criteria.addSelectColumn(D_ALIAS_SUFFIX);
    	criteria.addSelectColumn(D_BIRTH_DATE);
    	criteria.addSelectColumn(D_ADDRESS_LINE_1);
    	criteria.addSelectColumn(D_ADDRESS_LINE_2);
    	criteria.addSelectColumn(D_APARTMENT_NUMBER);
    	criteria.addSelectColumn(D_CITY);
    	criteria.addSelectColumn(D_STATE);
    	criteria.addSelectColumn(D_ZIPCODE);
    	criteria.addSelectColumn(D_PHONE);
    	criteria.addSelectColumn(D_CLEAN_AREA_CODE);
    	criteria.addSelectColumn(D_CLEAN_PHONE_NUMBER);
    	criteria.addSelectColumn(TIS_SIG_PLAINTIFF);
    	criteria.addSelectColumn(PLAINTIFF_TYPE);
    	criteria.addSelectColumn(PLAINTIFF_DESC);
    	criteria.addSelectColumn(P_COUNTY_FIPS_CODE);
    	criteria.addSelectColumn(P_COUNTY_FIPS_DESC);
    	criteria.addSelectColumn(P_STATE_FIPS_CODE);
    	criteria.addSelectColumn(P_STATE_FIPS_DESC);
    	criteria.addSelectColumn(P_FULLNAME);
    	criteria.addSelectColumn(P_FIRSTNAME);
    	criteria.addSelectColumn(P_MIDDLENAME);
    	criteria.addSelectColumn(P_LASTNAME);
    	criteria.addSelectColumn(P_ADDRESS_LINE_1);
    	criteria.addSelectColumn(P_ADDRESS_LINE_2);
    	criteria.addSelectColumn(P_CITY);
    	criteria.addSelectColumn(P_STATE);
    	criteria.addSelectColumn(P_ZIPCODE);
    	criteria.addSelectColumn(P_PHONE_NUMBER);
    	
    	criteria.addSelectColumn(P_CLEAN_AREA_CODE);
    	criteria.addSelectColumn(P_CLEAN_PHONE_NUMBER);
    	criteria.addSelectColumn(NAME);
    	criteria.addSelectColumn(ADDRESS1);
    	criteria.addSelectColumn(ADDRESS2);
    	criteria.addSelectColumn(CITY);
    	criteria.addSelectColumn(STATE);
    	criteria.addSelectColumn(ZIP);
    	
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
     * from a specified offset.  This is done so that you can select
     * other rows than just those needed for this object.  You may
     * for example want to create two objects from the same row.
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static EvictionResults row2Object(Record row,
                                             int offset,
                                             Class cls)
        throws TorqueException
    {
        try
        {
            EvictionResults obj = (EvictionResults) cls.newInstance();
            EvictionResultsPeer.populateObject(row, offset, obj);
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
     * from a specified offset.  This is done so that you can select
     * other rows than just those needed for this object.  You may
     * for example want to create two objects from the same row.
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void populateObject(Record row,
                                      int offset,
                                      EvictionResults obj)
        throws TorqueException
    {
        try
        {
        	obj.setResultId(row.getValue(offset + 0).asLong());
            obj.setUserSearchId(row.getValue(offset + 1).asLong());
            obj.setTis_sig_case(row.getValue(offset + 2).asString());
            obj.setTis_vendor_code(row.getValue(offset + 3).asString());
            obj.setCase_number(row.getValue(offset + 4).asString());
            obj.setCase_sequence_number(row.getValue(offset + 5).asString());
            obj.setCase_year_date(row.getValue(offset + 6).asString());
            obj.setBook_number(row.getValue(offset + 7).asString());
            obj.setPage_number(row.getValue(offset + 8).asString());
            obj.setUnique_id(row.getValue(offset + 9).asString());
            obj.setResult_code(row.getValue(offset + 10).asString());
            obj.setResult_desc(row.getValue(offset + 11).asString());
            obj.setResult_date(row.getValue(offset + 12).asString());
            obj.setFile_date(row.getValue(offset + 13).asString());
            obj.setInput_by(row.getValue(offset + 14).asString());
            obj.setInput_date(row.getValue(offset + 15).asString());
            obj.setExecuted_date(row.getValue(offset + 16).asString());
            obj.setReceived_date(row.getValue(offset + 17).asString());
            obj.setSatisfied_date(row.getValue(offset + 18).asString());
            obj.setVerified_date(row.getValue(offset + 19).asString());
            obj.setSource_id(row.getValue(offset + 20).asString());
            obj.setRecord_status(row.getValue(offset + 21).asString());
            obj.setJudgement_amount(row.getValue(offset + 22).asString());
            obj.setJudgement_flag(row.getValue(offset + 23).asString());
            obj.setState_abbr(row.getValue(offset + 24).asString());
            obj.setCourt_type_code(row.getValue(offset + 25).asString());
            obj.setCourt_type_desc(row.getValue(offset + 26).asString());
            obj.setCourthouse_sub_code(row.getValue(offset + 27).asString());
            obj.setCounty_fips_code(row.getValue(offset + 28).asString());
            obj.setCounty_fips_desc(row.getValue(offset + 29).asString());
            obj.setState_fips_code(row.getValue(offset + 30).asString());
            obj.setState_fips_desc(row.getValue(offset + 31).asString());
            obj.setAffiliate(row.getValue(offset + 32).asString());
            obj.setPossession_flag(row.getValue(offset + 33).asString());
            obj.setPossession_flag_desc(row.getValue(offset + 34).asString());
            obj.setRegion(row.getValue(offset + 35).asString());
            obj.setTis_sig_defendant(row.getValue(offset + 36).asString());
            obj.setDefendant_number(row.getValue(offset + 37).asString());
            obj.setD_county_fips_code(row.getValue(offset + 38).asString());
            obj.setD_county_fips_desc(row.getValue(offset + 39).asString());
            obj.setD_state_fips_code(row.getValue(offset + 40).asString());
            obj.setD_state_fips_desc(row.getValue(offset + 41).asString());
            obj.setD_ssn(row.getValue(offset + 42).asString());
            obj.setD_fullname(row.getValue(offset + 43).asString());
            obj.setD_firstname(row.getValue(offset + 44).asString());
            obj.setD_middlename(row.getValue(offset + 45).asString());
            
            obj.setD_lastname(row.getValue(offset + 46).asString());
            obj.setD_suffix(row.getValue(offset + 47).asString());
            obj.setD_alias_flag(row.getValue(offset + 48).asString());
            obj.setD_alias_firstname(row.getValue(offset + 49).asString());
            
            obj.setD_alias_middlename(row.getValue(offset + 50).asString());
            obj.setD_alias_lastname(row.getValue(offset + 51).asString());
            obj.setD_alias_suffix(row.getValue(offset + 52).asString());
            obj.setD_birth_date(row.getValue(offset + 53).asString());
            obj.setD_address_line_1(row.getValue(offset + 54).asString());
            obj.setD_address_line_2(row.getValue(offset + 55).asString());
            obj.setD_apartment_number(row.getValue(offset + 56).asString());
            obj.setD_city(row.getValue(offset + 57).asString());
            obj.setD_state(row.getValue(offset + 58).asString());
            obj.setD_phone(row.getValue(offset + 59).asString());
            obj.setD_zipcode(row.getValue(offset + 60).asString());
            obj.setD_clean_area_code(row.getValue(offset + 61).asString());
            obj.setD_clean_phone_number(row.getValue(offset + 62).asString());
            obj.setTis_sig_plaintiff(row.getValue(offset + 63).asString());
            
            obj.setPlaintiff_type(row.getValue(offset + 64).asString());
            obj.setPlaintiff_desc(row.getValue(offset + 65).asString());
            obj.setP_county_fips_code(row.getValue(offset + 66).asString());
            obj.setP_county_fips_desc(row.getValue(offset + 67).asString());
            obj.setP_state_fips_code(row.getValue(offset + 68).asString());
            obj.setP_state_fips_desc(row.getValue(offset + 69).asString());
            obj.setP_fullname(row.getValue(offset + 70).asString());
            obj.setP_firstname(row.getValue(offset + 71).asString());
            obj.setP_middlename(row.getValue(offset + 72).asString());
            obj.setP_lastname(row.getValue(offset + 73).asString());
            obj.setP_address_line_1(row.getValue(offset + 74).asString());
            obj.setP_address_line_2(row.getValue(offset + 75).asString());
            obj.setP_city(row.getValue(offset + 76).asString());
            obj.setP_state(row.getValue(offset + 77).asString());
            obj.setP_phone_number(row.getValue(offset + 78).asString());
            obj.setP_zipcode(row.getValue(offset + 79).asString());
            obj.setP_clean_area_code(row.getValue(offset + 80).asString());
            obj.setP_clean_phone_number(row.getValue(offset + 81).asString());
            obj.setName(row.getValue(offset + 82).asString());
            obj.setAddress1(row.getValue(offset + 83).asString());
            obj.setAddress2(row.getValue(offset + 84).asString());
            obj.setCity(row.getValue(offset + 85).asString());
            obj.setState(row.getValue(offset + 86).asString());
            obj.setZip(row.getValue(offset + 87).asString());
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
    public static List<EvictionResults> doSelect(Criteria criteria) throws TorqueException
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
    public static List<EvictionResults> doSelect(Criteria criteria, Connection con)
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
        return BaseEvictionResultsPeer
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
    public static List<EvictionResults> populateObjects(List<Record> records)
        throws TorqueException
    {
        List<EvictionResults> results = new ArrayList<EvictionResults>(records.size());

        // populate the object(s)
        for (int i = 0; i < records.size(); i++)
        {
            Record row =  records.get(i);
              results.add(EvictionResultsPeer.row2Object(row, 1,
                EvictionResultsPeer.getOMClass()));
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
         BaseEvictionResultsPeer
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
         EvictionResultsPeer
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
    public static List<EvictionResults> doSelect(EvictionResults obj) throws TorqueException
    {
        return doSelect(buildSelectCriteria(obj));
    }

    /**
     * Method to do inserts
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doInsert(EvictionResults obj) throws TorqueException
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
    public static void doUpdate(EvictionResults obj) throws TorqueException
    {
        doUpdate(buildCriteria(obj));
        obj.setModified(false);
    }

    /**
     * @param obj the data object to delete in the database.
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doDelete(EvictionResults obj) throws TorqueException
    {
        doDelete(buildSelectCriteria(obj));
    }

    /**
     * Method to do inserts.  This method is to be used during a transaction,
     * otherwise use the doInsert(EvictionResults) method.  It will take
     * care of the connection details internally.
     *
     * @param obj the data object to insert into the database.
     * @param con the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doInsert(EvictionResults obj, Connection con)
        throws TorqueException
    {
          obj.setPrimaryKey(doInsert(buildCriteria(obj), con));
          obj.setNew(false);
        obj.setModified(false);
    }

    /**
     * Method to do update.  This method is to be used during a transaction,
     * otherwise use the doUpdate(EvictionResults) method.  It will take
     * care of the connection details internally.
     *
     * @param obj the data object to update in the database.
     * @param con the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doUpdate(EvictionResults obj, Connection con)
        throws TorqueException
    {
        doUpdate(buildCriteria(obj), con);
        obj.setModified(false);
    }

    /**
     * Method to delete.  This method is to be used during a transaction,
     * otherwise use the doDelete(EvictionResults) method.  It will take
     * care of the connection details internally.
     *
     * @param obj the data object to delete in the database.
     * @param con the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doDelete(EvictionResults obj, Connection con)
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
        BaseEvictionResultsPeer
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
    public static Criteria buildCriteria( EvictionResults obj )
    {
        Criteria criteria = new Criteria(DATABASE_NAME);
              if (!obj.isNew())
            	  criteria.add(RESULT_ID, obj.getResultId());
              criteria.add(USER_SEARCH_ID, obj.getUserSearchId());
              criteria.add(TIS_SIG_CASE, obj.getTis_sig_case());
              criteria.add(TIS_VENDOR_CODE, obj.getTis_vendor_code());
              criteria.add(CASE_NUMBER, obj.getCase_number());
              criteria.add(CASE_SEQUENCE_NUMBER, obj.getCase_sequence_number());
              criteria.add(CASE_YEAR_DATE, obj.getCase_year_date());
              criteria.add(BOOK_NUMBER, obj.getBook_number());
              criteria.add(PAGE_NUMBER, obj.getPage_number());
              criteria.add(UNIQUE_ID, obj.getUnique_id());
              criteria.add(RESULT_CODE, obj.getResult_code());
              criteria.add(RESULT_DESC, obj.getResult_desc());
              criteria.add(RESULT_DATE, obj.getResult_date());
              criteria.add(FILE_DATE, obj.getFile_date());
              criteria.add(INPUT_BY, obj.getInput_by());
              criteria.add(INPUT_DATE, obj.getInput_date());
              criteria.add(EXECUTED_DATE, obj.getExecuted_date());
              criteria.add(RECEIVED_DATE, obj.getReceived_date());
              criteria.add(SATISFIED_DATE, obj.getSatisfied_date());
              criteria.add(VERIFIED_DATE, obj.getVerified_date());
              criteria.add(SOURCE_ID, obj.getSource_id());
              criteria.add(RECORD_STATUS, obj.getRecord_status());
              criteria.add(JUDGEMENT_AMOUNT, obj.getJudgement_amount());
              criteria.add(JUDGEMENT_FLAG, obj.getJudgement_flag());
              criteria.add(STATE_ABBR, obj.getState_abbr());
              criteria.add(COURT_TYPE_CODE, obj.getCourt_type_code());
              criteria.add(COURT_TYPE_DESC, obj.getCourt_type_desc());
              criteria.add(COURTHOUSE_SUB_CODE, obj.getCourthouse_sub_code());
              criteria.add(COUNTY_FIPS_CODE, obj.getCounty_fips_code());
              criteria.add(COUNTY_FIPS_DESC, obj.getCounty_fips_desc());
              criteria.add(STATE_FIPS_CODE, obj.getState_fips_code());
              criteria.add(STATE_FIPS_DESC, obj.getState_fips_desc());
              criteria.add(AFFILIATE, obj.getAffiliate());
              criteria.add(POSSESSION_FLAG, obj.getPossession_flag());
              criteria.add(POSSESSION_FLAG_DESC, obj.getPossession_flag_desc());
              criteria.add(REGION, obj.getRegion());
              criteria.add(TIS_SIG_DEFENDANT, obj.getTis_sig_defendant());
              criteria.add(DEFENDANT_NUMBER, obj.getDefendant_number());
              criteria.add(D_COUNTY_FIPS_CODE, obj.getD_county_fips_code());
              criteria.add(D_COUNTY_FIPS_DESC, obj.getD_county_fips_desc());
              criteria.add(D_STATE_FIPS_CODE, obj.getD_state_fips_code());
              criteria.add(D_STATE_FIPS_DESC, obj.getD_state_fips_desc());
              criteria.add(D_SSN, obj.getD_ssn());
              criteria.add(D_FULLNAME, obj.getD_fullname());
              criteria.add(D_FIRSTNAME, obj.getD_firstname());
              criteria.add(D_MIDDLENAME, obj.getD_middlename());
              criteria.add(D_LASTNAME,obj.getD_lastname());
              criteria.add(D_SUFFIX, obj.getD_suffix());
              criteria.add(D_ALIAS_FLAG, obj.getD_alias_flag());
              criteria.add(D_ALIAS_FIRSTNAME, obj.getD_alias_firstname());
              criteria.add(D_ALIAS_MIDDLENAME, obj.getD_alias_middlename());
              criteria.add(D_ALIAS_LASTNAME, obj.getD_alias_lastname());
              criteria.add(D_ALIAS_SUFFIX, obj.getD_alias_suffix());
              criteria.add(D_BIRTH_DATE, obj.getD_birth_date());
              criteria.add(D_ADDRESS_LINE_1, obj.getD_address_line_1());
              criteria.add(D_ADDRESS_LINE_2, obj.getD_address_line_2());
              criteria.add(D_APARTMENT_NUMBER, obj.getD_apartment_number());
              criteria.add(D_CITY, obj.getD_city());
              criteria.add(D_STATE, obj.getD_state());
              criteria.add(D_ZIPCODE, obj.getD_zipcode());
              criteria.add(D_PHONE, obj.getD_phone());
              criteria.add(D_CLEAN_AREA_CODE, obj.getD_clean_area_code());
              criteria.add(D_CLEAN_PHONE_NUMBER, obj.getD_clean_phone_number());
              criteria.add(TIS_SIG_PLAINTIFF, obj.getTis_sig_plaintiff());
              criteria.add(PLAINTIFF_TYPE, obj.getPlaintiff_type());
              criteria.add(PLAINTIFF_DESC, obj.getPlaintiff_desc());
              criteria.add(P_COUNTY_FIPS_CODE, obj.getP_county_fips_code());
              criteria.add(P_COUNTY_FIPS_DESC, obj.getP_county_fips_desc());
              criteria.add(P_STATE_FIPS_CODE, obj.getP_state_fips_code());
              criteria.add(P_STATE_FIPS_DESC, obj.getP_state_fips_desc());
              criteria.add(P_FULLNAME, obj.getP_fullname());
              criteria.add(P_FIRSTNAME, obj.getP_firstname());
              criteria.add(P_MIDDLENAME, obj.getP_middlename());
              criteria.add(P_LASTNAME, obj.getP_lastname());
              criteria.add(P_ADDRESS_LINE_1, obj.getP_address_line_1());
              criteria.add(P_ADDRESS_LINE_2, obj.getP_address_line_2());
              criteria.add(P_CITY, obj.getP_city());
              criteria.add(P_STATE, obj.getP_state());
              criteria.add(P_ZIPCODE, obj.getP_zipcode());
              criteria.add(P_PHONE_NUMBER, obj.getP_phone_number());
              criteria.add(P_CLEAN_AREA_CODE, obj.getP_clean_area_code());
              criteria.add(P_CLEAN_PHONE_NUMBER, obj.getP_clean_phone_number());
              criteria.add(NAME, obj.getName());
              criteria.add(ADDRESS1, obj.getAddress1());
              criteria.add(ADDRESS2, obj.getAddress2());
              criteria.add(CITY, obj.getCity());
              criteria.add(STATE, obj.getState());
              criteria.add(ZIP, obj.getZip());
              

          return criteria;
    }

    /** Build a Criteria object from the data object for this peer, skipping all binary columns */
    public static Criteria buildSelectCriteria( EvictionResults obj )
    {
        Criteria criteria = new Criteria(DATABASE_NAME);
              if (!obj.isNew())
        {
                    criteria.add(RESULT_ID, obj.getResultId());
                }
              criteria.add(USER_SEARCH_ID, obj.getUserSearchId());
              criteria.add(TIS_SIG_CASE, obj.getTis_sig_case());
              criteria.add(TIS_VENDOR_CODE, obj.getTis_vendor_code());
              criteria.add(CASE_NUMBER, obj.getCase_number());
              criteria.add(CASE_SEQUENCE_NUMBER, obj.getCase_sequence_number());
              criteria.add(CASE_YEAR_DATE, obj.getCase_year_date());
              criteria.add(BOOK_NUMBER, obj.getBook_number());
              criteria.add(PAGE_NUMBER, obj.getPage_number());
              criteria.add(UNIQUE_ID, obj.getUnique_id());
              criteria.add(RESULT_CODE, obj.getResult_code());
              criteria.add(RESULT_DESC, obj.getResult_desc());
              criteria.add(RESULT_DATE, obj.getResult_date());
              criteria.add(FILE_DATE, obj.getFile_date());
              criteria.add(INPUT_BY, obj.getInput_by());
              criteria.add(INPUT_DATE, obj.getInput_date());
              criteria.add(EXECUTED_DATE, obj.getExecuted_date());
              criteria.add(RECEIVED_DATE, obj.getReceived_date());
              criteria.add(SATISFIED_DATE, obj.getSatisfied_date());
              criteria.add(VERIFIED_DATE, obj.getVerified_date());
              criteria.add(SOURCE_ID, obj.getSource_id());
              criteria.add(RECORD_STATUS, obj.getRecord_status());
              criteria.add(JUDGEMENT_AMOUNT, obj.getJudgement_amount());
              criteria.add(JUDGEMENT_FLAG, obj.getJudgement_flag());
              criteria.add(STATE_ABBR, obj.getState_abbr());
              criteria.add(COURT_TYPE_CODE, obj.getCourt_type_code());
              criteria.add(COURT_TYPE_DESC, obj.getCourt_type_desc());
              criteria.add(COURTHOUSE_SUB_CODE, obj.getCourthouse_sub_code());
              criteria.add(COUNTY_FIPS_CODE, obj.getCounty_fips_code());
              criteria.add(COUNTY_FIPS_DESC, obj.getCounty_fips_desc());
              criteria.add(STATE_FIPS_CODE, obj.getState_fips_code());
              criteria.add(STATE_FIPS_DESC, obj.getState_fips_desc());
              criteria.add(AFFILIATE, obj.getAffiliate());
              criteria.add(POSSESSION_FLAG, obj.getPossession_flag());
              criteria.add(POSSESSION_FLAG_DESC, obj.getPossession_flag_desc());
              criteria.add(REGION, obj.getRegion());
              criteria.add(TIS_SIG_DEFENDANT, obj.getTis_sig_defendant());
              criteria.add(DEFENDANT_NUMBER, obj.getDefendant_number());
              criteria.add(D_COUNTY_FIPS_CODE, obj.getD_county_fips_code());
              criteria.add(D_COUNTY_FIPS_DESC, obj.getD_county_fips_desc());
              criteria.add(D_STATE_FIPS_CODE, obj.getD_state_fips_code());
              criteria.add(D_STATE_FIPS_DESC, obj.getD_state_fips_desc());
              criteria.add(D_SSN, obj.getD_ssn());
              criteria.add(D_FULLNAME, obj.getD_fullname());
              criteria.add(D_FIRSTNAME, obj.getD_firstname());
              criteria.add(D_MIDDLENAME, obj.getD_middlename());
              criteria.add(D_LASTNAME,obj.getD_lastname());
              criteria.add(D_SUFFIX, obj.getD_suffix());
              criteria.add(D_ALIAS_FLAG, obj.getD_alias_flag());
              criteria.add(D_ALIAS_FIRSTNAME, obj.getD_alias_firstname());
              criteria.add(D_ALIAS_MIDDLENAME, obj.getD_alias_middlename());
              criteria.add(D_ALIAS_LASTNAME, obj.getD_alias_lastname());
              criteria.add(D_ALIAS_SUFFIX, obj.getD_alias_suffix());
              criteria.add(D_BIRTH_DATE, obj.getD_birth_date());
              criteria.add(D_ADDRESS_LINE_1, obj.getD_address_line_1());
              criteria.add(D_ADDRESS_LINE_2, obj.getD_address_line_2());
              criteria.add(D_APARTMENT_NUMBER, obj.getD_apartment_number());
              criteria.add(D_CITY, obj.getD_city());
              criteria.add(D_STATE, obj.getD_state());
              criteria.add(D_ZIPCODE, obj.getD_zipcode());
              criteria.add(D_PHONE, obj.getD_phone());
              criteria.add(D_CLEAN_AREA_CODE, obj.getD_clean_area_code());
              criteria.add(D_CLEAN_PHONE_NUMBER, obj.getD_clean_phone_number());
              criteria.add(TIS_SIG_PLAINTIFF, obj.getTis_sig_plaintiff());
              criteria.add(PLAINTIFF_TYPE, obj.getPlaintiff_type());
              criteria.add(PLAINTIFF_DESC, obj.getPlaintiff_desc());
              criteria.add(P_COUNTY_FIPS_CODE, obj.getP_county_fips_code());
              criteria.add(P_COUNTY_FIPS_DESC, obj.getP_county_fips_desc());
              criteria.add(P_STATE_FIPS_CODE, obj.getP_state_fips_code());
              criteria.add(P_STATE_FIPS_DESC, obj.getP_state_fips_desc());
              criteria.add(P_FULLNAME, obj.getP_fullname());
              criteria.add(P_FIRSTNAME, obj.getP_firstname());
              criteria.add(P_MIDDLENAME, obj.getP_middlename());
              criteria.add(P_LASTNAME, obj.getP_lastname());
              criteria.add(P_ADDRESS_LINE_1, obj.getP_address_line_1());
              criteria.add(P_ADDRESS_LINE_2, obj.getP_address_line_2());
              criteria.add(P_CITY, obj.getP_city());
              criteria.add(P_STATE, obj.getP_state());
              criteria.add(P_ZIPCODE, obj.getP_zipcode());
              criteria.add(P_PHONE_NUMBER, obj.getP_phone_number());
              criteria.add(P_CLEAN_AREA_CODE, obj.getP_clean_area_code());
              criteria.add(P_CLEAN_PHONE_NUMBER, obj.getP_clean_phone_number());
              criteria.add(NAME, obj.getName());
              criteria.add(ADDRESS1, obj.getAddress1());
              criteria.add(ADDRESS2, obj.getAddress2());
              criteria.add(CITY, obj.getCity());
              criteria.add(STATE, obj.getState());
              criteria.add(ZIP, obj.getZip());
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
    public static EvictionResults retrieveByPK(long pk)
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
    public static EvictionResults retrieveByPK(long pk, Connection con)
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
    public static EvictionResults retrieveByPK(ObjectKey pk)
        throws TorqueException, NoRowsException, TooManyRowsException
    {
        Connection db = null;
        EvictionResults retVal = null;
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
    public static EvictionResults retrieveByPK(ObjectKey pk, Connection con)
        throws TorqueException, NoRowsException, TooManyRowsException
    {
        Criteria criteria = buildCriteria(pk);
        List<EvictionResults> v = doSelect(criteria, con);
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
            return (EvictionResults)v.get(0);
        }
    }

    /**
     * Retrieve a multiple objects by pk
     *
     * @param pks List of primary keys
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static List<EvictionResults> retrieveByPKs(List<ObjectKey> pks)
        throws TorqueException
    {
        Connection db = null;
        List<EvictionResults> retVal = null;
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
    public static List<EvictionResults> retrieveByPKs( List<ObjectKey> pks, Connection dbcon )
        throws TorqueException
    {
        List<EvictionResults> objs = null;
        if (pks == null || pks.size() == 0)
        {
            objs = new LinkedList<EvictionResults>();
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
     * selects a collection of EvictionResults objects pre-filled with their
     * BjlSearches objects.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in EvictionResultsPeer.
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    protected static List<EvictionResults> doSelectJoinBjlSearches(Criteria criteria)
        throws TorqueException
    {
        return doSelectJoinBjlSearches(criteria, null);
    }

    /**
     * selects a collection of EvictionResults objects pre-filled with their
     * BjlSearches objects.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in EvictionResultsPeer.
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    protected static List<EvictionResults> doSelectJoinBjlSearches(Criteria criteria, Connection conn)
        throws TorqueException
    {
        setDbName(criteria);

        EvictionResultsPeer.addSelectColumns(criteria);
        int offset = numColumns + 1;
        EvictionResultsPeer.addSelectColumns(criteria);

                        criteria.addJoin(EvictionResultsPeer.USER_SEARCH_ID,
                        		EvictionSearchesPeer.USER_SEARCH_ID);
        
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

        List<EvictionResults> results = new ArrayList<EvictionResults>();

        for (int i = 0; i < rows.size(); i++)
        {
            Record row =  rows.get(i);

                            Class omClass = EvictionResultsPeer.getOMClass();
                    EvictionResults obj1 = EvictionResultsPeer
                .row2Object(row, 1, omClass);
                     omClass = EvictionSearchesPeer.getOMClass();
                    EvictionSearches obj2 = EvictionSearchesPeer
                .row2Object(row, offset, omClass);

            boolean newObject = true;
            for (int j = 0; j < results.size(); j++)
            {
                EvictionResults temp_obj1 =  results.get(j);
                //EvictionSearches temp_obj2 = temp_obj1.getBjlSearches();
                EvictionSearches temp_obj2 = temp_obj1.getEvictionSearches();
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
