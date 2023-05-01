/**
 * function.js
 * 07_function.html에 포함.
 */

// console.log('hello'); // 브라우저

/**
 * JavaScript에서 함수를 선언(정의)하는 방법:
 * function 함수이름([파라미터, ....]) {
 *      실행 코드;
 *      [return [리턴값];]
 * }
 * -> 리턴 타입을 사용하지 않음. 자바스크립트의 경우 코드를 만났을 떄 실행이 되기에 그 전에 어떤 타입인지를 모르니 안 써도 됨.
 * 
 * (주의)
 * - 함수의 리턴 타입을 선언하지 않음.
 * - 파라미터를 선언할 때 var, const, let 키워드를 사용하지 않음.
 */

// 함수 선언:
 function add(x, y){ // x, y는 지역변수임.
     return x + y;
 }
 
 // 함수 호출: 
 let result = add(1,2);
 console.log('result =', result);
 
 
 // argument: 함수를 호출할 때 함수에 전달하는 값.
 // parameter: 전달받은 argument를 저장하게 위한 지역 변수. 함수 선언부에서 선언.
 
 
 // 자바스크립트 함수는 파라미터의 타입을 검사하지 않음.
 result = add('안녕하세요', 'Hello');
 console.log(result);
 
 // 자바스크립트 함수는 파라미터 개수를 검사하지 않음.
 result = add(10, 20, 30);
  // 함수 선언의 parameter 개수보다 더 많은 argument를 전달.
  // 30의 경우 함수 내부에서는 arguments에 값을 저장한 상태이다. 필요한 경우 arguments의 이름으로 사용할 수가 있다. for-of구문으로 하나씩 출력 가능함.
 console.log('result = ', result);
 
 result = add(1); //함수 선언의 parameter 개수보다 적은 argument를 전달.
 // 전달되지 않은 파라미터의 값은 undefined가 됨. -> 연산을 수행할 수가 없음.
 console.log('result = ',result); //-> NaN(Not a Number)
 
 // 자바스크립트의 모든 함수는 arguments 이름의 프로피티를 가지고 있음.
 // 함수 호출에서 전달한 모든 값들을 저장하는 (배열과 비슷한) 객체.
 function test() {
     console.log(arguments);
 }
 
 test(1);
 test(1, 'hello');
 
 /**
  * 0:1
  * 1: 'hello'
  * => 인덱스 i번에 해당 원소,
  * 출력 값이 배열의 모습.
  */