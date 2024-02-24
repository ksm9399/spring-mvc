package hello.springmvc.basic.requestmapping;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mapping/users")
public class MappingClassController {
  /**
   * 회원 목록 조회
   * GET /mapping/users
   */
  @GetMapping
  public String users() {
    return "get users";
  }

  /**
   * 회원 등록
   * POST /mapping/users
   */
  @PostMapping
  public String addUser() {
    return "post user";
  }

  /**
   * 회원 조회
   * GET /mapping/users/{userId}
   * @PathVariable 코드단에서 변수명을 다르게 사용하고 싶을 경우 @PathVariable("userId") String data 로 사용가능
   */
  @GetMapping("/{userId}")
  public String findUser(@PathVariable String userId) {
    return "get userId=" + userId;
  }

  /**
   * 회원 수정
   * PATCH /mapping/users/{userId}
   */
  @PatchMapping("/{userId}")
  public String updateUser(@PathVariable String userId) {
    return "update userId=" + userId;
  }

  /**
   * 회원 삭제
   * DELETE /mapping/users/{userId}
   */
  @DeleteMapping("/{userId}")
  public String deleteUser(@PathVariable String userId) {
    return "delete userId=" + userId;
  }
}
