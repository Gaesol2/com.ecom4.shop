package com.ecom4.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom4.common.dto.PageDTO;
import com.ecom4.custom.dto.MemberDTO;
import com.ecom4.member.dao.MemberDAO;

@Service
public class MemeberServiceImpl implements MemberService {
	@Autowired
	MemberDAO memberDao;
	
	@Override
	public int idCheck(String mem_id) {
		
		return memberDao.idCheck(mem_id);
	}

	@Override
	public int memberJoin(MemberDTO mdto) {
		
		return memberDao.memberJoin(mdto);
	}

	@Override
	public MemberDTO getMember(MemberDTO mdto) {
		
		return memberDao.getMember(mdto);
	}

	@Override
	public MemberDTO searchId(MemberDTO mdto) {

		return memberDao.searchId(mdto);
	}

	@Override
	public MemberDTO searchPw(MemberDTO mdto) {
		return memberDao.searchPw(mdto);
	}

	@Override
	public MemberDTO getInfo(MemberDTO mdto) {
		
		return memberDao.getInfo(mdto);
	}

	@Override
	public void updatePw(MemberDTO mdto) {
		memberDao.updatePw(mdto);
	}

	@Override
	public Map<String, Object> getMembers(MemberDTO mdto, PageDTO pdto) {
		Map<String, Object> reSet = new HashMap<>();
		//전체 카운트 가져오기
		int memberTot = memberDao.getMemberTot(mdto);
		List<MemberDTO> members = memberDao.getMembers(mdto);
		
		reSet.put("memberTot", memberTot);
		//전체 회원리스트 가지고 오기
		reSet.put("members", members);
		//reSet에 저장하기
		return reSet;
	}

	@Override
	public int memUpProc(MemberDTO mdto) {
		return memberDao.memUpProc(mdto);
	}

	@Override
	public int memDelete(MemberDTO mdto) {
		
		return memberDao.memDelete(mdto);
	}

}
