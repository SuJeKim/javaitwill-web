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
 
 // 자바스크립트의 모든 함수는 arguments 이름의 프로피티(property)를 가지고 있음.
 // 함수 호출에서 전달한 모든 값들을 저장하는 (배열과 비슷한) 객체.
 function test() {
     console.log(arguments);
     for(let x of arguments){
         console.log(x);
     }
     
 }
 
 test(1);
 test(1, 'hello');
 
 /**
  * 0:1
  * 1: 'hello'
  * => 인덱스 i번에 해당 원소,
  * 출력 값이 배열의 모습.
  */
 
 /**
  * JavaScript 함수의 특징: 함수는 객체(object)!
  * 1. 함수는 프로퍼티(property - 자바의 필드)를 가질 수 있음. (예) arguments
  * 2. 함수는 변수에 저장할 수 있음.
  * 3. 함수의 argument로 다른 함수를 전달할 수 있음.
  * 4. 함수 내부에서 다른 함수를 선언(정의)할 수 있음.
  * 5. 함수는 다른 함수를 리턴할 수 있음.
  */
 
 const plus = add; // 함수 자체를 변수에 저장. +  const plus = add(); > 함수를 호출(실행): 리턴값을 저장함.
 result = plus(100, 200); // 변수 plus는 함수.
 console.log('result = ', result);
 
 // 익명 함수: 이름이 없는 함수. -> 변수에 저장하지 않고 출력하는 방법 만든 위치에서 바로 함수 호출만 가능함.-> 재활용화와는 느낌.
 const minus = function (x, y) {
     return x - y;
 };
 
 console.log('minus =', minus(1, 2));
 
 // 2. 익명 함수를 선언과 동시에 호출 + 함수의 호출 결과인 리턴 값 저장함.
 result = (function (x, y) {
     return x / y;
 }) (1, 2);
 console.log('result = ', result);
 
 
 // 3. 함수를 argument로 갖는 함수를 정의:
 function calculate(x, y, operator) {
     return operator(x, y);
 }
 
 result = calculate(1, 2, add); // operator라는 변수에 add를 저장함. -> const operator = add;
 console.log('result =', result);
 
 
 result = calculate(1, 2, function(x, y) {
     return x - y;
 });
 console.log('result =', result);
 
 function increase(n) {
    // return function (x) { return x + n; }; <- 아래의 코드를 한 줄로.
    
    // 함수 안에서 함수를 정의 - 내부 함수.
    function addN(x) {
        return x + n;
    }
    
    // 함수를 리턴.
    return addN;
 }
 
 const IncreaseTen = increase(10); // increase(10)의 리턴값인 addN함수 { return (x + 10)}을 저장함. 
 console.log(IncreaseTen);
 console.log(IncreaseTen(1)); // -> 1 + 10
 
 const increaseOne = increase(1);
 console.log(increaseOne(1)); // -> 1 + 1
 
 // 화살표 함수(arrow function)
 // (파라미터,...) => { 실행 코드; .... } == 자바의 람다표현식.
 // (파라미터, ...) => 리턴값 // 함수의 바디가 1문장만 존재 + 그 문장이 리턴문일 경우.
 
 const fnAdd = (x, y) => { return x + y; }; //이름이 없는 익명함수.
 console.log(fnAdd(3, 4));
 
 
 const fnSubtract = (x, y) => x - y; // { retutn x - y; }
 console.log(fnSubtract(3, 4));
 
 
 
 