/**
 * object.js
    // Java: class -> new Constr(); 
 * 
 */

 document.addEventListener('DOMContentLoaded', function () {
    // JSON(JavaScript Object Notation): 자바스크립트 객체를 표기법. 
    // { property: value, ... }
    // 객체를 만들때 변수이름으로 키워드를 사용하는 것이 문제가 되지않음 -> 자바는 불가능함.
    // 단, 참조 연산자를 사용할 떄 문제가 발생함.( error 발생은 아님)
    // 그래서 ['']로 키워드와 구별함.
    
    const person = {  // {} 무속성 객체.
        name: '오쌤',
        age: 16,
        phone: ['010-0000-0000', '02-0000-0000'], // 제일 마지막 원소에서도 ,가 있어도 되고 없어도 됨. 배열도 마찬가지임. 구분자: ','
        //for: 'itwill',
    };
    console.log(person);
    
    // 객체의 property 접근(사용): (1) 참조 연산자 (2) 인덱스 연산자
    console.log(person.name); 
    // person 객체의 name property 값을 읽음
    // property는 자바의 filed와 비슷함. '.': 참조 연산자.
    console.log(person['age']); // person 객체의 age property 값을 읽음. 꼭 문자열로 쓰기
    console.log(person.phone);
    console.log(person.phone[0]);
    console.log(person['phone'][1]);
    
    // person 객체의 name property 값을 변경.
    person.name = '홍길동';
    console.log(person);
    
    // 오타시 발생 => 새로운 property 값을 추가.
    // div.innHTML = ''; => 새로운 property를 추가하는 것이 됨. document.get~Id: 객체를 만듦. 그래서 객체의 속성르 가짐. => 디버깅 힘듦.
    person.naem = '홍길동';
    console.log(person);
    
    // 자바스크립트의 객체는 property를 추가할 수 있음/ 삭제도 가능.
    person.company = '아이티윌';
    console.log(person);
    
    // 객체(object)에서 for-in 구문: 객체의 property 이름들을 iteration.
    // -> 객체에서의 property이름은 인덱스의 역할(구분)과 비슷함. 그래서 다음 구문을 사용하면 property이름들을 뽑아낼수 있음.
    for(let key in person){
        console.log(key, ':' , person[key]); // value를 출력하고 싶으면 인덱스 연산자 사용
    }
    
    // 메서드를 갖는 객체:
    const score = {
        korean: 100,
        english: 90,
        math: 70,
        sum: function () {
            return this.korean + this.english + this.math;
        }, // score가 갖는 메서드. -> 익명 함수이고 이를 변수 이름(sum)에 저장.
        mean: function () {
          return this.sum() / 3; // 함수 호출이니 () 사용하기.  
        },
    };
    
    console.log(score);
    console.log(score.sum()); // 메서드 호출
    console.log(score.mean());
    
    // 생성자 함수: this 사용 가능 -> 이떄는 snake 표기법으로 하기.대문자 사용.
    function Score(korean, english, math){
        
        // 필드
        this.korean = korean;
        this.english = english;
        this.math = math;
        
        // 메서드
        this.sum = function () {
            return this.korean + this.english + this.math;
        };
        this.mean = function () {
            return this.sum() / 3;
        };
    }
    
    // 생성자 함수 호출:
    const score1 = new Score(10, 20, 30); // 생성자 함수를 통해 객체 생성
    console.log(score1);
    console.log(score1.sum()); // 객체 메서드 호출
    console.log(score1.mean());
    
    const score2 = new Score(90, 95, 89);
    console.log(score2);
     console.log(score2.sum());
    console.log(score2.mean());
    
 });
 