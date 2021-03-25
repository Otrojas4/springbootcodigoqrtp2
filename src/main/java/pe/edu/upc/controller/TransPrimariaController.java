package pe.edu.upc.controller;

import java.text.ParseException;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pe.edu.upc.entity.TransPrimaria;

import pe.edu.upc.service.ITransPrimariaService;



@Controller
@RequestMapping("/transprimaria")
public class TransPrimariaController {
	@Autowired
	private ITransPrimariaService tpService;
	
	@RequestMapping("/")
	public String irCodigoqr(Map<String, Object> model) {
		model.put("listaTransPrimarias", tpService.listar());
		return "listTransPrimarias";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("transprimaria", new TransPrimaria());
		return "transprimaria";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid TransPrimaria objTp, BindingResult binRes, Model model)
			throws ParseException {
		if (binRes.hasErrors()) {
			return "transprimaria";
		}
		boolean flag = tpService.insertar(objTp);
			
			if (flag) {
				return "redirect:/transprimaria/listar/";
			} else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/transprimaria/irRegistrar";
			}
	
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				tpService.eliminar (id);
				model.put("listaTransPrimarias", tpService.listar());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar una Transformación asignada");
			model.put("listaTransPrimarias", tpService.listar());

		}
		return "listTransPrimarias";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {

		model.put("listaTransPrimarias", tpService.listar());
		return "listTransPrimarias";

	}
}
