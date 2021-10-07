package mortgageLender;

import org.junit.Test;
import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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
	public void testCreateLoan() {
		ls.addFunds(500000);
		
		String d1 = "10-6-2021";
		SimpleDateFormat form = new SimpleDateFormat("MM-dd-yyyy");
		
		Loan l1;
		Loan l2;
		Loan l3;
		try {
			l1 = ls.createLoan(250000, 21, 700, 100000, form.parse(d1));
			l2 = ls.createLoan(250000, 30, 730, 50000, form.parse(d1));
			l3 = ls.createLoan(2500000, 30, 730, 50000, form.parse(d1));
			
			
			assertEquals(1, l1.getQualification());
			assertEquals(0, l2.getQualification());
			assertEquals(-1, l3.getQualification());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	@Test
	public void testAcceptedOnHold() {
		ls.addFunds(500000);
		
		String d1 = "10-6-2021";
		SimpleDateFormat form = new SimpleDateFormat("MM-dd-yyyy");
		
		boolean[] b = {true, true, true};
		
		Loan l1;
		Loan l2;
		Loan l3;
		try {
			l1 = ls.createLoan(250000, 21, 700, 100000, form.parse(d1));
			l2 = ls.createLoan(250000, 30, 730, 50000, form.parse(d1));
			l3 = ls.createLoan(250000, 30, 730, 50000, form.parse(d1));
			
			ls.getApprovedLoans();
			ls.appOrDen(b);
			
			assertEquals("accepted", l1.getStatus());
			assertEquals("accepted", l2.getStatus());
			assertEquals("on hold", l3.getStatus());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void testThreeDays() {
		ls.addFunds(500000);
		
		String d1 = "10-01-2021";
		SimpleDateFormat form = new SimpleDateFormat("MM-dd-yyyy");
		
		boolean[] b = {true, true, true};
		
		Loan l1;
		
		try {
			l1 = ls.createLoan(250000, 21, 700, 100000, form.parse(d1));
			
			
			ls.getApprovedLoans();
			ls.appOrDen(b);
			
			assertEquals("expired", l1.getStatus());
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
	}

	
	
	

}
