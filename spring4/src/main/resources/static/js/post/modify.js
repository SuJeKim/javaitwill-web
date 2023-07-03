/**
 * modify.html에서 사용
 * 포스트 업데이트 & 삭제
 */

document.addEventListener('DOMContentLoaded', () => {

    // form을 찾음.
    const form = document.querySelector('form#postModifyForm');

    // id를 찾음.
    const id = document.querySelector('#id').value;


    // 수정 버튼을 찾음.
    const btnUpdate = document.querySelector('button#btnUpdate');
    btnUpdate.addEventListener('click', (e) => {

        // 만약 이벤트핸들러(콜백 함수) 외부에 있을 경우에는 html 문서가 완성된 상태에서 값이 들어감. 즉, 원래 있던 값, 미리 읽어둔 값이 존재.
        //-> 변경 내용을 처리할 수 없음.
        // 내부에서 변수 선언을 한 경우에는 버튼이 클릭된 당시에 값을 검사 및 읽음.
        
        // 제목을 찾음.
        const title = document.querySelector('#title').value;

        // 내용을 찾음.
        const content = document.querySelector('#content').value;
        
        if (title === '' || content === '') {
            alert('제목, 내용을 입력하세요');
            return; // 함수 종료.
        }

        const result = confirm(`${id}를 수정할까요?`)

        if (!result) {
            return;
        }
        
        form.action = 'update';
        form.method = 'post';
        form.submit();

    });

    // 삭제 버튼을 찾음.
    const btnDelete = document.querySelector('button#btnDelete');
    btnDelete.addEventListener('click', (e) => {

        const result = confirm(`정말 ${id}를 삭제할까요?`);

        console.log(`결과 = ${result}`)
        console.log(`id 값 = ${id}`)

        if (!result) {
            return;
        }

        // 절대 경로: '/post/delete'
        // 맨 처음 '/' 의미:
        // -> 클라이언트 포트번호 다음 주소
        // -> 서버에서는 context-root를 의미.
        form.action = '/post/delete'; // submit 요청 주소, 그전 주소: 'delete'도 가능함.
        form.method = 'post' // submit 요청 방식.
        form.submit(); // 폼 제출(submit), 요청 보내기.

    });


});