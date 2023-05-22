/**
 * post-modify.js
 * /post/modify.jsp에서 사용
 * 
 * 강사님 코드도 한 번 보기.
 */

// 익명 함수.
 document.addEventListener('DOMContentLoaded', function () {
     console.log('test'); // 브라우저 console 창에서 출력.
     
     //form 요소, const는 콜백 함수 내부에서도 찾을 수가 있음
     const form = document.querySelector('#modifyForm');
     
     // input#id 요소를 찾음.
     const inputId = document.querySelector('input#id');
     
     // input#title 요소를 찾음.
     const inputTitle = document.querySelector('input#title');
     // const title = document.querySelectro('input#title').value; => id의 값을 찾음: input에 입력된 값
     
     // textarea#content 요소를 찾음.
     const textareaContent = document.querySelector('textarea#content');
     // const content = document.querySelectro('textarea#content').value;
     
     
     // 수정 완료/ 삭제 버튼을 찾음
     const btnUpdate = document.querySelector('button#btnUpdate');
     const btnDelete = document.querySelector('button#btnDelete');
     
     // 삭제 버튼에 클릭 이벤트 리스너(핸들러)를 등록.
     // 삭제 버튼을 찾아서 이벤트 리스너를 등록.
     // 콜백 함수
     btnDelete.addEventListener('click', (e) => {
        alert('삭제'); 
         
        e.preventDefault(); 
        //-> form안에 있는 버튼 또는 input[type=submit]의 기본(클릭) 동작은
        // 폼의 내용을 서버로 제출(서버로 요청을 보냄)
        // 버튼의 기본 동작이 기능하지 않도록 함.
        
        const id = inputId.value;
        const result = confirm(`NO. ${id} 정말 삭제할까요?`); 
        // ``: 문자열 탬플릿 -> 안에서 ${}는 자바 스크립트 변수 찾아가는 것.
        // jsp에서는 el.
        
        // 확인 -> true, 취소 -> false 리턴.
        console.log(`삭제 확인 결과 = ${result}`); // 브라우저 개발자 도구의 콘솔 창 로그.
        
        //alert('삭제 버튼 클릭됨.'); // 브라우저가 띄어주는 팝업 창.
        
        // 사용자가 confirm 창에서 '확인'을 클릭했을 때.
        // jsp 창에서 버튼들이 form 외부에 존재를 해도 다음 if문을 설정할 수가 있다. 강사님 modify.jsp 참고하기.
        // -> 다음 아래는 form 내부에 값들을 처리하는 버튼을 작성하는 것이기에 버튼이 외부에 있어도 eventListener가 버튼의 작동을 정의하면 됨
        if (result) {
            form.action = 'delete'; // form 제출(요청) 주소(요청 주소를 설정함). => '/delete'를  처리할 수 있는 controller를 만들어야 함.
            // 현재 요청주소가 modify임 -> 삭제 버튼 클릭시 post까지 유지가 됨. 그 밑에서 주소만 delete라고 바꾸기를 원함.
            // ./delete == delete
            form.method = 'post'; // 요청 방식.
            form.submit(); // 폼 제출(요청 보내기) -> 요청을 서버로 보냄.
        }
     
     });
     
      // 수정 버튼에 클릭 이벤트 핸들러(리스너)를 등록.
    btnUpdate.addEventListener('click', (e) => {
        e.preventDefault();
        
        const id = inputId.value;
        const title =inputTitle.value;
        const contet = textareaContent.value;
        
        // 제목과 내용이 입력되어 있는 지 확인
        if(title === '' || content === '') {
            alert('제목, 내용을 입력하세요');
            return; // 함수 종료.
        }
        const result = confirm(`NO. ${id} 포스트를 업데이트 할까요?`);
        
        if (result) {
            form.action = './update'; // 폼 요청 주소.
            form.method = 'post'; // 폼 요청 방식
            form.submit();
        }
    });


     
 })