/**
 * event.js
 * 자바스크립트 함수는 파라미터 개수를 검사하지 않음.
 * -> 함수를 호출하는 브라우저에서는 event라는 객체를 항상 계속해서 만들어내고 전달하고 있음
 *    사용자가 따로 argument를 쓰지 않아도.(타입에 상관없이)
 */

 document.addEventListener('DOMContentLoaded',function () {
     const itemInput = document.querySelector(' input#itemInput');
     const btnInput = document.querySelector('button#btnInput');
     const itemList = document.querySelector('ul#itemList');
     const text = document.querySelector('input#itemInput2');
     const list = document.querySelector('ul#list');
     
     
     btnInput.addEventListener('click', function () {
         console.log(arguments);
         // input에 입력된 내용을 읽음.
         const value = itemInput.value;
         //console.log(value);
         
         // input에 입력된 값으로 li 요소를 만듦.
         const item = `<li>${value}</li>`
         
         // li 요소를 ul에 추가.
         itemList.innerHTML += item;
         
         // input의 값을 지움.
         itemInput.value = '';
         
         // input에 포커스를 줌.
         itemInput.focus();
     });
     
     // 이벤트 처리 2
     // 이벤트 이름을 찾기 힘들면 html에서 on으로 검색해보기.
     text.addEventListener('keydown', function(event) {
         //console.log(event); -> event: keyboardEvent 객체
         // 모든 이벤트 핸들러 함수(콜백)은 이벤트 객체를 argument로 전달받음.
         if(event.key === 'Enter'){ // 엔터 키가 눌렸을 떄
             const value =  text.value;
             const item = `<li>${value}</li>`;
             list.innerHTML += item;
             text.value = '';
             text.focus();
             
         }
     });
     
     // 이벤트 처리 3
     const userName = document.querySelector('input#username');
     const number = document.querySelector('input#age');
     const result = document.querySelector('div#result');
     
     // keyboard event > 실시간 갱신: 
     // keyup(-> 바로 갱신), keydown(-> 다음 글자가 존재할 대 갱신)에 따라 value가 달라짐.
     //                >  change -> 입력 끝내고 넘어갔을 떄 다음으로 넘어감. 
     userName.addEventListener('change' , function(event) {
         updateNameAndAge();
     });
    
     number.addEventListener('change', function(e) {
        updateNameAndAge();
    });
 
     function updateNameAndAge() {
         const name = userName.value; 
         const age2 = number.value;
         const text = '<b>이름:</b> ${name}, <b>나이:</b> ${age2}';
         result.innerHTML = text;
     }
  
 });