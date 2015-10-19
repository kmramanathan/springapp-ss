package springapp.repository;

import springapp.domain.Transaction;

public interface TransactionDao {   
	public Transaction getTransaction(int userId);
    //public void createTransaction(Transaction txn);
    public void saveTransaction(Transaction txn);
}