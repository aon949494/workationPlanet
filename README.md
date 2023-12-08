# P02

231201_업로드

수정한 내용 요약
- mainy에 '그룹 가입' 버튼 추가
- '그룹 가입' 버튼을 클릭하면 groupName, groupPw를 입력하는 페이지로 넘어가고, memberId는 로그인된 사용자의 id가 자동 입력됨
- 'Submit' 버튼을 클릭하면 GroupService, GroupMemberService에서 1) 존재하는 groupName인지, 2) groupName과 groupPw가 일치하는지, 3) 가입하고자 하는 member가 이미 해당 그룹에 가입되어 있는지를 확인한 다음 가입(db(GroupMember)에 저장), 오류가 있을 시 화면에 오류 메세지 생성
- 추가로 해당 그룹 member만 채팅방리스트를 확인할 수 있도록 수정 중

수정한 파일
- Controller (MGController, MainController)
- Entity (GroupMember)
- DTO (GroupMemberDTO)
- Repository (GroupMemberRepository)
- Service (GroupService, GroupServiceImpl, GroupMemberService, GroupMemberServiceImpl)
- templates/MemberGroup (g_join, mainy, g_main)
- templates/Chat (chatRoom)

- (GroupMember와 Group 엔티티를 ManyToOne으로 연결하려고 했는데 잘 되지않아 연결하지 않고 코드 작성함..)
  

===================================================================================================

231128_업로드

수정한 내용 요약
- chatLayout에 양식에 맞춰 채팅 구현(WebSocket 사용, DB 연결 x)
- /chat/chatList로 접속하여 '방 만들기' 버튼을 누르고 chatRoom 이름을 입력하면 생성된 채팅방 리스트가 왼쪽 영역에 나타남
- 왼쪽 영역의 채팅방을 클릭하면 채팅창이 생성됨

수정한 파일
- Chat (ChatController)
- templates/chat (chatList, chatRoom)
- templates/layout (chatLayout)
- static/css (chatLayout)

+ 사용하지 않는 파일 및 코드 삭제, security 부분(kim) 내용 추가

추가할 내용
- 채팅리스트 및 채팅창의 css, script 구현
- security의 인증을 받은 사용자 이름을 메세지 sender로 설정
