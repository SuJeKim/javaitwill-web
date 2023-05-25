/**
 * reply.js
 * 댓글 등록, 목록 검색, 수정, 삭제
 * /post/detail.jsp에 포함.
 * 
 * 함수 만드는 법
 * (1) function test() {}; 
 * (2) 변수 선언 = 익명 함수.
 */

 document.addEventListener('DOMContentLoaded', () => {
     
  // 댓글 개수 표시 영역
  const replyCountSpan = document.querySelector('span#replyCount');
  
  // 댓글 목록 표시 영역(div)
  const replies = document.querySelector('div#replies');
  
  // 댓글 삭제 버튼의 이벤트 리스너 (콜백) 함수
  const deleteReply = (e) => {
     // alert(JSON.stringify(e));
     //  console.log(e); // 이벤트 출력
     
     console.log(e.target); //e.target: 이벤트가 발생한 타겟. 여기서는 삭제 버튼
     
     if(!confirm('정말 삭제할까요?')){ // no
         return;
     }
     
     // 삭제할 댓글 아이디:
     const id = e.target.getAttribute('data-id');
     
     // 삭제 요청 URL
     const reqUrl = `/spring2/api/reply/${id}`;
     
     // 삭제 요청을 Ajax 방식으로 보냄.
     axios.delete(reqUrl)
          .then((response) => {
              console.log(response);
              alert('댓글 삭제 성공');
              
              // 댓글 목록 갱신
              getRepliesWithPostId();
          })
          .catch((error) => {
              console.log(error);
          });
     
  };
  
  // 댓글 수정 모달 객체 생성
  // + backdrop: true ==>  modal 외부 클릭시 사라짐.
  const modal = new bootstrap.Modal('div#replyUpdateModal', {backdrop: true});
  
  // 모달 element 찾기
  const modalInput = document.querySelector('input#modalRelyId'); 
  const modalTextarea = document.querySelector('textarea#modalReplyText');
  const modalBtnUpdate = document.querySelector('button#modalBtnUpdate');
  
  // 댓글 수정 버튼의 이벤트 리스너 (콜백) 함수 - 댓글 수정 모달을 보여주는 함수
  const showUpdateModal = (e) => {
      //alert(JSON.stringify(e));
      //console.log(e);
      //console.log(e.target); -> e.target: 이벤트가 발생한 타겟, 여기서는 수정 버튼
      const id = e.target.getAttribute('data-id'); // data-id: 수정 버튼에 있는 요소 (102 줄 참고)
      const reqUrl = `/spring2/api/reply/${id}`;
      axios.get(reqUrl) // 서버로 GET 방식의 Ajax 요청을 보냄: reqUrl로 비동기 요청을 보내서 실행할 함수를 만듦.
            .then((response) => { // 성공 응답이 왔을 때 실행할 콜백을 등록. +  response객체에 있는 data => replyReadDto
            
                // response에 포함된 data  객체에서 id, replyText 갑을 찾음.
                // data는 객체에 있는 id 속성 값(key 값)을 {id, replyText}의 전자에 replyText의 속성 값을 후자로.
                // {id, replyText}: replyReadDto의 맴버변수와 동일하게 작성하기.
                // response.data가 배열일 경우 
                const {id, replyText} = response.data; 
                
                // id와 replyText를 모달의 input과 textarea에 씀.
                modalInput.value = id;
                modalTextarea.value = replyText;
                
                // 모달을 보여줌.
                modal.show();
            })
            .catch((error) => console.log(error));  // 실패 응답이 왔을 때 실행할 콜백 등록.
  
  };
  
  const updateReply = (e) => {
      // 수정할 댓글 아이디
      const id = modalInput.value;
      
      // 수정할 댓글 내용
      const replyText = modalTextarea.value;
      
      // PUT 방식의 Ajax 요청을 보냄.
      const reqUrl = `/spring2/api/reply/${id}`;
      const data = {replyText}; 
      // 원래 JS의 객체 선언시 {key : value}, {replyText : replyText}: key === value의 이름이 동일한 경우에 간단하게 사용 가능.
      // -> {replyText : replyText}: 1개의 맴버 변수를 갖고 있는 객체
     
      // Ajax 요청에 대한 성공/실패 콜백 등록.
      axios.put(reqUrl,data)
            .then((response) => {
                alert(`댓글 업데이트 성공(${response.data})`);
                getRepliesWithPostId(); // 댓글 목록 업데이트
            })
            .catch((error) => {})
            .finally(() => {modal.hide();});
  };
  
  
  // 모달에서 [수정 내용 저장] 버튼 이벤트 리스너 등록.
  // ++ updateReply == (e) => {} 함수.
  modalBtnUpdate.addEventListener('click', updateReply);
  
  
  // 댓글 목록 HTML을 작성하고 replies 영역에 추가하는 함수.
  // argument data: Ajax 요청의 응답으로 전달받은 데이터.
  // -> data: 댓글들의 배열.(response 객체에 존재한는 data 배열)
  // -> data === response.data
  const makeReplyElements = (data) => {
      
      // 댓글 개수 업데이트
      replyCountSpan.innerHTML = data.length; // 배열 길이(원소 개수)
      
      replies.innerHTML = ''; // <div>의 컨텐트를 지움.
      
      let htmlstr = '';  // const와는 달리 변경 가능한 타입
      
      /*
      js의 for문 종류
      for (let i = 0; i < data.length; i++) {}
      for (let x in data) {} ==> 인덱스 iteration
      */
      
      for (let reply of data) { // 원소들 iteration
          console.log(reply);
          
          // Timestamp 타입의 값을 날짜/시간/ 타입 문자열로 변환:
          // modifiedTime: 브라우저의 콘솔창에서 response.data의 변수와 동일한 이름으로 선정
          const modified = new Date(reply.modifiedTime).toLocaleString();
          
          
          // 댓글 1개를 표시할 HTML 코드:
          // eventHandler: class="btnDelete, btnModify 
          htmlstr += `
          <div class="my-2 card">
             <div>
                <span class="d-none">${reply.id}</span>
                <span class="fw-bold">${reply.writer}</span>
                <span class="text-secondary">${modified}</span>
             </div>
             <div>
                ${reply.replyText}
             </div>
             <div>
                 <button class="btnDelete btn btn-outline-danger" data-id="${reply.id}">
                    삭제
                 </button> 
                 <button class="btnModify btn btn-outline-success" data-id="${reply.id}">
                    수정
                 </button>
             </div>
          </div>
          `;
          
      }
      
      // 작성된 HTML 코드를 replies <div> 영역 안에 포함. -> 이떄 버튼 생성
      replies.innerHTML = htmlstr;
      
      // -> 각 버튼의 eventhandler를 등록하는 곳. + 버튼이 댓글 개수만큼 존재하니 반복문으로 하기
      // 모든 삭제 버튼들을 찾아서 클릭 이벤트 리스너를 등록:
      // deleteButton가 여러개가 존재하기에 querySelectorAll을 사용 + deleteButtons: 요소들의 배열이 됨.
      // -> 브라우저 요소 참고(개발자 도구)
      const deleteButtons = document.querySelectorAll('button.btnDelete'); 
      for(let btn of deleteButtons) {
          btn.addEventListener('click', deleteReply);
      } // 에러 발생시 해당 코드에서 멈춤.
      
      // 모든 수정 버튼들을 찾아서 클릭 이벤트 리스너를 등록.
      const modifyButtons = document.querySelectorAll('button.btnModify');  
      for (let btn of modifyButtons) {
          btn.addEventListener('click', showUpdateModal);
      }  

  };
  
  
  
   
   // async에는 하나 이상의 await가 존재.
   const getRepliesWithPostId = async () => { 
   
     // 댓글 목록을 요청하기 위한 포스트 아이디(번호)
     const postId = document.querySelector('input#id').value;
     
     // 댓글 전체 목록을 요청할 URL
     const reqUrl = `/spring2/api/reply/all/${ postId }`; // /contextRoot/api/
     
     // Ajax 요청을 보내고 응답을 기다림.
     try {
         const response = await axios.get(reqUrl); // await: 비동기 작업은 get이 실행함. 응답을 받을 떄까지(thred)가 끝날때까지 기다린 후 응답을 받으면 다음 코드 실행.
         console.log(response);
         // 댓글 개수 업데이트 & 댓글 목록 보여주기
         makeReplyElements(response.data);
         
     } catch (error) {
         console.log(error);
     }
     
     
   };
   
   
    // bootStrap Collapse 객체를 생성 - 초기 상태는 화면에서 안보이는 상태
    const bsCollapse = new bootstrap.Collapse('div#replyToggleDiv', {toggle: false}); // {}: Object
    
    // 버튼 아이콘 이미지
    const toggleBtnIcon = document.querySelector('img#toggleBtnIcon');
    
    // 댓글 등록/목록 보이기/숨기기 토글 버튼에 이벤트 리스너를 등록
    const btnToggleReply = document.querySelector('button#btnToggleReply');  
    
    btnToggleReply.addEventListener('click', () => {
        bsCollapse.toggle();
       // const toggle = btnToggleReply.getAttribute('data-toggle'); // btnToggleReply가 가지고 있는 data-toggle 속성의 값을 리턴
        
        if (toggleBtnIcon.alt === 'toggle-off') { //toggle === 'toggle-off'
             toggleBtnIcon.src = '../static/assets/icons/toggle2-on.svg';
             toggleBtnIcon.alt = 'toggle-on';
            
            // btnToggleReply.innerHTML = '숨기기';  // 시작 태그와 end tag 사이에 있는 값을 변경.
            // btnToggleReply.setAttribute('data-toggle', 'toggle-on');
            
            // 댓글 전체 목록을 서버에 요청하고, 응답이 오면 화면 갱신.
            getRepliesWithPostId();
            
            
            
        } else {
            toggleBtnIcon.src = '/spring2/static/assets/icons/toggle2-off.svg';
            toggleBtnIcon.alt = 'toggle-off';
            replies.innerHTML = '';
            
           // btnToggleReply.innerHTML = '보이기';
           // btnToggleReply.setAttribute('data-toggle', 'toggle-off');
        }
    });
    
    // 댓글 등록 버튼
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

                // 댓글 목록을 새로 고침.
                getRepliesWithPostId();
                                
            }) // 성공 응답이 왔을 떄 실행할 콜백 함수 등록.
            
            .catch((error) => { // 요청을 보냈는데 실패 응답 등록 + (error) => {}: 실패 콜백 함수
                 console.log(error);
                 
            }); // error 응답이 왔을 떄 실행할 콜백 함수 등록.
            
  //        .finally(); // 무조건 등록 함.   
            
       
    }; 
    btnAddReplys.addEventListener('click', createReply);
    
    
 });