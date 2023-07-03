package com.itwill.spring4.repository.member;

public enum Role {
    USER("ROLE_USER", "USER"),
    ADMIN("ROLE_ADMIN", "ADMIN");
    //-> 해당 객체가 생성자를 통해 생성이 되며, 각 이름(user, admin)은 변수가 됨.
    //-> 또한, 상수를 정의하기에 순서에 따라 0~ 번호가 메겨짐.
    //-> user: 0, admin: 1

    private final String key;
    private final String name;
    
    Role(String key, String name) {
        this.key = key;
        this.name = name;
    }
    
    public String getKey() {
        return this.key;
    }
}
