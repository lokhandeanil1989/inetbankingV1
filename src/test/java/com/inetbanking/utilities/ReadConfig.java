package com.inetbanking.utilities;

import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;

public class ReadConfig {

	Properties pro;

	public ReadConfig()
	{
		File src= new File("./Configuration/config.properties");
		try 
		{
			FileInputStream fis= new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
		}
		catch(Exception e)
		{
			System.out.println("Exceeption is "+e.getMessage());
		}
	}
	public String getApplicationURL()
	{
		String url=pro.getProperty("baseURL");
		return url;
	}

	public String getUsername()
	{
		String username=pro.getProperty("username");
		return username;
	}

	public String getPassword()
	{
		String pwd=pro.getProperty("password");
		return pwd;
	}

	public String getChromepath()
	{
		String chromepath=pro.getProperty("chromepath");
		return chromepath;
	}

	public String getEdgepath()
	{
		String edgepath=pro.getProperty("edgepath");
		return edgepath;
	}

	public String getFirefoxpath()
	{
		String firefoxpath=pro.getProperty("firefoxpath");
		return firefoxpath;
	}
}

