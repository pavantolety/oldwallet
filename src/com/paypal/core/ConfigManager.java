package com.paypal.core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import com.oldwallet.config.SystemParams;
import com.paypal.sdk.util.ResourceLoader;

public final class ConfigManager {

	private static final Logger LOGGER = Logger.getLogger(ConfigManager.class);

	private static ConfigManager conf;

	private static Map<String, String> defaultMapView = null;

	private static final Properties DEFAULT_PROPERTIES;

	private Properties properties;

	private boolean propertyLoaded = false;

	private Map<String, String> mapView = null;

	static {
		DEFAULT_PROPERTIES = new Properties();
		DEFAULT_PROPERTIES.put(Constants.HTTP_CONNECTION_TIMEOUT, "5000");
		DEFAULT_PROPERTIES.put(Constants.HTTP_CONNECTION_RETRY, "2");
		DEFAULT_PROPERTIES.put(Constants.HTTP_CONNECTION_READ_TIMEOUT, "30000");
		DEFAULT_PROPERTIES.put(Constants.HTTP_CONNECTION_MAX_CONNECTION, "100");
		DEFAULT_PROPERTIES.put(Constants.DEVICE_IP_ADDRESS,
				SystemParams.DEVICE_IP_FOR_PAYPAL);
		DEFAULT_PROPERTIES.put(Constants.GOOGLE_APP_ENGINE, "false");
		defaultMapView = new HashMap<String, String>();
		for (Object object : DEFAULT_PROPERTIES.keySet()) {
			defaultMapView.put(object.toString().trim(), DEFAULT_PROPERTIES
					.getProperty(object.toString()).trim());
		}
	}

	@SuppressWarnings("deprecation")
	private ConfigManager() {

		ResourceLoader resourceLoader = new ResourceLoader(
				Constants.DEFAULT_CONFIGURATION_FILE);
		try {
			InputStream inputStream = resourceLoader.getInputStream();
			properties = new Properties();
			properties.load(inputStream);
			setPropertyLoaded(true);
		} catch (IOException e) {
			LOGGER.log(Priority.ERROR, e);
			throw new RuntimeException(e);
		} catch (Exception e) {
			LOGGER.log(Priority.ERROR, e);
			LOGGER.debug(e);
		}
	}

	public static ConfigManager getInstance() {
		synchronized (ConfigManager.class) {
			if (conf == null) {
				conf = new ConfigManager();
			}
		}
		return conf;
	}

	public static Properties getDefaultProperties() {
		return DEFAULT_PROPERTIES;
	}

	public static Map<String, String> getDefaultSDKMap() {
		return new HashMap<String, String>(defaultMapView);
	}

	public static Properties combineDefaultProperties(
			Properties receivedProperties) {
		Properties combinedProperties = new Properties(getDefaultProperties());
		if ((receivedProperties != null) && (!receivedProperties.isEmpty())) {
			ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
			try {
				receivedProperties.store(bos, null);
				combinedProperties.load(new ByteArrayInputStream(bos
						.toByteArray()));
			} catch (IOException e) {
				LOGGER.log(Priority.ERROR, e);
			}
		}
		return combinedProperties;
	}

	public void load(InputStream is) throws IOException {
		properties = new Properties();
		properties.load(is);
		if (!propertyLoaded) {
			setPropertyLoaded(true);
		}
	}

	public void load(Properties properties) {
		if (properties == null) {
			throw new IllegalArgumentException(
					"Initialization properties cannot be null");
		}
		this.properties = properties;
		if (!propertyLoaded) {
			setPropertyLoaded(true);
		}
	}

	public Map<String, String> getConfigurationMap() {
		if (mapView == null) {
			synchronized (DEFAULT_PROPERTIES) {
				mapView = new HashMap<String, String>();
				if (properties != null) {
					for (Object object : properties.keySet()) {
						mapView.put(object.toString().trim(), properties
								.getProperty(object.toString()).trim());
					}
				}
			}
		}
		return new HashMap<String, String>(mapView);
	}

	public String getValue(String key) {
		return properties.getProperty(key);
	}

	public String getValueWithDefault(String key, String defaultValue) {
		return properties.getProperty(key, defaultValue);
	}

	public Map<String, String> getValuesByCategory(String category) {
		String key = Constants.EMPTY_STRING;
		HashMap<String, String> map = new HashMap<String, String>();
		for (Object obj : properties.keySet()) {
			key = (String) obj;
			if (key.contains(category)) {
				map.put(key, properties.getProperty(key));
			}
		}
		return map;
	}

	public Set<String> getNumOfAcct() {
		String key = Constants.EMPTY_STRING;
		Set<String> set = new HashSet<String>();
		for (Object obj : properties.keySet()) {
			key = (String) obj;
			if (key.contains("acct")) {
				int pos = key.indexOf('.');
				String acct = key.substring(0, pos);
				set.add(acct);
			}
		}
		return set;

	}

	public boolean isPropertyLoaded() {
		return propertyLoaded;
	}

	private void setPropertyLoaded(boolean propertyLoaded) {
		this.propertyLoaded = propertyLoaded;
	}

}