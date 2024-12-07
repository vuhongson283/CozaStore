/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package Filter;

import DAO.AccountDAO;
import Model.RoleFeature;
import Model.Users;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luu Bach
 */
public class AuthorizationFilter implements Filter {

    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public AuthorizationFilter() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthorizationFilter:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
        /*
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    String values[] = request.getParameterValues(name);
	    int n = values.length;
	    StringBuffer buf = new StringBuffer();
	    buf.append(name);
	    buf.append("=");
	    for(int i=0; i < n; i++) {
	        buf.append(values[i]);
	        if (i < n-1)
	            buf.append(",");
	    }
	    log(buf.toString());
	}
         */
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthorizationFilter:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
        /*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
         */
        // For example, a filter might append something to the response.
        /*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    AccountDAO accountDAO = new AccountDAO();

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        if (debug) {
            log("AuthorizationFilter:doFilter()");
        }

        doBeforeProcessing(request, response);

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String url = req.getServletPath();
        HttpSession session = req.getSession();
        Users user = (Users) session.getAttribute("user");
//        res.getWriter().print(url);
        if (isResource(url)) {
            chain.doFilter(request, response);
        } else if (!url.contains("/login")) {
            session.setAttribute("requestedUrl", req.getRequestURI());
            //Authorization For Guest
            if (session.getAttribute("user") == null) {
                user = new Users();
                user.setUser_roleId("6");
                ArrayList<RoleFeature> roleFeatures;
                try {
                    roleFeatures = accountDAO.getAllRoleFeatureForGuest(user);
                    if (roleFeatures.size() == 0) {
                    } else {
                        boolean isAccessed = false;
                        for (RoleFeature rf : roleFeatures) {
                            if (url.contains(rf.getRoleURL()) && !url.contains("/home.jsp")) {
                                isAccessed = true;
                                break;
                            }
                        }
                        if (!isAccessed) {
                            String requestedUrl = (String) session.getAttribute("requestedUrl");
                            if (url.equals("/home.jsp")) {
                                url = "localhost:9999/SWP-demo3/home";
                                res.sendRedirect(url);
                                return;
                            }
                            res.getWriter().print(roleFeatures.size());
                            res.sendRedirect("localhost:9999" + requestedUrl);
                            return;
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(AuthorizationFilter.class.getName()).log(Level.SEVERE, null, ex);
                    res.sendRedirect("localhost:9999" + (String) session.getAttribute("requestedUrl"));
                    return;
                }
                //Authorization For other role
            } else {
                try {
                    ArrayList<RoleFeature> roleFeatures = accountDAO.getAllRoleFeature(user);
                    if (roleFeatures.size() == 0) {
                        response.getWriter().print("access denied");
                    } else {
                        boolean isAccessed = false; // Use boolean instead of int
                        for (RoleFeature rf : roleFeatures) {
                            if (url.contains(rf.getRoleURL()) && !url.contains("/home.jsp")) {
                                isAccessed = true;
                                break;
                            }
                        }
                        if (!isAccessed) {
                            res.sendRedirect("localhost:9999" + (String) session.getAttribute("requestedUrl"));
                            return;
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(AuthorizationFilter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        Throwable problem = null;
        try {
            chain.doFilter(request, response);
        } catch (Throwable t) {
            // If an exception is thrown somewhere down the filter chain,
            // we still want to execute our after processing, and then
            // rethrow the problem after that.
            problem = t;
            t.printStackTrace();
        }

        doAfterProcessing(request, response);

        // If there was a problem, we want to rethrow it if it is
        // a known type, otherwise log it.
        if (problem != null) {
            if (problem instanceof ServletException) {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException) {
                throw (IOException) problem;
            }
            sendProcessingError(problem, response);
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("AuthorizationFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("AuthorizationFilter()");
        }
        StringBuffer sb = new StringBuffer("AuthorizationFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

    private boolean isJspOrServlet(String url) {
        return url.endsWith(".jsp") || url.startsWith("/controller");
    }

    private boolean isResource(String url) {
        // Define a list of resource extensions that should not be filtered
        String[] resourceExtensions = {".css", ".js", ".png", ".jpg", ".jpeg", ".gif", ".ttf", "v=2.2.0", "v=4.7.0", "woff2", "woff"};

        for (String extension : resourceExtensions) {
            if (url.endsWith(extension)) {
                return true;
            }
        }
        return false;
    }

}
