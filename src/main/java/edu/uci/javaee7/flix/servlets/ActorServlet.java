/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uci.javaee7.flix.servlets;

import edu.uci.javaee7.flix.entities.Actor;
import edu.uci.javaee7.flix.logic.ActorBean;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Shing-Cheung
 */
@WebServlet(name = "ActorServlet", urlPatterns = {"/actors"})
public class ActorServlet extends HttpServlet {

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
        
        HttpSession session = request.getSession(true);
        ActorBean actorBean;
        if ((actorBean = (ActorBean) session.getAttribute("actorBean")) == null)    {
            actorBean = new ActorBean();
            session.setAttribute("actorBean", actorBean);
        }
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ActorServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            
            String command = request.getParameter("command");
            switch (command)    {
                case "list":
                    listActors(out, actorBean);
                    break;
                case "add":
                    addActor(out, request, response, actorBean);
                    break;
                case "delete":
                    deleteActor(out, request, response, actorBean);
                    break;
            }
            
//            out.println("<h1>Servlet ActorServlet at " + request.getContextPath() + "</h1>");
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

    private void listActors(PrintWriter out, ActorBean actorBean) {
        actorBean.getActors().forEach(out::println);
    }

    private void addActor(PrintWriter out, HttpServletRequest request, HttpServletResponse response, ActorBean actorBean) {
        Actor actor = new Actor();
        actor.setId(Integer.parseInt(request.getParameter("id")));
        actor.setName(request.getParameter("name"));
        actor.setAbout(request.getParameter("about"));
        actorBean.addActor(actor);
        out.println(actor.getName() + " added <br>");
    }

    private void deleteActor(PrintWriter out, HttpServletRequest request, HttpServletResponse response, ActorBean actorBean) {
        int id = Integer.parseInt(request.getParameter("id"));
        Actor actor = actorBean.getActor(id);
        actorBean.deleteActor(actor);
        out.println(actor.getName() + " removed <br>");
    }

}
