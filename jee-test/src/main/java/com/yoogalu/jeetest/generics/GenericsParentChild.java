package com.yoogalu.jeetest.generics;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GenericsParentChild {

	private static final String MONTHLY = "Monthly";
	private static final String DAILY = "Daily";

	public static void main(String[] args) {

	}

	static BaseJournal<?, ?> buildJournal(String type, Date accountingPeriod, Date transactionDate) {
		if (transactionDate == null) {
			return new MonthlyJournal(MONTHLY, accountingPeriod);
		} else {
			return new DailyJournal(DAILY, accountingPeriod, transactionDate);
		}
	}

}

abstract class BaseJournal<J extends BaseJournal<J, JL>, JL extends BaseJournalLine<JL, J>> {
	private String type;
	private Date accountingPeriod;
	private List<JL> lines = new ArrayList<>();
	private J accrualJournal;

	public BaseJournal(String type, Date accountingPeriod) {
		this.type = type;
		this.accountingPeriod = accountingPeriod;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getAccountingPeriod() {
		return accountingPeriod;
	}

	public void setAccountingPeriod(Date accountingPeriod) {
		this.accountingPeriod = accountingPeriod;
	}

	public List<JL> getLines() {
		return lines;
	}

	public void addLines(JL line) {
		this.lines.add(line);
	}

	public void linkLines(List<JL> lines) {
		this.lines = lines;
		for (JL line : lines) {
			line.setJournal(getSelf());
		}
	}

	protected abstract J getSelf();

	public void setLines(List<JL> lines) {
		this.lines = lines;
	}

	public J getAccrualJournal() {
		return accrualJournal;
	}

	public void setAccrualJournal(J accrualJournal) {
		this.accrualJournal = accrualJournal;
	}
}

abstract class BaseJournalLine<JL extends BaseJournalLine<JL, J>, J extends BaseJournal<J, JL>> {
	private Date accountingPeriod;
	private J journal;

	public Date getAccountingPeriod() {
		return accountingPeriod;
	}

	public void setAccountingPeriod(Date accountingPeriod) {
		this.accountingPeriod = accountingPeriod;
	}

	public J getJournal() {
		return journal;
	}

	public void setJournal(J baseJournal) {
		this.journal = baseJournal;
	}
}

class MonthlyJournal extends BaseJournal<MonthlyJournal, MonthlyJournalLine> {
	public MonthlyJournal(String type, Date accountingPeriod) {
		super(type, accountingPeriod);
	}

	@Override
	protected MonthlyJournal getSelf() {
		return this;
	}
}

class MonthlyJournalLine extends BaseJournalLine<MonthlyJournalLine, MonthlyJournal> {

}

class DailyJournal extends BaseJournal<DailyJournal, DailyJournalLine> {
	private Date transactionDate;

	public DailyJournal(String type, Date accountingPeriod, Date transactionDate) {
		super(type, accountingPeriod);
		this.transactionDate = transactionDate;
	}

	@Override
	protected DailyJournal getSelf() {
		return this;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

}

class DailyJournalLine extends BaseJournalLine<DailyJournalLine, DailyJournal> {
	private Date transactionDate;

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
}