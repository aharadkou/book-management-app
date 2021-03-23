package com.epam.training.listeners;

import com.epam.training.constants.ConstantsContextAttributes;
import com.epam.training.helpers.collections.HeterogeneousMap;
import com.epam.training.helpers.db.ConnectionManager;
import com.epam.training.model.dao.impl.BookImplDB;
import com.epam.training.helpers.PropertyReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener()
public final class ApplicationContextListener
        implements ServletContextListener {

    private static final Logger LOGGER =
            LogManager.getLogger(ApplicationContextListener.class);

    @Override
    public void contextInitialized(final ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        setDatabaseUrl(servletContext);
        logBasicInformation(servletContext);
        injectDAOImpl(servletContext);
    }

    private void setDatabaseUrl(final ServletContext sc) {
        PropertyReader propertyReader = new PropertyReader("db");
        String dbFilePath = propertyReader.getProperty("dbfile");
        ConnectionManager.initDatabaseUrl(sc.getRealPath(dbFilePath));

    }

    private void logBasicInformation(final ServletContext sc) {
        LOGGER.info(sc.getServerInfo());
        LOGGER.info(sc.getServletRegistrations().toString());
    }

    private void injectDAOImpl(final ServletContext sc) {
        HeterogeneousMap mapDAO = new HeterogeneousMap();
        mapDAO.putItem(BookImplDB.class, new BookImplDB());
        sc.setAttribute(ConstantsContextAttributes.DAO_MAP, mapDAO);
    }

}
