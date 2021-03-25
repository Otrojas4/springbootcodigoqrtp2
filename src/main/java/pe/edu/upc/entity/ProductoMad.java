package pe.edu.upc.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "mad")
public class ProductoMad implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty(message = "No puede estar vacío")
	@NotBlank(message = "No puede estar en blanco")
	@Column(name = "codProducto", length = 60, nullable = false)
	private String codProducto;
	
	@NotEmpty(message = "No puede estar vacío")
	@NotBlank(message = "No puede estar en blanco")
	@Column(name = "CodGuiaForestal", length = 60, nullable = false)
	private String CodGuiaForestal;
	
	@Size (min=2 ,max=50)
	@NotEmpty(message="No puede estar vacío")
	@NotBlank(message="No puede estar en blanco")
	@Column(name = "NombreProducto", length = 50, nullable = false)
	private String NombreProducto;

	@Size (min=2 ,max=50)
	@NotEmpty(message="No puede estar vacío")
	@NotBlank(message="No puede estar en blanco")
	@Column(name = "NombreAserradero", length = 50, nullable = false)
	private String NombreAserradero;
	
	@NotNull
	@Past(message = "No puedes seleccionar un dia que todavia no existe")
	@Temporal(TemporalType.DATE)
	@Column(name = "Fechaelaboracion")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date Fechaelaboracion;
	
	
	@NotEmpty(message = "No puede estar vacío")
	@NotBlank(message = "No puede estar en blanco")
	@Column(name = "codPlancha", length = 60, nullable = false)
	private String codPlancha;

	@Column(name = "TiempoElabo")
	private Double TiempoElabo;
	
	@Size (min=2 ,max=50)
	@NotEmpty(message="No puede estar vacío")
	@NotBlank(message="No puede estar en blanco")
	@Column(name = "TipoMadera", length = 50, nullable = false)
	private String TipoMadera;

	
	private String foto;
	
	@ManyToOne
	@JoinColumn(name = "idPri", nullable = false)
	private TransPrimaria transprimaria;
	
	@ManyToOne
	@JoinColumn(name = "idSec", nullable = false)
	private TransSecundaria transsecundaria;

	public ProductoMad() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductoMad(int id, String codProducto, String codGuiaForestal, String nombreProducto,
			String nombreAserradero, Date fechaelaboracion, String codPlancha, Double tiempoElabo, String tipoMadera,
			String foto, TransPrimaria transprimaria, TransSecundaria transsecundaria) {
		super();
		this.id = id;
		this.codProducto = codProducto;
		CodGuiaForestal = codGuiaForestal;
		NombreProducto = nombreProducto;
		NombreAserradero = nombreAserradero;
		Fechaelaboracion = fechaelaboracion;
		this.codPlancha = codPlancha;
		TiempoElabo = tiempoElabo;
		TipoMadera = tipoMadera;
		this.foto = foto;
		this.transprimaria = transprimaria;
		this.transsecundaria = transsecundaria;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodProducto() {
		return codProducto;
	}

	public void setCodProducto(String codProducto) {
		this.codProducto = codProducto;
	}

	public String getCodGuiaForestal() {
		return CodGuiaForestal;
	}

	public void setCodGuiaForestal(String codGuiaForestal) {
		CodGuiaForestal = codGuiaForestal;
	}

	public String getNombreProducto() {
		return NombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		NombreProducto = nombreProducto;
	}

	public String getNombreAserradero() {
		return NombreAserradero;
	}

	public void setNombreAserradero(String nombreAserradero) {
		NombreAserradero = nombreAserradero;
	}

	public Date getFechaelaboracion() {
		return Fechaelaboracion;
	}

	public void setFechaelaboracion(Date fechaelaboracion) {
		Fechaelaboracion = fechaelaboracion;
	}

	public String getCodPlancha() {
		return codPlancha;
	}

	public void setCodPlancha(String codPlancha) {
		this.codPlancha = codPlancha;
	}

	public Double getTiempoElabo() {
		return TiempoElabo;
	}

	public void setTiempoElabo(Double tiempoElabo) {
		TiempoElabo = tiempoElabo;
	}

	public String getTipoMadera() {
		return TipoMadera;
	}

	public void setTipoMadera(String tipoMadera) {
		TipoMadera = tipoMadera;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public TransPrimaria getTransprimaria() {
		return transprimaria;
	}

	public void setTransprimaria(TransPrimaria transprimaria) {
		this.transprimaria = transprimaria;
	}

	public TransSecundaria getTranssecundaria() {
		return transsecundaria;
	}

	public void setTranssecundaria(TransSecundaria transsecundaria) {
		this.transsecundaria = transsecundaria;
	}

	
	


	
	
}
