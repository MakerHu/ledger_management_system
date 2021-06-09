package com.bjtu.ledger_management_system.controller;

import com.bjtu.ledger_management_system.common.Result;
import com.bjtu.ledger_management_system.controller.dto.CreateTemplateDTO;
import com.bjtu.ledger_management_system.controller.dto.UserMsgDTO;
import com.bjtu.ledger_management_system.entity.Template;
import com.bjtu.ledger_management_system.service.LogService;
import com.bjtu.ledger_management_system.service.TemplateService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/template")
public class TemplateController {
    @Resource
    private TemplateService templateService;

    @Resource
    private LogService logService;

    @PostMapping("/create")
    public Result createTemplate(HttpServletRequest request, @RequestBody CreateTemplateDTO createTemplateDTO) {
        try {
            HttpSession session = request.getSession();
            UserMsgDTO userMsgDTO = (UserMsgDTO) session.getAttribute("userMsgDTO");

            createTemplateDTO.getNewTemplate().setCreatorid(userMsgDTO.getUid());
            templateService.add(createTemplateDTO.getNewTemplate(), createTemplateDTO.getTableHead());

            Long uid = userMsgDTO.getUid();
            String content = "创建了台账模板" + createTemplateDTO.getNewTemplate().getTempname();
            logService.addLog(uid, content);
            return Result.success();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
