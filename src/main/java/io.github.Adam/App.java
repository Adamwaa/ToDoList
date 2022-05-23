package io.github.Adam;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.plus.webapp.EnvConfiguration;
import org.eclipse.jetty.plus.webapp.PlusConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.component.AbstractLifeCycle;
import org.eclipse.jetty.util.component.LifeCycle;
import org.eclipse.jetty.webapp.*;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;

public class App {

    public static void main(String[] args) throws Exception {


        var webapp = new WebAppContext();

        // processing the annotation
        webapp.setResourceBase("src/main/webapp");
        webapp.setContextPath("/");
        // annotation scan
        webapp.setConfigurations(new Configuration[] {
                new AnnotationConfiguration(),
                new WebInfConfiguration(),
                new WebXmlConfiguration(),
                new MetaInfConfiguration(),
                new FragmentConfiguration(),
                new EnvConfiguration(),
                new PlusConfiguration(),
                new JettyWebXmlConfiguration()
        });

        //Set Atrribute value to all classes*
        webapp.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern", ".*/classes/.*");

        var  server = new Server(8080);
        server.setHandler(webapp);

        server.start();
        server.join();

        server.addLifeCycleListener(new AbstractLifeCycle.AbstractLifeCycleListener() {
            @Override
            public void lifeCycleStopped(LifeCycle event) {
                HibernateUtil.close();
            }
        });
        webapp.setInitParameter("org.eclipse.jetty.servlet.Default.maxCachedFiles", "0");


        var logger = LoggerFactory.getLogger(App.class);
        logger.info("Hello World");

    }

}
