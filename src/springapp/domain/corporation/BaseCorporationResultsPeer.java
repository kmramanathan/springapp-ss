package springapp.domain.corporation;

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

import springapp.domain.corporation.map.CorporationResultsMapBuilder;
import springapp.domain.corporation.map.CorporationSearchesMapBuilder;


import com.workingdogs.village.DataSetException;
import com.workingdogs.village.QueryDataSet;
import com.workingdogs.village.Record;

public abstract class BaseCorporationResultsPeer extends BasePeer {
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
    	return getMapBuilder(CorporationResultsMapBuilder.CLASS_NAME);
    }
    /** the column name for the result_id field */
    public static final String RESULT_ID;
      /** the column name for the user_search_id field */
    public static final String USER_SEARCH_ID;
    //Corporations response data
	/** Sos Corp fields*/
	public static final String FILING_NUMBER;
	public static final String FILING_DATE;
	public static final String FILING_STATE;
	public static final String STATE;
	public static final String CORPORATION_ID;
	public static final String CORPORATION_NAME;
	public static final String INCORP_DATE;
	public static final String CORPORATION_STATUS;
	public static final String CORPORATION_STATUS_DATE;
	public static final String CORPORATION_TYPE;
	public static final String BUS_TYPE;
	public static final String ORIGINATED_STATE;
	public static final String COUNTY;
	public static final String FEDERAL_TAX_ID;
	public static final String DURATION_DATE;
	
	/** corporation_name_information */
	public static final String CORPORATION_NAME_TYPE_DESC;
	public static final String CN_CORPORATION_NAME1;
	public static final String CN_EFFECTIVE_DATE;
	
	/** corporation name_information */
	public static final String NAME;
	public static final String NAME_TYPE;
	public static final String CURRENT_ACTIVE;
	
	/** corporation name_address */
	public static final String NA_ADDRESS1;
	public static final String NA_ADDRESS2;
	public static final String NA_ADDRESS3;
	public static final String NA_CITY;
	public static final String NA_STATE;
	public static final String NA_ZIP_CODE;
	public static final String NA_ADDRESS_TYPE;
	public static final String NA_CURRENT_ADDRESS;
	public static final String NA_PARSED_FLAG;
	
	/** Corporation address */
	
	public static final String A_ADDRESS1;
	public static final String A_ADDRESS2;
	public static final String A_ADDRESS3;
	public static final String A_CITY;
	public static final String A_STATE;
	public static final String A_ZIP_CODE;
	public static final String A_ADDRESS_TYPE;
	public static final String A_CURRENT_ADDRESS;
	public static final String A_EFFECTIVE_DATE;
	public static final String A_PARSED_FLAG;
	
	/** Corporation other_info */
	public static final String INFO_TITLE;
	public static final String INFO_DESC;
	
	/** corp_history*/
	
	public static final String HISTORY_PAGES;
	public static final String HISTORY_CODE;
	public static final String HISTORY_DESC;
	public static final String EFFECTIVE_DATE;
	public static final String HISTORY_NUM;
	public static final String HISTORY_NAME;
	public static final String LOCATOR_NUM;
	static
    {
        DATABASE_NAME = "services";
        TABLE_NAME = "corporation_results";

        RESULT_ID = "corporation_results.result_id";
        USER_SEARCH_ID = "corporation_results.user_search_id";
        FILING_NUMBER = "corporation_results.filing_number";
        FILING_DATE = "corporation_results.filing_date";
        FILING_STATE = "corporation_results.filing_state";
        STATE = "corporation_results.state";
        CORPORATION_ID = "corporation_results.corporation_id";
        CORPORATION_NAME = "corporation_results.corporation_name";
        INCORP_DATE = "corporation_results.incorp_date";
        CORPORATION_STATUS = "corporation_results.corporation_status";
        CORPORATION_STATUS_DATE = "corporation_results.corporation_status_date";
        CORPORATION_TYPE = "corporation_results.corporation_type";
        BUS_TYPE = "corporation_results.bus_type";
        ORIGINATED_STATE = "corporation_results.originated_state";
        COUNTY = "corporation_results.county";
        FEDERAL_TAX_ID = "corporation_results.federal_tax_id";
        DURATION_DATE = "corporation_results.duration_date";
        CORPORATION_NAME_TYPE_DESC = "corporation_results.corporation_name_type_desc";
        CN_CORPORATION_NAME1 = "corporation_results.cn_corporation_name1";
        CN_EFFECTIVE_DATE = "corporation_results.cn_effective_date";
        NAME = "corporation_results.name";
        NAME_TYPE = "corporation_results.name_type";
        CURRENT_ACTIVE = "corporation_results.current_active";
        NA_ADDRESS1 = "corporation_results.na_address1";
        NA_ADDRESS2 = "corporation_results.na_address2";
        NA_ADDRESS3 = "corporation_results.na_address3";
        NA_CITY = "corporation_results.na_city";
        NA_STATE = "corporation_results.na_state";
        NA_ZIP_CODE = "corporation_results.na_zip_code";
        NA_ADDRESS_TYPE = "corporation_results.na_address_type";
        NA_CURRENT_ADDRESS = "corporation_results.na_current_address";
        NA_PARSED_FLAG = "corporation_results.na_parsed_flag";
        A_ADDRESS1 = "corporation_results.a_address1";
        A_ADDRESS2 = "corporation_results.a_address2";
        A_ADDRESS3 = "corporation_results.a_address3";
        A_CITY = "corporation_results.a_city";
        A_STATE = "corporation_results.a_state";
        A_ZIP_CODE = "corporation_results.a_zip_code";
        A_ADDRESS_TYPE = "corporation_results.a_address_type";
        A_CURRENT_ADDRESS = "corporation_results.a_current_address";
        A_EFFECTIVE_DATE = "corporation_results.a_effective_date";
        A_PARSED_FLAG = "corporation_results.a_parsed_flag";
        INFO_TITLE = "corporation_results.info_title";
        INFO_DESC = "corporation_results.info_desc";
        HISTORY_PAGES="corporation_results.history_pages";
        HISTORY_CODE="corporation_results.history_code";
        HISTORY_DESC="corporation_results.history_desc";
        EFFECTIVE_DATE="corporation_results.effective_date";
        HISTORY_NUM="corporation_results.history_num";
        HISTORY_NAME="corporation_results.history_name";
        LOCATOR_NUM="corporation_results.locator_num";
        
        
        
              if (Torque.isInit())
        {
            try
            {
                //getMapBuilder(BjlResultsMapBuilder.CLASS_NAME);
            	getMapBuilder(CorporationResultsMapBuilder.CLASS_NAME);
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
        	Torque.registerMapBuilder(CorporationResultsMapBuilder.CLASS_NAME);
        }
    }
 
    /** number of columns for this peer */
    public static final int numColumns =  51;

    /** A class that can be returned by this peer. */
    protected static final String CLASSNAME_DEFAULT =
        "springapp.domain.corporation.CorporationResults";

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
    public static List<CorporationResults> resultSet2Objects(java.sql.ResultSet results)
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
        return BaseCorporationResultsPeer
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
        criteria.addSelectColumn(FILING_NUMBER);
        criteria.addSelectColumn(FILING_DATE);
        criteria.addSelectColumn(FILING_STATE);
        criteria.addSelectColumn(STATE);
        criteria.addSelectColumn(CORPORATION_ID);
        criteria.addSelectColumn(CORPORATION_NAME);
        criteria.addSelectColumn(INCORP_DATE);
        criteria.addSelectColumn(CORPORATION_STATUS);
        criteria.addSelectColumn(CORPORATION_STATUS_DATE);
        criteria.addSelectColumn(CORPORATION_TYPE);
        criteria.addSelectColumn(BUS_TYPE);
        criteria.addSelectColumn(ORIGINATED_STATE);
        criteria.addSelectColumn(COUNTY);
        criteria.addSelectColumn(FEDERAL_TAX_ID);
        criteria.addSelectColumn(DURATION_DATE);
        criteria.addSelectColumn(CORPORATION_NAME_TYPE_DESC);
        criteria.addSelectColumn(CN_CORPORATION_NAME1);
        criteria.addSelectColumn(CN_EFFECTIVE_DATE);
        criteria.addSelectColumn(NAME);
        criteria.addSelectColumn(NAME_TYPE);
        criteria.addSelectColumn(CURRENT_ACTIVE);
        criteria.addSelectColumn(NA_ADDRESS1);
        criteria.addSelectColumn(NA_ADDRESS2);
        criteria.addSelectColumn(NA_ADDRESS3);
        criteria.addSelectColumn(NA_CITY);
        criteria.addSelectColumn(NA_STATE);
        criteria.addSelectColumn(NA_ZIP_CODE);
        criteria.addSelectColumn(NA_ADDRESS_TYPE);
        criteria.addSelectColumn(NA_CURRENT_ADDRESS);
        criteria.addSelectColumn(NA_PARSED_FLAG);
        criteria.addSelectColumn(A_ADDRESS1);
        criteria.addSelectColumn(A_ADDRESS2);
        criteria.addSelectColumn(A_ADDRESS3);
        criteria.addSelectColumn(A_CITY);
        criteria.addSelectColumn(A_STATE);
        criteria.addSelectColumn(A_ZIP_CODE);
        criteria.addSelectColumn(A_ADDRESS_TYPE);
        criteria.addSelectColumn(A_CURRENT_ADDRESS);
        criteria.addSelectColumn(A_EFFECTIVE_DATE);
        criteria.addSelectColumn(A_PARSED_FLAG);
      	criteria.addSelectColumn(INFO_TITLE);
        criteria.addSelectColumn(INFO_DESC);
        criteria.addSelectColumn(HISTORY_PAGES);
        criteria.addSelectColumn(HISTORY_CODE);
        criteria.addSelectColumn(HISTORY_DESC);
    	criteria.addSelectColumn(EFFECTIVE_DATE);
    	criteria.addSelectColumn(HISTORY_NUM);
    	criteria.addSelectColumn(HISTORY_NAME);
    	criteria.addSelectColumn(LOCATOR_NUM);
    	    	
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
    public static CorporationResults row2Object(Record row,
                                             int offset,
                                             Class cls)
        throws TorqueException
    {
        try
        {
            CorporationResults obj = (CorporationResults) cls.newInstance();
            CorporationResultsPeer.populateObject(row, offset, obj);
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
                                      CorporationResults obj)
        throws TorqueException
    {
        try
        {
        	obj.setResultId(row.getValue(offset + 0).asLong());
            obj.setUserSearchId(row.getValue(offset + 1).asLong());
            obj.setFiling_number(row.getValue(offset + 2).asString());
            obj.setFiling_date(row.getValue(offset + 3).asString());
            obj.setFiling_state(row.getValue(offset + 4).asString());
            obj.setState(row.getValue(offset + 5).asString());
            obj.setCorporation_id(row.getValue(offset + 6).asString());
            obj.setCorporation_name(row.getValue(offset + 7).asString());
            obj.setIncorp_date(row.getValue(offset + 8).asString());
            obj.setCorporation_status(row.getValue(offset + 9).asString());
            obj.setCorporation_status_date(row.getValue(offset + 10).asString());
            obj.setCorporation_type(row.getValue(offset + 11).asString());
            obj.setBus_type(row.getValue(offset+12).asString());
            obj.setOriginated_state(row.getValue(offset + 13).asString());
            obj.setCounty(row.getValue(offset + 14).asString());
            obj.setFederal_tax_id(row.getValue(offset + 15).asString());
            obj.setDuration_date(row.getValue(offset + 16).asString());
            obj.setCorporation_name_type_desc(row.getValue(offset + 17).asString());
            obj.setCn_corporation_name1(row.getValue(offset + 18).asString());
            obj.setCn_effective_date(row.getValue(offset + 19).asString());
            obj.setName(row.getValue(offset + 20).asString());
            obj.setName_type(row.getValue(offset + 21).asString());
            obj.setCurrent_active(row.getValue(offset + 22).asString());
            obj.setNa_address1(row.getValue(offset + 23).asString());
            obj.setNa_address2(row.getValue(offset + 24).asString());
            obj.setNa_address3(row.getValue(offset + 25).asString());
            obj.setNa_city(row.getValue(offset + 26).asString());
            obj.setNa_state(row.getValue(offset + 27).asString());
            obj.setNa_zip_code(row.getValue(offset + 28).asString());
            obj.setNa_address_type(row.getValue(offset + 29).asString());
            obj.setNa_current_address(row.getValue(offset + 30).asString());
            obj.setNa_parsed_flag(row.getValue(offset + 31).asString());
            obj.setA_address1(row.getValue(offset + 32).asString());
            obj.setA_address2(row.getValue(offset + 33).asString());
            obj.setA_address3(row.getValue(offset + 34).asString());
            obj.setA_city(row.getValue(offset + 35).asString());
            obj.setA_state(row.getValue(offset + 36).asString());
            obj.setA_zip_code(row.getValue(offset + 37).asString());
            obj.setA_address_type(row.getValue(offset + 38).asString());
            obj.setA_current_address(row.getValue(offset + 39).asString());
            obj.setA_effective_date(row.getValue(offset + 40).asString());
            obj.setA_parsed_flag(row.getValue(offset + 41).asString());
            obj.setInfo_title(row.getValue(offset + 42).asString());
            obj.setInfo_desc(row.getValue(offset + 43).asString());
            obj.setHistory_pages(row.getValue(offset + 44).asString());
            
            obj.setHistory_code(row.getValue(offset + 45).asString());
            obj.setHistory_desc(row.getValue(offset + 46).asString());
            obj.setEffective_date(row.getValue(offset + 47).asString());
            obj.setHistory_num(row.getValue(offset + 48).asString());
            obj.setHistory_name(row.getValue(offset+ 49).asString());
            obj.setLocator_num(row.getValue(offset + 50).asString());
            
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
    public static List<CorporationResults> doSelect(Criteria criteria) throws TorqueException
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
    public static List<CorporationResults> doSelect(Criteria criteria, Connection con)
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
        return BaseCorporationResultsPeer
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
    public static List<CorporationResults> populateObjects(List<Record> records)
        throws TorqueException
    {
        List<CorporationResults> results = new ArrayList<CorporationResults>(records.size());

        // populate the object(s)
        for (int i = 0; i < records.size(); i++)
        {
            Record row =  records.get(i);
              results.add(CorporationResultsPeer.row2Object(row, 1,
                CorporationResultsPeer.getOMClass()));
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
         BaseCorporationResultsPeer
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
         CorporationResultsPeer
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
    public static List<CorporationResults> doSelect(CorporationResults obj) throws TorqueException
    {
        return doSelect(buildSelectCriteria(obj));
    }

    /**
     * Method to do inserts
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doInsert(CorporationResults obj) throws TorqueException
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
    public static void doUpdate(CorporationResults obj) throws TorqueException
    {
        doUpdate(buildCriteria(obj));
        obj.setModified(false);
    }

    /**
     * @param obj the data object to delete in the database.
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doDelete(CorporationResults obj) throws TorqueException
    {
        doDelete(buildSelectCriteria(obj));
    }

    /**
     * Method to do inserts.  This method is to be used during a transaction,
     * otherwise use the doInsert(CorporationResults) method.  It will take
     * care of the connection details internally.
     *
     * @param obj the data object to insert into the database.
     * @param con the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doInsert(CorporationResults obj, Connection con)
        throws TorqueException
    {
          obj.setPrimaryKey(doInsert(buildCriteria(obj), con));
          obj.setNew(false);
        obj.setModified(false);
    }

    /**
     * Method to do update.  This method is to be used during a transaction,
     * otherwise use the doUpdate(CorporationResults) method.  It will take
     * care of the connection details internally.
     *
     * @param obj the data object to update in the database.
     * @param con the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doUpdate(CorporationResults obj, Connection con)
        throws TorqueException
    {
        doUpdate(buildCriteria(obj), con);
        obj.setModified(false);
    }

    /**
     * Method to delete.  This method is to be used during a transaction,
     * otherwise use the doDelete(CorporationResults) method.  It will take
     * care of the connection details internally.
     *
     * @param obj the data object to delete in the database.
     * @param con the connection to use
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static void doDelete(CorporationResults obj, Connection con)
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
        BaseCorporationResultsPeer
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
    public static Criteria buildCriteria( CorporationResults obj )
    {
        Criteria criteria = new Criteria(DATABASE_NAME);
              if (!obj.isNew())
            criteria.add(RESULT_ID, obj.getResultId());
            criteria.add(USER_SEARCH_ID, obj.getUserSearchId());
          	criteria.add(FILING_NUMBER,obj.getFiling_number());
        	criteria.add(FILING_DATE, obj.getFiling_date());
        	criteria.add(FILING_STATE, obj.getFiling_state());
        	criteria.add(STATE, obj.getState());
        	criteria.add(CORPORATION_ID, obj.getCorporation_id());
        	criteria.add(CORPORATION_NAME, obj.getCorporation_name());
        	criteria.add(INCORP_DATE, obj.getIncorp_date());
        	criteria.add(CORPORATION_STATUS, obj.getCorporation_status());
        	criteria.add(CORPORATION_STATUS_DATE, obj.getCorporation_status_date());
        	criteria.add(CORPORATION_TYPE, obj.getCorporation_type());
        	criteria.add(BUS_TYPE, obj.getBus_type());
        	criteria.add(ORIGINATED_STATE, obj.getOriginated_state());
        	criteria.add(COUNTY, obj.getCounty());
        	criteria.add(FEDERAL_TAX_ID, obj.getFederal_tax_id());
        	criteria.add(DURATION_DATE, obj.getDuration_date());
        	criteria.add(CORPORATION_NAME_TYPE_DESC, obj.getCorporation_name_type_desc());
        	criteria.add(CN_CORPORATION_NAME1, obj.getCn_corporation_name1());
        	criteria.add(CN_EFFECTIVE_DATE, obj.getCn_effective_date());
        	criteria.add(NAME, obj.getName());
        	criteria.add(NAME_TYPE, obj.getName_type());
        	criteria.add(CURRENT_ACTIVE, obj.getCurrent_active());
        	criteria.add(NA_ADDRESS1, obj.getNa_address1());
        	criteria.add(NA_ADDRESS2, obj.getNa_address2());
        	criteria.add(NA_ADDRESS3, obj.getNa_address3());
        	criteria.add(NA_CITY, obj.getNa_city());
        	criteria.add(NA_STATE, obj.getNa_state());
        	criteria.add(NA_ZIP_CODE, obj.getNa_zip_code());
        	criteria.add(NA_ADDRESS_TYPE, obj.getNa_address_type());
        	criteria.add(NA_CURRENT_ADDRESS, obj.getNa_current_address());
        	criteria.add(NA_PARSED_FLAG, obj.getNa_parsed_flag());
        	criteria.add(A_ADDRESS1, obj.getA_address1());
        	criteria.add(A_ADDRESS2, obj.getA_address2());
        	criteria.add(A_ADDRESS3, obj.getA_address3());
        	criteria.add(A_CITY, obj.getA_city());
        	criteria.add(A_STATE, obj.getA_state());
        	criteria.add(A_ZIP_CODE, obj.getA_zip_code());
        	criteria.add(A_ADDRESS_TYPE, obj.getA_address_type());
        	criteria.add(A_CURRENT_ADDRESS, obj.getA_current_address());
        	criteria.add(A_EFFECTIVE_DATE, obj.getA_effective_date());
        	criteria.add(A_PARSED_FLAG, obj.getA_parsed_flag());
        	criteria.add(INFO_TITLE, obj.getInfo_title());
        	criteria.add(INFO_DESC, obj.getInfo_desc());
        	criteria.add(HISTORY_PAGES, obj.getHistory_pages());
        	criteria.add(HISTORY_CODE, obj.getHistory_code());
        	criteria.add(HISTORY_DESC, obj.getHistory_desc());
        	criteria.add(EFFECTIVE_DATE, obj.getEffective_date());
        	criteria.add(HISTORY_NUM, obj.getHistory_num());
        	criteria.add(HISTORY_NAME, obj.getHistory_name());
        	criteria.add(LOCATOR_NUM, obj.getLocator_num());              

          return criteria;
    }

    /** Build a Criteria object from the data object for this peer, skipping all binary columns */
    public static Criteria buildSelectCriteria( CorporationResults obj )
    {
        Criteria criteria = new Criteria(DATABASE_NAME);
              if (!obj.isNew())
        {
                    criteria.add(RESULT_ID, obj.getResultId());
                }
            criteria.add(USER_SEARCH_ID, obj.getUserSearchId());
            criteria.add(FILING_NUMBER,obj.getFiling_number());
          	criteria.add(FILING_DATE, obj.getFiling_date());
          	criteria.add(FILING_STATE, obj.getFiling_state());
          	criteria.add(STATE, obj.getState());
          	criteria.add(CORPORATION_ID, obj.getCorporation_id());
          	criteria.add(CORPORATION_NAME, obj.getCorporation_name());
          	criteria.add(INCORP_DATE, obj.getIncorp_date());
          	criteria.add(CORPORATION_STATUS, obj.getCorporation_status());
          	criteria.add(CORPORATION_STATUS_DATE, obj.getCorporation_status_date());
          	criteria.add(CORPORATION_TYPE, obj.getCorporation_type());
          	criteria.add(BUS_TYPE, obj.getBus_type());
          	criteria.add(ORIGINATED_STATE, obj.getOriginated_state());
          	criteria.add(COUNTY, obj.getCounty());
          	criteria.add(FEDERAL_TAX_ID, obj.getFederal_tax_id());
          	criteria.add(DURATION_DATE, obj.getDuration_date());
            criteria.add(CORPORATION_NAME_TYPE_DESC, obj.getCorporation_name_type_desc());
          	criteria.add(CN_CORPORATION_NAME1, obj.getCn_corporation_name1());
          	criteria.add(CN_EFFECTIVE_DATE, obj.getCn_effective_date());
          	criteria.add(NAME, obj.getName());
          	criteria.add(NAME_TYPE, obj.getName_type());
          	criteria.add(CURRENT_ACTIVE, obj.getCurrent_active());
          	criteria.add(NA_ADDRESS1, obj.getNa_address1());
          	criteria.add(NA_ADDRESS2, obj.getNa_address2());
          	criteria.add(NA_ADDRESS3, obj.getNa_address3());
          	criteria.add(NA_CITY, obj.getNa_city());
          	criteria.add(NA_STATE, obj.getNa_state());
          	criteria.add(NA_ZIP_CODE, obj.getNa_zip_code());
          	criteria.add(NA_ADDRESS_TYPE, obj.getNa_address_type());
          	criteria.add(NA_CURRENT_ADDRESS, obj.getNa_current_address());
          	criteria.add(NA_PARSED_FLAG, obj.getNa_parsed_flag());
          	criteria.add(A_ADDRESS1, obj.getA_address1());
          	criteria.add(A_ADDRESS2, obj.getA_address2());
          	criteria.add(A_ADDRESS3, obj.getA_address3());
          	criteria.add(A_CITY, obj.getA_city());
          	criteria.add(A_STATE, obj.getA_state());
          	criteria.add(A_ZIP_CODE, obj.getA_zip_code());
          	criteria.add(A_ADDRESS_TYPE, obj.getA_address_type());
          	criteria.add(A_CURRENT_ADDRESS, obj.getA_current_address());
          	criteria.add(A_EFFECTIVE_DATE, obj.getA_effective_date());
          	criteria.add(A_PARSED_FLAG, obj.getA_parsed_flag());
          	criteria.add(INFO_TITLE, obj.getInfo_title());
          	criteria.add(INFO_DESC, obj.getInfo_desc());
          	criteria.add(HISTORY_PAGES, obj.getHistory_pages());
          	criteria.add(HISTORY_CODE, obj.getHistory_code());
          	criteria.add(HISTORY_DESC, obj.getHistory_desc());
          	criteria.add(EFFECTIVE_DATE, obj.getEffective_date());
          	criteria.add(HISTORY_NUM, obj.getHistory_num());
          	criteria.add(HISTORY_NAME, obj.getHistory_name());
          	criteria.add(LOCATOR_NUM, obj.getLocator_num());              

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
    public static CorporationResults retrieveByPK(long pk)
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
    public static CorporationResults retrieveByPK(long pk, Connection con)
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
    public static CorporationResults retrieveByPK(ObjectKey pk)
        throws TorqueException, NoRowsException, TooManyRowsException
    {
        Connection db = null;
        CorporationResults retVal = null;
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
    public static CorporationResults retrieveByPK(ObjectKey pk, Connection con)
        throws TorqueException, NoRowsException, TooManyRowsException
    {
        Criteria criteria = buildCriteria(pk);
        List<CorporationResults> v = doSelect(criteria, con);
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
            return (CorporationResults)v.get(0);
        }
    }

    /**
     * Retrieve a multiple objects by pk
     *
     * @param pks List of primary keys
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    public static List<CorporationResults> retrieveByPKs(List<ObjectKey> pks)
        throws TorqueException
    {
        Connection db = null;
        List<CorporationResults> retVal = null;
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
    public static List<CorporationResults> retrieveByPKs( List<ObjectKey> pks, Connection dbcon )
        throws TorqueException
    {
        List<CorporationResults> objs = null;
        if (pks == null || pks.size() == 0)
        {
            objs = new LinkedList<CorporationResults>();
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
     * selects a collection of CorporationResults objects pre-filled with their
     * BjlSearches objects.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in CorporationResultsPeer.
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    protected static List<CorporationResults> doSelectJoinBjlSearches(Criteria criteria)
        throws TorqueException
    {
        return doSelectJoinBjlSearches(criteria, null);
    }

    /**
     * selects a collection of CorporationResults objects pre-filled with their
     * BjlSearches objects.
     *
     * This method is protected by default in order to keep the public
     * api reasonable.  You can provide public methods for those you
     * actually need in CorporationResultsPeer.
     *
     * @throws TorqueException Any exceptions caught during processing will be
     *         rethrown wrapped into a TorqueException.
     */
    protected static List<CorporationResults> doSelectJoinBjlSearches(Criteria criteria, Connection conn)
        throws TorqueException
    {
        setDbName(criteria);

        CorporationResultsPeer.addSelectColumns(criteria);
        int offset = numColumns + 1;
        CorporationResultsPeer.addSelectColumns(criteria);

                        criteria.addJoin(CorporationResultsPeer.USER_SEARCH_ID,
                        		CorporationSearchesPeer.USER_SEARCH_ID);
        
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

        List<CorporationResults> results = new ArrayList<CorporationResults>();

        for (int i = 0; i < rows.size(); i++)
        {
            Record row =  rows.get(i);

                            Class omClass = CorporationResultsPeer.getOMClass();
                    CorporationResults obj1 = CorporationResultsPeer
                .row2Object(row, 1, omClass);
                     omClass = CorporationSearchesPeer.getOMClass();
                    CorporationSearches obj2 = CorporationSearchesPeer
                .row2Object(row, offset, omClass);

            boolean newObject = true;
            for (int j = 0; j < results.size(); j++)
            {
                CorporationResults temp_obj1 =  results.get(j);
                //CorporationSearches temp_obj2 = temp_obj1.getBjlSearches();
                CorporationSearches temp_obj2 = temp_obj1.getCorporationSearches();
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
