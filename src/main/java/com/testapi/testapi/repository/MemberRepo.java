package com.testapi.testapi.repository;

import com.testapi.testapi.model.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepo extends CrudRepository<Member, Long> {

    boolean existsMemberByMemberNumber(String number);
    boolean existsMemberByMemberEmail(String email);
}
