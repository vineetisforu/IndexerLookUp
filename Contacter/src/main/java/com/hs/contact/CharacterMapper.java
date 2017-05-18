package com.hs.contact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CharacterMapper {

	private Map<Character, Set<Long>> characterDocMapping = new HashMap<Character, Set<Long>>();
	private Map<Long, String> indexName = new HashMap<Long, String>();
	private long count = 0;
	private boolean isCaseSensitive = false;
	
	
	public Map<Character, Set<Long>> getCharacterDocMapping() {
		return characterDocMapping;
	}

	public void setCharacterDocMapping(Map<Character, Set<Long>> characterDocMapping) {
		this.characterDocMapping = characterDocMapping;
	}

	public Map<Long, String> getIndexName() {
		return indexName;
	}

	public void setIndexName(Map<Long, String> indexName) {
		this.indexName = indexName;
	}

	public void intializeMap() {
		
		for(int i =65 ; i<123 ; i++) {
			
			characterDocMapping.put((char)i, new HashSet<Long>());
			
		}
	}
	
	/**
	 * Will index the passed in String to create a inverted index
	 * on character and document id mapping, where document mapping
	 * will be present in a HashMap with unique document id as key 
	 * string token as value
	 * @param name String to index
	 */
	public void indexName(String name) {
		
		if(name==null)
			throw new RuntimeException("input cannot be null");
		
		if(name.length()==0)
			return;
		
		indexName.put(++count, name);
		
		for(int i =0;i<name.length(); i++) {
			
			  if(isCaseSensitive)
				  caseSensitiveIndex(name, i);
			  else
				  caseInSensitiveIndex(name, i);
			
		}
		
	}
	
	/**
	 * Will be used to index the name in case Sensitive way,
	 * @param name input string
	 * @param i each index of string
	 */
	public void caseSensitiveIndex(String name, int i) {
		
		if(characterDocMapping.containsKey(name.charAt(i))) {
			
			if(!characterDocMapping.get(name.charAt(i)).contains(count)) {
				
				Set<Long> docSet = characterDocMapping.get(name.charAt(i));
				docSet.add(count);
			}
			
		} else {
			
			Set<Long> docSet = new HashSet<Long>();
			docSet.add(count);
			characterDocMapping.put(name.charAt(i), docSet);
			
		}
		
	}
	
	/**
	 * Will be used to index names in case Insensitive way
	 * @param name intput string 
	 * @param i each index of string
	 */
	public void caseInSensitiveIndex(String name, int i) {
		
		if(characterDocMapping.containsKey(Character.toLowerCase(name.charAt(i)))) {
			
			if(!characterDocMapping.get(Character.toLowerCase(name.charAt(i))).contains(count)) {
				
				Set<Long> docSet = characterDocMapping.get(Character.toLowerCase(name.charAt(i)));
				docSet.add(count);
			}
			
		} else {
			
			Set<Long> docSet = new HashSet<Long>();
			docSet.add(count);
			characterDocMapping.put(Character.toLowerCase(name.charAt(i)), docSet);
			
		}
		
	}
	
	/**
	 * Method to Search for the Queried String in the index
	 * 
	 * @param name Query String
	 * @return list of Strings if match is found,
	 * else null if the query string length is less than size 1 or null
	 * else returns empty list if the match is not found
	 */
	public List<String> searchName(String name) {
		
		List<String> matchedNames = new ArrayList<String>();
		String transformedName = name;
		
		if(name==null)
			throw new RuntimeException("input cannot be null");
		
		if(name.length()==0)
			return matchedNames;
		
		 if(!isCaseSensitive)
			 transformedName = name.toLowerCase();
		
		Set<Long> intersection = new HashSet<Long>(characterDocMapping.get(transformedName.charAt(0)));
		
		for(int i =0;i<transformedName.length();i++) {
			
			intersection.retainAll(characterDocMapping.get(transformedName.charAt(i)));
			
		}
		
		for(Long docId : intersection) {
			
			if(indexName.get(docId).contains(name) || (!isCaseSensitive && indexName.get(docId).toLowerCase().contains(name))) {
				matchedNames.add(indexName.get(docId));
			}
		}
		
		return matchedNames;
		
	}
	
	
	public boolean isCaseSensitive() {
		return isCaseSensitive;
	}

	public void setCaseSensitive(boolean isCaseSensitive) {
		this.isCaseSensitive = isCaseSensitive;
	}
}

