package com.ibsplc.itin.cas.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * 
 * @author A-4211
 * 
 */
public class CacheHandler {

	private Cache casCache;

	public CacheHandler() {
			
		initCache();
	}


	private void initCache() {
		
		CacheManager cacheManager = null;
		
		 cacheManager = CacheManager.getInstance();
				
		casCache = cacheManager.getCache("casCache");

	}

	public void registerToken(String token, String userName) {
		
		Element tokenCache = new Element(token, userName);
		casCache.put(tokenCache);
		isValidToken(token,userName);
	}

	public boolean isValidToken(String token, String userName) {

		boolean valid = false;
		if (casCache.getKeys().contains(token)) {
			Element element = casCache.get(token);
			if (element.getValue().toString().equals(userName)) {
				
				if (element != null) {
					if (!element.isExpired()) {
						return true;
					}
				}
			} else {
				return false;
			}
		}

		return valid;
	}

}
