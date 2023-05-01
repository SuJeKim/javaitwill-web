/**
 * array_function.js
 */

 document.addEventListener('DOMContentLoaded', function () {
     
     // 배열 메서드: concat(): 배열에 새로운 원소를 끝에 추가. 원소가 추가된 새로운 배열을 리턴.
     // 배열 push() 메서드: 기존 배열의  끝에 원소를 추가. 리턴값은 없음. 원본 배열을 훼손함.
     const arr = []; // 빈 배열 선언.
     console.log(arr);
    
     arr.push(100);
     console.log(arr);
     
     arr.push(200);
     console.log(200);
     // -> 원본 배열의 내용이 변함. const 가능. -> [] -> [100] -> [100,200]
     
     //const arr2 = [];
     //arr2 = arr2.concat(1);
     // -> 새로운 값으로 변경할려고 함. 리턴 값이 존재하니(새로운 배열을 만듦).
     let arr2 = [];
     console.log(arr2);
     
     arr2 = arr2.concat(1); //-> arr2 = [1];
     console.log(arr2);
     
     arr2 = arr2.concat(2);
     console.log(arr2);
    
     const numbers = [1, 2, 3, 4, 5, 6, 7];
     
     // 1. 배열 numbers의 원소들 중에서 홀수들만 원소로 갖는 배열을 만들고 출력.
     // > [1 ,3 ,5, 7]
     
     let even = [];
     let result = [];
     for(let i = 0; i < numbers.length; i++) {
         if(numbers[i] % 2 !== 0){
           even += even.concat(numbers[i] , ' ');
            //even.push(numbers[i]);
         }
         
     }
     console.log('1번 문제 =', even);
     
     // 강사님 코드.
    
    let odds = []; // 홀수들을 저장할 배열 선언
     for(let x of numbers) { // 뱅려의 원소를 순서대로  반복.
         if(x % 2) { // 2로 나눈 나머지가 있으면(홀수이면)
             odds = odds.concat(x); // 홀수릏 추가한 새로운 배열을 생성.
         }
     }
     console.log(odds);
     
     // 한 줄에 
     odds = numbers.filter((x) => x % 2);
     console.log(odds);
     
     // 2. 배열 numbers의 원소를 제곱한 숫자들을 원소로 갖는 배열을 만들고 출력.
     // > [1, 4, 9, 16, 25, 36, 49]
     
     let sq = [];
     for(let n = 0; n < numbers.length; n++){
         result += sq.concat(numbers[n] * numbers[n], ' ');
         
     }
     console.log('2번 문제 =', result, ' ');
    
    let squares = [];
    for (let x of numbers){
        squares = squares.concat(x ** 2); // **: 거듭제곱 연산자. 자바스크립트만 해당
    }
    console.log(squares);
     
     squares = numbers.map((x) => x ** 2);
     console.log(squares);
     
     // 3. 배열 numbers의 원소들 중에서 홀수의 원소로 갖는 배열을 만들고 출력.
     // > [1, 9, 35, 49]
     let oddSquares = [];
     for(let x of numbers) {
         if(x % 2){
             oddSquares = oddSquares.concat(x ** 2);
         }
     }
     console.log(oddSquares);
     
     oddSquares = numbers.filter((x) => x % 2).map((x) => x ** 2);
     console.log(oddSquares);
     
     
 });