package mortgageLender;

import java.util.ArrayList;
import java.util.List;

public class LoanService {

	Lender lender = new Lender();
	
	
	public void addFunds(int amt) {
		System.out.println(lender.getAvailableFunds());
		lender.setAvailableFunds(amt);
		
	}
	
	public int getLenderFunds() {
		return lender.getAvailableFunds();
	}
	
	public List<Loan> getAllLoans(){
		return lender.getCurLoans();
	}
	
	public Loan createLoan(int request, int dti, int credit_score, int savings){
		List<Loan> ls = new ArrayList<Loan>();
		
		if (!lender.getCurLoans().isEmpty()) {
			for (Loan l : lender.getCurLoans()) {
				ls.add(l);
			}
		}
		Loan l = new Loan(request, dti, credit_score, savings);
		int qual = -1;
		if (request < lender.getAvailableFunds()) {
			if (savings * 4 >= request) {
				if (dti < 36) {
					if (credit_score > 620) {
						qual = 1;
					}
					else {
						qual = 0;
					}
				}
			}
		}
		l.setLoan_id(lender.getCurLoans().size());
		l.setQualification(qual);
		ls.add(l);
		lender.setCurLoans(ls);
		return l;
	}

	

}
