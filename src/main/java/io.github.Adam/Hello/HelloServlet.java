package io.github.Adam.Hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Hello", urlPatterns = {"/api"})
public class HelloServlet extends HttpServlet {
    private final String NAME_PARAM = "name";
    private final String LANG_PARAM = "lang";

    private final Logger logger = LoggerFactory.getLogger(HelloServlet.class);
    private HelloService service;

    public HelloServlet() {
        this(new HelloService());
    }

    //connection servlet-service
    HelloServlet(HelloService service) {
        this.service = service;
    }




    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Get parameter" + req.getParameterMap());
        var name = req.getParameter(NAME_PARAM);
        var lang = req.getParameter(LANG_PARAM);

        Integer langId = null;

        try {
            langId = (Integer.valueOf(lang));
        } catch (NumberFormatException e) {
            //logger.warn(("Non-numeric language id used: " + langId));

        }
//        var greeting = service.prepareGreeting(name);
        resp.getWriter().write(service.prepareGreeting(name,langId));

    }
}