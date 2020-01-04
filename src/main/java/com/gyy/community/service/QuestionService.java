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
     * @param page
     * @param size
     * @return 返回 QuestionDTO
     */
    public PaginationDTO list(Integer page, Integer size) {

        //页面信息
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer count = questionMapper.count();
        paginationDTO.set(page,size,count);

        if (page < 1) {
            page = 1;
        }

        if (page > paginationDTO.getTotalPage()) {
            page = paginationDTO.getTotalPage();
        }

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
}
