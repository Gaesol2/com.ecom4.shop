package com.ecom4.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ecom4.custom.dto.MemberDTO;

@Mapper
public interface MemberDAO {

	int idCheck(String mem_id);

	int memberJoin(MemberDTO mdto);

	MemberDTO getMember(MemberDTO mdto);

	MemberDTO searchId(MemberDTO mdto);

	MemberDTO getInfo(MemberDTO mdto);

	MemberDTO searchPw(MemberDTO mdto);

	void updatePw(MemberDTO mdto);

	int getMemberTot(MemberDTO mdto);

	List<MemberDTO> getMembers(MemberDTO mdto);

	int memUpProc(MemberDTO mdto);

}
