package org.zerock.p01.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.zerock.p01.dto.GroupTodoDTO;
import org.zerock.p01.dto.TodoDTO;
import org.zerock.p01.repository.GroupTodoRepository;
import org.zerock.p01.repository.TodoRepository;
import org.zerock.p01.service.GroupTodoService;
import org.zerock.p01.service.TodoService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/todo")
@Log4j2
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;
    private final GroupTodoRepository groupTodoRepository;
    private final TodoRepository todoRepository;
    private final GroupTodoService groupTodoService;


    //개인캘린더 db연동
    @RequestMapping(value="/pplan", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>>pplan(String memberId, Model model){
        log.info("pplan"+memberId);
        List<Map<String, Object>> list = todoRepository.findByTodo(memberId);
        JSONObject jsonObj = new JSONObject();
        JSONArray jsonArr = new JSONArray();
        HashMap<String, Object> hash = new HashMap<String, Object>();

        for(int i=0; i < list.size(); i++) {
            log.info(list.get(i).get("title"));
            log.info("++++++content=="+list.get(i).get("content"));
            hash.put("title", list.get(i).get("title")); //제목
            hash.put("subtitle",list.get(i).get("content"));//내용
            hash.put("finished",list.get(i).get("is_finished"));//true or false
            hash.put("writer",list.get(i).get("writer"));//memberId
            hash.put("regDate",list.get(i).get("reg_Date"));//regDate
            hash.put("modDate",list.get(i).get("mod_Date"));//modDate
            hash.put("lno",list.get(i).get("lno"));//lno
            hash.put("start", list.get(i).get("due_Date")); //시작일자
            hash.put("end", list.get(i).get("due_Date")); //종료일자
            if(list.get(i).get("is_finished").equals(true)){
                hash.put("color","#0000CD");
            }
            else{
                hash.put("color","#FF0000");
            }
            jsonObj = new JSONObject(hash); //중괄호 {key:value , key:value, key:value}
            jsonArr.add(jsonObj); // 대괄호 안에 넣어주기[{key:value , key:value, key:value},{key:value , key:value, key:value}]
        }
        log.info("jsonArrCheck: {}", jsonArr);

        return jsonArr;
    }
    //개인캘린더 등록액션
    @RequestMapping(value = "/pregister",method = RequestMethod.POST)
    @ResponseBody
    public List<Map<String, Object>> PregisterActionPOST(TodoDTO todoDTO,Model model){
        todoService.todoInsert(todoDTO);
        List<Map<String, Object>> list = todoRepository.findByTodo(todoDTO.getWriter());
        JSONObject jsonObj = new JSONObject();
        JSONArray jsonArr = new JSONArray();
        HashMap<String, Object> hash = new HashMap<String, Object>();

        for(int i=0; i < list.size(); i++) {
            log.info(list.get(i).get("title"));
            log.info("++++++content=="+list.get(i).get("content"));
            hash.put("title", list.get(i).get("title")); //제목
            hash.put("subtitle",list.get(i).get("content"));//내용
            hash.put("finished",list.get(i).get("is_finished"));//true or false
            hash.put("writer",list.get(i).get("writer"));//memberId
            hash.put("regDate",list.get(i).get("reg_Date"));//regDate
            hash.put("modDate",list.get(i).get("mod_Date"));//modDate
            hash.put("lno",list.get(i).get("lno"));//lno
            hash.put("start", list.get(i).get("due_Date")); //시작일자
            hash.put("end", list.get(i).get("due_Date")); //종료일자
            if(list.get(i).get("is_finished").equals(true)){
                hash.put("color","#0000CD");
            }
            else{
                hash.put("color","#FF0000");
            }
            jsonObj = new JSONObject(hash); //중괄호 {key:value , key:value, key:value}
            jsonArr.add(jsonObj); // 대괄호 안에 넣어주기[{key:value , key:value, key:value},{key:value , key:value, key:value}]
        }
        log.info("jsonArrCheck: {}", jsonArr);

        return jsonArr;
    }
    //개인캘린더 수정액션
    @RequestMapping(value = "/pmodify",method = RequestMethod.POST)
    @ResponseBody
    public List<Map<String, Object>> PupdateActionPOST(TodoDTO todoDTO,boolean isFinished,Model model){
        log.info("개인캘린더 수정액션 진입");
//        log.info(title);
        log.info("======"+todoDTO);
        log.info(isFinished);
        todoDTO.setFinished(isFinished);
        todoService.todoModify(todoDTO);
        log.info(todoDTO.isFinished());
        List<Map<String, Object>> list = todoRepository.findByTodo(todoDTO.getWriter());
        JSONObject jsonObj = new JSONObject();
        JSONArray jsonArr = new JSONArray();
        HashMap<String, Object> hash = new HashMap<String, Object>();

        for(int i=0; i < list.size(); i++) {
            log.info(list.get(i).get("title"));
            log.info("++++++content=="+list.get(i).get("content"));
            hash.put("title", list.get(i).get("title")); //제목
            hash.put("subtitle",list.get(i).get("content"));//내용
            hash.put("finished",list.get(i).get("is_finished"));//true or false
            hash.put("writer",list.get(i).get("writer"));//memberId
            hash.put("regDate",list.get(i).get("reg_Date"));//regDate
            hash.put("modDate",list.get(i).get("mod_Date"));//modDate
            hash.put("lno",list.get(i).get("lno"));//lno
            hash.put("start", list.get(i).get("due_Date")); //시작일자
            hash.put("end", list.get(i).get("due_Date")); //종료일자
            if(list.get(i).get("is_finished").equals(true)){
                hash.put("color","#0000CD");
            }
            else{
                hash.put("color","#FF0000");
            }
            jsonObj = new JSONObject(hash); //중괄호 {key:value , key:value, key:value}
            jsonArr.add(jsonObj); // 대괄호 안에 넣어주기[{key:value , key:value, key:value},{key:value , key:value, key:value}]
        }
        log.info("jsonArrCheck: {}", jsonArr);

        return jsonArr;
    }
    //개인일정 삭제액션
    @RequestMapping(value = "/premove",method = RequestMethod.POST)
    @ResponseBody
    public List<Map<String, Object>> PremoveActionPOST(Long lno,String writer,Model model){
        todoService.todoDelete(lno);
        List<Map<String, Object>> list = todoRepository.findByTodo(writer);

        JSONObject jsonObj = new JSONObject();
        JSONArray jsonArr = new JSONArray();
        HashMap<String, Object> hash = new HashMap<String, Object>();

        for(int i=0; i < list.size(); i++) {
            log.info(list.get(i).get("title"));
            log.info("++++++content=="+list.get(i).get("content"));
            hash.put("title", list.get(i).get("title")); //제목
            hash.put("subtitle",list.get(i).get("content"));//내용
            hash.put("finished",list.get(i).get("is_finished"));//true or false
            hash.put("writer",list.get(i).get("writer"));//memberId
            hash.put("regDate",list.get(i).get("reg_Date"));//regDate
            hash.put("modDate",list.get(i).get("mod_Date"));//modDate
            hash.put("lno",list.get(i).get("lno"));//lno
            hash.put("start", list.get(i).get("due_Date")); //시작일자
            hash.put("end", list.get(i).get("due_Date")); //종료일자
            if(list.get(i).get("is_finished").equals(true)){
                hash.put("color","#0000CD");
            }
            else{
                hash.put("color","#FF0000");
            }
            jsonObj = new JSONObject(hash); //중괄호 {key:value , key:value, key:value}
            jsonArr.add(jsonObj); // 대괄호 안에 넣어주기[{key:value , key:value, key:value},{key:value , key:value, key:value}]
        }
        log.info("jsonArrCheck: {}", jsonArr);

        return jsonArr;
    }

    //그룹캘린더 db연동
    @RequestMapping(value = "/plan", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>>plan(String groupName, Model model) {
        log.info(groupName);
        List<Map<String, Object>> list = groupTodoRepository.findByGroupTodo(groupName);
        log.info(list.size());


        JSONObject jsonObj = new JSONObject();
        JSONArray jsonArr = new JSONArray();
        HashMap<String, Object> hash = new HashMap<String, Object>();

        for(int i=0; i < list.size(); i++) {
            log.info(list.get(i).get("title"));
            log.info("++++++content=="+list.get(i).get("content"));
            hash.put("title", list.get(i).get("title")); //제목
            hash.put("subtitle",list.get(i).get("content"));//내용
            hash.put("finished",list.get(i).get("is_finished"));//true or false
            hash.put("memberId",list.get(i).get("member_Id"));//memberId
            hash.put("regDate",list.get(i).get("reg_Date"));//regDate
            hash.put("modDate",list.get(i).get("mod_Date"));//modDate
            hash.put("groupName",list.get(i).get("group_Name"));//groupName
            hash.put("glno",list.get(i).get("glno"));//glno
            hash.put("start", list.get(i).get("due_Date")); //시작일자
            hash.put("end", list.get(i).get("due_Date")); //종료일자
            if(list.get(i).get("is_finished").equals(true)){
                hash.put("color","#0000CD");
            }
            else{
                hash.put("color","#FF0000");
            }
            jsonObj = new JSONObject(hash); //중괄호 {key:value , key:value, key:value}
            jsonArr.add(jsonObj); // 대괄호 안에 넣어주기[{key:value , key:value, key:value},{key:value , key:value, key:value}]
        }
        log.info("jsonArrCheck: {}", jsonArr);

        return jsonArr;
    }
    //그룹캘린더 등록 액션
    @RequestMapping(value = "/gregister",method = RequestMethod.POST)
    @ResponseBody
    public List<Map<String, Object>> GregisterActionPOST(GroupTodoDTO groupTodoDTO,Model model){
        log.info("/-/-/-"+groupTodoDTO);
        groupTodoService.g_todoInsert(groupTodoDTO);
        List<Map<String, Object>> list = groupTodoRepository.findByGroupTodo(groupTodoDTO.getGroupName());
        JSONObject jsonObj = new JSONObject();
        JSONArray jsonArr = new JSONArray();
        HashMap<String, Object> hash = new HashMap<String, Object>();

        for(int i=0; i < list.size(); i++) {
            log.info(list.get(i).get("title"));
            log.info("++++++content=="+list.get(i).get("content"));
            hash.put("title", list.get(i).get("title")); //제목
            hash.put("subtitle",list.get(i).get("content"));//내용
            hash.put("finished",list.get(i).get("is_finished"));//true or false
            hash.put("memberId",list.get(i).get("member_Id"));//memberId
            hash.put("regDate",list.get(i).get("reg_Date"));//regDate
            hash.put("modDate",list.get(i).get("mod_Date"));//modDate
            hash.put("groupName",list.get(i).get("group_Name"));//groupName
            hash.put("glno",list.get(i).get("glno"));//lno
            hash.put("start", list.get(i).get("due_Date")); //시작일자
            hash.put("end", list.get(i).get("due_Date")); //종료일자
            if(list.get(i).get("is_finished").equals(true)){
                hash.put("color","#0000CD");
            }
            else{
                hash.put("color","#FF0000");
            }
            jsonObj = new JSONObject(hash); //중괄호 {key:value , key:value, key:value}
            jsonArr.add(jsonObj); // 대괄호 안에 넣어주기[{key:value , key:value, key:value},{key:value , key:value, key:value}]
        }
        log.info("jsonArrCheck: {}", jsonArr);

        return jsonArr;
    }
    //그룹캘린더 수정 액션
    @RequestMapping(value = "/gmodify",method = RequestMethod.POST)
    @ResponseBody
    public List<Map<String, Object>> GmodifyActionPOST(GroupTodoDTO groupTodoDTO,boolean isFinished,Model model){
        groupTodoDTO.setFinished(isFinished);
        groupTodoService.groupTodoModify(groupTodoDTO);
        List<Map<String, Object>> list = groupTodoRepository.findByGroupTodo(groupTodoDTO.getGroupName());
        JSONObject jsonObj = new JSONObject();
        JSONArray jsonArr = new JSONArray();
        HashMap<String, Object> hash = new HashMap<String, Object>();

        for(int i=0; i < list.size(); i++) {
            log.info(list.get(i).get("title"));
            log.info("++++++content=="+list.get(i).get("content"));
            hash.put("title", list.get(i).get("title")); //제목
            hash.put("subtitle",list.get(i).get("content"));//내용
            hash.put("finished",list.get(i).get("is_finished"));//true or false
            hash.put("memberId",list.get(i).get("member_Id"));//memberId
            hash.put("regDate",list.get(i).get("reg_Date"));//regDate
            hash.put("modDate",list.get(i).get("mod_Date"));//modDate
            hash.put("groupName",list.get(i).get("group_Name"));//groupName
            hash.put("glno",list.get(i).get("glno"));//lno
            hash.put("start", list.get(i).get("due_Date")); //시작일자
            hash.put("end", list.get(i).get("due_Date")); //종료일자
            if(list.get(i).get("is_finished").equals(true)){
                hash.put("color","#0000CD");
            }
            else{
                hash.put("color","#FF0000");
            }
            jsonObj = new JSONObject(hash); //중괄호 {key:value , key:value, key:value}
            jsonArr.add(jsonObj); // 대괄호 안에 넣어주기[{key:value , key:value, key:value},{key:value , key:value, key:value}]
        }
        log.info("jsonArrCheck: {}", jsonArr);

        return jsonArr;
    }
    //그룹캘린더 삭제 액션
    @RequestMapping(value = "/gremove",method = RequestMethod.POST)
    @ResponseBody
    public List<Map<String, Object>> GremoveActionPOST(GroupTodoDTO groupTodoDTO,Model model){
        groupTodoService.groupTodoDelete(groupTodoDTO.getGlno());
        List<Map<String, Object>> list = groupTodoRepository.findByGroupTodo(groupTodoDTO.getGroupName());
        JSONObject jsonObj = new JSONObject();
        JSONArray jsonArr = new JSONArray();
        HashMap<String, Object> hash = new HashMap<String, Object>();

        for(int i=0; i < list.size(); i++) {
            log.info(list.get(i).get("title"));
            log.info("++++++content=="+list.get(i).get("content"));
            hash.put("title", list.get(i).get("title")); //제목
            hash.put("subtitle",list.get(i).get("content"));//내용
            hash.put("finished",list.get(i).get("is_finished"));//true or false
            hash.put("memberId",list.get(i).get("member_Id"));//memberId
            hash.put("regDate",list.get(i).get("reg_Date"));//regDate
            hash.put("modDate",list.get(i).get("mod_Date"));//modDate
            hash.put("groupName",list.get(i).get("group_Name"));//groupName
            hash.put("glno",list.get(i).get("glno"));//lno
            hash.put("start", list.get(i).get("due_Date")); //시작일자
            hash.put("end", list.get(i).get("due_Date")); //종료일자
            if(list.get(i).get("is_finished").equals(true)){
                hash.put("color","#0000CD");
            }
            else{
                hash.put("color","#FF0000");
            }
            jsonObj = new JSONObject(hash); //중괄호 {key:value , key:value, key:value}
            jsonArr.add(jsonObj); // 대괄호 안에 넣어주기[{key:value , key:value, key:value},{key:value , key:value, key:value}]
        }
        log.info("jsonArrCheck: {}", jsonArr);

        return jsonArr;
    }
}


