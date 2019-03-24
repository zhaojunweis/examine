package com.examine;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExamineApplicationTests {

	@Test
	public void contextLoads() throws FileNotFoundException {
		File path  = new File(ResourceUtils.getURL("src/main/resources/static").getPath());
		System.out.println(path.getAbsolutePath());
	}

}
