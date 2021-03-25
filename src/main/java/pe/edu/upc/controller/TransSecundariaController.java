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


import pe.edu.upc.entity.TransSecundaria;

import pe.edu.upc.service.ITransSecundariaService;



@Controller
@RequestMapping("/transsecundaria")
public class TransSecundariaController {

	@Autowired
	private ITransSecundariaService tsService;
	
	@RequestMapping("/")
	public String irCodigoqr(Map<String, Object> model) {
		model.put("listaTransSecundarias", tsService.listar());
		return "listTransSecundarias";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("transsecundaria", new TransSecundaria());
		return "transsecundaria";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid TransSecundaria objTs, BindingResult binRes, Model model)
			throws ParseException {
		if (binRes.hasErrors()) {
			return "transsecundaria";
		}
		boolean flag = tsService.insertar(objTs);
			
			if (flag) {
				return "redirect:/transsecundaria/listar/";
			} else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/transsecundaria/irRegistrar";
			}
	
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				tsService.eliminar (id);
				model.put("listaTransSecundarias", tsService.listar());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar una Transformación asignada");
			model.put("listaTransSecundarias", tsService.listar());

		}
		return "listTransSecundarias";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {

		model.put("listaTransSecundarias", tsService.listar());
		return "listTransSecundarias";

	}
}
