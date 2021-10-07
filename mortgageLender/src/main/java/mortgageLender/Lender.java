package mortgageLender;

import java.util.List;

public class Lender {
	
	private int availableFunds = 0;
	private int pendingFunds = 0;
	
	private List<Loan> curLoans;
	
	
	public Lender() {
		super();
	}
	
	public Lender(int availableFunds) {
		super();
		this.availableFunds = availableFunds;
	}
	
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
