package mortgageLender;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class LoanService {

	Lender lender = new Lender();
	
	
	public void addFunds(int amt) { //Adds money to availableFunds
		lender.setAvailableFunds(lender.getAvailableFunds() + amt);
	}
	
	public int getLenderFunds() { //gets AvailableFunds
		return lender.getAvailableFunds();
	}
	
	public int getPendingFunds() { //gets PendingFunds
		return lender.getPendingFunds();
	}
	
	public List<Loan> getAllLoans(){ //gets all the loan applications
		return lender.getCurLoans();
	}
	
	public Loan createLoan(int request, int dti, int credit_score, int savings, Date d){ //creates loan applications
		List<Loan> ls = new ArrayList<Loan>();
		
		if (!(lender.getCurLoans() == null)) {
			for (Loan l : lender.getCurLoans()) {
				ls.add(l);
			}
		}
		
		int lID; 
		if (lender.getCurLoans() == null) { //assigns loanID
			lID = 1;
		}
		else {
			lID = ls.size() + 1;
		}
			
		
		Loan l = new Loan(lID, request, dti, credit_score, savings, d);
		int qual = -1;
		if (request <= lender.getAvailableFunds() && dti < 36 && credit_score > 620) {	//Assigns qualification to each loan		
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

	public void getApprovedLoans(){ //Gets loans that are fully qualified and partially qualified
		int i = 0;
		for (Loan l : lender.getCurLoans()) {
			if (l.getQualification() == 1 || l.getQualification() == 0) {				
					if (l.getQualification() == 0) {
						i = l.getSavings() * 4;
						l.setLoanAmount(i);	
						l.setStatus("approved"); //Application is approved if it meets all the requirements
						if (lender.getAvailableFunds() - i >= 0) {
							lender.setAvailableFunds(lender.getAvailableFunds() - i);
							lender.setPendingFunds(lender.getPendingFunds() + i);
						}
						else {
							l.setStatus("on hold"); //Puts application on hold if there isn't enough money in availableFunds
						}
						
					}
					else {
						i = l.getRequestedAmount();
						l.setLoanAmount(i);
						l.setStatus("approved"); //Application is approved if it meets all the requirements
						if (lender.getAvailableFunds() - i >= 0) {
							lender.setAvailableFunds(lender.getAvailableFunds() - i);
							lender.setPendingFunds(lender.getPendingFunds() + i);
						}
						else {
							l.setStatus("on hold"); //Puts application on hold if there isn't enough money in availableFunds
						}
					} 
				
		}
			else {
				l.setLoanAmount(0);
				l.setStatus("denied"); //If application does not meet all the requirements, it is denied
			}
			
		}
		
	}
	
	public void approveOnHoldLoan(Loan l) { //Checks loans "on hold" and if there is enough availableFund, changes status to approved
		int i = 0;
	
		if (l.getQualification() == 0) { //Checks for partial qualification and changes the loanAmount
			i = l.getSavings() * 4;
			l.setLoanAmount(i);	
			if (lender.getAvailableFunds() - i >= 0) {
				l.setStatus("approved");
				lender.setAvailableFunds(lender.getAvailableFunds() - i);
				lender.setPendingFunds(lender.getPendingFunds() + i);
			}
		}
		else { //If its fully qualified
			i = l.getRequestedAmount();
			l.setLoanAmount(i);
			if (lender.getAvailableFunds() - i >= 0) {
				l.setStatus("approved");
				lender.setAvailableFunds(lender.getAvailableFunds() - i);
				lender.setPendingFunds(lender.getPendingFunds() + i);
			}
		}
	}
	
	public void appOrDen (boolean[] input) { //Checks if customer accepts or rejects offer
		
		int i = 0;
		for (Loan l : lender.getCurLoans()) {
			
			boolean b = checkExpiration(l);
			
			l.setCustomerResponseToTheQuestionOfWouldTheyLikeTheLoanOrNot(input[i]);
			
			if (b) { //Checks to see if offer has expired and puts the money from pendingAmount to availableAmount
				lender.setAvailableFunds(lender.getAvailableFunds() + l.getLoanAmount());
				lender.setPendingFunds(lender.getPendingFunds() - l.getLoanAmount());
				i++;
				continue;
			}
			else if (l.getStatus().equals("approved") && l.getCustomerResponseToTheQuestionOfWouldTheyLikeTheLoanOrNot()) { 
				//changes to accepted status and completes the transaction
					l.setStatus("accepted");			
					lender.setPendingFunds(lender.getPendingFunds() - l.getLoanAmount());
			}
			else { //changes to reject status and puts the appropriate pendingFunds back to availableFunds
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
	
	
	
	public boolean checkExpiration(Loan lo) { //Checks if 3 days have passed since loanDate
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
	
	public List<Loan> filterStatus (String st){ //gets the loan Status
		List<Loan> ls = new ArrayList<Loan>();
		for (Loan l : lender.getCurLoans()) {
			if (l.getStatus() == st) {
				ls.add(l);
			}
		}
		return ls;
	}
	
	
	

}
