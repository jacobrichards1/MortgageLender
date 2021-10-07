package mortgageLender;

public class Loan {
	
	private int loan_id;
	private int requestedAmount;
	private int dti;
	private int credit_score;
	private int savings;
	private int qualification;
	private int loanAmount;
	private boolean status;
	
	public Loan(int loan_id, int requestedAmount, int dti, int credit_score, int savings, int qualification, int loanAmount,
			boolean status) {
		super();
		this.requestedAmount = requestedAmount;
		this.dti = dti;
		this.credit_score = credit_score;
		this.savings = savings;
		this.qualification = qualification;
		this.loanAmount = loanAmount;
		this.status = status;
	}
	
	public Loan(int requestedAmount, int dti, int credit_score, int savings) {
		super();
		this.requestedAmount = requestedAmount;
		this.dti = dti;
		this.credit_score = credit_score;
		this.savings = savings;
	}
	
	


	public int getLoan_id() {
		return loan_id;
	}

	public void setLoan_id(int loan_id) {
		this.loan_id = loan_id;
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
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	

}
