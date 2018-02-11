package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import Controllo.OperazioniDB;

public final class VerificaLogin_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Verifica</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("\t\t");

			try {
				String username = (String) request.getParameter("username");
				String password = (String) request.getParameter("password");
				out.println(username);
				out.println(password);
				OperazioniDB opDb = new OperazioniDB();
				System.out.println(opDb.login(username, password) == "Studente");
				if (opDb.login(username, password) == "Studente") {
		
      out.write("\n");
      out.write("\t\t\t\t\t");
      if (true) {
        _jspx_page_context.forward("Studente.jsp");
        return;
      }
      out.write("\n");
      out.write("\t\t");

				} else if (opDb.login(username, password) == "Docente") {
		
      out.write("\n");
      out.write("\t\t\t\t\t");
      if (true) {
        _jspx_page_context.forward("Docente.jsp");
        return;
      }
      out.write("\n");
      out.write("\t\t");

				} else {
		
      out.write("\n");
      out.write("\t\t\t\t\t");
      if (true) {
        _jspx_page_context.forward("Login.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("error", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("e", request.getCharacterEncoding()));
        return;
      }
      out.write("\n");
      out.write("\t\t");

				}
			} catch (Exception e) {
		
      out.write("\n");
      out.write("\t\t\t\t");
      if (true) {
        _jspx_page_context.forward("ErrorPage.jsp");
        return;
      }
      out.write("\n");
      out.write("\t\t");

			}
		
      out.write("\n");
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
      out.write("\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
