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
            btnToggleReply.innerHTML = '숨기기';
            btnToggleReply.setAttribute('data-toggle', 'toggle-on');
        } else {
            btnToggleReply.innerHTML = '보이기';
            btnToggleReply.setAttribute('data-toggle', 'toggle-off');
        }
    });
    
    
    
 });