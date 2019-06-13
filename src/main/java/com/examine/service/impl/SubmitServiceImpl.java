package com.examine.service.impl;

import com.examine.dao.SubmitMapper;
import com.examine.domain.TSubmit;
import com.examine.service.SubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SubmitServiceImpl implements SubmitService {

    private final SubmitMapper submitMapper;

    @Autowired
    public SubmitServiceImpl(SubmitMapper submitMapper) {
        this.submitMapper = submitMapper;
    }

    /**
     * 插入登陆学生登陆信息到t_submit
     *
     * @param sName
     * @param ipAddress
     */
    @Override
    public void insertStudentLoginMessage(String sName, String ipAddress,Integer examId) {
        Map<String, String> loginMessage = new HashMap<>();
        loginMessage.put("sName", sName);
        loginMessage.put("ipAddress", ipAddress);
        loginMessage.put("examId",String.valueOf(examId));
        submitMapper.insertStudentLoginMessage(loginMessage);
    }

    /**
     * 非物理删除所有提交
     *
     * @return
     */
    @Override
    public boolean nonphysicalDeleteAllSubmit(Integer id) {
        boolean flag = false;
        Integer affectCount = submitMapper.nonphysicalDeleteAllSubmit(id);
        if (affectCount >= 1) {
            flag = true;
        }
        return flag;
    }

    /**
     * 下载所有提交的数据的压缩包
     *
     * @return
     */
    @Override
    public List<String> downloadSubmitZip() {

        return submitMapper.downloadSubmitZip();
    }

    /**
     * 学生与IP解绑
     *
     * @param sSno
     * @return
     */
    @Override
    public Integer doUnbinding(String sSno) {

        return submitMapper.doUnbinding(sSno);
    }

    /**
     * 修改归档完成标志位pageonhole为1
     * @param id
     */
    @Override
    public boolean finishedPageOnHole(Integer id) {
        return submitMapper.finishedPageOnHole(id);
    }

    /**
     * 查出提交结果
     * @param map
     * @return
     */
    @Override
    public List<TSubmit> selectSubmitResult(Map map) {
        return submitMapper.selectSubmitResult(map);
    }
}
