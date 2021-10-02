package com.example.rcp.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.attoparser.config.ParseConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.example.rcp.domain.LoginMember;
import com.example.rcp.domain.Members;
import com.example.rcp.service.AccountService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/account")
public class AccountController {

	@Autowired
	AccountService accountService;

	@GetMapping("/create")
	public String createForm(@SessionAttribute LoginMember loginMember, Model model) throws Exception {

		model.addAttribute(loginMember);
		return "account/createForm";
	}

	/*
	 * @PostMapping("/create") public String
	 * createAccount(@ModelAttribute("member")Members member,@SessionAttribute
	 * LoginMember loginMember,Model model) throws Exception{
	 * accountService.save(member);
	 * 
	 * return "account/detail";
	 * 
	 * 
	 * }
	 */

	@PostMapping("/create")
	public String createAccount(@SessionAttribute LoginMember loginMember, @RequestParam("file") MultipartFile file,
			Model model) throws Exception {
		List<Members> members = readFile(file);
		List<Members> failedCreateAccountMembers = accountService.unavailabledEmail(members);
		List<Members> memberList = accountService.save(members);
		
		if (memberList == null) {
			model.addAttribute("failedMsg","Unavailable Email address");
		} else {

			log.info("succeed={}", memberList.size());
			model.addAttribute("SuccedMsg",memberList.size()+"account created");
			model.addAttribute("memberList", memberList);

		}
		if (failedCreateAccountMembers != null) {
			
			model.addAttribute("failedMsg","Unavailable Email address");
			log.info("failed={}", failedCreateAccountMembers.size());
			model.addAttribute("failedList", failedCreateAccountMembers);
		}

			model.addAttribute("loginMember", loginMember);
			
		return "account/createForm";

	}

	public List<Members> readFile(MultipartFile file) throws IOException {

		List<Members> memberList = new ArrayList<>();

		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		log.info(extension);

		if (!extension.equals("xlsx") && !extension.equals("xls")) {
			throw new IOException("Excelファイルをアップロードしてください。");
		}

		Workbook workbook = null;

		if (extension.equals("xlsx")) {
			workbook = new XSSFWorkbook(file.getInputStream());
		} else if (extension.equals("xls")) {
			workbook = new HSSFWorkbook(file.getInputStream());
		}

		Sheet worksheet = workbook.getSheetAt(0);

		for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {

			Row row = worksheet.getRow(i);
			if (row.getCell(i) == null) {
				continue;
			}

			Members member = new Members();

			member.setMemberName(row.getCell(0).getStringCellValue());
			member.setMemberNameHiragana(row.getCell(1).getStringCellValue());
			member.setMemberPart(row.getCell(2).getStringCellValue());
			member.setMemberPartHiragana(row.getCell(3).getStringCellValue());
			member.setMemberTel(String.valueOf((int) row.getCell(4).getNumericCellValue()));
			String email = row.getCell(5).getStringCellValue() + "@abc.ne.jp";
			member.setMemberEmail(email);
			member.setMemberPassword(String.valueOf(row.getCell(6).getNumericCellValue()));
			member.setMemberAuth((short) row.getCell(7).getNumericCellValue());
			Date date = row.getCell(8).getDateCellValue();
			Timestamp regDate = new Timestamp(date.getTime());
			member.setMemberRegDate(regDate);
			memberList.add(member);

		}
		return memberList;

	}

}
