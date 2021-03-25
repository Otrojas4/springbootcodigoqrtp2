package pe.edu.upc.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "transsecundaria")
public class TransSecundaria implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSec;

	@NotEmpty(message = "No puede estar vacío")
	@NotBlank(message = "No puede estar en blanco")
	@Column(name = "CodTransSec", length = 60, nullable = false)
	private String CodTransSec;
	
	@NotEmpty(message = "No puede estar vacío")
	@NotBlank(message = "No puede estar en blanco")
	@Column(name = "CodMaquinaria1", length = 60, nullable = false)
	private String CodMaquinaria1;
	
	@Size (min=2 ,max=4)
	@NotEmpty(message = "No puede estar vacío")
	@NotBlank(message = "No puede estar en blanco")
	@Column(name = "Porcentaje1", length = 4, nullable = false)
	private String Porcentaje1;
	
	@NotEmpty(message = "No puede estar vacío")
	@NotBlank(message = "No puede estar en blanco")
	@Column(name = "CodMaquinaria2", length = 60, nullable = false)
	private String CodMaquinaria2;
	
	@Size (min=2 ,max=4)
	@NotEmpty(message = "No puede estar vacío")
	@NotBlank(message = "No puede estar en blanco")
	@Column(name = "Porcentaje2", length = 4, nullable = false)
	private String Porcentaje2;
	
	@NotNull
	@Past(message = "No puedes seleccionar un dia que todavia no existe")
	@Temporal(TemporalType.DATE)
	@Column(name = "Fechaelaboracions")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date Fechaelaboracions;
	
	
	@Column(name = "TiempoElabos")
	private Double TiempoElabos;


	public TransSecundaria() {
		super();
		// TODO Auto-generated constructor stub
	}


	public TransSecundaria(int idSec, String codTransSec, String codMaquinaria1, String porcentaje1,
			String codMaquinaria2, String porcentaje2, Date fechaelaboracions, Double tiempoElabos) {
		super();
		this.idSec = idSec;
		CodTransSec = codTransSec;
		CodMaquinaria1 = codMaquinaria1;
		Porcentaje1 = porcentaje1;
		CodMaquinaria2 = codMaquinaria2;
		Porcentaje2 = porcentaje2;
		Fechaelaboracions = fechaelaboracions;
		TiempoElabos = tiempoElabos;
	}


	public int getIdSec() {
		return idSec;
	}


	public void setIdSec(int idSec) {
		this.idSec = idSec;
	}


	public String getCodTransSec() {
		return CodTransSec;
	}


	public void setCodTransSec(String codTransSec) {
		CodTransSec = codTransSec;
	}


	public String getCodMaquinaria1() {
		return CodMaquinaria1;
	}


	public void setCodMaquinaria1(String codMaquinaria1) {
		CodMaquinaria1 = codMaquinaria1;
	}


	public String getPorcentaje1() {
		return Porcentaje1;
	}


	public void setPorcentaje1(String porcentaje1) {
		Porcentaje1 = porcentaje1;
	}


	public String getCodMaquinaria2() {
		return CodMaquinaria2;
	}


	public void setCodMaquinaria2(String codMaquinaria2) {
		CodMaquinaria2 = codMaquinaria2;
	}


	public String getPorcentaje2() {
		return Porcentaje2;
	}


	public void setPorcentaje2(String porcentaje2) {
		Porcentaje2 = porcentaje2;
	}


	public Date getFechaelaboracions() {
		return Fechaelaboracions;
	}


	public void setFechaelaboracions(Date fechaelaboracions) {
		Fechaelaboracions = fechaelaboracions;
	}


	public Double getTiempoElabos() {
		return TiempoElabos;
	}


	public void setTiempoElabos(Double tiempoElabos) {
		TiempoElabos = tiempoElabos;
	}


	
	
}
