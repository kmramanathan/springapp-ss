package springapp.manager;

import java.util.ArrayList;
import java.util.HashMap;

import net.searchsystems.limestone.bean.BGCOffenseBean;
import net.searchsystems.limestone.bean.BGCOffenseSupplementBean;

/**
 * A physical representation of a Bankruptcies, Judgments and Liens search.
 */
public class BGCOffenderMapHolder {
	protected HashMap<String,String> mapPersonal;
	protected HashMap<String,String> mapAddress;
	protected ArrayList<HashMap<String,String>> listAliases;
	protected HashMap<String,String> mapRecord;
	//protected ArrayList<HashMap<String,String>> listOffenses;
	//protected HashMap<String,String> mapSupplements;
	//protected ArrayList<HashMap<String,String>> listSupplements;
	protected ArrayList<BGCOffenseBean> listOffenses;
	protected ArrayList<BGCOffenseSupplementBean> listSupplements;
	
	public HashMap<String, String> getMapPersonal() {
		return mapPersonal;
	}
	public void setMapPersonal(HashMap<String, String> mapPersonal) {
		this.mapPersonal = mapPersonal;
	}
	public HashMap<String, String> getMapAddress() {
		return mapAddress;
	}
	public void setMapAddress(HashMap<String, String> mapAddress) {
		this.mapAddress = mapAddress;
	}
	public ArrayList<HashMap<String, String>> getListAliases() {
		return listAliases;
	}
	public void setListAliases(ArrayList<HashMap<String, String>> listAliases) {
		this.listAliases = listAliases;
	}
	public HashMap<String, String> getMapRecord() {
		return mapRecord;
	}
	public void setMapRecord(HashMap<String, String> mapRecord) {
		this.mapRecord = mapRecord;
	}
	public ArrayList<BGCOffenseBean> getListOffenses() {
		return listOffenses;
	}
	public void setListOffenses(ArrayList<BGCOffenseBean> listOffenses) {
		this.listOffenses = listOffenses;
	}
	public ArrayList<BGCOffenseSupplementBean> getListSupplements() {
		return listSupplements;
	}
	public void setListSupplements(ArrayList<BGCOffenseSupplementBean> listSupplements) {
		this.listSupplements = listSupplements;
	}
}