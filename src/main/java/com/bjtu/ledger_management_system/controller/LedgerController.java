package com.bjtu.ledger_management_system.controller;

import com.bjtu.ledger_management_system.common.Result;
import com.bjtu.ledger_management_system.controller.dto.UserMsgDTO;
import com.bjtu.ledger_management_system.entity.Ledger;
import com.bjtu.ledger_management_system.entity.Record;
import com.bjtu.ledger_management_system.entity.Role;
import com.bjtu.ledger_management_system.entity.Template;
import com.bjtu.ledger_management_system.service.LedgerService;
import com.bjtu.ledger_management_system.service.LogService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.List;

@RestController
@RequestMapping("/ledger")
public class LedgerController {
    @Resource
    private LogService logService;

    @Resource
    private LedgerService ledgerService;


    @PostMapping("/createledger")
    public Result<Ledger> createLedger(HttpServletRequest request, @RequestBody Ledger newLedger){
        try {
            HttpSession session = request.getSession();
            UserMsgDTO userMsgDTO = (UserMsgDTO) session.getAttribute("userMsgDTO");

            newLedger.setCreatorid(userMsgDTO.getUid());

            Ledger resultLedger = ledgerService.createLedger(newLedger);

            Long uid = userMsgDTO.getUid();
            String content = "创建了台账";
            logService.addLog(uid, content);
            return Result.success(resultLedger);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/addrecord")
    public Result addRecord(HttpServletRequest request, @RequestParam long ledgerid, @RequestParam long rowid, @RequestBody List<Record> recordList){
        try {
            HttpSession session = request.getSession();
            UserMsgDTO userMsgDTO = (UserMsgDTO) session.getAttribute("userMsgDTO");

            ledgerService.insertRecord(ledgerid,rowid,recordList);

            Long uid = userMsgDTO.getUid();
            String content = "添加了台账记录";
            logService.addLog(uid, content);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/updaterecord")
    public Result updateRecord(HttpServletRequest request, @RequestParam long ledgerid, @RequestParam long rowid, @RequestBody List<Record> recordList){
        try {
            HttpSession session = request.getSession();
            UserMsgDTO userMsgDTO = (UserMsgDTO) session.getAttribute("userMsgDTO");

            ledgerService.updateRecord(ledgerid,rowid,recordList);

            Long uid = userMsgDTO.getUid();
            String content = "修改了台账记录";
            logService.addLog(uid, content);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/delrecord")
    public Result delRecord(HttpServletRequest request, @RequestParam long ledgerid, @RequestParam long rowid){
        try {
            HttpSession session = request.getSession();
            UserMsgDTO userMsgDTO = (UserMsgDTO) session.getAttribute("userMsgDTO");

            ledgerService.delRecord(ledgerid,rowid);

            Long uid = userMsgDTO.getUid();
            String content = "删除了台账记录";
            logService.addLog(uid, content);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/getRecordList")
    public void getRecordList(HttpServletRequest request, HttpServletResponse response, @RequestParam long ledgerid, @RequestParam int pageNum, @RequestParam int pageSize){
        try {
            HttpSession session = request.getSession();
            UserMsgDTO userMsgDTO = (UserMsgDTO) session.getAttribute("userMsgDTO");

            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter out = null;

            int totalElements = ledgerService.getLedgerRecordNum(ledgerid);
            JSONArray resultList = ledgerService.getRecordListByPage(ledgerid,pageNum,pageSize);
            JSONObject data = new JSONObject();
            data.put("content",resultList);
            data.put("totalElements", totalElements);

            JSONObject res = new JSONObject();
            res.put("code", "0");
            res.put("msg", "成功！");
            res.put("data",data);

            out = response.getWriter();
            out.append(res.toString());

            Long uid = userMsgDTO.getUid();
            String content = "查看了台账记录";
            logService.addLog(uid, content);
            System.out.println(resultList.toString());
//            return Result.success();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/getledgerpage")
    public Result<Page<Ledger>> getLedgerPage(HttpServletRequest request, @RequestParam int pageNum, @RequestParam int pageSize) {
        try {
            HttpSession session = request.getSession();
            UserMsgDTO userMsgDTO = (UserMsgDTO) session.getAttribute("userMsgDTO");

            Page<Ledger> ledgerPage = ledgerService.getLedgerList(userMsgDTO.getLastdid(), pageNum, pageSize);

            Long uid = userMsgDTO.getUid();
            String content = "获取了部门"+userMsgDTO.getLastdid()+"的台账列表";
            logService.addLog(uid, content);
            return Result.success(ledgerPage);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/delledger")
    public Result delLedger(HttpServletRequest request, @RequestParam long ledgerid){
        try {
            HttpSession session = request.getSession();
            UserMsgDTO userMsgDTO = (UserMsgDTO) session.getAttribute("userMsgDTO");

            ledgerService.delLedger(ledgerid);

            Long uid = userMsgDTO.getUid();
            String content = "删除了台账";
            logService.addLog(uid, content);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 模糊查找用户
     * @param request
     * @param content
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/search")
    public Result<Page<Ledger>> queryLedgers(HttpServletRequest request,
                                         @RequestParam String content,
                                         @RequestParam Integer pageNum,
                                         @RequestParam Integer pageSize){
        return Result.success(ledgerService.getSpecificLedger(content,pageNum,pageSize));
    }


}
