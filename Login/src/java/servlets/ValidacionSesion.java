/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Conexion.ConBas;


public class ValidacionSesion extends HttpServlet {
   
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        
      response.setContentType("text/html");
      HttpSession sesion = request.getSession();
      ConBas Conectar = new ConBas();
            
      String Usuario = (String)sesion.getAttribute("Correo");
      String Contraseña = (String)sesion.getAttribute("Contraseña");
      

      if(Conectar.Conecta(Usuario, Contraseña)){
          
        out.println("<h1><center> Sesion de " + Conectar.ObtenerRango(Usuario) + "</center></h1>");
        
        if(Conectar.ObtenerRango(Usuario).equals("administrador"))
        {
            out.println("<a href=\"\\Login\\todos.html\">Pagina para todos<br><br>");
            out.println("<a href=\"\\Login\\admin.html\">Pagina de administrador 1<br>");
        }
        else
            if(Conectar.ObtenerRango(Usuario).equals("normal"))
            {
                out.println("<a href=\"\\Login\\todos.html\">Pagina para todos 1<br><br>");
                out.println("<a href=\"\\Login\\normal.html\">Pagina de usuario normal 1<br><br>");
            }
        else
                out.println("Escribiste mal el rango :c");
      }
      else
      {
          out.println("<h1> Sesion incorrecta, pruebe de nuevo </h1>");
          out.println("<p> Usuario: " + Usuario + "<br> Contraseña: " + Contraseña + "<p>");
      }
  
    }

}
