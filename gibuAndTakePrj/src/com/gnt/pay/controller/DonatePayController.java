package com.gnt.pay.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gnt.gibu.service.GibuService;
import com.gnt.gibu.vo.GibuVo;

@WebServlet(urlPatterns = "/donate/pay")
public class DonatePayController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int num = Integer.parseInt(req.getParameter("num"));
		
		GibuVo gibuvo = new GibuService().selectDetail(num);
		
		req.setAttribute("gibuvo", gibuvo);
		req.getRequestDispatcher("/views/payment/donatePay.jsp").forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int num = Integer.parseInt(req.getParameter("num"));
		int mno = Integer.parseInt(req.getParameter("mno"));
		int addmoney = Integer.parseInt(req.getParameter("addmoney"));
		System.out.println(num + ", " + addmoney +", " + mno);
		
		int result = new GibuService().donateMoney(num, mno, addmoney);
	}
}
