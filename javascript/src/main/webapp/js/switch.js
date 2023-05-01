/**
 * switch.js
 * 03_switch.html에 포함.
 * switch-case 구문
 */


// DOM: Document Object Model
// DOMContentLoaded: HTML 문서의 모든 요소(element)들이 만들어졌을 때 발생하는 이벤트.
// 이벤트 처리 함수를 등록. -> HTML 파일에 적용할 때 다음 이벤트 처리기는 꼭 작성하기.
document.addEventListener('DOMContentLoaded', function () { // 익명 함수.
     // select#weekday element를 찾음.
     const weekday = document.getElementById('weekday');
     console.log(weekday);
     
     // div#result element를 찾음.
     const result = document.getElementById('result');
     
     // 다른 함수안에 있는 함수가 잇을 경우 다음 함수가 안보임.
     
     // button#btn element를 찾음.
     const btn = document.getElementById('btn');
     // btn에 click 이벤트 리스너를 등록.
     btn.addEventListener('click', printResult)
     
     function printResult() {
         const value = weekday.value; // select에서 선택된 값을 읽음.
         // switch-case는 '==='로 비교해서 찾아간다.
         switch (value) {
             case 'mon':
             case 'tue':
             case 'wed':
             case 'thu':
             case 'fri':
                 result.innerHTML = '학원 출석';
                 break;
             case 'sat':
             case 'sun':
                 result.innerHTML = '캠핑';
                 break;
             default:
                 result.innerHTML = '몰랑';
         }
         
         // 자바스크립트의 switch-case에서 비교는 === 연산자 비교.
         // 타입을 자동 변환하지 않고, 타입과 값이 일치하는 case의 문장을 실행.
         
         // 자바스크립
     }
    
}); 

 