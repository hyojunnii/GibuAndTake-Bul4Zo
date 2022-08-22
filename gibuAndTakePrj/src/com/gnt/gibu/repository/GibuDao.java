package com.gnt.gibu.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static com.gnt.common.JDBCTemplate.*;

import com.gnt.common.ReplyVo;
import com.gnt.gibu.vo.GibuVo;

public class GibuDao {

	// 조회
	public List<GibuVo> selectList(Connection conn, int type) {

		// sql 준비
		String sql= "SELECT *  FROM DONATION D JOIN REGIST R ON D.REG_NO = R.REG_NO JOIN MEMBER M ON R.M_NO = M.M_NO JOIN REGIMG RI ON R.REG_NO = RI.REG_NO  AND R.REG_CLASS = '기부' AND R.REG_FIN = 'N' ORDER BY D.D_NO DESC";
		String paraclass = "";
		if(type!=0) {
				sql = "SELECT *  FROM DONATION D JOIN REGIST R ON D.REG_NO = R.REG_NO JOIN MEMBER M ON R.M_NO = M.M_NO JOIN REGIMG RI ON R.REG_NO = RI.REG_NO  AND R.REG_CLASS = '기부' AND R.REG_FIN = 'N'  AND D.D_CLASS = ? ORDER BY D.D_NO DESC";	
		}
		
		if (type==1) {
			paraclass = "아동청소년"; 
		}else if(type==2) {
			paraclass = "어르신"; 
		}else if(type==3) {
			paraclass = "장애인"; 
		}else if(type==4) {
			paraclass = "다문화"; 
		}else if(type==5) {
			paraclass = "지구촌"; 
		}else if(type==6) {
			paraclass = "가족여성"; 
		}else if(type==7) {
			paraclass = "시민사회"; 
		}else if(type==8) {
			paraclass = "동물"; 
		}
		
		PreparedStatement pstmt = null;
		List<GibuVo> list = new ArrayList<GibuVo>();
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			if(type!=0) {
				pstmt.setString(1, paraclass);				
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {

				// 테스트
				String dno = rs.getString("D_NO");
				String regno = rs.getString("REG_NO");
				String dclass = rs.getString("D_CLASS");
				int dgmoney = rs.getInt("D_GMONEY");
				int dpmoney = rs.getInt("D_PMONEY");
				String dsdate = rs.getString("D_SDATE");
				String dedate = rs.getString("D_EDATE");
				String dperson = rs.getString("D_PERSON");
				
				String regclass = rs.getString("REG_CLASS");
				String regname = rs.getString("REG_NAME");
				String regcontent = rs.getString("REG_CONTENT");
				String regsdate = rs.getString("REG_SDATE");
				String regfdate = rs.getString("REG_FDATE");
				String regpass = rs.getString("REG_PASS");
				String regfin = rs.getString("REG_FIN");
				String regdel = rs.getString("REG_DEL");
				String regmod = rs.getString("REG_MOD");
				int regcnt = rs.getInt("REG_CNT");
				String mno = rs.getString("M_NO");
				String mnick = rs.getString("M_NICK");
				String imgsrc = rs.getString("URL");
				double per = (double) dpmoney / (double) dgmoney * 100;
				// 수정완료 퍼센트 구하기
				String moneyper = String.format("%.1f", per);
				;

				GibuVo vo = new GibuVo();
				vo.setDno(dno);
				vo.setRegno(regno);
				vo.setDclass(dclass);
				vo.setDgmoney(dgmoney);
				vo.setDpmoney(dpmoney);
				vo.setDsdate(dsdate);
				vo.setDedate(dedate);
				vo.setDperson(dperson);
				vo.setRegclass(regclass);
				vo.setRegname(regname);
				vo.setRegcontent(regcontent);
				vo.setRegsdate(regsdate);
				vo.setRegfdate(regfdate);
				vo.setRegpass(regpass);
				vo.setRegfin(regfin);
				vo.setRegdel(regdel);
				vo.setRegmod(regmod);
				vo.setRegcnt(regcnt);
				vo.setMno(mno);
				vo.setMnick(mnick);
				vo.setImgsrc(imgsrc);
				vo.setMoneypercent(moneyper);
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;

	}

	public int getListCount(Connection conn, int type) {
		int listcount = 0;
		// sql 준비
		String sql = "SELECT COUNT(*) FROM DONATION D JOIN REGIST R ON D.REG_NO = R.REG_NO AND R.REG_FIN = 'N'";
		String paraclass = "";
		if(type!=0) {
				sql = "SELECT COUNT(*) FROM DONATION D JOIN REGIST R ON D.REG_NO = R.REG_NO AND R.REG_FIN = 'N' AND D.D_CLASS = ?";	
		}
		
		if (type==1) {
			paraclass = "아동청소년"; 
		}else if(type==2) {
			paraclass = "어르신"; 
		}else if(type==3) {
			paraclass = "장애인"; 
		}else if(type==4) {
			paraclass = "다문화"; 
		}else if(type==5) {
			paraclass = "지구촌"; 
		}else if(type==6) {
			paraclass = "가족여성"; 
		}else if(type==7) {
			paraclass = "시민사회"; 
		}else if(type==8) {
			paraclass = "동물"; 
		}
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			if(type!=0) {
				pstmt.setString(1, paraclass);				
			}
			rs = pstmt.executeQuery();
			
			
			
			if(rs.next()) {
				listcount = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return listcount;

	}
	
	//만료된 기부 목록 조회
	public List<GibuVo> selectDoneList(Connection conn, int type) {
		// sql 준비
				String sql = "SELECT *  FROM DONATION D JOIN REGIST R ON D.REG_NO = R.REG_NO JOIN MEMBER M ON R.M_NO = M.M_NO JOIN REGIMG RI ON R.REG_NO = RI.REG_NO  AND R.REG_CLASS = '기부' AND R.REG_FIN = 'Y' ORDER BY D.D_NO DESC";
				String paraclass = "";
				if(type!=0) {
						sql = "SELECT *  FROM DONATION D JOIN REGIST R ON D.REG_NO = R.REG_NO JOIN MEMBER M ON R.M_NO = M.M_NO JOIN REGIMG RI ON R.REG_NO = RI.REG_NO  AND R.REG_CLASS = '기부' AND R.REG_FIN = 'Y'  AND D.D_CLASS = ? ORDER BY D.D_NO DESC";	
				}
				
				if (type==1) {
					paraclass = "아동청소년"; 
				}else if(type==2) {
					paraclass = "어르신"; 
				}else if(type==3) {
					paraclass = "장애인"; 
				}else if(type==4) {
					paraclass = "다문화"; 
				}else if(type==5) {
					paraclass = "지구촌"; 
				}else if(type==6) {
					paraclass = "가족여성"; 
				}else if(type==7) {
					paraclass = "시민사회"; 
				}else if(type==8) {
					paraclass = "동물"; 
				}
				
				PreparedStatement pstmt = null;
				List<GibuVo> list = new ArrayList<GibuVo>();
				ResultSet rs = null;
				try {
					pstmt = conn.prepareStatement(sql);
					if(type!=0) {
						pstmt.setString(1, paraclass);				
					}
					rs = pstmt.executeQuery();
					while (rs.next()) {

						// 테스트	
						String dno = rs.getString("D_NO");
						String regno = rs.getString("REG_NO");
						String dclass = rs.getString("D_CLASS");
						int dgmoney = rs.getInt("D_GMONEY");
						int dpmoney = rs.getInt("D_PMONEY");
						String dsdate = rs.getString("D_SDATE");
						String dedate = rs.getString("D_EDATE");
						String dperson = rs.getString("D_PERSON");
						
						String regclass = rs.getString("REG_CLASS");
						String regname = rs.getString("REG_NAME");
						String regcontent = rs.getString("REG_CONTENT");
						String regsdate = rs.getString("REG_SDATE");
						String regfdate = rs.getString("REG_FDATE");
						String regpass = rs.getString("REG_PASS");
						String regfin = rs.getString("REG_FIN");
						String regdel = rs.getString("REG_DEL");
						String regmod = rs.getString("REG_MOD");
						int regcnt = rs.getInt("REG_CNT");
						String mno = rs.getString("M_NO");
						String mnick = rs.getString("M_NICK");
						String imgsrc = rs.getString("URL");
						double per = (double) dpmoney / (double) dgmoney * 100;
						// 수정완료 퍼센트 구하기
						String moneyper = String.format("%.1f", per);
						

						GibuVo vo = new GibuVo();
						vo.setDno(dno);
						vo.setRegno(regno);
						vo.setDclass(dclass);
						vo.setDgmoney(dgmoney);
						vo.setDpmoney(dpmoney);
						vo.setDsdate(dsdate);
						vo.setDedate(dedate);
						vo.setDperson(dperson);
						vo.setRegclass(regclass);
						vo.setRegname(regname);
						vo.setRegcontent(regcontent);
						vo.setRegsdate(regsdate);
						vo.setRegfdate(regfdate);
						vo.setRegpass(regpass);
						vo.setRegfin(regfin);
						vo.setRegdel(regdel);
						vo.setRegmod(regmod);
						vo.setRegcnt(regcnt);
						vo.setMno(mno);
						vo.setMnick(mnick);
						vo.setImgsrc(imgsrc);
						vo.setMoneypercent(moneyper);
						list.add(vo);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					close(pstmt);
					close(rs);
				}
				return list;
	}
	
	//기간만료 기부 카운트
	public int getListDoneCount(Connection conn, int type) {
		int listcount = 0;
		// sql 준비
		String sql = "SELECT COUNT(*) FROM DONATION D JOIN REGIST R ON D.REG_NO = R.REG_NO AND R.REG_FIN = 'Y'";
		String paraclass = "";
		if(type!=0) {
				sql = "SELECT COUNT(*) FROM DONATION D JOIN REGIST R ON D.REG_NO = R.REG_NO AND R.REG_FIN = 'Y' AND D.D_CLASS = ?";	
		}
		
		if (type==1) {
			paraclass = "아동청소년"; 
		}else if(type==2) {
			paraclass = "어르신"; 
		}else if(type==3) {
			paraclass = "장애인"; 
		}else if(type==4) {
			paraclass = "다문화"; 
		}else if(type==5) {
			paraclass = "지구촌"; 
		}else if(type==6) {
			paraclass = "가족여성"; 
		}else if(type==7) {
			paraclass = "시민사회"; 
		}else if(type==8) {
			paraclass = "동물"; 
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			if(type!=0) {
				pstmt.setString(1, paraclass);				
			}
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listcount = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return listcount;
	}

	
	//상세보기
	public GibuVo selectDetail(Connection conn, int type, int num) {
		// sql 준비
				String sql = "SELECT *  FROM DONATION D JOIN REGIST R ON D.REG_NO = R.REG_NO JOIN MEMBER M ON R.M_NO = M.M_NO JOIN REGIMG RI ON R.REG_NO = RI.REG_NO  AND R.REG_CLASS = '기부' AND R.REG_FIN = 'N' AND R.REG_NO = ?";
				String paraclass = "";
				if(type!=0) {
					sql = "SELECT *  FROM DONATION D JOIN REGIST R ON D.REG_NO = R.REG_NO JOIN MEMBER M ON R.M_NO = M.M_NO JOIN REGIMG RI ON R.REG_NO = RI.REG_NO  AND R.REG_CLASS = '기부' AND R.REG_FIN = 'N' AND R.REG_NO = ? AND D.D_CLASS = ? ";	
				}			
				
				
				if (type==1) {
					paraclass = "아동청소년"; 
				}else if(type==2) {
					paraclass = "어르신"; 
				}else if(type==3) {
					paraclass = "장애인"; 
				}else if(type==4) {
					paraclass = "다문화"; 
				}else if(type==5) {
					paraclass = "지구촌"; 
				}else if(type==6) {
					paraclass = "가족여성"; 
				}else if(type==7) {
					paraclass = "시민사회"; 
				}else if(type==8) {
					paraclass = "동물"; 
				}
				
				PreparedStatement pstmt = null;
				GibuVo vo = new GibuVo();
				ResultSet rs = null;
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, num);
					if(type!=0) {
						pstmt.setString(2, paraclass);
					}
					rs = pstmt.executeQuery();
					while (rs.next()) {

						// 테스트
						String dno = rs.getString("D_NO");
						String regno = rs.getString("REG_NO");
						String dclass = rs.getString("D_CLASS");
						int dgmoney = rs.getInt("D_GMONEY");
						int dpmoney = rs.getInt("D_PMONEY");
						String dsdate = rs.getString("D_SDATE");
						String dedate = rs.getString("D_EDATE");
						String dperson = rs.getString("D_PERSON");
						
						String regclass = rs.getString("REG_CLASS");
						String regname = rs.getString("REG_NAME");
						String regcontent = rs.getString("REG_CONTENT");
						String regsdate = rs.getString("REG_SDATE");
						String regfdate = rs.getString("REG_FDATE");
						String regpass = rs.getString("REG_PASS");
						String regfin = rs.getString("REG_FIN");
						String regdel = rs.getString("REG_DEL");
						String regmod = rs.getString("REG_MOD");
						int regcnt = rs.getInt("REG_CNT");
						String mno = rs.getString("M_NO");
						String mnick = rs.getString("M_NICK");
						String imgsrc = rs.getString("URL");
						double per = (double) dpmoney / (double) dgmoney * 100;
						// 수정완료 퍼센트 구하기
						String moneyper = String.format("%.1f", per);
						;

						vo = new GibuVo();
						vo.setDno(dno);
						vo.setRegno(regno);
						vo.setDclass(dclass);
						vo.setDgmoney(dgmoney);
						vo.setDpmoney(dpmoney);
						vo.setDsdate(dsdate);
						vo.setDedate(dedate);
						vo.setDperson(dperson);
						vo.setRegclass(regclass);
						vo.setRegname(regname);
						vo.setRegcontent(regcontent);
						vo.setRegsdate(regsdate);
						vo.setRegfdate(regfdate);
						vo.setRegpass(regpass);
						vo.setRegfin(regfin);
						vo.setRegdel(regdel);
						vo.setRegmod(regmod);
						vo.setRegcnt(regcnt);
						vo.setMno(mno);
						vo.setMnick(mnick);
						vo.setImgsrc(imgsrc);
						vo.setMoneypercent(moneyper);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					close(pstmt);
					close(rs);
				}
				return vo;
	}

	public List<ReplyVo> selectReply(Connection conn, int type, int num) {
		// sql 준비
		String sql = "SELECT * FROM REPLY RP JOIN MEMBER M ON RP.M_NO = M.M_NO WHERE RP.REG_NO=? AND RP.REP_DEL= 'N' ORDER BY RP.REP_NO DESC";
		String paraclass = "";
		if(type!=0) {
			sql = "";	
		}			
		
		
//		if (type==1) {
//			paraclass = "아동청소년"; 
//		}else if(type==2) {
//			paraclass = "어르신"; 
//		}else if(type==3) {
//			paraclass = "장애인"; 
//		}else if(type==4) {
//			paraclass = "다문화"; 
//		}else if(type==5) {
//			paraclass = "지구촌"; 
//		}else if(type==6) {
//			paraclass = "가족여성"; 
//		}else if(type==7) {
//			paraclass = "시민사회"; 
//		}else if(type==8) {
//			paraclass = "동물"; 
//		}
		
		PreparedStatement pstmt = null;
		List<ReplyVo> volist = new ArrayList<ReplyVo>();
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
//			if(type!=0) {
//				pstmt.setString(2, paraclass);
//			}
			rs = pstmt.executeQuery();
			while (rs.next()) {

				// 테스트
				String rep_no = rs.getString("REP_NO");		//댓글번호
				String mno = rs.getString("M_NO");			//사용자번호
				String regno = rs.getString("REG_NO");		//등록번호
				String repcontent = rs.getString("REP_CONTENT");	//댓글내용
				String repdate = rs.getString("REP_DATE");		//작성일
				String repdel = rs.getString("REP_DEL");		//삭제여부
				String repban = rs.getString("REP_BAN");		//신고여부
				String repmod = rs.getString("REP_MOD");		//마지막수정일자
				String mnick = rs.getString("M_NICK");

				ReplyVo vo = new ReplyVo();
				vo.setRep_no(rep_no);
				vo.setMno(mno);
				vo.setRegno(regno);
				vo.setRepcontent(repcontent);
				vo.setRepdate(repdate);
				vo.setRepdel(repdel);
				vo.setRepban(repban);
				vo.setRepmod(repmod);
				vo.setMnick(mnick);;
				
				volist.add(vo);
				

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return volist;

	}

}
