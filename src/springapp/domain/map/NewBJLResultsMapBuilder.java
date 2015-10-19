package springapp.domain.map;

import org.apache.torque.Torque;
import org.apache.torque.TorqueException;
import org.apache.torque.map.ColumnMap;
import org.apache.torque.map.DatabaseMap;
import org.apache.torque.map.MapBuilder;
import org.apache.torque.map.TableMap;

public class NewBJLResultsMapBuilder implements MapBuilder {
	/**
     * The name of this class
     */
    public static final String CLASS_NAME =
        "springapp.domain.map.NewBJLResultsMapBuilder";

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

        dbMap.addTable("new_bjl_results");
        TableMap tMap = dbMap.getTable("new_bjl_results");
        tMap.setJavaName("NewBJLResults");
        tMap.setOMClass( springapp.domain.NewBJLResults.class );
        tMap.setPeerClass( springapp.domain.NewBJLResultsPeer.class );
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
	    cMap.setForeignKey("new_bjl_searches", "user_search_id"); 
    cMap.setPosition(2);
  tMap.addColumn(cMap);
// ------------- Column: name_filing_state --------------------
cMap = new ColumnMap( "name_filing_state", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "name_filing_state" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
        cMap.setSize( 255 );
          cMap.setPosition(3);
  tMap.addColumn(cMap);
// ------------- Column: address --------------------
cMap = new ColumnMap( "firstname", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "firstname" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
        cMap.setSize( 255 );
          cMap.setPosition(4);
  tMap.addColumn(cMap);
// ------------- Column: middlename --------------------
cMap = new ColumnMap( "middlename", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "middlename" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
        cMap.setSize( 255 );
          cMap.setPosition(5);
  tMap.addColumn(cMap);
// ------------- Column: lastname --------------------
cMap = new ColumnMap( "lastname", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "lastname" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
        cMap.setSize( 255 );
          cMap.setPosition(6);
  tMap.addColumn(cMap);
// ------------- Column: name_suffix --------------------
cMap = new ColumnMap( "name_suffix", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "name_suffix" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
        cMap.setSize( 255 );
          cMap.setPosition(7);
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
          cMap.setPosition(8);
  tMap.addColumn(cMap);
// ------------- Column: name_type_desc --------------------
cMap = new ColumnMap( "name_type_desc", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "name_type_desc" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
        cMap.setSize( 255 );
          cMap.setPosition(9);
  tMap.addColumn(cMap);
// ------------- Column: ssn_taxid_flag --------------------
cMap = new ColumnMap( "ssn_taxid_flag", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "ssn_taxid_flag" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
        cMap.setSize( 255 );
          cMap.setPosition(10);
  tMap.addColumn(cMap);
// ------------- Column: ssn --------------------
cMap = new ColumnMap( "ssn", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "ssn" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
        cMap.setSize( 255 );
          cMap.setPosition(11);
  tMap.addColumn(cMap);
// ------------- Column: comment_sequence --------------------
cMap = new ColumnMap( "comment_sequence", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "comment_sequence" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
        cMap.setSize( 255 );
          cMap.setPosition(12);
  tMap.addColumn(cMap);
// ------------- Column: comment_type --------------------
cMap = new ColumnMap( "comment_type", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "comment_type" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
        cMap.setSize( 255 );
          cMap.setPosition(13);
  tMap.addColumn(cMap);
// ------------- Column: comment_type_desc --------------------
cMap = new ColumnMap( "comment_type_desc", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "comment_type_desc" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
        cMap.setSize( 255 );
          cMap.setPosition(14);
  tMap.addColumn(cMap);
// ------------- Column: house_number --------------------
cMap = new ColumnMap( "house_number", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "house_number" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
        cMap.setSize( 255 );
          cMap.setPosition(15);
  tMap.addColumn(cMap);
// ------------- Column: street_direction --------------------
cMap = new ColumnMap( "street_direction", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "street_direction" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
        cMap.setSize( 255 );
          cMap.setPosition(16);
  tMap.addColumn(cMap);
// ------------- Column: street_name --------------------
cMap = new ColumnMap( "street_name", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "street_name" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
        cMap.setSize( 255 );
          cMap.setPosition(17);
  tMap.addColumn(cMap);
// ------------- Column: street_suffix --------------------
cMap = new ColumnMap( "street_suffix", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "street_suffix" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
        cMap.setSize( 255 );
          cMap.setPosition(18);
  tMap.addColumn(cMap);
// ------------- Column: apartment_num --------------------
cMap = new ColumnMap( "apartment_num", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "apartment_num" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
        cMap.setSize( 255 );
          cMap.setPosition(19);
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
          cMap.setPosition(20);
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
          cMap.setPosition(21);
  tMap.addColumn(cMap);
// ------------- Column: zipcode --------------------
cMap = new ColumnMap( "zipcode", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "zipcode" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
        cMap.setSize( 255 );
          cMap.setPosition(22);
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
          cMap.setPosition(23);
  tMap.addColumn(cMap);
// ------------- Column: filing_group --------------------
cMap = new ColumnMap( "filing_group", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "filing_group" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
        cMap.setSize( 255 );
          cMap.setPosition(24);
  tMap.addColumn(cMap);
// ------------- Column: filing_group_desc --------------------
cMap = new ColumnMap( "filing_group_desc", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "filing_group_desc" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
        cMap.setSize( 255 );
          cMap.setPosition(25);
  tMap.addColumn(cMap);
// ------------- Column: filing_type --------------------
cMap = new ColumnMap( "filing_type", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "filing_type" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
        cMap.setSize( 255 );
          cMap.setPosition(26);
  tMap.addColumn(cMap);
// ------------- Column: filing_type_desc --------------------
cMap = new ColumnMap( "filing_type_desc", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "filing_type_desc" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
        cMap.setSize( 255 );
          cMap.setPosition(27);
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
          cMap.setPosition(28);
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
          cMap.setPosition(29);
  tMap.addColumn(cMap);
// ------------- Column: tax_lien_type --------------------
cMap = new ColumnMap( "tax_lien_type", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "tax_lien_type" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
        cMap.setSize( 255 );
          cMap.setPosition(30);
  tMap.addColumn(cMap);
// ------------- Column: tax_lien_type_desc --------------------
cMap = new ColumnMap( "tax_lien_type_desc", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "tax_lien_type_desc" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
        cMap.setSize( 255 );
          cMap.setPosition(31);
  tMap.addColumn(cMap);
// ------------- Column: generation_code --------------------
cMap = new ColumnMap( "bankruptcy_type", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "bankruptcy_type" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
        cMap.setSize( 255 );
          cMap.setPosition(32);
  tMap.addColumn(cMap);
// ------------- Column: bankruptcy_type_desc --------------------
cMap = new ColumnMap( "bankruptcy_type_desc", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "bankruptcy_type_desc" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
        cMap.setSize( 255 );
          cMap.setPosition(33);
  tMap.addColumn(cMap);
// ------------- Column: docket_number --------------------
cMap = new ColumnMap( "docket_number", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "docket_number" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
        cMap.setSize( 255 );
          cMap.setPosition(34);
  tMap.addColumn(cMap);
// ------------- Column: unlawful_detainer --------------------
cMap = new ColumnMap( "unlawful_detainer", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "unlawful_detainer" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
        cMap.setSize( 255 );
          cMap.setPosition(35);
  tMap.addColumn(cMap);
// ------------- Column: initial_date --------------------
cMap = new ColumnMap( "initial_date", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "initial_date" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
        cMap.setSize( 255 );
          cMap.setPosition(36);
  tMap.addColumn(cMap);
// ------------- Column: initial_amount --------------------
cMap = new ColumnMap( "initial_amount", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "initial_amount" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
        cMap.setSize( 255 );
          cMap.setPosition(37);
  tMap.addColumn(cMap);
// ------------- Column: initial_docket --------------------
cMap = new ColumnMap( "initial_docket", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "initial_docket" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
        cMap.setSize( 255 );
          cMap.setPosition(38);
  tMap.addColumn(cMap);
// ------------- Column: judgement_date --------------------
cMap = new ColumnMap( "judgement_date", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "judgement_date" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
        cMap.setSize( 255 );
          cMap.setPosition(39);
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
          cMap.setPosition(40);
  tMap.addColumn(cMap);
// ------------- Column: judgement_docket --------------------
cMap = new ColumnMap( "judgement_docket", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "judgement_docket" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
        cMap.setSize( 255 );
          cMap.setPosition(41);
  tMap.addColumn(cMap);
// ------------- Column: remove_date --------------------
cMap = new ColumnMap( "remove_date", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "remove_date" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
        cMap.setSize( 255 );
          cMap.setPosition(42);
  tMap.addColumn(cMap);
// ------------- Column: remove_docket --------------------
cMap = new ColumnMap( "remove_docket", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "remove_docket" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
        cMap.setSize( 255 );
          cMap.setPosition(43);
  tMap.addColumn(cMap);
// ------------- Column: dismissal_date --------------------
cMap = new ColumnMap( "dismissal_date", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "dismissal_date" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
        cMap.setSize( 255 );
          cMap.setPosition(44);
  tMap.addColumn(cMap);
// ------------- Column: dismissal_docket --------------------
cMap = new ColumnMap( "dismissal_docket", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "dismissal_docket" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
        cMap.setSize( 255 );
          cMap.setPosition(45);
  tMap.addColumn(cMap);
// ------------- Column: asset_amount --------------------
cMap = new ColumnMap( "asset_amount", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "asset_amount" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
        cMap.setSize( 255 );
          cMap.setPosition(46);
  tMap.addColumn(cMap);
  // ------------- Column: liability_amount --------------------
  cMap = new ColumnMap( "liability_amount", tMap);
  cMap.setType( "" );
  cMap.setTorqueType( "VARCHAR" );
  cMap.setUsePrimitive(true);
  cMap.setPrimaryKey(false);
	cMap.setNotNull(true);
  cMap.setJavaName( "liability_amount" );
  cMap.setAutoIncrement(false);
	cMap.setProtected(false);
    		cMap.setInheritance("false");
              cMap.setSize( 255 );
 	              cMap.setPosition(47);
        tMap.addColumn(cMap);
   // ------------- Column: plaintiff --------------------
  cMap = new ColumnMap( "plaintiff", tMap);
  cMap.setType( "" );
  cMap.setTorqueType( "VARCHAR" );
  cMap.setUsePrimitive(true);
  cMap.setPrimaryKey(false);
	cMap.setNotNull(true);
  cMap.setJavaName( "plaintiff" );
  cMap.setAutoIncrement(false);
	cMap.setProtected(false);
    		cMap.setInheritance("false");
                  cMap.setSize( 255 );
     	              cMap.setPosition(48);
            tMap.addColumn(cMap);
 // ------------- Column: beneficiary --------------------
cMap = new ColumnMap( "beneficiary", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "beneficiary" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
            cMap.setSize( 255 );
              cMap.setPosition(49);
      tMap.addColumn(cMap);
   // ------------- Column: situs --------------------
  cMap = new ColumnMap( "situs", tMap);
  cMap.setType( "" );
  cMap.setTorqueType( "VARCHAR" );
  cMap.setUsePrimitive(true);
  cMap.setPrimaryKey(false);
	cMap.setNotNull(true);
  cMap.setJavaName( "situs" );
  cMap.setAutoIncrement(false);
	cMap.setProtected(false);
    		cMap.setInheritance("false");
          cMap.setSize( 255 );
              cMap.setPosition(50);
    tMap.addColumn(cMap);
 // ------------- Column: trustee --------------------
cMap = new ColumnMap( "trustee", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "trustee" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
            cMap.setSize( 255 );
              cMap.setPosition(51);
      tMap.addColumn(cMap);
   // ------------- Column: county_code --------------------
  cMap = new ColumnMap( "county_code", tMap);
  cMap.setType( "" );
  cMap.setTorqueType( "VARCHAR" );
  cMap.setUsePrimitive(true);
  cMap.setPrimaryKey(false);
	cMap.setNotNull(true);
  cMap.setJavaName( "county_code" );
  cMap.setAutoIncrement(false);
	cMap.setProtected(false);
    		cMap.setInheritance("false");
          cMap.setSize( 255 );
              cMap.setPosition(52);
    tMap.addColumn(cMap);
 // ------------- Column: county_name --------------------
cMap = new ColumnMap( "county_name", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "county_name" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
            cMap.setSize( 255 );
              cMap.setPosition(53);
      tMap.addColumn(cMap);
   // ------------- Column: county_state --------------------
  cMap = new ColumnMap( "county_state", tMap);
  cMap.setType( "" );
  cMap.setTorqueType( "VARCHAR" );
  cMap.setUsePrimitive(true);
  cMap.setPrimaryKey(false);
	cMap.setNotNull(true);
  cMap.setJavaName( "county_state" );
  cMap.setAutoIncrement(false);
	cMap.setProtected(false);
    		cMap.setInheritance("false");
          cMap.setSize( 255 );
              cMap.setPosition(54);
    tMap.addColumn(cMap);
 // ------------- Column: court_code --------------------
cMap = new ColumnMap( "court_code", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "court_code" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
            cMap.setSize( 255 );
              cMap.setPosition(55);
      tMap.addColumn(cMap);
   // ------------- Column: court_desc --------------------
  cMap = new ColumnMap( "court_desc", tMap);
  cMap.setType( "" );
  cMap.setTorqueType( "VARCHAR" );
  cMap.setUsePrimitive(true);
  cMap.setPrimaryKey(false);
	cMap.setNotNull(true);
  cMap.setJavaName( "court_desc" );
  cMap.setAutoIncrement(false);
	cMap.setProtected(false);
    		cMap.setInheritance("false");
                      cMap.setSize( 255 );
         	              cMap.setPosition(56);
                tMap.addColumn(cMap);
 // ------------- Column: court_state --------------------
cMap = new ColumnMap( "court_state", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "court_state" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
            cMap.setSize( 255 );
              cMap.setPosition(57);
      tMap.addColumn(cMap);
   // ------------- Column: assets_available --------------------
  cMap = new ColumnMap( "assets_available", tMap);
  cMap.setType( "" );
  cMap.setTorqueType( "VARCHAR" );
  cMap.setUsePrimitive(true);
  cMap.setPrimaryKey(false);
	cMap.setNotNull(true);
  cMap.setJavaName( "assets_available" );
  cMap.setAutoIncrement(false);
	cMap.setProtected(false);
    		cMap.setInheritance("false");
          cMap.setSize( 255 );
              cMap.setPosition(58);
    tMap.addColumn(cMap);
 // ------------- Column: perfected_date --------------------
cMap = new ColumnMap( "perfected_date", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "perfected_date" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
            cMap.setSize( 255 );
              cMap.setPosition(59);
      tMap.addColumn(cMap);
   // ------------- Column: action_state_code --------------------
  cMap = new ColumnMap( "action_state_code", tMap);
  cMap.setType( "" );
  cMap.setTorqueType( "VARCHAR" );
  cMap.setUsePrimitive(true);
  cMap.setPrimaryKey(false);
	cMap.setNotNull(true);
  cMap.setJavaName( "action_state_code" );
  cMap.setAutoIncrement(false);
	cMap.setProtected(false);
    		cMap.setInheritance("false");
              cMap.setSize( 255 );
 	              cMap.setPosition(60);
        tMap.addColumn(cMap);
 // ------------- Column: action_desc --------------------
cMap = new ColumnMap( "action_desc", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "action_desc" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
            cMap.setSize( 255 );
              cMap.setPosition(61);
      tMap.addColumn(cMap);
   // ------------- Column: disposition_state_code --------------------
  cMap = new ColumnMap( "disposition_state_code", tMap);
  cMap.setType( "" );
  cMap.setTorqueType( "VARCHAR" );
  cMap.setUsePrimitive(true);
  cMap.setPrimaryKey(false);
	cMap.setNotNull(true);
  cMap.setJavaName( "disposition_state_code" );
  cMap.setAutoIncrement(false);
	cMap.setProtected(false);
    		cMap.setInheritance("false");
          cMap.setSize( 255 );
              cMap.setPosition(62);
    tMap.addColumn(cMap);
 // ------------- Column: disposition_desc --------------------
cMap = new ColumnMap( "disposition_desc", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "disposition_desc" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
            cMap.setSize( 255 );
              cMap.setPosition(63);
      tMap.addColumn(cMap);
   // ------------- Column: amount --------------------
  cMap = new ColumnMap( "amount", tMap);
  cMap.setType( "" );
  cMap.setTorqueType( "VARCHAR" );
  cMap.setUsePrimitive(true);
  cMap.setPrimaryKey(false);
	cMap.setNotNull(true);
  cMap.setJavaName( "amount" );
  cMap.setAutoIncrement(false);
	cMap.setProtected(false);
    		cMap.setInheritance("false");
          cMap.setSize( 255 );
              cMap.setPosition(64);
    tMap.addColumn(cMap);
 // ------------- Column: release_date --------------------
    cMap = new ColumnMap( "release_date", tMap);
    cMap.setType( "" );
    cMap.setTorqueType( "VARCHAR" );
    cMap.setUsePrimitive(true);
    cMap.setPrimaryKey(false);
	cMap.setNotNull(true);
    cMap.setJavaName( "release_date" );
    cMap.setAutoIncrement(false);
	cMap.setProtected(false);
      		cMap.setInheritance("false");
            cMap.setSize( 255 );
              cMap.setPosition(65);
      tMap.addColumn(cMap);
   // ------------- Column: release_number --------------------
      cMap = new ColumnMap( "release_number", tMap);
      cMap.setType( "" );
      cMap.setTorqueType( "VARCHAR" );
      cMap.setUsePrimitive(true);
      cMap.setPrimaryKey(false);
		cMap.setNotNull(true);
      cMap.setJavaName( "release_number" );
      cMap.setAutoIncrement(false);
		cMap.setProtected(false);
        		cMap.setInheritance("false");
              cMap.setSize( 255 );
 	              cMap.setPosition(66);
        tMap.addColumn(cMap);
 // ------------- Column: suit_case_number --------------------
    cMap = new ColumnMap( "suit_case_number", tMap);
    cMap.setType( "" );
    cMap.setTorqueType( "VARCHAR" );
    cMap.setUsePrimitive(true);
    cMap.setPrimaryKey(false);
	cMap.setNotNull(true);
    cMap.setJavaName( "suit_case_number" );
    cMap.setAutoIncrement(false);
	cMap.setProtected(false);
      		cMap.setInheritance("false");
	                    cMap.setSize( 255 );
	       	              cMap.setPosition(67);
	              tMap.addColumn(cMap);
   // ------------- Column: suit_date --------------------
  cMap = new ColumnMap( "suit_date", tMap);
  cMap.setType( "" );
  cMap.setTorqueType( "VARCHAR" );
  cMap.setUsePrimitive(true);
  cMap.setPrimaryKey(false);
	cMap.setNotNull(true);
  cMap.setJavaName( "suit_date" );
  cMap.setAutoIncrement(false);
	cMap.setProtected(false);
    		cMap.setInheritance("false");
          cMap.setSize( 255 );
              cMap.setPosition(68);
    tMap.addColumn(cMap);
 // ------------- Column: suit_amount --------------------
cMap = new ColumnMap( "suit_amount", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "suit_amount" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
            cMap.setSize( 255 );
              cMap.setPosition(69);
      tMap.addColumn(cMap);
   // ------------- Column: satisfaction_date --------------------
  cMap = new ColumnMap( "satisfaction_date", tMap);
  cMap.setType( "" );
  cMap.setTorqueType( "VARCHAR" );
  cMap.setUsePrimitive(true);
  cMap.setPrimaryKey(false);
	cMap.setNotNull(true);
  cMap.setJavaName( "satisfaction_date" );
  cMap.setAutoIncrement(false);
	cMap.setProtected(false);
    		cMap.setInheritance("false");
          cMap.setSize( 255 );
              cMap.setPosition(70);
    tMap.addColumn(cMap);
 // ------------- Column: discharge_date --------------------
cMap = new ColumnMap( "discharge_date", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "discharge_date" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
            cMap.setSize( 255 );
              cMap.setPosition(71);
      tMap.addColumn(cMap);
   // ------------- Column: closed_date --------------------
  cMap = new ColumnMap( "closed_date", tMap);
  cMap.setType( "" );
  cMap.setTorqueType( "VARCHAR" );
  cMap.setUsePrimitive(true);
  cMap.setPrimaryKey(false);
	cMap.setNotNull(true);
  cMap.setJavaName( "closed_date" );
  cMap.setAutoIncrement(false);
	cMap.setProtected(false);
    		cMap.setInheritance("false");
          cMap.setSize( 255 );
              cMap.setPosition(72);
    tMap.addColumn(cMap);
 // ------------- Column: trust_deed_number --------------------
cMap = new ColumnMap( "trust_deed_number", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "trust_deed_number" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
            cMap.setSize( 255 );
              cMap.setPosition(73);
      tMap.addColumn(cMap);
   // ------------- Column: trust_deed_date --------------------
  cMap = new ColumnMap( "trust_deed_date", tMap);
  cMap.setType( "" );
  cMap.setTorqueType( "VARCHAR" );
  cMap.setUsePrimitive(true);
  cMap.setPrimaryKey(false);
	cMap.setNotNull(true);
  cMap.setJavaName( "trust_deed_date" );
  cMap.setAutoIncrement(false);
	cMap.setProtected(false);
    		cMap.setInheritance("false");
          cMap.setSize( 255 );
              cMap.setPosition(74);
    tMap.addColumn(cMap);
 // ------------- Column: sale_number --------------------
    cMap = new ColumnMap( "sale_number", tMap);
    cMap.setType( "" );
    cMap.setTorqueType( "VARCHAR" );
    cMap.setUsePrimitive(true);
    cMap.setPrimaryKey(false);
	cMap.setNotNull(true);
    cMap.setJavaName( "sale_number" );
    cMap.setAutoIncrement(false);
	cMap.setProtected(false);
      		cMap.setInheritance("false");
            cMap.setSize( 255 );
              cMap.setPosition(75);
      tMap.addColumn(cMap);
   // ------------- Column: sale_date --------------------
      cMap = new ColumnMap( "sale_date", tMap);
      cMap.setType( "" );
      cMap.setTorqueType( "VARCHAR" );
      cMap.setUsePrimitive(true);
      cMap.setPrimaryKey(false);
		cMap.setNotNull(true);
      cMap.setJavaName( "sale_date" );
      cMap.setAutoIncrement(false);
		cMap.setProtected(false);
        		cMap.setInheritance("false");
              cMap.setSize( 255 );
 	              cMap.setPosition(76);
        tMap.addColumn(cMap);
        // ------------- Column: cancellation_number --------------------
        cMap = new ColumnMap( "cancellation_number", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "cancellation_number" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 255 );
   	              cMap.setPosition(77);
          tMap.addColumn(cMap);
          // ------------- Column: cancellation_date --------------------
          cMap = new ColumnMap( "cancellation_date", tMap);
          cMap.setType( "" );
          cMap.setTorqueType( "VARCHAR" );
          cMap.setUsePrimitive(true);
          cMap.setPrimaryKey(false);
    		cMap.setNotNull(true);
          cMap.setJavaName( "cancellation_date" );
          cMap.setAutoIncrement(false);
    		cMap.setProtected(false);
            		cMap.setInheritance("false");
                  cMap.setSize( 255 );
     	              cMap.setPosition(78);
            tMap.addColumn(cMap);
// ------------- Column: sched_341_date --------------------
cMap = new ColumnMap( "sched_341_date", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "sched_341_date" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
        cMap.setSize( 255 );
          cMap.setPosition(79);
  tMap.addColumn(cMap);
  // ------------- Column: update_date --------------------
  cMap = new ColumnMap( "update_date", tMap);
  cMap.setType( "" );
  cMap.setTorqueType( "VARCHAR" );
  cMap.setUsePrimitive(true);
  cMap.setPrimaryKey(false);
	cMap.setNotNull(true);
  cMap.setJavaName( "update_date" );
  cMap.setAutoIncrement(false);
	cMap.setProtected(false);
    		cMap.setInheritance("false");
          cMap.setSize( 255 );
              cMap.setPosition(80);
    tMap.addColumn(cMap);
    //Udhay Aug8==> New Tracers fields addedd 
    // ------------- Column: fullname --------------------
    cMap = new ColumnMap( "fullname", tMap);
    cMap.setType( "" );
    cMap.setTorqueType( "VARCHAR" );
    cMap.setUsePrimitive(true);
    cMap.setPrimaryKey(false);
	cMap.setNotNull(true);
    cMap.setJavaName( "fullname" );
    cMap.setAutoIncrement(false);
	cMap.setProtected(false);
      		cMap.setInheritance("false");
            cMap.setSize( 255 );
              cMap.setPosition(80);
      tMap.addColumn(cMap);
      // ------------- Column: businessname --------------------
      cMap = new ColumnMap( "businessname", tMap);
      cMap.setType( "" );
      cMap.setTorqueType( "VARCHAR" );
      cMap.setUsePrimitive(true);
      cMap.setPrimaryKey(false);
		cMap.setNotNull(true);
      cMap.setJavaName( "businessname" );
      cMap.setAutoIncrement(false);
		cMap.setProtected(false);
        		cMap.setInheritance("false");
              cMap.setSize( 255 );
 	              cMap.setPosition(80);
        tMap.addColumn(cMap);
// ------------- Column: dob --------------------
cMap = new ColumnMap( "dob", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "dob" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
        cMap.setSize( 255 );
          cMap.setPosition(80);
  tMap.addColumn(cMap);
  // ------------- Column: court_address --------------------
  cMap = new ColumnMap( "court_address", tMap);
  cMap.setType( "" );
  cMap.setTorqueType( "VARCHAR" );
  cMap.setUsePrimitive(true);
  cMap.setPrimaryKey(false);
	cMap.setNotNull(true);
  cMap.setJavaName( "court_address" );
  cMap.setAutoIncrement(false);
	cMap.setProtected(false);
    		cMap.setInheritance("false");
          cMap.setSize( 255 );
              cMap.setPosition(80);
    tMap.addColumn(cMap);
 // ------------- Column: court_city --------------------
cMap = new ColumnMap( "court_city", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "court_city" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
        cMap.setSize( 255 );
          cMap.setPosition(80);
  tMap.addColumn(cMap);
// ------------- Column: court_zip --------------------
cMap = new ColumnMap( "court_zip", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
cMap.setNotNull(true);
cMap.setJavaName( "court_zip" );
cMap.setAutoIncrement(false);
cMap.setProtected(false);
  		cMap.setInheritance("false");
        cMap.setSize( 255 );
          cMap.setPosition(80);
  tMap.addColumn(cMap);
  // ------------- Column: court_phone --------------------
  cMap = new ColumnMap( "court_phone", tMap);
  cMap.setType( "" );
  cMap.setTorqueType( "VARCHAR" );
  cMap.setUsePrimitive(true);
  cMap.setPrimaryKey(false);
	cMap.setNotNull(true);
  cMap.setJavaName( "court_phone" );
  cMap.setAutoIncrement(false);
	cMap.setProtected(false);
    		cMap.setInheritance("false");
      cMap.setSize( 255 );
          cMap.setPosition(80);
tMap.addColumn(cMap);
//
//------------- Column: sched_341_time --------------------
cMap = new ColumnMap( "sched_341_time", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
	cMap.setNotNull(true);
cMap.setJavaName( "sched_341_time" );
cMap.setAutoIncrement(false);
	cMap.setProtected(false);
 		cMap.setInheritance("false");
   cMap.setSize( 255 );
       cMap.setPosition(80);
tMap.addColumn(cMap);
//------------- Column: judge --------------------
cMap = new ColumnMap( "judge", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
	cMap.setNotNull(true);
cMap.setJavaName( "judge" );
cMap.setAutoIncrement(false);
	cMap.setProtected(false);
 		cMap.setInheritance("false");
   cMap.setSize( 255 );
       cMap.setPosition(80);
tMap.addColumn(cMap);
//------------- Column: lawfirm --------------------
cMap = new ColumnMap( "lawfirm", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
	cMap.setNotNull(true);
cMap.setJavaName( "lawfirm" );
cMap.setAutoIncrement(false);
	cMap.setProtected(false);
 		cMap.setInheritance("false");
   cMap.setSize( 255 );
       cMap.setPosition(80);
tMap.addColumn(cMap);
//------------- Column: book --------------------
cMap = new ColumnMap( "book", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
	cMap.setNotNull(true);
cMap.setJavaName( "book" );
cMap.setAutoIncrement(false);
	cMap.setProtected(false);
		cMap.setInheritance("false");
 cMap.setSize( 255 );
     cMap.setPosition(80);
tMap.addColumn(cMap);
//------------- Column: page --------------------
cMap = new ColumnMap( "page", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
	cMap.setNotNull(true);
cMap.setJavaName( "page" );
cMap.setAutoIncrement(false);
	cMap.setProtected(false);
		cMap.setInheritance("false");
 cMap.setSize( 255 );
     cMap.setPosition(80);
tMap.addColumn(cMap);
//------------- Column: origdept --------------------
cMap = new ColumnMap( "origdept", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
	cMap.setNotNull(true);
cMap.setJavaName( "origdept" );
cMap.setAutoIncrement(false);
	cMap.setProtected(false);
		cMap.setInheritance("false");
 cMap.setSize( 255 );
     cMap.setPosition(80);
tMap.addColumn(cMap);
//------------- Column: origcase --------------------
cMap = new ColumnMap( "origcase", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
	cMap.setNotNull(true);
cMap.setJavaName( "origcase" );
cMap.setAutoIncrement(false);
	cMap.setProtected(false);
		cMap.setInheritance("false");
 cMap.setSize( 255 );
     cMap.setPosition(80);
tMap.addColumn(cMap);
//------------- Column: origbook --------------------
cMap = new ColumnMap( "origbook", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
	cMap.setNotNull(true);
cMap.setJavaName( "origbook" );
cMap.setAutoIncrement(false);
	cMap.setProtected(false);
		cMap.setInheritance("false");
cMap.setSize( 255 );
   cMap.setPosition(80);
tMap.addColumn(cMap);
//------------- Column: origpage --------------------
cMap = new ColumnMap( "origpage", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
	cMap.setNotNull(true);
cMap.setJavaName( "origpage" );
cMap.setAutoIncrement(false);
	cMap.setProtected(false);
		cMap.setInheritance("false");
cMap.setSize( 255 );
   cMap.setPosition(80);
tMap.addColumn(cMap);
//------------- Column: assoccode --------------------
cMap = new ColumnMap( "assoccode", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
	cMap.setNotNull(true);
cMap.setJavaName( "assoccode" );
cMap.setAutoIncrement(false);
	cMap.setProtected(false);
		cMap.setInheritance("false");
cMap.setSize( 255 );
   cMap.setPosition(80);
tMap.addColumn(cMap);
//------------- Column: actiontype --------------------
cMap = new ColumnMap( "actiontype", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
	cMap.setNotNull(true);
cMap.setJavaName( "actiontype" );
cMap.setAutoIncrement(false);
	cMap.setProtected(false);
		cMap.setInheritance("false");
cMap.setSize( 255 );
   cMap.setPosition(80);
tMap.addColumn(cMap);
//------------- Column: actiontypedesc --------------------
cMap = new ColumnMap( "actiontypedesc", tMap);
cMap.setType( "" );
cMap.setTorqueType( "VARCHAR" );
cMap.setUsePrimitive(true);
cMap.setPrimaryKey(false);
	cMap.setNotNull(true);
cMap.setJavaName( "actiontypedesc" );
cMap.setAutoIncrement(false);
	cMap.setProtected(false);
		cMap.setInheritance("false");
cMap.setSize( 255 );
   cMap.setPosition(80);
tMap.addColumn(cMap);
                                                                                       
        tMap.setUseInheritance(false);
    }

}
