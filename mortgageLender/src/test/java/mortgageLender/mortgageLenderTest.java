package mortgageLender;

import org.junit.Test;
import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
	public void testApprovedOnHold() {
		ls.addFunds(500000);
		
		String d1 = "10-6-2021";
		SimpleDateFormat form = new SimpleDateFormat("MM-dd-yyyy");
		
		Loan l1;
		Loan l2;
		Loan l3;
		try {
			l1 = ls.createLoan(250000, 21, 700, 100000, form.parse(d1));
			l2 = ls.createLoan(250000, 30, 730, 50000, form.parse(d1));
			l3 = ls.createLoan(250000, 30, 730, 50000, form.parse(d1));
			
			ls.getApprovedLoans();
			
			assertEquals("approved", l1.getStatus());
			assertEquals("approved", l2.getStatus());
			assertEquals("on hold", l3.getStatus());
		} catch (ParseException e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	public void testAcceptedReject() {
		ls.addFunds(500000);
		
		String d1 = "10-6-2021";
		SimpleDateFormat form = new SimpleDateFormat("MM-dd-yyyy");
		
		boolean[] b = {true, false};
		
		Loan l1;
		Loan l2;
		//Loan l3;
		try {
			l1 = ls.createLoan(250000, 21, 700, 100000, form.parse(d1));
			l2 = ls.createLoan(250000, 30, 730, 50000, form.parse(d1));
			//l3 = ls.createLoan(250000, 30, 730, 50000, form.parse(d1));
			
			ls.getApprovedLoans();
			ls.appOrDen(b);
			
			assertEquals("accepted", l1.getStatus());
			assertEquals("rejected", l2.getStatus());
			
		} catch (ParseException e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	public void testPendingApprovedOnHold() {
		ls.addFunds(600000);
		
		String d1 = "10-6-2021";
		SimpleDateFormat form = new SimpleDateFormat("MM-dd-yyyy");
		
		//boolean[] b = {true, true};
		
		Loan l1;
		Loan l2;
		Loan l3;
		try {
			l1 = ls.createLoan(250000, 21, 700, 100000, form.parse(d1)); //full
			l2 = ls.createLoan(250000, 30, 730, 50000, form.parse(d1)); //partial
			l3 = ls.createLoan(250000, 30, 730, 50000, form.parse(d1));
			
			ls.getApprovedLoans();
			//ls.appOrDen(b);
			
			assertEquals(450000, ls.getPendingFunds());
			
		} catch (ParseException e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	public void testPendingAcceptReject() {
		ls.addFunds(500000);
		
		String d1 = "10-6-2021";
		SimpleDateFormat form = new SimpleDateFormat("MM-dd-yyyy");
		
		boolean[] b = {true, false, true};
		
		Loan l1;
		Loan l2;
		Loan l3;
		try {
			l1 = ls.createLoan(250000, 21, 700, 100000, form.parse(d1)); //full
			l2 = ls.createLoan(250000, 30, 730, 50000, form.parse(d1)); //partial 200000
			l3 = ls.createLoan(250000, 30, 730, 50000, form.parse(d1));
			
			ls.getApprovedLoans();
			ls.appOrDen(b);
			
			assertEquals(0, ls.getPendingFunds());
			assertEquals(50000, ls.getLenderFunds());
			//assertEquals("rejected", l2.getStatus());
			//assertEquals("accepted", l3.getStatus());
			
		} catch (ParseException e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	public void testAcceptReject() {
		ls.addFunds(500000);
		
		String d1 = "10-6-2021";
		SimpleDateFormat form = new SimpleDateFormat("MM-dd-yyyy");
		
		boolean[] b = {true, false, true};
		
		Loan l1;
		Loan l2;
		Loan l3;
		try {
			l1 = ls.createLoan(250000, 21, 700, 100000, form.parse(d1)); //full
			l2 = ls.createLoan(250000, 30, 730, 50000, form.parse(d1)); //partial 200000
			l3 = ls.createLoan(250000, 30, 730, 50000, form.parse(d1));
			
			ls.getApprovedLoans();
			ls.appOrDen(b);
			
			assertEquals("accepted", l1.getStatus());
			assertEquals("rejected", l2.getStatus());
			assertEquals("accepted", l3.getStatus());
			
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
	
	
	@Test
	public void testFilterQualification() {
		ls.addFunds(500000);
		
		String d1 = "10-6-2021";
		String d2 = "10-1-2021";
		SimpleDateFormat form = new SimpleDateFormat("MM-dd-yyyy");
		
		boolean[] b = {true, false, true};
		
		Loan l1;
		Loan l2;
		Loan l3;
		try {
			l1 = ls.createLoan(250000, 21, 700, 100000, form.parse(d1)); //full
			l2 = ls.createLoan(250000, 30, 730, 50000, form.parse(d1)); //partial 200000
			l3 = ls.createLoan(250000, 69, 730, 50000, form.parse(d1));
			
			//ls.getApprovedLoans();

			//ls.appOrDen(b);
			
			List<Loan> lst = ls.filterStatus("qualified");
			ArrayList<Integer> arr = new ArrayList<Integer>();
			for (Loan l : lst) {
				arr.add(l.getLoanID());
			}
			Integer[] expect = {1,2};
			assertArrayEquals(expect, arr.toArray());
			
	
		} catch (ParseException e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	public void testFilterDenied() {
		ls.addFunds(500000);
		
		String d1 = "10-6-2021";
		String d2 = "10-1-2021";
		SimpleDateFormat form = new SimpleDateFormat("MM-dd-yyyy");
		
		boolean[] b = {true, false, true};
		
		Loan l1;
		Loan l2;
		Loan l3;
		try {
			l1 = ls.createLoan(250000, 21, 700, 100000, form.parse(d1)); //full
			l2 = ls.createLoan(250000, 30, 730, 50000, form.parse(d1)); //partial 200000
			l3 = ls.createLoan(250000, 69, 730, 50000, form.parse(d1));
			
			
			List<Loan> lst = ls.filterStatus("denied");
			ArrayList<Integer> arr = new ArrayList<Integer>();
			for (Loan l : lst) {
				arr.add(l.getLoanID());
			}
			Integer[] expect = {3};
			assertArrayEquals(expect, arr.toArray());
			
			
	
		} catch (ParseException e) {
			e.printStackTrace();
		}	
	}
	@Test
	public void testFilterApproved() {
		ls.addFunds(500000);
		
		String d1 = "10-6-2021";
		String d2 = "10-1-2021";
		SimpleDateFormat form = new SimpleDateFormat("MM-dd-yyyy");
		
		boolean[] b = {true, false, true};
		
		Loan l1;
		Loan l2;
		Loan l3;
		try {
			l1 = ls.createLoan(250000, 21, 700, 100000, form.parse(d1)); //full
			l2 = ls.createLoan(250000, 30, 730, 50000, form.parse(d1)); //partial 200000
			l3 = ls.createLoan(250000, 30, 730, 50000, form.parse(d1));
			
			ls.getApprovedLoans();

			//ls.appOrDen(b);
			
			List<Loan> lst = ls.filterStatus("approved");
			ArrayList<Integer> arr = new ArrayList<Integer>();
			for (Loan l : lst) {
				arr.add(l.getLoanID());
			}
			Integer[] expect = {1,2};
			assertArrayEquals(expect, arr.toArray());
			
	
		} catch (ParseException e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	public void testFilterOnHold() {
		ls.addFunds(500000);
		
		String d1 = "10-6-2021";
		String d2 = "10-1-2021";
		SimpleDateFormat form = new SimpleDateFormat("MM-dd-yyyy");
		
		boolean[] b = {true, false, true};
		
		Loan l1;
		Loan l2;
		Loan l3;
		try {
			l1 = ls.createLoan(250000, 21, 700, 100000, form.parse(d1)); //full
			l2 = ls.createLoan(250000, 30, 730, 50000, form.parse(d1)); //partial 200000
			l3 = ls.createLoan(250000, 30, 730, 50000, form.parse(d1));
			
			ls.getApprovedLoans();

			//ls.appOrDen(b);
			
			List<Loan> lst = ls.filterStatus("on hold");
			ArrayList<Integer> arr = new ArrayList<Integer>();
			for (Loan l : lst) {
				arr.add(l.getLoanID());
			}
			Integer[] expect = {3};
			assertArrayEquals(expect, arr.toArray());
			
	
		} catch (ParseException e) {
			e.printStackTrace();
		}	
	}

	@Test
	public void testFilterAccepted() {
		ls.addFunds(500000);
		
		String d1 = "10-6-2021";
		String d2 = "10-1-2021";
		SimpleDateFormat form = new SimpleDateFormat("MM-dd-yyyy");
		
		boolean[] b = {true, false, true};
		
		Loan l1;
		Loan l2;
		Loan l3;
		try {
			l1 = ls.createLoan(250000, 21, 700, 100000, form.parse(d1)); //full
			l2 = ls.createLoan(250000, 30, 730, 50000, form.parse(d1)); //partial 200000
			l3 = ls.createLoan(250000, 30, 730, 50000, form.parse(d1));
			
			ls.getApprovedLoans();

			ls.appOrDen(b);
			
			List<Loan> lst = ls.filterStatus("accepted");
			ArrayList<Integer> arr = new ArrayList<Integer>();
			for (Loan l : lst) {
				arr.add(l.getLoanID());
			}
			Integer[] expect = {1, 3};
			assertArrayEquals(expect, arr.toArray());
			
	
		} catch (ParseException e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	public void testFilterRejected() {
		ls.addFunds(500000);
		
		String d1 = "10-6-2021";
		String d2 = "10-1-2021";
		SimpleDateFormat form = new SimpleDateFormat("MM-dd-yyyy");
		
		boolean[] b = {true, false, true};
		
		Loan l1;
		Loan l2;
		Loan l3;
		try {
			l1 = ls.createLoan(250000, 21, 700, 100000, form.parse(d1)); //full
			l2 = ls.createLoan(250000, 30, 730, 50000, form.parse(d1)); //partial 200000
			l3 = ls.createLoan(250000, 30, 730, 50000, form.parse(d1));
			
			ls.getApprovedLoans();

			ls.appOrDen(b);
			
			List<Loan> lst = ls.filterStatus("rejected");
			ArrayList<Integer> arr = new ArrayList<Integer>();
			for (Loan l : lst) {
				arr.add(l.getLoanID());
			}
			Integer[] expect = {2};
			assertArrayEquals(expect, arr.toArray());
			
	
		} catch (ParseException e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	public void testFilterExpired() {
		ls.addFunds(500000);
		
		String d1 = "10-6-2021";
		String d2 = "10-1-2021";
		SimpleDateFormat form = new SimpleDateFormat("MM-dd-yyyy");
		
		boolean[] b = {true, false, true};
		
		Loan l1;
		Loan l2;
		Loan l3;
		try {
			l1 = ls.createLoan(250000, 21, 700, 100000, form.parse(d1)); //full
			l2 = ls.createLoan(250000, 30, 730, 50000, form.parse(d1)); //partial 200000
			l3 = ls.createLoan(250000, 30, 730, 50000, form.parse(d2));
			
			ls.getApprovedLoans();

			ls.appOrDen(b);
			
			List<Loan> lst = ls.filterStatus("expired");
			ArrayList<Integer> arr = new ArrayList<Integer>();
			for (Loan l : lst) {
				arr.add(l.getLoanID());
			}
			Integer[] expect = {3};
			assertArrayEquals(expect, arr.toArray());
			
	
		} catch (ParseException e) {
			e.printStackTrace();
		}	
	}
	
	

}
