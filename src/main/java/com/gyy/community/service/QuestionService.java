package com.gyy.community.service;

import com.gyy.community.dto.PaginationDTO;
import com.gyy.community.dto.QuestionDTO;
import com.gyy.community.exception.CustomizeErrorCode;
import com.gyy.community.exception.CustomizeException;
import com.gyy.community.mapper.QuestionExtMapper;
import com.gyy.community.mapper.QuestionMapper;
import com.gyy.community.mapper.UserMapper;
import com.gyy.community.model.Question;
import com.gyy.community.model.QuestionExample;
import com.gyy.community.model.User;
import org.apache.ibatis.session.RowBounds;
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

    @Resource
    QuestionExtMapper questionExtMapper;

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
        Integer count = (int) questionMapper.countByExample(new QuestionExample());

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
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(offset, size));
        List<QuestionDTO> questionDTOList = new ArrayList<>();


        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);

        return paginationDTO;
    }

    public PaginationDTO listByUserId(Long userId, Integer page, Integer size) {
        //页面信息
        PaginationDTO paginationDTO = new PaginationDTO();

        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria()
                .andIdEqualTo(userId);
        Integer count = (int) questionMapper.countByExample(questionExample);

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
        QuestionExample example = new QuestionExample();
        example.createCriteria()
                .andIdEqualTo(userId);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, page));
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);

        return paginationDTO;
    }


    public QuestionDTO getById(Long id) {
        //通过id查询问题信息
        Question question = questionMapper.selectByPrimaryKey(id);
        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        //获取用户信息
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question, User user) {
        Question dbQuestion = questionMapper.selectByPrimaryKey(question.getId());
        if (question.getId() != null && user.getId().equals(dbQuestion.getCreator())) {
            //更新
            question.setCreator(dbQuestion.getCreator());
            question.setGmtCreate(dbQuestion.getGmtCreate());
            question.setGmtModified(System.currentTimeMillis());
            question.setViewCount(0);
            question.setCommentCount(0);
            question.setLikeCount(0);
            int i = questionMapper.updateByPrimaryKey(question);
            if (i != 1){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        } else {
            //创建
            question.setCreator(user.getId());
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insert(question);
        }
    }

    public void incView(Long id) {
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.incView(question);
    }
}
