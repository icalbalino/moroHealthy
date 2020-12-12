/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
@WebServlet(name = "dashboard", urlPatterns = {"/dashboard"})
public class dashboard extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet dashboard</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet dashboard at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
            
            
            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");

            out.println("<head>");
            out.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css' integrity='sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2' crossorigin='anonymous'>");
            out.println("<script src='https://code.jquery.com/jquery-3.5.1.slim.min.js' integrity='sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj' crossorigin='anonymous'> </script>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js' integrity='sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN' crossorigin='anonymous'> </script>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js' integrity='sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s' crossorigin='anonymous'> </script>");
            out.println("<script src='https://kit.fontawesome.com/3b79ccf7db.js' crossorigin='anonymous'> </script>");
            out.println("<link rel='stylesheet' href='./css/style.css'>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<title>moroHealthy</title>");
            out.println("</head>");

            out.println("<body>");

                out.println("<div class='d-flex' id='wrapper'>");
                    out.println("<div class='vh-100 side-menu-container d-flex flex-column justify-content space-between' id='side-menu'>");
                        out.println("<div class='menu-title'><img src='./img/jamu3.jpg' alt=''></div>");
                        out.println("<div class='list-group list-group-flush'>");
                            out.println("<a href='' class='list-group-item list-group-item-action'> <i class='fas fa-home col-2'></i> <span class='col'>Dashboard</span></a>");
                            out.println("<a href='' class='list-group-item list-group-item-action'> <i class='fas fa-money-check col-2'></i> <span class='col'>Transaksi</span></a>");
                            out.println("<a href='' class='list-group-item list-group-item-action'> <i class='fas fa-history col-2'></i> <span class='col'>History</span></a>");
//                            out.println("<a class='btn btn-success text-light' href=''>Logout</a>");
                        out.println("</div>");    
                    out.println("</div>");

                    out.println("<div class='col container-fluid content'>");
                        out.println("<div class=' h3 alert alert-warning mt-3' role='alert'> Halo, <?php echo $_SESSION['user']['nama']?> </div>");
                        out.println("<h4>Ringkasan hari ini</h4>");

                        out.println("<div class='card border-0 bg-light'>");
                            out.println("<div class='card-body card-shadow'>");
                                out.println("<div class='row padd justify-content space-between'>");
                                    out.println("<div class='col-sm-3 padd card-shadow border-rad d-flex flex-column align-items-center'>");
                                        out.println("<div class='h4'>Pembeli</div>");
                                        out.println("<div class='h6' style='color: #285A84'>0[not found]</div>");
                                    out.println("</div>");
                                    out.println("<div class='col'></div>");
                                    out.println("<div class='col-sm-3 padd card-shadow border-rad d-flex flex-column align-items-center'>");
                                        out.println("<div class='h4'>Transaksi</div>");
                                        out.println("<div class='h6' style='color: #285A84'>0[not found]</div>");
                                    out.println("</div>");
                                    out.println("<div class='col'></div>");
                                    out.println("<div class='col-sm-3 padd card-shadow border-rad d-flex flex-column align-items-center'>");
                                        out.println("<div class='h4'>Barang</div>");
                                        out.println("<div class='h6' style='color: #285A84'>0[not found]</div>");
                                    out.println("</div>");
                                out.println("</div>");
                            out.println("</div>");
                        out.println("</div>");
                    out.println("</div>");
                out.println("</div>");
                
//                <script>
//                    $('#side-menu-toggle').click(function(e){
//                        console.log('tes')
//                        e.preventDefault();
//                        $("#side-menu").width=10
//                    });
//                </script>
            out.println("</body>");

            out.println("</html>");
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
