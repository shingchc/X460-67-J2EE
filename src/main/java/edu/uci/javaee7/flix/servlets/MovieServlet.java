/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uci.javaee7.flix.servlets;

import edu.uci.javaee7.flix.entities.Movie;
import edu.uci.javaee7.flix.logic.MovieService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Shing-Cheung
 */
@WebServlet(name = "MovieServlet", urlPatterns = {"/movies"})
public class MovieServlet extends HttpServlet {

    @EJB
    MovieService movieService;
    
//    @Override
//    public void init(ServletConfig config)  {
//        movieService = new MovieService();
//        movieService.init();
//    }
    
//    @Override
//    public void destroy()   {
//        movieService.destroy();
//    }
    
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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MovieServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            
            String command = request.getParameter("command");
            switch (command)    {
                case "list":
                    listMovies(out);
                    break;
                case "get":
                    getMovie(request, out);
                    break;
                case "add":
                    addMovie(request, out);
                    break;
                case "delete":
                    deleteMovie(request, out);
                    break;
                default:
                    out.println("Invalid commnad");
            }
            
            out.println("<h1>Servlet MovieServlet at " + request.getContextPath() + "</h1>");
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

    private void listMovies(PrintWriter out) {
        movieService.listMovies()
                .forEach(m -> {
                    out.print(m);
                    m.getActorCollection()
                            .forEach(a -> out.print(";" + a.getName()));
                    out.println("<br>");
                });
   }

    private void getMovie(HttpServletRequest request, PrintWriter out) throws NumberFormatException {
        Movie movie;
        movie = movieService.getMovie(Integer.parseInt(request.getParameter("id")));
        out.println(movie);
        movie.getActorCollection().forEach(a -> out.println("; " + a.getName()));
        out.println("<br>");
    }

    private void addMovie(HttpServletRequest request, PrintWriter out) throws NumberFormatException {
        
        Movie movie = new Movie();
        movie.setId(Integer.parseInt(request.getParameter("id")));
        movie.setName(request.getParameter("name"));
        movie.setYr(Integer.parseInt(request.getParameter("yr")));
        movie.setSynopsis(request.getParameter("synopsis"));

        movieService.addMovie(movie);
        out.println(movie.getName() + " added <br>");
    }

    private void deleteMovie(HttpServletRequest request, PrintWriter out) throws NumberFormatException {

        Movie movie;
        movie = movieService.getMovie(Integer.parseInt(request.getParameter("id")));
        movieService.deleteMovie(movie);
        out.println("Deleted movie " + movie.getName() + "<br>");
    }

}
