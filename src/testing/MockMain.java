package testing;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MockMain {
	public static void main(String[] args) {

		String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		String appConfigPath = rootPath + "config.properties";
		String catalogConfigPath = rootPath + "catalog";
		 
		Properties appProps = new Properties();
		try {
			appProps.load(new FileInputStream("config.properties"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
		Properties catalogProps = new Properties();
		try {
			catalogProps.load(new FileInputStream(catalogConfigPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
