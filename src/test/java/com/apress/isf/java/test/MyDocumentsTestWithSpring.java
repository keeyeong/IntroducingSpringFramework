package com.apress.isf.java.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.apress.isf.java.model.Document;
import com.apress.isf.java.model.Type;
import com.apress.isf.java.service.SearchEngine;

public class MyDocumentsTestWithSpring {

	private SearchEngine engine;	
	private ClassPathXmlApplicationContext context;
	private Type documentType;
	
	@Before
	public void setup() {
		context = new ClassPathXmlApplicationContext("META-INF/spring/mydocuments-context.xml");
		engine = context.getBean(SearchEngine.class);
		documentType = context.getBean(Type.class);
	}
	
	@Test
	public void testFindByType() {	
		List<Document> documents = engine.findByType(documentType);
		assertNotNull(documents);
		assertTrue(documents.size()==1);
		assertEquals(documentType.getName(), documents.get(0).getType().getName());
		assertEquals(documentType.getDesc(), documents.get(0).getType().getDesc());
		assertEquals(documentType.getExtension(), documents.get(0).getType().getExtension());		
	}

	@Test
	public void testFindAll() {
		List<Document> documents = engine.listAll();
		assertNotNull(documents);
		assertTrue(documents.size()==4);
	}
}
