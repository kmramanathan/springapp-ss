package springapp.domain.eviction.map;

import org.apache.torque.Torque;
import org.apache.torque.TorqueException;
import org.apache.torque.map.ColumnMap;
import org.apache.torque.map.DatabaseMap;
import org.apache.torque.map.MapBuilder;
import org.apache.torque.map.TableMap;

import springapp.domain.eviction.EvictionResults;
import springapp.domain.eviction.EvictionResultsPeer;

public class EvictionResultsMapBuilder implements MapBuilder {
	/**
     * The name of this class
     */
    public static final String CLASS_NAME =
        "springapp.domain.eviction.map.EvictionResultsMapBuilder";

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

        dbMap.addTable("eviction_results");
        TableMap tMap = dbMap.getTable("eviction_results");
        tMap.setJavaName("EvictionResults");
        tMap.setOMClass( springapp.domain.eviction.EvictionResults.class );
        tMap.setPeerClass( springapp.domain.eviction.EvictionResultsPeer.class);
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
            	    cMap.setForeignKey("eviction_searches", "user_search_id"); 
            cMap.setPosition(2);
          tMap.addColumn(cMap);
    // ------------- Column: tis_sig_case --------------------
        cMap = new ColumnMap( "tis_sig_case", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "tis_sig_case" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(3);
          tMap.addColumn(cMap);
    // ------------- Column: tis_vendor_code --------------------
        cMap = new ColumnMap( "tis_vendor_code", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "tis_vendor_code" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(4);
          tMap.addColumn(cMap);
    // ------------- Column: case_number --------------------
        cMap = new ColumnMap( "case_number", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "case_number" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(5);
          tMap.addColumn(cMap);
    // ------------- Column: case_sequence_number --------------------
        cMap = new ColumnMap( "case_sequence_number", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "case_sequence_number" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(6);
          tMap.addColumn(cMap);
    // ------------- Column: case_year_date --------------------
        cMap = new ColumnMap( "case_year_date", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "case_year_date" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(7);
          tMap.addColumn(cMap);
    // ------------- Column: book_number --------------------
        cMap = new ColumnMap( "book_number", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "book_number" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(8);
          tMap.addColumn(cMap);
    // ------------- Column: page_number --------------------
        cMap = new ColumnMap( "page_number", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "page_number" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(9);
          tMap.addColumn(cMap);
    // ------------- Column: unique_id --------------------
        cMap = new ColumnMap( "unique_id", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "unique_id" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(10);
          tMap.addColumn(cMap);
    // ------------- Column: result_code --------------------
        cMap = new ColumnMap( "result_code", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "result_code" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(11);
          tMap.addColumn(cMap);
    // ------------- Column: result_desc --------------------
        cMap = new ColumnMap( "result_desc", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "result_desc" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(12);
          tMap.addColumn(cMap);
    // ------------- Column: result_date --------------------
        cMap = new ColumnMap( "result_date", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "result_date" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(13);
          tMap.addColumn(cMap);
    // ------------- Column: file_date --------------------
        cMap = new ColumnMap( "file_date", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "file_date" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(14);
          tMap.addColumn(cMap);
    // ------------- Column: input_by --------------------
        cMap = new ColumnMap( "input_by", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "input_by" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(15);
          tMap.addColumn(cMap);
    // ------------- Column: input_date --------------------
        cMap = new ColumnMap( "input_date", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "input_date" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(16);
          tMap.addColumn(cMap);
    // ------------- Column: executed_date --------------------
        cMap = new ColumnMap( "executed_date", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "executed_date" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(17);
          tMap.addColumn(cMap);
    // ------------- Column: received_date --------------------
        cMap = new ColumnMap( "received_date", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "received_date" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(18);
          tMap.addColumn(cMap);
    // ------------- Column: satisfied_date --------------------
        cMap = new ColumnMap( "satisfied_date", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "satisfied_date" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(19);
          tMap.addColumn(cMap);
    // ------------- Column: verified_date --------------------
        cMap = new ColumnMap( "verified_date", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "verified_date" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(20);
          tMap.addColumn(cMap);
    // ------------- Column: source_id --------------------
        cMap = new ColumnMap( "source_id", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "source_id" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(21);
          tMap.addColumn(cMap);
    // ------------- Column: record_status --------------------
        cMap = new ColumnMap( "record_status", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "record_status" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(22);
          tMap.addColumn(cMap);
    // ------------- Column: judgement_amount --------------------
        cMap = new ColumnMap( "judgement_amount", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "judgement_amount" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(23);
          tMap.addColumn(cMap);
    // ------------- Column: judgement_flag --------------------
        cMap = new ColumnMap( "judgement_flag", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "judgement_flag" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(24);
          tMap.addColumn(cMap);
    // ------------- Column: state_abbr --------------------
        cMap = new ColumnMap( "state_abbr", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "state_abbr" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(25);
          tMap.addColumn(cMap);
    // ------------- Column: court_type_code --------------------
        cMap = new ColumnMap( "court_type_code", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "court_type_code" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(26);
          tMap.addColumn(cMap);
    // ------------- Column: court_type_desc --------------------
        cMap = new ColumnMap( "court_type_desc", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "court_type_desc" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(27);
          tMap.addColumn(cMap);
    // ------------- Column: courthouse_sub_code --------------------
        cMap = new ColumnMap( "courthouse_sub_code", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "courthouse_sub_code" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(28);
          tMap.addColumn(cMap);
    // ------------- Column: county_fips_code --------------------
        cMap = new ColumnMap( "county_fips_code", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "county_fips_code" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(29);
          tMap.addColumn(cMap);
    // ------------- Column: county_fips_desc --------------------
        cMap = new ColumnMap( "county_fips_desc", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "county_fips_desc" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(30);
          tMap.addColumn(cMap);
    // ------------- Column: state_fips_code --------------------
        cMap = new ColumnMap( "state_fips_code", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "state_fips_code" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(31);
          tMap.addColumn(cMap);
    // ------------- Column: state_fips_desc --------------------
        cMap = new ColumnMap( "state_fips_desc", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "state_fips_desc" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(32);
          tMap.addColumn(cMap);
    // ------------- Column: affiliate --------------------
        cMap = new ColumnMap( "affiliate", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "affiliate" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(33);
          tMap.addColumn(cMap);
    // ------------- Column: possession_flag --------------------
        cMap = new ColumnMap( "possession_flag", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "possession_flag" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(34);
          tMap.addColumn(cMap);
    // ------------- Column: possession_flag_desc --------------------
        cMap = new ColumnMap( "possession_flag_desc", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "possession_flag_desc" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(35);
          tMap.addColumn(cMap);
    // ------------- Column: region --------------------
        cMap = new ColumnMap( "region", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "region" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(36);
          tMap.addColumn(cMap);
    // ------------- Column: tis_sig_defendant --------------------
        cMap = new ColumnMap( "tis_sig_defendant", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "tis_sig_defendant" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(37);
          tMap.addColumn(cMap);
    // ------------- Column: defendant_number --------------------
        cMap = new ColumnMap( "defendant_number", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "defendant_number" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(38);
          tMap.addColumn(cMap);
    // ------------- Column: d_county_fips_code --------------------
        cMap = new ColumnMap( "d_county_fips_code", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "d_county_fips_code" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(39);
          tMap.addColumn(cMap);
    // ------------- Column: d_county_fips_desc --------------------
        cMap = new ColumnMap( "d_county_fips_desc", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "d_county_fips_desc" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(40);
          tMap.addColumn(cMap);
    // ------------- Column: d_state_fips_code --------------------
        cMap = new ColumnMap( "d_state_fips_code", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "d_state_fips_code" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(41);
          tMap.addColumn(cMap);
    // ------------- Column: d_state_fips_desc --------------------
        cMap = new ColumnMap( "d_state_fips_desc", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "d_state_fips_desc" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(42);
          tMap.addColumn(cMap);
    // ------------- Column: d_ssn --------------------
        cMap = new ColumnMap( "d_ssn", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "d_ssn" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(43);
          tMap.addColumn(cMap);
    // ------------- Column: d_fullname --------------------
        cMap = new ColumnMap( "d_fullname", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "d_fullname" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(44);
          tMap.addColumn(cMap);
    // ------------- Column: d_firstname --------------------
        cMap = new ColumnMap( "d_firstname", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "d_firstname" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(45);
          tMap.addColumn(cMap);
    // ------------- Column: d_middlename --------------------
        cMap = new ColumnMap( "d_middlename", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "d_middlename" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(46);
          tMap.addColumn(cMap);
       // ------------- Column: d_lastname --------------------
          cMap = new ColumnMap( "d_lastname", tMap);
          cMap.setType( "" );
          cMap.setTorqueType( "VARCHAR" );
          cMap.setUsePrimitive(true);
          cMap.setPrimaryKey(false);
    		cMap.setNotNull(true);
          cMap.setJavaName( "d_lastname" );
          cMap.setAutoIncrement(false);
    		cMap.setProtected(false);
            		cMap.setInheritance("false");
                  cMap.setSize( 255 );
     	              cMap.setPosition(47);
            tMap.addColumn(cMap);
         // ------------- Column: d_suffix --------------------
            cMap = new ColumnMap( "d_suffix", tMap);
            cMap.setType( "" );
            cMap.setTorqueType( "VARCHAR" );
            cMap.setUsePrimitive(true);
            cMap.setPrimaryKey(false);
      		cMap.setNotNull(true);
            cMap.setJavaName( "d_suffix" );
            cMap.setAutoIncrement(false);
      		cMap.setProtected(false);
              		cMap.setInheritance("false");
                    cMap.setSize( 255 );
       	              cMap.setPosition(48);
              tMap.addColumn(cMap);
           // ------------- Column: d_alias_flag --------------------
              cMap = new ColumnMap( "d_alias_flag", tMap);
              cMap.setType( "" );
              cMap.setTorqueType( "VARCHAR" );
              cMap.setUsePrimitive(true);
              cMap.setPrimaryKey(false);
        		cMap.setNotNull(true);
              cMap.setJavaName( "d_alias_flag" );
              cMap.setAutoIncrement(false);
        		cMap.setProtected(false);
                		cMap.setInheritance("false");
                      cMap.setSize( 255 );
         	              cMap.setPosition(49);
                tMap.addColumn(cMap);
             // ------------- Column: d_alias_firstname --------------------
                cMap = new ColumnMap( "d_alias_firstname", tMap);
                cMap.setType( "" );
                cMap.setTorqueType( "VARCHAR" );
                cMap.setUsePrimitive(true);
                cMap.setPrimaryKey(false);
          		cMap.setNotNull(true);
                cMap.setJavaName( "d_alias_firstname" );
                cMap.setAutoIncrement(false);
          		cMap.setProtected(false);
                  		cMap.setInheritance("false");
                        cMap.setSize( 255 );
           	              cMap.setPosition(50);
                  tMap.addColumn(cMap);
               // ------------- Column: d_alias_middlename --------------------
                  cMap = new ColumnMap( "d_alias_middlename", tMap);
                  cMap.setType( "" );
                  cMap.setTorqueType( "VARCHAR" );
                  cMap.setUsePrimitive(true);
                  cMap.setPrimaryKey(false);
            		cMap.setNotNull(true);
                  cMap.setJavaName( "d_alias_middlename" );
                  cMap.setAutoIncrement(false);
            		cMap.setProtected(false);
                    		cMap.setInheritance("false");
                          cMap.setSize( 255 );
             	              cMap.setPosition(51);
                    tMap.addColumn(cMap);
                 // ------------- Column: d_alias_lastname --------------------
                    cMap = new ColumnMap( "d_alias_lastname", tMap);
                    cMap.setType( "" );
                    cMap.setTorqueType( "VARCHAR" );
                    cMap.setUsePrimitive(true);
                    cMap.setPrimaryKey(false);
              		cMap.setNotNull(true);
                    cMap.setJavaName( "d_alias_lastname" );
                    cMap.setAutoIncrement(false);
              		cMap.setProtected(false);
                      		cMap.setInheritance("false");
                            cMap.setSize( 255 );
               	              cMap.setPosition(52);
                      tMap.addColumn(cMap);
                   // ------------- Column: d_alias_suffix --------------------
                      cMap = new ColumnMap( "d_alias_suffix", tMap);
                      cMap.setType( "" );
                      cMap.setTorqueType( "VARCHAR" );
                      cMap.setUsePrimitive(true);
                      cMap.setPrimaryKey(false);
                		cMap.setNotNull(true);
                      cMap.setJavaName( "d_alias_suffix" );
                      cMap.setAutoIncrement(false);
                		cMap.setProtected(false);
                        		cMap.setInheritance("false");
                              cMap.setSize( 255 );
                 	              cMap.setPosition(53);
                        tMap.addColumn(cMap);
                     // ------------- Column: d_birth_date --------------------
                        cMap = new ColumnMap( "d_birth_date", tMap);
                        cMap.setType( "" );
                        cMap.setTorqueType( "VARCHAR" );
                        cMap.setUsePrimitive(true);
                        cMap.setPrimaryKey(false);
                  		cMap.setNotNull(true);
                        cMap.setJavaName( "d_birth_date" );
                        cMap.setAutoIncrement(false);
                  		cMap.setProtected(false);
                          		cMap.setInheritance("false");
                                cMap.setSize( 255 );
                   	              cMap.setPosition(54);
                          tMap.addColumn(cMap);
                       // ------------- Column: d_address_line_1 --------------------
                          cMap = new ColumnMap( "d_address_line_1", tMap);
                          cMap.setType( "" );
                          cMap.setTorqueType( "VARCHAR" );
                          cMap.setUsePrimitive(true);
                          cMap.setPrimaryKey(false);
                    		cMap.setNotNull(true);
                          cMap.setJavaName( "d_address_line_1" );
                          cMap.setAutoIncrement(false);
                    		cMap.setProtected(false);
                            		cMap.setInheritance("false");
                                  cMap.setSize( 255 );
                     	              cMap.setPosition(55);
                            tMap.addColumn(cMap);
                         // ------------- Column: d_address_line_2 --------------------
                            cMap = new ColumnMap( "d_address_line_2", tMap);
                            cMap.setType( "" );
                            cMap.setTorqueType( "VARCHAR" );
                            cMap.setUsePrimitive(true);
                            cMap.setPrimaryKey(false);
                      		cMap.setNotNull(true);
                            cMap.setJavaName( "d_address_line_2" );
                            cMap.setAutoIncrement(false);
                      		cMap.setProtected(false);
                              		cMap.setInheritance("false");
                                    cMap.setSize( 255 );
                       	              cMap.setPosition(56);
                              tMap.addColumn(cMap);
                           // ------------- Column: d_apartment_number --------------------
                              cMap = new ColumnMap( "d_apartment_number", tMap);
                              cMap.setType( "" );
                              cMap.setTorqueType( "VARCHAR" );
                              cMap.setUsePrimitive(true);
                              cMap.setPrimaryKey(false);
                        		cMap.setNotNull(true);
                              cMap.setJavaName( "d_apartment_number" );
                              cMap.setAutoIncrement(false);
                        		cMap.setProtected(false);
                                		cMap.setInheritance("false");
                                      cMap.setSize( 255 );
                         	              cMap.setPosition(57);
                                tMap.addColumn(cMap);
                             // ------------- Column: d_city --------------------
                                cMap = new ColumnMap( "d_city", tMap);
                                cMap.setType( "" );
                                cMap.setTorqueType( "VARCHAR" );
                                cMap.setUsePrimitive(true);
                                cMap.setPrimaryKey(false);
                          		cMap.setNotNull(true);
                                cMap.setJavaName( "d_city" );
                                cMap.setAutoIncrement(false);
                          		cMap.setProtected(false);
                                  		cMap.setInheritance("false");
                                        cMap.setSize( 255 );
                           	              cMap.setPosition(58);
                                  tMap.addColumn(cMap);
                               // ------------- Column: d_state --------------------
                                  cMap = new ColumnMap( "d_state", tMap);
                                  cMap.setType( "" );
                                  cMap.setTorqueType( "VARCHAR" );
                                  cMap.setUsePrimitive(true);
                                  cMap.setPrimaryKey(false);
                            		cMap.setNotNull(true);
                                  cMap.setJavaName( "d_state" );
                                  cMap.setAutoIncrement(false);
                            		cMap.setProtected(false);
                                    		cMap.setInheritance("false");
                                          cMap.setSize( 255 );
                             	              cMap.setPosition(59);
                                    tMap.addColumn(cMap);
                                 // ------------- Column: d_zipcode --------------------
                                    cMap = new ColumnMap( "d_zipcode", tMap);
                                    cMap.setType( "" );
                                    cMap.setTorqueType( "VARCHAR" );
                                    cMap.setUsePrimitive(true);
                                    cMap.setPrimaryKey(false);
                              		cMap.setNotNull(true);
                                    cMap.setJavaName( "d_zipcode" );
                                    cMap.setAutoIncrement(false);
                              		cMap.setProtected(false);
                                      		cMap.setInheritance("false");
                                            cMap.setSize( 255 );
                               	              cMap.setPosition(60);
                                      tMap.addColumn(cMap);
                                   // ------------- Column: d_phone --------------------
                                      cMap = new ColumnMap( "d_phone", tMap);
                                      cMap.setType( "" );
                                      cMap.setTorqueType( "VARCHAR" );
                                      cMap.setUsePrimitive(true);
                                      cMap.setPrimaryKey(false);
                                		cMap.setNotNull(true);
                                      cMap.setJavaName( "d_phone" );
                                      cMap.setAutoIncrement(false);
                                		cMap.setProtected(false);
                                        		cMap.setInheritance("false");
                                              cMap.setSize( 255 );
                                 	              cMap.setPosition(61);
                                        tMap.addColumn(cMap);
                                     // ------------- Column: d_clean_area_code --------------------
                                        cMap = new ColumnMap( "d_clean_area_code", tMap);
                                        cMap.setType( "" );
                                        cMap.setTorqueType( "VARCHAR" );
                                        cMap.setUsePrimitive(true);
                                        cMap.setPrimaryKey(false);
                                  		cMap.setNotNull(true);
                                        cMap.setJavaName( "d_clean_area_code" );
                                        cMap.setAutoIncrement(false);
                                  		cMap.setProtected(false);
                                          		cMap.setInheritance("false");
                                                cMap.setSize( 255 );
                                   	              cMap.setPosition(62);
                                          tMap.addColumn(cMap);
                                       // ------------- Column: d_clean_phone_number --------------------
                                          cMap = new ColumnMap( "d_clean_phone_number", tMap);
                                          cMap.setType( "" );
                                          cMap.setTorqueType( "VARCHAR" );
                                          cMap.setUsePrimitive(true);
                                          cMap.setPrimaryKey(false);
                                    		cMap.setNotNull(true);
                                          cMap.setJavaName( "d_clean_phone_number" );
                                          cMap.setAutoIncrement(false);
                                    		cMap.setProtected(false);
                                            		cMap.setInheritance("false");
                                                  cMap.setSize( 255 );
                                     	              cMap.setPosition(63);
                                            tMap.addColumn(cMap);
                                         // ------------- Column: tis_sig_plaintiff --------------------
                                            cMap = new ColumnMap( "tis_sig_plaintiff", tMap);
                                            cMap.setType( "" );
                                            cMap.setTorqueType( "VARCHAR" );
                                            cMap.setUsePrimitive(true);
                                            cMap.setPrimaryKey(false);
                                      		cMap.setNotNull(true);
                                            cMap.setJavaName( "tis_sig_plaintiff" );
                                            cMap.setAutoIncrement(false);
                                      		cMap.setProtected(false);
                                              		cMap.setInheritance("false");
                                                    cMap.setSize( 255 );
                                       	              cMap.setPosition(64);
                                              tMap.addColumn(cMap);
                                           // ------------- Column: plaintiff_type --------------------
                                              cMap = new ColumnMap( "plaintiff_type", tMap);
                                              cMap.setType( "" );
                                              cMap.setTorqueType( "VARCHAR" );
                                              cMap.setUsePrimitive(true);
                                              cMap.setPrimaryKey(false);
                                        		cMap.setNotNull(true);
                                              cMap.setJavaName( "plaintiff_type" );
                                              cMap.setAutoIncrement(false);
                                        		cMap.setProtected(false);
                                                		cMap.setInheritance("false");
                                                      cMap.setSize( 255 );
                                         	              cMap.setPosition(65);
                                                tMap.addColumn(cMap);
                                             // ------------- Column: plaintiff_desc --------------------
                                                cMap = new ColumnMap( "plaintiff_desc", tMap);
                                                cMap.setType( "" );
                                                cMap.setTorqueType( "VARCHAR" );
                                                cMap.setUsePrimitive(true);
                                                cMap.setPrimaryKey(false);
                                          		cMap.setNotNull(true);
                                                cMap.setJavaName( "plaintiff_desc" );
                                                cMap.setAutoIncrement(false);
                                          		cMap.setProtected(false);
                                                  		cMap.setInheritance("false");
                                                        cMap.setSize( 255 );
                                           	              cMap.setPosition(66);
                                                  tMap.addColumn(cMap);
                                               // ------------- Column: p_county_fips_code --------------------
                                                  cMap = new ColumnMap( "p_county_fips_code", tMap);
                                                  cMap.setType( "" );
                                                  cMap.setTorqueType( "VARCHAR" );
                                                  cMap.setUsePrimitive(true);
                                                  cMap.setPrimaryKey(false);
                                            		cMap.setNotNull(true);
                                                  cMap.setJavaName( "p_county_fips_code" );
                                                  cMap.setAutoIncrement(false);
                                            		cMap.setProtected(false);
                                                    		cMap.setInheritance("false");
                                                          cMap.setSize( 255 );
                                             	              cMap.setPosition(67);
                                                    tMap.addColumn(cMap);
                                                 // ------------- Column: p_county_fips_desc --------------------
                                                    cMap = new ColumnMap( "p_county_fips_desc", tMap);
                                                    cMap.setType( "" );
                                                    cMap.setTorqueType( "VARCHAR" );
                                                    cMap.setUsePrimitive(true);
                                                    cMap.setPrimaryKey(false);
                                              		cMap.setNotNull(true);
                                                    cMap.setJavaName( "p_county_fips_desc" );
                                                    cMap.setAutoIncrement(false);
                                              		cMap.setProtected(false);
                                                      		cMap.setInheritance("false");
                                                            cMap.setSize( 255 );
                                               	              cMap.setPosition(68);
                                                      tMap.addColumn(cMap);
                                                   // ------------- Column: p_state_fips_code --------------------
                                                      cMap = new ColumnMap( "p_state_fips_code", tMap);
                                                      cMap.setType( "" );
                                                      cMap.setTorqueType( "VARCHAR" );
                                                      cMap.setUsePrimitive(true);
                                                      cMap.setPrimaryKey(false);
                                                		cMap.setNotNull(true);
                                                      cMap.setJavaName( "p_state_fips_code" );
                                                      cMap.setAutoIncrement(false);
                                                		cMap.setProtected(false);
                                                        		cMap.setInheritance("false");
                                                              cMap.setSize( 255 );
                                                 	              cMap.setPosition(69);
                                                        tMap.addColumn(cMap);
                                                     // ------------- Column: p_state_fips_desc --------------------
                                                        cMap = new ColumnMap( "p_state_fips_desc", tMap);
                                                        cMap.setType( "" );
                                                        cMap.setTorqueType( "VARCHAR" );
                                                        cMap.setUsePrimitive(true);
                                                        cMap.setPrimaryKey(false);
                                                  		cMap.setNotNull(true);
                                                        cMap.setJavaName( "p_state_fips_desc" );
                                                        cMap.setAutoIncrement(false);
                                                  		cMap.setProtected(false);
                                                          		cMap.setInheritance("false");
                                                                cMap.setSize( 255 );
                                                   	              cMap.setPosition(70);
                                                          tMap.addColumn(cMap);
                                                       // ------------- Column: p_fullname --------------------
                                                          cMap = new ColumnMap( "p_fullname", tMap);
                                                          cMap.setType( "" );
                                                          cMap.setTorqueType( "VARCHAR" );
                                                          cMap.setUsePrimitive(true);
                                                          cMap.setPrimaryKey(false);
                                                    		cMap.setNotNull(true);
                                                          cMap.setJavaName( "p_fullname" );
                                                          cMap.setAutoIncrement(false);
                                                    		cMap.setProtected(false);
                                                            		cMap.setInheritance("false");
                                                                  cMap.setSize( 255 );
                                                     	              cMap.setPosition(71);
                                                            tMap.addColumn(cMap);
                                                         // ------------- Column: p_firstname --------------------
                                                            cMap = new ColumnMap( "p_firstname", tMap);
                                                            cMap.setType( "" );
                                                            cMap.setTorqueType( "VARCHAR" );
                                                            cMap.setUsePrimitive(true);
                                                            cMap.setPrimaryKey(false);
                                                      		cMap.setNotNull(true);
                                                            cMap.setJavaName( "p_firstname" );
                                                            cMap.setAutoIncrement(false);
                                                      		cMap.setProtected(false);
                                                              		cMap.setInheritance("false");
                                                                    cMap.setSize( 255 );
                                                       	              cMap.setPosition(72);
                                                              tMap.addColumn(cMap);
                                                           // ------------- Column: p_middlename --------------------
                                                              cMap = new ColumnMap( "p_middlename", tMap);
                                                              cMap.setType( "" );
                                                              cMap.setTorqueType( "VARCHAR" );
                                                              cMap.setUsePrimitive(true);
                                                              cMap.setPrimaryKey(false);
                                                        		cMap.setNotNull(true);
                                                              cMap.setJavaName( "p_middlename" );
                                                              cMap.setAutoIncrement(false);
                                                        		cMap.setProtected(false);
                                                                		cMap.setInheritance("false");
                                                                      cMap.setSize( 255 );
                                                         	              cMap.setPosition(73);
                                                                tMap.addColumn(cMap);
                                                             // ------------- Column: p_lastname --------------------
                                                                cMap = new ColumnMap( "p_lastname", tMap);
                                                                cMap.setType( "" );
                                                                cMap.setTorqueType( "VARCHAR" );
                                                                cMap.setUsePrimitive(true);
                                                                cMap.setPrimaryKey(false);
                                                          		cMap.setNotNull(true);
                                                                cMap.setJavaName( "p_lastname" );
                                                                cMap.setAutoIncrement(false);
                                                          		cMap.setProtected(false);
                                                                  		cMap.setInheritance("false");
                                                                        cMap.setSize( 255 );
                                                           	              cMap.setPosition(74);
                                                                  tMap.addColumn(cMap);
                                                               // ------------- Column: p_address_line_1 --------------------
                                                                  cMap = new ColumnMap( "p_address_line_1", tMap);
                                                                  cMap.setType( "" );
                                                                  cMap.setTorqueType( "VARCHAR" );
                                                                  cMap.setUsePrimitive(true);
                                                                  cMap.setPrimaryKey(false);
                                                            		cMap.setNotNull(true);
                                                                  cMap.setJavaName( "p_address_line_1" );
                                                                  cMap.setAutoIncrement(false);
                                                            		cMap.setProtected(false);
                                                                    		cMap.setInheritance("false");
                                                                          cMap.setSize( 255 );
                                                             	              cMap.setPosition(75);
                                                                    tMap.addColumn(cMap);
                                                                    // ------------- Column: p_address_line_2 --------------------
                                                                    cMap = new ColumnMap( "p_address_line_2", tMap);
                                                                    cMap.setType( "" );
                                                                    cMap.setTorqueType( "VARCHAR" );
                                                                    cMap.setUsePrimitive(true);
                                                                    cMap.setPrimaryKey(false);
                                                              		cMap.setNotNull(true);
                                                                    cMap.setJavaName( "p_address_line_2" );
                                                                    cMap.setAutoIncrement(false);
                                                              		cMap.setProtected(false);
                                                                      		cMap.setInheritance("false");
                                                                            cMap.setSize( 255 );
                                                               	              cMap.setPosition(76);
                                                                      tMap.addColumn(cMap);
                                                                      // ------------- Column: p_city --------------------
                                                                      cMap = new ColumnMap( "p_city", tMap);
                                                                      cMap.setType( "" );
                                                                      cMap.setTorqueType( "VARCHAR" );
                                                                      cMap.setUsePrimitive(true);
                                                                      cMap.setPrimaryKey(false);
                                                                		cMap.setNotNull(true);
                                                                      cMap.setJavaName( "p_city" );
                                                                      cMap.setAutoIncrement(false);
                                                                		cMap.setProtected(false);
                                                                        		cMap.setInheritance("false");
                                                                              cMap.setSize( 255 );
                                                                 	              cMap.setPosition(77);
                                                                        tMap.addColumn(cMap);
                                                                        // ------------- Column: p_state --------------------
                                                                        cMap = new ColumnMap( "p_state", tMap);
                                                                        cMap.setType( "" );
                                                                        cMap.setTorqueType( "VARCHAR" );
                                                                        cMap.setUsePrimitive(true);
                                                                        cMap.setPrimaryKey(false);
                                                                  		cMap.setNotNull(true);
                                                                        cMap.setJavaName( "p_state" );
                                                                        cMap.setAutoIncrement(false);
                                                                  		cMap.setProtected(false);
                                                                          		cMap.setInheritance("false");
                                                                                cMap.setSize( 255 );
                                                                   	              cMap.setPosition(78);
                                                                          tMap.addColumn(cMap);
                                                                          // ------------- Column: p_zipcode --------------------
                                                                          cMap = new ColumnMap( "p_zipcode", tMap);
                                                                          cMap.setType( "" );
                                                                          cMap.setTorqueType( "VARCHAR" );
                                                                          cMap.setUsePrimitive(true);
                                                                          cMap.setPrimaryKey(false);
                                                                    		cMap.setNotNull(true);
                                                                          cMap.setJavaName( "p_zipcode" );
                                                                          cMap.setAutoIncrement(false);
                                                                    		cMap.setProtected(false);
                                                                            		cMap.setInheritance("false");
                                                                                  cMap.setSize( 255 );
                                                                     	              cMap.setPosition(79);
                                                                            tMap.addColumn(cMap);
                                                                         // ------------- Column: p_phone_number --------------------
                                                                            cMap = new ColumnMap( "p_phone_number", tMap);
                                                                            cMap.setType( "" );
                                                                            cMap.setTorqueType( "VARCHAR" );
                                                                            cMap.setUsePrimitive(true);
                                                                            cMap.setPrimaryKey(false);
                                                                      		cMap.setNotNull(true);
                                                                            cMap.setJavaName( "p_phone_number" );
                                                                            cMap.setAutoIncrement(false);
                                                                      		cMap.setProtected(false);
                                                                              		cMap.setInheritance("false");
                                                                                    cMap.setSize( 255 );
                                                                       	              cMap.setPosition(80);
                                                                              tMap.addColumn(cMap);
                                                                           // ------------- Column: p_clean_area_code --------------------
                                                                              cMap = new ColumnMap( "p_clean_area_code", tMap);
                                                                              cMap.setType( "" );
                                                                              cMap.setTorqueType( "VARCHAR" );
                                                                              cMap.setUsePrimitive(true);
                                                                              cMap.setPrimaryKey(false);
                                                                        		cMap.setNotNull(true);
                                                                              cMap.setJavaName( "p_clean_area_code" );
                                                                              cMap.setAutoIncrement(false);
                                                                        		cMap.setProtected(false);
                                                                                		cMap.setInheritance("false");
                                                                                      cMap.setSize( 255 );
                                                                         	              cMap.setPosition(81);
                                                                                tMap.addColumn(cMap);
                                                                             // ------------- Column: p_clean_phone_number --------------------
                                                                                cMap = new ColumnMap( "p_clean_phone_number", tMap);
                                                                                cMap.setType( "" );
                                                                                cMap.setTorqueType( "VARCHAR" );
                                                                                cMap.setUsePrimitive(true);
                                                                                cMap.setPrimaryKey(false);
                                                                          		cMap.setNotNull(true);
                                                                                cMap.setJavaName( "p_clean_phone_number" );
                                                                                cMap.setAutoIncrement(false);
                                                                          		cMap.setProtected(false);
                                                                                  		cMap.setInheritance("false");
                                                                                        cMap.setSize( 255 );
                                                                           	              cMap.setPosition(82);
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
                                                                             	              cMap.setPosition(83);
                                                                                    tMap.addColumn(cMap);
                                                                                 // ------------- Column: address1 --------------------
                                                                                    cMap = new ColumnMap( "address1", tMap);
                                                                                    cMap.setType( "" );
                                                                                    cMap.setTorqueType( "VARCHAR" );
                                                                                    cMap.setUsePrimitive(true);
                                                                                    cMap.setPrimaryKey(false);
                                                                              		cMap.setNotNull(true);
                                                                                    cMap.setJavaName( "address1" );
                                                                                    cMap.setAutoIncrement(false);
                                                                              		cMap.setProtected(false);
                                                                                      		cMap.setInheritance("false");
                                                                                            cMap.setSize( 255 );
                                                                               	              cMap.setPosition(84);
                                                                                      tMap.addColumn(cMap);
                                                                                   // ------------- Column: address2 --------------------
                                                                                      cMap = new ColumnMap( "address2", tMap);
                                                                                      cMap.setType( "" );
                                                                                      cMap.setTorqueType( "VARCHAR" );
                                                                                      cMap.setUsePrimitive(true);
                                                                                      cMap.setPrimaryKey(false);
                                                                                		cMap.setNotNull(true);
                                                                                      cMap.setJavaName( "address2" );
                                                                                      cMap.setAutoIncrement(false);
                                                                                		cMap.setProtected(false);
                                                                                        		cMap.setInheritance("false");
                                                                                              cMap.setSize( 255 );
                                                                                 	              cMap.setPosition(85);
                                                                                        tMap.addColumn(cMap);
                                                                                     // ------------- Column: city --------------------
                                                                                        cMap = new ColumnMap( "city", tMap);
                                                                                        cMap.setType( "" );
                                                                                        cMap.setTorqueType( "VARCHAR" );
                                                                                        cMap.setUsePrimitive(true);
                                                                                        cMap.setPrimaryKey(false);
                                                                                  		cMap.setNotNull(true);
                                                                                        cMap.setJavaName( "city" );
                                                                                        cMap.setAutoIncrement(false);
                                                                                  		cMap.setProtected(false);
                                                                                          		cMap.setInheritance("false");
                                                                                                cMap.setSize( 255 );
                                                                                   	              cMap.setPosition(86);
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
                                                                                     	              cMap.setPosition(87);
                                                                                            tMap.addColumn(cMap);
                                                                                         // ------------- Column: zip --------------------
                                                                                            cMap = new ColumnMap( "zip", tMap);
                                                                                            cMap.setType( "" );
                                                                                            cMap.setTorqueType( "VARCHAR" );
                                                                                            cMap.setUsePrimitive(true);
                                                                                            cMap.setPrimaryKey(false);
                                                                                      		cMap.setNotNull(true);
                                                                                            cMap.setJavaName( "zip" );
                                                                                            cMap.setAutoIncrement(false);
                                                                                      		cMap.setProtected(false);
                                                                                              		cMap.setInheritance("false");
                                                                                                    cMap.setSize( 255 );
                                                                                       	              cMap.setPosition(88);
                                                                                              tMap.addColumn(cMap);
        tMap.setUseInheritance(false);
    }

}
