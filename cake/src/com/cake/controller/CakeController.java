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
			// 文件存放到特定的位置
			String root = "D:/";
			// 获取后缀名
			String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."),
					file.getOriginalFilename().length());
			// 生成名字(避免重复命名)
			String uuid = UUID.randomUUID().toString().replace("-", "");
			String name = uuid + ext;
			try {
				FileCopyUtils.copy(file.getBytes(), new File(root + File.separator + "images", name));
				cake.setPic(name);
				System.out.println("上传文件成功");
			} catch (Exception e) {
				System.out.println("上传文件失败");
			}
		} else {
			System.out.println("未上传文件");
		}
		if (null != price && !"".equals(price) && null != weight && !"".equals(weight)) {
			cake.setPrice(Integer.parseInt(price));
			cake.setWeight(Double.parseDouble(weight));
			cakeService.addCake(cake);
		} else {
			System.out.println("请输入重量与价格");
		}
		return "redirect:/cake/list";
	}

	@RequestMapping("/list")
	public String list(HttpServletRequest request) {
		// 根据参数，进行分页查询，List
		List<Cake> list = cakeService.findAllCakes();
		for (Cake cake : list) {
			if ("".equals(cake.getPic()) || null == cake.getPic()) {
				cake.setPic("null.jpg");
			}
		}
		request.setAttribute("cakes", list);
		System.out.println("查询数据，跳转到list.jsp");
		return "list";
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		int flag = cakeService.deleteCake(id);
		if (flag > 0) {
			System.out.println("删除成功");
		} else {
			System.out.println("删除失败");
		}
		return "redirect:/cake/list";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(Cake cake, @RequestParam("cakePrice") String price, @RequestParam("cakeWeight") String weight,
			HttpServletRequest request, @RequestParam("image") MultipartFile file) {
		if (!file.isEmpty()) {
			// 文件存放服务端的位置
			String root = "D:/";
			// 获取后缀名
			String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."),
					file.getOriginalFilename().length());
			// 生成名字(避免重复命名)
			String uuid = UUID.randomUUID().toString().replace("-", "");
			String name = uuid + ext;
			try {
				FileCopyUtils.copy(file.getBytes(), new File(root + File.separator + "images", name));
				cake.setPic(name);
				System.out.println("上传文件成功");
			} catch (Exception e) {
				System.out.println("上传文件失败");
			}
		} else {
			System.out.println("未上传文件");
		}
		if (null != price && !"".equals(price) && null != weight && !"".equals(weight)) {
			cake.setPrice(Integer.parseInt(price));
			cake.setWeight(Double.parseDouble(weight));
			int flag = cakeService.updateCake(cake);
			if (flag > 0) {
				System.out.println("更新成功");
			} else {
				System.out.println("更新失败");
			}
		} else {
			System.out.println("请输入重量与价格");
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
