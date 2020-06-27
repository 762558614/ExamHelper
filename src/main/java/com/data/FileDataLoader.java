package com.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.entity.Question;

public class FileDataLoader implements DataLoader {

	public Map<String, List<Question>> loadData() {
//		URL url = FileDataLoader.class.getClassLoader().getResource("questions");
//		File file = new File(url.getFile());
		File file = new File("questions");
		Map<String, List<Question>> subjects = new HashMap<String, List<Question>>();
		for(File folder : file.listFiles()) {
			String subject = folder.getName();
			List<Question> questions = new ArrayList<Question>();
			subjects.put(subject, questions);
			for(File questionFile : folder.listFiles()) {
				StringBuilder builder = new StringBuilder();
				char[] tempchars = new char[256];
				int len = 0;
				try(Reader reader = new InputStreamReader(new FileInputStream(questionFile));){
					while ((len = reader.read(tempchars)) != -1) {
					    builder.append(Arrays.copyOf(tempchars, len));
					}
				}catch (Exception e) {
				}
				Question question = new Question();
				String fileName = questionFile.getName();
				String[] head = fileName.split("-");
				int id = Integer.valueOf(head[0]);
				String title = head[1].substring(0, head[1].lastIndexOf('.'));
				question.setId(id);
				question.setQuestion(title);
				question.setAnswer(builder.toString().trim());
				questions.add(question);
			}
		}
		return subjects;
	}
	
}
