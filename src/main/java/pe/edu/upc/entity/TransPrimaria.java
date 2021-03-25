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
@Table(name = "transprimaria")
public class TransPrimaria implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPri;

	@NotEmpty(message = "No puede estar vacío")
	@NotBlank(message = "No puede estar en blanco")
	@Column(name = "CodTransPri", length = 60, nullable = false)
	private String CodTransPri;
	
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
	@Column(name = "Fechaelaboracionp")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date Fechaelaboracionp;
	
	
	@Column(name = "TiempoElabop")
	private Double TiempoElabop;


	public TransPrimaria() {
		super();
		// TODO Auto-generated constructor stub
	}


	public TransPrimaria(int idPri, String codTransPri, String codMaquinaria1, String porcentaje1,
			String codMaquinaria2, String porcentaje2, Date fechaelaboracionp, Double tiempoElabop) {
		super();
		this.idPri = idPri;
		CodTransPri = codTransPri;
		CodMaquinaria1 = codMaquinaria1;
		Porcentaje1 = porcentaje1;
		CodMaquinaria2 = codMaquinaria2;
		Porcentaje2 = porcentaje2;
		Fechaelaboracionp = fechaelaboracionp;
		TiempoElabop = tiempoElabop;
	}


	public int getIdPri() {
		return idPri;
	}


	public void setIdPri(int idPri) {
		this.idPri = idPri;
	}


	public String getCodTransPri() {
		return CodTransPri;
	}


	public void setCodTransPri(String codTransPri) {
		CodTransPri = codTransPri;
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


	public Date getFechaelaboracionp() {
		return Fechaelaboracionp;
	}


	public void setFechaelaboracionp(Date fechaelaboracionp) {
		Fechaelaboracionp = fechaelaboracionp;
	}


	public Double getTiempoElabop() {
		return TiempoElabop;
	}


	public void setTiempoElabop(Double tiempoElabop) {
		TiempoElabop = tiempoElabop;
	}


		

}
