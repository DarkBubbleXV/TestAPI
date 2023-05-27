package com.testapi.testapi.controller;

import com.testapi.testapi.dto.MemberRequest;
import com.testapi.testapi.dto.MemberRespond;
import com.testapi.testapi.dto.RespondDto;
import com.testapi.testapi.model.Member;
import com.testapi.testapi.repository.MemberRepo;
import com.testapi.testapi.util.ErrorParsingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/member")
public class MemberController {

    @Autowired
    private MemberRepo memberRepo;

    @PostMapping
    public ResponseEntity<RespondDto<?>> createMember(@Valid @RequestBody MemberRequest memberRequest, Errors errors){
        RespondDto<MemberRespond> memberRespondRespondDto = new RespondDto<>();

        if(errors.hasErrors()){
            memberRespondRespondDto.setStatus(false);
            memberRespondRespondDto.setMessage(ErrorParsingUtil.parse(errors));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(memberRespondRespondDto);
        }

        try {
            Member member = new Member();
            member.setMemberEmail(memberRequest.getMemberEmail());
            member.setMemberName(memberRequest.getMemberName());
            member.setMemberNumber(memberRequest.getMemberNumber());
            member.setMemberPassword(memberRequest.getMemberPassword());
            member = memberRepo.save(member);

            memberRespondRespondDto.setStatus(true);
            memberRespondRespondDto.getMessage().add("Member saved!");
            MemberRespond memberRespond = new MemberRespond();
            memberRespond.setMemberName(member.getMemberName());
            memberRespond.setMemberEmail(member.getMemberEmail());
            memberRespond.setMemberNumber(member.getMemberNumber());
            memberRespond.setId(member.getId());
            memberRespondRespondDto.setPayload(memberRespond);
            return ResponseEntity.ok(memberRespondRespondDto);
        } catch (Exception e){
            memberRespondRespondDto.setStatus(false);
            memberRespondRespondDto.getMessage().add(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(memberRespondRespondDto);
        }
    }

    @GetMapping
    public ResponseEntity<RespondDto<List<MemberRespond>>> findAll(){
        RespondDto<List<MemberRespond>> respondDto = new RespondDto<>();
        List<Member> memberList = new ArrayList<>();
        memberRepo.findAll().forEach(memberList::add);
        List<MemberRespond> memberResponds = new ArrayList<>();
        memberList.forEach(member -> {
            MemberRespond memberRespond = new MemberRespond();
            memberRespond.setId(member.getId());
            memberRespond.setMemberNumber(member.getMemberNumber());
            memberRespond.setMemberEmail(member.getMemberEmail());
            memberRespond.setMemberName(member.getMemberName());
            memberResponds.add(memberRespond);
        });
        respondDto.setStatus(true);
        respondDto.setPayload(memberResponds);
        return ResponseEntity.ok(respondDto);
    }
}
