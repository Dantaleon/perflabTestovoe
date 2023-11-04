package task3;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Test {
	
	@JsonProperty("id")
	private int id;
	@JsonProperty("title")
	private String title;
	@JsonProperty("value")
	private String value;
	@JsonProperty("values")
	private List<Test> values;
	
	public Test() {}
	
	public Test(int id, String title, String value, List<Test> values) {
		this.id = id;
		this.title = title;
		this.value = value;
		this.values = values;
	}
	
	//public Test(int id, String title, String value) {
	//	this.id = id;
	//	this.title = title;
	//	this.value = value;
	//}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public List<Test> getValues() {
		return values;
	}
	public void setValues(List<Test> values) {
		this.values = values;
	}
	
	
}
