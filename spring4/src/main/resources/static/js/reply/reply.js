/**
 * 댓글 영역 보이기/ 숨기기 토글
 * 댓글 검색, 등록, 수정, 삭제
 */
document.addEventListener('DOMContentLoaded', () => {
    // 로그인한 사용자 이름 -> 댓글 등록, 수정, 삭제할 때 사용하기 위해서.
    const authName = document.querySelector('div#authName').innerText;
    
    
    // 부트스트랩 collapse 객체를 생성. 초기 상태는 화면에 보이지 않는 상태.
    // {toggle: false}옵션: 자바스크립트 객체 {key:value}
    const bsCollapse = new bootstrap.Collapse('div#replyToggleDiv', { toggle: false });

    // 토글 버튼을 찾고, 이벤트 리스너를 등록.
    const btnToggleReply = document.querySelector('#btnToggleReply');
    btnToggleReply.addEventListener('click', (e) => {
        bsCollapse.toggle();

        // 버튼 이름 바꾸기.
        // e.target: 버튼이라는 요소 출력
        console.log(e.target.innerText);
        if (e.target.innerText === '보이기') {
            e.target.innerText = '숨기기';

            // 댓글 목록 불러오기:
            getRepliesWithPostId();
        } else {
            e.target.innerText = '보이기';
        }
    });

    // 댓글 삭제 버튼들의 클릭을 처리하는 이벤트 리스너 콜백:
    const deleteReply = (e) => {

        // 이벤트가 발생한 target 찾기.
        //-> console.log(e.target); 

        const result = confirm("정말 삭제할까요?");
        if (!result) {
            return;
        }

        // data-id에 있는 값 -> 삭제할 댓글 아이디
        const id = e.target.getAttribute('data-id');

        // Ajax DELETE 방식 요청 주소
        const reqUrl = `/api/reply/${id}`;

        axios
            .delete(reqUrl) // Ajax DELETE 요청을 보냄.
            .then((response) => {
                console.log(response);

                // 댓글 목록 새로 고침
                // const postId = document.querySelector('input#id').value;

                // 함수 선언시 argument를 선언하지 않았기에 param 작성시 해당 parma은 무시됨.
                getRepliesWithPostId();

            }) // 성공 응답일 때 실행할 콜백 등록.
            .catch((error) => console.log(error)); // 실패 응답일 때 실행할 콜백 등록.

    };
    // 댓글 수정 버튼들의 클릭을 처리하는 이벤트 리스너 콜백:
    const updateReply = (e) => {
        // console.log(e.target);

        // 수정할 댓글 아이디
        const replyId = e.target.getAttribute('data-id');

        // 댓글 입력 textArea 아이디
        const textAreaId = `textarea#replyText_${replyId}`; 
        // console.log(document.querySelector(textAreaId).value);

        // 수정할 댓글 내용.
        const replyText = document.querySelector(textAreaId).value

        const postId = document.querySelector('input#id').value;
        if(replyText === ''){ // 댓글 내용이 비어 있으면
            alert('수정할 댓글 내용을 입력하세요.');
            return;
        }

        // 요청 주소
        //-> 'http://localhost:8090/api/reply/10' 주소 줄일 뿐임.
        //-> queryString, pathVariable은 다름.
        //-> queryString에서 requestParameter의 이름은 중요함. -> 서버에서도 변수선언시 이름 동일하게 해야 함.
        //-> pathVariable에는 replyTd는 고정값이 아니라 변할 수 있는 변수. -> 변수의 이름 자체가 중요하지 않음
        // js: replyTd, controller: id는 함수와 비슷함.
        //  (예) ==> 함수를 호출하는 곳에서는 replyTd값을 넘긴거고 받는 곳에서는 id라고 하겠다.
        const reqUrl = `/api/reply/${replyId}`;
        
        // @requestBody로 들어감.
        // {replyText: replyText}, 요청 데이터(수정할 댓글 내용.)
        // data가 response.data이고 생긴 모양이 객체이기에 하나의 데이터라도 dto 선언 필
        const data = { replyText };

        // Ajax PUT 요청
        axios
            .put(reqUrl, data) // Put 방식의 Ajax 요청을 보냄.
            .then((response) => { // 성공 응답일 떄 동작할 콜백을 등록.
                console.log(response)

                // 댓글 목록 새로고침
                getRepliesWithPostId(postId);

            }) 
            .catch((error) => {console.log(error)}); // 에러 응답일 떄 동작할 콜백을 등록



    }

    // 댓글 작성
    const makeReplyElements = (data) => {
        // 댓글 개수를 배열(data)의 원소 개수로 업데이트.
        document.querySelector('span#replyCount').innerText = data.length;

        // 댓글 목록을 삽입할 div 요소를 찾음.
        const replies = document.querySelector('div#replies');

        // div 안에 작성된 기존 내용은 삭제.
        replies.innerHTML = '';

        // div안에 삽입할 HTML 코드 초기화.
        let htmlStr = '';

        for (let reply of data) {
            htmlStr += `
            <div class="card my-2">
                <div>
                    <span class="d-none">${reply.id}</span>
                    <span class="fw-bold">${reply.writer}</span>
                </div>
            `;
            
            // 로그인 한 사용자와 댓글 작성자가 같을 떄만 삭제, 수정 버튼을 보여줌.
            if (authName === reply.writer) {
                htmlStr += `
                <textarea id="replyText_${reply.id}">${reply.replyText}</textarea>
                <div>
                    <button class="btnDelete btn btn-outline-danger" data-id="${reply.id}">삭제</button>
                    <button class="btnMidify btn btn-outline-primary" data-id="${reply.id}">수정</button>
                </div>   
                `; 
             } else {
               htmlStr += `
               <textarea id="replyText_${reply.id}" readonly>${reply.replyText}</textarea>
               `;  
             }  
               
            htmlStr += '</div>';    
            
        }

        //작성된 html 문자열을 div 요소의 innerHtml로 설정.
        // 이 코드 실행 후에 실제로 버튼이 만들어짐
        replies.innerHTML = htmlStr;

        // 모든 댓글 삭제 버튼들에게 이벤트 리스너를 등록.
        // -> 버튼'들'이기에 다수의 버튼에 id값이 필요함. -> data-id는 만든 것. 즉, 버튼 데이터의 속성값을 설정함.
        // querySelector: 같은 element에서 가장 처음에 있는 것만 찾아줌
        // querySelectorAll: 같은 element 다 찾음.
        const btnDeletes = document.querySelectorAll('button.btnDelete');
        for (let btn of btnDeletes) {
            btn.addEventListener('click', deleteReply);
        }

        // 모든 댓글 수정 버튼들에게 이벤트 리스너를 등록.
        const btnModifies = document.querySelectorAll('button.btnMidify');
        for (let btn of btnModifies) {
            btn.addEventListener('click', updateReply);
        }

    };


    // 비동기적 처리
    // 포스트 번호에 달려있는 댓글 목록을 (Ajax 요청으로) 가져오는 함수:
    const getRepliesWithPostId = async () => {

        // 포스트 아이디(번호)
        const postId = document.querySelector('input#id').value;

        // Ajax 요청 주소
        // -> pathVariable 사용시 `` 사용.
        const reqUrl = `/api/reply/all/${postId}`;

        // Ajax 요청을 보내고 응답을 기다림.

        /*
        Ajax 요청:
        get -> 목록을 가져오는 것(select)
        Post -> 댓글 등록(insert)
        put -> 댓글 수정(update)
        delete -> 댓글 삭제(delete)
        */

        // 함수 바디에서 await가 존재할 경우 async수식어 사용 필
        // await가 없을 경우에는 async수식어 안 써도 됨.

        try {
            const response = await axios.get(reqUrl);
            console.log(response);
            makeReplyElements(response.data);

        } catch (error) {
            console.log(error);
        }
    };

    // 댓글 등록 버튼을 찾고, 이벤트 리스너를 등록
    const btnReplyCreate = document.querySelector('button#btnReplyCreate');
    btnReplyCreate.addEventListener('click', () => {
        // 포스트 아이디 찾음.
        const postId = document.querySelector('input#id').value;

        // 댓글 내용을 찾음.
        const replyText = document.querySelector('textarea#replyText').value;

        // 댓글 작성자는 admin. 나중에 로그인한 사용자 아이디로 변경.
        // const writer = 'admin';
        const writer = authName;

        // alert(`${postId}, ${replyText}, ${writer}`);

        // 댓글 내용이 비어 있으면 경고창을 보여주고 종료.
        if (replyText === '') {
            alert("댓글 내용을 입력하세요.");
            return;
        }

        // Ajax 요청에서 보낼 데이터.
        /*
        js에서 {}:
        1. 함수 바디
        2. 선언 = {}
           => object
           => (예) {x: 1, y: 'abc'}
           => 2015년 이후 문법:
           if(지역 변수 이름 === object의 key name) {
               const data = {postId, replyText, writer}; 
           }
        */
        const data = { postId, replyText, writer };

        // Ajax 요청을 보낼 URL.
        const reqUrl = '/api/reply';

        // Ajax POST 방식 요청을 보냄.
        // -> 89번째 줄과 해당 줄은 과정 동일.
        axios
            .post(reqUrl, data) // Ajax POST 방식 요청을 보냄.
            .then((response) => {
                console.log(response)

                // 댓글 목록 새로고침
                getRepliesWithPostId(postId);

                // 댓글 입력한 내용을 지움.
                document.querySelector('textarea#replyText').value = '';

            }) // 성공 응답(response)일 때 실행할 콜백 등록
            .catch((error) => {
                console.log(error)
            }); // 실패(error)일 때 실행할 콜백 등록.

    });

}); // 끝