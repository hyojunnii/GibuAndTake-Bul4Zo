package com.gnt.review.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gnt.review.service.ReviewService;
import com.gnt.review.vo.ReviewVo;

@WebServlet(urlPatterns = "/campaign/review/view")
public class CampaignReviewViewController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String revNo = req.getParameter("id");
		
		ReviewVo vo = new ReviewService().selectReviewOne(revNo);
		
		req.getRequestDispatcher("/views/review/campaignReviewView.jsp").forward(req, resp);
	}
	
	
}
