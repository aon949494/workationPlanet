package org.zerock.p01.service;

import org.zerock.p01.Entity.Group;
import org.zerock.p01.dto.GroupDTO;

import java.util.List;

public interface GroupService {
    // groupInsert
    String groupInsert(GroupDTO groupDTO);
    // groupRead
    GroupDTO groupReadOne(String groupName);
    List<GroupDTO> groupReadAll();
    // groupModify
    void groupModify(GroupDTO groupDTO);
    // groupDelete
    void groupDelete(String groupName);
    boolean groupCertify(String groupName, String groupPw); // groupName, pw 체크
    boolean groupManagerChk(String groupName, String groupManager); // groupManager 체크

}
