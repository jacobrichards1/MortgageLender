package mortgageLender;


import java.util.Date;

public class Loan {
	
	private int requestedAmount;
	private int dti;
	private int credit_score;
	private int savings;
	private int qualification;
	private int loanAmount;
	private boolean customerResponseToTheQuestionOfWouldTheyLikeTheLoanOrNot;
	private String status;
	private Date loanDate;
	private Date acceptedDate = java.util.Calendar.getInstance().getTime();
	
	public Loan(int requestedAmount, int dti, int credit_score, int savings, int qualification, int loanAmount,
			String status) {
		super();
		this.requestedAmount = requestedAmount;
		this.dti = dti;
		this.credit_score = credit_score;
		this.savings = savings;
		this.qualification = qualification;
		this.loanAmount = loanAmount;
		this.status = status;
	}
	
	public Loan(int requestedAmount, int dti, int credit_score, int savings, Date loanDate) {
		super();
		this.requestedAmount = requestedAmount;
		this.dti = dti;
		this.credit_score = credit_score;
		this.savings = savings;
		this.loanDate = loanDate;
	}
	

	public int getRequestedAmount() {
		return requestedAmount;
	}
	public void setRequestedAmount(int requestedAmount) {
		this.requestedAmount = requestedAmount;
	}
	public int getDti() {
		return dti;
	}
	public void setDti(int dti) {
		this.dti = dti;
	}
	public int getCredit_score() {
		return credit_score;
	}
	public void setCredit_score(int credit_score) {
		this.credit_score = credit_score;
	}
	public int getSavings() {
		return savings;
	}
	public void setSavings(int savings) {
		this.savings = savings;
	}
	public int getQualification() {
		return qualification;
	}
	public void setQualification(int qualification) {
		this.qualification = qualification;
	}
	public int getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(int loanAmount) {
		this.loanAmount = loanAmount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public boolean getCustomerResponseToTheQuestionOfWouldTheyLikeTheLoanOrNot() {
		return customerResponseToTheQuestionOfWouldTheyLikeTheLoanOrNot;
	}

	public void setCustomerResponseToTheQuestionOfWouldTheyLikeTheLoanOrNot(
			boolean customerResponseToTheQuestionOfWouldTheyLikeTheLoanOrNot) {
		this.customerResponseToTheQuestionOfWouldTheyLikeTheLoanOrNot = customerResponseToTheQuestionOfWouldTheyLikeTheLoanOrNot;
	}

	public Date getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}

	public Date getAcceptedDate() {
		return acceptedDate;
	}

	public void setAcceptedDate(Date acceptedDate) {
		this.acceptedDate = acceptedDate;
	}
	
	

}
