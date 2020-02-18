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

/**
 *
 * @author Raju
 */
public class CustomServlet extends HttpServlet {

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

        //captchaStr=Util.generateCaptchaTextMethod();
        captchaStr = Captcha.generateCaptchaTextWithLenght(5);

        try {

            int width = 100;
            int height = 40;

            //Color bg = new Color(0, 255, 255);
            Color bg = new Color(255, 255, 255);
            Color fg = new Color(85, 170, 85);

            Font font = new Font("Arial", Font.BOLD, 20);
            BufferedImage cpimg = new BufferedImage(width, height, BufferedImage.OPAQUE);
            Graphics g = cpimg.createGraphics();

            g.setFont(font);
            g.setColor(bg);
            g.fillRect(0, 0, width, height);
            g.setColor(fg);
            g.drawString(captchaStr, 10, 25);

            HttpSession session = request.getSession(true);
            session.setAttribute("CAPTCHA", captchaStr);

            OutputStream outputStream = response.getOutputStream();

            ImageIO.write(cpimg, FILE_TYPE, outputStream);
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
