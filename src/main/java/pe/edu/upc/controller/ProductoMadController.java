package pe.edu.upc.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.net.MalformedURLException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.Map;

import javax.imageio.ImageIO;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;


import pe.edu.upc.entity.ProductoMad;
import pe.edu.upc.service.IProductoMadService;
import pe.edu.upc.service.ITransPrimariaService;
import pe.edu.upc.service.ITransSecundariaService;
import pe.edu.upc.service.IUploadFileService;




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
	
	@Autowired
	private ResourceLoader resourceLoader;
	
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
			public String actualizarsi(@ModelAttribute @Valid ProductoMad objMad, BindingResult binRes, Model model,
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
			
		@RequestMapping("/irwriteQR/{id}")
			public String irwriteQR(@PathVariable int id, Model model, RedirectAttributes objRedir) throws WriterException, IOException {
				ProductoMad objMad = madService.listarId(id);
				String qrCodePath = writeQR(objMad);
				model.addAttribute("code", qrCodePath);
	
				return "QRcode";
			
			}
			@GetMapping("/readQR")
			public String verifyQR(@RequestParam(value="qrImage") String qrImage, Model model) throws Exception {
				model.addAttribute("content", readQR(qrImage));
				model.addAttribute("code", qrImage);
				return "QRcode";

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
			
			/*
		
			@RequestMapping(value = "/qrcode/{enlace}",method = RequestMethod.GET)
			public void qrcode(@PathVariable("enlace") String enlace , HttpServletResponse response) throws Exception{
				
				response.setContentType("image/png");
				OutputStream outputStream = response.getOutputStream();
				outputStream.write(ZHingHelper.getQRCodeImage(enlace, 200, 200));
				outputStream.flush();
				outputStream.close();
				
				
			}*/
			
		
	/* CAMBIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACA PARA USAR EN LOCAL HOST EL LINK HA ENCRIPTAR*/
			private String writeQR(ProductoMad request) throws WriterException, IOException {
				String qcodePath = "src/main/resources/static/imagenes/" + request.getCodProducto() + "-QRCode.png";
				QRCodeWriter qrCodeWriter = new QRCodeWriter();
				BitMatrix bitMatrix = qrCodeWriter.encode("http://spring-boot-wood-qr.azurewebsites.net/mad/verMad/" + request.getId(), BarcodeFormat.QR_CODE, 250, 250);
				Path path = FileSystems.getDefault().getPath(qcodePath);
				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
				return "/imagenes/" + request.getCodProducto()+ "-QRCode.png";
			}
			
			private String readQR(String qrImage) throws Exception {
				final Resource fileResource = resourceLoader.getResource("classpath:static/" + qrImage);
				File QRfile = fileResource.getFile();
				BufferedImage bufferedImg = ImageIO.read(QRfile);
				LuminanceSource source = new BufferedImageLuminanceSource(bufferedImg);
				BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
				Result result = new MultiFormatReader().decode(bitmap);
				System.out.println("Barcode Format: " + result.getBarcodeFormat());
				System.out.println("Content: " + result.getText());
				return result.getText();

			}
			
}
