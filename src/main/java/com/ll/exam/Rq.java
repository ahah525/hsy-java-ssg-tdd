package com.ll.exam;

import java.util.HashMap;
import java.util.Map;

public class Rq {
    private String url;     // path + query 문
    private String path;
    private Map<String, String> queryParams;   // query 파라미터(파라미터, 값)

    public Rq(String url) {
        this.url =  url;
        String[] urlBits = url.split("\\?", 2);// ? 기준으로 2개로 분리
        this.path = urlBits[0]; // path

        queryParams = new HashMap<>();  // hashMap 생성

        // query 문이 있으면 hashMap에 (파라미터 명, 값) 저장
        if(urlBits.length == 2) {
            String queryStr = urlBits[1];   // 쿼리문

            String[] queryBits = queryStr.split("&");// &기준으로 분리
            for(String queryBit : queryBits) {
                String[] param = queryBit.split("=", 2);// = 기준으로 2개로 분리
                // = 형식으로 입력되지 않았을 경우 예외 처리
                if(param.length == 1) {
                    continue;
                }
                String name = param[0].trim();     // 파라미터명
                String value = param[1].trim();    // 값
                queryParams.put(name, value);       // hashMap에 삽입
            }
        }
    }

    // url의 파라미터에 해당하는 값 반환
    public int getIntParam(String paramName, int defaultValue) {
        // 해당 파라미터에 대한 파라미터 값이 없으면 디폴트 값 리턴
        if(!queryParams.containsKey(paramName))
            return defaultValue;

        String value = queryParams.get(paramName);  // 파라미터 값
        // value가 비었을 경우 예외처리
        if(value.length() == 0) return defaultValue;
        return Integer.parseInt(value); // Integer 형변환하여 리턴
    }

    public String getPath() {
        return path;
    }
}