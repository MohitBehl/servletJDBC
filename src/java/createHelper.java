/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 16crypt
 */
class temp{
    Connection con = null;
    temp()throws ClassNotFoundException,SQLException{
        Class.forName("com.mysql.jdbc.Driver");
	String database = "servelet";
	String user = "root";
	String password = "";
	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + database, user, password);
    }
}
@WebServlet(urlPatterns = {"/createHelper"})
public class createHelper extends HttpServlet{
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    static String createQuery(String tableName,String colNames[],String colTypes[]){
        String query="CREATE TABLE "+tableName+" (";
        for(int i=0;i<colNames.length;i++){
            query += colNames[i]+" "+colTypes[i];
            if(i != colNames.length-1)
                query += ",";
        }
        query += ")";
        return query;
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            temp obj = new temp();
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>#PHP#MY#ADMIN</title>");            
            out.println("</head>");
            String tbName = request.getParameter("dbName");
            String tbColNum = request.getParameter("dbColNum");
            out.println("<body>");
            String []columnNames = request.getParameterValues("colName");
            
            String []columnTypes = request.getParameterValues("colType");
            out.println("<script>var uri = window.location.toString();\n" +
"if ()) {console.log(\"hello\");window.location.href=\"//localhost:8080/serveletJDBC\";}</script>");
            out.println(tbName);
            if(columnNames != null){
                String query1 = createQuery(tbName,columnNames,columnTypes);
                String query = createQuery(tbName,columnNames,columnTypes);
                out.println("<script>var uri = window.location.toString();\n" +
"if (uri.indexOf(\"?\") > 0) {\n" +
"    var clean_uri = uri.substring(0, uri.indexOf(\"?\"));\n" +
"    window.history.replaceState({}, document.title, clean_uri);\n" +
"}</script>");
                Statement stmt = obj.con.createStatement();
                stmt.execute(query);
                out.println("<script>");
                out.println("alert(\"TABLE CREATED SUCCESSFULLY\");");
                out.println("window.location.href=\"//localhost:8080/serveletJDBC\";");
                out.println("</script>");
            }
            out.println("TABLE :"+tbName);
            out.println("<form method=\'post\'>");
            out.println("<table>");
            for(int i=0;i<Integer.parseInt(tbColNum);i++){
                out.println("<tr>");
                out.println("<td>Column "+(i+1)+":Name</td>");
                out.println("<td><input name=\"colName\" type=\"text\" required=\"true\"></td>");
                out.println("<td><select name=\"colType\"><option value=\"varchar(300)\">text</option><option value=\"int(11)\">number</option></select>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("<input type=\"submit\" value=\"CREATE TABLE\">");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        }
        catch(Exception e){
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
