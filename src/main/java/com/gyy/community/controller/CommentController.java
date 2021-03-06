package com.gyy.community.controller;

import com.gyy.community.dto.CommentCreateDTO;
import com.gyy.community.dto.CommentDTO;
import com.gyy.community.dto.ResultDTO;
import com.gyy.community.enums.CommentEnum;
import com.gyy.community.exception.CustomizeErrorCode;
import com.gyy.community.mapper.NotificationMapper;
import com.gyy.community.model.Comment;
import com.gyy.community.model.User;
import com.gyy.community.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author GYY
 * @date 2020/1/10 20:14
 */
@Controller
public class CommentController {


    @Resource
    private CommentService commentService;



    /**
     * 提交评论
     * @param commentCreateDTO commentCreateDTO
     * @param request request
     * @return ResultDTO
     */
    @PostMapping("/comment")
    @ResponseBody
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO,
                       HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user == null){
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        //判断评论内容是否为空或空串
        if (StringUtils.isBlank(commentCreateDTO.getContent())){
            return ResultDTO.errorOf(CustomizeErrorCode.COMMENT_IS_EMPTY);
        }
        Comment comment = new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0L);
        commentService.insert(comment,user);
        return ResultDTO.okOf();
    }

    @GetMapping("/comment/{id}")
    @ResponseBody
    public ResultDTO<List<CommentDTO>> comment(@PathVariable(name = "id") Long id){
        List<CommentDTO> commentDTOS = commentService.listByTargetId(id, CommentEnum.COMMENT);
        return ResultDTO.okOf(commentDTOS);
    }
}
