package com.ecom4.member.service;

import java.util.Map;

import com.ecom4.common.dto.PageDTO;
import com.ecom4.custom.dto.MemberDTO;


public interface MemberService {
	int idCheck(String mem_id);

	int memberJoin(MemberDTO mdto);

	MemberDTO getMember(MemberDTO mdto);

	MemberDTO searchId(MemberDTO mdto);

	MemberDTO searchPw(MemberDTO mdto);

	MemberDTO getInfo(MemberDTO mdto);

	void updatePw(MemberDTO mdto);

	Map<String, Object> getMembers(MemberDTO mdto, PageDTO pdto);

	int memUpProc(MemberDTO mdto);
}
