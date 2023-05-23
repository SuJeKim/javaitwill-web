/**
 * reply.js
 * 댓글 등록, 목록 검색, 수정, 삭제
 * /post/detail.jsp에 포함.
 */

 document.addEventListener('DOMContentLoaded', () => {
    
    // bootStrap Collapse 객체를 생성 - 초기 상태는 화면에서 안보이는 상태
    const bsCollapse = new bootstrap.Collapse('div#replyToggleDiv', {toggle: false}); // {}: Object
    
    // 댓글 등록/목록 보이기/숨기기 토글 버튼에 이벤트 리스너를 등록
    const btnToggleReply = document.querySelector('button#btnToggleReply');  
    
    btnToggleReply.addEventListener('click', () => {
        bsCollapse.toggle();
        const toggle = btnToggleReply.getAttribute('data-toggle'); // btnToggleReply가 가지고 있는 data-toggle 속성의 값을 리턴
        
        if (toggle === 'toggle-off') {
            btnToggleReply.innerHTML = '숨기기';  // 시작 태그와 end tag 사이에 있는 값을 변경.
            btnToggleReply.setAttribute('data-toggle', 'toggle-on');
        } else {
            btnToggleReply.innerHTML = '보이기';
            btnToggleReply.setAttribute('data-toggle', 'toggle-off');
        }
    });
    
    const btnAddReplys = document.querySelector('button#btnAddReplys');
    
    const createReply = (e) => { // 콜백 함수
        // anxios 라이브러리를 사용해서 Ajax 요청을 보냄
        
       //해당 id의 값을 찾음
       const postId = document.querySelector('input#id').value;
       const replyText = document.querySelector('textarea#replyText').value;
       const writer = document.querySelector('input#writer').value;
       
       if(replyText === '') {
           alert('댓글 내용을 입력하세요.')
           return;
       }
        
        
       // 객체 생성 (property : property value)
       // 2015년 이후 문법
       const data = {
           postId,
           replyText,
           writer, 
       }; // js에서 object 생성방법. data: 3개의 변수를 갖는 js object <- ReplyCreateDto 자바클래스와 동일하게 선언
       
       /*
        (property : property value)
       과거 객체 생성 문법
       postId: postId,
       replyText: replyText,
       writer: writer,  // 마지막 ,는 없어도 있어도 됨.
       */
       
       // 브라우저가 리턴을 보내는 것.
       axios.post('/spring2/api/reply', data) // axios라이브러리를 통해 post 방식의 Ajax 요청보냄. (요청을 보낼 주소, 보낼 데이터)
            .then((response) => { // 실행할 콜백 함수 등록 + (response) => {}: 성공 콜백 함수
                //console.log(response);
                alert(`댓글 등록 성공 (${response.data})`); // 서버에서 보내 준 숫자, ReplyController에서 return ResponseEntity.ok(1); 에서 data:1을 나타냄. 그래서 alert 창에 1이 출력됨.
                // ``: 문자열 탬플릿. 문자열 사이에 변수 사용. ${}이용.
                
                
                // 댓글 입력 창의 내용을 지움
                document.querySelector('textarea#replyText').value = ''; // textarea와 input은 문자열을 읽을 때, 동일하게 값을 읽거나 지울 수 있음.

                // TODO:  댓글 목록을 새로 고침.
                                
            }) // 성공 응답이 왔을 떄 실행할 콜백 함수 등록.
            
            .catch((error) => { // 요청을 보냈는데 실패 응답 등록 + (error) => {}: 실패 콜백 함수
                 console.log(error);
                 
            }); // error 응답이 왔을 떄 실행할 콜백 함수 등록.
            
  //        .finally(); // 무조건 등록 함.   
            
       
    }; 
    btnAddReplys.addEventListener('click', createReply);
    
    
 });