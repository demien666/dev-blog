package com.demien.testloan.service;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.demien.testloan.dao.IBaseDAO;
import com.demien.testloan.domain.Loan;
import com.demien.testloan.utils.ObjectDataPopulator;

import static org.mockito.Mockito.*;

public class LoanServiceImplTest extends BaseServiceImplTest<Loan> {
	
	@SuppressWarnings("unchecked")
	static IBaseDAO<Loan> dao=mock(IBaseDAO.class);
	static LoanServiceImpl service=new LoanServiceImpl(dao);
	
	Loan testLoan;
	
	public LoanServiceImplTest() {
		super(Loan.class, dao, service);
	} 
	
	@Before
	public void init() throws Exception {
		testLoan=new Loan();
		ObjectDataPopulator.populate(testLoan);
	}
	
	@Test
	public void extendLoanTest() {
		when(dao.get(testLoan.getId())).thenReturn(testLoan);
		ArgumentCaptor<Loan> captor = ArgumentCaptor.forClass(Loan.class);
		service.extendLoan(testLoan.getId());
		
		verify(dao).update(captor.capture());
		Assert.assertEquals(testLoan.getDeadLine(), captor.getValue().getDeadLine());
		Assert.assertTrue( Math.abs( testLoan.getRate()-captor.getValue().getRate() ) <0.01 );
		
		verify(dao).save(captor.capture());
		Assert.assertTrue(testLoan.getDeadLine().getTime()<captor.getValue().getDeadLine().getTime());
		Assert.assertTrue( testLoan.getRate() < captor.getValue().getRate() );
		
		
	}
	
}
