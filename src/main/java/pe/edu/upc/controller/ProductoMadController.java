package pe.edu.upc.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import pe.edu.upc.entity.ProductoMad;
import pe.edu.upc.service.IProductoMadService;
import pe.edu.upc.service.ITransPrimariaService;
import pe.edu.upc.service.ITransSecundariaService;
import pe.edu.upc.service.IUploadFileService;
import pe.edu.upc.serviceImpl.ZHingHelper;



@Controller
@RequestMapping("/mad")
public class ProductoMadController {
	@Autowired
	private IProductoMadService madService;
	@Autowired
	private IUploadFileService uploadFileService;

	@Autowired
	private ITransPrimariaService tpService;
	
	@Autowired
	private ITransSecundariaService tsService;
	
	@RequestMapping("/bienvenido")
	public String irRaceBienvenido() {
		return "bienvenido";
	}
	

	
	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

		Resource recurso = null;

		try {
			recurso = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
	}
	
	
	
	
	// DETALLE MASCOTA
		// METODO PARA VER EL DETALLE EMPLEADO
			@GetMapping(value = "/ver/{id}")
			public String ver(@PathVariable(value = "id") Integer id, Map<String, Object> model, RedirectAttributes flash) {

				// Obtenemos el Empleado por el ID
				ProductoMad objMad = madService.findOne(id);

				// Validamos el empleado
				if (objMad == null) {
					flash.addFlashAttribute("error", "El producto no existe en la base de datos");
					return "redirect:/mad/listar";
				}

				model.put("mad", objMad);
				model.put("nameProducto", "Detalle del producto: " + objMad.getNombreProducto());

				return "mad/ver";
			}
			
			
			@RequestMapping("/")
			public String irProducto(Map<String, Object> model) {
				model.put("listaMaderas", madService.listar());
				return "listMadera";
			}
			
			
			@RequestMapping("/irRegistrar")
			public String irRegistrar(Model model) {
				
				
				model.addAttribute("mad", new ProductoMad());
				model.addAttribute("listaTransPrimarias", tpService.listar());
				model.addAttribute("listaTransSecundarias", tsService.listar());
				return "mad";
				
			}
			
			
			
			@RequestMapping("/registrar")
			public String registrar(@Valid ProductoMad objMad, BindingResult binRes, Model model,
					@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) {

				if (binRes.hasErrors()) {
					model.addAttribute("listaTransPrimarias", tpService.listar());
					model.addAttribute("listaTransSecundarias", tsService.listar());
					return "mad";
				}

				if (!foto.isEmpty()) {

					if (objMad.getId() != 0 && objMad.getId() > 0 && objMad.getFoto() != null
							&& objMad.getFoto().length() > 0) {

						uploadFileService.delete(objMad.getFoto());
					}

					////
					String uniqueFilename = null;
					try {
						uniqueFilename = uploadFileService.copy(foto);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
					// pasar el nombre de la Foto al Empleado
					objMad.setFoto(uniqueFilename);

				}

				boolean flag = madService.insertar(objMad);
				if (flag) {
					return "redirect:/mad/listar";
				} else {
					model.addAttribute("mensaje", "Ocurrió un error");
					return "redirect:/mad/irRegistrar";
				}

			}
			
			
			
			@RequestMapping(value = "/verMad/{id}")
			public String ver(@PathVariable int id, Map<String, Object> model, RedirectAttributes flash) {

				// Obtenemos el Empleado por el ID
				ProductoMad mad = madService.findOne(id); 

				// Validamos el pet
				if (mad == null) {
					flash.addFlashAttribute("error", "El producto no existe en la base de datos");
					return "redirect:/mad/listar";
				}

				model.put("mad", mad);
				model.put("titulo", "Detalle del producto: " + mad.getNombreProducto());

				return "/verMad";
			}
				
			
			@RequestMapping("/actualizar")
			public String actualizar(@ModelAttribute @Valid ProductoMad objMad, BindingResult binRes, Model model,
					RedirectAttributes objRedir) throws ParseException {
				if (binRes.hasErrors()) {
					return "redirect:/mad/listar";
				} else {
					boolean flag = madService.modificar(objMad);

					if (flag) {
						objRedir.addFlashAttribute("mensaje", "Se actualizó correctamente");
						return "redirect:/mad/listar";

					} else {
						objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
						return "redirect:/mad/listar";
					}
				}
			}
			
			
			
			@RequestMapping("/modificar/{id}")
			public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) {
				ProductoMad objMad = madService.listarId(id);
				if (objMad == null) {
					objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
					return "redirect:/mad/listar";
				} else {
					
					model.addAttribute("mad", objMad);
					return "mad";
				}
			}
			
			
			@RequestMapping("/listar")
			public String listar(Map<String, Object> model) {

				model.put("listaMaderas", madService.listar());
				return "listMadera";

			}
			
			@RequestMapping(value = "/eliminar/{id}")
			public String eliminar(@PathVariable(value = "id") Integer id,
					RedirectAttributes flash) {

				if (id != null && id > 0) {
					ProductoMad objMad = madService.findOne(id);
					madService.eliminar(id);
					
					flash.addFlashAttribute("success", "Producto eliminado con exito!");
					
					if (uploadFileService.delete(objMad.getFoto())) {
						flash.addFlashAttribute("info", "Foto " + objMad.getFoto() + " eliminada con exito!");
					}
				}
				return "listMadera";
			}
			
			
		
			@RequestMapping(value = "/qrcode/{codProducto}",method = RequestMethod.GET)
			public void qrcode(@PathVariable(value = "codProducto") String codProducto , HttpServletResponse response) throws Exception{
				
				response.setContentType("image/png");
				OutputStream outputStream = response.getOutputStream();
				outputStream.write(ZHingHelper.getQRCodeImage(codProducto, 200, 200));
				outputStream.flush();
				outputStream.close();
				
				
			}
			
			
	

			
			
}
