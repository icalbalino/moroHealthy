/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.ItemDao;
import dao.TrxDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Cart;
import model.Item;
import model.Trx;

/**
 *
 * @author DB1407
 */
@WebServlet(name = "Transaksi", urlPatterns = {"/transaksi"})
public class Transaksi extends HttpServlet {
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
        List<Cart> sessionCart = new ArrayList<>();
        HttpSession session = request.getSession();
        if(session.getAttribute("sessionCart") != null){
            sessionCart = (List<Cart>)session.getAttribute("sessionCart");
            System.out.println(sessionCart.toString());
        }
        if(request.getParameter("checkout")!=null){
            session.removeAttribute("sessionCart");
            session.setAttribute("sessionCart", null);
            System.out.println("checkout");
            session.setAttribute("sessionCart", new ArrayList<Cart>());
            Trx trx = new Trx(0, 2, new Date());
            for(Cart cart:sessionCart){
                System.out.println(cart.getQty());
            }
            new TrxDao().insert(trx, sessionCart);
        }else{
            List<Item> items = new ItemDao().getAll();
            System.out.println("parameters");
            System.out.println(request.getParameterNames());
            if(request.getParameter("addToCart")!=null){
                for(Item item:items){
                    if(request.getParameter("qty["+item.getId()+"]")!=null && !request.getParameter("qty["+item.getId()+"]").isEmpty()){
                        System.out.println(request.getParameter("qty["+item.getId()+"]"));
                        Cart cart = new Cart(item, Integer.parseInt(request.getParameter("qty["+item.getId()+"]")));
                        sessionCart.add(cart);
                    }
                }
            }
            System.out.println("set attribute");
            session.setAttribute("sessionCart", sessionCart);
        }
        
        response.sendRedirect("transaksi.jsp");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            
//        }
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
