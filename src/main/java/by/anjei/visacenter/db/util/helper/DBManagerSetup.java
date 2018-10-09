package by.anjei.visacenter.db.util.helper;

import by.anjei.visacenter.db.util.DBManager;
import by.anjei.visacenter.db.util.DatabaseConnectionBehavior;
import by.anjei.visacenter.db.util.PostgresDatabaseConnectionBehavior;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBManagerSetup implements ServletContextListener {
	
	private DBManager dbm = null;

	@Override
	public void contextInitialized(ServletContextEvent sce) {

        ServletContext context = sce.getServletContext();
        Properties properties = new Properties();
        InputStream input = null;

        try{
            input = new FileInputStream("src/main/resources/db.properties");
            properties.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String dbUser = properties.getProperty("dbUser");
		String dbPassword = properties.getProperty("dbPassword");
		String dbUrl = properties.getProperty("dbUrl");
        String dbDriver = properties.getProperty("dbDriver");
		
		DatabaseConnectionBehavior conB = new PostgresDatabaseConnectionBehavior(dbUser, dbPassword, dbUrl, dbDriver);
		DBManager dbm = new DBManager(conB);
        if (!dbm.isConnected()) {
            if (!dbm.openConnection())
            {
                try {
                    throw new IOException("Could not connect to database and open connection");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        context.setAttribute("dbmanager", dbm);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// cleanup the connection when the context is destroyed
		if (dbm != null) {
			if (dbm.isConnected()) {
				dbm.closeConnection(false);
			}
		}
		dbm = null;
        System.out.println("!!!!!!!!!!!!!!Context is destroyed.!!!!!!!!!!!!!!!!!");
	}
}
