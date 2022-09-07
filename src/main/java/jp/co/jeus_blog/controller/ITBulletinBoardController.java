package jp.co.jeus_blog.controller;

import jp.co.jeus_blog.dto.ITBoardRegisterFormDto;
import jp.co.jeus_blog.service.ITBulletinBoardService;
import jp.co.jeus_blog.service.ImageUploadService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * IT Bulletin board controller
 */
@Log4j2
@RequestMapping("blog/it-bulletin")
@Transactional
@Controller
public class ITBulletinBoardController {

    @Autowired
    private ITBulletinBoardService boardService;
    @Autowired
    private ImageUploadService imageUploadService;

    /**
     * Returns all board data.
     *
     * @return Returns a list of boards from the board table.
     */
    @RequestMapping(value = "boards", method = RequestMethod.GET)
    public String getBoards(Model model) {
        model.addAttribute("boards", boardService.getAllPosts());
        return "it-board";
    }

    @GetMapping(value="board/register")
    public String getBoardRegister(Model model) {
        model.addAttribute("form", new ITBoardRegisterFormDto());
        return "it-board-register";
    }

    @PostMapping(value="board/register")
    public String register(@RequestPart("logoFile") MultipartFile multipartFile, ITBoardRegisterFormDto form) {
        log.info("Create new board. [{}]", form.getBoardName());
        String imageName = imageUploadService.uploadLogoImage(multipartFile);
        boardService.saveToBoard(form, imageName);
        return "redirect:/blog/it-bulletin/boards";
    }
}
