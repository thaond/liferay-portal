/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.portal.dao.orm.hibernate;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Properties;

import org.hibernate.cache.Cache;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.CacheProvider;

/**
 * <a href="CacheProviderWrapper.java.html"><b><i>View Source</i></b></a>
 *
 * @author	   Brian Wing Shun Chan
 * @deprecated
 */
public class CacheProviderWrapper implements CacheProvider {

	public CacheProviderWrapper(CacheProvider cacheProvider) {
		this.cacheProvider = cacheProvider;
	}

	public CacheProviderWrapper(String cacheProviderClassName) {
		try {
			cacheProvider = (CacheProvider)Class.forName(
				cacheProviderClassName).newInstance();
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	public Cache buildCache(String regionName, Properties properties)
		throws CacheException {

		return new CacheWrapper(
			cacheProvider.buildCache(regionName, properties));
	}

	public boolean isMinimalPutsEnabledByDefault() {
		return cacheProvider.isMinimalPutsEnabledByDefault();
	}

	public long nextTimestamp() {
		return cacheProvider.nextTimestamp();
	}

	public void start(Properties properties) throws CacheException {
		cacheProvider.start(properties);
	}

	public void stop() {
		cacheProvider.stop();
	}

	protected CacheProvider cacheProvider;

	private static Log _log = LogFactoryUtil.getLog(CacheProviderWrapper.class);

}