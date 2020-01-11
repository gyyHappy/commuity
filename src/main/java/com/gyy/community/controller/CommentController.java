package com.gyy.community.controller;

import com.gyy.community.dto.CommentDTO;
import com.gyy.community.dto.ResultDTO;
import com.gyy.community.exception.CustomizeErrorCode;
import com.gyy.community.mapper.CommentMapper;
import com.gyy.community.model.Comment;
import com.gyy.community.model.User;
import com.gyy.community.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author GYY
 * @date 2020/1/10 20:14
 */
@Controller
public class CommentController {


    @Resource
    private CommentService commentService;

    @PostMapping("/comment")
    @ResponseBody
    public Object post(@RequestBody CommentDTO commentDTO,
                       HttpServletRequest request){
//        User user = (User) request.getSession().getAttribute("user");
//        if (user == null){
//            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
//        }
        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setCommentator(1L);
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setLikeCount(0L);
        commentService.insert(comment);
        return ResultDTO.okOf();
    }
}
