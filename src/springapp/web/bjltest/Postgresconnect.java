package springapp.web.bjltest;

import java.sql.*;

public class Postgresconnect{
  public static void main(String[] args) {
  System.out.println("postgres Connect Example.");
  Connection conn = null;
  String url = "jdbc:postgresql://127.0.0.1:5432/";
  String dbName = "services";
  String driver = "org.postgresql.Driver";
  String userName = "postgres"; 
  String password = "vivek";
  try {
  Class.forName(driver).newInstance();
  conn = DriverManager.getConnection(url+dbName,userName,password);
  System.out.println("Connected to the database");
  Statement st=conn.createStatement();
  String query="SELECT firstname,lastname,ssn,name_type_desc,house_number,street_direction,street_name," +
  		"street_suffix,apartment_num,city,state,zipcode,filing_group_desc,bankruptcy_type_desc,docket_number FROM new_bjl_results WHERE result_id BETWEEN  '1324062' AND '1324068' AND filing_group_desc='BANKRUPTCY' GROUP BY firstname,lastname,ssn,name_type_desc,house_number,street_direction,street_name," +
  		"street_suffix,apartment_num,city,state,zipcode,filing_group_desc,bankruptcy_type_desc,docket_number ORDER BY firstname,lastname,ssn,name_type_desc,house_number,street_direction,street_name," +
  		"street_suffix,apartment_num,city,state,zipcode,filing_group_desc,bankruptcy_type_desc,docket_number ASC";
  ResultSet rs=st.executeQuery(query);
  while (rs.next()) {
	System.out.println("firstname: "+rs.getString("firstname")+"Lastname:"+rs.getString("lastname")+" Docket No:"+rs.getString("docket_number")+"state:"+rs.getString("state")+"name_type_desc:"+rs.getString("name_type_desc")+"name_type_desc:"+rs.getString("name_type_desc"));
}
  conn.close();
  System.out.println("Disconnected from database");
  } catch (Exception e) {
  e.printStackTrace();
  }
  }
}