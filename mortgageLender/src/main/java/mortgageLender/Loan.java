package mortgageLender;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Loan {
	
	private int loanID;
	private int requestedAmount;
	private int dti;
	private int credit_score;
	private int savings;
	private int qualification;
	private int loanAmount;
	private boolean customerResponseToTheQuestionOfWouldTheyLikeTheLoanOrNot;
	private String status;
	private Date loanDate;
	SimpleDateFormat form = new SimpleDateFormat("MM-dd-yyyy");
	private Date acceptedDate;
	
	public Loan(int requestedAmount, int dti, int credit_score, int savings, int qualification, int loanAmount,
			String status) { //With qualification, loanAmount, status
		super();
		this.requestedAmount = requestedAmount;
		this.dti = dti;
		this.credit_score = credit_score;
		this.savings = savings;
		this.qualification = qualification;
		this.loanAmount = loanAmount;
		this.status = status;
		try {
			this.acceptedDate =   form.parse("10-6-2021");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Loan(int loanID, int requestedAmount, int dti, int credit_score, int savings, Date loanDate) {  //With loanID and loanDate
		super();
		this.loanID = loanID;
		this.requestedAmount = requestedAmount;
		this.dti = dti;
		this.credit_score = credit_score;
		this.savings = savings;
		this.loanDate = loanDate;
		try {
			this.acceptedDate =   form.parse("10-6-2021");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Setters and Getters
	
	public int getLoanID() {
		return loanID;
	}

	public void setLoanID(int loanID) {
		this.loanID = loanID;
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
