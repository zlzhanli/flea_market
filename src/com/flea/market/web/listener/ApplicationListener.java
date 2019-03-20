package com.flea.market.web.listener;

import com.flea.market.web.filter.ActionCoreFilter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener()
public class ApplicationListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {
    private static final Log log = LogFactory.getLog(ActionCoreFilter.class);

    // Public constructor is required by servlet spec
    public ApplicationListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed).
         You can initialize servlet context related data here.
      */

        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("js", sce.getServletContext().getContextPath() + "/static/local/js");
        servletContext.setAttribute("img", sce.getServletContext().getContextPath() + "/static/local/img");
        servletContext.setAttribute("css", sce.getServletContext().getContextPath() + "/static/local/css");
        servletContext.setAttribute("jquery", sce.getServletContext().getContextPath() + "/static/jquery");
        servletContext.setAttribute("easy_ui", sce.getServletContext().getContextPath() + "/static/easy_ui");
        servletContext.setAttribute("bootstrap", sce.getServletContext().getContextPath() + "/static/bootstrap");
        servletContext.setAttribute("root", sce.getServletContext().getContextPath());
        servletContext.setAttribute("upload", sce.getServletContext().getContextPath() + "/upload");
    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context
         (the Web application) is undeployed or
         Application Server shuts down.
      */
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
        log.info("session create: session id " + se.getSession().getId());
        /* Session is created. */
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        log.info("session Destroyed: session id " + se.getSession().getId());
        /* Session is destroyed. */
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
        log.info("attribute Add: name " + sbe.getName());
      /* This method is called when an attribute
         is added to a session.
      */
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
        log.info("attribute Remove: name " + sbe.getName());
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
        log.info("attribute Replaced: name " + sbe.getName());
      /* This method is invoked when an attibute
         is replaced in a session.
      */
    }
}
