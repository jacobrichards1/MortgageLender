package mortgageLender;

import java.util.List;

public class Lender {
	
	private int availableFunds;
	private List<Loan> curLoans;
	private List<Loan> onHoldLoans;
	
	public Lender(int availableFunds, List<Loan> curLoans) {
		super();
		this.availableFunds = availableFunds;
		this.curLoans = curLoans;
	}
	
	public Lender(int availableFunds) {
		super();
		this.availableFunds = availableFunds;
	}
	
	public Lender() {
		super();
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
	
	
	
	

}
