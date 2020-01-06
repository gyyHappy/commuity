package com.gyy.community.service;

import com.gyy.community.dto.PaginationDTO;
import com.gyy.community.dto.QuestionDTO;
import com.gyy.community.mapper.QuestionMapper;
import com.gyy.community.mapper.UserMapper;
import com.gyy.community.model.Question;
import com.gyy.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author GYY
 * @date 2020/1/2 18:03
 */
@Service
public class QuestionService {

    @Resource
    QuestionMapper questionMapper;

    @Resource
    UserMapper userMapper;

    /**
     * 查询问题信息列表
     *
     * @param page 当前页
     * @param size 每页显示的条数
     * @return 返回 QuestionDTO
     */
    public PaginationDTO list(Integer page, Integer size) {

        //页面信息
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer count = questionMapper.count();

        Integer totalPage;
        //判断当前应该有的页数
        if (count % size == 0) {
            totalPage = count / size;
        } else {
            totalPage = count / size + 1;
        }
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        paginationDTO.set(totalPage, page);
        // offset = size * (page - 1)
        Integer offset = size * (page - 1);
        List<Question> list = questionMapper.list(offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : list) {
            User user = userMapper.selectById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);

        return paginationDTO;
    }

    public PaginationDTO listByUserId(int userId, Integer page, Integer size) {
        //页面信息
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer count = questionMapper.countByUserId(userId);

        Integer totalPage;
        //判断当前应该有的页数
        if (count % size == 0) {
            totalPage = count / size;
        } else {
            totalPage = count / size + 1;
        }
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }

        paginationDTO.set(totalPage, page);

        // offset = size * (page - 1)
        Integer offset = size * (page - 1);
        if (offset < 0) {
            offset = 0;
        }
        List<Question> list = questionMapper.listByUserId(userId, offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : list) {
            User user = userMapper.selectById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);

        return paginationDTO;
    }


    public QuestionDTO getById(Integer id) {
        //通过id查询问题信息
        Question question = questionMapper.getById(id);
        //获取用户信息
        User user = userMapper.selectById(question.getCreator());
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question, User user) {
        Question dbQuestion = questionMapper.getById(question.getId());
        if (question.getId() != null && user.getId() == dbQuestion.getCreator()) {
            //更新
            question.setCreator(dbQuestion.getCreator());
            question.setGmtCreate(dbQuestion.getGmtCreate());
            question.setGmtModified(System.currentTimeMillis());
            questionMapper.update(question);
        }else {
            //创建
            question.setCreator(user.getId());
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insert(question);
        }
    }
}
