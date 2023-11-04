package task3;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tests {
	
	@JsonProperty("tests")
	private List<Test> tests;
	
	public Tests() {}
	
	public Tests(List<Test> tests) {
		this.tests = tests;
	}

	public List<Test> getTestsList() {
		return tests;
	}

	public void setTestsList(List<Test> testsList) {
		this.tests = testsList;
	}

	
}
