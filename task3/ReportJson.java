package task3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReportJson {

	public static void main(String[] args) {
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			
			File fileTests = new File(args[0]);
			
			TypeReference<Tests> typeRef
				= new TypeReference<Tests>() {};
			
			Tests tests = mapper.readValue(fileTests, typeRef); // разобрали json тесты в нашу структуру
			
			File fileValues = new File(args[1]);
			
			TypeReference<Map<String, List<Value>>> typeRefVal
				= new TypeReference<Map<String, List<Value>>>(){};
				
			Map<String, List<Value>> valuesMap = mapper.readValue(fileValues, typeRefVal); // json values в map
			
			List<List<Test>> nestedLists = new ArrayList<List<Test>>(); // начальные данные для рекурсивного метода
			nestedLists.add(tests.getTestsList());
			
			fillAllValueFields(tests, nestedLists, valuesMap.get("values")); // вызов рекурсивного метода заполнения value
			
			File reportFile = new File(fileTests.getParent(), "report.json"); // запись в каталог файла tests.json нового файла report.json
			reportFile.createNewFile();
			
			mapper.writeValue(reportFile, tests.getTestsList());
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		

	}
	
	// рекурсивный метод заполнения value полей
	// все листы Test, у которых есть values, добавляются
	// в новый лист листов, который используется в вызове в конце
	public static void fillAllValueFields(Tests tests, List<List<Test>> nestedLists, List<Value> listValue) {
		
		List<List<Test>> newNestedLists = new ArrayList<List<Test>>();
		
		for(List<Test> listTest : nestedLists) {
			
			for (Test test : listTest) {
				
				if (test.getValues() == null) {
					
					test.setValue(listValue.get(getIndexById(test.getId(), listValue))
							.getValue());
					
				} else {
					
					newNestedLists.add(test.getValues());
					
				}
				
			}
			
		}
		if (newNestedLists.size() != 0)
			fillAllValueFields(tests, newNestedLists, listValue);
		else
			return;
	}
	
	// Получаем индекс value по id test
	public static int getIndexById(int id, List<Value> listValue) {
		
		int index = -1;
		
		for (int i = 0; i < listValue.size(); ++i) {
			
			if (listValue.get(i).getId() == id) {
				index = i;
			}
		}
		
		return index;
	}
	
}
