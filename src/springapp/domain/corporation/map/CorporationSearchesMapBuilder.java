package springapp.domain.corporation.map;

import java.util.Date;

import org.apache.torque.Torque;
import org.apache.torque.TorqueException;
import org.apache.torque.map.ColumnMap;
import org.apache.torque.map.DatabaseMap;
import org.apache.torque.map.MapBuilder;
import org.apache.torque.map.TableMap;

public class CorporationSearchesMapBuilder implements MapBuilder{
	/**
     * The name of this class
     */
    public static final String CLASS_NAME =
        "springapp.domain.corporation.map.CorporationSearchesMapBuilder";

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

        dbMap.addTable("corporation_searches");
        TableMap tMap = dbMap.getTable("corporation_searches");
        tMap.setJavaName("CorporationSearches");
        tMap.setOMClass( springapp.domain.corporation.CorporationSearches.class );
        tMap.setPeerClass( springapp.domain.corporation.CorporationSearchesPeer.class );
	    tMap.setPrimaryKeyMethod(TableMap.NATIVE);
        // this might need upgrading based on what all the databases
        // need, but for now assume one parameter.
          tMap.setPrimaryKeyMethodInfo("ss_user_searches_seq");

        ColumnMap cMap = null;


    // ------------- Column: user_search_id --------------------
        cMap = new ColumnMap( "user_search_id", tMap);
        cMap.setType( new Long(0) );
        cMap.setTorqueType( "BIGINT" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(true);
  		cMap.setNotNull(true);
        cMap.setJavaName( "UserSearchId" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                    cMap.setPosition(1);
          tMap.addColumn(cMap);
    // ------------- Column: search_category_id --------------------
        cMap = new ColumnMap( "search_category_id", tMap);
        cMap.setType( new Short((short)0) );
        cMap.setTorqueType( "SMALLINT" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "SearchCategoryId" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                    cMap.setPosition(2);
          tMap.addColumn(cMap);
    // ------------- Column: search_sub_category_id --------------------
        cMap = new ColumnMap( "search_sub_category_id", tMap);
        cMap.setType( new Short((short)0) );
        cMap.setTorqueType( "SMALLINT" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "SearchSubCategoryId" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                    cMap.setPosition(3);
          tMap.addColumn(cMap);
    // ------------- Column: user_id --------------------
        cMap = new ColumnMap( "user_id", tMap);
        cMap.setType( new Integer(0) );
        cMap.setTorqueType( "INTEGER" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "UserId" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                    cMap.setPosition(4);
          tMap.addColumn(cMap);
    // ------------- Column: transaction_id --------------------
        cMap = new ColumnMap( "transaction_id", tMap);
        cMap.setType( new Integer(0) );
        cMap.setTorqueType( "INTEGER" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "TransactionId" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                    cMap.setPosition(5);
          tMap.addColumn(cMap);
    // ------------- Column: match_count --------------------
        cMap = new ColumnMap( "match_count", tMap);
        cMap.setType( new Short((short)0) );
        cMap.setTorqueType( "SMALLINT" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "MatchCount" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                    cMap.setPosition(6);
          tMap.addColumn(cMap);
    // ------------- Column: create_date --------------------
        cMap = new ColumnMap( "create_date", tMap);
        cMap.setType( new Date() );
        cMap.setTorqueType( "TIMESTAMP" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "CreateDate" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                    cMap.setPosition(7);
          tMap.addColumn(cMap);
    // ------------- Column: completed --------------------
        cMap = new ColumnMap( "completed", tMap);
        cMap.setType( new Boolean(true) );
        cMap.setTorqueType( "BIT" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "Completed" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                    cMap.setPosition(8);
          tMap.addColumn(cMap);
    // ------------- Column: search_status_id --------------------
        cMap = new ColumnMap( "search_status_id", tMap);
        cMap.setType( new Short((short)0) );
        cMap.setTorqueType( "SMALLINT" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "SearchStatusId" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                    cMap.setPosition(9);
          tMap.addColumn(cMap);
    // ------------- Column: name --------------------
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
                cMap.setSize( 100 );
   	              cMap.setPosition(10);
          tMap.addColumn(cMap);
    // ------------- Column: defendant --------------------
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
                cMap.setSize( 100 );
   	              cMap.setPosition(11);
          tMap.addColumn(cMap);
    // ------------- Column: defendant_address --------------------
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
                cMap.setSize( 100 );
   	              cMap.setPosition(12);
          tMap.addColumn(cMap);
    // ------------- Column: defendant_city --------------------
        cMap = new ColumnMap( "initial", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(true);
        cMap.setJavaName( "initial" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 100 );
   	              cMap.setPosition(13);
          tMap.addColumn(cMap);
    // ------------- Column: defendant_state --------------------
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
                cMap.setSize( 2 );
   	              cMap.setPosition(14);
          tMap.addColumn(cMap);
          
       // ------------- Column: invoiced --------------------
          cMap = new ColumnMap( "invoiced", tMap);
          cMap.setType( "" );
          cMap.setTorqueType( "VARCHAR" );
          cMap.setUsePrimitive(true);
          cMap.setPrimaryKey(false);
    		cMap.setNotNull(true);
          cMap.setJavaName( "Invoiced" );
          cMap.setAutoIncrement(false);
    		cMap.setProtected(false);
            		cMap.setInheritance("false");
                  cMap.setSize( 2 );
     	              cMap.setPosition(15);
            tMap.addColumn(cMap);
    
    // ------------- Column: reference_code --------------------
        cMap = new ColumnMap( "reference", tMap);
        cMap.setType( "" );
        cMap.setTorqueType( "VARCHAR" );
        cMap.setUsePrimitive(true);
        cMap.setPrimaryKey(false);
  		cMap.setNotNull(false);
        cMap.setJavaName( "reference" );
        cMap.setAutoIncrement(false);
  		cMap.setProtected(false);
          		cMap.setInheritance("false");
                cMap.setSize( 50 );
   	              cMap.setPosition(16);
          tMap.addColumn(cMap);
        tMap.setUseInheritance(false);
    }

}
