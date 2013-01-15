/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package images;

import business.model.database.FormatEnum;
import business.model.database.Picture;
import business.model.databaseManager.pictureManager.PictureManagerLocal;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
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
    
    @EJB
    private PictureManagerLocal pictureManagerLocal;

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
        
        String userId = request.getParameter("UserId");
        String pictureId = request.getParameter("PictureId");
                 
        Picture pictureToView = pictureManagerLocal.findPictureById(Integer.parseInt(pictureId));
        File filePicture = new File(pictureToView.getPath());
        
        String format = null;
        if (pictureToView.getFormat() == FormatEnum.jpg) {
            format = "jpeg";
        } else if (pictureToView.getFormat() == FormatEnum.png) {
            format = "PNG";
        }
        response.setContentType("image/" + format);
        response.setHeader("Content-Disposition", "attachment;filename=\"" + filePicture.getName() + "\"");
        ServletOutputStream out = response.getOutputStream();
        BufferedInputStream from = null;
        response.setContentLength((int) filePicture.length());
        int bufferSize = 64 * 1024;
        from = new BufferedInputStream(new FileInputStream(filePicture), bufferSize * 2);
        byte[] bufferFile = new byte[bufferSize];
        for (int i = 0;; i++) {
            int len = from.read(bufferFile);
            if (len < 0) {
                break;
            }
            out.write(bufferFile, 0, len);
        }
        out.flush();
        from.close();
        out.close();
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
