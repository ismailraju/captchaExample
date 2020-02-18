/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.captchaExample;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nl.captcha.backgrounds.BackgroundProducer;

/**
 *
 * @author Raju
 */
public class CustomServlet2 extends HttpServlet {

    public static final String FILE_TYPE = "jpg";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp); //To change body of generated methods, choose Tools | Templates.
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        super.doPost(req, resp); //To change body of generated methods, choose Tools | Templates.

        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Max-Age", 0);

        String captchaStr = "";
        try {

            nl.captcha.Captcha captcha = new nl.captcha.Captcha.Builder(200, 50)
                    .addText()
                    .addBackground( )
                    .addNoise()
                    .gimp()
                    .addBorder()
                    .build(); // Required. 

            HttpSession session = request.getSession(true);
            session.setAttribute("CAPTCHA", captcha.getAnswer());

            OutputStream outputStream = response.getOutputStream();

            ImageIO.write(captcha.getImage(), FILE_TYPE, outputStream);
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
