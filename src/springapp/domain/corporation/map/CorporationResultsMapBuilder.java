package springapp.domain.corporation.map;

import org.apache.torque.Torque;
import org.apache.torque.TorqueException;
import org.apache.torque.map.ColumnMap;
import org.apache.torque.map.DatabaseMap;
import org.apache.torque.map.MapBuilder;
import org.apache.torque.map.TableMap;

import springapp.domain.eviction.EvictionResults;
import springapp.domain.eviction.EvictionResultsPeer;

public class CorporationResultsMapBuilder implements MapBuilder {
	/**
     * The name of this class
     */
    public static final String CLASS_NAME =
        "springapp.domain.corporation.map.CorporationResultsMapBuilder";

    /**
     * The database map.
     */
    private DatabaseMap dbMap = null;

    /**
     * Tells us if this DatabaseMapBuilder is built so that we
     * don't have to re-build it every time.
     *
     * @return true if this DatabaseMapBuilder is built
     */
    public boolean isBuilt()
    {
        return (dbMap != null);
    }

    /**
     * Gets the databasemap this map builder built.
     *
     * @return the databasemap
     */
    public DatabaseMap getDatabaseMap()
    {
        return this.dbMap;
    }

    /**
     * The doBuild() method builds the DatabaseMap
     *
     * @throws TorqueException
     */
    public synchronized void doBuild() throws TorqueException
    {
    	if ( isBuilt() ) {
    		return;
    	}
        dbMap = Torque.getDatabaseMap("services");

        dbMap.addTable("corporation_results");
        TableMap tMap = dbMap.getTable("corporation_results");
        tMap.setJavaName("CorporationResults");
        tMap.setOMClass( springapp.domain.corporation.CorporationResults.class );
        tMap.setPeerClass( springapp.domain.corporation.CorporationResultsPeer.class);
	    tMap.setPrimaryKeyMethod(TableMap.NATIVE);
        tMap.setPrimaryKeyMethodInfo("bjl_results_SEQ");

        ColumnMap cMap = null;


    // ------------- Column: result_id --------------------
        cMap = new ColumnMap( "result_id", tMap);
        cMap.setType( new Long(0) );
        cMap.setTorqueType( "BIGINT" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(true);
  		cMap.setNotNull(true);
        cMap.setJavaName( "ResultId" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                    cMap.setPosition(1);
          tMap.addColumn(cMap);
    // ------------- Column: user_search_id --------------------
        cMap = new ColumnMap( "user_search_id", tMap);
        cMap.setType( new Long(0) );
        cMap.setTorqueType( "BIGINT" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "UserSearchId" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
            	    cMap.setForeignKey("corporation_searches", "user_search_id"); 
            cMap.setPosition(2);
          tMap.addColumn(cMap);
    // ------------- Column: filing_number --------------------
        cMap = new ColumnMap( "filing_number", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "filing_number" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(3);
          tMap.addColumn(cMap);
    // ------------- Column: filing_date --------------------
        cMap = new ColumnMap( "filing_date", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "filing_date" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(4);
          tMap.addColumn(cMap);
    // ------------- Column: filing_state --------------------
        cMap = new ColumnMap( "filing_state", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "filing_state" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(5);
          tMap.addColumn(cMap);
    // ------------- Column: state --------------------
        cMap = new ColumnMap( "state", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "state" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(6);
          tMap.addColumn(cMap);
    // ------------- Column: corporation_id --------------------
        cMap = new ColumnMap( "corporation_id", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "corporation_id" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(7);
          tMap.addColumn(cMap);
    // ------------- Column: corporation_name --------------------
        cMap = new ColumnMap( "corporation_name", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "corporation_name" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(8);
          tMap.addColumn(cMap);
    // ------------- Column: incorp_date --------------------
        cMap = new ColumnMap( "incorp_date", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "incorp_date" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(9);
          tMap.addColumn(cMap);
    // ------------- Column: corporation_status --------------------
        cMap = new ColumnMap( "corporation_status", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "corporation_status" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(10);
          tMap.addColumn(cMap);
    // ------------- Column: corporation_status_date --------------------
        cMap = new ColumnMap( "corporation_status_date", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "corporation_status_date" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(11);
          tMap.addColumn(cMap);
    // ------------- Column: corporation_type --------------------
        cMap = new ColumnMap( "corporation_type", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "corporation_type" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(12);
          tMap.addColumn(cMap);
       // ------------- Column: bus_type --------------------
          cMap = new ColumnMap( "bus_type", tMap);
          cMap.setType( "" );
          cMap.setTorqueType( "VARCHAR" );
          cMap.setUsePrimitive(true);
          cMap.setPrimaryKey(false);
    		cMap.setNotNull(true);
          cMap.setJavaName( "bus_type" );
          cMap.setAutoIncrement(false);
    		cMap.setProtected(false);
            		cMap.setInheritance("false");
                  cMap.setSize( 255 );
     	              cMap.setPosition(13);
            tMap.addColumn(cMap);
    // ------------- Column: originated_state --------------------
        cMap = new ColumnMap( "originated_state", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "originated_state" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(14);
          tMap.addColumn(cMap);
    // ------------- Column: county --------------------
        cMap = new ColumnMap( "county", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "county" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(15);
          tMap.addColumn(cMap);
    // ------------- Column: federal_tax_id --------------------
        cMap = new ColumnMap( "federal_tax_id", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "federal_tax_id" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(16);
          tMap.addColumn(cMap);
    // ------------- Column: duration_date --------------------
        cMap = new ColumnMap( "duration_date", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "duration_date" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(17);
          tMap.addColumn(cMap);
   
    // ------------- Column: corporation_name_type_desc --------------------
        cMap = new ColumnMap( "corporation_name_type_desc", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "corporation_name_type_desc" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(18);
          tMap.addColumn(cMap);
    // ------------- Column: cn_corporation_name1 --------------------
        cMap = new ColumnMap( "cn_corporation_name1", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "cn_corporation_name1" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(19);
          tMap.addColumn(cMap);
    // ------------- Column: cn_effective_date --------------------
        cMap = new ColumnMap( "cn_effective_date", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "cn_effective_date" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(20);
          tMap.addColumn(cMap);
    // ------------- Column: name --------------------
        cMap = new ColumnMap( "name", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "name" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(21);
          tMap.addColumn(cMap);
    // ------------- Column: name_type --------------------
        cMap = new ColumnMap( "name_type", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "name_type" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(22);
          tMap.addColumn(cMap);
    // ------------- Column: current_active --------------------
        cMap = new ColumnMap( "current_active", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "current_active" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(23);
          tMap.addColumn(cMap);
    // ------------- Column: na_address1 --------------------
        cMap = new ColumnMap( "na_address1", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "na_address1" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(24);
          tMap.addColumn(cMap);
    // ------------- Column: na_address2 --------------------
        cMap = new ColumnMap( "na_address2", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "na_address2" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(25);
          tMap.addColumn(cMap);
    // ------------- Column: na_address3 --------------------
        cMap = new ColumnMap( "na_address3", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "na_address3" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(26);
          tMap.addColumn(cMap);
    // ------------- Column: na_city --------------------
        cMap = new ColumnMap( "na_city", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "na_city" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(27);
          tMap.addColumn(cMap);
    // ------------- Column: na_state --------------------
        cMap = new ColumnMap( "na_state", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "na_state" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(28);
          tMap.addColumn(cMap);
    // ------------- Column: na_zip_code --------------------
        cMap = new ColumnMap( "na_zip_code", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "na_zip_code" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(29);
          tMap.addColumn(cMap);
    // ------------- Column: na_address_type --------------------
        cMap = new ColumnMap( "na_address_type", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "na_address_type" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(30);
          tMap.addColumn(cMap);
    // ------------- Column: na_current_address --------------------
        cMap = new ColumnMap( "na_current_address", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "na_current_address" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(31);
          tMap.addColumn(cMap);
    // ------------- Column: na_parsed_flag --------------------
        cMap = new ColumnMap( "na_parsed_flag", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "na_parsed_flag" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(32);
          tMap.addColumn(cMap);
    // ------------- Column: a_address1 --------------------
        cMap = new ColumnMap( "a_address1", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "a_address1" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(33);
          tMap.addColumn(cMap);
    // ------------- Column: a_address2 --------------------
        cMap = new ColumnMap( "a_address2", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "a_address2" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(34);
          tMap.addColumn(cMap);
    // ------------- Column: a_address3 --------------------
        cMap = new ColumnMap( "a_address3", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "a_address3" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(35);
          tMap.addColumn(cMap);
    // ------------- Column: a_city --------------------
        cMap = new ColumnMap( "a_city", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "a_city" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(36);
          tMap.addColumn(cMap);
    // ------------- Column: a_state --------------------
        cMap = new ColumnMap( "a_state", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "a_state" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(37);
          tMap.addColumn(cMap);
    // ------------- Column: a_zip_code --------------------
        cMap = new ColumnMap( "a_zip_code", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "a_zip_code" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(38);
          tMap.addColumn(cMap);
    // ------------- Column: a_address_type --------------------
        cMap = new ColumnMap( "a_address_type", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "a_address_type" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(39);
          tMap.addColumn(cMap);
    // ------------- Column: a_current_address --------------------
        cMap = new ColumnMap( "a_current_address", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "a_current_address" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(40);
          tMap.addColumn(cMap);
    // ------------- Column: a_effective_date --------------------
        cMap = new ColumnMap( "a_effective_date", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "a_effective_date" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(41);
          tMap.addColumn(cMap);
    // ------------- Column: a_parsed_flag --------------------
        cMap = new ColumnMap( "a_parsed_flag", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "a_parsed_flag" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(42);
          tMap.addColumn(cMap);
    // ------------- Column: info_title --------------------
        cMap = new ColumnMap( "info_title", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "info_title" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(43);
          tMap.addColumn(cMap);
    // ------------- Column: info_desc --------------------
        cMap = new ColumnMap( "info_desc", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "info_desc" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(44);
          tMap.addColumn(cMap);
    // ------------- Column: history_pages --------------------
        cMap = new ColumnMap( "history_pages", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "history_pages" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(45);
          tMap.addColumn(cMap);
       // ------------- Column: history_code --------------------
          cMap = new ColumnMap( "history_code", tMap);
          cMap.setType( "" );
          cMap.setTorqueType( "VARCHAR" );
          cMap.setUsePrimitive(true);
          cMap.setPrimaryKey(false);
    		cMap.setNotNull(true);
          cMap.setJavaName( "history_code" );
          cMap.setAutoIncrement(false);
    		cMap.setProtected(false);
            		cMap.setInheritance("false");
                  cMap.setSize( 255 );
     	              cMap.setPosition(46);
            tMap.addColumn(cMap);
         // ------------- Column: history_desc --------------------
            cMap = new ColumnMap( "history_desc", tMap);
            cMap.setType( "" );
            cMap.setTorqueType( "VARCHAR" );
            cMap.setUsePrimitive(true);
            cMap.setPrimaryKey(false);
      		cMap.setNotNull(true);
            cMap.setJavaName( "history_desc" );
            cMap.setAutoIncrement(false);
      		cMap.setProtected(false);
              		cMap.setInheritance("false");
                    cMap.setSize( 255 );
       	              cMap.setPosition(47);
              tMap.addColumn(cMap);
           // ------------- Column: effective_date --------------------
              cMap = new ColumnMap( "effective_date", tMap);
              cMap.setType( "" );
              cMap.setTorqueType( "VARCHAR" );
              cMap.setUsePrimitive(true);
              cMap.setPrimaryKey(false);
        		cMap.setNotNull(true);
              cMap.setJavaName( "effective_date" );
              cMap.setAutoIncrement(false);
        		cMap.setProtected(false);
                		cMap.setInheritance("false");
                      cMap.setSize( 255 );
         	              cMap.setPosition(48);
                tMap.addColumn(cMap);
             // ------------- Column: history_num --------------------
                cMap = new ColumnMap( "history_num", tMap);
                cMap.setType( "" );
                cMap.setTorqueType( "VARCHAR" );
                cMap.setUsePrimitive(true);
                cMap.setPrimaryKey(false);
          		cMap.setNotNull(true);
                cMap.setJavaName( "history_num" );
                cMap.setAutoIncrement(false);
          		cMap.setProtected(false);
                  		cMap.setInheritance("false");
                        cMap.setSize( 255 );
           	              cMap.setPosition(49);
                  tMap.addColumn(cMap);
               // ------------- Column: history_name --------------------
                  cMap = new ColumnMap( "history_name", tMap);
                  cMap.setType( "" );
                  cMap.setTorqueType( "VARCHAR" );
                  cMap.setUsePrimitive(true);
                  cMap.setPrimaryKey(false);
            	  cMap.setNotNull(true);
                  cMap.setJavaName( "history_name" );
                  cMap.setAutoIncrement(false);
            	  cMap.setProtected(false);
                  cMap.setInheritance("false");
                  cMap.setSize( 255 );
             	  cMap.setPosition(50);
                  tMap.addColumn(cMap);
                 // ------------- Column: locator_num --------------------
                    cMap = new ColumnMap( "locator_num", tMap);
                    cMap.setType( "" );
                    cMap.setTorqueType( "VARCHAR" );
                    cMap.setUsePrimitive(true);
                    cMap.setPrimaryKey(false);
              		cMap.setNotNull(true);
                    cMap.setJavaName( "locator_num" );
                    cMap.setAutoIncrement(false);
              		cMap.setProtected(false);
                    cMap.setInheritance("false");
                    cMap.setSize( 255 );
               	    cMap.setPosition(51);
                      tMap.addColumn(cMap);
                  
        tMap.setUseInheritance(false);
    }

}
