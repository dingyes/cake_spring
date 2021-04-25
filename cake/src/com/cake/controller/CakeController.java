package com.cake.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cake.entity.Cake;
import com.cake.service.CakeService;

@Controller
@RequestMapping("/cake")
public class CakeController {

	@Resource
	private CakeService cakeService;

	@RequestMapping("/add")
	public String add(@RequestParam("cakePrice") String price, @RequestParam("cakeWeight") String weight,
			HttpServletRequest request, @RequestParam("image") MultipartFile file) {
		Cake cake = new Cake();
		if (!file.isEmpty()) {
			// �ļ���ŵ��ض���λ��
			String root = "D:/";
			// ��ȡ��׺��
			String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."),
					file.getOriginalFilename().length());
			// ��������(�����ظ�����)
			String uuid = UUID.randomUUID().toString().replace("-", "");
			String name = uuid + ext;
			try {
				FileCopyUtils.copy(file.getBytes(), new File(root + File.separator + "images", name));
				cake.setPic(name);
				System.out.println("�ϴ��ļ��ɹ�");
			} catch (Exception e) {
				System.out.println("�ϴ��ļ�ʧ��");
			}
		} else {
			System.out.println("δ�ϴ��ļ�");
		}
		if (null != price && !"".equals(price) && null != weight && !"".equals(weight)) {
			cake.setPrice(Integer.parseInt(price));
			cake.setWeight(Double.parseDouble(weight));
			cakeService.addCake(cake);
		} else {
			System.out.println("������������۸�");
		}
		return "redirect:/cake/list";
	}

	@RequestMapping("/list")
	public String list(HttpServletRequest request) {
		// ���ݲ��������з�ҳ��ѯ��List
		List<Cake> list = cakeService.findAllCakes();
		for (Cake cake : list) {
			if ("".equals(cake.getPic()) || null == cake.getPic()) {
				cake.setPic("null.jpg");
			}
		}
		request.setAttribute("cakes", list);
		System.out.println("��ѯ���ݣ���ת��list.jsp");
		return "list";
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		int flag = cakeService.deleteCake(id);
		if (flag > 0) {
			System.out.println("ɾ���ɹ�");
		} else {
			System.out.println("ɾ��ʧ��");
		}
		return "redirect:/cake/list";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(Cake cake, @RequestParam("cakePrice") String price, @RequestParam("cakeWeight") String weight,
			HttpServletRequest request, @RequestParam("image") MultipartFile file) {
		if (!file.isEmpty()) {
			// �ļ���ŷ���˵�λ��
			String root = "D:/";
			// ��ȡ��׺��
			String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."),
					file.getOriginalFilename().length());
			// ��������(�����ظ�����)
			String uuid = UUID.randomUUID().toString().replace("-", "");
			String name = uuid + ext;
			try {
				FileCopyUtils.copy(file.getBytes(), new File(root + File.separator + "images", name));
				cake.setPic(name);
				System.out.println("�ϴ��ļ��ɹ�");
			} catch (Exception e) {
				System.out.println("�ϴ��ļ�ʧ��");
			}
		} else {
			System.out.println("δ�ϴ��ļ�");
		}
		if (null != price && !"".equals(price) && null != weight && !"".equals(weight)) {
			cake.setPrice(Integer.parseInt(price));
			cake.setWeight(Double.parseDouble(weight));
			int flag = cakeService.updateCake(cake);
			if (flag > 0) {
				System.out.println("���³ɹ�");
			} else {
				System.out.println("����ʧ��");
			}
		} else {
			System.out.println("������������۸�");
		}
		return "redirect:/cake/list";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable("id") int id, HttpServletRequest request) {
		Cake cake = cakeService.findSpecificCake(id);
		System.out.println(cake);
		if (cake.getPic() == null || "".equals(cake.getPic())) {
			cake.setPic("null.jpg");
		}
		System.out.println(cake.getPic());
		request.setAttribute("update", cake);
		return "updateCake";
	}

}
