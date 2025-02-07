package com.example.rcp.controller;

import java.io.IOException;
import java.sql.Timestamp;

import java.util.ArrayList;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.rcp.domain.LoginMember;
import com.example.rcp.domain.Members;
import com.example.rcp.domain.SearchOption;

//import com.example.rcp.domain.SearchOption;

import com.example.rcp.repository.MembersRepository;
import com.example.rcp.service.AccountService;
import com.example.rcp.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private MemberService memberService;

	@ModelAttribute(name = "select_items")
	public Map<String, String> selectItems() {
		Map<String, String> select_items = new LinkedHashMap<>();
		select_items.put("All", "all");
		select_items.put("社員名", "name");
		select_items.put("部署名", "part");
		return select_items;
	}

	@GetMapping("/create")
	public String createForm(@SessionAttribute LoginMember loginMember, Model model) throws Exception {

		model.addAttribute(loginMember);
		return "account/createForm";
	}

	@GetMapping("/members")
	public String getMemberList(@SessionAttribute LoginMember loginMember, Model model, Pageable pageable,
			SearchOption searchOption) throws Exception {

		model.addAttribute("searchOption", searchOption);
		model.addAttribute(loginMember);

		return "account/accountSearch";

	}
	


	@GetMapping("/members/{selectedItem}")
	public String getMemberListFromAll(@SessionAttribute LoginMember loginMember, @PathVariable String selectedItem,
			Model model, Pageable pageable, SearchOption searchOption) throws Exception {

		int startPage = 0;
		int endPage = 0;
		if (searchOption.getSearchText() == "") {

		} else {

			Page<com.example.rcp.model.Members> memberList = accountService.selectMembers(pageable, searchOption);
			if (memberList.getContent().isEmpty()) {

			} else {

				startPage = Math.max(1, memberList.getPageable().getPageNumber() - 4);
				endPage = Math.min(memberList.getTotalPages(), memberList.getPageable().getPageNumber() + 4);

			}

			model.addAttribute("memberList", memberList);
			model.addAttribute("startPage", startPage);
			model.addAttribute("endPage", endPage);
		}

		model.addAttribute("searchOption", searchOption);
		model.addAttribute("selectedItem", selectedItem);
		model.addAttribute(loginMember);

		return "account/accountSearch";

	}

	@PostMapping("/create")
	public String createAccount(@SessionAttribute LoginMember loginMember, @RequestParam("file") MultipartFile file,
			Model model) throws Exception {
		try {
			List<Members> members = readFile(file);
			List<Members> failedCreateAccountMembers = accountService.unavailabledEmail(members);
			List<Members> memberList = accountService.save(members);
			if (memberList == null) {
				model.addAttribute("failedMsg", "Unavailable Email address");
			} else {

				log.info("succeed={}", memberList.size());
				model.addAttribute("succedMsg", memberList.size() + "account created");
				model.addAttribute("memberList", memberList);

			}
			if (failedCreateAccountMembers != null) {

				model.addAttribute("failedMsg", "Unavailable Email address");
				log.info("failed={}", failedCreateAccountMembers.size());
				model.addAttribute("failedList", failedCreateAccountMembers);
			}

		} catch (Exception e) {
			model.addAttribute("exceptionMsg", e.getMessage());

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
		log.info("row={}", worksheet.getPhysicalNumberOfRows());
		Iterator<Row> iterator = worksheet.iterator();
		Row row = iterator.next();

		while (iterator.hasNext()) {
			row = iterator.next();

			Members member = new Members();

			member.setMemberName(cellReader(row.getCell(0)));
			member.setMemberNameHiragana(cellReader(row.getCell(1)));
			member.setMemberPart(cellReader(row.getCell(2)));
			member.setMemberPartHiragana(cellReader(row.getCell(3)));
			member.setMemberTel(cellReader(row.getCell(4)));
			String email = cellReader(row.getCell(5)) + "@abc.ne.jp";
			member.setMemberEmail(email);
			member.setMemberPassword(cellReader(row.getCell(6)));
			String auth = cellReader(row.getCell(7));
			member.setMemberAuth(Short.parseShort(auth));
			Date date = row.getCell(8).getDateCellValue();
			Timestamp regDate = new Timestamp(date.getTime());
			member.setMemberRegDate(regDate);

			memberList.add(member);
		}

		return memberList;

	}

	@SuppressWarnings("incomplete-switch")
	public static String cellReader(Cell cell) {
		String value = "";

		CellType ct = cell.getCellTypeEnum();
		if (ct != null) {
			switch (cell.getCellTypeEnum()) {
			case FORMULA:
				value = cell.getStringCellValue();
				break;
			case NUMERIC:
				value = (int) cell.getNumericCellValue() + "";
				break;
			case STRING:
				value = cell.getStringCellValue() + "";
				break;
			case BOOLEAN:
				value = cell.getBooleanCellValue() + "";
				break;
			case ERROR:
				value = cell.getErrorCellValue() + "";
				break;
			}
		}
		return value;

	}

}
