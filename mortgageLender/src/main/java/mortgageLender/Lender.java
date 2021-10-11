package mortgageLender;

import java.util.List;

public class Lender {
	
	private int availableFunds = 0; //Funds that are available to give out
	private int pendingFunds = 0;  //Funds that have been approved by the lender and are waiting for customer's acceptance
	
	private List<Loan> curLoans;
	
	
	public Lender() {
		super();
	}
	
	public Lender(int availableFunds) {
		super();
		this.availableFunds = availableFunds;
	}
	
	//Setters and Getters
	
	public int getAvailableFunds() {
		return availableFunds;
	}
	public void setAvailableFunds(int availableFunds) {
		this.availableFunds = availableFunds;
	}
	public List<Loan> getCurLoans() {
		return curLoans;
	}
	public void setCurLoans(List<Loan> curLoans) {
		this.curLoans = curLoans;
	}
	
	public int getPendingFunds() {
		return pendingFunds;
	}

	public void setPendingFunds(int pendingFunds) {
		this.pendingFunds = pendingFunds;
	}
	
	
	
	

}
