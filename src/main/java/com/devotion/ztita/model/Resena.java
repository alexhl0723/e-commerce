package com.devotion.ztita.model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;


/**
 * The persistent class for the resenas database table.
 * 
 */
@Entity
@Table(name="resenas")
@NamedQuery(name="Resena.findAll", query="SELECT r FROM Resena r")
public class Resena implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_resena")
	private int idResena;

	private int calificacion;

	@Lob
	private String comentario;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_resena")
	private Date fechaResena;

	@Column(name="id_usuario")
	private int idUsuario;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="id_producto")
	private Producto producto;

	public Resena() {
	}

	public int getIdResena() {
		return this.idResena;
	}

	public void setIdResena(int idResena) {
		this.idResena = idResena;
	}

	public int getCalificacion() {
		return this.calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Date getFechaResena() {
		return this.fechaResena;
	}

	public void setFechaResena(Date fechaResena) {
		this.fechaResena = fechaResena;
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}