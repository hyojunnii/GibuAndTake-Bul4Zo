package com.gnt.campaignproof.repository;

import static com.gnt.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.gnt.campaignproof.vo.CampaignProofVo;

public class CampaignProofDao {

	//캠페인 인증 삽입
	public int insertProof(Connection conn, CampaignProofVo proofVo) {
		String sql = "INSERT INTO CAMPAIGNPROOF(CP_NO, M_NO, CAM_NO, CP_CONTENT) VALUES(SEQ_CAMPAIGNPROOF_CP_NO.NEXTVAL, ?, ?, ?)";
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql); 
			pstmt.setInt(1, proofVo.getCamNo()); 
			pstmt.setInt(2, proofVo.getmNo());
			pstmt.setString(3, proofVo.getContent()); 
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	//캠페인 인증 이미지 삽입
	public int insertProofImg(Connection conn, CampaignProofVo proofVo, String changeName) {
		String sql = "INSERT INTO PROOFIMG(PR_NO, CP_NO, URL) VALUES(SEQ_PROOFIMG_PR_NO.NEXTVAL, SEQ_CAMPAIGNPROOF_CP_NO.CURRVAL, ?)";
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, changeName); 
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

}
