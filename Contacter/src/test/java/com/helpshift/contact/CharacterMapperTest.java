package com.helpshift.contact;

import java.util.Map;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hs.contact.CharacterMapper;

public class CharacterMapperTest {

	private static CharacterMapper cMapper;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		cMapper = new CharacterMapper();
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void initializeMapShouldFillValues() {
		
		reset();
		cMapper.intializeMap();
		Assert.assertEquals(cMapper.getCharacterDocMapping().size(), 58);
		
	}
	

	@Test(expected = RuntimeException.class)
	public void indexWithNullShouldReturnNull() {
		
		cMapper.indexName(null);
	}
	
	@Test
	public void addIndexStringShouldUpdateMap() {
		
		reset();
		cMapper.indexName("test name");
		Assert.assertEquals(cMapper.getIndexName().size(), 1);
		
	}
	
	@Test
	public void ifCaseInsensitiveThenLowerCharacterIndexShouldBePopulated() {
		
		cMapper.setCaseSensitive(false);
		System.out.println(cMapper.getCharacterDocMapping());
		boolean isCaseSensitive = cMapper.isCaseSensitive();
		
		if(!isCaseSensitive) {
		
		cMapper.indexName("Another Name");
		Map<Character, Set<Long>> characterDocMapping = cMapper.getCharacterDocMapping();
		
			for(int i =65; i<92; i++ ) {
				
				if(characterDocMapping.containsKey((char)i))
					Assert.assertTrue(characterDocMapping.get((char)i).size()==0);
				
			}
		
		}
		
		
	}
	
	public void reset() {
		
		cMapper.getCharacterDocMapping().clear();
		cMapper.getIndexName().clear();
	}
	
}
