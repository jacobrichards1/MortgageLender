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
	
	public int getPendingFunds() {
		return lender.getPendingFunds();
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
		
		int lID; 
		if (lender.getCurLoans() == null) {
			lID = 1;
		}
		else {
			lID = ls.size() + 1;
		}
			
		
		Loan l = new Loan(lID, request, dti, credit_score, savings, d);
		int qual = -1;
		if (request <= lender.getAvailableFunds() && dti < 36 && credit_score > 620) {			
			if (savings * 4 >= request) {
				qual = 1;
				l.setStatus("qualified");
			}
			else {
				qual = 0;
				l.setStatus("qualified");
			}		
		}
		else {
			l.setStatus("denied");
		}
		
		l.setQualification(qual);
		
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
						if (lender.getAvailableFunds() - i >= 0) {
							lender.setAvailableFunds(lender.getAvailableFunds() - i);
							lender.setPendingFunds(lender.getPendingFunds() + i);
						}
						else {
							l.setStatus("on hold");
						}
						
					}
					else {
						i = l.getRequestedAmount();
						l.setLoanAmount(i);
						l.setStatus("approved");
						if (lender.getAvailableFunds() - i >= 0) {
							lender.setAvailableFunds(lender.getAvailableFunds() - i);
							lender.setPendingFunds(lender.getPendingFunds() + i);
						}
						else {
							l.setStatus("on hold");
						}
					} 
				
		}
			else {
				l.setLoanAmount(0);
				l.setStatus("denied");
			}
			
		}
		
	}
	
	public void approveOnHoldLoan(Loan l) {
		int i = 0;
	
		if (l.getQualification() == 0) {
			i = l.getSavings() * 4;
			l.setLoanAmount(i);	
			if (lender.getAvailableFunds() - i >= 0) {
				l.setStatus("approved");
				lender.setAvailableFunds(lender.getAvailableFunds() - i);
				lender.setPendingFunds(lender.getPendingFunds() + i);
			}
		}
		else {
			i = l.getRequestedAmount();
			l.setLoanAmount(i);
			if (lender.getAvailableFunds() - i >= 0) {
				l.setStatus("approved");
				lender.setAvailableFunds(lender.getAvailableFunds() - i);
				lender.setPendingFunds(lender.getPendingFunds() + i);
			}
		}
	}
	
	public void appOrDen (boolean[] input) {
		
		
		int i = 0;
		for (Loan l : lender.getCurLoans()) {
			
			boolean b = checkExpiration(l);
			
			l.setCustomerResponseToTheQuestionOfWouldTheyLikeTheLoanOrNot(input[i]);
			
			if (b) {
				lender.setAvailableFunds(lender.getAvailableFunds() + l.getLoanAmount());
				lender.setPendingFunds(lender.getPendingFunds() - l.getLoanAmount());
				i++;
				continue;
			}
			else if (l.getStatus().equals("approved") && l.getCustomerResponseToTheQuestionOfWouldTheyLikeTheLoanOrNot()) {
					l.setStatus("accepted");			
					lender.setPendingFunds(lender.getPendingFunds() - l.getLoanAmount());
			}
			else {
				l.setStatus("rejected");
				lender.setAvailableFunds(lender.getAvailableFunds() + l.getLoanAmount());
				lender.setPendingFunds(lender.getPendingFunds() - l.getLoanAmount());
				
				List<Loan> holdList = filterStatus("on hold");
				for (Loan hl : holdList) {
					approveOnHoldLoan(hl);
				}
				
			}
			i++;
		}
	}
	
	
	
	public boolean checkExpiration(Loan lo) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		
		c1.setTime(lo.getLoanDate());
		c2.setTime(lo.getAcceptedDate());
			
		if (c2.get(Calendar.DATE) - c1.get(Calendar.DATE) > 3 || c2.get(Calendar.DATE) - c1.get(Calendar.DATE) < 0) {
				lo.setStatus("expired");
				return true;
		}
		return false;
		
	}
	
	public List<Loan> filterStatus (String st){
		List<Loan> ls = new ArrayList<Loan>();
		for (Loan l : lender.getCurLoans()) {
			if (l.getStatus() == st) {
				ls.add(l);
			}
		}
		return ls;
	}
	
	
	

}
