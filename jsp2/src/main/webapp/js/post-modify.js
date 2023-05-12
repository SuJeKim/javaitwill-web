/**
 * post-modify.js
 * /post/modify.jsp에서 사용됨.
 * 
 * function () {} == () => {}
 */

 document.addEventListener('DOMContentLoaded', function () {
     // form 요소를 찾음
     const form = document.querySelector('#postModifyForm');
     
     // input#id 요소를 찾음.
     const inputId = document.querySelector('input#id');
     
     // input#title 요소를 찾음.
     const inputTitle = document.querySelector('input#title');
     
     // textarea#content 요소를 찾음.
     const textareaContent = document.querySelector('textarea#content');
     
     // 수정 완료/ 삭제 버튼을 찾음
     const btnUpdate = document.querySelector('button#btnUpdate');
     const btnDelete = document.querySelector('button#btnDelete');
     
     // 삭제 버튼에 클릭 이벤트 리스너(핸들러)를 등록.
     btnDelete.addEventListener('click', (e) => {
        e.preventDefault(); 
        //-> form안에 있는 버튼 또는 input[type=submit]의 기본(클릭) 동작은
        // 폼의 내용을 서버로 제출(서버로 요청을 보냄)
        // 버튼의 기본 동작이 기능하지 않도록 함.
        
        alert('삭제 버튼 클릭됨.'); // 브라우저가 띄어주는 팝업 창.
     });
     
 }); 
 