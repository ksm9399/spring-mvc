package hello.itemservice.web.basic;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor // final이 붙은 멤버 필드 생성자를 만들어 줌
public class BasicItemController {

  private final ItemRepository itemRepository;

  @GetMapping
  public String items(
    Model model
  ) {
    List<Item> items = itemRepository.findAll();

    model.addAttribute("items", items);
    return "basic/items";
  }

  @GetMapping("/{itemId}")
  public String item(
    @PathVariable Long itemId,
    Model model
  ) {
    Item item = itemRepository.findById(itemId);

    model.addAttribute("item", item);
    return "basic/item";
  }

  @GetMapping("/add")
  public String addForm() {
    return "basic/addForm";
  }

  // @PostMapping("/add")
  public String addItemV1(
    @RequestParam String itemName,
    @RequestParam int price,
    @RequestParam Integer quantity,
    Model model
  ) {
    Item item = new Item();

    item.setItemName(itemName);
    item.setPrice(quantity);
    item.setQuantity(quantity);

    itemRepository.save(item);

    model.addAttribute("item", item);

    return "basic/item";
  }

  /**
   * @ModelAttribute("item") Item item
   * model.addAttribute("item", item); 자동 추가
   * @ModelAttribute("item") 선언된 네임명으로 model담아 뷰로 보내줌
   */
  // @PostMapping("/add")
  public String addItemV2(
    @ModelAttribute("item") Item item,
    Model model
  ) {
    itemRepository.save(item);
    // model.addAttribute("item", item); // 자동 추가, 생략 가능

    return "basic/item";
  }

  /**
   * @ModelAttribute name 생략 가능
   * model.addAttribute(item); 자동 추가, 생략 가능
   * 생략시 model에 저장되는 name은 클래스명 첫글자만 소문자로 등록 Item -> item
   */
  // @PostMapping("/add")
  public String addItemV3(
    @ModelAttribute Item item
  ) {
    itemRepository.save(item);

    return "basic/item";
  }

  /**
   * @ModelAttribute 자체 생략 가능
   * model.addAttribute(item) 자동 추가
   * 일반 자료형일 경우 @RequestParam 자체 생략됨 (int, String...)
   */
  // @PostMapping("/add")
  public String addItemV4(Item item) {
    itemRepository.save(item);
    return "basic/item";
  }

  /**
   * PRG - Post/Redirect/Get
   * 웹 브라우저의 새로 고침은 마지막에 서버에 전송한 데이터를 다시 전송
   * addItemV4 리턴 값 뷰 호출 -> 웹 새로고침시 POST /add 상품 데이터를 서버로 전송됨
   * id값만 다른 중복되는 데이터가 계속 쌓이는 문제 발생
   * 저장 후 리다이렉트 통해 GET /items/{id} 호출 -> 새로고침 해도 GET /items/{id} 호출 됨
   */
  // @PostMapping("/add")
  public String addItemV5(Item item) {
    itemRepository.save(item);

    // redirect에서 + item.getId() 처럼 URL에 변수를 더해서 사용하는 것은 URL 인코딩이 안되기 때문에 위험 -> RedirectAttributes사용
    return "redirect:/basic/items/" + item.getId();
  }

  /**
   * RedirectAttributes
   */
  @PostMapping("/add")
  public String addItemV6(
    Item item,
    RedirectAttributes redirectAttributes
  ) {
    Item savedItem = itemRepository.save(item);
    redirectAttributes.addAttribute("itemId", savedItem.getId());
    redirectAttributes.addAttribute("status", true);  // 쿼리파라미터 형식으로 붙음

    return "redirect:/basic/items/{itemId}";
  }


  @GetMapping("/{itemId}/edit")
  public String editForm(
    @PathVariable Long itemId,
    Model model
  ) {
    Item item = itemRepository.findById(itemId);
    model.addAttribute("item", item);

    return "basic/editForm";
  }

  @PostMapping("/{itemId}/edit")
  public String edit(
    @PathVariable Long itemId,
    @ModelAttribute Item item,
    Model model
  ) {
    itemRepository.update(itemId, item);

    return "redirect:/basic/items/{itemId}";
  }

  /**
   * 테스트용 데이터 추가
   */
  @PostConstruct
  public void init() {
    itemRepository.save(new Item("itemA", 10000, 10));
    itemRepository.save(new Item("itemB", 20000, 20));
  }
}
