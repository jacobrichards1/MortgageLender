package mortgageLender;

import java.util.ArrayList;
import java.util.List;

public class LoanService {

	Lender lender = new Lender();
	
	public void addFunds(int amt) {
		lender.setAvailableFunds(amt);
	}
	
	public List<Loan> createLoan(int request, int dti, int credit_score, int savings){
		List<Loan> ls = new ArrayList<Loan>();
		Loan l = new Loan();
		
		return ls;
	}

	

}
