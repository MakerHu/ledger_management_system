package com.bjtu.ledger_management_system.controller;

import com.bjtu.ledger_management_system.common.Result;
import com.bjtu.ledger_management_system.controller.dto.CreateTemplateDTO;
import com.bjtu.ledger_management_system.controller.dto.UserMsgDTO;
import com.bjtu.ledger_management_system.entity.Template;
import com.bjtu.ledger_management_system.entity.User;
import com.bjtu.ledger_management_system.service.LogService;
import com.bjtu.ledger_management_system.service.TemplateService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

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
            createTemplateDTO.getNewTemplate().setDid(userMsgDTO.getLastdid());
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

    @GetMapping("/gettemplate")
    public Result<CreateTemplateDTO> getTemplate(HttpServletRequest request, @RequestParam long tempid) {
        try {
            HttpSession session = request.getSession();
            UserMsgDTO userMsgDTO = (UserMsgDTO) session.getAttribute("userMsgDTO");

            CreateTemplateDTO resultTemp = templateService.getTemplate(tempid);

            Long uid = userMsgDTO.getUid();
            String content = "获取了tempid为"+tempid+"的台账模板";
            logService.addLog(uid, content);
            return Result.success(resultTemp);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/gettemplatepage")
    public Result<Page<Template>> getTemplatePage(HttpServletRequest request, @RequestParam int pageNum, @RequestParam int pageSize) {
        try {
            HttpSession session = request.getSession();
            UserMsgDTO userMsgDTO = (UserMsgDTO) session.getAttribute("userMsgDTO");

            Page<Template> templatePage = templateService.getTemplateList(pageNum,pageSize);

            Long uid = userMsgDTO.getUid();
            String content = "获取了台账模板列表";
            logService.addLog(uid, content);
            return Result.success(templatePage);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 模糊查找台账模板
     * @param request
     * @param content
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/search")
    public Result<Page<Template>> queryUsers(HttpServletRequest request,
                                         @RequestParam String content,
                                         @RequestParam Integer pageNum,
                                         @RequestParam Integer pageSize){
        return Result.success(templateService.getSpecificTemp(content,pageNum,pageSize));
    }

}
