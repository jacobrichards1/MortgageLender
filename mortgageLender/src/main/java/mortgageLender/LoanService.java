package mortgageLender;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class LoanService {

	Lender lender = new Lender();
	
	
	public void addFunds(int amt) {
		lender.setAvailableFunds(lender.getAvailableFunds() + amt);
	}
	
	public int getLenderFunds() {
		return lender.getAvailableFunds();
	}
	
	public List<Loan> getAllLoans(){
		return lender.getCurLoans();
	}
	
	public Loan createLoan(int request, int dti, int credit_score, int savings, Date d){
		List<Loan> ls = new ArrayList<Loan>();
		
		if (!(lender.getCurLoans() == null)) {
			for (Loan l : lender.getCurLoans()) {
				ls.add(l);
			}
		}
		Loan l = new Loan(request, dti, credit_score, savings, d);
		int qual = -1;
		if (request <= lender.getAvailableFunds()) {
			if (dti < 36) {
				if (credit_score > 620) {
					if (savings * 4 >= request) {
						qual = 1;
					}
					else {
						qual = 0;
					}
				}
			}
		}
		
		l.setQualification(qual);
		l.setStatus("pending");
		ls.add(l);
		lender.setCurLoans(ls);
		return l;
	}

	public void getApprovedLoans(){
		int i = 0;
		for (Loan l : lender.getCurLoans()) {
			if (l.getQualification() == 1 || l.getQualification() == 0) {
					if (l.getQualification() == 0) {
						i = l.getSavings() * 4;
						l.setLoanAmount(i);	
						l.setStatus("approved");
					}
					else {
						i = l.getRequestedAmount();
						l.setLoanAmount(i);
						l.setStatus("approved");
					} 
				
		}
			else {
				l.setLoanAmount(0);
				l.setStatus("denied");
			}
			
		}
		
	}
	
	public void appOrDen (boolean[] input) {
		
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		
		int i = 0;
		for (Loan l : lender.getCurLoans()) {
			l.setCustomerResponseToTheQuestionOfWouldTheyLikeTheLoanOrNot(input[i]);
			
			c1.setTime(l.getLoanDate());
			c2.setTime(l.getAcceptedDate());
			
			if (c2.get(Calendar.DATE) - c1.get(Calendar.DATE) > 3 || c2.get(Calendar.DATE) - c1.get(Calendar.DATE) < 0) {
				l.setStatus("expired");
				continue;
			}
			if (l.getStatus().equals("approved") && l.getCustomerResponseToTheQuestionOfWouldTheyLikeTheLoanOrNot()) {
				if (lender.getAvailableFunds() - l.getLoanAmount() >= 0) {
					l.setStatus("accepted");
					lender.setPendingFunds(l.getLoanAmount());
					lender.setAvailableFunds(lender.getAvailableFunds() - l.getLoanAmount());
				}
				else {
					l.setStatus("on hold");
				}
			}
			else {
				l.setStatus("rejected");
			}
			
			 
			
			
			i++;
		}
	}
	
	public List<Loan> filterStatus (String st){
		List<Loan> ls = new ArrayList<Loan>();
		for (Loan l : lender.getCurLoans()) {
			if (l.getStatus().equals(st)) {
				ls.add(l);
			}
		}
		return ls;
	}
	
	
	

}
