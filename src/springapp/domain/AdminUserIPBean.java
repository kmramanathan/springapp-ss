package springapp.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import net.searchsystems.limestone.bean.SsPassQuestionsBean;


@Entity
@Table(name="users_ip")
public class AdminUserIPBean implements Serializable {
	

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


    /** The value for the userId field */
	@Id
	@GeneratedValue
	@Column(name="id")
    private int id; 
	
	@Column(name="user_id")
    private int user_id;      
    
	@Column(name="users_ip")
    private String users_ip;
      
   
	//UserIp
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getUserId() {
		return user_id;
	}

	public void setUserId(int userId) {
		this.user_id = userId;
	}
		

	public String getUsersIP() {
		return users_ip;
	}

	public void setUsersIP(String users_ip) {
		this.users_ip = users_ip;
	}

	

  

}
