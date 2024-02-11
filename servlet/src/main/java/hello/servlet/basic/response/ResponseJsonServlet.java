package hello.servlet.basic.response;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import hello.servlet.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "responseJsonServlet", urlPatterns="/response-json")
public class ResponseJsonServlet extends HttpServlet {

  private ObjectMapper objectMapper = new ObjectMapper();

  @Override
  protected void service(
    HttpServletRequest request,
    HttpServletResponse response
  ) throws ServletException, IOException {

    //Content-Type: text/html;charset=utf-8
    response.setContentType("application/json");
    response.setCharacterEncoding("utf-8");

    HelloData helloData = new HelloData();
    helloData.setUserName("kim");
    helloData.setAge(20);

    // {"userName": "kim", "age": 20}
    String result = objectToJson(helloData);

    response.getWriter().write(result);

    // json데이터를 다시 객체로 변경해봄
    HelloData parseJson = jsonToObject(result, HelloData.class);
    parseJson.setAge(10);
    parseJson.setUserName("kang");

    System.out.println(parseJson.getAge());
    System.out.println(parseJson.getUserName());
  }

  // 객체 -> json데이터로 변환
  public String objectToJson(Object data) throws JsonProcessingException {
    String result = objectMapper.writeValueAsString(data);

    return result;
  }

  // json데이터 -> 객체로 변환
  public <T> T jsonToObject(String result, Class<T> classType) throws JsonMappingException, JsonProcessingException {
    return objectMapper.readValue(result, classType);
  }
}
