package org.example.friendexam.controller;

import lombok.RequiredArgsConstructor;
import org.example.friendexam.domain.Friend;
import org.example.friendexam.service.FriendService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/friends")
public class FriendController {
    private final FriendService friendService;

    @GetMapping
    public String friends(Model model){
        Iterable<Friend> friends =  friendService.findAllFriends();
        model.addAttribute("friends", friends);
        return "friends/list";
    }

    //친구등록 - url 몇개?  --  addForm  add    -- 메서드별로 따로 일을 할 수 있죠?
    //friends/add    --  Get   --  폼 보여줘요.    Post  등록해줘요.
    @GetMapping("/add")
    public String addForm(Model model){
        model.addAttribute("friend", new Friend());
        return "friends/form";
    }

    @PostMapping("/add")
    public String addFriend(@ModelAttribute Friend friend, RedirectAttributes redirectAttributes){
        friendService.saveFriend(friend);
        redirectAttributes.addFlashAttribute("message","친구등록 성공!!");
        return "redirect:/friends";
    }


}
