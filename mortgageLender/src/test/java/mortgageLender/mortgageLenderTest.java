package mortgageLender;

import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Before;

public class mortgageLenderTest {
	
	LoanService ls;
	
	@Before
	public void init() {
		 ls = new LoanService();
	}
	
	@Test
	public void testAddFunds() {
		ls.addFunds(150000);
		
		assertEquals(150000, ls.getLenderFunds());
	}
	
	@Test
	public void createLoan() {
		Loan l1 = ls.createLoan(250000, 21, 700, 100000);
		Loan l2 = ls.createLoan(250000, 30, 630, 50000);
		
		
		assertEquals(1, l1.getQualification());
		assertEquals(-1, l2.getQualification());
	}
	
	
	

}
