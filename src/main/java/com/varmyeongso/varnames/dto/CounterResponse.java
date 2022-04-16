package com.varmyeongso.varnames.dto;

public class CounterResponse {

	private final long totalCount;
	private final long variableCount;
	private final long classCount;

	public CounterResponse(long totalCount, long variableCount, long classCount) {
		this.totalCount = totalCount;
		this.variableCount = variableCount;
		this.classCount = classCount;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public long getVariableCount() {
		return variableCount;
	}

	public long getClassCount() {
		return classCount;
	}
}
