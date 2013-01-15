/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package images;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrateur
 */
public class Download extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Download</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Download at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        System.out.println("Le programe passe bien par ici");
                
        response.setContentType("image/PNG");
        response.setHeader("Content-Disposition", "attachment;filename=\"Capture_1357488150987.PNG\"");

        ServletOutputStream out = response.getOutputStream();
        File file = null;
        BufferedInputStream from = null;
        try {
            //dans mon cas le filepath et le path complet après création
            // d'un temp file 
            file = new File("/fotosteppictures/user9/Capture_1357488150987.PNG");
            response.setContentLength((int) file.length());
            int bufferSize = 64 * 1024;
            long time = System.currentTimeMillis();

            try {
                from = new BufferedInputStream(new FileInputStream(file), bufferSize * 2);
                byte[] bufferFile = new byte[bufferSize];
                for (int i = 0;; i++) {
                    int len = from.read(bufferFile);
                    if (len < 0) {
                        break;
                    }
                    out.write(bufferFile, 0, len);
                }
                out.flush();
            } finally {
                try {
                    from.close();
                } catch (Exception e) {
                }
                try {
                    out.close();
                } catch (Exception e) {
                }
            }
            time = (System.currentTimeMillis() - time) / 1000;
            // seconds download 
            double kb = (file.length() * 1.0 / 1024);

            if (file != null) {
                file.delete();
            }
        } catch (Exception e) {
            return;
        } finally {
            try {
                file.delete();
            } catch (Exception ex) {
            }
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
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
