package com.gnt.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gnt.member.service.MemberService;
import com.gnt.member.vo.MemberVo;

@WebServlet(urlPatterns="/member/idCheck")
public class IdCheck extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/views/member/memberJoin.jsp").forward(req, resp);
	}
	
	
	
	/*
	 * @Override protected void doPost(HttpServletRequest req, HttpServletResponse
	 * resp) throws ServletException, IOException {
	 * 
	 * req.setCharacterEncoding("UTF-8");
	 * 
	 * String memberId = req.getParameter("memberId");
	 * 
	 * PrintWriter out = resp.getWriter();
	 * 
	 * MemberVo vo = new MemberVo();
	 * 
	 * vo.setId(memberId);
	 * 
	 * int idCheck = new MemberService().checkId(vo);
	 * 
	 * 
	 * }
	 */
	
}
